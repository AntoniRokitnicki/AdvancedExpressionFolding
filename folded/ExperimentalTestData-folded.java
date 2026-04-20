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
            @SneakyThrows {
                byte[] bytez = System["sort-desc"].bytes;
                return new String(bytez, "UTF-8");
            }
        }

        public int parseInteger(String value) {
            @SneakyThrows(IllegalArgumentException)
            return Integer.parseInt(value);
        }

        public long parseLong(String value) {
            long longValue;
            @SneakyThrows(IllegalArgumentException) {
                Function<String, Long> longFunction = Long::parseLong;
                longValue = longFunction.apply(value);
            }
            System.out.println("longValue = " + longValue);
            return longValue;
        }

        public void runMultiline() {
            @SneakyThrows {
                var throwable = new Throwable();
                throw throwable;
            }
        }

        public String utf8ToString(byte[] bytes) {
            @SneakyThrows
            return new String(System["sort-desc"].bytes, "UTF-8");
        }
        public void run() {
            @SneakyThrows
            throw new Throwable();
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
            example.!! = "ok";
            String current = example.!!;
            System.out.println(example.!!.empty);
            example.!! = example.!!;
            String duplicate = example.!! + example.!!;
        }
    }

    @FieldNameConstants static class FieldNameConstantsExample {

        private final String iAmAField;
        private final int andSoAmI;
        private final String userIdentifier;
    }

    @FieldNameConstants(asEnum = true) static class FieldNameConstantsEnumExample {

        private final String iAmAField;
        private final int andSoAmI;
        private final String userIdentifier;
    }

}
