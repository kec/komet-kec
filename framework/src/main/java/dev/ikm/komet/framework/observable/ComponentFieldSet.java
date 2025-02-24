package dev.ikm.komet.framework.observable;


import java.util.Collection;

public class ComponentFieldSet extends ImmutableEnumSet<ComponentField> {
    public ComponentFieldSet(ComponentField... elements) {
        super(elements);
    }

    public ComponentFieldSet(Collection<? extends ComponentField> elements) {
        super(elements);
    }

    @Override
    protected Class<ComponentField> enumClass() {
        return ComponentField.class;
    }
}
