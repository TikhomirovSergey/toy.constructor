package com.github.toy.constructor.core.api;

import com.github.toy.constructor.core.api.cleaning.Refreshable;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.toy.constructor.core.api.cleaning.RefreshAndStopUtil.refresh;
import static com.github.toy.constructor.core.api.StoryWriter.action;
import static com.github.toy.constructor.core.api.StoryWriter.toGet;
import static com.github.toy.constructor.core.api.proxy.ProxyFactory.getProxied;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.emptyCollectionOf;

public class RefreshTest {

    private RefreshableStep refreshableStep;

    @Test
    public void checkRefreshTest() {
        refreshableStep = getProxied(RefreshableStep.class);
        refreshableStep.perform(action("Add elements to some list", refreshableStep1 -> {
            refreshableStep.getListToRefresh().add(1);
            refreshableStep.getListToRefresh().add(true);
            refreshableStep.getListToRefresh().add("String");
        }));

        assertThat("Check added values",
                refreshableStep.get(toGet("Get elements", RefreshableStep::getListToRefresh)),
                contains(1, true, "String"));

        refresh(this);
        assertThat("Check values",
                refreshableStep.get(toGet("Get elements", RefreshableStep::getListToRefresh)),
                emptyCollectionOf(Object.class));
    }

    @CreateWith(provider = ProviderOfEmptyParameters.class)
    private static class RefreshableStep implements PerformActionStep<RefreshableStep>, GetStep<RefreshableStep>, Refreshable {

        private final List<Object> listToRefresh = new ArrayList<>();

        protected RefreshableStep() {
            super();
        }

        @Override
        public void refresh() {
            listToRefresh.clear();
        }

        List<Object> getListToRefresh() {
            return listToRefresh;
        }
    }
}
