package dev.ikm.komet.layout.field;

import dev.ikm.tinkar.component.FieldDefinition;
import javafx.scene.layout.Region;

/**
 * Represents a factory interface in the Knowledge Layout framework for creating and managing
 * instances of field areas specifically designed to handle fields containing definitions
 * of data types. This interface extends {@link KlListFieldAreaFactory}, inheriting
 * its type constraints and functionality while specializing it for areas managing
 * field definitions.
 *
 * The type parameters allow defining the type of field definitions in the list and the JavaFX node type
 * that this factory produces and associates with the field areas. It supports the
 * generation of UI components tailored to handle lists of field definitions with enhanced
 * configurability and type safety.
 *
 * @param <LE> the type of field definitions managed by the field areas created by this factory
 * @param <FX> the type of JavaFX region used as the parent UI element for the field areas
 */
public interface KlListOfFieldDefinitionAreaFactory<LE extends FieldDefinition, FX extends Region>
        extends KlListFieldAreaFactory<LE, FX> {
}
