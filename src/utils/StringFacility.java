package utils;

/**
 * Created by rinku on 10/20/15.
 */
public class StringFacility {
    public static String join(Object[] array, String sep) {
        StringBuilder result = new StringBuilder();
        for (int i = 0, il = array.length; i < il; i++) {
            if (i > 0)
                result.append(sep);
            result.append(array[i].toString());
        }
        return result.toString();
    }
}

