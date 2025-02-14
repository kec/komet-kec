package dev.ikm.komet.layout.component.field;

import dev.ikm.komet.framework.observable.ObservableField;
import dev.ikm.komet.layout.KlWidget;
import dev.ikm.tinkar.common.bind.ClassConceptBinding;
import dev.ikm.tinkar.common.bind.annotations.axioms.ParentProxy;
import dev.ikm.tinkar.common.bind.annotations.names.FullyQualifiedName;
import dev.ikm.tinkar.common.bind.annotations.names.RegularName;
import dev.ikm.tinkar.common.bind.annotations.publicid.PublicIdAnnotation;
import dev.ikm.tinkar.common.bind.annotations.publicid.UuidAnnotation;
import javafx.scene.Parent;

/**
 * Represents a pane in the Knowledge Layout framework that is associated with a specific field.
 * This interface provides mechanisms to interact with and observe the value of the field,
 * as well as property access for the field data.
 *
 * @param <T> the type of the value associated with this field pane
 */
@FullyQualifiedName("Knowledge Layout field pane")
@RegularName("Field pane")
@ParentProxy(parentName = "Komet panels (SOLOR)",
        parentPublicId = @PublicIdAnnotation(@UuidAnnotation("b3d1cdf6-27a5-502d-8f16-ed026a7b9d15")))
public sealed interface KlFieldPane<T> extends KlWidget<Parent>, ClassConceptBinding permits KlBooleanFieldPane, KlComponentFieldPane, KlConceptFieldPane, KlGenericFieldPane {
    /**
     * Retrieves the value associated with the field pane by accessing the value
     * of the underlying {@code ObservableField} instance.
     *
     * @return the value of type {@code T} managed by the associated {@code ObservableField}
     */
    default T fieldValue() {
        return getField().value();
    }

    /**
     * Sets the {@code ObservableField} for this field pane.
     *
     * @param field the {@code ObservableField} instance to be associated with this field pane.
     *              It provides the value and observable properties for this field.
     */
    void setField(ObservableField<T> field);

    /**
     * Retrieves the {@code ObservableField} instance associated with this field pane.
     * The {@code ObservableField} provides access to the field's value and supports
     * observation of property changes.
     *
     * @return the {@code ObservableField} instance managing the field's data and properties
     */
    ObservableField<T> getField();

}
