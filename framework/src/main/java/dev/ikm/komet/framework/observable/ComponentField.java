package dev.ikm.komet.framework.observable;

public enum ComponentField {
    PUBLIC_ID_FIELD(ComponentFieldSource.COMPONENT),
    COMPONENT_VERSIONS_FIELD(ComponentFieldSource.COMPONENT),
    PATTERN_MEANING_FIELD(ComponentFieldSource.PATTERN_VERSION),
    PATTERN_PURPOSE_FIELD(ComponentFieldSource.PATTERN_VERSION),
    PATTERN_FIELD_DEFINITION_LIST(ComponentFieldSource.PATTERN_VERSION),
    PATTERN_FIELD_DEFINITION(ComponentFieldSource.PATTERN_VERSION, true),
    SEMANTIC_PATTERN_FIELD(ComponentFieldSource.SEMANTIC),
    SEMANTIC_REFERENCED_COMPONENT_FIELD(ComponentFieldSource.SEMANTIC),
    SEMANTIC_FIELD_LIST(ComponentFieldSource.SEMANTIC_VERSION),
    SEMANTIC_FIELD(ComponentFieldSource.SEMANTIC_VERSION, true),
    STATUS_FIELD(ComponentFieldSource.STAMP_VERSION),
    TIME_FIELD(ComponentFieldSource.STAMP_VERSION),
    AUTHOR_FIELD(ComponentFieldSource.STAMP_VERSION),
    MODULE_FIELD(ComponentFieldSource.STAMP_VERSION),
    PATH_FIELD(ComponentFieldSource.STAMP_VERSION);

    final ComponentFieldSource source;
    final boolean indexed;

    ComponentField(ComponentFieldSource componentFieldSource) {
        this.source = componentFieldSource;
        this.indexed = false;
    }
    ComponentField(ComponentFieldSource componentFieldSource, boolean indexed) {
        this.source = componentFieldSource;
        this.indexed = indexed;
    }

    private static final ComponentFieldSet COMPONENT_FIELDS = new ComponentFieldSet(PUBLIC_ID_FIELD, COMPONENT_VERSIONS_FIELD);
    private static final ComponentFieldSet PATTERN_VERSION_FIELDS = new ComponentFieldSet(PUBLIC_ID_FIELD, PATTERN_MEANING_FIELD, PATTERN_PURPOSE_FIELD, PATTERN_FIELD_DEFINITION_LIST,
            STATUS_FIELD, TIME_FIELD, AUTHOR_FIELD, MODULE_FIELD, PATH_FIELD);

    private static final ComponentFieldSet SEMANTIC_FIELDS = new ComponentFieldSet(PUBLIC_ID_FIELD, SEMANTIC_PATTERN_FIELD, SEMANTIC_REFERENCED_COMPONENT_FIELD, COMPONENT_VERSIONS_FIELD);
    private static final ComponentFieldSet SEMANTIC_VERSION_FIELDS = new ComponentFieldSet(PUBLIC_ID_FIELD, SEMANTIC_PATTERN_FIELD, SEMANTIC_REFERENCED_COMPONENT_FIELD, SEMANTIC_FIELD_LIST,
            STATUS_FIELD, TIME_FIELD, AUTHOR_FIELD, MODULE_FIELD, PATH_FIELD);

    private static final ComponentFieldSet STAMP_VERSION_FIELDS = new ComponentFieldSet(PUBLIC_ID_FIELD,
            STATUS_FIELD, TIME_FIELD, AUTHOR_FIELD, MODULE_FIELD, PATH_FIELD);
    public static ComponentFieldSet componentFields() {
        return COMPONENT_FIELDS;
    }

    public static ComponentFieldSet conceptFields() {
        return COMPONENT_FIELDS;
    }

    public static ComponentFieldSet patternFields() {
        return COMPONENT_FIELDS;
    }

    public static ComponentFieldSet semanticFields() {
        return SEMANTIC_FIELDS;
    }

    public static ComponentFieldSet stampFields() {
        return COMPONENT_FIELDS;
    }

    public static ComponentFieldSet conceptVersionFields() {
        return STAMP_VERSION_FIELDS;
    }

    public static ComponentFieldSet semanticVersionFields() {
        return SEMANTIC_VERSION_FIELDS;
    }

    public static ComponentFieldSet patternVersionFields() {
        return PATTERN_VERSION_FIELDS;
    }

    public static ComponentFieldSet stampVersionFields() {
        return STAMP_VERSION_FIELDS;
    }

    public static ComponentFieldSet fieldsForObservable(ObservableEntity observableEntity) {
        return switch (observableEntity) {
            case ObservableConcept _ -> conceptFields();
            case ObservablePattern _ -> patternFields();
            case ObservableSemantic _ -> semanticFields();
            case ObservableStamp _ -> stampFields();
        };
    }

    public static ComponentFieldSet fieldsForObservable(ObservableEntitySnapshot observableSnapshot) {
        return switch (observableSnapshot) {
            case ObservableConceptSnapshot _ -> conceptFields();
            case ObservablePatternSnapshot _ -> patternFields();
            case ObservableSemanticSnapshot _ -> semanticFields();
        };
    }

    public static ComponentFieldSet fieldsForObservable(ObservableVersion observableVersion) {
        return switch (observableVersion) {
            case ObservableConceptVersion _ -> conceptVersionFields();
            case ObservableSemanticVersion _ -> semanticVersionFields();
            case ObservablePatternVersion _ -> patternVersionFields();
            case ObservableStampVersion _ -> stampVersionFields();
        };
    }

}
