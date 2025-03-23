package dev.ikm.komet.layout.area;

import dev.ikm.tinkar.common.binary.Decoder;
import dev.ikm.tinkar.common.binary.DecoderInput;
import dev.ikm.tinkar.common.binary.Encoder;
import dev.ikm.tinkar.common.binary.EncoderOutput;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;

public record KlSpecifierForArea(String areaFactoryName, GridLayout gridLayout, ImmutableList<KlAreaSpecifier> children)
        implements KlAreaSpecifier {

    @Encoder
    @Override
    public void encode(EncoderOutput out) {
        out.writeInt(children.size());
        for (KlAreaSpecifier child : children) {
            out.write(child);
        }
        out.writeString(areaFactoryName);
        out.write(gridLayout);
    }

    @Decoder
    public static KlSpecifierForArea decode(DecoderInput in) {
        int childCount = in.readInt();
        MutableList<KlAreaSpecifier> children = Lists.mutable.ofInitialCapacity(childCount);
        for (int i = 0; i < childCount; i++) {
            children.add(in.decode());
        }
        return new KlSpecifierForArea(in.readString(),
                in.decode(), children.toImmutable());
    }}
