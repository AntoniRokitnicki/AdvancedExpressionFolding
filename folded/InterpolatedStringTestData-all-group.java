package data;

public class InterpolatedStringTestData {
    public static void main(String[] args) {
        [0:"System.out."]println("Hello, [1:"\" + "]args[0][1:")"];
        [3:"System.out."]println("Hello, [4:"\" + "]args[0][4:" + \""]!");
        [6:"System.out."]println[7:"("]args[0][7:" + \""], hello!");
        [9:"System.out."]println[10:"("]args[0][10:" + \""], [10:"\" + "]args[0][10:")"];
        [12:"String"] name = args[0];
        [13:"System.out."]println("Hello, [14:"\" + "]name[14:")"];
        [16:"System.out."]println("Hello, [17:"\" + "]name[17:" + \""]!");
        [19:"System.out."]println[20:"("]name[20:" + \""], hello!");
        [22:"System.out."]println[23:"("]name[23:" + \""], [23:"\" + "]name[23:")"];
        [25:"System.out."]println("Unicode: [26:"\" + "]'\u0041'[26:")"];
        [28:"System.out."]println("Next: [29:"\" + "][30:"(char)("]'A' + 1[30:")"][29:")"];
        [33:"System.out."]println("Length: [34:"\" + "]args.length[34:")"];
        [36:"System.out."]println("Sum: [37:"\" + ("]2 + 3[37:")"]);
        [39:"System.out."]println("Upper: [40:"\" + "]name.toUpperCase()[40:")"];
    }
}
