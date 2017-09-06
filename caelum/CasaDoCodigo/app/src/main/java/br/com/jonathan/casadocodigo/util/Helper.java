package br.com.jonathan.casadocodigo.util;

import java.util.List;

public class Helper {

    public static String join(String delimiter, List<String> list) {
        StringBuilder values = new StringBuilder();

        for (String l : list) {
            values.append(l).append(delimiter);
        }

        return values.toString();
    }

}
