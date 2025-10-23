package data;

interface UnimplementedOperations {
    void perform();
    int calculate(int left, int right);
}

public class PseudoAnnotationsNotFullyImplementedTestData implements UnimplementedOperations {

    @Override
    public void perform() {
        throw new NotImplementedException();
    }

    @Override
    public int calculate(int left, int right) {
        throw new NotImplementedException();
    }
}

class NotImplementedException extends RuntimeException {
}
