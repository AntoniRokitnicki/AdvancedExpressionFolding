package data;

public class ToBuilderCopyTestData {

    public void copies(Person person, Person source) <fold text='{...}' expand='true'>{
        var renamed = person<fold text='.copy(' expand='false'>.toBuilder()</fold>
<fold text='            name = ' expand='false'>                .name(</fold>"Alice"<fold text=',' expand='false'>)</fold>
<fold text='            age = ' expand='false'>                .age(</fold>31<fold text='' expand='false'>)</fold>
<fold text='        )' expand='false'>                .build()</fold>;
        Person older = person<fold text='.copy(age = ' expand='false'>.toBuilder().age(</fold>person.getAge() + 1<fold text=')' expand='false'>).build()</fold>;
        Person mixed = person<fold text='.copy(' expand='false'>.toBuilder()</fold><fold text='name = ' expand='false'>.name(</fold>"Bob"<fold text=',' expand='false'>)</fold>
<fold text='            age = ' expand='false'>                .age(</fold>40<fold text=')' expand='false'>).build()</fold>;
        Person mapped = person<fold text='.copy(name = ' expand='false'>.toBuilder()
                .name(</fold>source.getName()<fold text=')' expand='false'>)
                .build()</fold>;
        Person titled = person<fold text='.copy(name = ' expand='false'>.toBuilder().name(</fold>"Dr. " + person.getName()<fold text=')' expand='false'>).build()</fold>;
        Person commented = person<fold text='.copy(' expand='false'>.toBuilder()</fold>
                // a comment keeps the chain on its lines
<fold text='            name = ' expand='false'>                .name(</fold>"Carol"<fold text='' expand='false'>)</fold>
<fold text='        )' expand='false'>                .build()</fold>;
    }</fold>

    public void notFolded(Person person) <fold text='{...}' expand='true'>{
        Person.PersonBuilder unfinished = person.toBuilder().name("Alice");
        Person unchanged = person.toBuilder().build();
        Person greeted = person.toBuilder().greet().name("Alice").build();
        Person shouted = person.toBuilder().nameUpperCase("alice").build();
        Person fresh = Person.builder().name("Alice").build();
        String text = person.toBuilder().name("Alice").toString();
    }</fold>

    static class Person <fold text='{...}' expand='true'>{
        private final String name;
        private final int age;

        Person(String name, int age) <fold text='{...}' expand='true'>{
            this.name = name;
            this.age = age;
        }</fold>

        public static PersonBuilder builder()<fold text=' { ' expand='false'> {
            </fold>return new PersonBuilder();<fold text=' }' expand='false'>
        }</fold>

        public PersonBuilder toBuilder()<fold text=' { ' expand='false'> {
            </fold>return new PersonBuilder().name(name).age(age);<fold text=' }' expand='false'>
        }</fold>

        public String getName()<fold text=' { ' expand='false'> {
            </fold>return name;<fold text=' }' expand='false'>
        }</fold>

        public int getAge()<fold text=' { ' expand='false'> {
            </fold>return age;<fold text=' }' expand='false'>
        }</fold>

        static class PersonBuilder <fold text='{...}' expand='true'>{
            private String name;
            private int age;

            public PersonBuilder name(String name) <fold text='{...}' expand='true'>{
                this.name = name;
                return this;
            }</fold>

            public PersonBuilder age(int age) <fold text='{...}' expand='true'>{
                this.age = age;
                return this;
            }</fold>

            public PersonBuilder nameUpperCase(String name) <fold text='{...}' expand='true'>{
                this.name = name.toUpperCase();
                return this;
            }</fold>

            public PersonBuilder greet()<fold text=' { ' expand='false'> {
                </fold>return this;<fold text=' }' expand='false'>
            }</fold>

            public Person build()<fold text=' { ' expand='false'> {
                </fold>return new Person(name, age);<fold text=' }' expand='false'>
            }</fold>
        }</fold>
    }</fold>
}
