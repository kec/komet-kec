package dev.ikm.komet.framework.observable.locators;

import dev.ikm.komet.framework.observable.AttributeCategory;
import dev.ikm.komet.framework.observable.AttributeLocator;
import dev.ikm.tinkar.common.binary.Decoder;
import dev.ikm.tinkar.common.binary.DecoderInput;
import dev.ikm.tinkar.common.binary.Encoder;
import dev.ikm.tinkar.common.binary.EncoderOutput;

/**
 * Represents a specific attribute element within a list of components in the observable attribute framework.
 * This record implements the {@link AttributeLocator} interface and provides a mechanism to locate fields
 * by their category and position index within a list.
 * <p>
 * The category of this attribute element is defined by a {@link AttributeCategory}, which organizes
 * fields based on their context, such as components, versions, patterns, semantics, or stamps. This
 * ensures classification and consistency in attribute identification.
 * <p>
 * The index specifies the position of the attribute within the list, allowing precise access to individual
 * elements in contexts involving lists of fields.
 * <p>
 * This record is part of the overall attribute location system used within the observable attribute framework,
 * simplifying operations like attribute categorization and retrieval.
 *
 * @param category the category of the attribute as defined by {@link AttributeCategory}
 * @param index the position of the attribute within a list
 */
public record DirectListElementLocator(AttributeCategory category,
                                       int index) implements DirectAttributeLocator {

    @Encoder
    @Override
    public void subEncode(EncoderOutput out) {
        out.writeString(category.name());
        out.writeInt(index);
    }

    @Decoder
    public static DirectListElementLocator decode(DecoderInput in) {
        AttributeCategory category = AttributeCategory.valueOf(in.readString());
        int index = in.readInt();
        return new DirectListElementLocator(category, index);
    }
}
