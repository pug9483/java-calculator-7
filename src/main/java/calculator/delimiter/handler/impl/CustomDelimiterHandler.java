package calculator.delimiter.handler.impl;

import calculator.delimiter.handler.DelimiterHandler;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CustomDelimiterHandler implements DelimiterHandler {

    @Override
    public List<String> split(String str) {
        String delimiter = extractDelimiter(str);

        str = str.substring(str.indexOf("\n") + 1);

        if (!str.matches("\\d+(?:" + Pattern.quote(delimiter) + "\\d+)*")) {
            throw new IllegalArgumentException("형식이 일치하지 않습니다");
        }

        return Arrays.stream(str.split(Pattern.quote(delimiter)))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isSupport(String str) {
        return str.contains("//") && str.contains("\n");
    }

    public String extractDelimiter(String s) {
        Pattern pattern = Pattern.compile("//(.*?)\\n");
        Matcher matcher = pattern.matcher(s);

        if(matcher.find()) return matcher.group(1);

        throw new IllegalArgumentException("구분자를 찾을 수 없습니다.");
    }
}
