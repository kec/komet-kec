package dev.ikm.komet.framework.observable;

public sealed interface AssociatedAttributeLocator extends AttributeLocator
        permits AssociatedSingularAttributeLocator, AssociatedListElementAttributeLocator {
    int associatedComponentNid();

    default DirectAttributeLocator componentFieldLocator() {
        return switch (this) {
            case AssociatedListElementAttributeLocator associatedComponentFieldListElement ->
                    new DirectListElementAttributeLocator(associatedComponentFieldListElement.category(),
                            associatedComponentFieldListElement.index());
            case AssociatedSingularAttributeLocator associatedComponentField ->
                    new DirectSingularAttributeLocator(associatedComponentField.category());
        };
    }
}

