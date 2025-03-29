package dev.ikm.komet.framework.observable;

public sealed interface DirectAttributeLocator extends AttributeLocator
    permits DirectSingularAttributeLocator, DirectListElementAttributeLocator {
}
