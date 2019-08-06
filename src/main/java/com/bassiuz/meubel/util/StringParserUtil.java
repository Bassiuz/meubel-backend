package com.bassiuz.meubel.util;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;

public class StringParserUtil {

    public static String getStringBetweenTwoStrings(String source, String start, String end) {
        if (source.contains(start) && source.contains(end))
        {
            String result = source.substring(source.indexOf(start) + start.length(), source.length());
            return result.substring(0, result.indexOf(end));
        }

        return "";
    }

}