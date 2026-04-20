package data;

import java.io.UnsupportedEncodingException;
import java.util.function.Function;

/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getExperimental()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testExperimentalTestData()}
 */
@SuppressWarnings("ALL")
public class ExperimentalTestData {

    public class SneakyThrowsExample implements Runnable {

        public String utf8ToStringMultiline(byte[] bytes) {
            try {
                byte[] bytez = System.getProperty("sort-desc").getBytes();
                return new String(bytez, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

        public int parseInteger(String value) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }
        }

        public long parseLong(String value) {
            long longValue;
            try {
                Function<String, Long> longFunction = Long::parseLong;
                longValue = longFunction.apply(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }
            System.out.println("longValue = " + longValue);
            return longValue;
        }

        public void runMultiline() {
            try {
                var throwable = new Throwable();
                throw throwable;
            } catch (Throwable t) {
                throw new IllegalStateException(t);
            }
        }

        public String utf8ToString(byte[] bytes) {
            try {
                return new String(System.getProperty("sort-desc").getBytes(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        public void run() {
            try {
            throw new Throwable();
            } catch (Throwable t) {
                throw new IllegalStateException(t);
            }
        }

        class Nagative {
            public String utf8ToString(byte[] bytes) {
                try {
                    return new String(bytes, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            public void run() {
                try {
                    throw new Throwable();
                } catch (Throwable t) {
                    throw new RuntimeException("", t);
                }
            }

            public String utf8ToStringMultiline(byte[] bytes) {
                try {
                    String charsetName = "UTF-8";
                    return new String(bytes, charsetName);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            public void runMultiline() {
                try {
                    Throwable throwable = new Throwable();
                    throw throwable;
                } catch (Throwable t) {
                    throw new RuntimeException("", t);
                }
            }
        }
    }

    static class NamelessAccessorExample {

        private String state;

        void set(String value) {
            this.state = value;
        }

        String get() {
            return state;
        }

        void demo() {
            NamelessAccessorExample example = new NamelessAccessorExample();
            example.set("ok");
            String current = example.get();
            System.out.println(example.get().isEmpty());
            example.set(example.get());
            String duplicate = example.get() + example.get();
        }
    }

    static class FieldNameConstantsExample {

        private final String iAmAField = "";
        private final int andSoAmI = 0;
        private final String userIdentifier = "";

        public static final class Fields {
            public static final String iAmAField = "iAmAField";
            public static final String andSoAmI = "andSoAmI";
            public static final String USER_IDENTIFIER = "userIdentifier";
        }
    }

    static class FieldNameConstantsEnumExample {

        private final String iAmAField = "";
        private final int andSoAmI = 0;
        private final String userIdentifier = "";

        public enum Fields {
            iAmAField,
            andSoAmI,
            USER_IDENTIFIER
        }
    }

}
