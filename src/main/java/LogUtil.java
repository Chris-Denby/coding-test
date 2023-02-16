import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogUtil {

    public static Log buildLog(String line) {
        Pattern ip = Pattern.compile("\\b(?:[0-9]{1,3}\\.){3}[0-9]{1,3}\\b");
        Pattern url = Pattern.compile("(?<=GET|POST|PATCH|DELETE|PUT/)(.*)(?= HTTP)");
        Pattern date = Pattern.compile("(?<=\\[)(.*)*(?=\\])");
        Matcher ipMatcher = ip.matcher(line);
        Matcher urlMatcher = url.matcher(line);
        Matcher dateMatcher = date.matcher(line);
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy", Locale.ENGLISH);

        return Log.builder()
                .url(urlMatcher.find() ? urlMatcher.group().trim() : null)
                .ip(ipMatcher.find() ? ipMatcher.group() : null)
                .date(dateMatcher.find() ? LocalDate.parse(dateMatcher.group().substring(0,dateMatcher.group().indexOf(':')), dtFormat) : null)
                .build();
    }

    public static List buildLogList(Stream<String> fileLines) {
        return fileLines
                .map((line)-> buildLog(line))
                .collect(Collectors.toList());
    }
}
