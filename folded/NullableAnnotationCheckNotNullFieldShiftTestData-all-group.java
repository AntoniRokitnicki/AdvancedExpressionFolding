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
            this.args = [1:"Preconditions.checkNotNull("][0:"args"][10:"args"][1:")"];
            this.l = [3:"Preconditions.checkNotNull("][2:"l"][11:"l"][3:")"];
            this.data = [5:"Preconditions.checkNotNull("][13:"Preconditions.checkNotNull("]z.[4:"getData()"][6:"getData()"][12:"getData()"][14:"getData()"][15:"getData()"][5:")"][13:")"];
            this.o = [8:"Preconditions.checkNotNull("][7:"o"][16:"o"][8:")"];
            new HashMap<String, String>()[9:".put("][17:".put("]"a"[9:", "][17:", "]"b"[9:")"][17:")"];
            printStatus();
        }

        public void mainMsgs(String args, Object o, Long l, Preconditions z) {
            this.args = [19:"Preconditions.checkNotNull("][29:"Preconditions.checkNotNull("][18:"args"][28:"args"][19:", \"args are null\")"][29:", \"args are null\")"];
            this.l = [21:"Preconditions.checkNotNull("][31:"Preconditions.checkNotNull("][20:"l"][30:"l"][21:", \"l is null\")"][31:", \"l is null\")"];
            this.data = [23:"Preconditions.checkNotNull("][33:"Preconditions.checkNotNull("]z.[22:"getData()"][24:"getData()"][32:"getData()"][34:"getData()"][35:"getData()"][23:", \"saaa is null\")"][33:", \"saaa is null\")"];
            this.o = [26:"Preconditions.checkNotNull("][37:"Preconditions.checkNotNull("][25:"o"][36:"o"][26:", \"o is null\")"][37:", \"o is null\")"];
            new HashMap<String, String>()[27:".put("][38:".put("]"a"[27:", "][38:", "]"b"[27:")"][38:")"];
            printStatus();
        }

        public void mainNullable([49:"@Nullable"][53:"@Nullable"][49:" "][53:" "]String[49:" "][53:" "]args, [50:"@Nullable"][54:"@Nullable"][50:" "][54:" "]Object[50:" "][54:" "]o, [51:"@Nullable"][55:"@Nullable"][51:" "][55:" "]Long[51:" "][55:" "]l, [52:"@Nullable"][56:"@Nullable"][52:" "][56:" "]Preconditions[52:" "][56:" "]z) {
            this.args = [40:"Preconditions.checkNotNull("][39:"args"][57:"args"][40:")"];
            this.l = [42:"Preconditions.checkNotNull("][41:"l"][58:"l"][42:")"];
            this.data = [44:"Preconditions.checkNotNull("][60:"Preconditions.checkNotNull("]z.[43:"getData()"][45:"getData()"][59:"getData()"][61:"getData()"][62:"getData()"][44:")"][60:")"];
            this.o = [47:"Preconditions.checkNotNull("][46:"o"][63:"o"][47:")"];
            new HashMap<String, String>()[48:".put("][64:".put("]"a"[48:", "][64:", "]"b"[48:")"][64:")"];
            printStatus();
        }

        private void printStatus() {[65:"\n            "]new HashMap<String, String>()[66:".put("][67:".put("][68:".put("][69:".put("]"a"[66:", "][67:", "][68:", "][69:", "]"b"[66:")"][67:")"][68:")"][69:")"][65:";"][65:"\n        "]}
    }

    class Preconditions {
        public static <T> T checkNotNull(T o, String s) {[70:"\n            "][70:"return"][70:" "]o[70:";"][70:"\n        "]}
        public static <T> T checkNotNull(T o) {[71:"\n            "][71:"return"][71:" "]o[71:";"][71:"\n        "]}
        public Preconditions getData() {[72:"\n            "][72:"return"][72:" "]this[72:";"][72:"\n        "]}
    }

}
