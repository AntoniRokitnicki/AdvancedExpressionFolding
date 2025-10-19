import java.util.logging.Logger;

public class PersonaLoggerExample {
    private static final Logger LOG = Logger.getLogger(PersonaLoggerExample.class.getName());

    void log(String userId, String message) {
        LOG.info(() -> "user=" + userId + " message=" + message);
        LOG.fine(() -> "payload=" + message.strip());
    }
}
