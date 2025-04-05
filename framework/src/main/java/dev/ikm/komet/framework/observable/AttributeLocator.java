package dev.ikm.komet.framework.observable;

import dev.ikm.komet.framework.observable.locators.*;
import dev.ikm.tinkar.common.binary.*;

public sealed interface AttributeLocator extends Encodable, Comparable<AttributeLocator>
        permits AssociatedAttributeLocator, DirectAttributeLocator {

    enum PermittedImplementation {
        AssociatedComponentAttribute(AssociatedSingularAttributeLocator.class),
        AssociatedComponentAttributeListElement(AssociatedListElementAttributeLocator.class),
        ComponentAttributeLocator(DirectSingularAttributeLocator.class),
        ComponentFieldAttributeElementLocator(DirectListElementLocator.class);

        final Class implementationCLass;

        PermittedImplementation(Class implementationCLass) {
            this.implementationCLass = implementationCLass;
        }

        static PermittedImplementation getForClass(AttributeLocator attributeLocator) {
            for (PermittedImplementation permittedImplementation : PermittedImplementation.values()) {
                if (permittedImplementation.implementationCLass.equals(attributeLocator.getClass())) {
                    return permittedImplementation;
                }
            }
            throw new IllegalStateException("AttributeLocator " + attributeLocator + " is not available. ");
        }
    }

    AttributeCategory category();

    default ObservableAttribute get(ObservableComponent observableComponent) {
        return switch (this) {
            case AssociatedAttributeLocator associatedComponentLocator -> {
                ObservableComponent associatedComponent = ObservableEntity.get(associatedComponentLocator.associatedComponentNid());
                yield associatedComponentLocator.componentFieldLocator().get(associatedComponent);
            }
            case DirectAttributeLocator componentLocator ->
                    locateComponentAttribute(componentLocator, observableComponent);
        };
    }

    static ObservableAttribute locateComponentAttribute(DirectAttributeLocator componentLocator, ObservableComponent observableComponent) {

        return switch (componentLocator) {
            case DirectSingularAttributeLocator locator -> switch (observableComponent) {
                case ObservableEntity observableEntity ->
                        AttributeFinderForComponent.locate(observableEntity, locator);
                case ObservableVersion observableVersion ->
                        AttributeFinderForComponent.locate(observableVersion, locator);
            };

            case DirectListElementLocator locator -> switch (observableComponent) {
                case ObservableEntity observableEntity ->
                        AttributeFinderForComponent.locate(observableEntity, locator);
                case ObservableVersion observableVersion ->
                        AttributeFinderForComponent.locate(observableVersion, locator);
            };
            case DirectListElementLocatorWithObservable observable -> observable.observableAttribute();
            case DirectSingularAttributeLocatorWithObservable observable -> observable.observableAttribute();
            case ObservableAttributeWithLocator observable -> observable.observableAttribute();
        };
    }

    @Encoder
    default void encode(EncoderOutput out) {
        PermittedImplementation implementation = PermittedImplementation.getForClass(this);
        out.writeString(implementation.name());
        this.subEncode(out);
    }

    void subEncode(EncoderOutput out);

    @Decoder
    static AttributeLocator decode(DecoderInput in) {
        String implementationName = in.readString();
        switch (PermittedImplementation.valueOf(implementationName)) {
            case ComponentAttributeLocator -> DirectSingularAttributeLocator.decode(in);
            case ComponentFieldAttributeElementLocator -> DirectListElementLocator.decode(in);
            case AssociatedComponentAttribute -> AssociatedSingularAttributeLocator.decode(in);
            case AssociatedComponentAttributeListElement -> AssociatedListElementAttributeLocator.decode(in);
        }
        throw new IllegalStateException("Implementation " + implementationName + " is not available. ");
    }

    interface direct {
        static DirectSingularAttributeLocator singular(AttributeCategory category) {
            return new DirectSingularAttributeLocator(category);
        }
        static DirectSingularAttributeLocatorWithObservable singularWithObservable(AttributeCategory category, ObservableAttribute ObservableAttribute) {
            return new DirectSingularAttributeLocatorWithObservable(category, ObservableAttribute);
        }

        static DirectListElementLocator list(AttributeCategory category, int index) {
            return new DirectListElementLocator(category, index);
        }
        static DirectListElementLocatorWithObservable listWithObservable(AttributeCategory category, int index, ObservableAttribute ObservableAttribute) {
            return new DirectListElementLocatorWithObservable(category, index, ObservableAttribute);
        }
    }

    interface associated {
        static AssociatedSingularAttributeLocator singular(AttributeCategory category, int associatedComponentNid) {
            return new AssociatedSingularAttributeLocator(associatedComponentNid, category);
        }

        static AssociatedListElementAttributeLocator list(AttributeCategory category, int associatedComponentNid, int index) {
            return new AssociatedListElementAttributeLocator(associatedComponentNid, category, index);
        }
    }

    /**
     * Determines if this {@link AttributeLocator} is equal to the specified {@link AttributeLocator}.
     * Equality is established by comparing the ordering of the two locators using the {@code compareTo} method.
     *
     * @param otherLocator the {@link AttributeLocator} to be compared with this {@link AttributeLocator}.
     * @return true if the two {@link AttributeLocator} objects are considered equal, false otherwise.
     */
    default boolean equals(AttributeLocator otherLocator) {
        return this.compareTo(otherLocator) == 0;
    }

    /**
     * Compares this {@link AttributeLocator} object with the specified {@link AttributeLocator} for order.
     * The comparison is primarily based on the properties and type of the provided locators.
     *
     * @param otherLocator the {@link AttributeLocator} to be compared against this {@link AttributeLocator}.
     * @return a negative integer, zero, or a positive integer as this {@link AttributeLocator}
     *         is less than, equal to, or greater than the specified {@link AttributeLocator},
     *         based on their properties and type.
     */
    default int compareTo(AttributeLocator otherLocator) {
        return compareTo(this, otherLocator);
    }

    /**
     * Compares two {@link AttributeLocator} objects to determine their ordering.
     * The comparison is based on the specific type and properties of the provided locators.
     * For associated attribute locators, the comparison considers the associated component NID,
     * category, and optionally the index if they are list elements.
     * For direct attribute locators, the comparison considers the category and optionally the index if they are list elements.
     *
     * @param first the first {@link AttributeLocator} to be compared
     * @param second the second {@link AttributeLocator} to be compared
     * @return a negative integer, zero, or a positive integer as the first {@link AttributeLocator} is less than,
     * equal to, or greater than the second {@link AttributeLocator}, based on their properties
     */
    static int compareTo(AttributeLocator first, AttributeLocator second) {
        return switch (first) {
            case AssociatedAttributeLocator firstAAL -> switch (second) {
                case AssociatedAttributeLocator secondAAL -> {
                    if (firstAAL.associatedComponentNid() != secondAAL.associatedComponentNid()) {
                        yield firstAAL.associatedComponentNid() - secondAAL.associatedComponentNid();
                    }
                    if (firstAAL.category() != secondAAL.category()) {
                        yield firstAAL.category().compareTo(secondAAL.category());
                    }
                    if (first instanceof AssociatedListElementAttributeLocator firstALEL && second instanceof AssociatedListElementAttributeLocator secondALEL) {
                        yield firstALEL.index() - secondALEL.index();
                    }
                    // Components are the same, Categories are the same, but they aren't list elements
                    yield 0;
                }
                case DirectAttributeLocator secondDAL -> -1;
            };
            case DirectAttributeLocator firstDAL -> switch (second) {
                case AssociatedAttributeLocator secondAAL -> 1;
                case DirectAttributeLocator secondDAL -> {
                    if (firstDAL.category() != secondDAL.category()) {
                        yield firstDAL.category().compareTo(secondDAL.category());
                    }
                    if (first instanceof DirectListElementLocator firstDLEL && second instanceof DirectListElementLocator secondDLEL) {
                        yield firstDLEL.index() - secondDLEL.index();
                    }
                    // Categories are the same, but they aren't list elements
                    yield 0;
                }
            };
        };
    }

}