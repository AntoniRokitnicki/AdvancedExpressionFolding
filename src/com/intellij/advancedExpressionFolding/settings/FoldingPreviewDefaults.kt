package com.intellij.advancedExpressionFolding.settings

object FoldingPreviewDefaults {
    const val FILE_NAME = "PreviewSnippet.java"

    val DEFAULT_SNIPPET: String = """
        import java.util.Optional;

        public class PreviewSample {
            private String name;

            public void demonstrate(String input) {
                System.out.println("Hello " + input);
            }

            public void printName() {
                System.out.println(name);
            }
        }
    """.trimIndent()
}
