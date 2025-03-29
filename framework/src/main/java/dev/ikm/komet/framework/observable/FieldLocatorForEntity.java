package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.coordinate.stamp.calculator.StampCalculator;

public class FieldLocatorForEntity {

    static <T> ObservableField<T> locate(ObservableEntity observableEntity,
                                         DirectSingularAttributeLocator componentFieldLocator,
                                         StampCalculator stampCalculator){
        return switch (observableEntity) {
            case ObservableConcept observableConcept -> locateFieldOnConcept(observableConcept, componentFieldLocator, stampCalculator);
            case ObservablePattern observablePattern -> locateFieldOnPattern(observablePattern, componentFieldLocator, stampCalculator);
            case ObservableSemantic observableSemantic -> locateFieldOnSemantic(observableSemantic, componentFieldLocator, stampCalculator);
            case ObservableStamp observableStamp -> locateFieldOnStamp(observableStamp, componentFieldLocator, stampCalculator);
        };
    }

    private static <T> ObservableField<T> locateFieldOnStamp(ObservableStamp observableStamp,
                                                             DirectSingularAttributeLocator componentFieldLocator,
                                                             StampCalculator stampCalculator) {
        return observableStamp.getObservableAttributes().get(componentFieldLocator.category());
    }

    private static <T> ObservableField<T> locateFieldOnSemantic(ObservableSemantic observableSemantic,
                                                                DirectSingularAttributeLocator componentFieldLocator,
                                                                StampCalculator stampCalculator) {
        return observableSemantic.getObservableAttributes().get(componentFieldLocator.category());
    }

    private static <T> ObservableField<T> locateFieldOnPattern(ObservablePattern observablePattern,
                                                               DirectSingularAttributeLocator componentFieldLocator,
                                                               StampCalculator stampCalculator) {
        return observablePattern.getObservableAttributes().get(componentFieldLocator.category());
    }

    private static <T> ObservableField<T> locateFieldOnConcept(ObservableConcept observableConcept,
                                                               DirectSingularAttributeLocator componentFieldLocator,
                                                               StampCalculator stampCalculator) {
        return observableConcept.getObservableAttributes().get(componentFieldLocator.category());
    }

}
