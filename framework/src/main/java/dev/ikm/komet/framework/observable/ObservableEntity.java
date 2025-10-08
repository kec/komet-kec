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

import dev.ikm.komet.framework.observable.binding.Binding;
import dev.ikm.tinkar.collection.ConcurrentReferenceHashMap;
import dev.ikm.tinkar.common.id.PublicId;
import dev.ikm.tinkar.common.service.PrimitiveData;
import dev.ikm.tinkar.common.util.broadcast.Subscriber;
import dev.ikm.tinkar.component.FieldDataType;
import dev.ikm.tinkar.coordinate.view.calculator.ViewCalculator;
import dev.ikm.tinkar.entity.*;
import javafx.application.Platform;
import javafx.beans.property.*;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * TODO: should be a way of listening for changes to the versions of the entity? Yes, use the versionProperty()...
 *
 * @param <OV>
 */
public abstract sealed class ObservableEntity<OV extends ObservableVersion<?>>
        implements Entity<OV>, ObservableComponent
        permits ObservableConcept, ObservablePattern, ObservableSemantic, ObservableStamp {

    protected static final ConcurrentReferenceHashMap<PublicId, ObservableEntity> SINGLETONS =
            new ConcurrentReferenceHashMap<>(ConcurrentReferenceHashMap.ReferenceType.WEAK,
                    ConcurrentReferenceHashMap.ReferenceType.WEAK);
    private static final EntityChangeSubscriber ENTITY_CHANGE_SUBSCRIBER = new EntityChangeSubscriber();

    static {
        Entity.provider().addSubscriberWithWeakReference(ENTITY_CHANGE_SUBSCRIBER);
    }

    final FeatureList<OV> versionSetAsList;

    final private AtomicReference<Entity<? extends EntityVersion>> entityReference;


    ObservableEntity(Entity<? extends EntityVersion> entity) {
        Entity<? extends EntityVersion> entityClone = switch (entity) {
            case ConceptRecord conceptEntity -> conceptEntity.analogueBuilder().build();

            case PatternRecord patternEntity -> patternEntity.analogueBuilder().build();

            case SemanticRecord semanticEntity -> semanticEntity.analogueBuilder().build();

            case StampRecord stampEntity -> stampEntity.analogueBuilder().build();

            default -> throw new UnsupportedOperationException("Can't handle: " + entity);
        };
        this.versionSetAsList = new FeatureList<>(FeatureKey.Entity.VersionSet(entity.nid()),
                Binding.Component.pattern(), Binding.Component.versionsFieldDefinitionIndex(), this);


        this.entityReference = new AtomicReference<>(entityClone);
        for (EntityVersion version : entity.versions()) {
            versionSetAsList.add(wrap(version));
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
//        if (!Platform.isFxApplicationThread()) {
//            Platform.runLater(() -> updateVersions(entity, observableEntity));
//        } else {
//            updateVersions(entity, observableEntity);
//        }

        return (OE) observableEntity;
    }

    private static void updateVersions(Entity<? extends EntityVersion> entity, ObservableEntity observableEntity) {
        if (!((Entity) observableEntity.entityReference.get()).versions().equals(entity.versions())) {
            observableEntity.entityReference.set(entity);
            observableEntity.versionSetAsList.clear();
            for (EntityVersion version : entity.versions().stream().sorted((v1, v2) ->
                    Long.compare(v1.stamp().time(), v2.stamp().time())).toList()) {
                observableEntity.versionSetAsList.add(observableEntity.wrap(version));
            }
        }
    }

    public static <OE extends ObservableEntity> OE get(int nid) {
        return get(Entity.getFast(nid));
    }

    protected Entity<? extends EntityVersion> entity() {
        return entityReference.get();
    }

    public FeatureList<OV> versionProperty() {
        return versionSetAsList;
    }

    @Override
    public ImmutableList<OV> versions() {
        return Lists.immutable.ofAll(versionSetAsList);
    }

    public Optional<OV> getVersion(int stampNid) {
        return versions().detectOptional(each -> each.stampNid() == stampNid);
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

    // TODO: replace with JEP 502: Stable Values when finalized to allow lazy initialization of feature.
    final AtomicReference<FeatureWrapper> publicIdFeatureReference = new AtomicReference<>();
    private FeatureWrapper getPublicIdFeature() {
        return publicIdFeatureReference.updateAndGet(currentValue -> currentValue != null
                ? currentValue
                : makePublicIdFeature());
    }
    private FeatureWrapper makePublicIdFeature() {
        return new FeatureWrapper(this.publicId(),
                Binding.Component.pattern().nid(),
                Binding.Component.publicIdFieldDefinitionIndex(),
                this,
                FeatureKey.Entity.PublicId(this.nid()));
    }

    @Override
    public final ImmutableList<Feature> getFeatures() {
        // TODO: replace with JEP 502: Stable Values when finalized to allow lazy initialization of feature lists.
        // TODO: Handle changes in StampCalculator.
        MutableList<Feature> features = Lists.mutable.empty();

        // Public ID:
        features.add(getPublicIdFeature());
        // Versions
        features.add(this.versionSetAsList);

        for (OV version : versions()) {
            features.add(version);
        }

        addAdditionalChronologyFeatures(features);

        return features.toImmutable();
    }

    protected abstract void addAdditionalChronologyFeatures(MutableList<Feature> features);

    public Feature<?> getFeature(FeatureKey featureKey) {
        return switch (featureKey) {
            case FeatureKey.ChronologyFeature chronologyFeatureKey -> getFeatures().select(feature -> chronologyFeatureKey.match(feature.featureKey())).getOnly();
            case FeatureKey.VersionFeature versionFeatureKey -> getVersion(versionFeatureKey.stampNid()).get().getFeature(versionFeatureKey);
        };
    }

    public final class EntityFeature implements Feature<ObservableEntity<OV>> {

        private EntityFeature() {
        }

        @Override
        public ReadOnlyProperty<? extends Feature<ObservableEntity<OV>>> featureProperty() {
            return entityFeatureWrapper;
        }

        @Override
        public FeatureKey featureKey() {
            return FeatureKey.Entity.Object(ObservableEntity.this.nid());
        }

        @Override
        public ObservableComponent containingComponent() {
            return ObservableEntity.this;
        }

        @Override
        public int patternNid() {
            return switch (ObservableEntity.this) {
                case ObservableConcept _-> Binding.Concept.pattern().nid();
                case ObservablePattern _-> Binding.Pattern.pattern().nid();
                case ObservableSemantic _-> Binding.Semantic.pattern().nid();
                case ObservableStamp _-> Binding.Stamp.pattern().nid();
            };
        }

        @Override
        public int indexInPattern() {
            // TODO: replace with a new index after adding a new field for the meaning and purpose of the entity class
            return 0;
        }

        @Override
        public String toString() {
            return ObservableEntity.this.getClass().getSimpleName() + " Feature<" + nid() + "> " + PrimitiveData.text(nid());
        }

    }

    // TODO: replace with JEP 502: Stable Values when finalized to allow lazy initialization of feature.
    private final  ReadOnlyObjectProperty<? extends Feature<ObservableEntity<OV>>> entityFeatureWrapper =
            new ReadOnlyObjectWrapper<>(this, this.getClass().getSimpleName(), new ObservableEntity<OV>.EntityFeature()).getReadOnlyProperty();

    private ReadOnlyProperty<? extends Feature<ObservableEntity<OV>>> featureProperty() {
        return entityFeatureWrapper;
    }

    public Feature<ObservableEntity<OV>> asFeature() {
        return entityFeatureWrapper.getValue();
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
