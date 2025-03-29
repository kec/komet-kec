package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.common.binary.*;
import dev.ikm.tinkar.coordinate.stamp.calculator.StampCalculator;

public sealed interface AttributeLocator extends Encodable, Comparable<AttributeLocator>
        permits AssociatedAttributeLocator, DirectAttributeLocator {

    enum PermittedImplementation {
        AssociatedComponentAttribute(AssociatedSingularAttributeLocator.class),
        AssociatedComponentAttributeListElement(AssociatedListElementAttributeLocator.class),
        ComponentAttributeLocator(DirectSingularAttributeLocator.class),
        ComponentFieldAttributeElementLocator(DirectListElementAttributeLocator.class);

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

    default ObservableAttribute get(ObservableComponent observableComponent, StampCalculator stampCalculator) {
        return switch (this) {
            case AssociatedAttributeLocator associatedComponentLocator -> {
                ObservableComponent associatedComponent = ObservableEntity.get(associatedComponentLocator.associatedComponentNid());
                yield associatedComponentLocator.componentFieldLocator().get(associatedComponent, stampCalculator);
            }
            case DirectAttributeLocator componentLocator ->
                    locateComponentField(componentLocator, observableComponent, stampCalculator);
        };
    }

    static ObservableAttribute locateComponentField(DirectAttributeLocator componentLocator, ObservableComponent observableComponent, StampCalculator stampCalculator) {

        return switch (componentLocator) {
            case DirectSingularAttributeLocator componentFieldLocator -> switch (observableComponent) {
                case ObservableEntity observableEntity ->
                        FieldLocatorForEntity.locate(observableEntity, componentFieldLocator, stampCalculator);
                case ObservableVersion observableVersion ->
                        FieldLocatorForVersion.locate(observableVersion, componentFieldLocator, stampCalculator);
            };

            case DirectListElementAttributeLocator componentFieldListElementLocator -> switch (observableComponent) {
                case ObservableEntity observableEntity ->
                        FieldLocatorListElementForEntity.locate(observableEntity, componentFieldListElementLocator, stampCalculator);
                case ObservableVersion observableVersion ->
                        FieldLocatorListElementForVersion.locate(observableVersion, componentFieldListElementLocator, stampCalculator);
            };
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
            case ComponentFieldAttributeElementLocator -> DirectListElementAttributeLocator.decode(in);
            case AssociatedComponentAttribute -> AssociatedSingularAttributeLocator.decode(in);
            case AssociatedComponentAttributeListElement -> AssociatedListElementAttributeLocator.decode(in);
        }
        throw new IllegalStateException("Implementation " + implementationName + " is not available. ");
    }

    interface direct {
        static DirectSingularAttributeLocator singular(AttributeCategory category) {
            return new DirectSingularAttributeLocator(category);
        }

        static DirectListElementAttributeLocator list(AttributeCategory category, int index) {
            return new DirectListElementAttributeLocator(category, index);
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
}