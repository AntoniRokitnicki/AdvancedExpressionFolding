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
            [1:"try"][2:"try"] {
                [3:"byte[]"] bytez = System[5:".getProperty("]"sort-desc"[5:")"].[4:"getBytes()"];
                return new String(bytez, "UTF-8");
            }[1:" "][2:" "][1:"catch (UnsupportedEncodingException e) {\n                throw new RuntimeException(e);\n            }"][2:"catch (UnsupportedEncodingException e) {\n                throw new RuntimeException(e);\n            }"]           }
        }

        public int parseInteger(String value) {
            [7:"try"][8:"try"][7:" "][8:" "][7:"{"][8:"{"]
            [7:"    "][8:"    "]return Integer.parseInt(value);[7:"\n            "][7:"\n            "][8:"\n            "][8:"\n            "][7:"}"][8:"}"][7:" "][8:" "][7:"catch (NumberFormatException e) {\n                throw new IllegalArgumentException(e);\n            }"][8:"catch (NumberFormatException e) {\n                throw new IllegalArgumentException(e);\n            }"]           }
        }

        public long parseLong(String value) {
            long longValue;
            [10:"try"][14:"try"] {
                [15:"Function<String, Long>"] longFunction = Long::parseLong;
                longValue = longFunction.apply(value);
            }[10:" "][14:" "][10:"catch (NumberFormatException e) {\n                throw new IllegalArgumentException(e);\n            }"][14:"catch (NumberFormatException e) {\n                throw new IllegalArgumentException(e);\n            }"]
            }
            [11:"System.out."][17:"System.out."]println("longValue = [12:"\" + "][12:"\" + "][13:"\" + "][13:"\" + "][18:"\" + "][18:"\" + "][19:"\" + "][19:"\" + "]longValue[12:")"][13:")"][18:")"][19:")"];
            return longValue;
        }

        public void runMultiline() {
            [20:"try"][21:"try"] {
                [22:"var"] throwable = new Throwable();
                throw throwable;
            }[20:" "][21:" "][20:"catch (Throwable t) {\n                throw new IllegalStateException(t);\n            }"][21:"catch (Throwable t) {\n                throw new IllegalStateException(t);\n            }"]
            }
        }

        public String utf8ToString(byte[] bytes) {
            [24:"try"][25:"try"][24:" "][25:" "][24:"{"][25:"{"]
            [24:"    "][25:"    "]return new String(System[27:".getProperty("]"sort-desc"[27:")"].[26:"getBytes()"], "UTF-8");[24:"\n            "][24:"\n            "][25:"\n            "][25:"\n            "][24:"}"][25:"}"][24:" "][25:" "][24:"catch (UnsupportedEncodingException e) {\n                throw new RuntimeException(e);\n            }"][25:"catch (UnsupportedEncodingException e) {\n                throw new RuntimeException(e);\n            }"]
            }
        }
        public void run() {
            [29:"try"][30:"try"][29:" "][30:" "][29:"{"][30:"{"]
            throw new Throwable();[29:"\n            "][29:"\n            "][30:"\n            "][30:"\n            "][29:"}"][30:"}"][29:" "][30:" "][29:"catch (Throwable t) {\n                throw new IllegalStateException(t);\n            }"][30:"catch (Throwable t) {\n                throw new IllegalStateException(t);\n            }"]
            }
        }

        class Nagative {
            public String utf8ToString(byte[] bytes) {
                try {
                    return new String(bytes, "UTF-8");
                } catch [32:"("][33:"("]UnsupportedEncodingException e[32:")"][33:")"] {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            public void run() {
                try {
                    throw new Throwable();
                } catch [34:"("][35:"("]Throwable t[34:")"][35:")"] {
                    throw new RuntimeException("", t);
                }
            }

            public String utf8ToStringMultiline(byte[] bytes) {
                try {
                    [36:"String"][38:"String"] charsetName = "UTF-8";
                    return new String(bytes, charsetName);
                } catch [37:"("][39:"("]UnsupportedEncodingException e[37:")"][39:")"] {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            public void runMultiline() {
                try {
                    [40:"Throwable"][42:"Throwable"] throwable = new Throwable();
                    throw throwable;
                } catch [41:"("][43:"("]Throwable t[41:")"][43:")"] {
                    throw new RuntimeException("", t);
                }
            }
        }
    }

}
