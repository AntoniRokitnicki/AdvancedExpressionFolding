package data;

@SuppressWarnings("ALL")
public class InterfaceExtensionPropertiesTestData {

    //TODO: default & static methods examples - all should be ignored
    public interface User {
        String name();
        void setName(String name);

        int age();
        void setAge(int age);
    }

    public interface PublicUser {
        public String name();
        public void setName(String name);

        public int age();
        public void setAge(int age);
    }


    public interface ReadOnlyUser {
        String name();
        int age();
    }

    public interface WriteOnlyUser {
        void setName(String name);
        void setAge(int age);
    }

    public interface MixedAccessUser {
        String name();
        void setName(String name);

        int age();
    }

    public interface MixedAccessUser2 {
        String name();
        void setName(String name);

        void setAge(int age);
    }

    public interface SingleGetterUser {
        String name();
    }

    public interface SingleSetterUser {
        void setName(String name);
    }

    public interface SinglePropertyUser {
        String name();
        void setName(String name);
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
            String name();

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
            void setName(String name);

            /**
             * Gets the age of the user.
             * @return the age of the user
             */
            int age();

            /**
             * Sets the age of the user.
             * @param age the age to set
             */
            void setAge(int age);
        }

        /**
         * Interface representing a read-only user with getter methods for name and age.
         */
        public interface ReadOnlyUser {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
            String name();

            /**
             * Gets the age of the user.
             * @return the age of the user
             */
            int age();
        }

        /**
         * Interface representing a write-only user with setter methods for name and age.
         */
        public interface WriteOnlyUser {
            /**
             * Sets the name of the user.
             * @param name the name to set
             */
            void setName(String name);

            /**
             * Sets the age of the user.
             * @param age the age to set
             */
            void setAge(int age);
        }

        /**
         * Interface representing a user with mixed access: both getter and setter for name, and getter only for age.
         */
        public interface MixedAccessUser {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
            String name();

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
            void setName(String name);

            /**
             * Gets the age of the user.
             * @return the age of the user
             */
            int age();
        }

        /**
         * Interface representing a user with mixed access: both getter and setter for name, and setter only for age.
         */
        public interface MixedAccessUser2 {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
            String name();

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
            void setName(String name);

            /**
             * Sets the age of the user.
             * @param age the age to set
             */
            void setAge(int age);
        }

        /**
         * Interface representing a user with a single getter method for name.
         */
        public interface SingleGetterUser {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
            String name();
        }

        /**
         * Interface representing a user with a single setter method for name.
         */
        public interface SingleSetterUser {
            /**
             * Sets the name of the user.
             * @param name the name to set
             */
            void setName(String name);
        }

        /**
         * Interface representing a user with both getter and setter methods for name.
         */
        public interface SinglePropertyUser {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
            String name();

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
            void setName(String name);
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
