package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.component.FieldDataType;
import dev.ikm.tinkar.entity.Attribute;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;


public sealed abstract class ObservableAttribute<DT> implements Attribute<DT>
        permits ObservableField {

    SimpleObjectProperty<Attribute<DT>> attributeProperty = new SimpleObjectProperty<>();
    SimpleObjectProperty<DT> valueProperty = new SimpleObjectProperty<>();

    public final BooleanProperty refreshProperties = new SimpleBooleanProperty(false);
    public final boolean writeOnEveryChange;

    public ObservableAttribute(Attribute<DT> attribute, boolean writeOnEveryChange) {
        this.writeOnEveryChange = writeOnEveryChange;
        attributeProperty.set(attribute);
        if (attribute != null) {
            valueProperty.set(attribute.value());
        }
        valueProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleValueChange(newValue);
                attributeProperty.set(attribute().with(newValue));
            }
        });
        refreshProperties.addListener((observable, oldValue, newValue) -> {
            if(!newValue){
                writeToDatabase(value());
            }
        });

    }
    public ObservableAttribute(Attribute<DT> attribute) {
        this(attribute, true);
    }

    private void handleValueChange(Object newValue) {
        if (writeOnEveryChange && !refreshProperties.get()) {
            writeToDatabase(newValue);
        }
    }

    protected abstract void writeToDatabase(Object newValue);

    public Attribute<DT> attribute() {
        return attributeProperty.get();
    }

    @Override
    public DT value() {
        return attribute().value();
    }

    @Override
    public FieldDataType attributeDataType() {
        return attribute().attributeDataType();
    }

    @Override
    public int meaningNid() {
        return attribute().meaningNid();
    }

    @Override
    public int purposeNid() {
        return attribute().purposeNid();
    }

    @Override
    public int dataTypeNid() {
        return attribute().dataTypeNid();
    }

    public ObjectProperty<DT> valueProperty() {
        return valueProperty;
    }

    public ObjectProperty<Attribute<DT>> attributeProperty() {
        return attributeProperty;
    }

}
