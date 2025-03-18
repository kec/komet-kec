package dev.ikm.komet.layout.area;

import dev.ikm.komet.layout.KlFactory;
import dev.ikm.komet.layout.preferences.KlPreferencesFactory;
import dev.ikm.tinkar.common.binary.Encodable;
import dev.ikm.tinkar.common.service.PluggableService;
import org.eclipse.collections.api.list.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public sealed interface KlAreaSpecifier
        extends Encodable
        permits KlForArea, KlForFieldArea {

    Logger LOG = LoggerFactory.getLogger(KlAreaSpecifier.class);

    GridLayout gridLayout();

    String areaFactoryName();

    ImmutableList<KlAreaSpecifier> children();

    default
    <KL extends KlArea> KlFactory<KL> makeFactory() {
        if (areaFactoryName() == null || areaFactoryName().isBlank()) {
            throw new IllegalArgumentException("areaFactoryName is null or blank");
        }
        try {
            Class factoryClass = PluggableService.forName(areaFactoryName());
            Constructor constructor = factoryClass.getConstructor();
            KlFactory<KL> factory = (KlFactory<KL>) constructor.newInstance();
            return factory;
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            LOG.error("Constructing: {}", areaFactoryName(), e);
            throw new IllegalArgumentException("Unable to construct factory for: " + areaFactoryName(), e);
        }
    }

    default
    <KL extends KlArea> KL makeArea(KlPreferencesFactory preferencesFactory) {
        KlFactory<KL> areaFactory = makeFactory();
        return areaFactory.create(preferencesFactory, gridLayout());
    }
}
