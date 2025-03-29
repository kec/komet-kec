package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.coordinate.stamp.calculator.StampCalculator;

public class FieldLocatorForVersion {

    static <T> ObservableField<T> locate(ObservableVersion observableVersion,
                                         DirectSingularAttributeLocator componentFieldLocator,
                                         StampCalculator stampCalculator) {
        return switch (observableVersion) {
            case ObservableConceptVersion observableConceptVersion ->
                    locateFieldOnConceptVersion(observableConceptVersion, componentFieldLocator, stampCalculator);
            case ObservablePatternVersion observablePatternVersion ->
                    locateFieldOnPatternVersion(observablePatternVersion, componentFieldLocator, stampCalculator);
            case ObservableSemanticVersion observableSemanticVersion ->
                    locateFieldOnSemanticVersion(observableSemanticVersion, componentFieldLocator, stampCalculator);
            case ObservableStampVersion observableStampVersion ->
                    locateFieldOnStampVersion(observableStampVersion, componentFieldLocator, stampCalculator);
        };
    }

    private static <T> ObservableField<T> locateFieldOnStampVersion(ObservableStampVersion observableStampVersion,
                                                                    DirectSingularAttributeLocator componentFieldLocator,
                                                                    StampCalculator stampCalculator) {
        return observableStampVersion.getObservableAttributes().get(componentFieldLocator.category());
    }

    private static <T> ObservableField<T> locateFieldOnSemanticVersion(ObservableSemanticVersion observableSemanticVersion,
                                                                       DirectSingularAttributeLocator componentFieldLocator,
                                                                       StampCalculator stampCalculator) {
        return observableSemanticVersion.getObservableAttributes().get(componentFieldLocator.category());
    }

    private static <T> ObservableField<T> locateFieldOnPatternVersion(ObservablePatternVersion observablePatternVersion,
                                                                      DirectSingularAttributeLocator componentFieldLocator,
                                                                      StampCalculator stampCalculator) {
        return observablePatternVersion.getObservableAttributes().get(componentFieldLocator.category());
    }

    private static <T> ObservableField<T> locateFieldOnConceptVersion(ObservableConceptVersion observableConceptVersion,
                                                                      DirectSingularAttributeLocator componentFieldLocator,
                                                                      StampCalculator stampCalculator) {
        return observableConceptVersion.getObservableAttributes().get(componentFieldLocator.category());
    }


}
