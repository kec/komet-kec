package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.component.FieldDataType;
import dev.ikm.tinkar.terms.ConceptFacade;
import dev.ikm.tinkar.terms.ConceptToDataType;
import dev.ikm.tinkar.terms.EntityProxy;
import dev.ikm.tinkar.terms.PatternFacade;
import javafx.beans.property.ObjectProperty;

public interface ObservableFeatureField<DT> {

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

    DT value();

    ObjectProperty<DT> valueProperty();

}