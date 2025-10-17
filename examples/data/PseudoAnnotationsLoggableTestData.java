package data;

public class PseudoAnnotationsLoggableTestData {

    public String conditionalReturn(boolean flag) {
        if (flag) {
            return "yes";
        } else {
            return "no";
        }
    }

    public void loopWithBreak(int limit) {
        for (int i = 0; i < limit; i++) {
            if (i > 10) {
                break;
            }
        }
    }

    public void loopWithContinue(int[] values) {
        for (int value : values) {
            if (value < 0) {
                continue;
            }
            System.out.println(value);
        }
    }

    public void throwException() {
        throw new IllegalStateException("fail");
    }

    public int returnFromSwitch(int value) {
        switch (value) {
            case 0:
                return 0;
            case 1:
                return 1;
            default:
                return -1;
        }
    }

    public void nestedReturns(boolean a, boolean b) {
        if (a) {
            if (b) {
                return;
            }
            return;
        }
        return;
    }
}
