package ch.helvetia.zepasweb.ui.tagfunctions;

import javax.faces.context.FacesContext;
import java.util.Locale;

public class TaglibUtil {

    public static String substring(String input, int start, int length) {
        return input.substring(start, length);
    }

    public static Locale locale() {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }

    public static String localeString() {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        if (locale == null) {
            return "de";
        }
        return locale.getLanguage();
    }

    public static String append(String s1, String s2) {
        return s1 + s2;
    }
}
