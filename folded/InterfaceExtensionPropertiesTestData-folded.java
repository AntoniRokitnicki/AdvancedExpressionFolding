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
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           @Getter String name;

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           @Setter String name;

            /**
             * Gets the age of the user.
             * @return the age of the user
             */
           @Getter int age;

            /**
             * Sets the age of the user.
             * @param age the age to set
             */
           @Setter int age;
        }

        /**
         * Interface representing a read-only user with getter methods for name and age.
         */
        public interface ReadOnlyUser {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           @Getter String name;

            /**
             * Gets the age of the user.
             * @return the age of the user
             */
           @Getter int age;
        }

        /**
         * Interface representing a write-only user with setter methods for name and age.
         */
        public interface WriteOnlyUser {
            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           @Setter String name;

            /**
             * Sets the age of the user.
             * @param age the age to set
             */
           @Setter int age;
        }

        /**
         * Interface representing a user with mixed access: both getter and setter for name, and getter only for age.
         */
        public interface MixedAccessUser {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           @Getter String name;

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           @Setter String name;

            /**
             * Gets the age of the user.
             * @return the age of the user
             */
           @Getter int age;
        }

        /**
         * Interface representing a user with mixed access: both getter and setter for name, and setter only for age.
         */
        public interface MixedAccessUser2 {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           @Getter String name;

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           @Setter String name;

            /**
             * Sets the age of the user.
             * @param age the age to set
             */
           @Setter int age;
        }

        /**
         * Interface representing a user with a single getter method for name.
         */
        public interface SingleGetterUser {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           @Getter String name;
        }

        /**
         * Interface representing a user with a single setter method for name.
         */
        public interface SingleSetterUser {
            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           @Setter String name;
        }

        /**
         * Interface representing a user with both getter and setter methods for name.
         */
        public interface SinglePropertyUser {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           @Getter String name;

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           @Setter String name;
        }
    }

    interface Finder {
       @Finder String tag(String name);
        //@Finder String tag(String name);

       @Finder String tag(byte age);

       @Finder String name(String name);

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
