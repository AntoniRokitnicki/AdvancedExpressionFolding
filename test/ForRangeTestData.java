import java.util.ArrayList;
import java.util.List;

public class ForRangeTestData {
        public static void main(String[] args) {
                for (int i = 0; i < args.length; i++) {
                        String arg = args
                                [i];
                System.out.println(arg);
                System.out.println(i);
        }
                for (int i = 0; i < args.length; i++) {
                        String arg = args
                                [i];
                System.out.println(arg);
        }
                for (int i = 0; i < args.length; i++) {
                        System.out.println(i);
        }
                for (int i = 0; i <= args.length - 1; i++) {
                        System.out.println(i);
        }
                        List<String> list = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                        String a = list
                                .get(i);
                System.out.println(a);
        }
                for (int i = 0; i < list.size(); i++) {
                        String a = list
                                .get(i);
                System.out.println(a);
                System.out.println(i);
        }
                if (args.length > 0 && args.length < 2) {
                        System.out.println(args.length);
        }
        }
}