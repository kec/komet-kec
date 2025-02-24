package dev.ikm.komet.framework.observable;

/**
 * Enum representing the sources of fields for different components within the system.
 * Each constant specifies a specific component source that manages or categorizes
 * sets of fields or characteristics within the observable framework.
 * <p>
 * The component field sources provide a means to group and associate fields to a
 * specific entity type or version type (e.g., component, version, pattern, etc.).
 * These groupings help with the identification and management of fields in
 * observable frameworks, facilitating field retrieval and configuration logic.
 * <p>
 *  <p>COMPONENT: Indicates that the source is an abract component.
 *  <p>PATTERN_VERSION: Indicates that the source is a pattern's version.
 *  <p>SEMANTIC: Indicates that the source is a semantic.
 *  <p>SEMANTIC_VERSION: Indicates that the source is a semantic's version.
 *  <p>STAMP_VERSION: Indicates that the source is a stamp version.
 */
public enum ComponentFieldSource {
    COMPONENT,
    PATTERN_VERSION,
    SEMANTIC,
    SEMANTIC_VERSION,
    STAMP_VERSION
}
