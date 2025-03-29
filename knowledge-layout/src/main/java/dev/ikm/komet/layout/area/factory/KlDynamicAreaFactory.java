package dev.ikm.komet.layout.area.factory;

import dev.ikm.komet.layout.KlFactory;
import dev.ikm.komet.layout.area.GridLayout;
import dev.ikm.komet.layout.area.KlArea;
import dev.ikm.komet.layout.preferences.KlPreferencesFactory;
import dev.ikm.komet.preferences.KometPreferences;
import dev.ikm.tinkar.common.binary.Encodable;
import dev.ikm.tinkar.common.service.PluggableService;
import javafx.scene.layout.GridPane;
import org.eclipse.collections.api.list.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public sealed interface KlDynamicAreaFactory
        extends Encodable
        permits KlSupplementalAreaFactory, KlAttributeAreaFactory {

    Logger LOG = LoggerFactory.getLogger(KlDynamicAreaFactory.class);

    GridLayout gridLayout();

    String areaFactoryName();

    ImmutableList<KlDynamicAreaFactory> children();

    default Class factoryClass() {
        if (areaFactoryName() == null || areaFactoryName().isBlank()) {
            throw new IllegalArgumentException("areaFactoryName is null or blank");
        }
        try {
            return PluggableService.forName(areaFactoryName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    default
    <KL extends KlArea> KlFactory<KL> makeFactory() {
        try {
            Constructor constructor = factoryClass().getConstructor();
            KlFactory<KL> factory = (KlFactory<KL>) constructor.newInstance();
            return factory;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            LOG.error("Constructing: {}", areaFactoryName(), e);
            throw new IllegalArgumentException("Unable to construct factory for: " + areaFactoryName(), e);
        }
    }

    default
    <KL extends KlArea> KL makeAreaInParentAndAddToGrid(GridPane gridPane, KometPreferences parentPreferences) {
        KL klArea = makeAreaInParent(parentPreferences);
        klArea.setGridLayout(gridLayout());
        gridPane.getChildren().add(klArea.fxObject());
        return klArea;
    }

    default
    <KL extends KlArea> KL makeAreaInParent(KometPreferences parentPreferences) {
        KlPreferencesFactory preferencesFactory =
                KlPreferencesFactory.create(parentPreferences, factoryClass());
        return makeArea(preferencesFactory);
    }

    default
    <KL extends KlArea> KL makeArea(KlPreferencesFactory preferencesFactory) {
        KlFactory<KL> areaFactory = makeFactory();
        return areaFactory.create(preferencesFactory, gridLayout());
    }
}
