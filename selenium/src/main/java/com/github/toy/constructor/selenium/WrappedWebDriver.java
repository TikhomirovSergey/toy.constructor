package com.github.toy.constructor.selenium;

import com.github.toy.constructor.core.api.cleaning.Refreshable;
import com.github.toy.constructor.core.api.cleaning.Stoppable;
import com.github.toy.constructor.selenium.properties.SupportedWebDrivers;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.sf.cglib.proxy.Enhancer;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.grid.internal.utils.configuration.StandaloneConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.remote.server.SeleniumServer;

import java.lang.reflect.Constructor;
import java.net.URL;

import static com.github.toy.constructor.core.api.utils.ConstructorUtil.findSuitableConstructor;
import static com.github.toy.constructor.selenium.properties.FlagProperties.*;
import static com.github.toy.constructor.selenium.properties.URLProperties.BASE_WEB_DRIVER_URL_PROPERTY;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static org.openqa.selenium.net.PortProber.findFreePort;

public class WrappedWebDriver implements WrapsDriver, Refreshable, Stoppable {

    private final static String DEFAULT_LOCAL_HOST = "http://localhost:%s/wd/hub";
    private static SeleniumServer server;
    private static boolean serverStarted;
    private static URL serverUrl;

    private final SupportedWebDrivers supportedWebDriver;
    private WebDriver driver;
    private boolean isWebDriverInstalled;

    public WrappedWebDriver(SupportedWebDrivers supportedWebDriver) {
        this.supportedWebDriver = supportedWebDriver;
    }

    private static synchronized void initServerLocally() {
        if (server != null && serverStarted) {
            return;
        }
        serverStarted = false;
        StandaloneConfiguration standAloneConfig = new StandaloneConfiguration();
        int serverPort = findFreePort();
        standAloneConfig.port = serverPort;
        server = new SeleniumServer(standAloneConfig);
        try {
            serverStarted = server.boot();
            serverUrl = new URL(format(DEFAULT_LOCAL_HOST, serverPort));
        }
        catch (Throwable e) {
            serverStarted = false;
            server = null;
            throw new RuntimeException(e);
        }
    }

    private static synchronized void shutDownServerLocally() {
        if (server == null || serverStarted) {
            return;
        }

        try {
            server.stop();
            server = null;
            serverStarted = false;
        }
        catch (Throwable ignored) {
        }
    }

    private void initDriverIfNecessary() {
        driver = ofNullable(driver).orElseGet(() -> {
            Object[] parameters;
            Object[] arguments = supportedWebDriver.get();
            if (supportedWebDriver.requiresRemoteUrl() && supportedWebDriver.getRemoteURL() == null) {
                initServerLocally();
                parameters = ArrayUtils.addAll(new Object[] {serverUrl}, arguments);
            }
            else {
                parameters = arguments;
            }

            try {
                Constructor<? extends WebDriver> c = findSuitableConstructor(supportedWebDriver.getWebDriverClass(),
                        parameters);

                if (!isWebDriverInstalled) {
                    ofNullable(supportedWebDriver.getWebDriverManager())
                            .ifPresent(WebDriverManager::setup);
                    isWebDriverInstalled = true;
                }

                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(supportedWebDriver.getWebDriverClass());
                enhancer.setCallback(new WebDriverMethodInterceptor());

                WebDriver driver = (WebDriver) enhancer.create(c.getParameterTypes(), parameters);
                ofNullable(BASE_WEB_DRIVER_URL_PROPERTY.get())
                        .ifPresent(url -> driver.get(url.toString()));
                if (FORCE_WINDOW_MAXIMIZING_ON_START.get()) {
                    driver.manage().window().maximize();
                }
                return driver;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void refresh() {
        if (driver == null) {
            return;
        }

        boolean isAlive;
        try {
            driver.getCurrentUrl();
            isAlive = true;
        }
        catch (WebDriverException e) {
            isAlive = false;
        }

        if (!isAlive) {
            driver = null;
            return;
        }

        if (!KEEP_WEB_DRIVER_SESSION_OPENED.get()) {
            driver.quit();
            driver = null;
            return;
        }

        if (CLEAR_WEB_DRIVER_COOKIES.get()) {
            driver.manage().deleteAllCookies();
        }

        if (GET_BACK_TO_BASE_URL.get()) {
            ofNullable(BASE_WEB_DRIVER_URL_PROPERTY.get()).ifPresent(url ->
                    driver.get(url.toString()));
        }
    }

    @Override
    public WebDriver getWrappedDriver() {
        initDriverIfNecessary();
        return driver;
    }

    @Override
    public void shutDown() {
        ofNullable(driver).ifPresent(webDriver -> {
            try {
                webDriver.quit();
            }
            catch (Throwable ignored) {}
        });

        shutDownServerLocally();
    }
}
