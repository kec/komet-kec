package dev.ikm.komet.layout.area.factory;

import dev.ikm.komet.layout.area.GridLayout;
import dev.ikm.tinkar.common.binary.Decoder;
import dev.ikm.tinkar.common.binary.DecoderInput;
import dev.ikm.tinkar.common.binary.Encoder;
import dev.ikm.tinkar.common.binary.EncoderOutput;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;

public record KlSupplementalAreaFactory(String areaFactoryName, GridLayout gridLayout, ImmutableList<KlDynamicAreaFactory> children)
        implements KlDynamicAreaFactory {

    @Encoder
    @Override
    public void encode(EncoderOutput out) {
        out.writeInt(children.size());
        for (KlDynamicAreaFactory child : children) {
            out.write(child);
        }
        out.writeString(areaFactoryName);
        out.write(gridLayout);
    }

    @Decoder
    public static KlSupplementalAreaFactory decode(DecoderInput in) {
        int childCount = in.readInt();
        MutableList<KlDynamicAreaFactory> children = Lists.mutable.ofInitialCapacity(childCount);
        for (int i = 0; i < childCount; i++) {
            children.add(in.decode());
        }
        return new KlSupplementalAreaFactory(in.readString(),
                in.decode(), children.toImmutable());
    }}
