package data;

public class SummaryParentOverrideTestData {

    class GrandparentClass {
        public void grandparentMethod() {[0:"\n            "][1:"System.out."][3:"System.out."][5:"System.out."][7:"System.out."]println([2:"\"Grandparent Method\""][4:"\"Grandparent Method\""][6:"\"Grandparent Method\""][8:"\"Grandparent Method\""])[0:";"][0:"\n        "]}

        public void sharedMethod() {[9:"\n            "][10:"System.out."][12:"System.out."][14:"System.out."][16:"System.out."]println([11:"\"Shared method from Grandparent\""][13:"\"Shared method from Grandparent\""][15:"\"Shared method from Grandparent\""][17:"\"Shared method from Grandparent\""])[9:";"][9:"\n        "]}
    }

    class ParentClass extends GrandparentClas[18:"s"] {
        [20:"@Override"][20:"\n        "]public void grandparentMethod() [19:"{"]
            [21:"System.out."][25:"System.out."]println([22:"\"Overridden Grandparent Method in Parent\""][26:"\"Overridden Grandparent Method in Parent\""]);
            [23:"System.out."][27:"System.out."]println([24:"\"Overridden Grandparent Method in Parent\""][28:"\"Overridden Grandparent Method in Parent\""]);
        }

        public void parentMethod() {[29:"\n            "][30:"System.out."][32:"System.out."][34:"System.out."][36:"System.out."]println([31:"\"Parent Method\""][33:"\"Parent Method\""][35:"\"Parent Method\""][37:"\"Parent Method\""])[29:";"][29:"\n        "]}
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

        [48:"@Override"][48:"\n        "]public void interfaceMethodOne() {[43:"\n            "][44:"System.out."][46:"System.out."][49:"System.out."][51:"System.out."]println([45:"\"Implementation of Interface Method One\""][47:"\"Implementation of Interface Method One\""][50:"\"Implementation of Interface Method One\""][52:"\"Implementation of Interface Method One\""])[43:";"][43:"\n        "][42:"}"]

        [59:"@Override"][59:"\n        "]public void interfaceMethodTwo() {[54:"\n            "][55:"System.out."][57:"System.out."][60:"System.out."][62:"System.out."]println([56:"\"Implementation of Interface Method Two\""][58:"\"Implementation of Interface Method Two\""][61:"\"Implementation of Interface Method Two\""][63:"\"Implementation of Interface Method Two\""])[54:";"][54:"\n        "][53:"}"]

        [70:"@Override"][70:"\n        "]public void sharedMethod() {[65:"\n            "][66:"System.out."][68:"System.out."][71:"System.out."][73:"System.out."]println([67:"\"Overridden Shared Method\""][69:"\"Overridden Shared Method\""][72:"\"Overridden Shared Method\""][74:"\"Overridden Shared Method\""])[65:";"][65:"\n        "][64:"}"]

        [81:"@Override"][81:"\n        "]public void grandparentMethod() {[76:"\n            "][77:"System.out."][79:"System.out."][82:"System.out."][84:"System.out."]println([78:"\"Overridden Grandparent Method in TestDataClass\""][80:"\"Overridden Grandparent Method in TestDataClass\""][83:"\"Overridden Grandparent Method in TestDataClass\""][85:"\"Overridden Grandparent Method in TestDataClass\""])[76:";"][76:"\n        "][75:"}"]

        public void additionalMethodOne() {[86:"\n            "][87:"System.out."][89:"System.out."][91:"System.out."][93:"System.out."]println([88:"\"Additional method one\""][90:"\"Additional method one\""][92:"\"Additional method one\""][94:"\"Additional method one\""])[86:";"][86:"\n        "]}

        public void additionalMethodTwo() {[95:"\n            "][96:"System.out."][98:"System.out."][100:"System.out."][102:"System.out."]println([97:"\"Additional method two\""][99:"\"Additional method two\""][101:"\"Additional method two\""][103:"\"Additional method two\""])[95:";"][95:"\n        "]}

        public void additionalMethodThree() {[104:"\n            "][105:"System.out."][107:"System.out."][109:"System.out."][111:"System.out."]println([106:"\"Additional method three\""][108:"\"Additional method three\""][110:"\"Additional method three\""][112:"\"Additional method three\""])[104:";"][104:"\n        "]}
    }
}