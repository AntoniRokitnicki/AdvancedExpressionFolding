package data;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Optional;

@Getter @Setter @Serial @SuppressWarnings("ALL")
public class LombokTestData {

    LombokTestData data;
    boolean ok;
    String string;

    public Optional<LombokTestData> asOptional() {
        return data;
    }

    @Getter public class LombokGetters {
        LombokTestData data;
        boolean ok;

        @Getter* public class LombokGettersPartial {
            LombokTestData data;
            boolean ok;
        }
    }

    @Getter @Setter public class LombokSetters {
        LombokTestData data;
        boolean ok;

        @Setter* public class LombokSettersPartial {
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

        @ToString* public class ToStringPartial {
            LombokTestData data;
            boolean ok;
        }

        @ToString* public class ToStringPartial2 {
            LombokTestData data;
            boolean ok;
            String string;
        }
    }

    @EqualsAndHashCode public class EqualsAndHashCodeFull {
        LombokTestData data;
        boolean ok;

        @EqualsAndHashCode* public class EqualsAndHashCodePartial {
            LombokTestData data;
            boolean ok;
        }

        @EqualsAndHashCode* public class EqualsAndHashCodePartialTwo {
            LombokTestData data;
            boolean ok;
            String string;
        }
    }

    @Equals public class EqualsFull {
        LombokTestData data;
        boolean ok;

        @Equals* public class EqualsPartial {
            LombokTestData data;
            boolean ok;
        }

        @Equals* public class EqualsPartialTwo {
            LombokTestData data;
            boolean ok;
            String string;
        }
    }

    @HashCode public class HashCodeFull {
        LombokTestData data;
        boolean ok;

        @HashCode* public class HashCodePartial {
            LombokTestData data;
            boolean ok;
        }

        @HashCode* public class HashCodePartialTwo {
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

        @Data* public class DataWithPartialGetters {
            LombokTestData data;
            boolean ok;
        }

        @Data* public class DataWithPartialSetters {
            LombokTestData data;
            boolean ok;
        }
    }

}
