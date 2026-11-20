package data;

public class ToBuilderCopyTestData {

    public void copies(Person person, Person source) {
        var renamed = person.toBuilder()
                .name("Alice")
                .age(31)
                .build();
        Person older = person.toBuilder().age(person.getAge() + 1).build();
        Person mixed = person.toBuilder().name("Bob")
                .age(40).build();
        Person mapped = person.toBuilder()
                .name(source.getName())
                .build();
        Person titled = person.toBuilder().name("Dr. " + person.getName()).build();
        Person commented = person.toBuilder()
                // a comment keeps the chain on its lines
                .name("Carol")
                .build();
    }

    public void notFolded(Person person) {
        Person.PersonBuilder unfinished = person.toBuilder().name("Alice");
        Person unchanged = person.toBuilder().build();
        Person greeted = person.toBuilder().greet().name("Alice").build();
        Person shouted = person.toBuilder().nameUpperCase("alice").build();
        Person fresh = Person.builder().name("Alice").build();
        String text = person.toBuilder().name("Alice").toString();
    }

    static class Person {
        private final String name;
        private final int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public static PersonBuilder builder() {
            return new PersonBuilder();
        }

        public PersonBuilder toBuilder() {
            return new PersonBuilder().name(name).age(age);
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        static class PersonBuilder {
            private String name;
            private int age;

            public PersonBuilder name(String name) {
                this.name = name;
                return this;
            }

            public PersonBuilder age(int age) {
                this.age = age;
                return this;
            }

            public PersonBuilder nameUpperCase(String name) {
                this.name = name.toUpperCase();
                return this;
            }

            public PersonBuilder greet() {
                return this;
            }

            public Person build() {
                return new Person(name, age);
            }
        }
    }
}
