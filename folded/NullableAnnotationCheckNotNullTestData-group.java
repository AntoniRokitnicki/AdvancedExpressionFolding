package data;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

@SuppressWarnings("ALL")
public class NullableAnnotationCheckNotNullTestData {

    class PreconditionsCheck {
        public void main(String[0:" "]{[0:" "]}args, Object o, Long[0:" "]{[0:" "]}l, Preconditions z) {[0:"
            "]{[0:"
            "]}[0:"Preconditions.checkNotNull(args);"]{[0:"Preconditions.checkNotNull(args);"]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]} [0:")"]{[0:")"]}}}[0:"
            "]{[0:"
            "]}[0:"Preconditions.checkNotNull(l);"]{[0:"Preconditions.checkNotNull(l);"]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]} [0:")"]{[0:")"]}}}
            [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}}}}}}}}}z.[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]}}}}}}}}}}[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]}}}}}}}}};
            [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}}}}}}}}}o[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]}}}}}}}}};
            System.out.println();
            new HashMap<String, String>().put("a", "b");
        }

        public void mainMsgs(String[0:" "]{[0:" "]}args, Object o, Long[0:" "]{[0:" "]}l, Preconditions z) {[0:"
            "]{[0:"
            "]}[0:"Preconditions.checkNotNull(args, "args are null");"]{[0:"Preconditions.checkNotNull(args, "args are null");"]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]} [0:", "args are null")"]{[0:", "args are null")"]}}}[0:"
            "]{[0:"
            "]}[0:"Preconditions.checkNotNull(l, "l is null");"]{[0:"Preconditions.checkNotNull(l, "l is null");"]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]} [0:", "l is null")"]{[0:", "l is null")"]}}}
            [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}}}}}}}}}z.[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]}}}}}}}}}}[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]}}}}}}}}};
            [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}}}}}}}}}o[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]}}}}}}}}};
            System.out.println();
            new HashMap<String, String>().put("a", "b");
        }

        public void mainConflictAnnotations([0:"@Nullable"]{[0:"@Nullable"]}[0:" "]{[0:" "]}String[0:" "]{[0:" "]{[0:" "]{[0:" "]}}}args, [0:"@Nullable"]{[0:"@Nullable"]}[0:" "]{[0:" "]}Object[0:" "]{[0:" "]}o, [0:"@Nullable"]{[0:"@Nullable"]}[0:" "]{[0:" "]}Long[0:" "]{[0:" "]{[0:" "]{[0:" "]}}}l, [0:"@Nullable"]{[0:"@Nullable"]}[0:" "]{[0:" "]}Preconditions[0:" "]{[0:" "]}z) {[0:"
            "]{[0:"
            "]}[0:"Preconditions.checkNotNull(args);"]{[0:"Preconditions.checkNotNull(args);"]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]} [0:")"]{[0:")"]}}}[0:"
            "]{[0:"
            "]}[0:"Preconditions.checkNotNull(l);"]{[0:"Preconditions.checkNotNull(l);"]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]} [0:")"]{[0:")"]}}}
            [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}}}}}}}}}z.[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]}}}}}}}}}}[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]}}}}}}}}};
            [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}}}}}}}}}o[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]{[0:")"]}}}}}}}}};
            System.out.println();
            new HashMap<String, String>().put("a", "b");
        }

        public void mainConflictAnnotationsWithMsg([0:"@Nullable"]{[0:"@Nullable"]}[0:" "]{[0:" "]}String[0:" "]{[0:" "]{[0:" "]{[0:" "]}}}args, [0:"@Nullable"]{[0:"@Nullable"]}[0:" "]{[0:" "]}Object[0:" "]{[0:" "]}o, [0:"@Nullable"]{[0:"@Nullable"]}[0:" "]{[0:" "]}Long[0:" "]{[0:" "]{[0:" "]{[0:" "]}}}l, [0:"@Nullable"]{[0:"@Nullable"]}[0:" "]{[0:" "]}Preconditions[0:" "]{[0:" "]}z) {[0:"
            "]{[0:"
            "]}[0:"Preconditions.checkNotNull(args, "args are null");"]{[0:"Preconditions.checkNotNull(args, "args are null");"]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]} [0:", "args are null")"]{[0:", "args are null")"]}}}[0:"
            "]{[0:"
            "]}[0:"Preconditions.checkNotNull(l, "l is null");"]{[0:"Preconditions.checkNotNull(l, "l is null");"]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]} [0:", "l is null")"]{[0:", "l is null")"]}}}
            [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}}}}}}}}}z.[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]{[0:"getData()"]}}}}}}}}}}[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]}}}}}}}}};
            [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}}}}}}}}}o[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]{[0:", "o is null")"]}}}}}}}}};
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
            this.args = [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}args[0:")"]{[0:")"]};
            this.l = [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}l[0:")"]{[0:")"]};
            this.data = [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}z.[0:"getData()"]{[0:"getData()"]{[0:"getData()"]}}[0:")"]{[0:")"]};
            this.o = [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}o[0:")"]{[0:")"]};
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainMsgs(String args, Object o, Long l, Preconditions z) {
            this.args = [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}args[0:", "args are null")"]{[0:", "args are null")"]};
            this.l = [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}l[0:", "l is null")"]{[0:", "l is null")"]};
            this.data = [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}z.[0:"getData()"]{[0:"getData()"]{[0:"getData()"]}}[0:", "saaa is null")"]{[0:", "saaa is null")"]};
            this.o = [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}o[0:", "o is null")"]{[0:", "o is null")"]};
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainNullable([0:"@Nullable"]{[0:"@Nullable"]}[0:" "]{[0:" "]}String[0:" "]{[0:" "]}args, [0:"@Nullable"]{[0:"@Nullable"]}[0:" "]{[0:" "]}Object[0:" "]{[0:" "]}o, [0:"@Nullable"]{[0:"@Nullable"]}[0:" "]{[0:" "]}Long[0:" "]{[0:" "]}l, [0:"@Nullable"]{[0:"@Nullable"]}[0:" "]{[0:" "]}Preconditions[0:" "]{[0:" "]}z) {
            this.args = [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}args[0:")"]{[0:")"]};
            this.l = [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}l[0:")"]{[0:")"]};
            this.data = [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}z.[0:"getData()"]{[0:"getData()"]{[0:"getData()"]}}[0:")"]{[0:")"]};
            this.o = [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}o[0:")"]{[0:")"]};
            new HashMap<String, String>().put("a", "b");
            printStatus();
        }

        public void mainMsgsNullable([0:"@Nullable"]{[0:"@Nullable"]}[0:" "]{[0:" "]}String[0:" "]{[0:" "]}args, [0:"@Nullable"]{[0:"@Nullable"]}[0:" "]{[0:" "]}Object[0:" "]{[0:" "]}o, [0:"@Nullable"]{[0:"@Nullable"]}[0:" "]{[0:" "]}Long[0:" "]{[0:" "]}l, [0:"@Nullable"]{[0:"@Nullable"]}[0:" "]{[0:" "]}Preconditions[0:" "]{[0:" "]}z) {
            this.args = [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}args[0:", "args are null")"]{[0:", "args are null")"]};
            this.l = [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}l[0:", "l is null")"]{[0:", "l is null")"]};
            this.data = [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}z.[0:"getData()"]{[0:"getData()"]{[0:"getData()"]}}[0:", "saaa is null")"]{[0:", "saaa is null")"]};
            this.o = [0:"Preconditions.checkNotNull("]{[0:"Preconditions.checkNotNull("]}o[0:", "o is null")"]{[0:", "o is null")"]};
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
