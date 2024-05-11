package data;

import static data.ConstTestData.EOrder.SECOND;

public class ConstTestData {

    econst EOrder ORDER1 = EOrder.FIRST;
    econst EOrder ORDER2 = SECOND;

    const PUBLIC_STATIC_FINAL_VAR = "";
    const PRIVATE_STATIC_FINAL_VAR = "";
    const PROTECTED_STATIC_FINAL_VAR = "";
    const DEFAULT_STATIC_FINAL_VAR = "";
    const String DEFAULT_STATIC_FINAL_VAR_REF = DEFAULT_STATIC_FINAL_VAR;

    const VERSION = 1;
    const int VERSION_REF = VERSION;


    enum EOrder {
        FIRST,
        SECOND;
    }

}

