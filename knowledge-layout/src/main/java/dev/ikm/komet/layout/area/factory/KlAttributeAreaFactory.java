package dev.ikm.komet.layout.area.factory;

import dev.ikm.komet.framework.observable.AttributeLocator;
import dev.ikm.komet.layout.area.GridLayout;
import dev.ikm.tinkar.common.binary.*;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public record KlAttributeAreaFactory(String areaFactoryName, AttributeLocator attributeLocator,
                                     GridLayout gridLayout, ImmutableList<KlDynamicAreaFactory> children)
        implements Encodable, KlDynamicAreaFactory {
        private static final Logger LOG = LoggerFactory.getLogger(KlAttributeAreaFactory.class);

    @Encoder
    @Override
    public void encode(EncoderOutput out) {
        out.writeInt(children.size());
        for (KlDynamicAreaFactory child : children) {
            out.write(child);
        }
        out.writeString(areaFactoryName);
        out.write(attributeLocator);
        out.write(gridLayout);
    }

    @Decoder
    public static KlAttributeAreaFactory decode(DecoderInput in) {
        int childCount = in.readInt();
        MutableList<KlDynamicAreaFactory> children = Lists.mutable.ofInitialCapacity(childCount);
        for (int i = 0; i < childCount; i++) {
            children.add(in.decode());
        }
        return new KlAttributeAreaFactory(in.readString(),
                in.decode(), in.decode(), children.toImmutable());
    }
}
