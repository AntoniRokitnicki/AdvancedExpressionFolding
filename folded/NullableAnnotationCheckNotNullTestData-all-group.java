package data;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

@SuppressWarnings("ALL")
public class NullableAnnotationCheckNotNullTestData {

    class PreconditionsCheck {
        public void main(String[11:" "][31:" "]args, Object o, Long[21:" "][41:" "]l, Preconditions z) {[10:"\n            "][30:"\n            "][0:"Preconditions.checkNotNull("][46:"Preconditions.checkNotNull("]Null(args);"][30:"Preconditions.checkNotNull(args);"]:")"][46:")"];[20:"\n            "][40:"\n            "][1:"Preconditions.checkNotNull("][47:"Preconditions.checkNotNull("]Null(l);"][40:"Preconditions.checkNotNull(l);"]:")"][47:")"];
            [2:"Preconditions.checkNotNull("][6:"Preconditions.checkNotNull("][12:"Preconditions.checkNotNull("][16:"Preconditions.checkNotNull("][22:"Preconditions.checkNotNull("][26:"Preconditions.checkNotNull("][32:"Preconditions.checkNotNull("][36:"Preconditions.checkNotNull("][42:"Preconditions.checkNotNull("][48:"Preconditions.checkNotNull("]z.[3:"getData()"][7:"getData()"][13:"getData()"][17:"getData()"][23:"getData()"][27:"getData()"][33:"getData()"][37:"getData()"][43:"getData()"][49:"getData()"][50:"getData()"][2:")"][6:")"][12:")"][16:")"][22:")"][26:")"][32:")"][36:")"][42:")"][48:")"];
            [4:"Preconditions.checkNotNull("][8:"Preconditions.checkNotNull("][14:"Preconditions.checkNotNull("][18:"Preconditions.checkNotNull("][24:"Preconditions.checkNotNull("][28:"Preconditions.checkNotNull("][34:"Preconditions.checkNotNull("][38:"Preconditions.checkNotNull("][44:"Preconditions.checkNotNull("][51:"Preconditions.checkNotNull("]o[4:")"][8:")"][14:")"][18:")"][24:")"][28:")"][34:")"][38:")"][44:")"][51:")"];
            System.out.println();
            new HashMap<String, String>()[5:".put("][9:".put("][15:".put("][19:".put("][25:".put("][29:".put("][35:".put("][39:".put("][45:".put("][52:".put("]"a"[5:", "][9:", "][15:", "][19:", "][25:", "][29:", "][35:", "][39:", "][45:", "][52:", "]"b"[5:")"][9:")"][15:")"][19:")"][25:")"][29:")"][35:")"][39:")"][45:")"][52:")"];
        }

        public void mainMsgs(String[64:" "][84:" "]args, Object o, Long[74:" "][94:" "]l, Preconditions z) {[63:"\n            "][83:"\n            "][53:"Preconditions.checkNotNull("][99:"Preconditions.checkNotNull("]Null(args, \"args are null\");"][83:"Preconditions.checkNotNull(args, \"args are null\");"]ull\")"][99:", \"args are null\")"];[73:"\n            "][93:"\n            "][54:"Preconditions.checkNotNull("][100:"Preconditions.checkNotNull("]Null(l, \"l is null\");"][93:"Preconditions.checkNotNull(l, \"l is null\");"]ull\")"][100:", \"l is null\")"];
            [55:"Preconditions.checkNotNull("][59:"Preconditions.checkNotNull("][65:"Preconditions.checkNotNull("][69:"Preconditions.checkNotNull("][75:"Preconditions.checkNotNull("][79:"Preconditions.checkNotNull("][85:"Preconditions.checkNotNull("][89:"Preconditions.checkNotNull("][95:"Preconditions.checkNotNull("][101:"Preconditions.checkNotNull("]z.[56:"getData()"][60:"getData()"][66:"getData()"][70:"getData()"][76:"getData()"][80:"getData()"][86:"getData()"][90:"getData()"][96:"getData()"][102:"getData()"][103:"getData()"][55:", \"o is null\")"][59:", \"o is null\")"][65:", \"o is null\")"][69:", \"o is null\")"][75:", \"o is null\")"][79:", \"o is null\")"][85:", \"o is null\")"][89:", \"o is null\")"][95:", \"o is null\")"][101:", \"o is null\")"];
            [57:"Preconditions.checkNotNull("][61:"Preconditions.checkNotNull("][67:"Preconditions.checkNotNull("][71:"Preconditions.checkNotNull("][77:"Preconditions.checkNotNull("][81:"Preconditions.checkNotNull("][87:"Preconditions.checkNotNull("][91:"Preconditions.checkNotNull("][97:"Preconditions.checkNotNull("][104:"Preconditions.checkNotNull("]o[57:", \"o is null\")"][61:", \"o is null\")"][67:", \"o is null\")"][71:", \"o is null\")"][77:", \"o is null\")"][81:", \"o is null\")"][87:", \"o is null\")"][91:", \"o is null\")"][97:", \"o is null\")"][104:", \"o is null\")"];
            System.out.println();
            new HashMap<String, String>()[58:".put("][62:".put("][68:".put("][72:".put("][78:".put("][82:".put("][88:".put("][92:".put("][98:".put("][105:".put("]"a"[58:", "][62:", "][68:", "][72:", "][78:", "][82:", "][88:", "][92:", "][98:", "][105:", "]"b"[58:")"][62:")"][68:")"][72:")"][78:")"][82:")"][88:")"][92:")"][98:")"][105:")"];
        }

        public void mainConflictAnnotations([118:"@Nullable"][142:"@Nullable"][118:" "][142:" "]String[117:" "][118:" "][141:" "][142:" "]args, [123:"@Nullable"][147:"@Nullable"][123:" "][147:" "]Object[123:" "][147:" "]o, [130:"@Nullable"][154:"@Nullable"][130:" "][154:" "]Long[129:" "][130:" "][153:" "][154:" "]l, [135:"@Nullable"][159:"@Nullable"][135:" "][159:" "]Preconditions[135:" "][159:" "]z) {[116:"\n            "][140:"\n            "][106:"Preconditions.checkNotNull("][160:"Preconditions.checkNotNull("]tNull(args);"][140:"Preconditions.checkNotNull(args);"]06:")"][160:")"];[128:"\n            "][152:"\n            "][107:"Preconditions.checkNotNull("][161:"Preconditions.checkNotNull("]tNull(l);"][152:"Preconditions.checkNotNull(l);"]07:")"][161:")"];
            [108:"Preconditions.checkNotNull("][112:"Preconditions.checkNotNull("][119:"Preconditions.checkNotNull("][124:"Preconditions.checkNotNull("][131:"Preconditions.checkNotNull("][136:"Preconditions.checkNotNull("][143:"Preconditions.checkNotNull("][148:"Preconditions.checkNotNull("][155:"Preconditions.checkNotNull("][162:"Preconditions.checkNotNull("]z.[109:"getData()"][113:"getData()"][120:"getData()"][125:"getData()"][132:"getData()"][137:"getData()"][144:"getData()"][149:"getData()"][156:"getData()"][163:"getData()"][164:"getData()"][108:")"][112:")"][119:")"][124:")"][131:")"][136:")"][143:")"][148:")"][155:")"][162:")"];
            [110:"Preconditions.checkNotNull("][114:"Preconditions.checkNotNull("][121:"Preconditions.checkNotNull("][126:"Preconditions.checkNotNull("][133:"Preconditions.checkNotNull("][138:"Preconditions.checkNotNull("][145:"Preconditions.checkNotNull("][150:"Preconditions.checkNotNull("][157:"Preconditions.checkNotNull("][165:"Preconditions.checkNotNull("]o[110:")"][114:")"][121:")"][126:")"][133:")"][138:")"][145:")"][150:")"][157:")"][165:")"];
            System.out.println();
            new HashMap<String, String>()[111:".put("][115:".put("][122:".put("][127:".put("][134:".put("][139:".put("][146:".put("][151:".put("][158:".put("][166:".put("]"a"[111:", "][115:", "][122:", "][127:", "][134:", "][139:", "][146:", "][151:", "][158:", "][166:", "]"b"[111:")"][115:")"][122:")"][127:")"][134:")"][139:")"][146:")"][151:")"][158:")"][166:")"];
        }

        public void mainConflictAnnotationsWithMsg([179:"@Nullable"][203:"@Nullable"][179:" "][203:" "]String[178:" "][179:" "][202:" "][203:" "]args, [184:"@Nullable"][208:"@Nullable"][184:" "][208:" "]Object[184:" "][208:" "]o, [191:"@Nullable"][215:"@Nullable"][191:" "][215:" "]Long[190:" "][191:" "][214:" "][215:" "]l, [196:"@Nullable"][220:"@Nullable"][196:" "][220:" "]Preconditions[196:" "][220:" "]z) {[177:"\n            "][201:"\n            "][167:"Preconditions.checkNotNull("][221:"Preconditions.checkNotNull("]tNull(args, \"args are null\");"][201:"Preconditions.checkNotNull(args, \"args are null\");"]null\")"][221:", \"args are null\")"];[189:"\n            "][213:"\n            "][168:"Preconditions.checkNotNull("][222:"Preconditions.checkNotNull("]tNull(l, \"l is null\");"][213:"Preconditions.checkNotNull(l, \"l is null\");"]null\")"][222:", \"l is null\")"];
            [169:"Preconditions.checkNotNull("][173:"Preconditions.checkNotNull("][180:"Preconditions.checkNotNull("][185:"Preconditions.checkNotNull("][192:"Preconditions.checkNotNull("][197:"Preconditions.checkNotNull("][204:"Preconditions.checkNotNull("][209:"Preconditions.checkNotNull("][216:"Preconditions.checkNotNull("][223:"Preconditions.checkNotNull("]z.[170:"getData()"][174:"getData()"][181:"getData()"][186:"getData()"][193:"getData()"][198:"getData()"][205:"getData()"][210:"getData()"][217:"getData()"][224:"getData()"][225:"getData()"][169:", \"o is null\")"][173:", \"o is null\")"][180:", \"o is null\")"][185:", \"o is null\")"][192:", \"o is null\")"][197:", \"o is null\")"][204:", \"o is null\")"][209:", \"o is null\")"][216:", \"o is null\")"][223:", \"o is null\")"];
            [171:"Preconditions.checkNotNull("][175:"Preconditions.checkNotNull("][182:"Preconditions.checkNotNull("][187:"Preconditions.checkNotNull("][194:"Preconditions.checkNotNull("][199:"Preconditions.checkNotNull("][206:"Preconditions.checkNotNull("][211:"Preconditions.checkNotNull("][218:"Preconditions.checkNotNull("][226:"Preconditions.checkNotNull("]o[171:", \"o is null\")"][175:", \"o is null\")"][182:", \"o is null\")"][187:", \"o is null\")"][194:", \"o is null\")"][199:", \"o is null\")"][206:", \"o is null\")"][211:", \"o is null\")"][218:", \"o is null\")"][226:", \"o is null\")"];
            new HashMap<String, String>()[172:".put("][176:".put("][183:".put("][188:".put("][195:".put("][200:".put("][207:".put("][212:".put("][219:".put("][227:".put("]"a"[172:", "][176:", "][183:", "][188:", "][195:", "][200:", "][207:", "][212:", "][219:", "][227:", "]"b"[172:")"][176:")"][183:")"][188:")"][195:")"][200:")"][207:")"][212:")"][219:")"][227:")"];
            System.out.println();
        }
    }

    class PreconditionsCheckReturn {
        private String args;
        private Object o;
        private Long l;
        private Preconditions data;

        public void main1(String args, Object o, Long l, Preconditions z) {
            this.args = [229:"Preconditions.checkNotNull("][228:"args"][238:"args"][229:")"];
            this.l = [231:"Preconditions.checkNotNull("][230:"l"][239:"l"][231:")"];
            this.data = [233:"Preconditions.checkNotNull("][241:"Preconditions.checkNotNull("]z.[232:"getData()"][234:"getData()"][240:"getData()"][242:"getData()"][243:"getData()"][233:")"][241:")"];
            this.o = [236:"Preconditions.checkNotNull("][235:"o"][244:"o"][236:")"];
            new HashMap<String, String>()[237:".put("][245:".put("]"a"[237:", "][245:", "]"b"[237:")"][245:")"];
            printStatus();
        }

        public void mainMsgs(String args, Object o, Long l, Preconditions z) {
            this.args = [247:"Preconditions.checkNotNull("][257:"Preconditions.checkNotNull("][246:"args"][256:"args"][247:", \"args are null\")"][257:", \"args are null\")"];
            this.l = [249:"Preconditions.checkNotNull("][259:"Preconditions.checkNotNull("][248:"l"][258:"l"][249:", \"l is null\")"][259:", \"l is null\")"];
            this.data = [251:"Preconditions.checkNotNull("][261:"Preconditions.checkNotNull("]z.[250:"getData()"][252:"getData()"][260:"getData()"][262:"getData()"][263:"getData()"][251:", \"saaa is null\")"][261:", \"saaa is null\")"];
            this.o = [254:"Preconditions.checkNotNull("][265:"Preconditions.checkNotNull("][253:"o"][264:"o"][254:", \"o is null\")"][265:", \"o is null\")"];
            new HashMap<String, String>()[255:".put("][266:".put("]"a"[255:", "][266:", "]"b"[255:")"][266:")"];
            printStatus();
        }

        public void mainNullable([277:"@Nullable"][281:"@Nullable"][277:" "][281:" "]String[277:" "][281:" "]args, [278:"@Nullable"][282:"@Nullable"][278:" "][282:" "]Object[278:" "][282:" "]o, [279:"@Nullable"][283:"@Nullable"][279:" "][283:" "]Long[279:" "][283:" "]l, [280:"@Nullable"][284:"@Nullable"][280:" "][284:" "]Preconditions[280:" "][284:" "]z) {
            this.args = [268:"Preconditions.checkNotNull("][267:"args"][285:"args"][268:")"];
            this.l = [270:"Preconditions.checkNotNull("][269:"l"][286:"l"][270:")"];
            this.data = [272:"Preconditions.checkNotNull("][288:"Preconditions.checkNotNull("]z.[271:"getData()"][273:"getData()"][287:"getData()"][289:"getData()"][290:"getData()"][272:")"][288:")"];
            this.o = [275:"Preconditions.checkNotNull("][274:"o"][291:"o"][275:")"];
            new HashMap<String, String>()[276:".put("][292:".put("]"a"[276:", "][292:", "]"b"[276:")"][292:")"];
            printStatus();
        }

        public void mainMsgsNullable([303:"@Nullable"][307:"@Nullable"][303:" "][307:" "]String[303:" "][307:" "]args, [304:"@Nullable"][308:"@Nullable"][304:" "][308:" "]Object[304:" "][308:" "]o, [305:"@Nullable"][309:"@Nullable"][305:" "][309:" "]Long[305:" "][309:" "]l, [306:"@Nullable"][310:"@Nullable"][306:" "][310:" "]Preconditions[306:" "][310:" "]z) {
            this.args = [294:"Preconditions.checkNotNull("][312:"Preconditions.checkNotNull("][293:"args"][311:"args"][294:", \"args are null\")"][312:", \"args are null\")"];
            this.l = [296:"Preconditions.checkNotNull("][314:"Preconditions.checkNotNull("][295:"l"][313:"l"][296:", \"l is null\")"][314:", \"l is null\")"];
            this.data = [298:"Preconditions.checkNotNull("][316:"Preconditions.checkNotNull("]z.[297:"getData()"][299:"getData()"][315:"getData()"][317:"getData()"][318:"getData()"][298:", \"saaa is null\")"][316:", \"saaa is null\")"];
            this.o = [301:"Preconditions.checkNotNull("][320:"Preconditions.checkNotNull("][300:"o"][319:"o"][301:", \"o is null\")"][320:", \"o is null\")"];
            new HashMap<String, String>()[302:".put("][321:".put("]"a"[302:", "][321:", "]"b"[302:")"][321:")"];
            printStatus();
        }

        private void printStatus() {[322:"\n            "]new HashMap<String, String>()[323:".put("][324:".put("][325:".put("][326:".put("]"a"[323:", "][324:", "][325:", "][326:", "]"b"[323:")"][324:")"][325:")"][326:")"][322:";"][322:"\n        "]}
    }

    class Preconditions {
        public static <T> T checkNotNull(T o, String s) {[327:"\n            "][327:"return"][327:" "]o[327:";"][327:"\n        "]}
        public static <T> T checkNotNull(T o) {[328:"\n            "][328:"return"][328:" "]o[328:";"][328:"\n        "]}
        public Preconditions getData() {[329:"\n            "][329:"return"][329:" "]this[329:";"][329:"\n        "]}
    }

}
