package co.edu.uco.nose.crosscuting.helper;

public final class BooleanHelper {

    private static final Boolean DEFAULT = false;

    private BooleanHelper() {
    }

    public static Boolean getDefault() {
        return DEFAULT;
    }

    public static boolean getDefault(final Boolean value) {
        return ObjectHelper.getDefault(value, getDefault());
    }

}
