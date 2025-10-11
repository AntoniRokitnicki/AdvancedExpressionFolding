package data;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@SuppressWarnings("ALL")
public class InterfaceExtensionPropertiesTestData {

    public interface User {
       [0:" "]String [0:"getN"]ame[0:"()"];
       [0:" "][0:"void"] [0:"setN"]ame[0:"(String name)"];

       [0:" "]int [0:"getA"]ge[0:"()"];
       [0:" "][0:"void"] [0:"setA"]ge[0:"(int age)"];
    }

    public interface PublicUser {
       [0:" "]public String [0:"getN"]ame[0:"()"];
       [0:" "]public [0:"void"] [0:"setN"]ame[0:"(String name)"];

       [0:" "]public int [0:"getA"]ge[0:"()"];
       [0:" "]public [0:"void"] [0:"setA"]ge[0:"(int age)"];
    }

    interface Ignored {
        public interface DefaultUser {
            default String getName() {
                return "Unknown User";
            }
            default void setName(String name) {
            }
           [0:" "]int [0:"getA"]ge[0:"()"];
        }

        public interface StaticUser {
            static String getName() {
                return "Static User";
            }
           [0:" "]int [0:"getA"]ge[0:"()"];
            static void setName(String name) {
            }
        }
    }

    public interface ReadOnlyUser {
       [0:" "]String [0:"getN"]ame[0:"()"];
       [0:" "]int [0:"getA"]ge[0:"()"];
    }

    public interface WriteOnlyUser {
       [0:" "][0:"void"] [0:"setN"]ame[0:"(String name)"];
       [0:" "][0:"void"] [0:"setA"]ge[0:"(int age)"];
    }

    public interface MixedAccessUser {
       [0:" "]String [0:"getN"]ame[0:"()"];
       [0:" "][0:"void"] [0:"setN"]ame[0:"(String name)"];

       [0:" "]int [0:"getA"]ge[0:"()"];
    }

    public interface MixedAccessUser2 {
       [0:" "]String [0:"getN"]ame[0:"()"];
       [0:" "][0:"void"] [0:"setN"]ame[0:"(String name)"];

       [0:" "][0:"void"] [0:"setA"]ge[0:"(int age)"];
    }

    public interface SingleGetterUser {
       [0:" "]String [0:"getN"]ame[0:"()"];
    }

    public interface SingleSetterUser {
       [0:" "][0:"void"] [0:"setN"]ame[0:"(String name)"];
    }

    public interface SinglePropertyUser {
       [0:" "]String [0:"getN"]ame[0:"()"];
       [0:" "][0:"void"] [0:"setN"]ame[0:"(String name)"];
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
           [0:" "]String [0:"getN"]ame[0:"()"];

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           [0:" "][0:"void"] [0:"setN"]ame[0:"(String name)"];

            /**
             * Gets the age of the user.
             * @return the age of the user
             */
           [0:" "]int [0:"getA"]ge[0:"()"];

            /**
             * Sets the age of the user.
             * @param age the age to set
             */
           [0:" "][0:"void"] [0:"setA"]ge[0:"(int age)"];
        }

        /**
         * Interface representing a read-only user with getter methods for name and age.
         */
        public interface ReadOnlyUser {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           [0:" "]String [0:"getN"]ame[0:"()"];

            /**
             * Gets the age of the user.
             * @return the age of the user
             */
           [0:" "]int [0:"getA"]ge[0:"()"];
        }

        /**
         * Interface representing a write-only user with setter methods for name and age.
         */
        public interface WriteOnlyUser {
            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           [0:" "][0:"void"] [0:"setN"]ame[0:"(String name)"];

            /**
             * Sets the age of the user.
             * @param age the age to set
             */
           [0:" "][0:"void"] [0:"setA"]ge[0:"(int age)"];
        }

        /**
         * Interface representing a user with mixed access: both getter and setter for name, and getter only for age.
         */
        public interface MixedAccessUser {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           [0:" "]String [0:"getN"]ame[0:"()"];

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           [0:" "][0:"void"] [0:"setN"]ame[0:"(String name)"];

            /**
             * Gets the age of the user.
             * @return the age of the user
             */
           [0:" "]int [0:"getA"]ge[0:"()"];
        }

        /**
         * Interface representing a user with mixed access: both getter and setter for name, and setter only for age.
         */
        public interface MixedAccessUser2 {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           [0:" "]String [0:"getN"]ame[0:"()"];

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           [0:" "][0:"void"] [0:"setN"]ame[0:"(String name)"];

            /**
             * Sets the age of the user.
             * @param age the age to set
             */
           [0:" "][0:"void"] [0:"setA"]ge[0:"(int age)"];
        }

        /**
         * Interface representing a user with a single getter method for name.
         */
        public interface SingleGetterUser {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           [0:" "]String [0:"getN"]ame[0:"()"];
        }

        /**
         * Interface representing a user with a single setter method for name.
         */
        public interface SingleSetterUser {
            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           [0:" "][0:"void"] [0:"setN"]ame[0:"(String name)"];
        }

        /**
         * Interface representing a user with both getter and setter methods for name.
         */
        public interface SinglePropertyUser {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           [0:" "]String [0:"getN"]ame[0:"()"];

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           [0:" "][0:"void"] [0:"setN"]ame[0:"(String name)"];
        }
    }

    //TODO: nullable support
    interface Finder {
       [0:" "]//@FindBy String tag(String name);
        String [0:"findT"]ag[0:"ByName"](String name);

       [0:" "]String [0:"findT"]ag[0:"ByAge"](byte [0:"name"]);

       [0:" "]String [0:"findN"]ame[0:"ByName"](String name);
    }

    public interface NullableUser {
       [0:" "][0:"@Nullable"][0:"
        "]Integer[0:" "][0:"getA"]ge[0:"()"];
       [0:" "][0:"void"][0:" "][0:"setA"]ge[0:"(@Nullable int age)"]{[0:"@Nullable"]};
       [0:" "][0:"@Nullable"][0:"
        "]String[0:" "][0:"getN"]ame[0:"()"];
       [0:" "][0:"void"][0:" "][0:"setN"]ame[0:"(@Nullable String name)"]{[0:"@Nullable"]};
    }

    public interface NotNullUser {
       [0:" "][0:"@NotNull()"][0:" "]String[0:" "][0:"getN"]ame[0:"()"];
       [0:" "][0:"void"][0:" "][0:"setN"]ame[0:"(@NotNull String name)"]{[0:"@NotNull"]};
       [0:" "]int [0:"getA"]ge[0:"()"];
    }

    /**
     public interface User {
        @Getter String name; <inlay jump to setter?>
        @Setter String name; <inlay jump to getter?>
        @Getter int age;
     }
    **/

}
