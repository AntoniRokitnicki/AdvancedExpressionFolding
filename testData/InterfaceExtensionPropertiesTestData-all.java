<fold text='📦' expand='false'>package</fold> data;

@SuppressWarnings("ALL")
public <fold text='🏛️' expand='false'>class</fold> InterfaceExtensionPropertiesTestData {

    <fold text='//...' expand='true'>//TODO: default & static methods examples - all should be ignored
    //TODO: Nullable examples</fold>

    public <fold text='🖥️' expand='false'>interface</fold> User <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
       <fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;

       <fold text='@Getter ' expand='true'> </fold><fold text='🔢' expand='false'>int</fold> <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
       <fold text='@Setter ' expand='true'> </fold><fold text='int' expand='true'>void</fold> <fold text='a' expand='true'>setA</fold>ge<fold text='' expand='true'>(<fold text='🔢' expand='false'>int</fold> age)</fold>;
    }</fold>

    public <fold text='🖥️' expand='false'>interface</fold> PublicUser <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>public String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
       <fold text='@Setter ' expand='true'> </fold>public <fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;

       <fold text='@Getter ' expand='true'> </fold>public <fold text='🔢' expand='false'>int</fold> <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
       <fold text='@Setter ' expand='true'> </fold>public <fold text='int' expand='true'>void</fold> <fold text='a' expand='true'>setA</fold>ge<fold text='' expand='true'>(<fold text='🔢' expand='false'>int</fold> age)</fold>;
    }</fold>


    public <fold text='🖥️' expand='false'>interface</fold> ReadOnlyUser <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
       <fold text='@Getter ' expand='true'> <fold text='🔢' expand='false'></fold>int</fold> <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
    }</fold>

    public <fold text='🖥️' expand='false'>interface</fold> WriteOnlyUser <fold text='{...}' expand='true'>{
       <fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;
       <fold text='@Setter ' expand='true'> </fold><fold text='int' expand='true'>void</fold> <fold text='a' expand='true'>setA</fold>ge<fold text='' expand='true'>(<fold text='🔢' expand='false'>int</fold> age)</fold>;
    }</fold>

    public <fold text='🖥️' expand='false'>interface</fold> MixedAccessUser <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
       <fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;

       <fold text='@Getter ' expand='true'> </fold><fold text='🔢' expand='false'>int</fold> <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
    }</fold>

    public <fold text='🖥️' expand='false'>interface</fold> MixedAccessUser2 <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
       <fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;

       <fold text='@Setter ' expand='true'> </fold><fold text='int' expand='true'>void</fold> <fold text='a' expand='true'>setA</fold>ge<fold text='' expand='true'>(<fold text='🔢' expand='false'>int</fold> age)</fold>;
    }</fold>

    public <fold text='🖥️' expand='false'>interface</fold> SingleGetterUser <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
    }</fold>

    public <fold text='🖥️' expand='false'>interface</fold> SingleSetterUser <fold text='{...}' expand='true'>{
       <fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;
    }</fold>

    public <fold text='🖥️' expand='false'>interface</fold> SinglePropertyUser <fold text='{...}' expand='true'>{
       <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
       <fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;
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
           <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;

            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
           <fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;

            <fold text='/** Gets the age of the user. ...*/' expand='true'>/**
             * Gets the age of the user.
             * @return the age of the user
             */</fold>
           <fold text='@Getter ' expand='true'> </fold><fold text='🔢' expand='false'>int</fold> <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;

            <fold text='/** Sets the age of the user. ...*/' expand='true'>/**
             * Sets the age of the user.
             * @param age the age to set
             */</fold>
           <fold text='@Setter ' expand='true'> </fold><fold text='int' expand='true'>void</fold> <fold text='a' expand='true'>setA</fold>ge<fold text='' expand='true'>(<fold text='🔢' expand='false'>int</fold> age)</fold>;
        }</fold>

        <fold text='/** Interface representing a read-only user with getter methods for name and age. */' expand='true'>/**
         * Interface representing a read-only user with getter methods for name and age.
         */</fold>
        public <fold text='🖥️' expand='false'>interface</fold> ReadOnlyUser <fold text='{...}' expand='true'>{
            <fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
           <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;

            <fold text='/** Gets the age of the user. ...*/' expand='true'>/**
             * Gets the age of the user.
             * @return the age of the user
             */</fold>
           <fold text='@Getter ' expand='true'> </fold><fold text='🔢' expand='false'>int</fold> <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
        }</fold>

        <fold text='/** Interface representing a write-only user with setter methods for name and age. */' expand='true'>/**
         * Interface representing a write-only user with setter methods for name and age.
         */</fold>
        public <fold text='🖥️' expand='false'>interface</fold> WriteOnlyUser <fold text='{...}' expand='true'>{
            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
           <fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;

            <fold text='/** Sets the age of the user. ...*/' expand='true'>/**
             * Sets the age of the user.
             * @param age the age to set
             */</fold>
           <fold text='@Setter ' expand='true'> </fold><fold text='int' expand='true'>void</fold> <fold text='a' expand='true'>setA</fold>ge<fold text='' expand='true'>(<fold text='🔢' expand='false'>int</fold> age)</fold>;
        }</fold>

        <fold text='/** Interface representing a user with mixed access: both getter and setter for name, and getter only for age. */' expand='true'>/**
         * Interface representing a user with mixed access: both getter and setter for name, and getter only for age.
         */</fold>
        public <fold text='🖥️' expand='false'>interface</fold> MixedAccessUser <fold text='{...}' expand='true'>{
            <fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
           <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;

            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
           <fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;

            <fold text='/** Gets the age of the user. ...*/' expand='true'>/**
             * Gets the age of the user.
             * @return the age of the user
             */</fold>
           <fold text='@Getter ' expand='true'> </fold><fold text='🔢' expand='false'>int</fold> <fold text='a' expand='true'>getA</fold>ge<fold text='' expand='true'>()</fold>;
        }</fold>

        <fold text='/** Interface representing a user with mixed access: both getter and setter for name, and setter only for age. */' expand='true'>/**
         * Interface representing a user with mixed access: both getter and setter for name, and setter only for age.
         */</fold>
        public <fold text='🖥️' expand='false'>interface</fold> MixedAccessUser2 <fold text='{...}' expand='true'>{
            <fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
           <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;

            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
           <fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;

            <fold text='/** Sets the age of the user. ...*/' expand='true'>/**
             * Sets the age of the user.
             * @param age the age to set
             */</fold>
           <fold text='@Setter ' expand='true'> </fold><fold text='int' expand='true'>void</fold> <fold text='a' expand='true'>setA</fold>ge<fold text='' expand='true'>(<fold text='🔢' expand='false'>int</fold> age)</fold>;
        }</fold>

        <fold text='/** Interface representing a user with a single getter method for name. */' expand='true'>/**
         * Interface representing a user with a single getter method for name.
         */</fold>
        public <fold text='🖥️' expand='false'>interface</fold> SingleGetterUser <fold text='{...}' expand='true'>{
            <fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
           <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;
        }</fold>

        <fold text='/** Interface representing a user with a single setter method for name. */' expand='true'>/**
         * Interface representing a user with a single setter method for name.
         */</fold>
        public <fold text='🖥️' expand='false'>interface</fold> SingleSetterUser <fold text='{...}' expand='true'>{
            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
           <fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;
        }</fold>

        <fold text='/** Interface representing a user with both getter and setter methods for name. */' expand='true'>/**
         * Interface representing a user with both getter and setter methods for name.
         */</fold>
        public <fold text='🖥️' expand='false'>interface</fold> SinglePropertyUser <fold text='{...}' expand='true'>{
            <fold text='/** Gets the name of the user. ...*/' expand='true'>/**
             * Gets the name of the user.
             * @return the name of the user
             */</fold>
           <fold text='@Getter ' expand='true'> </fold>String <fold text='n' expand='true'>getN</fold>ame<fold text='' expand='true'>()</fold>;

            <fold text='/** Sets the name of the user. ...*/' expand='true'>/**
             * Sets the name of the user.
             * @param name the name to set
             */</fold>
           <fold text='@Setter ' expand='true'> </fold><fold text='String' expand='true'>void</fold> <fold text='n' expand='true'>setN</fold>ame<fold text='' expand='true'>(String name)</fold>;
        }</fold>
    }</fold>

    <fold text='🖥️' expand='false'>interface</fold> Finder <fold text='{...}' expand='true'>{
       <fold text='@Finder ' expand='true'> </fold>String <fold text='t' expand='true'>findT</fold>ag<fold text='' expand='true'>ByName</fold>(String name);
        //@Finder String tag(String name);

       <fold text='@Finder ' expand='true'> </fold>String <fold text='t' expand='true'>findT</fold>ag<fold text='' expand='true'>ByAge</fold>(<fold text='💾' expand='false'>byte</fold> <fold text='age' expand='true'>name</fold>);

       <fold text='@Finder ' expand='true'> </fold>String <fold text='n' expand='true'>findN</fold>ame<fold text='' expand='true'>ByName</fold>(String name);

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
