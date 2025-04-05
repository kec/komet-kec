package dev.ikm.komet.framework.observable;

import dev.ikm.komet.framework.observable.locators.DirectAttributeLocator;
import dev.ikm.komet.framework.observable.locators.DirectListElementLocatorWithObservable;
import dev.ikm.komet.framework.observable.locators.DirectSingularAttributeLocatorWithObservable;

public sealed interface ObservableAttributeWithLocator extends DirectAttributeLocator
        permits DirectListElementLocatorWithObservable, DirectSingularAttributeLocatorWithObservable {

    ObservableAttribute observableAttribute();
}
