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
            this.args = [1:"Preconditions.checkNotNull("][0:"args"][9:"args"][1:")"];
            this.l = [3:"Preconditions.checkNotNull("][2:"l"][10:"l"][3:")"];
            this.data = [5:"Preconditions.checkNotNull("][12:"Preconditions.checkNotNull("]z.[4:"getData()"][6:"getData()"][11:"getData()"][13:"getData()"][14:"getData()"][5:")"][12:")"];
            this.o = [8:"Preconditions.checkNotNull("][7:"o"][15:"o"][8:")"];
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainMsgs(String args, Object o, Long l, Preconditions z) {
            this.args = [17:"Preconditions.checkNotNull("][26:"Preconditions.checkNotNull("][16:"args"][25:"args"][17:", \"args are null\")"][26:", \"args are null\")"];
            this.l = [19:"Preconditions.checkNotNull("][28:"Preconditions.checkNotNull("][18:"l"][27:"l"][19:", \"l is null\")"][28:", \"l is null\")"];
            this.data = [21:"Preconditions.checkNotNull("][30:"Preconditions.checkNotNull("]z.[20:"getData()"][22:"getData()"][29:"getData()"][31:"getData()"][32:"getData()"][21:", \"saaa is null\")"][30:", \"saaa is null\")"];
            this.o = [24:"Preconditions.checkNotNull("][34:"Preconditions.checkNotNull("][23:"o"][33:"o"][24:", \"o is null\")"][34:", \"o is null\")"];
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainNullable([44:"@Nullable"][48:"@Nullable"][44:" "][48:" "]String[44:" "][48:" "]args, [45:"@Nullable"][49:"@Nullable"][45:" "][49:" "]Object[45:" "][49:" "]o, [46:"@Nullable"][50:"@Nullable"][46:" "][50:" "]Long[46:" "][50:" "]l, [47:"@Nullable"][51:"@Nullable"][47:" "][51:" "]Preconditions[47:" "][51:" "]z) {
            this.args = [36:"Preconditions.checkNotNull("][35:"args"][52:"args"][36:")"];
            this.l = [38:"Preconditions.checkNotNull("][37:"l"][53:"l"][38:")"];
            this.data = [40:"Preconditions.checkNotNull("][55:"Preconditions.checkNotNull("]z.[39:"getData()"][41:"getData()"][54:"getData()"][56:"getData()"][57:"getData()"][40:")"][55:")"];
            this.o = [43:"Preconditions.checkNotNull("][42:"o"][58:"o"][43:")"];
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
