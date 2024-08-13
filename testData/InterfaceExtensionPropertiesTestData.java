package data;

import <fold text='...' expand='false'>org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;</fold>

@SuppressWarnings("ALL")
public class InterfaceExtensionPropertiesTestData {

    interface TODO <fold text='{...}' expand='true'>{
        public interface NullableUser <fold text='{...}' expand='true'>{
            int getAge();
            <fold text='' expand='false'>@Nullable</fold>
            String<fold text='? ' expand='false'> </fold>getName();
            void setName(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>name);
        }</fold>
        public interface NotNullUser <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>@NotNull</fold>
            String<fold text='!! ' expand='false'> </fold>getName();
            void setName(<fold text='' expand='false'>@NotNull</fold><fold text='' expand='false'> </fold>String<fold text='!! ' expand='false'> </fold>name);
            int getAge();
        }</fold>

    }</fold>

    public interface User <fold text='{...}' expand='true'>{
        String getName();
        void setName(String name);

        int getAge();
        void setAge(int age);
    }</fold>

    public interface PublicUser <fold text='{...}' expand='true'>{
        public String getName();
        public void setName(String name);

        public int getAge();
        public void setAge(int age);
    }</fold>

    interface Ignored <fold text='{...}' expand='true'>{
        public interface DefaultUser <fold text='{...}' expand='true'>{
            default String getName()<fold text=' { ' expand='false'> {
                </fold>return "Unknown User";<fold text=' }' expand='false'>
            }</fold>
            default void setName(String name) <fold text='{}' expand='true'>{
            }</fold>
            int getAge();
        }</fold>

        public interface StaticUser <fold text='{...}' expand='true'>{
            static String getName()<fold text=' { ' expand='false'> {
                </fold>return "Static User";<fold text=' }' expand='false'>
            }</fold>
            int getAge();
            static void setName(String name) <fold text='{}' expand='true'>{
            }</fold>
        }</fold>
    }</fold>

    public interface ReadOnlyUser <fold text='{...}' expand='true'>{
        String getName();
        int getAge();
    }</fold>

    public interface WriteOnlyUser <fold text='{...}' expand='true'>{
        void setName(String name);
        void setAge(int age);
    }</fold>

    public interface MixedAccessUser <fold text='{...}' expand='true'>{
        String getName();
        void setName(String name);

        int getAge();
    }</fold>

    public interface MixedAccessUser2 <fold text='{...}' expand='true'>{
        String getName();
        void setName(String name);

        void setAge(int age);
    }</fold>

    public interface SingleGetterUser <fold text='{...}' expand='true'>{
        String getName();
    }</fold>

    public interface SingleSetterUser <fold text='{...}' expand='true'>{
        void setName(String name);
    }</fold>

    public interface SinglePropertyUser <fold text='{...}' expand='true'>{
        String getName();
        void setName(String name);
    }</fold>

    class Javadoced <fold text='{...}' expand='true'>{
        <fold text='/** Interface representing a user with both getter and setter methods for name and age. */' expand='true'>/**
         * Interface representing a user with both getter and setter methods for name and age.
         */</fold>
        public interface User <fold text='{...}' expand='true'>{
            <fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
            String getName();

            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
            void setName(String name);

            <fold text='/** Gets the age of the user. ...*/' expand='true'>/**
             * Gets the age of the user.
             * @return the age of the user
             */</fold>
            int getAge();

            <fold text='/** Sets the age of the user. ...*/' expand='true'>/**
             * Sets the age of the user.
             * @param age the age to set
             */</fold>
            void setAge(int age);
        }</fold>

        <fold text='/** Interface representing a read-only user with getter methods for name and age. */' expand='true'>/**
         * Interface representing a read-only user with getter methods for name and age.
         */</fold>
        public interface ReadOnlyUser <fold text='{...}' expand='true'>{
            <fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
            String getName();

            <fold text='/** Gets the age of the user. ...*/' expand='true'>/**
             * Gets the age of the user.
             * @return the age of the user
             */</fold>
            int getAge();
        }</fold>

        <fold text='/** Interface representing a write-only user with setter methods for name and age. */' expand='true'>/**
         * Interface representing a write-only user with setter methods for name and age.
         */</fold>
        public interface WriteOnlyUser <fold text='{...}' expand='true'>{
            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
            void setName(String name);

            <fold text='/** Sets the age of the user. ...*/' expand='true'>/**
             * Sets the age of the user.
             * @param age the age to set
             */</fold>
            void setAge(int age);
        }</fold>

        <fold text='/** Interface representing a user with mixed access: both getter and setter for name, and getter only for age. */' expand='true'>/**
         * Interface representing a user with mixed access: both getter and setter for name, and getter only for age.
         */</fold>
        public interface MixedAccessUser <fold text='{...}' expand='true'>{
            <fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
            String getName();

            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
            void setName(String name);

            <fold text='/** Gets the age of the user. ...*/' expand='true'>/**
             * Gets the age of the user.
             * @return the age of the user
             */</fold>
            int getAge();
        }</fold>

        <fold text='/** Interface representing a user with mixed access: both getter and setter for name, and setter only for age. */' expand='true'>/**
         * Interface representing a user with mixed access: both getter and setter for name, and setter only for age.
         */</fold>
        public interface MixedAccessUser2 <fold text='{...}' expand='true'>{
            <fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
            String getName();

            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
            void setName(String name);

            <fold text='/** Sets the age of the user. ...*/' expand='true'>/**
             * Sets the age of the user.
             * @param age the age to set
             */</fold>
            void setAge(int age);
        }</fold>

        <fold text='/** Interface representing a user with a single getter method for name. */' expand='true'>/**
         * Interface representing a user with a single getter method for name.
         */</fold>
        public interface SingleGetterUser <fold text='{...}' expand='true'>{
            <fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
            String getName();
        }</fold>

        <fold text='/** Interface representing a user with a single setter method for name. */' expand='true'>/**
         * Interface representing a user with a single setter method for name.
         */</fold>
        public interface SingleSetterUser <fold text='{...}' expand='true'>{
            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
            void setName(String name);
        }</fold>

        <fold text='/** Interface representing a user with both getter and setter methods for name. */' expand='true'>/**
         * Interface representing a user with both getter and setter methods for name.
         */</fold>
        public interface SinglePropertyUser <fold text='{...}' expand='true'>{
            <fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
            String getName();

            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
            void setName(String name);
        }</fold>
    }</fold>

    interface Finder <fold text='{...}' expand='true'>{
        //@Finder String tag(String name);
        String findTagByName(String name);

        String findTagByAge(byte name);

        String findNameByName(String name);

    }</fold>

    <fold text='/**     public interface User { ...*/' expand='true'>/**
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
    **/</fold>

}
