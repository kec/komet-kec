package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.component.FieldDataType;
import dev.ikm.tinkar.terms.ConceptFacade;
import dev.ikm.tinkar.terms.ConceptToDataType;
import dev.ikm.tinkar.terms.EntityProxy;
import dev.ikm.tinkar.terms.PatternFacade;

/*
ComponentFeature?

DT extends Feature?

Always has a value?

 */
public sealed interface LocatableFeature
        permits FeatureList, ObservableFieldAbstract, ObservableFieldDefinition, ObservableVersion, Feature {

    /*
    If I just implement ObservableFeature as:

    Meaning, Purpose, Value, and disconnect from all the other classes:

    DT can be:
        Object (Traditional Semantic Field)
        FieldDefinition (Pattern field definitions)
        List<ObservableFeature>

ObservableFeatureField

Field<LocatableField>


    ObservableDefinition can be encapsulated by a feature, but not a feature itself.

    ObservableFeature
        ObservableField
            ObservableFieldDirect
            ObservableFieldIndirect
        ObservableFeatureList<ObservableField>
     */

    FeatureLocator locator();

    ObservableComponent containingComponent();

    int patternNid();

    default PatternFacade pattern() {
        return EntityProxy.Pattern.make(patternNid());
    }

    int indexInPattern();

    int patternVersionStampNid();

    int meaningNid();

    default ConceptFacade meaning() {
        return EntityProxy.Concept.make(meaningNid());
    }

    int purposeNid();

    default ConceptFacade purpose() {
        return EntityProxy.Concept.make(purposeNid());
    }

    int dataTypeNid();

    default ConceptFacade dataType() {
        return EntityProxy.Concept.make(dataTypeNid());
    }

    default FieldDataType fieldDataType() {
        return ConceptToDataType.convert(dataType());
    }

}
