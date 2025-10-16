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
            [0:"try"] {
                byte[] bytez = System[2:".getProperty("]"sort-desc"[2:")"].getBytes();
                return new String(bytez, "UTF-8");
            }[0:" "][0:"catch (UnsupportedEncodingException e) {\n                throw new RuntimeException(e);\n            }"]
        }

        public int parseInteger(String value) {
            [3:"try"][3:" "][3:"{"]
            [3:"    "]return Integer.parseInt(value);[3:"\n            "][3:"}"][3:" "][3:"catch (NumberFormatException e) {\n                throw new IllegalArgumentException(e);\n            }"]
        }

        public long parseLong(String value) {
            long longValue;
            [5:"try"] {
                Function<String, Long> longFunction = Long::parseLong;
                longValue = longFunction.apply(value);
            }[5:" "][5:"catch (NumberFormatException e) {\n                throw new IllegalArgumentException(e);\n            }"]
            System.out.println("longValue = " + longValue);
            return longValue;
        }

        public void runMultiline() {
            [7:"try"] {
                var throwable = new Throwable();
                throw throwable;
            }[7:" "][7:"catch (Throwable t) {\n                throw new IllegalStateException(t);\n            }"]
        }

        public String utf8ToString(byte[] bytes) {
            [9:"try"][9:" "][9:"{"]
            [9:"    "]return new String(System[11:".getProperty("]"sort-desc"[11:")"].getBytes(), "UTF-8");[9:"\n            "][9:"}"][9:" "][9:"catch (UnsupportedEncodingException e) {\n                throw new RuntimeException(e);\n            }"]
        }
        public void run() {
            [12:"try"][12:" "][12:"{"]
            throw new Throwable();[12:"\n            "][12:"}"][12:" "][12:"catch (Throwable t) {\n                throw new IllegalStateException(t);\n            }"]
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
