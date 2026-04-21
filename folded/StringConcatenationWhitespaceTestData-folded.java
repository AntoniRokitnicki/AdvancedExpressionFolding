package data;

class Subject {
    String method() {
        return "value";
    }
}

public class StringConcatenationWhitespaceTestData {
    String format(Subject subject) {
        return "Abc:${subject.method()}";
    }
}
