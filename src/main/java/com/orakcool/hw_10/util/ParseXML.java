package com.orakcool.hw_10.util;

import com.orakcool.hw_10.model.ParseModel;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseXML {
    static public final String FIND_TAG = "(<%s>)([\\s\\S]*?)</%s>";
    static public final String START_TAG = "(<([^/?]*?)>)";
    static public final String SUB_TAG = "(\\S*?)\\s*?=\\s*?\"(\\S*?)\"";
    static private final int GET_PROPERTIES = 2;

    public static String matchTag(String matchSource, String regex, String tag) {
        return matchTag(matchSource, String.format(regex, tag, tag));
    }

    public static String matchTag(String matchSource, String regex) {
        Matcher matcher = Pattern.compile(regex)
                .matcher(matchSource);
        if (matcher.find()) {
            return matcher.group(GET_PROPERTIES);
        }
        return "";
    }

    public static HashMap<String, ParseModel> matchModel(String source) {
        HashMap<String, ParseModel> result = new HashMap<>();
        Pattern pattern = Pattern.compile(START_TAG);
        Matcher matcher = pattern.matcher(source);

        while (matcher.find()) {
            String tag = matcher.group(GET_PROPERTIES).trim();

            if (tag.split("\s").length < 2) {
                String tagBody = matchTag(source, FIND_TAG, tag);
                Matcher subTags = pattern.matcher(tagBody);
                if (subTags.find()) {
                    HashMap<String, ParseModel> subParse = matchModel(tagBody);
                    result.put(tag, new ParseModel(subParse));
                } else {
                    result.put(tag, new ParseModel(tagBody));
                }
            } else {
                String tag1 = tag.split("\s", 2)[0];
                Matcher subTags = Pattern.compile(SUB_TAG).matcher(tag);
                while (subTags.find()) {
                    result.put(tag1 + capitalize(subTags.group(1)), new ParseModel(subTags.group(2)));
                }

                source = source.replace(tag, tag1);
                String tagBody = matchTag(source, FIND_TAG, tag1);

                result.put(tag1, new ParseModel(tagBody));
            }
        }
        return result;
    }

    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
