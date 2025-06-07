package dev.ikm.komet.layout.area;

import dev.ikm.komet.layout.KlArea;
import dev.ikm.komet.layout.KlView;
import dev.ikm.komet.layout.LayoutKey;
import dev.ikm.tinkar.common.binary.*;
import dev.ikm.tinkar.common.service.PluggableService;
import io.soabase.recordbuilder.core.RecordBuilder;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.lang.reflect.Constructor;

/**
 * Represents a layout record that defines grid-based layout constraints and properties.
 * This includes position-related settings such as row and column indices, span values,
 * alignment preferences, sizing constraints, and fill behavior.
 *
 * This class implements {@code Encodable}, enabling its serialization and deserialization
 * through custom encoding and decoding methods.
 */
@RecordBuilder
public record AreaGridSettings(
        int columnIndex,
        int rowIndex,
        int columnSpan,
        int rowSpan,
        Priority hGrow,
        Priority vGrow,
        HPos hAlignment,
        VPos vAlignment,
        Insets margin,
        Double maxHeight,
        Double maxWidth,
        Double preferredHeight,
        Double preferredWidth,
        boolean fillHeight,
        boolean fillWidth,
        boolean visible,
        LayoutKey.ForArea layoutKeyForArea,
        String areaFactoryClassName) implements AreaGridSettingsBuilder.With, Encodable {

    private static final int marshalVersion = 1;

    public static final AreaGridSettings DEFAULT = new AreaGridSettings();

    /**
     * Constructs an instance of {@code AreaGridSettings} with default column and row indices
     * set to 0, as well as default configurations for other properties.
     * <p>
     * This constructor initializes a {@code AreaGridSettings} with:
     * <p> - Column index: 0
     * <p> - Row index: 0
     * <p> - Column span: 1
     * <p> - Row span: 1
     * <p> - Horizontal grow priority: {@code Priority.SOMETIMES}
     * <p> - Vertical grow priority: {@code Priority.NEVER}
     * <p> - Horizontal alignment: {@code HPos.LEFT}
     * <p> - Vertical alignment: {@code VPos.TOP}
     * <p> - Margins: {@code new Insets(0)}
     * <p> - Maximum height: {@code Double.MAX_VALUE}
     * <p> - Maximum width: {@code Double.MAX_VALUE}
     * <p> - Preferred height: {@code Region.USE_COMPUTED_SIZE}
     * <p> - Preferred width: {@code Region.USE_COMPUTED_SIZE}
     * <p> - Fill height: true
     * <p> - Fill width: true
     */
    private AreaGridSettings() {
        this(0, 0, LayoutKey.EMPTY, KlArea.Factory.class.getName());
    }

    /**
     * Constructs a {@code AreaGridSettings} with specified column and row indices.
     * Default values for other properties are applied, including column span, row span,
     * alignment, and grow priorities.
     *
     * @param columnIndex      the index of the column where the layout begins
     * @param rowIndex         the index of the row where the layout begins
     * @param layoutKeyForArea
     */
    public AreaGridSettings(int columnIndex, int rowIndex, LayoutKey.ForArea layoutKeyForArea,
                            String areaFactoryClassName) {
        this(columnIndex, rowIndex, 1, 1,
                Priority.SOMETIMES, Priority.NEVER, HPos.LEFT, VPos.TOP,
                new Insets(0),
                Double.MAX_VALUE, Double.MAX_VALUE,
                Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE,
                true, true, true, layoutKeyForArea, areaFactoryClassName);
    }

    /**
     * Constructs an instance of {@code AreaGridSettings} based on the provided {@code GridIncrementer}.
     * The initial column and row indices in the layout are derived from the current values
     * of the {@code column} and {@code row} fields within the {@code GridIncrementer}.
     * Default values are used for other layout properties, such as span, alignment,
     * grow priorities, margins, and size constraints.
     *  <p>
     * The caller is responsible for calling the
     *
     * @param incrementer the {@code GridIncrementer} instance used to determine the
     *                    initial column and row indices of the layout. The {@code GridIncrementer}'s
     *                    current column and row values are applied during initialization.
     */
    public AreaGridSettings(GridStepper incrementer, LayoutKey.ForArea layoutKeyForArea, String areaFactoryClassName) {
        this(incrementer.column(), incrementer.row(), 1, 1,
                Priority.SOMETIMES, Priority.NEVER, HPos.LEFT, VPos.TOP,
                new Insets(0),
                Double.MAX_VALUE, Double.MAX_VALUE,
                Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE,
                true, true, true,
                layoutKeyForArea, areaFactoryClassName);
    }

    public KlArea makeAndAddToParent(KlView parentView) {
        KlArea.Factory factory = makeAreaFactory();
        return factory.createAndAddToParent(this, parentView);
    }

    public <F extends KlArea.Factory> F makeAreaFactory() {
        try {
            Class factoryClass = PluggableService.forName(areaFactoryClassName());
            Constructor constructor = factoryClass.getConstructor();
            return (F) constructor.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public AreaGridSettings with(int columnIndex, int rowIndex, LayoutKey.ForArea layoutKeyForArea, String areaFactoryClassName) {
        return new AreaGridSettings(columnIndex, rowIndex, columnSpan, rowSpan,
                hGrow, vGrow, hAlignment, vAlignment, margin, maxHeight, maxWidth,
                preferredHeight, preferredWidth, fillHeight, fillWidth, visible,
                layoutKeyForArea, areaFactoryClassName);
    }

    public AreaGridSettings with(Class factoryClass) {
        return this.withAreaFactoryClassName(factoryClass.getName());
    }



    /**
     * Encodes the properties of the layout record into the given {@code EncoderOutput}.
     *
     * @param out the {@code EncoderOutput} to which the layout record properties are written
     */
    @Override
    @Encoder
    public void encode(EncoderOutput out) {
        out.writeInt(marshalVersion);
        out.writeInt(columnIndex);
        out.writeInt(rowIndex);
        out.writeInt(columnSpan);
        out.writeInt(rowSpan);
        out.writeString(hGrow.name());
        out.writeString(vGrow.name());
        out.writeString(hAlignment.name());
        out.writeString(vAlignment.name());
        out.writeDouble(margin.getTop());
        out.writeDouble(margin.getRight());
        out.writeDouble(margin.getBottom());
        out.writeDouble(margin.getLeft());
        out.writeDouble(maxHeight);
        out.writeDouble(maxWidth);
        out.writeDouble(preferredHeight);
        out.writeDouble(preferredWidth);
        out.writeBoolean(fillHeight);
        out.writeBoolean(fillWidth);
        out.writeBoolean(visible);
        out.write(layoutKeyForArea);
        out.writeString(areaFactoryClassName);
    }

    /**
     * Decodes a {@code DecoderInput} to reconstruct a {@code LayoutRecord} instance.
     * The method reads the object version and deserializes the contained properties
     * if the version matches the supported marshal version.
     *
     * @param in the {@code DecoderInput} from which the layout record properties
     *           are read and reconstructed
     * @return a {@code LayoutRecord} instance containing the deserialized properties
     * @throws UnsupportedOperationException if the object version is unsupported
     */
    @Decoder
    public static AreaGridSettings decode(DecoderInput in) {
        int objectMarshalVersion = in.readInt();
        if (objectMarshalVersion == marshalVersion) {
            return new AreaGridSettings(
                    in.readInt(), // int columnIndex,
                    in.readInt(), // int rowIndex,
                    in.readInt(), // int columnSpan,
                    in.readInt(), // int rowSpan,
                    Priority.valueOf(in.readString()), // Priority hGrow,
                    Priority.valueOf(in.readString()), // Priority vGrow,
                    HPos.valueOf(in.readString()), // HPos hAlignment,
                    VPos.valueOf(in.readString()), // VPos vAlignment,
                    new Insets(in.readDouble(), // Insets margin top,
                            in.readDouble(), // Insets margin right,
                            in.readDouble(), // Insets margin bottom,
                            in.readDouble() // Insets margin left,
                    ),
                    in.readDouble(), // Double maxHeight,
                    in.readDouble(), // Double maxWidth,
                    in.readDouble(), // Double preferredHeight,
                    in.readDouble(), // Double preferredWidth
                    in.readBoolean(), // boolean fillHeight
                    in.readBoolean(), // boolean fillWidth
                    in.readBoolean(),
                    LayoutKey.LayoutKeyRecord.decode(in),
                    in.readString()
             );
        } else {
            throw new UnsupportedOperationException("Unsupported version: " + objectMarshalVersion);
        }
    }
}
