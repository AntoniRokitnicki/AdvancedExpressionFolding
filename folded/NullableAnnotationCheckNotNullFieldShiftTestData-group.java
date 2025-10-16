package data;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

@SuppressWarnings("ALL")
public class NullableAnnotationCheckNotNullFieldShiftTestData {

    class PreconditionsCheckReturn {
        private String args;
        private Object o;
        private Long l;
        private Preconditions data;

        public void main1(String args, Object o, Long l, Preconditions z) {
            this.args = [1:"Preconditions.checkNotNull("][0:"args"][1:")"];
            this.l = [3:"Preconditions.checkNotNull("][2:"l"][3:")"];
            this.data = [5:"Preconditions.checkNotNull("]z.[4:"getData()"][5:")"];
            this.o = [8:"Preconditions.checkNotNull("][7:"o"][8:")"];
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainMsgs(String args, Object o, Long l, Preconditions z) {
            this.args = [17:"Preconditions.checkNotNull("][16:"args"][17:", \"args are null\")"];
            this.l = [19:"Preconditions.checkNotNull("][18:"l"][19:", \"l is null\")"];
            this.data = [21:"Preconditions.checkNotNull("]z.[20:"getData()"][21:", \"saaa is null\")"];
            this.o = [24:"Preconditions.checkNotNull("][23:"o"][24:", \"o is null\")"];
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainNullable([44:"@Nullable"][44:" "]String[44:" "]args, [45:"@Nullable"][45:" "]Object[45:" "]o, [46:"@Nullable"][46:" "]Long[46:" "]l, [47:"@Nullable"][47:" "]Preconditions[47:" "]z) {
            this.args = [36:"Preconditions.checkNotNull("][35:"args"][36:")"];
            this.l = [38:"Preconditions.checkNotNull("][37:"l"][38:")"];
            this.data = [40:"Preconditions.checkNotNull("]z.[39:"getData()"][40:")"];
            this.o = [43:"Preconditions.checkNotNull("][42:"o"][43:")"];
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        private void printStatus() {
            new HashMap<String, String>().put("a", "b");
        }
    }

    class Preconditions {
        public static <T> T checkNotNull(T o, String s) {
            return o;
        }
        public static <T> T checkNotNull(T o) {
            return o;
        }
        public Preconditions getData() {
            return this;
        }
    }

}
