package data;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class KotlinPreconditionsTestData {

    private final String name;
    private boolean connected;

    public KotlinPreconditionsTestData(String name) {
        this.name = Objects.requireNonNull(name, "name");
    }

    public void ship(String order, String customer, int quantity) {
        if (order == null) {
            throw new IllegalArgumentException("order");
        }
        Objects.requireNonNull(customer, "customer");
        requireNonNull(name);
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be positive: " + quantity);
        if (!(quantity < 100)) {
            throw new IllegalArgumentException();
        }
    }

    public void send(String message) {
        if (!connected) {
            throw new IllegalStateException("Not connected");
        }
        if (message != null) {
            throw new IllegalStateException("Already sent " + message);
        }
    }

    public String describe(int state) {
        switch (state) {
            case 0:
                return "new";
            case 1:
                return "done";
            default:
                throw new IllegalStateException("Unknown state " + state);
        }
    }

    public void notFolded(String order, Exception cause) {
        if (order == null) {
            System.out.println("missing order");
            throw new IllegalArgumentException("order");
        }
        if (order.isEmpty()) {
            throw new UnsupportedOperationException("order");
        }
        if (order.isBlank()) {
            throw new IllegalArgumentException("order", cause);
        }
        if (connected) {
            throw new IllegalStateException("connected");
        } else {
            connected = true;
        }
        Objects.requireNonNull(order, () -> "order");
    }
}
