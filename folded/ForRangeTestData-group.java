package data;

import java.util.ArrayList;
import java.util.List;

public class ForRangeTestData {
        public static void main(String[] args) {
                for [0:"("][6:"("]int i[0:" = 0; i < args.length; i++) {\n                        "][6:" = 0; i < args.length; i++) {\n                        "]String arg[0:" = "][6:" = "]args[0:"\n                                [i];"][6:"\n                                [i];"]
                System.out.println(arg);
                System.out.println(i);
        }
                for ([1:"int i = 0; i < args.length; i++) {\n                        "][7:"int i = 0; i < args.length; i++) {\n                        "]String arg[1:" = "][7:" = "]args[1:"\n                                [i];"][7:"\n                                [i];"]
                System.out.println(arg);
        }
                for (int i[2:" = "][8:" = "]0[2:"; i < "][8:"; i < "]args.length[2:"; i++"][8:"; i++"]) {
                        System.out.println(i);
        }
                for (int i[3:" = "][9:" = "]0[3:"; i <= "][9:"; i <= "]args.length - 1[3:"; i++"][9:"; i++"]) {
                        System.out.println(i);
        }
                        List<String> list = new ArrayList<>();
                for ([4:"int i = 0; i < list.size(); i++) {\n                        "][10:"int i = 0; i < list.size(); i++) {\n                        "]String a[4:" = "][10:" = "]list[4:"\n                                .get(i);"][10:"\n                                .get(i);"]
                System.out.println(a);
        }
                for [5:"("][11:"("]int i[5:" = 0; i < list.size(); i++) {\n                        "][11:" = 0; i < list.size(); i++) {\n                        "]String a[5:" = "][11:" = "]list[5:"\n                                .get(i);"][11:"\n                                .get(i);"]
                System.out.println(a);
                System.out.println(i);
        }
                if (args.length[12:" > "]0[12:" && args.length < "]2[12:")"] {
                        System.out.println(args.length);
        }
        }
}