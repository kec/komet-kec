package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.component.FieldDefinition;
import javafx.collections.ModifiableObservableListBase;
import org.eclipse.collections.api.factory.Lists;

import java.util.List;

public final class FeatureList<LF extends LocatableFeature>
        extends ModifiableObservableListBase<LF>
        implements LocatableFeature {

    private final List<LF> backingList;
    private final FeatureLocator locator;
    private final FieldDefinition fieldDefinition;
    private final ObservableComponent containingComponent;

    public FeatureList(FeatureLocator locator, FieldDefinition fieldDefinition, ObservableComponent containingComponent) {
        this.locator = locator;
        this.fieldDefinition = fieldDefinition;
        this.containingComponent = containingComponent;
        this.backingList = Lists.mutable.empty();
    }

    public FeatureList(List<LF> backingList, FeatureLocator locator, FieldDefinition fieldDefinition, ObservableComponent containingComponent) {
        this.locator = locator;
        this.fieldDefinition = fieldDefinition;
        this.backingList = backingList;
        this.containingComponent = containingComponent;
    }

    @Override
    public FeatureLocator locator() {
        return locator;
    }

    public LF get(int index) {
        return backingList.get(index);
    }

    public int size() {
        return backingList.size();
    }

    protected void doAdd(int index, LF element) {
        backingList.add(index, element);
    }

    protected LF doSet(int index, LF element) {
        return backingList.set(index, element);
    }

    protected LF doRemove(int index) {
        return backingList.remove(index);
    }

    @Override
    public ObservableComponent containingComponent() {
        return this.containingComponent;
    }

    @Override
    public int patternNid() {
        return this.fieldDefinition.patternNid();
    }

    @Override
    public int indexInPattern() {
        return this.fieldDefinition.indexInPattern();
    }

    @Override
    public int patternVersionStampNid() {
        return this.fieldDefinition.patternVersionStampNid();
    }

    @Override
    public int meaningNid() {
        return this.fieldDefinition.meaningNid();
    }

    @Override
    public int purposeNid() {
        return this.fieldDefinition.purposeNid();
    }

    @Override
    public int dataTypeNid() {
        return this.fieldDefinition.dataTypeNid();
    }
}
