package dev.ikm.komet.kview.klfields;

import dev.ikm.komet.framework.observable.ObservableField;
import dev.ikm.komet.framework.view.ObservableView;
import dev.ikm.komet.layout.version.field.KlField;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Region;

public abstract class BaseDefaultKlField<T> implements KlField<T> {
    protected final ObservableField<T> observableField;
    protected final ObservableView observableView;

    protected ObjectProperty<Region> fxObject = new SimpleObjectProperty<>() {
        @Override
        protected void invalidated() {
            Tooltip.install(get(), tooltip);
        }
    };

    protected final boolean isEditable;

    protected final Tooltip tooltip = new Tooltip();

    private final String title;

    public BaseDefaultKlField(ObservableField<T> observableField, ObservableView observableView, boolean isEditable) {
        this.observableField = observableField;
        this.observableView = observableView;

        this.isEditable = isEditable;

        title = observableView.getDescriptionTextOrNid(field().definition(observableView.calculator()).meaningNid())
                + ":";

        tooltip.setText(observableView.getDescriptionTextOrNid(observableField.fieldDefinition(observableView.calculator()).purposeNid()));
    }

    protected void updateTooltipText() {
        tooltip.setText(observableView.getDescriptionTextOrNid(observableField.fieldDefinition(observableView.calculator()).purposeNid()));
    }

    @Override
    public void restoreFromPreferencesOrDefaults() {
        // Not supported
    }

    // -- on edit action
    private ObjectProperty<Runnable> onEditAction = new SimpleObjectProperty<>();
    public Runnable getOnEditAction() { return onEditAction.get(); }
    public ObjectProperty<Runnable> onEditActionProperty() { return onEditAction; }
    public void setOnEditAction(Runnable onEditAction) { this.onEditAction.set(onEditAction); }

    // -- attribute
    @Override
    public ObservableField<T> field() {
        return observableField;
    }

    // -- title
    public String getTitle() { return title; }

    // -- klWidget
    protected void setFxObject(Region klWidget) { this.fxObject.set(klWidget); }

    @Override
    public Region fxObject() {
        return fxObject.get();
    }

    @Override
    public void knowledgeLayoutUnbind() {
        // not implemented here
    }

    @Override
    public void knowledgeLayoutBind() {
        // not implemented here
    }

    @Override
    public void save() {
        // not implemented here
    }
}