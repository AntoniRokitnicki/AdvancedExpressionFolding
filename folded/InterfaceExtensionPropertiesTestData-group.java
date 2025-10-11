package data;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@SuppressWarnings("ALL")
public class InterfaceExtensionPropertiesTestData {

    public interface User {
       [0:" "]String [0:"getN"]ame[0:"()"];
       [1:" "][1:"void"] [1:"setN"]ame[1:"(String name)"];

       [2:" "]int [2:"getA"]ge[2:"()"];
       [3:" "][3:"void"] [3:"setA"]ge[3:"(int age)"];
    }

    public interface PublicUser {
       [4:" "]public String [4:"getN"]ame[4:"()"];
       [5:" "]public [5:"void"] [5:"setN"]ame[5:"(String name)"];

       [6:" "]public int [6:"getA"]ge[6:"()"];
       [7:" "]public [7:"void"] [7:"setA"]ge[7:"(int age)"];
    }

    interface Ignored {
        public interface DefaultUser {
            default String getName() {
                return "Unknown User";
            }
            default void setName(String name) {
            }
           [8:" "]int [8:"getA"]ge[8:"()"];
        }

        public interface StaticUser {
            static String getName() {
                return "Static User";
            }
           [9:" "]int [9:"getA"]ge[9:"()"];
            static void setName(String name) {
            }
        }
    }

    public interface ReadOnlyUser {
       [10:" "]String [10:"getN"]ame[10:"()"];
       [11:" "]int [11:"getA"]ge[11:"()"];
    }

    public interface WriteOnlyUser {
       [12:" "][12:"void"] [12:"setN"]ame[12:"(String name)"];
       [13:" "][13:"void"] [13:"setA"]ge[13:"(int age)"];
    }

    public interface MixedAccessUser {
       [14:" "]String [14:"getN"]ame[14:"()"];
       [15:" "][15:"void"] [15:"setN"]ame[15:"(String name)"];

       [16:" "]int [16:"getA"]ge[16:"()"];
    }

    public interface MixedAccessUser2 {
       [17:" "]String [17:"getN"]ame[17:"()"];
       [18:" "][18:"void"] [18:"setN"]ame[18:"(String name)"];

       [19:" "][19:"void"] [19:"setA"]ge[19:"(int age)"];
    }

    public interface SingleGetterUser {
       [20:" "]String [20:"getN"]ame[20:"()"];
    }

    public interface SingleSetterUser {
       [21:" "][21:"void"] [21:"setN"]ame[21:"(String name)"];
    }

    public interface SinglePropertyUser {
       [22:" "]String [22:"getN"]ame[22:"()"];
       [23:" "][23:"void"] [23:"setN"]ame[23:"(String name)"];
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
           [24:" "]String [24:"getN"]ame[24:"()"];

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           [25:" "][25:"void"] [25:"setN"]ame[25:"(String name)"];

            /**
             * Gets the age of the user.
             * @return the age of the user
             */
           [26:" "]int [26:"getA"]ge[26:"()"];

            /**
             * Sets the age of the user.
             * @param age the age to set
             */
           [27:" "][27:"void"] [27:"setA"]ge[27:"(int age)"];
        }

        /**
         * Interface representing a read-only user with getter methods for name and age.
         */
        public interface ReadOnlyUser {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           [28:" "]String [28:"getN"]ame[28:"()"];

            /**
             * Gets the age of the user.
             * @return the age of the user
             */
           [29:" "]int [29:"getA"]ge[29:"()"];
        }

        /**
         * Interface representing a write-only user with setter methods for name and age.
         */
        public interface WriteOnlyUser {
            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           [30:" "][30:"void"] [30:"setN"]ame[30:"(String name)"];

            /**
             * Sets the age of the user.
             * @param age the age to set
             */
           [31:" "][31:"void"] [31:"setA"]ge[31:"(int age)"];
        }

        /**
         * Interface representing a user with mixed access: both getter and setter for name, and getter only for age.
         */
        public interface MixedAccessUser {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           [32:" "]String [32:"getN"]ame[32:"()"];

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           [33:" "][33:"void"] [33:"setN"]ame[33:"(String name)"];

            /**
             * Gets the age of the user.
             * @return the age of the user
             */
           [34:" "]int [34:"getA"]ge[34:"()"];
        }

        /**
         * Interface representing a user with mixed access: both getter and setter for name, and setter only for age.
         */
        public interface MixedAccessUser2 {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           [35:" "]String [35:"getN"]ame[35:"()"];

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           [36:" "][36:"void"] [36:"setN"]ame[36:"(String name)"];

            /**
             * Sets the age of the user.
             * @param age the age to set
             */
           [37:" "][37:"void"] [37:"setA"]ge[37:"(int age)"];
        }

        /**
         * Interface representing a user with a single getter method for name.
         */
        public interface SingleGetterUser {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           [38:" "]String [38:"getN"]ame[38:"()"];
        }

        /**
         * Interface representing a user with a single setter method for name.
         */
        public interface SingleSetterUser {
            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           [39:" "][39:"void"] [39:"setN"]ame[39:"(String name)"];
        }

        /**
         * Interface representing a user with both getter and setter methods for name.
         */
        public interface SinglePropertyUser {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           [40:" "]String [40:"getN"]ame[40:"()"];

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           [41:" "][41:"void"] [41:"setN"]ame[41:"(String name)"];
        }
    }

    //TODO: nullable support
    interface Finder {
       [42:" "]//@FindBy String tag(String name);
        String [42:"findT"]ag[42:"ByName"](String name);

       [43:" "]String [43:"findT"]ag[43:"ByAge"](byte [43:"name"]);

       [44:" "]String [44:"findN"]ame[44:"ByName"](String name);
    }

    public interface NullableUser {
       [45:" "][45:"@Nullable"][45:"\n        "]Integer[45:" "][45:"getA"]ge[45:"()"];
       [46:" "][46:"void"][46:" "][46:"setA"]ge[46:"(@Nullable int age)"]nt age);
       [47:" "][47:"@Nullable"][47:"\n        "]String[47:" "][47:"getN"]ame[47:"()"];
       [48:" "][48:"void"][48:" "][48:"setN"]ame[48:"(@Nullable String name)"]g name);
    }

    public interface NotNullUser {
       [49:" "][49:"@NotNull()"][49:" "]String[49:" "][49:"getN"]ame[49:"()"];
       [50:" "][50:"void"][50:" "][50:"setN"]ame[50:"(@NotNull String name)"]g name);
       [51:" "]int [51:"getA"]ge[51:"()"];
    }

    /**
     public interface User {
        @Getter String name; <inlay jump to setter?>
        @Setter String name; <inlay jump to getter?>
        @Getter int age;
     }
    **/

}
