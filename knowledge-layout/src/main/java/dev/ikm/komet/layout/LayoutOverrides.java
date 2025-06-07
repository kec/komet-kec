package dev.ikm.komet.layout;

import dev.ikm.komet.layout.area.AreaGridSettings;
import dev.ikm.komet.preferences.KometPreferences;
import dev.ikm.komet.preferences.Preferences;
import dev.ikm.tinkar.common.binary.Decoder;
import dev.ikm.tinkar.common.binary.DecoderInput;
import dev.ikm.tinkar.common.binary.Encodable;
import dev.ikm.tinkar.common.binary.EncoderOutput;
import dev.ikm.tinkar.common.service.PluggableService;
import dev.ikm.tinkar.common.service.SaveState;
import dev.ikm.tinkar.common.util.uuid.UuidT5Generator;
import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;

import java.util.Objects;
import java.util.UUID;

/// Goal: A reproducible key and layout process for a location within a graph of KlWidgets.
///
/// Challenges:
/// 1. The scene graph may not be complete at the time the Grid Location is computed?
/// 2. We need to find—and subscribe to—parent observable properties.
/// 3. The layout must be serializable and be able to define and restore custom overrides
public class LayoutOverrides implements Encodable {

    enum Keys {
        LAYOUT_OVERRIDES;
    }

    static final ConcurrentHashMap<UUID, LayoutOverrides> overrideInstances = new ConcurrentHashMap<>();

    /**
     * TODO: This caching strategy will need to be revised for a multi-user environment to keep
     * layout specific to the user, and to handle memory better.
     */
    private final ConcurrentHashMap<LayoutKey.Property, AreaGridSettings> layoutOverrides = new ConcurrentHashMap<>();

    private final LayoutKey.ForArea rootLayoutKey;

    private final String rootLayoutClassName;

    public static LayoutOverrides make(UUID overrideMapUuid, Class<? extends KnowledgeLayout> rootLayoutClass) {
        if (!overrideInstances.containsKey(overrideMapUuid)) {
            overrideInstances.putIfAbsent(overrideMapUuid,
                    new LayoutOverrides(overrideMapUuid, rootLayoutClass));
        }
        return overrideInstances.get(overrideMapUuid);
    }

    public void save() {
        KometPreferences preferences = Preferences.get().getConfigurationPreferences().node(rootLayoutClassName).node(rootLayoutKey.id().toString());
        preferences.putObject(Keys.LAYOUT_OVERRIDES, this);
    }

    public static LayoutOverrides restore(UUID overrideMapUuid, Class<? extends KnowledgeLayout> rootLayoutClass) {
        KometPreferences preferences = Preferences.get().getConfigurationPreferences().node(rootLayoutClass).node(overrideMapUuid.toString());
        return preferences.getObject(Keys.LAYOUT_OVERRIDES, new LayoutOverrides(overrideMapUuid, rootLayoutClass));
    }

    private LayoutOverrides(UUID overrideMapUuid, Class<? extends KnowledgeLayout> rootLayoutClass) {
        this(overrideMapUuid, rootLayoutClass.getName());
    }

    private LayoutOverrides(UUID overrideMapUuid, String rootLayoutClassName) {
        this.rootLayoutKey = LayoutKey.makeTopArea(overrideMapUuid);
        this.rootLayoutClassName = rootLayoutClassName;
        overrideInstances.putIfAbsent(overrideMapUuid, this);
    }

    private LayoutOverrides(DecoderInput in) {
        this.rootLayoutKey = in.decode();
        this.rootLayoutClassName = in.readString();
        int size = in.readVarInt();
        for (int i = 0; i < size; i++) {
            layoutOverrides.put(in.decode(), in.decode());
        }
    }

    public AreaGridSettings getOrDefault(AreaGridSettings defaultLayout) {
        Objects.nonNull(defaultLayout);
        if (layoutOverrides.containsKey(defaultLayout.layoutKeyForArea())) {
            return layoutOverrides.get(defaultLayout.layoutKeyForArea());
        }
        return defaultLayout;
    }

    @Override
    public void encode(EncoderOutput out) {
        out.write(rootLayoutKey);
        out.writeString(rootLayoutClassName);
        out.writeVarInt(layoutOverrides.size());
        layoutOverrides.forEach((key, value) -> {
            out.write(key);
            out.write(value);
        });
    }

    @Decoder
    public static LayoutOverrides decode(DecoderInput in) {
        return switch (Encodable.checkVersion(in)) {
            // if special handling for particular versions, add case condition.
            default -> new LayoutOverrides(in);
        };
    }

    public static class Saver implements SaveState {
        @Override
        public void save() {
            overrideInstances.forEach((layoutClass, overrides) -> overrides.save());
        }
    }
}
