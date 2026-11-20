package data;

public class ToBuilderCopyTestData {

    public void copies(Person person, Person source) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>var</fold> renamed = person<fold text='.copy(' expand='false'>.toBuilder()</fold>
<fold text='            name = ' expand='false'>                .name(</fold>"Alice"<fold text=',' expand='false'>)</fold>
<fold text='            age = ' expand='false'>                .age(</fold>31<fold text='' expand='false'>)</fold>
<fold text='        )' expand='false'>                .build()</fold>;
        <fold text='val' expand='false'>Person</fold> older = person<fold text='.copy(age = ' expand='false'>.toBuilder().age(</fold>person.<fold text='age' expand='false'>getAge()</fold> + 1<fold text=')' expand='false'>).build()</fold>;
        <fold text='val' expand='false'>Person</fold> mixed = person<fold text='.copy(' expand='false'>.toBuilder()</fold><fold text='name = ' expand='false'>.name(</fold>"Bob"<fold text=',' expand='false'>)</fold>
<fold text='            age = ' expand='false'>                .age(</fold>40<fold text=')' expand='false'>).build()</fold>;
        <fold text='val' expand='false'>Person</fold> mapped = person<fold text='.copy(name = ' expand='false'>.toBuilder()
                .name(</fold>source<fold text='<<' expand='false'>.getName()</fold><fold text=')' expand='false'>)
                .build()</fold>;
        <fold text='val' expand='false'>Person</fold> titled = person.toBuilder().name("Dr. <fold text='${' expand='false'>" + </fold>person.<fold text='name' expand='false'>getName()</fold><fold text='}")' expand='false'>)</fold>.build();
        <fold text='val' expand='false'>Person</fold> commented = person<fold text='.copy(' expand='false'>.toBuilder()</fold>
                // a comment keeps the chain on its lines
<fold text='            name = ' expand='false'>                .name(</fold>"Carol"<fold text='' expand='false'>)</fold>
<fold text='        )' expand='false'>                .build()</fold>;
    }</fold>

    public void notFolded(Person person) <fold text='{...}' expand='true'>{
        <fold text='val' expand='false'>Person.PersonBuilder</fold> unfinished = person.toBuilder().name("Alice");
        <fold text='val' expand='false'>Person</fold> unchanged = person.toBuilder().build();
        <fold text='val' expand='false'>Person</fold> greeted = person.toBuilder().greet().name("Alice").build();
        <fold text='val' expand='false'>Person</fold> shouted = person.toBuilder().nameUpperCase("alice").build();
        <fold text='val' expand='false'>Person</fold> fresh = Person.builder().name("Alice").build();
        <fold text='val' expand='false'>String</fold> text = person.toBuilder().name("Alice").toString();
    }</fold>

    <fold text='@HasBuilder @LightValue s' expand='false'>s</fold>tatic class Person <fold text='{...}' expand='true'>{
        private final String name;
        private final int age;<fold text='' expand='false'>

        </fold><fold text='' expand='false'>Person(String name, int age) <fold text='{...}' expand='true'>{
            this.name = <fold text='<<' expand='false'>name</fold>;
            this.age = <fold text='<<' expand='false'>age</fold>;
        }</fold></fold>

        public static PersonBuilder builder()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new PersonBuilder()<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold>

        public PersonBuilder toBuilder()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new PersonBuilder().name(name).age(age)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public String getName()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>name<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold><fold text='' expand='false'>

        </fold><fold text='' expand='false'>public int getAge()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
            </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>age<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
        </fold>}</fold></fold>

        static class PersonBuilder <fold text='{...}' expand='true'>{
            private String name;
            private int age;

            public PersonBuilder name(String name) <fold text='{...}' expand='true'>{
                this.name = <fold text='<<' expand='false'>name</fold>;
                return this;
            }</fold>

            public PersonBuilder age(int age) <fold text='{...}' expand='true'>{
                this.age = <fold text='<<' expand='false'>age</fold>;
                return this;
            }</fold>

            public PersonBuilder nameUpperCase(String name) <fold text='{...}' expand='true'>{
                this.name = name.toUpperCase();
                return this;
            }</fold>

            public PersonBuilder greet()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>this<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>

            public Person build()<fold text=' { ' expand='false'> {<fold text=' ' expand='true'>
                </fold></fold><fold text='' expand='true'>return</fold><fold text='' expand='true'> </fold>new Person(name, age)<fold text='' expand='true'>;</fold><fold text=' ' expand='true'><fold text=' }' expand='false'>
            </fold>}</fold>
        }</fold>
    }</fold>
}
