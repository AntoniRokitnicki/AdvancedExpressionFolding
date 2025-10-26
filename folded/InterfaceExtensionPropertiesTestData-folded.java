package data;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@SuppressWarnings("ALL")
public class InterfaceExtensionPropertiesTestData {

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

    interface Ignored {
        public interface DefaultUser {
            default String getName() {
                return "Unknown User";
            }
            default void setName(String name) {
            }
           @Getter int age;
        }

        public interface StaticUser {
            static String getName() {
                return "Static User";
            }
           @Getter int age;
            static void setName(String name) {
            }
        }
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

        public interface User {

           @Getter String name;

           @Setter String name;

           @Getter int age;

           @Setter int age;
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
    }

    //TODO: nullable support
    interface Finder {
       @FindBy //@FindBy String tag(String name);
        String tag(String name);

       @FindBy String tag(byte age);

       @FindBy String name(String name);
    }

    public interface NullableUser {
       @Getter Integer? age;
       @Setter int? age;
       @Getter String? name;
       @Setter String? name;
    }

    public interface NotNullUser {
       @Getter String!! name;
       @Setter String!! name;
       @Getter int age;
    }

}
