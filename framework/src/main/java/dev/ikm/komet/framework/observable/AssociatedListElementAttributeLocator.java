package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.common.binary.Decoder;
import dev.ikm.tinkar.common.binary.DecoderInput;
import dev.ikm.tinkar.common.binary.Encoder;
import dev.ikm.tinkar.common.binary.EncoderOutput;

/**
 * Represents a field locator for an associated component list field within the observable field framework.
 * This class implements the {@link AttributeLocator} interface and provides information about fields that
 * relate to a list of associated components. It is identified by the combination of an associated
 * component identifier, a field category, and an index in the list.
 * <p>
 * The associated component is represented by its numeric identifier (NID). Each list field is categorized
 * by a {@link AttributeCategory}, which helps define the context of the field within the framework,
 * such as component fields, version-specific fields, or pattern fields.
 * <p>
 * This record is used to index and retrieving fields associated with specific
 * components and their lists in an observable field system, ensuring proper field organization and access.
 *
 * @param associatedComponentNid the identifier (NID) of the associated component
 * @param category the category of the field as defined by {@link AttributeCategory}
 * @param index the index within the list of associated fields
 */
public record AssociatedListElementAttributeLocator(int associatedComponentNid,
                                                    AttributeCategory category,
                                                    int index) implements AssociatedAttributeLocator {
    @Encoder
    @Override
    public void subEncode(EncoderOutput out) {
        out.writeNid(associatedComponentNid);
        out.writeString(category.name());
        out.writeInt(index);
    }

    @Decoder
    public static AssociatedListElementAttributeLocator decode(DecoderInput in) {
        int associatedComponentNid = in.readNid();
        AttributeCategory category = AttributeCategory.valueOf(in.readString());
        int index = in.readInt();
        return new AssociatedListElementAttributeLocator(associatedComponentNid, category, index);
    }

    /**
     * Compares this {@code AssociatedComponentFieldListElement} instance with another instance of {@code FieldLocator}.
     * The comparison is performed based on the type of the provided {@code FieldLocator} and, if applicable,
     * specific properties of the {@code AssociatedComponentFieldListElement}.
     *
     * @param locator the {@code FieldLocator} instance to compare this object to
     * @return a negative integer, zero, or a positive integer as this instance is less than,
     *         equal to, or greater than the specified {@code FieldLocator} instance
     */
    @Override
    public int compareTo(AttributeLocator locator) {
        return switch (locator) {
            case DirectSingularAttributeLocator _ -> 1;
            case DirectListElementAttributeLocator _ -> 1;
            case AssociatedSingularAttributeLocator _ -> 1;
            case AssociatedListElementAttributeLocator associatedComponentFieldListElement -> {
                if (associatedComponentFieldListElement.associatedComponentNid() == this.associatedComponentNid) {
                    yield this.category.compareTo(associatedComponentFieldListElement.category());
                }
                yield Integer.compare(this.associatedComponentNid, associatedComponentFieldListElement.associatedComponentNid());
            }
         };
    }
}
