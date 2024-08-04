package data;

public class InterfaceExtensionPropertiesTestData {

    public interface User {
        String getName();
        void setName(String name);

        int getAge();
        void setAge(int age);
    }


    /**
     public interface User {
     property String name { get; set; }
     property int age { get; }
     }


     public interface User {
     String name { get; set; }
     int age { get; }
     }


     public interface User {
    @Getter @Setter String name;
    @Getter int age;
    }
     */
}
