package data;

import java.util.ArrayList;
import java.util.List;

public class ForRangeTestData {
        public static void main(String[] args) {
                for ((int i, String arg) : args) {

                System.out.println(arg);
                System.out.println(i);
        }
                for (String arg : args) {

                System.out.println(arg);
        }
                for (int i : [0, args.length)) {
                        System.out.println(i);
        }
                for (int i : [0, args.length - 1]) {
                        System.out.println(i);
        }
                        List<String> list = new ArrayList<>();
                for (String a : list) {

                System.out.println(a);
        }
                for ((int i, String a) : list) {

                System.out.println(a);
                System.out.println(i);
        }
                if (args.length in (0, 2)) {
                        System.out.println(args.length);
        }
        }
}