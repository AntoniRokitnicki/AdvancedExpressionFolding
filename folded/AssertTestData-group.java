package data;

public class AssertTestData {
    public static void main(String[] args) {
        [0:"if"] [0:"("]args.length [0:"=="] 0[0:") {\n            throw new IllegalArgumentException()"];[0:"\n        }"]
        [1:"if"] [1:"("]args.length [1:"=="] 1[1:") {\n         "] [1:" "] [1:"throw new IllegalArgumentException("]"..."[1:")"];[1:"\n        }"]
        [2:"if"] [2:"("]args.length [2:"=="] 2[2:")\n         "] [2:" "] [2:"throw new IllegalArgumentException("]"..."[2:")"];
    }
}
