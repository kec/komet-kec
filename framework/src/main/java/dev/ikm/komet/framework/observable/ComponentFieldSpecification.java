package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.common.binary.*;

import java.util.OptionalInt;

/**
 * Represents a specification for a component field, optionally including an index.
 *
 * This record pairs a {@link ComponentField}, which specifies a field
 * within a component, with an {@link OptionalInt} that provides an optional
 * index. The index is typically used for fields that support indexed access,
 * such as lists or arrays within a component.
 *
 * ComponentField defines the type of field being described and
 * its source, and its usage varies based on the context of the component.
 */
public record ComponentFieldSpecification(ComponentField componentField, OptionalInt index) implements Encodable {

    /**
     * Constructs a {@code ComponentFieldSpecification} with a specified {@link ComponentField} and index.
     * This constructor ensures that the provided {@code ComponentField} supports indexing and associates it
     * with the given index.
     *
     * @param componentField the {@link ComponentField} to be specified, representing the field within a component.
     * @param index the index to associate with the {@code componentField}, provided as an integer.
     * @throws IllegalStateException if the specified {@code componentField} does not support indexing.
     */
    public ComponentFieldSpecification(ComponentField componentField, int index) {
        this(componentField, OptionalInt.of(index));
        if (!componentField.indexed) {
            throw new IllegalStateException("Index specified for unindexed field: " + componentField);
        }
    }

    /**
     * Constructs a {@code ComponentFieldSpecification} with a specified {@link ComponentField}.
     * This constructor does not provide an index and ensures that the provided {@code ComponentField}
     * does not require indexing. If the {@code ComponentField} requires an index, an exception is thrown.
     *
     * @param componentField the {@link ComponentField} to be specified, representing the field within a component.
     *                       This field must not be indexed.
     * @throws IllegalStateException if the specified {@code componentField} requires an index
     *                               but no index is provided.
     */
    public ComponentFieldSpecification(ComponentField componentField) {
        this(componentField, OptionalInt.empty());
        if (componentField.indexed) {
            throw new IllegalStateException("Index must be specified for an indexed field: " + componentField);
        }
    }

    /**
     * Encodes the {@code ComponentFieldSpecification} instance into the provided {@link EncoderOutput}.
     * This will serialize the component's field name as a string and optionally encode an associated index.
     * If an index is present, it is written as an integer; otherwise, a default value of -1 is written.
     *
     * @param out the {@link EncoderOutput} to which this {@code ComponentFieldSpecification} instance
     *            will be serialized.
     */
    @Encoder
    @Override
    public void encode(EncoderOutput out) {
        out.writeString(componentField.name());
        index.ifPresentOrElse(i -> out.writeInt(i), () -> out.writeInt(-1));
    }

    /**
     * Decodes a {@code ComponentFieldSpecification} instance from the provided {@link DecoderInput}.
     * This method reads data from the decoder input stream to reconstruct a {@code ComponentFieldSpecification}
     * object by interpreting serialized values such as the component field and its index.
     *
     * @param in the {@link DecoderInput} from which the {@code ComponentFieldSpecification} will be decoded.
     *           This input contains the serialized byte stream representation of the object.
     * @return a {@code ComponentFieldSpecification} instance decoded from the provided input, containing
     *         the component field and its associated index.
     */
    @Decoder
    public static ComponentFieldSpecification decode(DecoderInput in) {
        return new ComponentFieldSpecification(ComponentField.valueOf(in.readString()), decodeFieldIndex(in));
    }

    /**
     * Decodes a field index from the provided {@link DecoderInput}.
     * If the decoded integer value is -1, it represents an empty optional;
     * otherwise, the integer value is wrapped in an {@code OptionalInt}.
     *
     * @param in the {@link DecoderInput} containing the encoded field index.
     *           This input stream is used to read the serialized integer value.
     * @return an {@code OptionalInt} containing the decoded field index if it is not -1,
     *         or an empty {@code OptionalInt} if the decoded value is -1.
     */
    private static OptionalInt decodeFieldIndex(DecoderInput in) {
        int fieldIndex = in.readInt();
        return fieldIndex == -1 ? OptionalInt.empty() : OptionalInt.of(fieldIndex);
    }

}
