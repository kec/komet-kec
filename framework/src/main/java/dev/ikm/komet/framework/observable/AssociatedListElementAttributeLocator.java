package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.common.binary.Decoder;
import dev.ikm.tinkar.common.binary.DecoderInput;
import dev.ikm.tinkar.common.binary.Encoder;
import dev.ikm.tinkar.common.binary.EncoderOutput;

/**
 * Represents a attribute locator for an associated component list attribute within the observable attribute framework.
 * This class implements the {@link AttributeLocator} interface and provides information about fields that
 * relate to a list of associated components. It is identified by the combination of an associated
 * component identifier, a attribute category, and an index in the list.
 * <p>
 * The associated component is represented by its numeric identifier (NID). Each list attribute is categorized
 * by a {@link AttributeCategory}, which helps define the context of the attribute within the framework,
 * such as component fields, version-specific fields, or pattern fields.
 * <p>
 * This record is used to index and retrieving fields associated with specific
 * components and their lists in an observable attribute system, ensuring proper attribute organization and access.
 *
 * @param associatedComponentNid the identifier (NID) of the associated component
 * @param category the category of the attribute as defined by {@link AttributeCategory}
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
}
