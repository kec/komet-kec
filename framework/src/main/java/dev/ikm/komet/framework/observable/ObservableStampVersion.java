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
import dev.ikm.tinkar.entity.FieldDefinitionForEntity;
import dev.ikm.tinkar.entity.PatternEntityVersion;
import dev.ikm.tinkar.entity.StampVersionRecord;
import org.eclipse.collections.api.list.MutableList;

import java.util.concurrent.atomic.AtomicReference;

public final class ObservableStampVersion
        extends ObservableVersion<StampVersionRecord> {

    ObservableStampVersion(StampVersionRecord stampVersion) {
        super(stampVersion);
    }

    @Override
    public ObservableStamp getObservableEntity() {
        return ObservableEntity.get(nid());
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
    public int patternNid() {
        return Binding.Stamp.pattern().nid();
    }

    @Override
    public int indexInPattern() {
        return Binding.Stamp.versionItemDefinitionIndex();
    }

    @Override
    public StampVersionRecord getVersionRecord() {
        return version();
    }

    // TODO: replace with JEP 502: Stable Values when finalized to allow lazy initialization of feature.
    private AtomicReference<Feature> versionStatusFieldReference = new AtomicReference<>();
    private Feature getVersionStatusField(StampCalculator stampCalculator) {
        return versionStatusFieldReference.updateAndGet(currentValue -> currentValue != null
                ? currentValue
                : makeVersionStatusField(stampCalculator));
    }
    private Feature makeVersionStatusField(StampCalculator stampCalculator) {
        Latest<PatternEntityVersion> componentVersionPattern = stampCalculator.latestPatternEntityVersion(Binding.Stamp.Version.pattern());
        PatternEntityVersion pattern = componentVersionPattern.get();
        FieldDefinitionForEntity fieldDefinition = pattern.fieldDefinitions().get(Binding.Stamp.Version.stampFieldDefinitionIndex());
        FeatureLocator locator = FeatureLocator.Version.StampStatus(this.nid());
        return new Feature(this.state(), fieldDefinition, this, locator);
    }


    // TODO: replace with JEP 502: Stable Values when finalized to allow lazy initialization of feature.
    private AtomicReference<Feature> versionTimeFieldReference = new AtomicReference<>();
    private Feature getVersionTimeField(StampCalculator stampCalculator) {
        return versionTimeFieldReference.updateAndGet(currentValue -> currentValue != null
                ? currentValue
                : makeVersionTimeField(stampCalculator));
    }
    private Feature makeVersionTimeField(StampCalculator stampCalculator) {
        Latest<PatternEntityVersion> componentVersionPattern = stampCalculator.latestPatternEntityVersion(Binding.Stamp.Version.pattern());
        PatternEntityVersion pattern = componentVersionPattern.get();
        FieldDefinitionForEntity fieldDefinition = pattern.fieldDefinitions().get(Binding.Stamp.Version.timeFieldDefinitionIndex());
        FeatureLocator locator = FeatureLocator.Version.StampTime(nid());
        return new Feature(this.time(), fieldDefinition, this, locator);
    }

    // TODO: replace with JEP 502: Stable Values when finalized to allow lazy initialization of feature.
    private AtomicReference<Feature> versionAuthorFieldReference = new AtomicReference<>();
    private Feature getVersionAuthorFeature(StampCalculator stampCalculator) {
        return versionAuthorFieldReference.updateAndGet(currentValue -> currentValue != null
                ? currentValue
                : makeVersionAuthorFeature(stampCalculator));
    }
    private Feature makeVersionAuthorFeature(StampCalculator stampCalculator) {
        Latest<PatternEntityVersion> componentVersionPattern = stampCalculator.latestPatternEntityVersion(Binding.Stamp.Version.pattern());
        PatternEntityVersion pattern = componentVersionPattern.get();
        FieldDefinitionForEntity fieldDefinition = pattern.fieldDefinitions().get(Binding.Stamp.Version.authorFieldDefinitionIndex());
        FeatureLocator locator = FeatureLocator.Version.StampAuthor(this.nid());
        return new Feature(this.author(), fieldDefinition, this, locator);
    }

    // TODO: replace with JEP 502: Stable Values when finalized to allow lazy initialization of feature.
    private AtomicReference<Feature> versionModuleFieldReference = new AtomicReference<>();
    private Feature getVersionModuleFeature(StampCalculator stampCalculator) {
        return versionModuleFieldReference.updateAndGet(currentValue -> currentValue != null
                ? currentValue
                : makeVersionModuleFeature(stampCalculator));
    }
    private Feature makeVersionModuleFeature(StampCalculator stampCalculator) {
        Latest<PatternEntityVersion> componentVersionPattern = stampCalculator.latestPatternEntityVersion(Binding.Stamp.Version.pattern());
        PatternEntityVersion pattern = componentVersionPattern.get();
        FieldDefinitionForEntity fieldDefinition = pattern.fieldDefinitions().get(Binding.Stamp.Version.moduleFieldDefinitionIndex());
        FeatureLocator locator = FeatureLocator.Version.StampModule(this.nid());
        return new Feature(this.module(), fieldDefinition, this, locator);
    }


    // TODO: replace with JEP 502: Stable Values when finalized to allow lazy initialization of feature.
    private AtomicReference<Feature> versionPathFieldReference = new AtomicReference<>();
    private Feature getVersionPathField(StampCalculator stampCalculator) {
        return versionPathFieldReference.updateAndGet(currentValue -> currentValue != null
                ? currentValue
                : makeVersionPathField(stampCalculator));
    }
    private Feature makeVersionPathField(StampCalculator stampCalculator) {
        Latest<PatternEntityVersion> componentVersionPattern = stampCalculator.latestPatternEntityVersion(Binding.Stamp.Version.pattern());
        PatternEntityVersion pattern = componentVersionPattern.get();
        FieldDefinitionForEntity fieldDefinition = pattern.fieldDefinitions().get(Binding.Stamp.Version.pathFieldDefinitionIndex());
        FeatureLocator locator = FeatureLocator.Version.StampPath(this.nid());
        return new Feature(this.path(), fieldDefinition, this, locator);
    }

    @Override
    protected void addAdditionalVersionFeatures(MutableList<Feature> features, StampCalculator stampCalculator) {
        // Status
        features.add(getVersionStatusField(stampCalculator));

        // Time
        features.add(getVersionTimeField(stampCalculator));

        // Author
        features.add(getVersionAuthorFeature(stampCalculator));

        // Module
        features.add(getVersionModuleFeature(stampCalculator));

        // Path
        features.add(getVersionPathField(stampCalculator));
    }
}
