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
import dev.ikm.tinkar.coordinate.stamp.calculator.Latest;
import dev.ikm.tinkar.coordinate.stamp.calculator.StampCalculator;
import dev.ikm.tinkar.entity.*;
import dev.ikm.tinkar.entity.transaction.Transaction;
import dev.ikm.tinkar.terms.ConceptFacade;
import dev.ikm.tinkar.terms.State;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


public abstract sealed class ObservableVersion<V extends EntityVersion>
        implements EntityVersion, ObservableComponent, LocatableFeature
        permits ObservableConceptVersion, ObservablePatternVersion, ObservableSemanticVersion, ObservableStampVersion {
    protected final SimpleObjectProperty<V> versionProperty = new SimpleObjectProperty<>();

    final SimpleObjectProperty<State> stateProperty = new SimpleObjectProperty<>();
    final SimpleLongProperty timeProperty = new SimpleLongProperty();
    final SimpleObjectProperty<ConceptFacade> authorProperty = new SimpleObjectProperty<>();
    final SimpleObjectProperty<ConceptFacade> moduleProperty = new SimpleObjectProperty<>();
    final SimpleObjectProperty<ConceptFacade> pathProperty = new SimpleObjectProperty<>();


    ObservableVersion(V entityVersion) {
        versionProperty.set(entityVersion);
        stateProperty.set(entityVersion.state());
        timeProperty.set(entityVersion.time());
        authorProperty.set(Entity.provider().getEntityFast(entityVersion.authorNid()));
        moduleProperty.set(Entity.provider().getEntityFast(entityVersion.moduleNid()));
        pathProperty.set(Entity.provider().getEntityFast(entityVersion.pathNid()));
        addListeners();
    }

    public abstract ObservableEntity<? extends ObservableVersion> getObservableEntity();

    // TODO: replace with JEP 502: Stable Values when finalized, or for testing.
    AtomicInteger versionIndex = new AtomicInteger(-1);

    @Override
    public FeatureLocator locator() {
        return FeatureLocator.Chronology.VersionListItem(nid(), versionIndex.updateAndGet
                (currentValue -> currentValue == -1 ? getVersionIndex() : currentValue));
    }

    private int getVersionIndex() {
        return getObservableEntity().versionProperty.indexOf(this);
    }

    @Override
    public ObservableComponent containingComponent() {
        return getObservableEntity();
    }

    public final FieldDefinitionForEntity getFeatureDefinition() {
        PatternEntity<PatternEntityVersion> pattern = Entity.getFast(patternNid());
        PatternEntityVersion patternVersion = pattern.getVersionFast(patternVersionStampNid());
        return patternVersion.fieldDefinitions().get(indexInPattern());
    }

    /**
     * TODO: Better to implement this method with a stamp calculator based on context, maybe a scoped variable.
     *
     * @return
     */
    @Override
    public int patternVersionStampNid() {
        PatternEntity pattern = Entity.getFast(patternNid());
        return pattern.lastVersion().stampNid();
    }

    @Override
    public final int meaningNid() {
        return getFeatureDefinition().meaningNid();
    }

    @Override
    public final int purposeNid() {
        return getFeatureDefinition().purposeNid();
    }

    @Override
    public final int dataTypeNid() {
        return getFeatureDefinition().dataTypeNid();
    }

    public int nid() {
        return entity().nid();
    }

    protected void addListeners() {
        stateProperty.addListener((observable, oldValue, newValue) -> {
            if (version().uncommitted()) {
                Transaction.forVersion(version()).ifPresentOrElse(transaction -> {
                    StampEntity newStamp = transaction.getStamp(newValue, version().time(),
                            version().authorNid(), version().moduleNid(), version().pathNid());
                    versionProperty.set(withStampNid(newStamp.nid()));
                }, () -> {
                    throw new IllegalStateException("No transaction for uncommitted version: " + version());
                });
            } else {
                throw new IllegalStateException("Version is already committed, cannot change value.");
            }
        });

        timeProperty.addListener((observable, oldValue, newValue) -> {
            // TODO when to update the chronology with new record? At commit time? Automatically with reactive stream for commits?
            if (version().uncommitted()) {
                Transaction.forVersion(version()).ifPresentOrElse(transaction -> {
                    StampEntity newStamp = transaction.getStamp(version().state(), newValue.longValue(),
                            version().authorNid(), version().moduleNid(), version().pathNid());
                    versionProperty.set(withStampNid(newStamp.nid()));
                }, () -> {
                    throw new IllegalStateException("No transaction for uncommitted version: " + version());
                });
            } else {
                throw new IllegalStateException("Version is already committed, cannot change value.");
            }
        });

        authorProperty.addListener((observable, oldValue, newValue) -> {
            if (version().uncommitted()) {
                Transaction.forVersion(version()).ifPresentOrElse(transaction -> {
                    StampEntity newStamp = transaction.getStamp(version().state(), version().time(),
                            newValue.nid(), version().moduleNid(), version().pathNid());
                    versionProperty.set(withStampNid(newStamp.nid()));
                }, () -> {
                    throw new IllegalStateException("No transaction for uncommitted version: " + version());
                });
            } else {
                throw new IllegalStateException("Version is already committed, cannot change value.");
            }
        });

        moduleProperty.addListener((observable, oldValue, newValue) -> {
            if (version().uncommitted()) {
                Transaction.forVersion(version()).ifPresentOrElse(transaction -> {
                    StampEntity newStamp = transaction.getStamp(version().state(), version().time(),
                            version().authorNid(), newValue.nid(), version().pathNid());
                    versionProperty.set(withStampNid(newStamp.nid()));
                }, () -> {
                    throw new IllegalStateException("No transaction for uncommitted version: " + version());
                });
            } else {
                throw new IllegalStateException("Version is already committed, cannot change value.");
            }
        });

        pathProperty.addListener((observable, oldValue, newValue) -> {
            if (version().uncommitted()) {
                Transaction.forVersion(version()).ifPresentOrElse(transaction -> {
                    StampEntity newStamp = transaction.getStamp(version().state(), version().time(),
                            version().authorNid(), version().moduleNid(), newValue.nid());
                    versionProperty.set(withStampNid(newStamp.nid()));
                }, () -> {
                    throw new IllegalStateException("No transaction for uncommitted version: " + version());
                });
            } else {
                throw new IllegalStateException("Version is already committed, cannot change value.");
            }
        });
    }

    public V version() {
        return versionProperty.getValue();
    }

    protected abstract V withStampNid(int stampNid);

    public ObjectProperty<V> versionProperty() {
        return versionProperty;
    }

    @Override
    public Entity entity() {
        return version().entity();
    }

    @Override
    public int stampNid() {
        return version().stampNid();
    }

    @Override
    public Entity chronology() {
        return version().chronology();
    }

    public ObjectProperty<State> stateProperty() {
        return stateProperty;
    }

    public LongProperty timeProperty() {
        return timeProperty;
    }

    public ObjectProperty<ConceptFacade> authorProperty() {
        return authorProperty;
    }

    public ObjectProperty<ConceptFacade> moduleProperty() {
        return moduleProperty;
    }

    public ObjectProperty<ConceptFacade> pathProperty() {
        return pathProperty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVersionRecord().stampNid());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof ObservableVersion observableVersion) {
            return getVersionRecord().equals(observableVersion.getVersionRecord());
        }
        return false;
    }

    public abstract V getVersionRecord();

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + getVersionRecord().toString();
    }

    // TODO: replace with JEP 502: Stable Values when finalized to allow lazy initialization of feature.
    private AtomicReference<Feature> versionStampFeatureReference = new AtomicReference<>();
    private Feature getVersionStampFeature(StampCalculator stampCalculator) {
        return versionStampFeatureReference.updateAndGet(currentValue -> currentValue != null
                ? currentValue
                : makeVersionStampFeature(stampCalculator));
    }
    private Feature makeVersionStampFeature(StampCalculator stampCalculator) {
        Latest<PatternEntityVersion> componentVersionPattern = stampCalculator.latestPatternEntityVersion(Binding.Component.Version.pattern());
        PatternEntityVersion pattern = componentVersionPattern.get();
        FieldDefinitionForEntity fieldDefinition = pattern.fieldDefinitions().get(Binding.Component.Version.stampFieldDefinitionIndex());
        FeatureLocator locator = FeatureLocator.Version.VersionStamp(this.nid(), this.stampNid());
        return new Feature(this.stamp(), fieldDefinition, this, locator);
    }

    @Override
    public final ImmutableList<Feature> getFeatures(StampCalculator stampCalculator) {
        // TODO: replace with JEP 502: Stable Values when finalized to allow lazy initialization of feature lists.
        MutableList<Feature> features = Lists.mutable.empty();

        features.add(getVersionStampFeature(stampCalculator));

        addAdditionalVersionFeatures(features, stampCalculator);

        return features.toImmutable();
    }

    protected abstract void addAdditionalVersionFeatures(MutableList<Feature> features, StampCalculator stampCalculator);

}
