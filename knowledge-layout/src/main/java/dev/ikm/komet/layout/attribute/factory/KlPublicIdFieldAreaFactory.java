package dev.ikm.komet.layout.attribute.factory;

import dev.ikm.komet.layout.attribute.KlPublicIdAttributeArea;
import dev.ikm.tinkar.common.id.PublicId;
import javafx.scene.layout.Region;

public interface KlPublicIdFieldAreaFactory<FX extends Region> extends KlFieldAreaFactory<PublicId, FX, KlPublicIdAttributeArea<FX>> {
}
