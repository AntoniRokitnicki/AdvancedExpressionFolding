# Settings Preview Snippet

The settings dialog preview is rendered from [`resources/preview/SettingsPreviewSnippet.java`](../resources/preview/SettingsPreviewSnippet.java).
Update both the Java snippet and this document whenever a new folding mode should be showcased in the preview so the UI stays representative of the current feature set.

## Covered folding examples

* **Getter/setter properties:** `getName()` and `setName(String)` fold into a Kotlin-style property when the option is enabled.
* **Collection access and concatenation:** `names.get(index)` and the `name += ...` loop exercise collection index folding and concatenation collapsing.
* **Optional/null-safe handling:** The `Optional` chain in `synchronize` demonstrates the optional folding modes.
* **Date literals:** `LocalDate.of(...)` highlights literal folding for date construction.
* **Logging:** `logger.info(...)` participates in log folding.
* **Stream pipelines:** `names.stream()...` covers stream and lambda folding.

Keep the snippet compact so all folds are visible without scrolling, and prefer constructs that are deterministic and resilient to PSI evolution.

```java
package preview;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

class SettingsPreviewSnippet {
    private final Logger logger = Logger.getLogger(SettingsPreviewSnippet.class.getName());
    private final List<String> names = new ArrayList<>();
    private String name;

    SettingsPreviewSnippet(String initialName) {
        this.name = initialName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    void synchronize(LocalDate fallbackDate) {
        if (name == null) {
            name = Optional.ofNullable(fallbackDate)
                .map(LocalDate::toString)
                .orElse(LocalDate.of(2024, 1, 20).toString());
        }
        for (int index = 0; index < names.size(); index++) {
            name += names.get(index);
        }
        logger.info("name=" + name);
    }

    void updateFirst(String replacement) {
        if (names.isEmpty()) {
            return;
        }
        names.set(0, replacement);
    }

    Optional<String> firstNonEmpty() {
        return names.stream()
            .filter(item -> item != null && !item.isEmpty())
            .findFirst();
    }
}
```
