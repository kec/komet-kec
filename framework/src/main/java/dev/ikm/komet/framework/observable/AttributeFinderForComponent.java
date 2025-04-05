package dev.ikm.komet.framework.observable;

import dev.ikm.komet.framework.observable.locators.DirectListElementLocator;
import dev.ikm.komet.framework.observable.locators.DirectListElementLocatorWithObservable;
import dev.ikm.komet.framework.observable.locators.DirectSingularAttributeLocator;

import java.util.Optional;

public class AttributeFinderForComponent {

    static ObservableAttribute locate(ObservableComponent observableComponent,
                                         DirectSingularAttributeLocator attributeLocator){
        return switch (observableComponent) {
            case ObservableConcept observableConcept -> locateAttributeFilter(observableConcept, attributeLocator);
            case ObservablePattern observablePattern -> locateAttributeFilter(observablePattern, attributeLocator);
            case ObservableSemantic observableSemantic -> locateAttributeFilter(observableSemantic, attributeLocator);
            case ObservableStamp observableStamp -> locateAttributeFilter(observableStamp, attributeLocator);
            case ObservableConceptVersion observableConceptVersion -> locateAttributeFilter(observableConceptVersion, attributeLocator);
            case ObservablePatternVersion observablePatternVersion -> locateAttributeFilter(observablePatternVersion, attributeLocator);
            case ObservableSemanticVersion observableSemanticVersion -> locateAttributeFilter(observableSemanticVersion, attributeLocator);
            case ObservableStampVersion observableStampVersion -> locateAttributeFilter(observableStampVersion, attributeLocator);
        };
    }

    static ObservableAttribute locate(ObservableComponent observableComponent,
                                         DirectListElementLocator attributeLocator) {
        return switch (observableComponent) {
            case ObservableConcept observableConcept -> locateAttributeFilter(observableConcept, attributeLocator);
            case ObservablePattern observablePattern -> locateAttributeFilter(observablePattern, attributeLocator);
            case ObservableSemantic observableSemantic -> locateAttributeFilter(observableSemantic, attributeLocator);
            case ObservableStamp observableStamp -> locateAttributeFilter(observableStamp, attributeLocator);
            case ObservableConceptVersion observableConceptVersion -> locateAttributeFilter(observableConceptVersion, attributeLocator);
            case ObservablePatternVersion observablePatternVersion -> locateAttributeFilter(observablePatternVersion, attributeLocator);
            case ObservableSemanticVersion observableSemanticVersion -> locateAttributeFilter(observableSemanticVersion, attributeLocator);
            case ObservableStampVersion observableStampVersion -> locateAttributeFilter(observableStampVersion, attributeLocator);
        };
    }

    public static ObservableAttribute locateAttributeFilter(ObservableComponent observableComponent, DirectListElementLocator listElementLocator) {
        Optional<ObservableAttributeWithLocator> optionalAttributeWithLocator = observableComponent.getObservableAttributes().stream().filter(
                streamAttributeWithLocator ->
                        ((streamAttributeWithLocator instanceof DirectListElementLocatorWithObservable streamListElementLocator) &&
                                (streamListElementLocator.index() == listElementLocator.index()) &&
                                listElementLocator.category().equals(streamListElementLocator.category()))
                ).findFirst();
        return optionalAttributeWithLocator.get().observableAttribute();
    }


    public static ObservableAttribute locateAttributeFilter(ObservableComponent observableComponent, DirectSingularAttributeLocator attributeLocator) {
        Optional<ObservableAttributeWithLocator> optionalAttributeWithLocator = observableComponent.getObservableAttributes().stream().filter(
                attributeWithLocator -> attributeLocator.equals(attributeLocator.category())).findFirst();
        return optionalAttributeWithLocator.get().observableAttribute();
    }
}
