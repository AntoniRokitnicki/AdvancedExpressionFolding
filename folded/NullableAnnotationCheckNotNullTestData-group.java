package data;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

@SuppressWarnings("ALL")
public class NullableAnnotationCheckNotNullTestData {

    class PreconditionsCheck {
        public void main(String[9:" "][25:" "]args, Object o, Long[17:" "][33:" "]l, Preconditions z) {[8:"\n            "][24:"\n            "][0:"Preconditions.checkNotNull("][37:"Preconditions.checkNotNull("]ull(args);"][24:"Preconditions.checkNotNull(args);"]:")"][37:")"];[16:"\n            "][32:"\n            "][1:"Preconditions.checkNotNull("][38:"Preconditions.checkNotNull("]Null(l);"][32:"Preconditions.checkNotNull(l);"]:")"][38:")"];
            [2:"Preconditions.checkNotNull("][5:"Preconditions.checkNotNull("][10:"Preconditions.checkNotNull("][13:"Preconditions.checkNotNull("][18:"Preconditions.checkNotNull("][21:"Preconditions.checkNotNull("][26:"Preconditions.checkNotNull("][29:"Preconditions.checkNotNull("][34:"Preconditions.checkNotNull("][39:"Preconditions.checkNotNull("]z.[3:"getData()"][6:"getData()"][11:"getData()"][14:"getData()"][19:"getData()"][22:"getData()"][27:"getData()"][30:"getData()"][35:"getData()"][40:"getData()"][41:"getData()"][2:")"][5:")"][10:")"][13:")"][18:")"][21:")"][26:")"][29:")"][34:")"][39:")"];
            [4:"Preconditions.checkNotNull("][7:"Preconditions.checkNotNull("][12:"Preconditions.checkNotNull("][15:"Preconditions.checkNotNull("][20:"Preconditions.checkNotNull("][23:"Preconditions.checkNotNull("][28:"Preconditions.checkNotNull("][31:"Preconditions.checkNotNull("][36:"Preconditions.checkNotNull("][42:"Preconditions.checkNotNull("]o[4:")"][7:")"][12:")"][15:")"][20:")"][23:")"][28:")"][31:")"][36:")"][42:")"];
            System.out.println();
            new HashMap<String, String>().put("a", "b");
        }

        public void mainMsgs(String[52:" "][68:" "]args, Object o, Long[60:" "][76:" "]l, Preconditions z) {[51:"\n            "][67:"\n            "][43:"Preconditions.checkNotNull("][80:"Preconditions.checkNotNull("]Null(args, \"args are null\");"][67:"Preconditions.checkNotNull(args, \"args are null\");"]ull\")"][80:", \"args are null\")"];[59:"\n            "][75:"\n            "][44:"Preconditions.checkNotNull("][81:"Preconditions.checkNotNull("]Null(l, \"l is null\");"][75:"Preconditions.checkNotNull(l, \"l is null\");"]ull\")"][81:", \"l is null\")"];
            [45:"Preconditions.checkNotNull("][48:"Preconditions.checkNotNull("][53:"Preconditions.checkNotNull("][56:"Preconditions.checkNotNull("][61:"Preconditions.checkNotNull("][64:"Preconditions.checkNotNull("][69:"Preconditions.checkNotNull("][72:"Preconditions.checkNotNull("][77:"Preconditions.checkNotNull("][82:"Preconditions.checkNotNull("]z.[46:"getData()"][49:"getData()"][54:"getData()"][57:"getData()"][62:"getData()"][65:"getData()"][70:"getData()"][73:"getData()"][78:"getData()"][83:"getData()"][84:"getData()"][45:", \"o is null\")"][48:", \"o is null\")"][53:", \"o is null\")"][56:", \"o is null\")"][61:", \"o is null\")"][64:", \"o is null\")"][69:", \"o is null\")"][72:", \"o is null\")"][77:", \"o is null\")"][82:", \"o is null\")"];
            [47:"Preconditions.checkNotNull("][50:"Preconditions.checkNotNull("][55:"Preconditions.checkNotNull("][58:"Preconditions.checkNotNull("][63:"Preconditions.checkNotNull("][66:"Preconditions.checkNotNull("][71:"Preconditions.checkNotNull("][74:"Preconditions.checkNotNull("][79:"Preconditions.checkNotNull("][85:"Preconditions.checkNotNull("]o[47:", \"o is null\")"][50:", \"o is null\")"][55:", \"o is null\")"][58:", \"o is null\")"][63:", \"o is null\")"][66:", \"o is null\")"][71:", \"o is null\")"][74:", \"o is null\")"][79:", \"o is null\")"][85:", \"o is null\")"];
            System.out.println();
            new HashMap<String, String>().put("a", "b");
        }

        public void mainConflictAnnotations([96:"@Nullable"][116:"@Nullable"][96:" "][116:" "]String[95:" "][96:" "][115:" "][116:" "]args, [100:"@Nullable"][120:"@Nullable"][100:" "][120:" "]Object[100:" "][120:" "]o, [106:"@Nullable"][126:"@Nullable"][106:" "][126:" "]Long[105:" "][106:" "][125:" "][126:" "]l, [110:"@Nullable"][130:"@Nullable"][110:" "][130:" "]Preconditions[110:" "][130:" "]z) {[94:"\n            "][114:"\n            "][86:"Preconditions.checkNotNull("][131:"Preconditions.checkNotNull("]Null(args);"][114:"Preconditions.checkNotNull(args);"]6:")"][131:")"];[104:"\n            "][124:"\n            "][87:"Preconditions.checkNotNull("][132:"Preconditions.checkNotNull("]tNull(l);"][124:"Preconditions.checkNotNull(l);"]7:")"][132:")"];
            [88:"Preconditions.checkNotNull("][91:"Preconditions.checkNotNull("][97:"Preconditions.checkNotNull("][101:"Preconditions.checkNotNull("][107:"Preconditions.checkNotNull("][111:"Preconditions.checkNotNull("][117:"Preconditions.checkNotNull("][121:"Preconditions.checkNotNull("][127:"Preconditions.checkNotNull("][133:"Preconditions.checkNotNull("]z.[89:"getData()"][92:"getData()"][98:"getData()"][102:"getData()"][108:"getData()"][112:"getData()"][118:"getData()"][122:"getData()"][128:"getData()"][134:"getData()"][135:"getData()"][88:")"][91:")"][97:")"][101:")"][107:")"][111:")"][117:")"][121:")"][127:")"][133:")"];
            [90:"Preconditions.checkNotNull("][93:"Preconditions.checkNotNull("][99:"Preconditions.checkNotNull("][103:"Preconditions.checkNotNull("][109:"Preconditions.checkNotNull("][113:"Preconditions.checkNotNull("][119:"Preconditions.checkNotNull("][123:"Preconditions.checkNotNull("][129:"Preconditions.checkNotNull("][136:"Preconditions.checkNotNull("]o[90:")"][93:")"][99:")"][103:")"][109:")"][113:")"][119:")"][123:")"][129:")"][136:")"];
            System.out.println();
            new HashMap<String, String>().put("a", "b");
        }

        public void mainConflictAnnotationsWithMsg([147:"@Nullable"][167:"@Nullable"][147:" "][167:" "]String[146:" "][147:" "][166:" "][167:" "]args, [151:"@Nullable"][171:"@Nullable"][151:" "][171:" "]Object[151:" "][171:" "]o, [157:"@Nullable"][177:"@Nullable"][157:" "][177:" "]Long[156:" "][157:" "][176:" "][177:" "]l, [161:"@Nullable"][181:"@Nullable"][161:" "][181:" "]Preconditions[161:" "][181:" "]z) {[145:"\n            "][165:"\n            "][137:"Preconditions.checkNotNull("][182:"Preconditions.checkNotNull("]tNull(args, \"args are null\");"][165:"Preconditions.checkNotNull(args, \"args are null\");"]null\")"][182:", \"args are null\")"];[155:"\n            "][175:"\n            "][138:"Preconditions.checkNotNull("][183:"Preconditions.checkNotNull("]tNull(l, \"l is null\");"][175:"Preconditions.checkNotNull(l, \"l is null\");"]null\")"][183:", \"l is null\")"];
            [139:"Preconditions.checkNotNull("][142:"Preconditions.checkNotNull("][148:"Preconditions.checkNotNull("][152:"Preconditions.checkNotNull("][158:"Preconditions.checkNotNull("][162:"Preconditions.checkNotNull("][168:"Preconditions.checkNotNull("][172:"Preconditions.checkNotNull("][178:"Preconditions.checkNotNull("][184:"Preconditions.checkNotNull("]z.[140:"getData()"][143:"getData()"][149:"getData()"][153:"getData()"][159:"getData()"][163:"getData()"][169:"getData()"][173:"getData()"][179:"getData()"][185:"getData()"][186:"getData()"][139:", \"o is null\")"][142:", \"o is null\")"][148:", \"o is null\")"][152:", \"o is null\")"][158:", \"o is null\")"][162:", \"o is null\")"][168:", \"o is null\")"][172:", \"o is null\")"][178:", \"o is null\")"][184:", \"o is null\")"];
            [141:"Preconditions.checkNotNull("][144:"Preconditions.checkNotNull("][150:"Preconditions.checkNotNull("][154:"Preconditions.checkNotNull("][160:"Preconditions.checkNotNull("][164:"Preconditions.checkNotNull("][170:"Preconditions.checkNotNull("][174:"Preconditions.checkNotNull("][180:"Preconditions.checkNotNull("][187:"Preconditions.checkNotNull("]o[141:", \"o is null\")"][144:", \"o is null\")"][150:", \"o is null\")"][154:", \"o is null\")"][160:", \"o is null\")"][164:", \"o is null\")"][170:", \"o is null\")"][174:", \"o is null\")"][180:", \"o is null\")"][187:", \"o is null\")"];
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
            this.args = [188:"Preconditions.checkNotNull("][193:"Preconditions.checkNotNull("]args[188:")"][193:")"];
            this.l = [189:"Preconditions.checkNotNull("][194:"Preconditions.checkNotNull("]l[189:")"][194:")"];
            this.data = [190:"Preconditions.checkNotNull("][195:"Preconditions.checkNotNull("]z.[191:"getData()"][196:"getData()"][197:"getData()"][190:")"][195:")"];
            this.o = [192:"Preconditions.checkNotNull("][198:"Preconditions.checkNotNull("]o[192:")"][198:")"];
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainMsgs(String args, Object o, Long l, Preconditions z) {
            this.args = [199:"Preconditions.checkNotNull("][204:"Preconditions.checkNotNull("]args[199:", \"args are null\")"][204:", \"args are null\")"];
            this.l = [200:"Preconditions.checkNotNull("][205:"Preconditions.checkNotNull("]l[200:", \"l is null\")"][205:", \"l is null\")"];
            this.data = [201:"Preconditions.checkNotNull("][206:"Preconditions.checkNotNull("]z.[202:"getData()"][207:"getData()"][208:"getData()"][201:", \"saaa is null\")"][206:", \"saaa is null\")"];
            this.o = [203:"Preconditions.checkNotNull("][209:"Preconditions.checkNotNull("]o[203:", \"o is null\")"][209:", \"o is null\")"];
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainNullable([215:"@Nullable"][219:"@Nullable"][215:" "][219:" "]String[215:" "][219:" "]args, [216:"@Nullable"][220:"@Nullable"][216:" "][220:" "]Object[216:" "][220:" "]o, [217:"@Nullable"][221:"@Nullable"][217:" "][221:" "]Long[217:" "][221:" "]l, [218:"@Nullable"][222:"@Nullable"][218:" "][222:" "]Preconditions[218:" "][222:" "]z) {
            this.args = [210:"Preconditions.checkNotNull("][223:"Preconditions.checkNotNull("]args[210:")"][223:")"];
            this.l = [211:"Preconditions.checkNotNull("][224:"Preconditions.checkNotNull("]l[211:")"][224:")"];
            this.data = [212:"Preconditions.checkNotNull("][225:"Preconditions.checkNotNull("]z.[213:"getData()"][226:"getData()"][227:"getData()"][212:")"][225:")"];
            this.o = [214:"Preconditions.checkNotNull("][228:"Preconditions.checkNotNull("]o[214:")"][228:")"];
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainMsgsNullable([234:"@Nullable"][238:"@Nullable"][234:" "][238:" "]String[234:" "][238:" "]args, [235:"@Nullable"][239:"@Nullable"][235:" "][239:" "]Object[235:" "][239:" "]o, [236:"@Nullable"][240:"@Nullable"][236:" "][240:" "]Long[236:" "][240:" "]l, [237:"@Nullable"][241:"@Nullable"][237:" "][241:" "]Preconditions[237:" "][241:" "]z) {
            this.args = [229:"Preconditions.checkNotNull("][242:"Preconditions.checkNotNull("]args[229:", \"args are null\")"][242:", \"args are null\")"];
            this.l = [230:"Preconditions.checkNotNull("][243:"Preconditions.checkNotNull("]l[230:", \"l is null\")"][243:", \"l is null\")"];
            this.data = [231:"Preconditions.checkNotNull("][244:"Preconditions.checkNotNull("]z.[232:"getData()"][245:"getData()"][246:"getData()"][231:", \"saaa is null\")"][244:", \"saaa is null\")"];
            this.o = [233:"Preconditions.checkNotNull("][247:"Preconditions.checkNotNull("]o[233:", \"o is null\")"][247:", \"o is null\")"];
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
