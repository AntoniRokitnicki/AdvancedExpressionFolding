package data;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@SuppressWarnings("ALL")
public class InterfaceExtensionPropertiesTestData {

    interface TODO {
        public interface NullableUser {
            @Nullable
            Integer getAge();
            void setAge(@Nullable int age);
            @Nullable
            String getName();
            void setName(@Nullable String name);
        }

        public interface NotNullUser {
            @NotNull
            String getName();
            void setName(@NotNull String name);
            int getAge();
        }
    }

    public interface User {
        String getName();
        void setName(String name);

        int getAge();
        void setAge(int age);
    }

    public interface PublicUser {
        public String getName();
        public void setName(String name);

        public int getAge();
        public void setAge(int age);
    }

    interface Ignored {
        public interface DefaultUser {
            default String getName() {
                return "Unknown User";
            }
            default void setName(String name) {
            }
            int getAge();
        }

        public interface StaticUser {
            static String getName() {
                return "Static User";
            }
            int getAge();
            static void setName(String name) {
            }
        }
    }

    public interface ReadOnlyUser {
        String getName();
        int getAge();
    }

    public interface WriteOnlyUser {
        void setName(String name);
        void setAge(int age);
    }

    public interface MixedAccessUser {
        String getName();
        void setName(String name);

        int getAge();
    }

    public interface MixedAccessUser2 {
        String getName();
        void setName(String name);

        void setAge(int age);
    }

    public interface SingleGetterUser {
        String getName();
    }

    public interface SingleSetterUser {
        void setName(String name);
    }

    public interface SinglePropertyUser {
        String getName();
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
            String getName();

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
            void setName(String name);

            /**
             * Gets the age of the user.
             * @return the age of the user
             */
            int getAge();

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
            String getName();

            /**
             * Gets the age of the user.
             * @return the age of the user
             */
            int getAge();
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
            String getName();

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
            void setName(String name);

            /**
             * Gets the age of the user.
             * @return the age of the user
             */
            int getAge();
        }

        /**
         * Interface representing a user with mixed access: both getter and setter for name, and setter only for age.
         */
        public interface MixedAccessUser2 {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
            String getName();

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
            String getName();
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
            String getName();

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
            void setName(String name);
        }
    }

    interface Finder {
        //@FindBy String tag(String name);
        String findTagByName(String name);

        String findTagByAge(byte name);

        String findNameByName(String name);

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
