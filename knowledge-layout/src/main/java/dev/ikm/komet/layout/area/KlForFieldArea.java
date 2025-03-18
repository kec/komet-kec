package dev.ikm.komet.layout.area;

import dev.ikm.komet.framework.observable.FieldLocator;
import dev.ikm.tinkar.common.binary.*;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public record KlForFieldArea(String areaFactoryName, FieldLocator fieldLocator,
                             GridLayout gridLayout, ImmutableList<KlAreaSpecifier> children)
        implements Encodable, KlAreaSpecifier {
        private static final Logger LOG = LoggerFactory.getLogger(KlForFieldArea.class);

    @Encoder
    @Override
    public void encode(EncoderOutput out) {
        out.writeInt(children.size());
        for (KlAreaSpecifier child : children) {
            out.write(child);
        }
        out.writeString(areaFactoryName);
        out.write(fieldLocator);
        out.write(gridLayout);
    }

    @Decoder
    public static KlForFieldArea decode(DecoderInput in) {
        int childCount = in.readInt();
        MutableList<KlAreaSpecifier> children = Lists.mutable.ofInitialCapacity(childCount);
        for (int i = 0; i < childCount; i++) {
            children.add(in.decode());
        }
        return new KlForFieldArea(in.readString(),
                in.decode(), in.decode(), children.toImmutable());
    }
}
