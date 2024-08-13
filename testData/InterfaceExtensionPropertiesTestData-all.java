<fold text='📦' expand='false'>package</fold> data;

<fold text='🚢' expand='false'>import</fold> <fold text='...' expand='false'>org.jetbrains.annotations.NotNull;

<fold text='🚢' expand='false'>import</fold> javax.annotation.Nullable;</fold>

@SuppressWarnings("ALL")
public <fold text='🏛️' expand='false'>class</fold> InterfaceExtensionPropertiesTestData {

    <fold text='🖥️' expand='false'>interface</fold> TODO <fold text='{...}' expand='true'>{
        public <fold text='🖥️' expand='false'>interface</fold> NullableUser <fold text='{...}' expand='true'>{
            <fold text='🔢' expand='false'>int</fold> getAge();
            <fold text='' expand='false'>@Nullable</fold>
            String<fold text='? ' expand='false'> </fold>getName();
            <fold text='💀' expand='false'>void</fold> setName(<fold text='' expand='false'>@Nullable</fold><fold text='' expand='false'> </fold>String<fold text='? ' expand='false'> </fold>name);
        }</fold>
        public <fold text='🖥️' expand='false'>interface</fold> NotNullUser <fold text='{...}' expand='true'>{
            <fold text='' expand='false'>@NotNull</fold>
            String<fold text='!! ' expand='false'> </fold>getName();
            <fold text='💀' expand='false'>void</fold> setName(<fold text='' expand='false'>@NotNull</fold><fold text='' expand='false'> </fold>String<fold text='!! ' expand='false'> </fold>name);
            <fold text='🔢' expand='false'>int</fold> getAge();
        }</fold>

    }</fold>

    public <fold text='🖥️' expand='false'>interface</fold> User <fold text='{...}' expand='true'>{
        String getName();
        <fold text='💀' expand='false'>void</fold> setName(String name);

        <fold text='🔢' expand='false'>int</fold> getAge();
        <fold text='💀' expand='false'>void</fold> setAge(<fold text='🔢' expand='false'>int</fold> age);
    }</fold>

    public <fold text='🖥️' expand='false'>interface</fold> PublicUser <fold text='{...}' expand='true'>{
        public String getName();
        public <fold text='💀' expand='false'>void</fold> setName(String name);

        public <fold text='🔢' expand='false'>int</fold> getAge();
        public <fold text='💀' expand='false'>void</fold> setAge(<fold text='🔢' expand='false'>int</fold> age);
    }</fold>

    <fold text='🖥️' expand='false'>interface</fold> Ignored <fold text='{...}' expand='true'>{
        public <fold text='🖥️' expand='false'>interface</fold> DefaultUser <fold text='{...}' expand='true'>{
            default String getName()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>"Unknown User"<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>
            default <fold text='💀' expand='false'>void</fold> setName(String name) <fold text='{}' expand='true'>{
            }</fold>
            <fold text='🔢' expand='false'>int</fold> getAge();
        }</fold>

        public <fold text='🖥️' expand='false'>interface</fold> StaticUser <fold text='{...}' expand='true'>{
            <fold text='⚡' expand='false'>static</fold> String getName()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>"Static User"<fold text='' expand='true'>;</fold><fold text='' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>
            <fold text='🔢' expand='false'>int</fold> getAge();
            <fold text='⚡' expand='false'>static</fold> <fold text='💀' expand='false'>void</fold> setName(String name) <fold text='{}' expand='true'>{
            }</fold>
        }</fold>
    }</fold>

    public <fold text='🖥️' expand='false'>interface</fold> ReadOnlyUser <fold text='{...}' expand='true'>{
        String getName();
        <fold text='🔢' expand='false'>int</fold> getAge();
    }</fold>

    public <fold text='🖥️' expand='false'>interface</fold> WriteOnlyUser <fold text='{...}' expand='true'>{
        <fold text='💀' expand='false'>void</fold> setName(String name);
        <fold text='💀' expand='false'>void</fold> setAge(<fold text='🔢' expand='false'>int</fold> age);
    }</fold>

    public <fold text='🖥️' expand='false'>interface</fold> MixedAccessUser <fold text='{...}' expand='true'>{
        String getName();
        <fold text='💀' expand='false'>void</fold> setName(String name);

        <fold text='🔢' expand='false'>int</fold> getAge();
    }</fold>

    public <fold text='🖥️' expand='false'>interface</fold> MixedAccessUser2 <fold text='{...}' expand='true'>{
        String getName();
        <fold text='💀' expand='false'>void</fold> setName(String name);

        <fold text='💀' expand='false'>void</fold> setAge(<fold text='🔢' expand='false'>int</fold> age);
    }</fold>

    public <fold text='🖥️' expand='false'>interface</fold> SingleGetterUser <fold text='{...}' expand='true'>{
        String getName();
    }</fold>

    public <fold text='🖥️' expand='false'>interface</fold> SingleSetterUser <fold text='{...}' expand='true'>{
        <fold text='💀' expand='false'>void</fold> setName(String name);
    }</fold>

    public <fold text='🖥️' expand='false'>interface</fold> SinglePropertyUser <fold text='{...}' expand='true'>{
        String getName();
        <fold text='💀' expand='false'>void</fold> setName(String name);
    }</fold>

    <fold text='🏛️' expand='false'>class</fold> Javadoced <fold text='{...}' expand='true'>{
        <fold text='/** Interface representing a user with both getter and setter methods for name and age. */' expand='true'>/**
         * Interface representing a user with both getter and setter methods for name and age.
         */</fold>
        public <fold text='🖥️' expand='false'>interface</fold> User <fold text='{...}' expand='true'>{
            <fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
            String getName();

            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
            <fold text='💀' expand='false'>void</fold> setName(String name);

            <fold text='/** Gets the age of the user. ...*/' expand='true'>/**
             * Gets the age of the user.
             * @return the age of the user
             */</fold>
            <fold text='🔢' expand='false'>int</fold> getAge();

            <fold text='/** Sets the age of the user. ...*/' expand='true'>/**
             * Sets the age of the user.
             * @param age the age to set
             */</fold>
            <fold text='💀' expand='false'>void</fold> setAge(<fold text='🔢' expand='false'>int</fold> age);
        }</fold>

        <fold text='/** Interface representing a read-only user with getter methods for name and age. */' expand='true'>/**
         * Interface representing a read-only user with getter methods for name and age.
         */</fold>
        public <fold text='🖥️' expand='false'>interface</fold> ReadOnlyUser <fold text='{...}' expand='true'>{
            <fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
            String getName();

            <fold text='/** Gets the age of the user. ...*/' expand='true'>/**
             * Gets the age of the user.
             * @return the age of the user
             */</fold>
            <fold text='🔢' expand='false'>int</fold> getAge();
        }</fold>

        <fold text='/** Interface representing a write-only user with setter methods for name and age. */' expand='true'>/**
         * Interface representing a write-only user with setter methods for name and age.
         */</fold>
        public <fold text='🖥️' expand='false'>interface</fold> WriteOnlyUser <fold text='{...}' expand='true'>{
            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
            <fold text='💀' expand='false'>void</fold> setName(String name);

            <fold text='/** Sets the age of the user. ...*/' expand='true'>/**
             * Sets the age of the user.
             * @param age the age to set
             */</fold>
            <fold text='💀' expand='false'>void</fold> setAge(<fold text='🔢' expand='false'>int</fold> age);
        }</fold>

        <fold text='/** Interface representing a user with mixed access: both getter and setter for name, and getter only for age. */' expand='true'>/**
         * Interface representing a user with mixed access: both getter and setter for name, and getter only for age.
         */</fold>
        public <fold text='🖥️' expand='false'>interface</fold> MixedAccessUser <fold text='{...}' expand='true'>{
            <fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
            String getName();

            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
            <fold text='💀' expand='false'>void</fold> setName(String name);

            <fold text='/** Gets the age of the user. ...*/' expand='true'>/**
             * Gets the age of the user.
             * @return the age of the user
             */</fold>
            <fold text='🔢' expand='false'>int</fold> getAge();
        }</fold>

        <fold text='/** Interface representing a user with mixed access: both getter and setter for name, and setter only for age. */' expand='true'>/**
         * Interface representing a user with mixed access: both getter and setter for name, and setter only for age.
         */</fold>
        public <fold text='🖥️' expand='false'>interface</fold> MixedAccessUser2 <fold text='{...}' expand='true'>{
            <fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
            String getName();

            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
            <fold text='💀' expand='false'>void</fold> setName(String name);

            <fold text='/** Sets the age of the user. ...*/' expand='true'>/**
             * Sets the age of the user.
             * @param age the age to set
             */</fold>
            <fold text='💀' expand='false'>void</fold> setAge(<fold text='🔢' expand='false'>int</fold> age);
        }</fold>

        <fold text='/** Interface representing a user with a single getter method for name. */' expand='true'>/**
         * Interface representing a user with a single getter method for name.
         */</fold>
        public <fold text='🖥️' expand='false'>interface</fold> SingleGetterUser <fold text='{...}' expand='true'>{
            <fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
            String getName();
        }</fold>

        <fold text='/** Interface representing a user with a single setter method for name. */' expand='true'>/**
         * Interface representing a user with a single setter method for name.
         */</fold>
        public <fold text='🖥️' expand='false'>interface</fold> SingleSetterUser <fold text='{...}' expand='true'>{
            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
            <fold text='💀' expand='false'>void</fold> setName(String name);
        }</fold>

        <fold text='/** Interface representing a user with both getter and setter methods for name. */' expand='true'>/**
         * Interface representing a user with both getter and setter methods for name.
         */</fold>
        public <fold text='🖥️' expand='false'>interface</fold> SinglePropertyUser <fold text='{...}' expand='true'>{
            <fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
            String getName();

            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
            <fold text='💀' expand='false'>void</fold> setName(String name);
        }</fold>
    }</fold>

    <fold text='🖥️' expand='false'>interface</fold> Finder <fold text='{...}' expand='true'>{
        //@Finder String tag(String name);
        String findTagByName(String name);

        String findTagByAge(<fold text='💾' expand='false'>byte</fold> name);

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
