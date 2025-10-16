package data;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

@SuppressWarnings("ALL")
public class NullableAnnotationCheckNotNullTestData {

    class PreconditionsCheck {
        public void main(String[11:" "]args, Object o, Long[21:" "]l, Preconditions z) {[10:"\n            "][0:"Preconditions.checkNotNull("]args[0:")"];[20:"\n            "][1:"Preconditions.checkNotNull("]l[1:")"];
            [2:"Preconditions.checkNotNull("]z.[3:"getData()"][2:")"];
            [4:"Preconditions.checkNotNull("]o[4:")"];
            System.out.println();
            new HashMap<String, String>()[5:".put("]"a"[5:", "]"b"[5:")"];
        }

        public void mainMsgs(String[64:" "]args, Object o, Long[74:" "]l, Preconditions z) {[63:"\n            "][53:"Preconditions.checkNotNull("]args[53:", \"args are null\")"];[73:"\n            "][54:"Preconditions.checkNotNull("]l[54:", \"l is null\")"];
            [55:"Preconditions.checkNotNull("]z.[56:"getData()"][55:", \"o is null\")"];
            [57:"Preconditions.checkNotNull("]o[57:", \"o is null\")"];
            System.out.println();
            new HashMap<String, String>()[58:".put("]"a"[58:", "]"b"[58:")"];
        }

        public void mainConflictAnnotations([118:"@Nullable"][118:" "]String[117:" "]args, [123:"@Nullable"][123:" "]Object[123:" "]o, [130:"@Nullable"][130:" "]Long[129:" "]l, [135:"@Nullable"][135:" "]Preconditions[135:" "]z) {[116:"\n            "][106:"Preconditions.checkNotNull("]args[106:")"];[128:"\n            "][107:"Preconditions.checkNotNull("]l[107:")"];
            [108:"Preconditions.checkNotNull("]z.[109:"getData()"][108:")"];
            [110:"Preconditions.checkNotNull("]o[110:")"];
            System.out.println();
            new HashMap<String, String>()[111:".put("]"a"[111:", "]"b"[111:")"];
        }

        public void mainConflictAnnotationsWithMsg([179:"@Nullable"][179:" "]String[178:" "]args, [184:"@Nullable"][184:" "]Object[184:" "]o, [191:"@Nullable"][191:" "]Long[190:" "]l, [196:"@Nullable"][196:" "]Preconditions[196:" "]z) {[177:"\n            "][167:"Preconditions.checkNotNull("]args[167:", \"args are null\")"];[189:"\n            "][168:"Preconditions.checkNotNull("]l[168:", \"l is null\")"];
            [169:"Preconditions.checkNotNull("]z.[170:"getData()"][169:", \"o is null\")"];
            [171:"Preconditions.checkNotNull("]o[171:", \"o is null\")"];
            new HashMap<String, String>()[172:".put("]"a"[172:", "]"b"[172:")"];
            System.out.println();
        }
    }

    class PreconditionsCheckReturn {
        private String args;
        private Object o;
        private Long l;
        private Preconditions data;

        public void main1(String args, Object o, Long l, Preconditions z) {
            this.args = [229:"Preconditions.checkNotNull("][228:"args"][229:")"];
            this.l = [231:"Preconditions.checkNotNull("][230:"l"][231:")"];
            this.data = [233:"Preconditions.checkNotNull("]z.[232:"getData()"][233:")"];
            this.o = [236:"Preconditions.checkNotNull("][235:"o"][236:")"];
            new HashMap<String, String>()[237:".put("]"a"[237:", "]"b"[237:")"];
            printStatus();
        }

        public void mainMsgs(String args, Object o, Long l, Preconditions z) {
            this.args = [247:"Preconditions.checkNotNull("][246:"args"][247:", \"args are null\")"];
            this.l = [249:"Preconditions.checkNotNull("][248:"l"][249:", \"l is null\")"];
            this.data = [251:"Preconditions.checkNotNull("]z.[250:"getData()"][251:", \"saaa is null\")"];
            this.o = [254:"Preconditions.checkNotNull("][253:"o"][254:", \"o is null\")"];
            new HashMap<String, String>()[255:".put("]"a"[255:", "]"b"[255:")"];
            printStatus();
        }

        public void mainNullable([277:"@Nullable"][277:" "]String[277:" "]args, [278:"@Nullable"][278:" "]Object[278:" "]o, [279:"@Nullable"][279:" "]Long[279:" "]l, [280:"@Nullable"][280:" "]Preconditions[280:" "]z) {
            this.args = [268:"Preconditions.checkNotNull("][267:"args"][268:")"];
            this.l = [270:"Preconditions.checkNotNull("][269:"l"][270:")"];
            this.data = [272:"Preconditions.checkNotNull("]z.[271:"getData()"][272:")"];
            this.o = [275:"Preconditions.checkNotNull("][274:"o"][275:")"];
            new HashMap<String, String>()[276:".put("]"a"[276:", "]"b"[276:")"];
            printStatus();
        }

        public void mainMsgsNullable([303:"@Nullable"][303:" "]String[303:" "]args, [304:"@Nullable"][304:" "]Object[304:" "]o, [305:"@Nullable"][305:" "]Long[305:" "]l, [306:"@Nullable"][306:" "]Preconditions[306:" "]z) {
            this.args = [294:"Preconditions.checkNotNull("][293:"args"][294:", \"args are null\")"];
            this.l = [296:"Preconditions.checkNotNull("][295:"l"][296:", \"l is null\")"];
            this.data = [298:"Preconditions.checkNotNull("]z.[297:"getData()"][298:", \"saaa is null\")"];
            this.o = [301:"Preconditions.checkNotNull("][300:"o"][301:", \"o is null\")"];
            new HashMap<String, String>()[302:".put("]"a"[302:", "]"b"[302:")"];
            printStatus();
        }

        private void printStatus() {[322:"\n            "]new HashMap<String, String>()[323:".put("]"a"[323:", "]"b"[323:")"][322:";"][322:"\n        "]}
    }

    class Preconditions {
        public static <T> T checkNotNull(T o, String s) {[327:"\n            "][327:"return"][327:" "]o[327:";"][327:"\n        "]}
        public static <T> T checkNotNull(T o) {[328:"\n            "][328:"return"][328:" "]o[328:";"][328:"\n        "]}
        public Preconditions getData() {[329:"\n            "][329:"return"][329:" "]this[329:";"][329:"\n        "]}
    }

}
