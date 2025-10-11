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
            new HashMap<String, String>()[9:".put("]"a"[9:", "]"b"[9:")"];
            printStatus();
        }

        public void mainMsgs(String args, Object o, Long l, Preconditions z) {
            this.args = [19:"Preconditions.checkNotNull("][18:"args"][19:", \"args are null\")"];
            this.l = [21:"Preconditions.checkNotNull("][20:"l"][21:", \"l is null\")"];
            this.data = [23:"Preconditions.checkNotNull("]z.[22:"getData()"][23:", \"saaa is null\")"];
            this.o = [26:"Preconditions.checkNotNull("][25:"o"][26:", \"o is null\")"];
            new HashMap<String, String>()[27:".put("]"a"[27:", "]"b"[27:")"];
            printStatus();
        }

        public void mainNullable([49:"@Nullable"][49:" "]String[49:" "]args, [50:"@Nullable"][50:" "]Object[50:" "]o, [51:"@Nullable"][51:" "]Long[51:" "]l, [52:"@Nullable"][52:" "]Preconditions[52:" "]z) {
            this.args = [40:"Preconditions.checkNotNull("][39:"args"][40:")"];
            this.l = [42:"Preconditions.checkNotNull("][41:"l"][42:")"];
            this.data = [44:"Preconditions.checkNotNull("]z.[43:"getData()"][44:")"];
            this.o = [47:"Preconditions.checkNotNull("][46:"o"][47:")"];
            new HashMap<String, String>()[48:".put("]"a"[48:", "]"b"[48:")"];
            printStatus();
        }

        private void printStatus() {[65:"\n            "]new HashMap<String, String>()[66:".put("]"a"[66:", "]"b"[66:")"][65:";"][65:"\n        "]}
    }

    class Preconditions {
        public static <T> T checkNotNull(T o, String s) {[70:"\n            "][70:"return"][70:" "]o[70:";"][70:"\n        "]}
        public static <T> T checkNotNull(T o) {[71:"\n            "][71:"return"][71:" "]o[71:";"][71:"\n        "]}
        public Preconditions getData() {[72:"\n            "][72:"return"][72:" "]this[72:";"][72:"\n        "]}
    }

}
