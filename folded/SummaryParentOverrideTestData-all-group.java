package data;

public class SummaryParentOverrideTestData {

    class GrandparentClass {
        public void grandparentMethod() {[0:"\n            "][1:"System.out."]println([2:"\"Grandparent Method\""])[0:";"][0:"\n        "]}

        public void sharedMethod() {[9:"\n            "][10:"System.out."]println([11:"\"Shared method from Grandparent\""])[9:";"][9:"\n        "]}
    }

    class ParentClass extends GrandparentClas[18:"s"] {
        [20:"@Override"][20:"\n        "]public void grandparentMethod() [19:"{"]
            [21:"System.out."]println([22:"\"Overridden Grandparent Method in Parent\""]);
            [23:"System.out."]println([24:"\"Overridden Grandparent Method in Parent\""]);
        }

        public void parentMethod() {[29:"\n            "][30:"System.out."]println([31:"\"Parent Method\""])[29:";"][29:"\n        "]}
    }

    interface FirstInterface {
        void interfaceMethodOne();
        void sharedMethod();
    }

    interface SecondInterface {
        void interfaceMethodTwo();
    }

    interface WithoutMethodInterface {

    }

    public class TestDataClass extends ParentClas[38:"s"] implements FirstInterfac[39:"e"], SecondInterfac[40:"e"], WithoutMethodInterfac[41:"e"] {

        [48:"@Override"][48:"\n        "]public void interfaceMethodOne() {[43:"\n            "][44:"System.out."]println([45:"\"Implementation of Interface Method One\""])[43:";"][43:"\n        "][42:"}"]

        [59:"@Override"][59:"\n        "]public void interfaceMethodTwo() {[54:"\n            "][55:"System.out."]println([56:"\"Implementation of Interface Method Two\""])[54:";"][54:"\n        "][53:"}"]

        [70:"@Override"][70:"\n        "]public void sharedMethod() {[65:"\n            "][66:"System.out."]println([67:"\"Overridden Shared Method\""])[65:";"][65:"\n        "][64:"}"]

        [81:"@Override"][81:"\n        "]public void grandparentMethod() {[76:"\n            "][77:"System.out."]println([78:"\"Overridden Grandparent Method in TestDataClass\""])[76:";"][76:"\n        "][75:"}"]

        public void additionalMethodOne() {[86:"\n            "][87:"System.out."]println([88:"\"Additional method one\""])[86:";"][86:"\n        "]}

        public void additionalMethodTwo() {[95:"\n            "][96:"System.out."]println([97:"\"Additional method two\""])[95:";"][95:"\n        "]}

        public void additionalMethodThree() {[104:"\n            "][105:"System.out."]println([106:"\"Additional method three\""])[104:";"][104:"\n        "]}
    }
}