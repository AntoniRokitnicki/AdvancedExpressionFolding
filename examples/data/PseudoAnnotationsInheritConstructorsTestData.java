package data;

import java.io.IOException;

public class PseudoAnnotationsInheritConstructorsTestData {

    public static class BaseError extends Exception {
        public BaseError() {
        }

        public BaseError(String message) {
            super(message);
        }

        public BaseError(String message, Throwable cause) throws IOException {
            super(message, cause);
        }
    }

    public static class DerivedError extends BaseError {
        public DerivedError() {
            super();
        }

        public DerivedError(String message) {
            super(message);
        }

        public DerivedError(String message, Throwable cause) throws IOException {
            super(message, cause);
        }
    }
}
