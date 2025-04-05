package dev.ikm.komet.framework.observable;

import dev.ikm.komet.framework.observable.locators.DirectAttributeLocator;
import dev.ikm.komet.framework.observable.locators.DirectListElementLocator;
import dev.ikm.komet.framework.observable.locators.DirectSingularAttributeLocator;

public sealed interface AssociatedAttributeLocator extends AttributeLocator
        permits AssociatedSingularAttributeLocator, AssociatedListElementAttributeLocator {
    int associatedComponentNid();

    default DirectAttributeLocator componentFieldLocator() {
        return switch (this) {
            case AssociatedListElementAttributeLocator associatedComponentFieldListElement ->
                    new DirectListElementLocator(associatedComponentFieldListElement.category(),
                            associatedComponentFieldListElement.index());
            case AssociatedSingularAttributeLocator associatedComponentField ->
                    new DirectSingularAttributeLocator(associatedComponentField.category());
        };
    }
}

