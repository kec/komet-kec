package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.coordinate.stamp.calculator.Latest;
import dev.ikm.tinkar.coordinate.stamp.calculator.StampCalculator;
import dev.ikm.tinkar.entity.FieldDefinitionRecord;
import dev.ikm.tinkar.entity.FieldRecord;
import dev.ikm.tinkar.entity.PatternEntityVersion;

public class FieldLocatorListElementForVersion {
    static <T> ObservableField<T> locate(ObservableVersion observableVersion,
                                         DirectListElementAttributeLocator componentFieldListElementLocator,
                                         StampCalculator stampCalculator) {
        return switch (observableVersion) {
            case ObservableConceptVersion observableConceptVersion -> locateFieldListElementOnConceptVersion(observableConceptVersion, componentFieldListElementLocator, stampCalculator);
            case ObservablePatternVersion observablePatternVersion -> locateFieldListElementOnPatternVersion(observablePatternVersion, componentFieldListElementLocator, stampCalculator);
            case ObservableSemanticVersion observableSemanticVersion -> locateFieldListElementOnSemanticVersion(observableSemanticVersion, componentFieldListElementLocator, stampCalculator);
            case ObservableStampVersion observableStampVersion -> locateFieldListElementOnStampVersion(observableStampVersion, componentFieldListElementLocator, stampCalculator);
        };
    }

    static <T> ObservableField<T> locateFieldListElementOnStampVersion(ObservableStampVersion stampVersion,
                                                                       DirectListElementAttributeLocator componentFieldListElementLocator,
                                                                       StampCalculator stampCalculator) {
        throw new IllegalStateException("There are no list element fields on a stamp version");
    }

    static <T> ObservableField<T> locateFieldListElementOnSemanticVersion(ObservableSemanticVersion semanticVersion,
                                                                          DirectListElementAttributeLocator componentFieldListElementLocator,
                                                                          StampCalculator stampCalculator) {
        Latest<PatternEntityVersion> latestPatternVersion = stampCalculator.latestPatternEntityVersion(semanticVersion.patternNid());
        if (latestPatternVersion.isPresent()) {
            return semanticVersion.fields(latestPatternVersion.get()).get(componentFieldListElementLocator.index());
        }
        throw new IllegalStateException("There is no latest pattern version for: \n\n" + latestPatternVersion +
                "\n\n of semantic: \n\n" + semanticVersion);
    }


    static <T> ObservableField<T> locateFieldListElementOnPatternVersion(ObservablePatternVersion patternVersion,
                                                                         DirectListElementAttributeLocator componentFieldListElementLocator,
                                                                         StampCalculator stampCalculator) {
            return  switch(componentFieldListElementLocator.category()) {
                case PATTERN_FIELD_DEFINITION -> {
                    ObservableFieldDefinition patternVersionListElement = patternVersion.fieldDefinitions().get(componentFieldListElementLocator.index());
                    FieldDefinitionRecord fieldDefinitionRecord =  patternVersionListElement.fieldDefinitionReference.get();
                    FieldRecord fieldRecord = new FieldRecord(patternVersionListElement, patternVersion.nid(), patternVersion.stampNid(), fieldDefinitionRecord);
                    ObservableField observableField = new ObservableField(fieldRecord);
                    yield observableField;
                }
                case PUBLIC_ID_FIELD, COMPONENT_VERSIONS_LIST, COMPONENT_VERSION, VERSION_STAMP_FIELD,
                     PATTERN_VERSION, PATTERN_MEANING_FIELD, PATTERN_PURPOSE_FIELD, PATTERN_FIELD_DEFINITION_LIST,
                     SEMANTIC_VERSION, SEMANTIC_PATTERN_FIELD, SEMANTIC_REFERENCED_COMPONENT_FIELD, SEMANTIC_FIELD_LIST, SEMANTIC_FIELD,
                     STAMP_VERSION, STATUS_FIELD, TIME_FIELD, AUTHOR_FIELD, MODULE_FIELD, PATH_FIELD ->
                        throw new IllegalStateException("There are no fields of type "  + componentFieldListElementLocator.category()
                                + " a semantic version: " + patternVersion);
            };
    }

    static <T> ObservableField<T> locateFieldListElementOnConceptVersion(ObservableConceptVersion conceptVersion,
                                                                         DirectListElementAttributeLocator componentFieldListElementLocator,
                                                                         StampCalculator stampCalculator) {
        throw new IllegalStateException("There are no list element fields on a stamp version");
    }
}
