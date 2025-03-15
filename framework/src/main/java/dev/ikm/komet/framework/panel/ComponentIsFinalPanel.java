/*
 * Copyright © 2015 Integrated Knowledge Management (support@ikm.dev)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.ikm.komet.framework.panel;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableSet;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import dev.ikm.komet.framework.observable.*;
import dev.ikm.komet.framework.panel.concept.ConceptVersionPanel;
import dev.ikm.komet.framework.panel.pattern.PatternVersionPanel;
import dev.ikm.komet.framework.panel.semantic.SemanticVersionPanel;
import dev.ikm.komet.framework.view.ViewProperties;
import dev.ikm.tinkar.common.service.TinkExecutor;
import dev.ikm.tinkar.coordinate.stamp.calculator.Latest;
import dev.ikm.tinkar.entity.EntityVersion;
import dev.ikm.tinkar.terms.EntityFacade;

import java.util.Optional;

import static dev.ikm.komet.framework.StyleClasses.COMPONENT_COLLAPSIBLE_PANEL;

/**
 * @param <ES>
 * @param <OE>
 * @param <OV>
 * @param <EV>
 */
public class ComponentIsFinalPanel<ES extends ObservableEntitySnapshot<OE, OV>,
        OE extends ObservableEntity<OV>,
        OV extends ObservableVersion<EV>,
        EV extends EntityVersion> extends ComponentPanelAbstract {
    protected final TitledPane collapsiblePane = new TitledPane("Component", componentDetailPane);
    private final ES component;

    {
        collapsiblePane.getStyleClass().add(COMPONENT_COLLAPSIBLE_PANEL.toString());
    }

    public ComponentIsFinalPanel(ES component, ViewProperties viewProperties,
                                 SimpleObjectProperty<EntityFacade> topEnclosingComponentProperty,
                                 ObservableSet<Integer> referencedNids) {
        super(viewProperties, referencedNids);
        if (component == null) {
            throw new NullPointerException();
        }
        this.component = component;
        this.collapsiblePane.setContentDisplay(ContentDisplay.LEFT);
        Platform.runLater(() -> referencedNids.add(component.nid()));
        // TODO finish good identicon graphic.
        // this.collapsiblePane.setGraphic(Identicon.generateIdenticon(component.publicId(), 24, 24));
        TinkExecutor.threadPool().execute(() -> {
            Latest<OV> latestComponent = component.getLatestVersion();
            latestComponent.ifPresent(latestVersion -> {
                Platform.runLater(() -> addVersionPanel(latestVersion, true));
            });
            for (OV contradictedVersion : latestComponent.contradictions()) {
                Platform.runLater(() -> addVersionPanel(contradictedVersion, true));
            }
            for (OV historicVersion : component.getHistoricVersions()) {
                if (historicVersion != null) {
                    Platform.runLater(() -> addVersionPanel(historicVersion, false));
                } else {
                    // TODO this should never happen.
                    System.out.println("TODO this should never happen. 21");
                }
            }
            Platform.runLater(() -> addSemanticReferences(component, topEnclosingComponentProperty));
        });
    }

    private void addVersionPanel(OV version, boolean expanded) {
        ComponentVersionIsFinalPanel<OV> versionPanel = makeVersionPanel(version);
        BorderPane.setAlignment(versionPanel.versionDetailsPane, Pos.TOP_LEFT);
        versionPanel.collapsiblePane.setExpanded(expanded);
        Platform.runLater(() -> ComponentIsFinalPanel.this.componentPanelBox.getChildren().add(versionPanel.getVersionDetailsPane()));
    }

    private ComponentVersionIsFinalPanel makeVersionPanel(OV version) {
        if (version instanceof ObservableSemanticVersion semanticVersion) {
            return new SemanticVersionPanel(semanticVersion, viewProperties);
        } else if (version instanceof ObservableConceptVersion conceptVersion) {
            return new ConceptVersionPanel(conceptVersion, viewProperties);
        } else if (version instanceof ObservablePatternVersion patternVersion) {
            return new PatternVersionPanel(patternVersion, viewProperties);
        }
        throw new UnsupportedOperationException("Can't handle version type: " + version.toString());
    }

    @Override
    public final Optional<ES> getComponent() {
        return Optional.of(component);
    }

    public Node getComponentDetailPane() {
        return collapsiblePane;
    }

}
