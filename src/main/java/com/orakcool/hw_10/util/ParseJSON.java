package com.orakcool.hw_10.util;

import com.orakcool.hw_10.model.ParseModel;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseJSON {

    private static final String MAIN_JSON_TEST = "\\[\\s+\\{([\\s\\S]+)}\\s+\\]";
    private static final String BODY_JSON = "\"([\\s\\S].*)\":[\\s]*\"([\\s\\S].*)\"";
    private static final String SUBCLASS_JSON = "\"([\\s\\S].*)\":[\\s]+\\{([\\s\\S]*)\\}";

    public static HashMap<String, ParseModel> matchModel(String source) {
        HashMap<String, ParseModel> result = new HashMap<>();

        Matcher matcher = Pattern.compile(SUBCLASS_JSON)
                .matcher(source);
        while (matcher.find()) {
            result.put(matcher.group(1).trim(), new ParseModel(matchModel(matcher.group(2).trim())));
            source = source.replace(matcher.group(0), "");
        }

        matcher = Pattern.compile(BODY_JSON)
                .matcher(source);
        while (matcher.find()) {
            result.put(matcher.group(1).trim(), new ParseModel(matcher.group(2).trim()));
        }
        return result;
    }

    private static String jsonValidate(String json) {
        Matcher matcher = Pattern.compile(MAIN_JSON_TEST)
                .matcher(json);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid JSON source: " + json);
        }
        return matcher.group(1).trim();
    }

    public static HashMap<String, ParseModel> getModel(String source) {
        source = jsonValidate(source);

        return matchModel(source);
    }
}
