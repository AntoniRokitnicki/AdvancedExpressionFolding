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

    class ParentClass extends GrandparentClas[0:"s"] {
        @Override
        public void grandparentMethod() [1:"{"]
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

    public class TestDataClass extends ParentClas[2:"s"] implements FirstInterfac[3:"e"], SecondInterfac[4:"e"], WithoutMethodInterfac[5:"e"] {

        @Override
        public void interfaceMethodOne() {
            System.out.println("Implementation of Interface Method One");
        [6:"}"]

        @Override
        public void interfaceMethodTwo() {
            System.out.println("Implementation of Interface Method Two");
        [7:"}"]

        @Override
        public void sharedMethod() {
            System.out.println("Overridden Shared Method");
        [8:"}"]

        @Override
        public void grandparentMethod() {
            System.out.println("Overridden Grandparent Method in TestDataClass");
        [9:"}"]

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