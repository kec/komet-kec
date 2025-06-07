package dev.ikm.komet.layout;

import dev.ikm.komet.layout.area.*;
import dev.ikm.komet.layout.component.KlChronologyArea;
import dev.ikm.komet.layout.component.KlMultiComponentArea;
import dev.ikm.komet.layout.preferences.KlPreferencesFactory;
import dev.ikm.komet.layout.preferences.PropertyWithDefault;
import dev.ikm.komet.layout.version.KlMultiVersionArea;
import dev.ikm.komet.layout.version.KlVersionArea;
import dev.ikm.komet.layout.window.KlRenderView;
import dev.ikm.komet.preferences.KometPreferences;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableMap;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.util.Objects;
import java.util.Optional;

/**
 * The {@code KlArea} interface represents a fundamental building block within
 * the Knowledge Layout framework, intended for managing and organizing user interface
 * regions. This sealed interface defines the contract for various specialized
 * pane or area types, enabling structured, type-safe layouts and interactions
 * within the framework.
 * <p>
 * Implementations of this interface can serve distinct purposes, such as managing
 * components, versions, fields, or supplemental layouts. By leveraging a type-safe
 * hierarchy, the {@code KlArea} interface ensures consistent and extensible design
 * across different areas of the framework.
 * <p>
 * @param <FX> the type of JavaFX {@code Region} that serves as the base node for this area
 * <p>
 * @see KlChronologyArea
 * @see KlVersionArea
 * @see KlPropertyArea
 * @see KlSupplementalArea
 */
public sealed interface KlArea<FX extends Region>
        extends KlGadget<FX>
        permits KlWidget, KlAssociationArea, KlGenericArea, KlPropertyArea, KlSupplementalArea,
        KlChronologyArea, KlMultiVersionArea, KlVersionArea {

    /**
     * Keys for objects that {@code KlWidget}'s will store in the properties of their associated
     * JavaFx {@code Node}s. Some of these objects will provide caching and computation
     * functionality (behavior), and may not be strictly data carriers. In those cases,
     * where the state must be saved and restored, the {@code KlWidget}'s class is responsible for
     * populating the properties with objects derived from, and saved to, a corresponding
     * KometPreferencesNode with a corresponding PropertyKey.
     */
    enum PropertyKeys {

        /**
         * Represents a property key used to associate the master {@code KnowledgeLayout} object with a JavaFX {@code Node}.
         * The {@code KL_MASTER_LAYOUT} key is intended to enable dynamic attachment of a master layout object
         * or behavior, which could include layout configurations, preferences, or contextual information.
         * This key facilitates the management of layout-specific data and behaviors within the system,
         * supporting complex UI arrangements or hierarchical relationships between components.
         * <p>
         * A Master Layout contains the LayoutOverrides that is passed to all dependent {@code LayoutComputer} objects.
         */
        KL_MASTER_LAYOUT
    }

    /**
     * Enumeration for defining preference keys used in a GridLayout configuration. Each key is
     * associated with a default value, which can be used when the specific property is not explicitly set.
     *
     * This enum implements the PropertyWithDefault interface, allowing for retrieval of default values
     * associated with each specific preference key.
     *
     * The keys and their defaults represent different layout properties such as column index, row index,
     * column and row spans, growth behavior, alignments, margins, dimensions, and fill behaviors,
     * commonly used in grid-based layouts.
     */
    enum PreferenceKeys implements PropertyWithDefault {
        COLUMN_INDEX(AreaGridSettings.DEFAULT.columnIndex()),
        ROW_INDEX(AreaGridSettings.DEFAULT.rowIndex()),
        COLUMN_SPAN(AreaGridSettings.DEFAULT.columnSpan()),
        ROW_SPAN(AreaGridSettings.DEFAULT.rowSpan()),
        H_GROW(AreaGridSettings.DEFAULT.hGrow()),
        V_GROW(AreaGridSettings.DEFAULT.vGrow()),
        H_ALIGNMENT(AreaGridSettings.DEFAULT.hAlignment()),
        V_ALIGNMENT(AreaGridSettings.DEFAULT.vAlignment()),
        MARGIN(AreaGridSettings.DEFAULT.margin()),
        MAX_HEIGHT(AreaGridSettings.DEFAULT.maxHeight()),
        MAX_WIDTH(AreaGridSettings.DEFAULT.maxWidth()),
        PREFERRED_HEIGHT(AreaGridSettings.DEFAULT.preferredHeight()),
        PREFERRED_WIDTH(AreaGridSettings.DEFAULT.preferredWidth()),
        FILL_HEIGHT(AreaGridSettings.DEFAULT.fillHeight()),
        FILL_WIDTH(AreaGridSettings.DEFAULT.fillWidth()),
        VISIBLE(AreaGridSettings.DEFAULT.visible()),
        LAYOUT_KEY(AreaGridSettings.DEFAULT.layoutKeyForArea()),
        AREA_FACTORY_CLASS_NAME(AreaGridSettings.DEFAULT.areaFactoryClassName());

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

    default KnowledgeLayout getMasterLayout() {
        Parent fx = fxObject();
        KnowledgeLayout masterLayout = (KnowledgeLayout) fx.getProperties().get(PropertyKeys.KL_MASTER_LAYOUT);
        while (masterLayout == null) {
            if (fx.getParent() != null) {
                fx = fx.getParent();
                masterLayout = (KnowledgeLayout) fx.getProperties().get(PropertyKeys.KL_MASTER_LAYOUT);
            } else {
                Scene scene = fx.getScene();
                KlObject klPeer = (KlObject) scene.getProperties().get(KlObject.PropertyKeys.KL_PEER);
                masterLayout = switch (klPeer) {
                    case KlRenderView renderView -> renderView.getMasterLayout();
                    case null -> throw new IllegalStateException("Can't find master layout in scene graph. KL_PEER is null. ");
                    default -> throw new IllegalStateException("Can't find master layout in scene graph. " +
                            "KL_PEER is not a KlRenderView: " + klPeer);
                };
            }
        }
        return masterLayout;
    }

    default void setMasterLayout(KnowledgeLayout masterLayout) {
        properties().put(PropertyKeys.KL_MASTER_LAYOUT, masterLayout);
    }

    FX fxObject();

    default ObservableMap<Object, Object> properties() {
        return fxObject().getProperties();
    }

    /**
     * Retrieves a GridLayout instance with the current configuration.
     *
     * @return a configured GridLayout object with the specified column index,
     *         row index, colspan, rowspan, grow priorities, alignments, margins,
     *         and size constraints.
     */
    default AreaGridSettings getAreaLayout() {
        return new AreaGridSettings(
                getColumnIndex(),
                getRowIndex(),
                getColspan(),
                getRowspan(),
                getHgrow(),
                getVgrow(),
                getHalignment(),
                getValignment(),
                getMargins(),
                getMaxHeight(),
                getMaxWidth(),
                getPrefHeight(),
                getPrefWidth(),
                getFillHeight(),
                getFillWidth(),
                getVisible(),
                getLayoutKeyForArea(),
                getAreaFactoryClassName()
        );
    }

    /**
     * Configures the grid layout properties for a component based on the given GridLayout object.
     *
     * @param areaGridSettings the GridLayout object containing the layout configuration
     */
    default void setGridLayout(AreaGridSettings areaGridSettings) {
        setColumnIndex(areaGridSettings.columnIndex());
        setRowIndex(areaGridSettings.rowIndex());
        setColspan(areaGridSettings.columnSpan());
        setRowspan(areaGridSettings.rowSpan());
        setHgrow(areaGridSettings.hGrow());
        setVgrow(areaGridSettings.vGrow());
        setHalignment(areaGridSettings.hAlignment());
        setValignment(areaGridSettings.vAlignment());
        setMargins(areaGridSettings.margin());
        setMaxHeight(areaGridSettings.maxHeight());
        setMaxWidth(areaGridSettings.maxWidth());
        setPrefHeight(areaGridSettings.preferredHeight());
        setPrefWidth(areaGridSettings.preferredWidth());
        setFillHeight(areaGridSettings.fillHeight());
        setFillWidth(areaGridSettings.fillWidth());
        setVisible(areaGridSettings.visible());
        setLayoutKeyForArea(areaGridSettings.layoutKeyForArea());
        setAreaFactoryClassName(areaGridSettings.areaFactoryClassName());
    }

    /**
     * Sets the column index for the pane in the GridPane layout.
     *
     * @param columnIndex the column index to set for the pane
     */
    default void setColumnIndex(int columnIndex) {
        GridPane.setColumnIndex(fxObject(), columnIndex);
    }

    /**
     * Retrieves the column index for this pane in the GridPane layout.
     *
     * @return the column index of the pane
     */
    default int getColumnIndex() {
        return GridPane.getColumnIndex(fxObject());
    }

    /**
     * Sets the row index for the pane in the GridPane layout.
     *
     * @param rowIndex the row index to set for the pane
     */
    default void setRowIndex(int rowIndex) {
        GridPane.setRowIndex(fxObject(), rowIndex);
    }

    /**
     * Retrieves the row index for this pane in the GridPane layout.
     *
     * @return the row index of the pane
     */
    default int getRowIndex() {
        return GridPane.getRowIndex(fxObject());
    }

    /**
     * Sets the column span for the pane in the GridPane layout.
     *
     * @param colspan the number of columns the pane should span
     */
    default void setColspan(int colspan) {
        GridPane.setColumnSpan(fxObject(), colspan);
    }

    /**
     * Retrieves the column span for the pane in the GridPane layout.
     *
     * @return the number of columns the pane spans
     */
    default int getColspan() {
        return GridPane.getColumnSpan(fxObject());
    }

    /**
     * Sets the row span for the pane in the GridPane layout.
     *
     * @param rowspan the number of rows the pane should span
     */
    default void setRowspan(int rowspan) {
        GridPane.setRowSpan(fxObject(), rowspan);
    }

    /**
     * Retrieves the row span for the pane in the GridPane layout.
     *
     * @return the number of rows the pane spans
     */
    default int getRowspan() {
        return GridPane.getRowSpan(fxObject());
    }

    /**
     * Sets the horizontal grow priority for the pane in the GridPane layout.
     *
     * @param priority the horizontal grow priority to set for the pane
     */
    default void setHgrow(Priority priority) {
        GridPane.setHgrow(fxObject(), priority);
    }

    /**
     * Retrieves the horizontal grow priority for the pane in the GridPane layout.
     *
     * @return the horizontal grow priority of the pane
     */
    default Priority getHgrow() {
        return GridPane.getHgrow(fxObject());
    }

    /**
     * Sets the vertical grow priority for the pane in the GridPane layout.
     *
     * @param priority the vertical grow priority to set for the pane
     */
    default void setVgrow(Priority priority) {
        GridPane.setVgrow(fxObject(), priority);
    }

    /**
     * Retrieves the vertical grow priority for the pane in the GridPane layout.
     *
     * @return the vertical grow priority of the pane
     */
    default Priority getVgrow() {
        return GridPane.getVgrow(fxObject());
    }

    /**
     * Sets the horizontal alignment for the widget within its grid cell.
     *
     * @param hPos the horizontal alignment to apply, specified as an HPos value
     */
    default void setHalignment(HPos hPos) {
        GridPane.setHalignment(fxObject(), hPos);
    }

    /**
     * Retrieves the horizontal alignment of this widget within its grid cell.
     *
     * @return the horizontal alignment represented as an {@code HPos} value.
     */
    default HPos getHalignment() {
        return GridPane.getHalignment(fxObject());
    }

    /**
     * Sets the vertical alignment for this widget within its grid cell in a {@code GridPane} layout.
     *
     * @param vPos the vertical alignment to apply, specified as a {@code VPos} value
     */
    default void setValignment(VPos vPos) {
        GridPane.setValignment(fxObject(), vPos);
    }

    /**
     * Retrieves the vertical alignment of this widget within its grid cell in a {@code GridPane} layout.
     * The alignment is represented as a {@code VPos} value.
     *
     * @return the vertical alignment of the widget within its grid cell.
     */
    default VPos getValignment() {
        return GridPane.getValignment(fxObject());
    }

    /**
     * Determines the visibility status of the associated FX Gadget.
     *
     * @return true if the FX Gadget is visible; false otherwise.
     */
    default boolean getVisible() {
        return fxObject().isVisible();
    }

    /**
     * Sets the visibility of the object.
     *
     * @param visible a boolean value where true makes the object visible,
     *                and false makes it invisible.
     */
    default void setVisible(boolean visible) {
        fxObject().setVisible(visible);
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
        GridPane.setMargin(fxObject(), insets);
    }

    /**
     * Retrieves the margins as insets for the pane in the GridPane layout.
     *
     * The insets determine the amount of space to be applied around the pane.
     *
     * @return the Insets object containing the top, right, bottom, and left margins of the pane
     */
    default Insets getMargins() {
        return GridPane.getMargin(fxObject());
    }


    /**
     * Sets whether the widget should fill its cell's width within the GridPane.
     *
     * @param fillWidth a boolean value where {@code true} means the widget should
     *                  fill the width of its cell, and {@code false} means it should not.
     */
    default void setFillWidth(boolean fillWidth) {
        GridPane.setFillWidth(fxObject(), fillWidth);
    }
    /**
     * Determines whether the widget is configured to fill the available width.
     *
     * @return true if the widget is set to fill its width, otherwise false
     */
    default boolean getFillWidth() {
        Boolean fillWidth = GridPane.isFillWidth(fxObject());
        if (fillWidth == null) {
            return true;
        }
        return fillWidth;
    }

    /**
     * Sets whether the widget should fill the available vertical space in its layout container.
     *
     * @param fillHeight a boolean indicating whether the widget should fill the vertical space (true) or not (false)
     */
    default void setFillHeight(boolean fillHeight) {
        GridPane.setFillHeight(fxObject(), fillHeight);
    }
    /**
     * Determines whether the height of the target widget within the GridPane should
     * be expanded to fill its cell, based on the GridPane's isFillHeight property.
     *
     * @return true if the height of the widget is set to fill its allocated cell space,
     *         false otherwise.
     */
    default boolean getFillHeight() {
        Boolean fillHeight = GridPane.isFillHeight(fxObject());
        if (fillHeight == null) {
            return true;
        }
        return fillHeight;
    }

    /**
     * Retrieves the maxHeight property of the associated Region, if present.
     *
     * @return an Optional containing the maxHeight DoubleProperty of the Region if the fxGadget is an instance of Region;
     *         otherwise, an empty Optional
     */
    default Optional<DoubleProperty> maxHeightPropertyOptional() {
        if (fxObject() instanceof Region region) {
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
        if (fxObject() instanceof Region region) {
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
        if (fxObject() instanceof Region region) {
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
        if (fxObject() instanceof Region region) {
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
        if (fxObject() instanceof Region region) {
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
        if (fxObject() instanceof Region region) {
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
        if (fxObject() instanceof Region region) {
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
        if (fxObject() instanceof Region region) {
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
        if (fxObject() instanceof Region region) {
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
        if (fxObject() instanceof Region region) {
            return Optional.of(region.prefWidthProperty());
        }
        return Optional.empty();
    }

    /**
     * Returns a property that represents the visibility state of the Fx objects.
     *
     * @return a BooleanProperty that holds the visibility state. If true, the object is visible; otherwise, it is not.
     */
    default BooleanProperty visibleProperty() {
        return fxObject().visibleProperty();
    }


    /**
     * Sets the preferred width for the associated region of this widget if the underlying
     * FX gadget is an instance of {@code Region}. Updates the region's preferred width to the
     * specified value.
     *
     * @param prefWidth the preferred width to set for the associated region
     */
    default void setPrefWidth(double prefWidth) {
        if (fxObject() instanceof Region region) {
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
        if (fxObject() instanceof Region region) {
            return region.getPrefWidth();
        }
        return Region.USE_COMPUTED_SIZE;
    }
    /**
     * Retrieves the value associated with the "KL_KEY" preference key from the properties map
     * and casts it to a LayoutKey.
     *
     * @return the LayoutKey object associated with the "KL_KEY" preference key, or null if not present or not of type LayoutKey.
     */
    default LayoutKey.ForArea getLayoutKeyForArea() {
        return (LayoutKey.ForArea) properties().get(PreferenceKeys.LAYOUT_KEY.name());
    }
    /**
     * Sets the layout key in the properties map.
     *
     * @param layoutKeyForArea the layout key to be set
     */
    default void setLayoutKeyForArea(LayoutKey.ForArea layoutKeyForArea) {
        properties().put(PreferenceKeys.LAYOUT_KEY.name(), layoutKeyForArea);
    }
    /**
     * Retrieves the area factory value from the properties using the specified preference key.
     *
     * @return the area factory value as a String, or null if not found.
     */
    default String getAreaFactoryClassName() {
        return (String) properties().get(PreferenceKeys.AREA_FACTORY_CLASS_NAME.name());
    }
    /**
     * Sets the area factory value in the properties.
     *
     * @param areaFactoryClassName the name of the area factory class to set
     */
    default void setAreaFactoryClassName(String areaFactoryClassName) {
        properties().put(PreferenceKeys.AREA_FACTORY_CLASS_NAME.name(), areaFactoryClassName);
    }


    sealed interface Factory<FX extends Region, KL extends KlArea<FX>> extends KlView.Factory<FX, KL>
            permits KlAssociationArea.Factory, KlPropertyArea.Factory, KlSupplementalArea.Factory, KlChronologyArea.Factory, KlMultiComponentArea.Factory, KlMultiVersionArea.Factory, KlVersionArea.Factory {

        /**
         * Provides the default {@code AreaGridSettings} for the factory. The default settings
         * will include the factory's class name as the area factory class name.
         *
         * @return An {@code AreaGridSettings} object configured with the default settings
         * including the factory's class name as the area factory class name.
         */
        default AreaGridSettings defaultAreaGridSettings() {
            return AreaGridSettings.DEFAULT.withAreaFactoryClassName(this.getClass().getName());
        }

        /**
         * Create new {@code KL} object with default {@code AreaGridSettings}
         * @param preferencesFactory
         * @return a {@code KL} object.
         */
        default KL create(KlPreferencesFactory preferencesFactory) {
            return create(preferencesFactory, defaultAreaGridSettings());
        }


        /**
         * Creates a new area of type {@code KL} using the specified preferences factory and area layout.
         *
         * @param preferencesFactory the {@code KlPreferencesFactory} that provides preferences for the new area
         * @param areaGridSettings the layout information used to configure the new area
         * @return a new area of type {@code KL} configured using the provided preferences factory and layout
         */
        KL create(KlPreferencesFactory preferencesFactory, AreaGridSettings areaGridSettings);

        default KL createAndAddToParent(AreaGridSettings areaGridSettings, KlView parentArea) {
            Objects.requireNonNull(areaGridSettings, "areaLayout is null");
            Objects.requireNonNull(parentArea, "parentArea is null");

            KlPreferencesFactory preferencesFactory =
                    KlPreferencesFactory.create(parentArea.preferences(), this.getClass());

            KL klView = this.create(preferencesFactory, areaGridSettings);
            parentArea.addToParent(klView);
            return klView;
        }
    }
}
