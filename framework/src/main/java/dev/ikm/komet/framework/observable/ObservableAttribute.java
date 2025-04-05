package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.component.AttributeDefinition;
import dev.ikm.tinkar.entity.Attribute;


public sealed interface ObservableAttribute<DT> extends Attribute<DT>, AttributeDefinition
        permits ObservableField, ObservableFieldDefinition {

    ObservableComponent containingComponent();

}
