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

import dev.ikm.tinkar.coordinate.stamp.calculator.StampCalculator;
import dev.ikm.tinkar.terms.TinkarTerm;
import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.list.ImmutableList;
import dev.ikm.tinkar.collection.ConcurrentReferenceHashMap;
import dev.ikm.tinkar.common.id.PublicId;
import dev.ikm.tinkar.common.service.PrimitiveData;
import dev.ikm.tinkar.common.util.broadcast.Subscriber;
import dev.ikm.tinkar.component.FieldDataType;
import dev.ikm.tinkar.coordinate.view.calculator.ViewCalculator;
import dev.ikm.tinkar.entity.*;
import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * TODO: should be a way of listening for changes to the versions of the entity? Yes, use the versionProperty()...
 *
 * @param <OV>
 */
public abstract sealed class ObservableEntity<OV extends ObservableVersion<? extends EntityVersion>>
        implements Entity<OV>, ObservableComponent
        permits ObservableConcept, ObservablePattern, ObservableSemantic, ObservableStamp {

    protected static final ConcurrentReferenceHashMap<PublicId, ObservableEntity> SINGLETONS =
            new ConcurrentReferenceHashMap<>(ConcurrentReferenceHashMap.ReferenceType.WEAK,
                    ConcurrentReferenceHashMap.ReferenceType.WEAK);
    private static final EntityChangeSubscriber ENTITY_CHANGE_SUBSCRIBER = new EntityChangeSubscriber();

    static {
        Entity.provider().addSubscriberWithWeakReference(ENTITY_CHANGE_SUBSCRIBER);
    }

    final SimpleListProperty<OV> versionProperty = new SimpleListProperty<>(FXCollections.observableArrayList());

    final private AtomicReference<Entity<? extends EntityVersion>> entityReference;


    ObservableEntity(Entity<? extends EntityVersion> entity) {
        Entity<? extends EntityVersion> entityClone = switch (entity) {
            case ConceptRecord conceptEntity -> conceptEntity.analogueBuilder().build();

            case PatternRecord patternEntity -> patternEntity.analogueBuilder().build();

            case SemanticRecord semanticEntity -> semanticEntity.analogueBuilder().build();

            case StampRecord stampEntity -> stampEntity.analogueBuilder().build();

            default -> throw new UnsupportedOperationException("Can't handle: " + entity);
        };

        this.entityReference = new AtomicReference<>(entityClone);
        for (EntityVersion version : entity.versions()) {
            versionProperty.add(wrap(version));
        }
    }

    protected abstract OV wrap(EntityVersion version);

    public static <OE extends ObservableEntity<OV>, OV extends ObservableVersion<? extends EntityVersion>>
    ObservableEntitySnapshot<OE, OV> getSnapshot(int nid, ViewCalculator calculator) {
        return get(Entity.getFast(nid)).getSnapshot(calculator);
    }

    public abstract ObservableEntitySnapshot<?,?> getSnapshot(ViewCalculator calculator);

    public static <OE extends ObservableEntity> OE get(Entity<? extends EntityVersion> entity) {
        if (entity instanceof ObservableEntity) {
            return (OE) entity;
        }
        ObservableEntity observableEntity = SINGLETONS.computeIfAbsent(entity.publicId(), publicId ->
                switch (entity) {
                    case ConceptEntity conceptEntity -> new ObservableConcept(conceptEntity);
                    case PatternEntity patternEntity -> new ObservablePattern(patternEntity);
                    case SemanticEntity semanticEntity -> new ObservableSemantic(semanticEntity);
                    case StampEntity stampEntity -> new ObservableStamp(stampEntity);
                    default -> throw new UnsupportedOperationException("Can't handle: " + entity);
                });
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> updateVersions(entity, observableEntity));
        } else {
            updateVersions(entity, observableEntity);
        }

        return (OE) observableEntity;
    }

    private static void updateVersions(Entity<? extends EntityVersion> entity, ObservableEntity observableEntity) {
        if (!((Entity) observableEntity.entityReference.get()).versions().equals(entity.versions())) {
            observableEntity.entityReference.set(entity);
            observableEntity.versionProperty.clear();
            for (EntityVersion version : entity.versions().stream().sorted((v1, v2) ->
                    Long.compare(v1.stamp().time(), v2.stamp().time())).toList()) {
                observableEntity.versionProperty.add(observableEntity.wrap(version));
            }
        }
    }


    @Override
    public final ImmutableMap<FieldLocator, ObservableField> getObservableFields() {
        MutableMap<FieldLocator, ObservableField> fieldMap = Maps.mutable.empty();

        int firstStamp = StampCalculator.firstStampTimeOnly(this.entity().stampNids());

        for (FieldCategory fieldCategory: FieldCategorySet.conceptFields()) {
            ComponentFieldLocator fieldLocator = new ComponentFieldLocator(fieldCategory);
            switch (fieldCategory) {
                case PUBLIC_ID_FIELD -> {
                    //TODO temporary until we get a pattern for concept fields...
                    //TODO get right starter set entities. Temporary incorrect codes for now.
                    Object value = this.publicId();
                    int dataTypeNid = TinkarTerm.IDENTIFIER_VALUE.nid();
                    int purposeNid = TinkarTerm.IDENTIFIER_VALUE.nid();
                    int meaningNid = TinkarTerm.IDENTIFIER_VALUE.nid();
                    int patternNid = TinkarTerm.IDENTIFIER_PATTERN.nid();
                    int indexInPattern = 0;
                    int patternVersionStampNid;
                    Optional<PatternEntity> optionalIdPattern = Entity.get(TinkarTerm.IDENTIFIER_PATTERN.nid());
                    if (optionalIdPattern.isPresent()) {
                        PatternEntity idPattern = optionalIdPattern.get();
                        patternVersionStampNid = StampCalculator.firstStampTimeOnly(idPattern.stampNids());
                    } else {
                        patternVersionStampNid = StampRecord.nonExistentStamp().nid();
                    }
                    FieldDefinitionRecord fdr = new FieldDefinitionRecord(dataTypeNid, purposeNid, meaningNid,
                            patternVersionStampNid, patternNid,  indexInPattern);
                    fieldMap.put(fieldLocator, new ObservableField(new FieldRecord(value, this.nid(), firstStamp, fdr)));
                }
                case COMPONENT_VERSIONS_LIST -> {
                    //TODO temporary until we get a pattern for concept fields...
                    //TODO get right starter set entities. Temporary incorrect codes for now.
                    Object value = this.versions();
                    int dataTypeNid = TinkarTerm.VERSION_LIST_FOR_CHRONICLE.nid();
                    int purposeNid = TinkarTerm.VERSION_LIST_FOR_CHRONICLE.nid();
                    int meaningNid = TinkarTerm.VERSION_LIST_FOR_CHRONICLE.nid();
                    Entity<EntityVersion> idPattern = Entity.getFast(TinkarTerm.STAMP_PATTERN.nid());
                    int patternVersionStampNid = StampCalculator.firstStampTimeOnly(idPattern.stampNids());
                    int patternNid = TinkarTerm.IDENTIFIER_PATTERN.nid();
                    int indexInPattern = 0;

                    FieldDefinitionRecord fdr = new FieldDefinitionRecord(dataTypeNid, purposeNid, meaningNid,
                            patternVersionStampNid, patternNid,  indexInPattern);

                    fieldMap.put(fieldLocator, new ObservableField(new FieldRecord(value, this.nid(), firstStamp, fdr)));
                }
            }
        }

        addAdditionalFields(fieldMap);

        return fieldMap.toImmutable();
    }

    protected abstract void addAdditionalFields(MutableMap<FieldLocator, ObservableField> fieldMap);

    public static <OE extends ObservableEntity> OE get(int nid) {
        return get(Entity.getFast(nid));
    }

    protected Entity<? extends EntityVersion> entity() {
        return entityReference.get();
    }

    public ObservableList<OV> versionProperty() {
        return versionProperty;
    }

    @Override
    public ImmutableList<OV> versions() {
        return Lists.immutable.ofAll(versionProperty);
    }

    @Override
    public byte[] getBytes() {
        return entityReference.get().getBytes();
    }

    @Override
    public FieldDataType entityDataType() {
        return entityReference.get().entityDataType();
    }

    @Override
    public FieldDataType versionDataType() {
        return entityReference.get().versionDataType();
    }

    @Override
    public int nid() {
        return entityReference.get().nid();
    }

    @Override
    public long mostSignificantBits() {
        return entityReference.get().mostSignificantBits();
    }

    @Override
    public long leastSignificantBits() {
        return entityReference.get().leastSignificantBits();
    }

    @Override
    public long[] additionalUuidLongs() {
        return entityReference.get().additionalUuidLongs();
    }

    public Iterable<ObservableSemantic> getObservableSemanticList() {
        throw new UnsupportedOperationException();
    }

    public static class EntityChangeSubscriber implements Subscriber<Integer> {

        @Override
        public void onNext(Integer nid) {
            // Do nothing with item, but request another...
            if (SINGLETONS.containsKey(PrimitiveData.publicId(nid))) {
                Platform.runLater(() -> {
                    get(Entity.getFast(nid));
                });
            }
        }
    }
}
