package data;

@SuppressWarnings("ALL")
public class InterfaceExtensionPropertiesTestData {

    //TODO: default & static methods examples - all should be ignored
    //TODO: Nullable examples

    public interface User {
       @Getter String name;
       @Setter String name;

       @Getter int age;
       @Setter int age;
    }

    public interface PublicUser {
       @Getter public String name;
       @Setter public String name;

       @Getter public int age;
       @Setter public int age;
    }


    public interface ReadOnlyUser {
       @Getter String name;
       @Getter int age;
    }

    public interface WriteOnlyUser {
       @Setter String name;
       @Setter int age;
    }

    public interface MixedAccessUser {
       @Getter String name;
       @Setter String name;

       @Getter int age;
    }

    public interface MixedAccessUser2 {
       @Getter String name;
       @Setter String name;

       @Setter int age;
    }

    public interface SingleGetterUser {
       @Getter String name;
    }

    public interface SingleSetterUser {
       @Setter String name;
    }

    public interface SinglePropertyUser {
       @Getter String name;
       @Setter String name;
    }

    class Javadoced {
        /**
         * Interface representing a user with both getter and setter methods for name and age.
         */
        public interface User {
           @Getter /**
             * Gets the name of the user.
             * @return the name of the user
             */
            String name;

           @Setter /**
             * Sets the name of the user.
             * @param name the name to set
             */
            String name;

           @Getter /**
             * Gets the age of the user.
             * @return the age of the user
             */
            int age;

           @Setter /**
             * Sets the age of the user.
             * @param age the age to set
             */
            int age;
        }

        /**
         * Interface representing a read-only user with getter methods for name and age.
         */
        public interface ReadOnlyUser {
           @Getter /**
             * Gets the name of the user.
             * @return the name of the user
             */
            String name;

           @Getter /**
             * Gets the age of the user.
             * @return the age of the user
             */
            int age;
        }

        /**
         * Interface representing a write-only user with setter methods for name and age.
         */
        public interface WriteOnlyUser {
           @Setter /**
             * Sets the name of the user.
             * @param name the name to set
             */
            String name;

           @Setter /**
             * Sets the age of the user.
             * @param age the age to set
             */
            int age;
        }

        /**
         * Interface representing a user with mixed access: both getter and setter for name, and getter only for age.
         */
        public interface MixedAccessUser {
           @Getter /**
             * Gets the name of the user.
             * @return the name of the user
             */
            String name;

           @Setter /**
             * Sets the name of the user.
             * @param name the name to set
             */
            String name;

           @Getter /**
             * Gets the age of the user.
             * @return the age of the user
             */
            int age;
        }

        /**
         * Interface representing a user with mixed access: both getter and setter for name, and setter only for age.
         */
        public interface MixedAccessUser2 {
           @Getter /**
             * Gets the name of the user.
             * @return the name of the user
             */
            String name;

           @Setter /**
             * Sets the name of the user.
             * @param name the name to set
             */
            String name;

           @Setter /**
             * Sets the age of the user.
             * @param age the age to set
             */
            int age;
        }

        /**
         * Interface representing a user with a single getter method for name.
         */
        public interface SingleGetterUser {
           @Getter /**
             * Gets the name of the user.
             * @return the name of the user
             */
            String name;
        }

        /**
         * Interface representing a user with a single setter method for name.
         */
        public interface SingleSetterUser {
           @Setter /**
             * Sets the name of the user.
             * @param name the name to set
             */
            String name;
        }

        /**
         * Interface representing a user with both getter and setter methods for name.
         */
        public interface SinglePropertyUser {
           @Getter /**
             * Gets the name of the user.
             * @return the name of the user
             */
            String name;

           @Setter /**
             * Sets the name of the user.
             * @param name the name to set
             */
            String name;
        }
    }

    /**
     public interface User {
        property String name { get; set; }
        property int age { get; }
     }
     public interface User {
        String name { get; set; }
        int age { get; }
     }

     public interface User {
        @Getter String name; <inlay jump to setter?>
        @Setter String name; <inlay jump to getter?>
        @Getter int age;
     }
    **/

}
