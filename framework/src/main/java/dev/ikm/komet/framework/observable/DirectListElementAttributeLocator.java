package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.common.binary.Decoder;
import dev.ikm.tinkar.common.binary.DecoderInput;
import dev.ikm.tinkar.common.binary.Encoder;
import dev.ikm.tinkar.common.binary.EncoderOutput;

/**
 * Represents a specific field element within a list of components in the observable field framework.
 * This record implements the {@link AttributeLocator} interface and provides a mechanism to locate fields
 * by their category and position index within a list.
 * <p>
 * The category of this field element is defined by a {@link AttributeCategory}, which organizes
 * fields based on their context, such as components, versions, patterns, semantics, or stamps. This
 * ensures classification and consistency in field identification.
 * <p>
 * The index specifies the position of the field within the list, allowing precise access to individual
 * elements in contexts involving lists of fields.
 * <p>
 * This record is part of the overall field location system used within the observable field framework,
 * simplifying operations like field categorization and retrieval.
 *
 * @param category the category of the field as defined by {@link AttributeCategory}
 * @param index the position of the field within a list
 */
public record DirectListElementAttributeLocator(AttributeCategory category,
                                                int index) implements DirectAttributeLocator {

    @Encoder
    @Override
    public void subEncode(EncoderOutput out) {
        out.writeString(category.name());
        out.writeInt(index);
    }

    @Decoder
    public static DirectListElementAttributeLocator decode(DecoderInput in) {
        AttributeCategory category = AttributeCategory.valueOf(in.readString());
        int index = in.readInt();
        return new DirectListElementAttributeLocator(category, index);
    }

    /**
     * Compares this {@code FieldLocator} instance with another {@code FieldLocator} instance to determine their order.
     * The comparison is based on the specific type of {@code FieldLocator} and additional attributes, if applicable.
     *
     * @param locator the {@code FieldLocator} instance to compare against
     * @return a positive integer if this instance should be ordered after the specified {@code FieldLocator},
     *         a negative integer if this instance should be ordered before, or zero if they are equal
     */
    public int compareTo(AttributeLocator locator) {
        return switch (locator) {
            case DirectSingularAttributeLocator _ -> 1;
            case DirectListElementAttributeLocator componentFieldListElementLocator ->
                    Integer.compare(this.index, componentFieldListElementLocator.index());
            case AssociatedSingularAttributeLocator _ -> -1;
            case AssociatedListElementAttributeLocator _  -> -1;
        };
    }
}
