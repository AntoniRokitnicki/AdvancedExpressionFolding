package data;

import java.util.ArrayList;
import java.util.List;

public class ForRangeTestData {
        public static void main(String[] args) {
                for [0:"(int "]i[0:" = 0; i < args.length; i++) {\n                        String"] arg[0:" = "]args[0:"\n                                [i];"]
                [12:"System.out."]println(arg);
                [13:"System.out."]println(i);
        }
                for [1:"("][15:"int"] i = 0; i < args.length; i++) {
                        [16:"String"] arg[1:" = "]args[1:"\n                                [i];"]
                [17:"System.out."]println(arg);
        }
                for [2:"("][19:"int"] i[2:" = "]0[2:"; i < "]args.length[2:"; i++"][2:")"] {
                        [20:"System.out."]println(i);
        }
                for [3:"("][22:"int"] i[3:" = "]0[3:"; i <= "]args.length - 1[3:"; i++"][3:")"] {
                        [23:"System.out."]println(i);
        }
                        [4:"List<String>"] list = [5:"new ArrayList<>()"];
                for [6:"("][27:"int"] i = 0; i < list.size(); i++) {
                        [28:"String"] a[6:" = "]list[29:"\n                                .get("]i[29:")"];
                [30:"System.out."]println(a);
        }
                for [7:"(int "]i[7:" = 0; i < list.size(); i++) {\n                        String"] a[7:" = "]list[34:"\n                                .get("]i[34:")"];
                [35:"System.out."]println(a);
                [36:"System.out."]println(i);
        }
                if [8:"("]args.length[38:" > "]0[38:" && args.length < "]2[8:")"] {
                        [39:"System.out."]println(args.length);
        }
        }
}