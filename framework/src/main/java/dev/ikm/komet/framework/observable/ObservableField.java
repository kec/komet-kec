/*
 * Copyright © 2015 Integrated Knowledge Management (support@ikm.dev)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.component.AttributeDefinition;
import dev.ikm.tinkar.component.FieldDataType;
import dev.ikm.tinkar.entity.*;
import dev.ikm.tinkar.entity.transaction.Transaction;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

import java.util.Optional;

public final class ObservableField<DT> implements ObservableAttribute<DT>, Field<DT>, AttributeDefinition {

    private final SimpleObjectProperty<Field<DT>> fieldProperty = new SimpleObjectProperty<>();
    public final BooleanProperty refreshProperties = new SimpleBooleanProperty(false);
    private ObservableComponent containingComponent;
    private SimpleObjectProperty<DT> valueProperty = new SimpleObjectProperty<>();
    public final boolean writeOnEveryChange;

    public ObservableField(Field<DT> attribute, ObservableComponent containingComponent, boolean writeOnEveryChange) {
        this.containingComponent = containingComponent;
        this.writeOnEveryChange = writeOnEveryChange;
        this.fieldProperty.set(attribute);
        if (attribute != null) {
            attribute.optionalValue().ifPresent(value -> valueProperty.set(value));
        }
        valueProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleValueChange(newValue);
                fieldProperty.set(field().with(newValue));
            }
        });
        refreshProperties.addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                writeToDatabase(value());
            }
        });
    }

    private void handleValueChange(Object newValue) {
        if (writeOnEveryChange && !refreshProperties.get()) {
            writeToDatabase(newValue);
        }
    }

    public ObservableField(Field<DT> attribute, ObservableComponent containingComponent) {
        this(attribute, containingComponent, true);
    }

    @Override
    public DT value() {
        return valueProperty.getValue();
    }

    public ObjectProperty<DT> valueProperty() {
        return valueProperty;
    }

    @Override
    public Optional<DT> optionalValue() {
        return Optional.ofNullable(valueProperty.get());
    }

    public FieldRecord<DT> field() {
        return (FieldRecord<DT>) fieldProperty.get();
    }

    @Override
    public FieldDataType fieldDataType() {
        return field().fieldDataType();
    }

    @Override
    public int fieldIndex() {
        return field().fieldIndex();
    }

    public Attribute<DT> attribute() {
        return fieldProperty.get();
    }

    public ObservableComponent containingComponent() {
        return containingComponent;
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

    public ObjectProperty<Field<DT>> fieldProperty() {
        return fieldProperty;
    }

    public void writeToDatabase(Object newValue) {
        StampRecord stamp = Entity.getStamp(field().versionStampNid());
        // Get current version
        SemanticVersionRecord version = Entity.getVersionFast(field().nid(), field().versionStampNid());
        SemanticRecord semantic = Entity.getFast(field().nid());
        MutableList fieldsForNewVersion = Lists.mutable.of(version.fieldValues().toArray());
        fieldsForNewVersion.set(fieldIndex(), newValue);

        if (stamp.lastVersion().committed()) {

            // Create transaction
            Transaction t = Transaction.make();
            // newStamp already written to the entity store.
            StampEntity newStamp = t.getStampForEntities(stamp.state(), stamp.authorNid(), stamp.moduleNid(), stamp.pathNid(), version.entity());

            // Create a new version.
            SemanticVersionRecord newVersion = version.with().fieldValues(fieldsForNewVersion.toImmutable()).stampNid(newStamp.nid()).build();

            SemanticRecord analogue = semantic.with(newVersion).build();

            // Entity provider will broadcast the nid of the changed entity.
            Entity.provider().putEntity(analogue);
        } else {
            SemanticVersionRecord newVersion = version.withFieldValues(fieldsForNewVersion.toImmutable());
            // if a version with the same stamp as newVersion exists, that version will be removed
            // prior to adding the new version so you don't get duplicate versions with the same stamp.
            SemanticRecord analogue = semantic.with(newVersion).build();
            // Entity provider will broadcast the nid of the changed entity.
            Entity.provider().putEntity(analogue);
        }
    }
}
