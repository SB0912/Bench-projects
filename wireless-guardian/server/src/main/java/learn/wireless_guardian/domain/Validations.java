package learn.wireless_guardian.domain;

import learn.wireless_guardian.models.Business;
import learn.wireless_guardian.models.ResellerOrg;

public class Validations {

    public static boolean isNullOrBlank(String value) {
        return value == null || value.isBlank();
    }

    public static boolean isNullOrBlank(int value) {
        return value <= 0;
    }

    public static boolean isNullOrBlank(double value) {
        return value <= -181;
    }

    public static boolean isNullOrBlank(boolean value) {
        return false;
    }

}
