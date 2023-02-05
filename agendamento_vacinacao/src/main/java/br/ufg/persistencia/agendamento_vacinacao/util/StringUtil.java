package br.ufg.persistencia.agendamento_vacinacao.util;



import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    private StringUtil() {}
    public static boolean isNullOrEmpty(Object object) {
        return object == null
                || (object.getClass() == String.class && object.toString().isEmpty())
                || (object instanceof Collection && ((Collection) object).isEmpty());
    }
}
