package data;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@SuppressWarnings("ALL")
public class InterfaceExtensionPropertiesTestData {

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

        public interface User {

            String getName();

            void setName(String name);

            int getAge();

            void setAge(int age);
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
    }

    //TODO: nullable support
    interface Finder {
        //@FindBy String tag(String name);
        String findTagByName(String name);

        String findTagByAge(byte name);

        String findNameByName(String name);
    }

    public interface NullableUser {
        @Nullable
        Integer getAge();
        void setAge(@Nullable int age);
        @Nullable
        String getName();
        void setName(@Nullable String name);
    }

    public interface NotNullUser {
        @NotNull() String getName();
        void setName(@NotNull String name);
        int getAge();
    }

}
