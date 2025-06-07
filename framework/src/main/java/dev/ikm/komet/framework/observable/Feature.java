package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.component.FieldDataType;
import dev.ikm.tinkar.component.FieldDefinition;
import dev.ikm.tinkar.terms.ConceptFacade;
import dev.ikm.tinkar.terms.ConceptToDataType;
import dev.ikm.tinkar.terms.EntityProxy;
import dev.ikm.tinkar.terms.PatternFacade;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * The Feature class represents an attribute or characteristic within an {@code ObservableComponent}.
 * It serves as a data encapsulation for metadata and associated values of a field in the component.
 * <p>
 *  Features contain their field definition, and therefore require a {@code StampCalculator} to determine
 *  the correct pattern version based on the {@code StampCalculator}, which is used to determine current meaning and purpose.
 *
 * @param <DT> the data type of the value associated with this Feature
 */
public final class Feature<DT> implements LocatableFeature {
    private final ObservableComponent containingComponent;
    private final FieldDefinition fieldDefinition;
    private final ObjectProperty<DT> valueProperty;
    public final FeatureLocator locator;

    public Feature(DT value, FieldDefinition fieldDefinition, ObservableComponent containingComponent, FeatureLocator locator) {
        this.containingComponent = containingComponent;
        this.fieldDefinition = fieldDefinition;
        this.locator = locator;
        this.valueProperty = new SimpleObjectProperty<>();
        this.valueProperty.set(value);
    }

    public Feature(ObservableField field, ObservableComponent containingComponent) {
        this.containingComponent = containingComponent;
        this.fieldDefinition = field.field();
        this.locator = field.locator();
        this.valueProperty = field.valueProperty();
    }

    public int patternNid() {
        return fieldDefinition.patternNid();
    }

    public int patternVersionStampNid() {
        return fieldDefinition.patternVersionStampNid();
    }

    public DT value() {
        return valueProperty.getValue();
    }

    public ObjectProperty<DT> valueProperty() {
        return valueProperty;
    }

    public int indexInPattern() {
        return fieldDefinition.indexInPattern();
    }

    public ObservableComponent containingComponent() {
        return containingComponent;
    }

    public FeatureLocator locator() { return locator; }

    public int meaningNid() {
        return fieldDefinition.meaningNid();
    }

    public ConceptFacade meaning() {
        return EntityProxy.Concept.make(fieldDefinition.meaningNid());
    }

    public ConceptFacade purpose() {
        return EntityProxy.Concept.make(fieldDefinition.purposeNid());
    }

    public PatternFacade pattern() {
        return EntityProxy.Pattern.make(fieldDefinition.patternNid());
    }

    public ConceptFacade dataType() {
        return EntityProxy.Concept.make(fieldDefinition.dataTypeNid());
    }

    public FieldDataType fieldDataType() {
        return ConceptToDataType.convert(dataType());
    }

    public int purposeNid() {
        return fieldDefinition.purposeNid();
    }

    public int dataTypeNid() {
        return fieldDefinition.dataTypeNid();
    }

}
