package data;

import static data.ConstTestData.EOrder.SECOND;

public class ConstTestData {

    static final EOrder ORDER1 = EOrder.FIRST;
    static final EOrder ORDER2 = SECOND;

    public static final String PUBLIC_STATIC_FINAL_VAR = "";
    private static final String PRIVATE_STATIC_FINAL_VAR = "";
    protected static final String PROTECTED_STATIC_FINAL_VAR = "";
    static final String DEFAULT_STATIC_FINAL_VAR = "";
    static final String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

    static final int VERSION = 1;
    static final int VERSION_REF = VERSION;


    enum EOrder {
        FIRST,
        SECOND;
    }

}

