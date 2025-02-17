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

import dev.ikm.tinkar.entity.*;
import dev.ikm.tinkar.terms.ConceptFacade;
import dev.ikm.tinkar.terms.TinkarTerm;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;

public final class ObservableStampVersion
        extends ObservableVersion<StampVersionRecord> {

    enum StampField {
        STATE(TinkarTerm.STATUS_FOR_VERSION.nid(), TinkarTerm.STATUS_VALUE.nid(), TinkarTerm.CONCEPT_TYPE.nid()),
        TIME(TinkarTerm.TIME_FOR_VERSION.nid(), TinkarTerm.TIMING.nid(), TinkarTerm.LONG.nid()),
        AUTHOR(TinkarTerm.AUTHOR_FOR_VERSION.nid(), TinkarTerm.AUTHOR_FOR_VERSION.nid(), TinkarTerm.CONCEPT_TYPE.nid()),
        MODULE(TinkarTerm.MODULE_FOR_VERSION.nid(), TinkarTerm.MODULE_FOR_VERSION.nid(), TinkarTerm.CONCEPT_TYPE.nid()),
        PATH(TinkarTerm.PATH_FOR_VERSION.nid(), TinkarTerm.PATH_FOR_VERSION.nid(), TinkarTerm.CONCEPT_TYPE.nid());

        int meaningNid;
        int purposeNid;
        int dataTypeNid;

        StampField(int meaningNid, int purposeNid, int dataTypeNid) {
            this.meaningNid = meaningNid;
            this.purposeNid = purposeNid;
            this.dataTypeNid = dataTypeNid;
        }

        FieldDefinitionRecord fieldDefinitionRecord() {
            PatternEntity<PatternEntityVersion> stampPattern = Entity.getFast(TinkarTerm.STAMP_PATTERN.nid());
            return new FieldDefinitionRecord(dataTypeNid, purposeNid, meaningNid, stampPattern.versions().get(0).stampNid(),
            TinkarTerm.STAMP_PATTERN.nid(), this.ordinal());
        }
    }

    ObservableStampVersion(StampVersionRecord stampVersion) {
        super(stampVersion);
    }

    protected void addListeners() {
        stateProperty.addListener((observable, oldValue, newValue) -> {
            versionProperty.set(version().withStateNid(newValue.nid()));
        });

        timeProperty.addListener((observable, oldValue, newValue) -> {
            // TODO when to update the chronology with new record? At commit time? Automatically with reactive stream for commits?
            versionProperty.set(version().withTime(newValue.longValue()));
        });

        authorProperty.addListener((observable, oldValue, newValue) -> {
            versionProperty.set(version().withAuthorNid(newValue.nid()));
        });

        moduleProperty.addListener((observable, oldValue, newValue) -> {
            versionProperty.set(version().withModuleNid(newValue.nid()));
        });

        pathProperty.addListener((observable, oldValue, newValue) -> {
            versionProperty.set(version().withPathNid(newValue.nid()));
        });
    }

    @Override
    protected StampVersionRecord withStampNid(int stampNid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StampVersionRecord getVersionRecord() {
        return version();
    }



    /**
     * Constructs an immutable list containing the field values of the object.
     * The field values include state, time, author, module, and path as represented
     * by their respective concept facade objects or corresponding properties.
     *
     * @return An immutable list of field values for the object.
     */
    public ImmutableList<Object> fieldValues() {
        MutableList<Object> fieldValues = Lists.mutable.of();
        fieldValues.add(ConceptFacade.make(this.state().nid()));
        fieldValues.add(this.time());
        fieldValues.add(ConceptFacade.make(this.author().nid()));
        fieldValues.add(ConceptFacade.make(this.module().nid()));
        fieldValues.add(ConceptFacade.make(this.path().nid()));
        return fieldValues.toImmutable();
    }


    public ImmutableList<ObservableField> fields() {
        // TODO: Get this to work with a STAMP pattern in the starter set...
        // TODO: Note the stamp pattern exists, but is not correct in all cases.
        // TODO: Validate fixes then update.
        ObservableField[] fieldArray = new ObservableField[fieldValues().size()];
        for (int indexInPattern = 0; indexInPattern < fieldArray.length; indexInPattern++) {
            StampField currentField = StampField.values()[indexInPattern];
            FieldDefinitionRecord fieldDefinitionRecord = currentField.fieldDefinitionRecord();
            Object value = fieldValues().get(indexInPattern);
            fieldArray[indexInPattern] =
                    new ObservableField(new FieldRecord(value, this.nid(), this.stampNid(), fieldDefinitionRecord));
        }
        return Lists.immutable.of(fieldArray);
    }

}
