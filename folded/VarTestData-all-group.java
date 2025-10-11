package data;

public class VarTestData {
    public static void main(String[] args) {
        [0:"String"] string = "Hello, world";
        System.out.println();
        [1:"int"] count = 0;
        for [2:"("][7:"String"] arg : args[2:")"] {
                [8:"System.out."]println(arg);
        count++;
        }
        for [3:"(int "]i[3:" = 0; i < args.length; i++) {\n                String"] arg[3:" = "]args[3:"[i];"]
        [12:"System.out."]println(arg);
        i++;
        }
    }
}
