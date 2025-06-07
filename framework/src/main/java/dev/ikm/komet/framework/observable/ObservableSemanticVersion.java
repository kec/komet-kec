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
import dev.ikm.tinkar.terms.EntityProxy;
import dev.ikm.tinkar.terms.PatternFacade;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;

import java.util.concurrent.atomic.AtomicReference;

public final class ObservableSemanticVersion
        extends ObservableVersion<SemanticVersionRecord>
        implements SemanticEntityVersion {
    ObservableSemanticVersion(SemanticVersionRecord semanticVersionRecord) {
        super(semanticVersionRecord);
    }

    @Override
    public ObservableSemantic getObservableEntity() {
        return ObservableEntity.get(nid());
    }
    @Override
    protected SemanticVersionRecord withStampNid(int stampNid) {
        return version().withStampNid(stampNid);
    }

    @Override
    public SemanticEntity entity() {
        return version().entity();
    }

    @Override
    public SemanticEntity chronology() {
        return version().chronology();
    }

    @Override
    public SemanticVersionRecord getVersionRecord() {
        return version();
    }

    @Override
    public PatternFacade pattern() {
        return EntityProxy.Pattern.make(patternNid());
    }

    @Override
    public int patternNid() {
        return Binding.Semantic.pattern().nid();
    }

    @Override
    public int indexInPattern() {
        return Binding.Semantic.versionItemDefinitionIndex();
    }

    @Override
    public ImmutableList<Object> fieldValues() {
        return version().fieldValues();
    }

    @Override
    public ImmutableList<ObservableField> fields(PatternEntityVersion patternVersion) {
        ObservableField[] fieldArray = new ObservableField[fieldValues().size()];
        for (int indexInPattern = 0; indexInPattern < fieldArray.length; indexInPattern++) {
            Object value = fieldValues().get(indexInPattern);
            FieldDefinitionForEntity fieldDef = patternVersion.fieldDefinitions().get(indexInPattern);
            FieldDefinitionRecord fieldDefinitionRecord = new FieldDefinitionRecord(fieldDef.dataTypeNid(),
                    fieldDef.purposeNid(), fieldDef.meaningNid(), patternVersion.stampNid(), patternVersion.nid(), indexInPattern);
            fieldArray[indexInPattern] = new ObservableField(new FieldRecord(value, this.nid(), this.stampNid(), fieldDefinitionRecord), this);
        }
        return Lists.immutable.of(fieldArray);
    }

    // TODO: replace with JEP 502: Stable Values when finalized to allow lazy initialization of feature.
    private AtomicReference<Feature> fieldListReference = new AtomicReference<>();
    private Feature getFieldListFeature(StampCalculator stampCalculator) {
        return fieldListReference.updateAndGet(currentValue -> currentValue != null
                ? currentValue
                : makeFieldListFeature(stampCalculator));
    }
    private Feature makeFieldListFeature(StampCalculator stampCalculator) {
        Latest<PatternEntityVersion> componentVersionPattern = stampCalculator.latestPatternEntityVersion(Binding.Semantic.Version.pattern());
        PatternEntityVersion patternEntityVersion = componentVersionPattern.get();
        FieldDefinitionForEntity fieldDefinition = patternEntityVersion.fieldDefinitions().get(Binding.Semantic.Version.semanticFieldsDefinitionIndex());
        FeatureLocator locator = FeatureLocator.Version.SemanticFieldList(this.nid(), stampNid());
        return new Feature(this.fields(patternEntityVersion), fieldDefinition, this, locator);
    }

    @Override
    protected void addAdditionalVersionFeatures(MutableList<Feature> features, StampCalculator stampCalculator) {
        features.add(getFieldListFeature(stampCalculator));

        Latest<PatternEntityVersion> componentVersionPattern = stampCalculator.latestPatternEntityVersion(Binding.Semantic.Version.pattern());
        for (ObservableField field : fields(componentVersionPattern.get())) {
            features.add(new Feature(field, this));
        }
    }
}
