package data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Optional;

@SuppressWarnings("ALL")
@Getter @Setter @Serial public class LombokTestData {

    LombokTestData data;
    boolean ok;
    String string;

    public Optional<LombokTestData> asOptional() {
        return Optional.ofNullable(data);
    }

    @Getter public class LombokGetters {
        LombokTestData data;
        boolean ok;

        @Getterˣ public class LombokGettersPartial {
            LombokTestData data;
            boolean ok;
        }
    }

    @Getter @Setter public class LombokSetters {
        LombokTestData data;
        boolean ok;

        @Setterˣ public class LombokSettersPartial {
            LombokTestData data;
            boolean ok;
        }

        @Setter public class LombokSettersFinalField {
            LombokTestData data;
            final boolean ok = true;
        }
    }

    @ToString public class ToStringFull {
        LombokTestData data;
        boolean ok;

        @ToStringˣ public class ToStringPartial {
            LombokTestData data;
            boolean ok;
        }

        @ToStringˣ public class ToStringPartial2 {
            LombokTestData data;
            boolean ok;
            String string;
        }
    }

    @EqualsAndHashCode public class EqualsAndHashCodeFull {
        LombokTestData data;
        boolean ok;

        @EqualsAndHashCodeˣ public class EqualsAndHashCodePartial {
            LombokTestData data;
            boolean ok;
        }

        @EqualsAndHashCodeˣ public class EqualsAndHashCodePartialTwo {
            LombokTestData data;
            boolean ok;
            String string;
        }
    }

    @Equals public class EqualsFull {
        LombokTestData data;
        boolean ok;

        @Equalsˣ public class EqualsPartial {
            LombokTestData data;
            boolean ok;
        }

        @Equalsˣ public class EqualsPartialTwo {
            LombokTestData data;
            boolean ok;
            String string;
        }
    }

    @HashCode public class HashCodeFull {
        LombokTestData data;
        boolean ok;

        @HashCodeˣ public class HashCodePartial {
            LombokTestData data;
            boolean ok;
        }

        @HashCodeˣ public class HashCodePartialTwo {
            LombokTestData data;
            boolean ok;
            String string;
        }
    }

    @Data public class DataFull {
        LombokTestData data;
        boolean ok;

        @Data public class DataWithoutToString {
            LombokTestData data;
            boolean ok;

        }

        @Dataˣ public class DataWithPartialGetters {
            LombokTestData data;
            boolean ok;
        }

        @Dataˣ public class DataWithPartialSetters {
            LombokTestData data;
            boolean ok;
        }
    }

    public class FoldOn {
        @Getter public class FoldOnPublic{
            boolean ok;
        }

        @Getter class FoldOnClass{
            boolean ok;
        }

        @SuppressWarnings("ALL")
        @Getter class FoldOnWithAnnotation {
            boolean ok;
        }
    }

}
