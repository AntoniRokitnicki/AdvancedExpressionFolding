package data;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

@SuppressWarnings("ALL")
public class NullableAnnotationCheckNotNullTestData {

    class PreconditionsCheck {
        public void main(String[9:" "]args, Object o, Long[17:" "]l, Preconditions z) {[8:"\n            "][0:"Preconditions.checkNotNull("]args[0:")"];[16:"\n            "][1:"Preconditions.checkNotNull("]l[1:")"];
            [2:"Preconditions.checkNotNull("]z.[3:"getData()"][2:")"];
            [4:"Preconditions.checkNotNull("]o[4:")"];
            System.out.println();
            new HashMap<String, String>().put("a", "b");
        }

        public void mainMsgs(String[52:" "]args, Object o, Long[60:" "]l, Preconditions z) {[51:"\n            "][43:"Preconditions.checkNotNull("]args[43:", \"args are null\")"];[59:"\n            "][44:"Preconditions.checkNotNull("]l[44:", \"l is null\")"];
            [45:"Preconditions.checkNotNull("]z.[46:"getData()"][45:", \"o is null\")"];
            [47:"Preconditions.checkNotNull("]o[47:", \"o is null\")"];
            System.out.println();
            new HashMap<String, String>().put("a", "b");
        }

        public void mainConflictAnnotations([96:"@Nullable"][96:" "]String[95:" "]args, [100:"@Nullable"][100:" "]Object[100:" "]o, [106:"@Nullable"][106:" "]Long[105:" "]l, [110:"@Nullable"][110:" "]Preconditions[110:" "]z) {[94:"\n            "][86:"Preconditions.checkNotNull("]args[86:")"];[104:"\n            "][87:"Preconditions.checkNotNull("]l[87:")"];
            [88:"Preconditions.checkNotNull("]z.[89:"getData()"][88:")"];
            [90:"Preconditions.checkNotNull("]o[90:")"];
            System.out.println();
            new HashMap<String, String>().put("a", "b");
        }

        public void mainConflictAnnotationsWithMsg([147:"@Nullable"][147:" "]String[146:" "]args, [151:"@Nullable"][151:" "]Object[151:" "]o, [157:"@Nullable"][157:" "]Long[156:" "]l, [161:"@Nullable"][161:" "]Preconditions[161:" "]z) {[145:"\n            "][137:"Preconditions.checkNotNull("]args[137:", \"args are null\")"];[155:"\n            "][138:"Preconditions.checkNotNull("]l[138:", \"l is null\")"];
            [139:"Preconditions.checkNotNull("]z.[140:"getData()"][139:", \"o is null\")"];
            [141:"Preconditions.checkNotNull("]o[141:", \"o is null\")"];
            new HashMap<String, String>().put("a", "b");
            System.out.println();
        }
    }

    class PreconditionsCheckReturn {
        private String args;
        private Object o;
        private Long l;
        private Preconditions data;

        public void main1(String args, Object o, Long l, Preconditions z) {
            this.args = [188:"Preconditions.checkNotNull("]args[188:")"];
            this.l = [189:"Preconditions.checkNotNull("]l[189:")"];
            this.data = [190:"Preconditions.checkNotNull("]z.[191:"getData()"][190:")"];
            this.o = [192:"Preconditions.checkNotNull("]o[192:")"];
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainMsgs(String args, Object o, Long l, Preconditions z) {
            this.args = [199:"Preconditions.checkNotNull("]args[199:", \"args are null\")"];
            this.l = [200:"Preconditions.checkNotNull("]l[200:", \"l is null\")"];
            this.data = [201:"Preconditions.checkNotNull("]z.[202:"getData()"][201:", \"saaa is null\")"];
            this.o = [203:"Preconditions.checkNotNull("]o[203:", \"o is null\")"];
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainNullable([215:"@Nullable"][215:" "]String[215:" "]args, [216:"@Nullable"][216:" "]Object[216:" "]o, [217:"@Nullable"][217:" "]Long[217:" "]l, [218:"@Nullable"][218:" "]Preconditions[218:" "]z) {
            this.args = [210:"Preconditions.checkNotNull("]args[210:")"];
            this.l = [211:"Preconditions.checkNotNull("]l[211:")"];
            this.data = [212:"Preconditions.checkNotNull("]z.[213:"getData()"][212:")"];
            this.o = [214:"Preconditions.checkNotNull("]o[214:")"];
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainMsgsNullable([234:"@Nullable"][234:" "]String[234:" "]args, [235:"@Nullable"][235:" "]Object[235:" "]o, [236:"@Nullable"][236:" "]Long[236:" "]l, [237:"@Nullable"][237:" "]Preconditions[237:" "]z) {
            this.args = [229:"Preconditions.checkNotNull("]args[229:", \"args are null\")"];
            this.l = [230:"Preconditions.checkNotNull("]l[230:", \"l is null\")"];
            this.data = [231:"Preconditions.checkNotNull("]z.[232:"getData()"][231:", \"saaa is null\")"];
            this.o = [233:"Preconditions.checkNotNull("]o[233:", \"o is null\")"];
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
