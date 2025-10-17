package data;

public class TypeCastTestData {
    public static void main(String[] args) {
        [0:"TypeCastTestData"][2:"TypeCastTestData"] t = new TypeCastTestData();
        if [1:"("][3:"("]t.[4:"getObject()"] instanceof TypeCastTestData &&
                [6:"((TypeCastTestData) "]t.[7:"getObject()"][6:")."][5:"getObject()"] instanceof TypeCastTestData[1:")"][3:")"] {
                [8:"System.out."]println([10:"((TypeCastTestData) "][15:"((TypeCastTestData) "][12:"((TypeCastTestData) "][17:"((TypeCastTestData) "]t.[13:"getObject()"][18:"getObject()"][12:")."][17:")."][11:"getObject()"][16:"getObject()"][10:")."][15:")."][9:"getObject()"][14:"getObject()"]);
        handle([19:"((TypeCastTestData) "][21:"((TypeCastTestData) "]t.[22:"getObject()"][21:")."][20:"getObject()"][19:")"]);
        }
    }

    private Object getObject() {[23:"\n        "][23:"return"][23:" "]this[23:";"][23:"\n    "]}

    private static void handle(TypeCastTestData t) {[24:"\n        "][25:"System.out."][26:"System.out."][27:"System.out."][27:"System.out."][27:"System.out."][28:"System.out."]println(t)[24:";"][24:"\n    "]}
}
