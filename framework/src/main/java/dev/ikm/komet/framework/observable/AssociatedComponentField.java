package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.common.binary.*;

/**
 * The AssociatedComponentFieldElement class represents a field element associated with a specific component
 * within the observable field framework. This class is part of the field location system, implementing
 * the {@link FieldLocator} interface to facilitate field categorization and identification.
 * <p>
 * Each instance of this class associates a component, represented by its unique identifier (nid), with a
 * specific field category. The field category is defined by the {@link FieldCategory} enumeration,
 * which helps classify fields based on their context, such as component-related fields, version-specific
 * fields, or semantic fields.
 * <p>
 * This class is generally used to locate and manage fields tied to components, leveraging the framework's
 * field-based operation mechanisms. It contributes to simplifying field identification, retrieval, and
 * categorization within the observable field framework, ensuring consistent and organized handling of
 * field-related operations.
 *
 * @param associatedComponentNid the unique nid (native identifier) of the associated component
 * @param category the field category as specified by {@link FieldCategory}
 */
public record AssociatedComponentField(int associatedComponentNid, FieldCategory category)
        implements FieldLocatorForAssociatedComponent {

    @Encoder
    @Override
    public void subEncode(EncoderOutput out) {
        out.writeNid(associatedComponentNid);
        out.writeString(category.name());
    }

    @Decoder
    public static AssociatedComponentField decode(DecoderInput in) {
        int associatedComponentNid = in.readNid();
        FieldCategory category = FieldCategory.valueOf(in.readString());
        return new AssociatedComponentField(associatedComponentNid, category);
    }
    /**
     * Compares this {@code FieldLocator} with the specified {@code FieldLocator} for order.
     * This comparison is primarily based on the implementation type of the provided {@code FieldLocator}.
     * If both locators are instances of {@code FieldLocatorForAssociatedComponent}, a further comparison is
     * performed based on the associated component's native identifier (nid) and, if necessary, the field category.
     *
     * @param fieldLocator the {@code FieldLocator} instance to be compared against this locator.
     *                     Must be a non-null instance of {@code FieldLocator}.
     * @return a negative integer, zero, or a positive integer as this locator is less than, equal to,
     *         or greater than the specified {@code fieldLocator}. Specifically:
     *         - Returns {@code 1} if the provided locator is an instance of {@code FieldLocatorForComponent}.
     *         - Returns the result of comparing the associated component nids if both locators are instances
     *           of {@code FieldLocatorForAssociatedComponent}, or
     *         - Returns the result of comparing the field categories in case the nids are equal.
     */
    @Override
    public int compareTo(FieldLocator fieldLocator) {
        return switch (fieldLocator) {
            case FieldLocatorForComponent _ -> 1;
            case FieldLocatorForAssociatedComponent locator -> {
                if (locator.associatedComponentNid() == this.associatedComponentNid) {
                    yield this.category.compareTo(locator.category());
                }
                yield Integer.compare(this.associatedComponentNid, locator.associatedComponentNid());
            }
        };
    }

}
