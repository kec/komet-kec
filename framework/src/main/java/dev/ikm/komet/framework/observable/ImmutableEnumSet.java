package dev.ikm.komet.framework.observable;


import java.util.*;

public abstract class ImmutableEnumSet<E extends Enum<E>>  implements Iterable<E> {
    private long bits = 0;

    protected ImmutableEnumSet(E... elements) {
        for (E element : elements) {
            bits |= (1L << element.ordinal());
        }
    }

    protected ImmutableEnumSet(Collection<? extends E> elements) {
        for (E element : elements) {
            bits |= (1L << element.ordinal());
        }
    }

    public boolean contains(E element) {
        return (bits & (1L << element.ordinal())) != 0;
    }

    protected abstract Class<E> enumClass();

    public EnumSet<E> toEnumSet() {
        EnumSet<E> result = EnumSet.noneOf(enumClass());
        for (E field : enumClass().getEnumConstants()) {
            if (contains(field)) {
                result.add(field);
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return toEnumSet().iterator();
    }
}
