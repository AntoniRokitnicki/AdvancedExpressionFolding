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
            [8:"default"][8:" "][8:"String"][8:" "]getName[8:"()"] {[8:"\n                "][8:"return"][8:" "]"Unknown User"[8:";"][8:"\n            "]}
            default void setName(String name) {
            }
           [9:" "]int [9:"getA"]ge[9:"()"];
        }

        public interface StaticUser {
            static String getName() {[10:"\n                "][10:"return"][10:" "]"Static User"[10:";"][10:"\n            "]}
           [11:" "]int [11:"getA"]ge[11:"()"];
            static void setName(String name) {
            }
        }
    }

    public interface ReadOnlyUser {
       [12:" "]String [12:"getN"]ame[12:"()"];
       [13:" "]int [13:"getA"]ge[13:"()"];
    }

    public interface WriteOnlyUser {
       [14:" "][14:"void"] [14:"setN"]ame[14:"(String name)"];
       [15:" "][15:"void"] [15:"setA"]ge[15:"(int age)"];
    }

    public interface MixedAccessUser {
       [16:" "]String [16:"getN"]ame[16:"()"];
       [17:" "][17:"void"] [17:"setN"]ame[17:"(String name)"];

       [18:" "]int [18:"getA"]ge[18:"()"];
    }

    public interface MixedAccessUser2 {
       [19:" "]String [19:"getN"]ame[19:"()"];
       [20:" "][20:"void"] [20:"setN"]ame[20:"(String name)"];

       [21:" "][21:"void"] [21:"setA"]ge[21:"(int age)"];
    }

    public interface SingleGetterUser {
       [22:" "]String [22:"getN"]ame[22:"()"];
    }

    public interface SingleSetterUser {
       [23:" "][23:"void"] [23:"setN"]ame[23:"(String name)"];
    }

    public interface SinglePropertyUser {
       [24:" "]String [24:"getN"]ame[24:"()"];
       [25:" "][25:"void"] [25:"setN"]ame[25:"(String name)"];
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
           [26:" "]String [26:"getN"]ame[26:"()"];

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           [27:" "][27:"void"] [27:"setN"]ame[27:"(String name)"];

            /**
             * Gets the age of the user.
             * @return the age of the user
             */
           [28:" "]int [28:"getA"]ge[28:"()"];

            /**
             * Sets the age of the user.
             * @param age the age to set
             */
           [29:" "][29:"void"] [29:"setA"]ge[29:"(int age)"];
        }

        /**
         * Interface representing a read-only user with getter methods for name and age.
         */
        public interface ReadOnlyUser {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           [30:" "]String [30:"getN"]ame[30:"()"];

            /**
             * Gets the age of the user.
             * @return the age of the user
             */
           [31:" "]int [31:"getA"]ge[31:"()"];
        }

        /**
         * Interface representing a write-only user with setter methods for name and age.
         */
        public interface WriteOnlyUser {
            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           [32:" "][32:"void"] [32:"setN"]ame[32:"(String name)"];

            /**
             * Sets the age of the user.
             * @param age the age to set
             */
           [33:" "][33:"void"] [33:"setA"]ge[33:"(int age)"];
        }

        /**
         * Interface representing a user with mixed access: both getter and setter for name, and getter only for age.
         */
        public interface MixedAccessUser {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           [34:" "]String [34:"getN"]ame[34:"()"];

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           [35:" "][35:"void"] [35:"setN"]ame[35:"(String name)"];

            /**
             * Gets the age of the user.
             * @return the age of the user
             */
           [36:" "]int [36:"getA"]ge[36:"()"];
        }

        /**
         * Interface representing a user with mixed access: both getter and setter for name, and setter only for age.
         */
        public interface MixedAccessUser2 {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           [37:" "]String [37:"getN"]ame[37:"()"];

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           [38:" "][38:"void"] [38:"setN"]ame[38:"(String name)"];

            /**
             * Sets the age of the user.
             * @param age the age to set
             */
           [39:" "][39:"void"] [39:"setA"]ge[39:"(int age)"];
        }

        /**
         * Interface representing a user with a single getter method for name.
         */
        public interface SingleGetterUser {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           [40:" "]String [40:"getN"]ame[40:"()"];
        }

        /**
         * Interface representing a user with a single setter method for name.
         */
        public interface SingleSetterUser {
            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           [41:" "][41:"void"] [41:"setN"]ame[41:"(String name)"];
        }

        /**
         * Interface representing a user with both getter and setter methods for name.
         */
        public interface SinglePropertyUser {
            /**
             * Gets the name of the user.
             * @return the name of the user
             */
           [42:" "]String [42:"getN"]ame[42:"()"];

            /**
             * Sets the name of the user.
             * @param name the name to set
             */
           [43:" "][43:"void"] [43:"setN"]ame[43:"(String name)"];
        }
    }

    //TODO: nullable support
    interface Finder {
       [44:" "]//@FindBy String tag(String name);
        String [44:"findT"]ag[44:"ByName"](String name);

       [45:" "]String [45:"findT"]ag[45:"ByAge"](byte [45:"name"]);

       [46:" "]String [46:"findN"]ame[46:"ByName"](String name);
    }

    public interface NullableUser {
       [47:" "][47:"@Nullable"][47:"\n        "]Integer[47:" "][47:"getA"]ge[47:"()"];
       [48:" "][48:"void"][48:" "][48:"setA"]ge[48:"(@Nullable int age)"]nt age);
       [49:" "][49:"@Nullable"][49:"\n        "]String[49:" "][49:"getN"]ame[49:"()"];
       [50:" "][50:"void"][50:" "][50:"setN"]ame[50:"(@Nullable String name)"]g name);
    }

    public interface NotNullUser {
       [51:" "][51:"@NotNull()"][51:" "]String[51:" "][51:"getN"]ame[51:"()"];
       [52:" "][52:"void"][52:" "][52:"setN"]ame[52:"(@NotNull String name)"]g name);
       [53:" "]int [53:"getA"]ge[53:"()"];
    }

    /**
     public interface User {
        @Getter String name; <inlay jump to setter?>
        @Setter String name; <inlay jump to getter?>
        @Getter int age;
     }
    **/

}
