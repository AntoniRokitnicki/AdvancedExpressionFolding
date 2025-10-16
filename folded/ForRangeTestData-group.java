package data;

import java.util.ArrayList;
import java.util.List;

public class ForRangeTestData {
        public static void main(String[] args) {
                for [0:"("]int i[0:" = 0; i < args.length; i++) {\n                        "]String arg[0:" = "]args[0:"\n                                [i];"]
                System.out.println(arg);
                System.out.println(i);
        }
                for ([1:"int i = 0; i < args.length; i++) {\n                        "]String arg[1:" = "]args[1:"\n                                [i];"]
                System.out.println(arg);
        }
                for (int i[2:" = "]0[2:"; i < "]args.length[2:"; i++"]) {
                        System.out.println(i);
        }
                for (int i[3:" = "]0[3:"; i <= "]args.length - 1[3:"; i++"]) {
                        System.out.println(i);
        }
                        List<String> list = new ArrayList<>();
                for ([4:"int i = 0; i < list.size(); i++) {\n                        "]String a[4:" = "]list[4:"\n                                .get(i);"]
                System.out.println(a);
        }
                for [5:"("]int i[5:" = 0; i < list.size(); i++) {\n                        "]String a[5:" = "]list[5:"\n                                .get(i);"]
                System.out.println(a);
                System.out.println(i);
        }
                if (args.length[12:" > "]0[12:" && args.length < "]2[12:")"] {
                        System.out.println(args.length);
        }
        }
}