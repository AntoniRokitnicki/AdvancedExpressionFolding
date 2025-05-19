package data;

public class SummaryParentOverrideTestData {

    class GrandparentClass {
        public void grandparentMethod() {
            System.out.println("Grandparent Method");
        }

        public void sharedMethod() {
            System.out.println("Shared method from Grandparent");
        }
    }

    class ParentClass extends GrandparentClass(1-grandparentMethod) {
        @Override
        public void grandparentMethod() { // overrides from GrandparentClass
            System.out.println("Overridden Grandparent Method in Parent");
            System.out.println("Overridden Grandparent Method in Parent");
        }

        public void parentMethod() {
            System.out.println("Parent Method");
        }
    }

    interface FirstInterface {
        void interfaceMethodOne();
        void sharedMethod();
    }

    interface SecondInterface {
        void interfaceMethodTwo();
    }

    public class TestDataClass extends ParentClass(1-grandparentMethod) implements FirstInterface(2-interfaceMethodOne, sharedMethod), SecondInterface(1-interfaceMethodTwo) {

        @Override
        public void interfaceMethodOne() {
            System.out.println("Implementation of Interface Method One");
        } // overrides from FirstInterface

        @Override
        public void interfaceMethodTwo() {
            System.out.println("Implementation of Interface Method Two");
        } // overrides from SecondInterface

        @Override
        public void sharedMethod() {
            System.out.println("Overridden Shared Method");
        } // overrides from FirstInterface

        @Override
        public void grandparentMethod() {
            System.out.println("Overridden Grandparent Method in TestDataClass");
        } // overrides from ParentClass

        public void additionalMethodOne() {
            System.out.println("Additional method one");
        }

        public void additionalMethodTwo() {
            System.out.println("Additional method two");
        }

        public void additionalMethodThree() {
            System.out.println("Additional method three");
        }
    }
}