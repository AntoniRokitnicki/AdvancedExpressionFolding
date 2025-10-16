package data[0:";"]

public class AssertTestData {
    public static void main(String[] args) {
        [1:"if"] [1:"("]args.length [1:"=="] 0[1:") {\n            throw new IllegalArgumentException();\n        }"]
        [2:"if"] [2:"("]args.length [2:"=="] 1[2:") {\n         "] [2:" "] [2:"throw new IllegalArgumentException("]"..."[2:");\n        }"]
        [3:"if"] [3:"("]args.length [3:"=="] 2[3:")\n         "] [3:" "] [3:"throw new IllegalArgumentException("]"..."[3:");"]
    }
}
