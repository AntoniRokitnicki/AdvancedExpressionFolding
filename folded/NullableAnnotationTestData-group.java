package data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.HashMap;

/**
 * {@link com.intellij.advancedExpressionFolding.AdvancedExpressionFoldingSettings.IState#getNullable()}
 * <p>
 *  {@link com.intellij.advancedExpressionFolding.processor.NullableExt#createExpression(com.intellij.psi.PsiMethod)}
 * <p>
 * {@link com.intellij.advancedExpressionFolding.FoldingTest#testNullableAnnotationTestData()}
 */
@SuppressWarnings("ALL")
public class NullableAnnotationTestData {
    [1:"@NotNull"]
    [0:"N"]ullableAnnotationTestData[1:" "]data;
    [2:"b"]oolean ok;
    [4:"@Nullable"]
    [3:"S"]tring[4:" "]string;[0:"\n    "][0:"public NullableAnnotationTestData getData() {\n        return data;\n    }"][0:"\n    "][0:"public void setData(NullableAnnotationTestData data) {\n        this.data = data;\n    }"][2:"\n    "][2:"public boolean isOk() {\n        return ok;\n    }"][2:"\n    "][2:"public void setOk(boolean ok) {\n        this.ok = ok;\n    }"][3:"\n    "][3:"public String getString() {\n        return string;\n    }"][3:"\n    "][3:"public void setString(String string) {\n        this.string = string;\n    }"]

    [5:"@Nonnull"]
    private NullableAnnotationTestData[5:" "]data2;
    boolean ok2;
    [6:"@Nullable"]
    private String[6:" "]string2;

    public void select([7:"@Nullable"][10:"@Nullable"][7:" "][10:" "]String[7:" "][10:" "]element,
                       int i,
                       [8:"@NotNull"][11:"@NotNull"][8:" "][11:" "]Object[8:" "][11:" "]o,
                       [9:"@Nonnull"][12:"@Nonnull"][9:" "][12:" "]LocalDate[9:" "][12:" "]date
                       ) {
        new HashMap<String, String>().put("a", "b");

    }

    [13:"@NotNull"]
    public String[13:" "]getStringNotNull() {
        return string;
    }

    [14:"@Nonnull"]
    public String[14:" "]getStringNotNull2() {
        return string;
    }

    [15:"@Nullable"]
    public String[15:" "]getStringNull() {
        return string;
    }

    interface Datable {
        [16:"@Nullable"]
        public Integer[16:" "]select([17:"@Nullable"][20:"@Nullable"][17:" "][20:" "]String[17:" "][20:" "]element,
                           int i,
                           [18:"@NotNull"][21:"@NotNull"][18:" "][21:" "]Object[18:" "][21:" "]o,
                           [19:"@Nonnull"][22:"@Nonnull"][19:" "][22:" "]LocalDate[19:" "][22:" "]date
        );
    }

    public enum FieldFoldingAnnotation {
        NOT_NULL("NotNull", "NonNull"),
        NULLABLE("Nullable");

        private String[] annotations;

        FieldFoldingAnnotation(String... annotations) {

        }

        [23:"@Nonnull"]
        public static int[23:" "]select([24:"@Nullable"][27:"@Nullable"][24:" "][27:" "]String[24:" "][27:" "]element,
                                 int i,
                                 [25:"@NotNull"][28:"@NotNull"][25:" "][28:" "]Object[25:" "][28:" "]o,
                                 [26:"@Nonnull"][29:"@Nonnull"][26:" "][29:" "]LocalDate[26:" "][29:" "]date
        ) {
            return 1;
        }

    }

    public record UserDataRecord([30:"@Nonnull"][30:" "]String[30:" "]username, boolean active, [31:"@Nullable"][31:" "]String[31:" "]userIdentifier, [32:"@NotNull"][32:" "]String[32:" "]username2) {
    }

    [33:"c"]lass GetterNullable {
        NullableAnnotationTestData getterNullable;[33:"\n\n        "][34:"@Nullable"]lable\n        public NullableAnnotationTestData getGetterNullable() {\n            return getterNullable;\n        }"]      }
    }

    [35:"c"]lass SetterNullable {
        NullableAnnotationTestData setterNullable;[35:"\n\n        "][35:"public void setSetterNullable(@Nonnull NullableAnnotationTestData setterNullable) {\n            this.setterNullable = setterNullable;\n        }"]     this.setterNullable = setterNullable;
        }
    }

    public class LombokFieldLevelIntegration {
        public class HasGetter {
            [39:"@Nullable"]
            [38:"p"]rivate String[39:" "]field;
            private String bla;[38:"\n\n            "][38:"public @Nullable String getField() {\n                return field;\n            }"]
            }
        }

        public class HasSetter {
            [42:"@Nullable"]
            [41:"p"]rivate String[42:" "]field;
            private String bla;[41:"\n\n            "][41:"public void setField(@Nullable String field) {\n                this.field = field;\n            }"]) {
                this.field = field;
            }
        }

        public class HasGetterSetter {
            [46:"@Nullable"]
            [45:"p"]rivate String[46:" "]field;
            private String bla;[45:"\n\n            "][45:"public @Nullable String getField() {\n                new HashMap<String, String>().put(\"a\", \"b\");\n                return field;\n            }"]
            }[45:"\n\n            "][45:"public void setField(@Nullable String field) {\n                this.field = field;\n            }"]) {
                this.field = field;
            }
        }
    }

    public class LombokFieldLevelNotPrivateIntegration {
        public class HasGetter {
            [51:"@Nullable"]
            [50:"S"]tring[51:" "]field;
            String bla;[50:"\n\n            "][50:"public @Nullable String getField() {\n                return field;\n            }"]
            }
        }

        public class HasSetter {
            [54:"@Nullable"]
            [53:"S"]tring[54:" "]field;
            String bla;[53:"\n\n            "][53:"public void setField(@Nullable String field) {\n                this.field = field;\n            }"]) {
                this.field = field;
            }
        }

        public class HasGetterSetter {
            [58:"@Nullable"]
            [57:"S"]tring[58:" "]field;
            String bla;[57:"\n\n            "][57:"public @Nullable String getField() {\n                return field;\n            }"]
            }[57:"\n\n            "][57:"public void setField(@Nullable String field) {\n                this.field = field;\n            }"]) {
                this.field = field;
            }
        }
    }


}
