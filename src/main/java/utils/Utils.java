package utils;

import java.util.regex.Pattern;

public class Utils {

    public static String capitalizeFully(String text) {
        String regex = "\\b(.)(.*?)\\b";
        String result = Pattern.compile(regex).matcher(text).replaceAll(
                matche -> matche.group(1).toUpperCase() + matche.group(2)
        );

        return result;
    }
}
