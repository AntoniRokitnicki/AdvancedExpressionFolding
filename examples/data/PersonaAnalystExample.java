import java.util.Optional;

public class PersonaAnalystExample {
    String normalize(Optional<String> source) {
        return source
            .map(String::trim)
            .filter(s -> !s.isEmpty())
            .orElse("<missing>");
    }
}
