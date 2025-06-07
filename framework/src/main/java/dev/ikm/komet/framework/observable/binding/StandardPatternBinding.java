package dev.ikm.komet.framework.observable.binding;

import dev.ikm.tinkar.terms.EntityProxy;

import java.util.UUID;

/**
 * The StandardPatternBinding is an example of the binding to generate for each pattern.
 * Make the name of the interface based on the meaning of the pattern. Then create a name for each
 * field based on the meaning of the field, and a name for each version field based on the meaning of the version field.
 */
public interface StandardPatternBinding {
    static EntityProxy.Pattern pattern() {
        return EntityProxy.Pattern.make("Component field pattern",
                UUID.fromString("e5d91cfb-ce2c-49e2-b522-0a3f285f1c53"));
    }

    static int publicIdFieldIndex() {
        return 0;
    }

    static int versionsFieldIndex() {
        return 1;
    }

    interface Version {
        // Component version field pattern: [95ebfa49-3ca7-4a86-ab91-bcc2081ab265]
        static EntityProxy.Pattern pattern() {
            return EntityProxy.Pattern.make("Component version field pattern",
                    UUID.fromString("95ebfa49-3ca7-4a86-ab91-bcc2081ab265"));
        }

        static int stampFieldIndex() {
            return 0;
        }

        static int patternSpecificFieldIndex() {
            return 1;
        }

        static int patternSpecificFieldIndex2() {
            return 2;
        }

    }

}
