package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.coordinate.stamp.calculator.StampCalculator;

public class FieldLocatorListElementForEntity {

    static <T> ObservableField<T> locate(ObservableEntity observableEntity,
                                         DirectListElementAttributeLocator componentFieldListElementLocator,
                                         StampCalculator stampCalculator) {
        return switch (observableEntity) {
            case ObservableConcept observableConcept -> locateFieldListElementOnConcept(observableConcept, componentFieldListElementLocator, stampCalculator);
            case ObservablePattern observablePattern -> locateFieldListElementOnPattern(observablePattern, componentFieldListElementLocator, stampCalculator);
            case ObservableSemantic observableSemantic -> locateFieldListElementOnSemantic(observableSemantic, componentFieldListElementLocator, stampCalculator);
            case ObservableStamp observableStamp -> locateFieldListElementOnStamp(observableStamp, componentFieldListElementLocator, stampCalculator);
        };
    }

    private static <T> ObservableField<T> locateFieldListElementOnStamp(ObservableStamp observableStamp,
                                                                        DirectListElementAttributeLocator componentFieldListElementLocator,
                                                                        StampCalculator stampCalculator) {
        return observableStamp.getObservableAttributes().get(componentFieldListElementLocator.category());
    }

    private static <T> ObservableField<T> locateFieldListElementOnSemantic(ObservableSemantic observableSemantic,
                                                                           DirectListElementAttributeLocator componentFieldListElementLocator,
                                                                           StampCalculator stampCalculator) {
        return observableSemantic.getObservableAttributes().get(componentFieldListElementLocator.category());
    }

    private static <T> ObservableField<T> locateFieldListElementOnPattern(ObservablePattern observablePattern,
                                                                          DirectListElementAttributeLocator componentFieldListElementLocator,
                                                                          StampCalculator stampCalculator) {
        return observablePattern.getObservableAttributes().get(componentFieldListElementLocator.category());
    }

    private static <T> ObservableField<T> locateFieldListElementOnConcept(ObservableConcept observableConcept,
                                                                          DirectListElementAttributeLocator componentFieldListElementLocator,
                                                                          StampCalculator stampCalculator) {
        return observableConcept.getObservableAttributes().get(componentFieldListElementLocator.category());
    }
}
