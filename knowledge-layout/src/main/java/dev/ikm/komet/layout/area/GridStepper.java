package dev.ikm.komet.layout.area;

import dev.ikm.komet.framework.observable.FeatureLocator;
import dev.ikm.komet.layout.LayoutKey;

public interface GridStepper {

    void setStep(GridStep step);

    void reset();

    int row();

    int column();

    AreaGridSettings nextForFeature(LayoutKey.ForArea forAreaLayoutKey, FeatureLocator locator, String factoryClassName);

    AreaGridSettings nextForSupplemental(LayoutKey.ForArea forAreaLayoutKey, String factoryClassName);

}
