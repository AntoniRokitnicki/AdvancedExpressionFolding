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

    class ParentClass extends GrandparentClass {
        @Override
        public void grandparentMethod() {
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

    interface WithoutMethodInterface {

    }

    public class TestDataClass extends ParentClass implements FirstInterface, SecondInterface, WithoutMethodInterface {

        @Override
        public void interfaceMethodOne() {
            System.out.println("Implementation of Interface Method One");
        }

        @Override
        public void interfaceMethodTwo() {
            System.out.println("Implementation of Interface Method Two");
        }

        @Override
        public void sharedMethod() {
            System.out.println("Overridden Shared Method");
        }

        @Override
        public void grandparentMethod() {
            System.out.println("Overridden Grandparent Method in TestDataClass");
        }

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