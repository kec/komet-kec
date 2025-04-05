package dev.ikm.komet.framework.observable.locators;

import dev.ikm.komet.framework.observable.*;
import dev.ikm.tinkar.common.binary.Decoder;
import dev.ikm.tinkar.common.binary.DecoderInput;
import dev.ikm.tinkar.common.binary.Encoder;
import dev.ikm.tinkar.common.binary.EncoderOutput;

import java.util.Optional;

public record DirectSingularAttributeLocatorWithObservable(AttributeCategory category,
                                                           ObservableAttribute observableAttribute)
        implements DirectAttributeLocator, ObservableAttributeWithLocator {
    @Encoder
    @Override
    public void subEncode(EncoderOutput out) {
        out.writeString(category.name());
        out.writeBoolean(observableAttribute.containingComponent() instanceof ObservableVersion<?>);
        out.writeNid(observableAttribute.containingComponent().nid());
        switch (observableAttribute.containingComponent()) {
            case ObservableEntity<?> observableEntity -> out.writeNid(observableEntity.versions().get(0).stampNid());
            case ObservableVersion<?> observableVersion -> out.writeNid(observableVersion.stampNid());
        }
    }

    @Decoder
    public static DirectSingularAttributeLocatorWithObservable decode(DecoderInput in) {
        AttributeCategory category = AttributeCategory.valueOf(in.readString());
        boolean isObservableVersion = in.readBoolean();
        int containingEntityNid = in.readNid();
        int stampNid = in.readNid();
        ObservableEntity<?> containingEntity = ObservableEntity.get(containingEntityNid);
        if (isObservableVersion) {
            Optional<? extends ObservableVersion<?>> optionalVersion = containingEntity.getVersion(stampNid);
            if (optionalVersion.isPresent()) {
                ObservableVersion<?> version = optionalVersion.get();
                DirectSingularAttributeLocator directLocator = new DirectSingularAttributeLocator(category);
                ObservableAttribute observableAttribute = AttributeLocator.locateComponentAttribute(directLocator, version);
                return new DirectSingularAttributeLocatorWithObservable(category, observableAttribute);
            }
        }
        DirectSingularAttributeLocator directLocator = new DirectSingularAttributeLocator(category);
        ObservableAttribute observableAttribute = AttributeLocator.locateComponentAttribute(directLocator, containingEntity);
        return new DirectSingularAttributeLocatorWithObservable(category, observableAttribute);
    }
}
