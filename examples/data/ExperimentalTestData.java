package data;

import java.io.UnsupportedEncodingException;

/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getExperimental()}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testExperimentalTestData()}
 */
@SuppressWarnings("ALL")
public class ExperimentalTestData {

    public class SneakyThrowsExample implements Runnable {
        public String utf8ToString(byte[] bytes) {
            try {
                return new String(bytes, "UTF-8");
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
        }
    }



}
