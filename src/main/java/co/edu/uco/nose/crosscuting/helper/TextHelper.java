package co.edu.uco.nose.crosscuting.helper;

import java.text.MessageFormat;
import java.util.regex.Pattern;

public final class  TextHelper {

    private static final String EMPTY = "";

    private TextHelper() {
    }

    public static String getDefault() {
        return EMPTY;
    }

    public static String getDefault(final String value) {
        return ObjectHelper.getDefault(value, getDefault());
    }

    public static String getDefaultWithTrim(final String value) {
        return getDefault(value).trim();
    }

    public static boolean isEmpty(final String value) {
        return EMPTY.equals(getDefault(value));
    }

    public static boolean isEmptyWithTrim(final String value) {
        return isEmpty(getDefaultWithTrim(value));
    }

    public static boolean lengthIsValid(final String value, final int min, final int max, final boolean mustApplyTrim) {
        var valueToValidate = mustApplyTrim ? getDefaultWithTrim(value) : getDefault(value);
        var length = valueToValidate.length();
        return length >= min && length <= max;
    }

    public static boolean lenthIsValidWithTrim(final String value, final int min, final int max) {
        return lengthIsValid(getDefaultWithTrim(value), min, max, true);
    }

    public static boolean matchesRegex(final String text, final String regex) {
        if (isEmpty(text) || isEmpty(regex)) {
            return false;
        }
        return Pattern.matches(regex, text);
    }

    public static String format(final String message, final String... params) {
        if (isEmpty(message)) {
            return getDefault();
        }
        return MessageFormat.format(message, (Object[]) params);
    }

}
