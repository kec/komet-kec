package dev.ikm.komet.layout;

import dev.ikm.komet.layout.preferences.PropertyWithDefault;
import dev.ikm.komet.preferences.KometPreferences;
import javafx.collections.ObservableMap;
import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.UUID;

/**
 * The {@code KlWidget} interface defines a contract for a customizable widget
 * that integrates with a scene graph and provides various layout and styling
 * functionalities. It extends {@code KlGadget} and genericizes the
 * {@code Node} class, representing the underlying JavaFX node.
 *
 * @param <FX> the type of {@code Node} that this widget extends or encapsulates.
 */

public non-sealed interface KlWidget<FX extends Parent> extends KlGadget<FX> {

    /**
     * Enumeration representing layout preferences for a {@code KlWidget}.
     * Each enum constant defines a specific preference key tied to layout
     * and configuration settings used within the {@code KlWidget} or related
     * components. Preferences include layout indices, spans, grow priorities,
     * alignments, and margins.
     * <p>
     * Each preference key has an associated default value, which can be retrieved
     * using the {@code defaultValue()} method. These preferences are specifically
     * designed to work within a {@code GridPane}-based layout.
     * <p>
     * Enum Constants:
     * <p>- COLUMN_INDEX: Represents the column index with a default value of 0.
     * <p>- ROW_INDEX: Represents the row index with a default value of 0.
     * <p>- COLUMN_SPAN: Represents the number of columns spanned, defaulting to 1.
     * <p>- ROW_SPAN: Represents the number of rows spanned, defaulting to 1.
     * <p>- H_GROW: Indicates the horizontal growth priority using {@code Priority}.
     * <p>- V_GROW: Indicates the vertical growth priority using {@code Priority}.
     * <p>- H_ALIGNMENT: Specifies the horizontal alignment using {@code Pos}.
     * <p>- V_ALIGNMENT: Specifies the vertical alignment using {@code Pos}.
     * <p>- MARGIN: Defines the layout margins using {@code Insets}.
     * <p>NOTE: these values are stored in the ObservableMap of the {@code klWidget().properties()},
     * If future KlWidget needs dictate keys not associated with this ObservableMap, they may be accomidated
     * in a separate enumeration class and added to save and restore independently.
     */
    enum PreferenceKeys implements PropertyWithDefault {
        COLUMN_INDEX(0),
        ROW_INDEX(0),
        COLUMN_SPAN(1),
        ROW_SPAN(1),
        H_GROW(Priority.NEVER.name()),
        V_GROW(Priority.NEVER.name()),
        H_ALIGNMENT(HPos.LEFT.name()),
        V_ALIGNMENT(VPos.TOP.name()),
        MARGIN(Insets.EMPTY);

        final Object defaultValue;
        PreferenceKeys(Object defaultValue) {
            this.defaultValue = defaultValue;
        }
        @Override
        public Object defaultValue() {
            return this.defaultValue;
        }
    }
    default KometPreferences preferences() {
        // TODO eliminate this after refactoring existing KlWidgets to support KlGadget, and factories with preferences.
        throw new UnsupportedOperationException("Please override and implement...");
    }
    /**
     * Retrieves the widget representation for this KlWidget.
     *
     * @return The widget instance, represented by the specific implementation of the KlWidget.
     */
    default FX fxGadget() {
        return klWidget();
    }

    /**
     * Retrieves the scene graph node that presents this KlWidget.
     *
     * @param <SGN> The type of the scene graph node extending {@code Node}.
     * @return The scene graph node instance.
     */
    default <SGN extends Parent> SGN klWidget() {
        return (SGN) this;
    }

    default ObservableMap<Object, Object> properties() {
        return klWidget().getProperties();
    }

    /**
     * Sets the column index for the pane in the GridPane layout.
     *
     * @param columnIndex the column index to set for the pane
     */
    default void setColumnIndex(int columnIndex) {
        GridPane.setColumnIndex(klWidget(), columnIndex);
    }

    /**
     * Retrieves the column index for this pane in the GridPane layout.
     *
     * @return the column index of the pane
     */
    default int getColumnIndex() {
        return GridPane.getColumnIndex(klWidget());
    }

    /**
     * Sets the row index for the pane in the GridPane layout.
     *
     * @param rowIndex the row index to set for the pane
     */
    default void setRowIndex(int rowIndex) {
        GridPane.setRowIndex(klWidget(), rowIndex);
    }

    /**
     * Retrieves the row index for this pane in the GridPane layout.
     *
     * @return the row index of the pane
     */
    default int getRowIndex() {
        return GridPane.getRowIndex(klWidget());
    }

    /**
     * Sets the column span for the pane in the GridPane layout.
     *
     * @param colspan the number of columns the pane should span
     */
    default void setColspan(int colspan) {
        GridPane.setColumnSpan(klWidget(), colspan);
    }

    /**
     * Retrieves the column span for the pane in the GridPane layout.
     *
     * @return the number of columns the pane spans
     */
    default int getColspan() {
        return GridPane.getColumnSpan(klWidget());
    }

    /**
     * Sets the row span for the pane in the GridPane layout.
     *
     * @param rowspan the number of rows the pane should span
     */
    default void setRowspan(int rowspan) {
        GridPane.setRowSpan(klWidget(), rowspan);
    }

    /**
     * Retrieves the row span for the pane in the GridPane layout.
     *
     * @return the number of rows the pane spans
     */
    default int getRowspan() {
        return GridPane.getRowSpan(klWidget());
    }

    /**
     * Sets the horizontal grow priority for the pane in the GridPane layout.
     *
     * @param priority the horizontal grow priority to set for the pane
     */
    default void setHgrow(Priority priority) {
        GridPane.setHgrow(klWidget(), priority);
    }

    /**
     * Retrieves the horizontal grow priority for the pane in the GridPane layout.
     *
     * @return the horizontal grow priority of the pane
     */
    default Priority getHgrow() {
        return GridPane.getHgrow(klWidget());
    }

    /**
     * Sets the vertical grow priority for the pane in the GridPane layout.
     *
     * @param priority the vertical grow priority to set for the pane
     */
    default void setVgrow(Priority priority) {
        GridPane.setVgrow(klWidget(), priority);
    }

    /**
     * Retrieves the vertical grow priority for the pane in the GridPane layout.
     *
     * @return the vertical grow priority of the pane
     */
    default Priority getVgrow() {
        return GridPane.getVgrow(klWidget());
    }

    /**
     * Sets the horizontal alignment for the widget within its grid cell.
     *
     * @param hPos the horizontal alignment to apply, specified as an HPos value
     */
    default void setHalignment(HPos hPos) {
        GridPane.setHalignment(klWidget(), hPos);
    }

    /**
     * Retrieves the horizontal alignment of this widget within its grid cell.
     *
     * @return the horizontal alignment represented as an {@code HPos} value.
     */
    default HPos getHalignment() {
        return GridPane.getHalignment(klWidget());
    }

    /**
     * Sets the vertical alignment for this widget within its grid cell in a {@code GridPane} layout.
     *
     * @param vPos the vertical alignment to apply, specified as a {@code VPos} value
     */
    default void setValignment(VPos vPos) {
        GridPane.setValignment(klWidget(), vPos);
    }

    /**
     * Retrieves the vertical alignment of this widget within its grid cell in a {@code GridPane} layout.
     * The alignment is represented as a {@code VPos} value.
     *
     * @return the vertical alignment of the widget within its grid cell.
     */
    default VPos getValignment() {
        return GridPane.getValignment(klWidget());
    }

    /**
     * Sets the margins for the pane in the GridPane layout.
     *
     * @param top the amount of space to be applied to the top of the pane
     * @param right the amount of space to be applied to the right of the pane
     * @param bottom the amount of space to be applied to the bottom of the pane
     * @param left the amount of space to be applied to the left of the pane
     */
    default void setMargins(double top, double right, double bottom, double left) {
        setMargins(new Insets(top, right, bottom, left));
    }

    /**
     * Sets the margins from the insets for the pane in the GridPane layout.
     *
     * @param insets the Insets object containing the top, right, bottom, and left margins
     */
    default void setMargins(Insets insets) {
        GridPane.setMargin(klWidget(), insets);
    }

    /**
     * Retrieves the margins as insets for the pane in the GridPane layout.
     *
     * The insets determine the amount of space to be applied around the pane.
     *
     * @return the Insets object containing the top, right, bottom, and left margins of the pane
     */
    default Insets getMargins() {
        return GridPane.getMargin(klWidget());
    }

    /**
     * Retrieves the unique identifier for this KlWidget. Note that the UUID for the
     * KlWidget is independent of whatever entity it may contain at a particular instant. And the
     * UUID will not change across the life of this Knowledge Layout Component.
     *
     * @return the UUID representing the unique identifier of the KlWidget.
     * @deprecated use klObjectId() instead.
     */
    @Deprecated
    default UUID klWidgetId() {
        return klObjectId();
    }

}
