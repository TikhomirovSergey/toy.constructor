package com.github.toy.constructor.selenium.functions.target.locator.window;

import com.github.toy.constructor.core.api.SequentialGetStepSupplier;
import com.github.toy.constructor.selenium.SeleniumSteps;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

import static com.github.toy.constructor.core.api.StoryWriter.toGet;
import static com.github.toy.constructor.selenium.functions.target.locator.window.GetWindowSupplier.window;

public final class GetWindowPositionSupplier extends SequentialGetStepSupplier<SeleniumSteps, Point, Window, GetWindowPositionSupplier> {

    private GetWindowPositionSupplier() {
        super();
    }

    /**
     * Builds a function which gets position of the first window.
     *
     * @return Supplier of a function which gets position.
     */
    public static GetWindowPositionSupplier windowPosition() {
        return positionOf(window());
    }

    /**
     * Builds a function which gets position of some window.
     *
     * @param supplier is how to get the window which should return position
     * @return Supplier of a function which gets position.
     */
    public static GetWindowPositionSupplier positionOf(GetWindowSupplier supplier) {
        return new GetWindowPositionSupplier().from(supplier);
    }

    /**
     * Builds a function which gets position of the given window.
     *
     * @param window to get position of
     * @return Supplier of a function which gets position.
     */
    public static GetWindowPositionSupplier positionOf(Window window) {
        return new GetWindowPositionSupplier().from(window);
    }

    @Override
    protected Function<Window, Point> getEndFunction() {
        return toGet("Position of the window", WebDriver.Window::getPosition);
    }
}
