import lombok.Data;

@Data
public class PersonaReviewerExample {
    private String name;
    private int age;

    public static PersonaReviewerExample from(String name, int age) {
        PersonaReviewerExample example = new PersonaReviewerExample();
        example.setName(name);
        example.setAge(age);
        return example;
    }
}
