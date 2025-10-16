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

    public class SneakyThrowsExample implements Runnabl[0:"e"] {

        public String utf8ToStringMultiline(byte[] bytes) {
            [1:"try"] {
                [3:"byte[]"] bytez = System[5:".getProperty("]"sort-desc"[5:")"].[4:"getBytes()"];
                return new String(bytez, "UTF-8");
            }[1:" "][1:"catch (UnsupportedEncodingException e) {\n                throw new RuntimeException(e);\n            }"]
        }

        public int parseInteger(String value) {
            [7:"try"][7:" "][7:"{"]
            [7:"    "]return Integer.parseInt(value);[7:"\n            "][7:"}"][7:" "][7:"catch (NumberFormatException e) {\n                throw new IllegalArgumentException(e);\n            }"]
        }

        public long parseLong(String value) {
            long longValue;
            [10:"try"] {
                [15:"Function<String, Long>"] longFunction = Long::parseLong;
                longValue = longFunction.apply(value);
            }[10:" "][10:"catch (NumberFormatException e) {\n                throw new IllegalArgumentException(e);\n            }"]
            [11:"System.out."]println("longValue = [12:"\" + "]longValue[12:")"];
            return longValue;
        }

        public void runMultiline() {
            [20:"try"] {
                [22:"var"] throwable = new Throwable();
                throw throwable;
            }[20:" "][20:"catch (Throwable t) {\n                throw new IllegalStateException(t);\n            }"]
        }

        public String utf8ToString(byte[] bytes) {
            [24:"try"][24:" "][24:"{"]
            [24:"    "]return new String(System[27:".getProperty("]"sort-desc"[27:")"].[26:"getBytes()"], "UTF-8");[24:"\n            "][24:"}"][24:" "][24:"catch (UnsupportedEncodingException e) {\n                throw new RuntimeException(e);\n            }"]
        }
        public void run() {
            [29:"try"][29:" "][29:"{"]
            throw new Throwable();[29:"\n            "][29:"}"][29:" "][29:"catch (Throwable t) {\n                throw new IllegalStateException(t);\n            }"]
        }

        class Nagative {
            public String utf8ToString(byte[] bytes) {
                try {
                    return new String(bytes, "UTF-8");
                } catch [32:"("]UnsupportedEncodingException e[32:")"] {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            public void run() {
                try {
                    throw new Throwable();
                } catch [34:"("]Throwable t[34:")"] {
                    throw new RuntimeException("", t);
                }
            }

            public String utf8ToStringMultiline(byte[] bytes) {
                try {
                    [36:"String"] charsetName = "UTF-8";
                    return new String(bytes, charsetName);
                } catch [37:"("]UnsupportedEncodingException e[37:")"] {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            public void runMultiline() {
                try {
                    [40:"Throwable"] throwable = new Throwable();
                    throw throwable;
                } catch [41:"("]Throwable t[41:")"] {
                    throw new RuntimeException("", t);
                }
            }
        }
    }

}
