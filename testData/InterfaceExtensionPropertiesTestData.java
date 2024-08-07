package data;

@SuppressWarnings("ALL")
public class InterfaceExtensionPropertiesTestData {

    //TODO: default & static methods examples - all should be ignored
    public interface User <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
        void setName(String name);

       <fold text='@Getter ' expand='true'> </fold>int <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
        void setAge(int age);
    }</fold>

    public interface PublicUser <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>public String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
        public void setName(String name);

       <fold text='@Getter ' expand='true'> </fold>public int <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
        public void setAge(int age);
    }</fold>


    public interface ReadOnlyUser <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
       <fold text='@Getter ' expand='true'> </fold>int <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
    }</fold>

    public interface WriteOnlyUser <fold text='{...}' expand='true'>{
        void setName(String name);
        void setAge(int age);
    }</fold>

    public interface MixedAccessUser <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
        void setName(String name);

       <fold text='@Getter ' expand='true'> </fold>int <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
    }</fold>

    public interface MixedAccessUser2 <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
        void setName(String name);

        void setAge(int age);
    }</fold>

    public interface SingleGetterUser <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
    }</fold>

    public interface SingleSetterUser <fold text='{...}' expand='true'>{
        void setName(String name);
    }</fold>

    public interface SinglePropertyUser <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
        void setName(String name);
    }</fold>

    class Javadoced <fold text='{...}' expand='true'>{
        <fold text='/** Interface representing a user with both getter and setter methods for name and age. */' expand='true'>/**
         * Interface representing a user with both getter and setter methods for name and age.
         */</fold>
        public interface User <fold text='{...}' expand='true'>{
           <fold text='@Getter ' expand='true'> </fold><fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
            String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;

            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
            void setName(String name);

           <fold text='@Getter ' expand='true'> </fold><fold text='/** Gets the age of the user. ...*/' expand='true'>/**
             * Gets the age of the user.
             * @return the age of the user
             */</fold>
            int <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;

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
           <fold text='@Getter ' expand='true'> </fold><fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
            String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;

           <fold text='@Getter ' expand='true'> </fold><fold text='/** Gets the age of the user. ...*/' expand='true'>/**
             * Gets the age of the user.
             * @return the age of the user
             */</fold>
            int <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
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
           <fold text='@Getter ' expand='true'> </fold><fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
            String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;

            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
            void setName(String name);

           <fold text='@Getter ' expand='true'> </fold><fold text='/** Gets the age of the user. ...*/' expand='true'>/**
             * Gets the age of the user.
             * @return the age of the user
             */</fold>
            int <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
        }</fold>

        <fold text='/** Interface representing a user with mixed access: both getter and setter for name, and setter only for age. */' expand='true'>/**
         * Interface representing a user with mixed access: both getter and setter for name, and setter only for age.
         */</fold>
        public interface MixedAccessUser2 <fold text='{...}' expand='true'>{
           <fold text='@Getter ' expand='true'> </fold><fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
            String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;

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
           <fold text='@Getter ' expand='true'> </fold><fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
            String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
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
           <fold text='@Getter ' expand='true'> </fold><fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
            String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;

            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
            void setName(String name);
        }</fold>
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
