package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.common.binary.*;

/**
 * The AssociatedComponentFieldElement class represents a attribute element associated with a specific component
 * within the observable attribute framework. This class is part of the attribute location system, implementing
 * the {@link AttributeLocator} interface to facilitate attribute categorization and identification.
 * <p>
 * Each instance of this class associates a component, represented by its unique identifier (nid), with a
 * specific attribute category. The attribute category is defined by the {@link AttributeCategory} enumeration,
 * which helps classify fields based on their context, such as component-related fields, version-specific
 * fields, or semantic fields.
 * <p>
 * This class is generally used to locate and manage fields tied to components, leveraging the framework's
 * attribute-based operation mechanisms. It contributes to simplifying attribute identification, retrieval, and
 * categorization within the observable attribute framework, ensuring consistent and organized handling of
 * attribute-related operations.
 *
 * @param associatedComponentNid the unique nid (native identifier) of the associated component
 * @param category the attribute category as specified by {@link AttributeCategory}
 */
public record AssociatedSingularAttributeLocator(int associatedComponentNid, AttributeCategory category)
        implements AssociatedAttributeLocator {

    @Encoder
    @Override
    public void subEncode(EncoderOutput out) {
        out.writeNid(associatedComponentNid);
        out.writeString(category.name());
    }

    @Decoder
    public static AssociatedSingularAttributeLocator decode(DecoderInput in) {
        int associatedComponentNid = in.readNid();
        AttributeCategory category = AttributeCategory.valueOf(in.readString());
        return new AssociatedSingularAttributeLocator(associatedComponentNid, category);
    }

}
