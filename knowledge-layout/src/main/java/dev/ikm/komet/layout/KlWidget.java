package dev.ikm.komet.layout;

import dev.ikm.komet.layout.preferences.PropertyWithDefault;
import dev.ikm.komet.preferences.KometPreferences;
import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableMap;
import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.util.Optional;
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
        MARGIN(Insets.EMPTY),
        MAX_HEIGHT(Double.MAX_VALUE),
        MAX_WIDTH(Double.MAX_VALUE),
        PREFERRED_HEIGHT(Region.USE_COMPUTED_SIZE),
        PREFERRED_WIDTH(Region.USE_COMPUTED_SIZE);

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
     * Retrieves the maxHeight property of the associated Region, if present.
     *
     * @return an Optional containing the maxHeight DoubleProperty of the Region if the fxGadget is an instance of Region;
     *         otherwise, an empty Optional
     */
    default Optional<DoubleProperty> maxHeightPropertyOptional() {
        if (fxGadget() instanceof Region region) {
            return Optional.of(region.maxHeightProperty());
        }
        return Optional.empty();
    }

    /**
     * Sets the maximum height for this widget's associated region,
     * if the underlying FX gadget is an instance of {@code Region}.
     *
     * @param maxHeight the maximum height value to set for the associated region
     */
    default void setMaxHeight(double maxHeight) {
        if (fxGadget() instanceof Region region) {
            region.setMaxHeight(maxHeight);
        }
    }

    /**
     * Retrieves the maximum height for the underlying region associated with this widget.
     * If the underlying FX gadget is an instance of {@code Region}, the maximum height
     * specific to that region is returned. Otherwise, the default value for using the
     * computed size is returned.
     *
     * @return the maximum height of the region if applicable, otherwise the value
     *         {@code Region.USE_COMPUTED_SIZE}.
     */
    default double getMaxHeight() {
        if (fxGadget() instanceof Region region) {
            return region.getMaxHeight();
        }
        return Region.USE_COMPUTED_SIZE;
    }

    /**
     * Retrieves the optional maxWidth property of the current fxGadget if it is an instance of Region.
     *
     * @return An Optional containing the maxWidth property as a DoubleProperty if the fxGadget is a Region, or an empty Optional if not.
     */
    default Optional<DoubleProperty> maxWidthPropertyOptional() {
        if (fxGadget() instanceof Region region) {
            return Optional.of(region.maxWidthProperty());
        }
        return Optional.empty();
    }

    /**
     * Sets the maximum width for this widget's associated region, if the underlying FX
     * gadget is an instance of {@code Region}.
     *
     * @param maxWidth the maximum width value to set for the associated region
     */
    default void setMaxWidth(double maxWidth) {
        if (fxGadget() instanceof Region region) {
            region.setMaxWidth(maxWidth);
        }
    }

    /**
     * Retrieves the maximum width for the underlying region associated with this widget.
     * If the underlying FX gadget is an instance of {@code Region}, the maximum width
     * specific to that region is returned. Otherwise, the default value for using the
     * computed size is returned.
     *
     * @return the maximum width of the region if applicable, otherwise the value
     *         {@code Region.USE_COMPUTED_SIZE}.
     */
    default double getMaxWidth() {
        if (fxGadget() instanceof Region region) {
            return region.getMaxWidth();
        }
        return Region.USE_COMPUTED_SIZE;
    }

    /**
     * Retrieves the preferred height property of the underlying JavaFX Region
     * if the fxGadget is an instance of Region.
     *
     * @return an Optional containing the preferred height property as a DoubleProperty
     *         if the fxGadget is an instance of Region, otherwise an empty Optional.
     */
    default Optional<DoubleProperty> prefHeightPropertyOptional() {
        if (fxGadget() instanceof Region region) {
            return Optional.of(region.prefHeightProperty());
        }
        return Optional.empty();
    }

    /**
     * Sets the preferred height for this widget's associated region.
     * If the underlying FX gadget is an instance of {@code Region}, the preferred height
     * of the region is updated to the specified value.
     *
     * @param prefHeight the preferred height to set for the associated region
     */
    default void setPrefHeight(double prefHeight) {
        if (fxGadget() instanceof Region region) {
            region.setPrefHeight(prefHeight);
        }
    }

    /**
     * Retrieves the preferred height of the associated region.
     * If the underlying FX gadget is an instance of {@code Region}, the preferred height
     * specific to that region is returned. Otherwise, the value {@code Region.USE_COMPUTED_SIZE} is returned.
     *
     * @return the preferred height of the region if applicable, otherwise the value {@code Region.USE_COMPUTED_SIZE}.
     */
    default double getPrefHeight() {
        if (fxGadget() instanceof Region region) {
            return region.getPrefHeight();
        }
        return Region.USE_COMPUTED_SIZE;
    }
    /**
     * Retrieves the optional `DoubleProperty` that represents the preferred width of the underlying FX gadget,
     * if the FX gadget is an instance of `Region`.
     *
     * @return an `Optional` containing the preferred width property if the FX gadget is a `Region`, otherwise an empty `Optional`
     */
    default Optional<DoubleProperty> prefWidthPropertyOptional() {
        if (fxGadget() instanceof Region region) {
            return Optional.of(region.prefWidthProperty());
        }
        return Optional.empty();
    }

    /**
     * Sets the preferred width for the associated region of this widget if the underlying
     * FX gadget is an instance of {@code Region}. Updates the region's preferred width to the
     * specified value.
     *
     * @param prefWidth the preferred width to set for the associated region
     */
    default void setPrefWidth(double prefWidth) {
        if (fxGadget() instanceof Region region) {
            region.setPrefWidth(prefWidth);
        }
    }

    /**
     * Retrieves the preferred width of the associated region.
     * If the underlying FX gadget is an instance of {@code Region}, the preferred width
     * specific to that region is returned. Otherwise, the value {@code Region.USE_COMPUTED_SIZE} is returned.
     *
     * @return the preferred width of the region if applicable, otherwise the value {@code Region.USE_COMPUTED_SIZE}.
     */
    default double getPrefWidth() {
        if (fxGadget() instanceof Region region) {
            return region.getPrefWidth();
        }
        return Region.USE_COMPUTED_SIZE;
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
