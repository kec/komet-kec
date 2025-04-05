package dev.ikm.komet.framework.observable.locators;

import dev.ikm.komet.framework.observable.AttributeCategory;
import dev.ikm.komet.framework.observable.AttributeLocator;
import dev.ikm.tinkar.common.binary.Decoder;
import dev.ikm.tinkar.common.binary.DecoderInput;
import dev.ikm.tinkar.common.binary.Encoder;
import dev.ikm.tinkar.common.binary.EncoderOutput;

/**
 * Represents a specific attribute element associated with a component in the observable attribute framework.
 * This class implements the {@link AttributeLocator} interface and provides a way to locate fields by their category.
 * <p>
 * The category of this attribute element is defined by a {@link AttributeCategory}, which classifies
 * and organizes fields based on their context within the framework. Each category corresponds to a
 * specific type of attribute, such as fields related to components, versions, patterns, semantics, or stamps.
 * <p>
 * This class is used to facilitate the identification and handling of fields within the observable
 * component framework, ensuring consistency and clear attribute categorization in various contexts.
 * <p>
 * It is part of the attribute location system within the observable attribute framework and contributes to
 * simplifying attribute-based operations like retrieval and categorization.
 *
 * @param category the category of the attribute as defined by {@link AttributeCategory}
 */
public record DirectSingularAttributeLocator(AttributeCategory category) implements DirectAttributeLocator {
    @Encoder
    @Override
    public void subEncode(EncoderOutput out) {
        out.writeString(category.name());
    }

    @Decoder
    public static DirectSingularAttributeLocator decode(DecoderInput in) {
        AttributeCategory category = AttributeCategory.valueOf(in.readString());
        return new DirectSingularAttributeLocator(category);
    }

}
