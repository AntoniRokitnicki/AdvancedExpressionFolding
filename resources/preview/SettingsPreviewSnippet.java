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
