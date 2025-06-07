package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.common.binary.Encodable;
import dev.ikm.tinkar.common.binary.EncoderOutput;

/**
 * If we want to include a dynamic value for a locator, such as specifying a locator for a specific pattern,
 * we can't use enums. We could use a record that contains an enum. But that is redundant, perhaps.
 */
public sealed interface FeatureLocator extends Encodable {
    static FeatureLocator anyVersion() {
        return FeatureLocator.Chronology.VersionListItem(FeatureLocator.WILDCARD);
    }
    class Chronology {
        static ChronologyProperty.PublicId PublicId() {
            return new PublicIdLocator();
        }
        static ChronologyProperty.PublicId PublicId(int nid) {
            return new PublicIdLocator(nid);
        }
        static ChronologyProperty.VersionList VersionList() {
            return new VersionListLocator();
        }
        static ChronologyProperty.VersionList VersionList(int nid) {
            return new VersionListLocator(nid);
        }
        static ChronologyProperty.VersionListItem VersionListItem(int nid, int index) {
            return new VersionListItemLocator(index);
        }
        static ChronologyProperty.VersionListItem VersionListItem(int index) {
            return new VersionListItemLocator(index);
        }
        static ChronologyProperty.Semantic.Pattern SemanticPattern() {
            return new PatternForSemanticLocator();
        }
        static ChronologyProperty.Semantic.Pattern SemanticPattern(int nid) {
            return new PatternForSemanticLocator(nid);
        }
        static ChronologyProperty.Semantic.ReferencedComponent SemanticReferencedComponent() {
            return new ReferencedComponentForSemanticLocator();
        }
        static ChronologyProperty.Semantic.ReferencedComponent SemanticReferencedComponent(int nid) {
            return new ReferencedComponentForSemanticLocator(nid);
        }
    }
    class Version {
        static VersionProperty.VersionStamp VersionStamp(int nid, int stampNid) {
            return new VersionStampLocator(nid, stampNid);
        }
        static VersionProperty.VersionStamp VersionStamp() {
            return new VersionStampLocator();
        }
        static VersionProperty.Pattern.PatternMeaning PatternMeaning() {
            return new PatternMeaningLocator();
        }
        static VersionProperty.Pattern.PatternMeaning PatternMeaning(int nid, int stampNid) {
            return new PatternMeaningLocator(nid, stampNid);
        }
        static VersionProperty.Pattern.PatternPurpose PatternPurpose() {
            return new PatternPurposeLocator();
        }
        static VersionProperty.Pattern.PatternPurpose PatternPurpose(int nid, int stampNid) {
            return new PatternPurposeLocator(nid, stampNid);
        }
        static VersionProperty.Pattern.FieldDefinitionList PatternFieldDefinitionList() {
            return new FieldDefinitionListLocator();
        }
        static VersionProperty.Pattern.FieldDefinitionList PatternFieldDefinitionList(int nid, int stampNid) {
            return new FieldDefinitionListLocator(nid, stampNid);
        }
        static VersionProperty.Pattern.FieldDefinitionListItem PatternFieldDefinitionListItem(int index) {
            return new FieldDefinitionListItemLocator(index, FeatureLocator.WILDCARD);
        }
        static VersionProperty.Pattern.FieldDefinitionListItem PatternFieldDefinitionListItem(int index, int patternNid) {
            return new FieldDefinitionListItemLocator(index, patternNid);
        }
        static VersionProperty.Pattern.FieldDefinitionListItem PatternFieldDefinitionListItem(int nid, int index, int patternNid, int stampNid) {
            return new FieldDefinitionListItemLocator(nid, index, patternNid, stampNid);
        }
        static VersionProperty.Semantic.FieldList SemanticFieldList() {
            return new SemanticFieldListLocator();
        }
        static VersionProperty.Semantic.FieldList SemanticFieldList(int nid, int stampNid) {
            return new SemanticFieldListLocator(nid, stampNid);
        }
        static VersionProperty.Semantic.FieldListItem SemanticFieldListItem(int index) {
            return new SemanticFieldListItemLocator(index, FeatureLocator.WILDCARD);
        }
        static VersionProperty.Semantic.FieldListItem SemanticFieldListItem(int index, int patternNid) {
            return new SemanticFieldListItemLocator(index, patternNid);
        }
        static VersionProperty.Semantic.FieldListItem SemanticFieldListItem(int nid, int index, int patternNid, int stampNid) {
            return new SemanticFieldListItemLocator(nid, index, patternNid, stampNid);
        }
        static VersionProperty.Stamp.Status StampStatus(int nid) {
            return new StatusForStampLocator(nid, nid);
        }
        static VersionProperty.Stamp.Status StampStatus() {
            return new StatusForStampLocator();
        }
        static VersionProperty.Stamp.Time StampTime() {
            return new TimeForStampLocator();
        }
        static VersionProperty.Stamp.Time StampTime(int nid) {
            return new TimeForStampLocator(nid, nid);
        }
        static VersionProperty.Stamp.Author StampAuthor() {
            return new AuthorForStampLocator();
        }
        static VersionProperty.Stamp.Author StampAuthor(int nid) {
            return new AuthorForStampLocator(nid, nid);
        }
        static VersionProperty.Stamp.Module StampModule() {
            return new ModuleForStampLocator();
        }
        static VersionProperty.Stamp.Module StampModule(int nid) {
            return new ModuleForStampLocator(nid, nid);
        }
        static VersionProperty.Stamp.Path StampPath() {
            return new PathForStampLocator();
        }
        static VersionProperty.Stamp.Path StampPath(int nid) {
            return new PathForStampLocator(nid, nid);
        }
    }


    int WILDCARD = Integer.MAX_VALUE;

    /**
     * Native identifier or wildcard for the component being located.
     * @return
     */
    int nid();

    default boolean match(FeatureLocator another) {
        return match(this, another);
    }
    @Override
    default void encode(EncoderOutput out) {
        throw new UnsupportedOperationException();
    }

    /**
     * Determines whether two {@link FeatureLocator} objects match based on specific conditions.
     * This method checks for equality and implements additional matching rules for specific
     * subclasses of {@link FeatureLocator}, such as {@link FieldDefinitionListItemLocator} and
     * {@link SemanticFieldListItemLocator}. Note that a wildcard will match any number (index, nid, or patternNid),
     * but that any specific number will not match a wildcard. Matches are therefore order-dependent. A.matches(B)
     * does not guarantee that B.matches(A).
     *
     * @param first the first {@link FeatureLocator} to compare.
     * @param second the second {@link FeatureLocator} to compare.
     * @return true if the two {@link FeatureLocator} objects are considered a match based on
     *         equality or the defined rules for a pattern wildcard, false otherwise.
     */
    static boolean match(FeatureLocator first, FeatureLocator second) {
        if (first.getClass() != second.getClass()) {
            return false;
        }
        if (first.equals(second)) {
            return true;
        }
        // Classes are equal, but fields are not. Check for wildcards.
        return switch (first) {

            case VersionListItemLocator firstLocator
                when second instanceof VersionListItemLocator secondLocator ->
                    (firstLocator.nid() == WILDCARD || firstLocator.nid() == secondLocator.nid())
                    && (firstLocator.index() == WILDCARD || firstLocator.index() == secondLocator.index());

            case FieldDefinitionListItemLocator firstLocator
                    when second instanceof FieldDefinitionListItemLocator secondLocator ->
                        (firstLocator.nid() == WILDCARD || firstLocator.nid() == secondLocator.nid())
                        && (firstLocator.patternNid() == WILDCARD || firstLocator.patternNid() == secondLocator.patternNid())
                        && (firstLocator.stampNid() == WILDCARD || firstLocator.stampNid() == secondLocator.stampNid())
                        && (firstLocator.index() == WILDCARD || firstLocator.index() == secondLocator.index());

            case SemanticFieldListItemLocator firstLocator
                    when second instanceof SemanticFieldListItemLocator secondLocator ->
                        (firstLocator.nid() == WILDCARD || firstLocator.nid() == secondLocator.nid())
                        && (firstLocator.patternNid() == WILDCARD || firstLocator.patternNid() == secondLocator.patternNid())
                        && (firstLocator.stampNid() == WILDCARD || firstLocator.stampNid() == secondLocator.stampNid())
                        && (firstLocator.index() == WILDCARD || firstLocator.index() == secondLocator.index());

            // All other classes have only nids: no index, patternNid, or stampNid.
            default -> first.nid() == WILDCARD;
        };
    }

    /**
     * Represents an item in a list with an index property.
     * This interface provides a method to retrieve a locator that contains the index of the item
     * and serves as a base for various types of list items with specific characteristics.
     *
     * <p>
     * Key Characteristics:
     * <p>- A sealed interface, ensuring the implementation subclasses are finite and known.
     * <p>- Permits derivation by specific list item types with additional properties or behaviors.
     * <p>- Focuses on maintaining an index for each item.
     * <p>
     * Implementing classes are expected to refine this interface to represent specific
     * types of list items while adhering to the index-based organizational model.
     */
    sealed interface ListItem {
        int index();
    }
    /**
     * Represents a locator for property of a chronology entity. This interface acts as the root
     * of a hierarchical structure that defines various property locators associated with
     * a chronology, such as identifiers, versions, and semantics. It extends the
     * {@link FeatureLocator} interface and utilizes the sealed interface mechanism to
     * restrict and organize its subtypes, ensuring that all possible subtypes
     * are explicitly declared and known at compile time.
     */
    sealed interface ChronologyProperty extends FeatureLocator {
        sealed interface PublicId extends ChronologyProperty {}
        /**
         * Represents a version list property within the chronology framework. The `VersionList`
         * interface extends the `ChronologyProperty` interface and is a sealed interface
         * restricted to known implementations. This allows for controlled subtype hierarchies
         * directly related to version list functionalities.
         * <p>
         * Key characteristics:
         * <p> - Provides a method to retrieve an instance of a version list locator.
         * <p> - Acts as a specific property type in the broader chronology property hierarchy.
         * <p> - Designed to ensure compile-time safety for supported implementations.
         *
         */
        sealed interface VersionList extends ChronologyProperty {}
        /**
         * Represents an item in a version list as part of a chronology property hierarchy.
         * This interface extends both `ListItem` and `ChronologyProperty` to define an item
         * that is associated with a specific index and belongs to the version-related scope
         * of a chronology.
         *
         * Key characteristics:
         * - Sealed interface ensuring implementations are finite and explicitly defined.
         * - Combines indexing capabilities from `ListItem` with properties tied to
         *   chronology versioning.
         * - Provides a static factory method to retrieve a specific implementation
         *   of `VersionListItem` based on its index.
         *
         * Method Details:
         * - `get(int index)`: Retrieves an instance of `VersionListItem` with the specified index.
         *   This is implemented via an underlying constructor call to the appropriate subtype
         *   (`VersionListItemLocator`).
         *
         * Intended use cases include scenarios requiring the association of specific
         * indices with properties in a versioned chronology data structure.
         */
        sealed interface VersionListItem extends ListItem, ChronologyProperty {}
        sealed interface Semantic extends ChronologyProperty {
            sealed interface Pattern extends Semantic {}
            sealed interface ReferencedComponent extends Semantic {}
        }
    }
    sealed interface VersionProperty extends FeatureLocator {
        int stampNid();
        sealed interface PatternDefinedItem extends VersionProperty {
            //TODO: We are transitioning to all Features being pattern defined items. Need to consider how to update.
            int patternNid();
        }
        sealed interface VersionStamp extends VersionProperty {}
        sealed interface Pattern extends VersionProperty {
            sealed interface PatternMeaning extends Pattern {}
            sealed interface PatternPurpose extends Pattern {}
            sealed interface FieldDefinitionList extends Pattern {}
            sealed interface FieldDefinitionListItem extends ListItem, PatternDefinedItem {}
        }
        sealed interface Semantic extends VersionProperty {
            sealed interface FieldList extends Semantic {}
            sealed interface FieldListItem extends ListItem, PatternDefinedItem, Semantic {}
        }
        sealed interface Stamp extends VersionProperty {
            sealed interface Status extends Stamp {}
            sealed interface Time extends Stamp {}
            sealed interface Author extends Stamp {}
            sealed interface Module extends Stamp {}
            sealed interface Path extends Stamp {}
        }
    }

}

record PublicIdLocator(int nid) implements FeatureLocator.ChronologyProperty.PublicId {
    public PublicIdLocator() {this(FeatureLocator.WILDCARD);} }
record VersionListLocator(int nid) implements FeatureLocator.ChronologyProperty.VersionList {
    public VersionListLocator() {this(FeatureLocator.WILDCARD);} }
record VersionListItemLocator(int nid, int index) implements FeatureLocator.ChronologyProperty.VersionListItem {
    public VersionListItemLocator(int index) {this(FeatureLocator.WILDCARD, index);} }
record PatternForSemanticLocator(int nid) implements FeatureLocator.ChronologyProperty.Semantic.Pattern {
    public PatternForSemanticLocator() {this(FeatureLocator.WILDCARD);} }
record ReferencedComponentForSemanticLocator(int nid) implements FeatureLocator.ChronologyProperty.Semantic.ReferencedComponent {
    public ReferencedComponentForSemanticLocator() {this(FeatureLocator.WILDCARD);} }
record VersionStampLocator(int nid, int stampNid) implements FeatureLocator.VersionProperty.VersionStamp {
    public VersionStampLocator() {this(FeatureLocator.WILDCARD, FeatureLocator.WILDCARD);}
}
record StatusForStampLocator(int nid, int stampNid) implements FeatureLocator.VersionProperty.Stamp.Status {
    public StatusForStampLocator() {this(FeatureLocator.WILDCARD, FeatureLocator.WILDCARD);} }
record TimeForStampLocator(int nid, int stampNid) implements FeatureLocator.VersionProperty.Stamp.Time {
    public TimeForStampLocator() {this(FeatureLocator.WILDCARD, FeatureLocator.WILDCARD);} }
record AuthorForStampLocator(int nid, int stampNid) implements FeatureLocator.VersionProperty.Stamp.Author {
    public AuthorForStampLocator() {this(FeatureLocator.WILDCARD, FeatureLocator.WILDCARD);} }
record ModuleForStampLocator(int nid, int stampNid) implements FeatureLocator.VersionProperty.Stamp.Module {
    public ModuleForStampLocator() {this(FeatureLocator.WILDCARD, FeatureLocator.WILDCARD);} }
record PathForStampLocator(int nid, int stampNid) implements FeatureLocator.VersionProperty.Stamp.Path {
    public PathForStampLocator() {this(FeatureLocator.WILDCARD, FeatureLocator.WILDCARD);} }
record PatternMeaningLocator(int nid, int stampNid) implements FeatureLocator.VersionProperty.Pattern.PatternMeaning {
    public PatternMeaningLocator() {this(FeatureLocator.WILDCARD, FeatureLocator.WILDCARD);} }
record PatternPurposeLocator(int nid, int stampNid) implements FeatureLocator.VersionProperty.Pattern.PatternPurpose {
    public PatternPurposeLocator() {this(FeatureLocator.WILDCARD, FeatureLocator.WILDCARD);} }
record FieldDefinitionListLocator(int nid, int stampNid) implements FeatureLocator.VersionProperty.Pattern.FieldDefinitionList {
    public FieldDefinitionListLocator() {this(FeatureLocator.WILDCARD, FeatureLocator.WILDCARD);} }
record FieldDefinitionListItemLocator(int nid, int index, int patternNid, int stampNid) implements FeatureLocator.VersionProperty.Pattern.FieldDefinitionListItem {
    public FieldDefinitionListItemLocator(int index, int patternNid) {this(FeatureLocator.WILDCARD, index, patternNid, FeatureLocator.WILDCARD);} }
record SemanticFieldListLocator(int nid, int stampNid) implements FeatureLocator.VersionProperty.Semantic.FieldList {
    public SemanticFieldListLocator() {this(FeatureLocator.WILDCARD, FeatureLocator.WILDCARD);} }
record SemanticFieldListItemLocator(int nid, int index, int patternNid, int stampNid) implements FeatureLocator.VersionProperty.Semantic.FieldListItem {
    public SemanticFieldListItemLocator(int index, int patternNid) {this(FeatureLocator.WILDCARD, index, patternNid, FeatureLocator.WILDCARD);} }
