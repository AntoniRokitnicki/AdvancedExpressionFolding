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
            [0:"try"]{[0:"try"]} {
                byte[] bytez = System[0:".getProperty("]"sort-desc"[0:")"].getBytes();
                return new String(bytez, "UTF-8");
            }[0:" "]{[0:" "]}[0:"catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }"]{[0:"catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }"]}
        }

        public int parseInteger(String value) {
            [0:"try"]{[0:"try"]}[0:" "]{[0:" "]}[0:"{"]{[0:"{"]}
            [0:"    "]{[0:"    "]}return Integer.parseInt(value);[0:"
            "]{[0:"
            "]{[0:"
            "]{[0:"
            "]}}}[0:"}"]{[0:"}"]}[0:" "]{[0:" "]}[0:"catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }"]{[0:"catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }"]}
        }

        public long parseLong(String value) {
            long longValue;
            [0:"try"]{[0:"try"]} {
                Function<String, Long> longFunction = Long::parseLong;
                longValue = longFunction.apply(value);
            }[0:" "]{[0:" "]}[0:"catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }"]{[0:"catch (NumberFormatException e) {
                throw new IllegalArgumentException(e);
            }"]}
            System.out.println("longValue = " + longValue);
            return longValue;
        }

        public void runMultiline() {
            [0:"try"]{[0:"try"]} {
                var throwable = new Throwable();
                throw throwable;
            }[0:" "]{[0:" "]}[0:"catch (Throwable t) {
                throw new IllegalStateException(t);
            }"]{[0:"catch (Throwable t) {
                throw new IllegalStateException(t);
            }"]}
        }

        public String utf8ToString(byte[] bytes) {
            [0:"try"]{[0:"try"]}[0:" "]{[0:" "]}[0:"{"]{[0:"{"]}
            [0:"    "]{[0:"    "]}return new String(System[0:".getProperty("]"sort-desc"[0:")"].getBytes(), "UTF-8");[0:"
            "]{[0:"
            "]{[0:"
            "]{[0:"
            "]}}}[0:"}"]{[0:"}"]}[0:" "]{[0:" "]}[0:"catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }"]{[0:"catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }"]}
        }
        public void run() {
            [0:"try"]{[0:"try"]}[0:" "]{[0:" "]}[0:"{"]{[0:"{"]}
            throw new Throwable();[0:"
            "]{[0:"
            "]{[0:"
            "]{[0:"
            "]}}}[0:"}"]{[0:"}"]}[0:" "]{[0:" "]}[0:"catch (Throwable t) {
                throw new IllegalStateException(t);
            }"]{[0:"catch (Throwable t) {
                throw new IllegalStateException(t);
            }"]}
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

}
