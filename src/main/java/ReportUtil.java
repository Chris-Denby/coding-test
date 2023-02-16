import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportUtil {

    public static List<String> getUniqueIP(List<Log> logList) {
        Map<String, String> map = logList.stream().collect(Collectors
                .toMap(
                        Log::getIp,
                        Log::getUrl,
                        (existing, replacement) -> replacement));
        return map.keySet().stream().distinct().collect(Collectors.toList());
    }

    public static List<String> getUniqueURL(List<Log> logList) {
        Map<String, String> map = logList.stream().collect(Collectors
                .toMap(
                        Log::getUrl,
                        Log::getIp,
                        (existing, replacement) -> replacement));
        return map.keySet().stream().distinct().collect(Collectors.toList());
    }

    public static List getTop3AddressesByViews(List<Log> logList) {
        List<String> uniqueList = getUniqueURL(logList);
        Map<String, Integer> ranked = new HashMap<>();

        //for each unique url
        //find its frequency in the logList
        //count its views
        //add the url and views to a map
        //sort the map, descending and limit to top 3

        uniqueList.stream().forEach((uniqueUrl)-> {
            int views = Collections.frequency(logList.stream().map(log->log.getUrl()).collect(Collectors.toList()),uniqueUrl);
            ranked.put(uniqueUrl, views);
        });

        return ranked.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public static List getTop3IPsByActivity(List<Log> logList) {
        //over the log period - which IP address made the most requests
        Map<String, Integer> ranked = new HashMap<>();
        List<String> requestIPs = logList.stream().map(log->log.getIp()).collect(Collectors.toList());
        getUniqueIP(logList).forEach((ip)->{
            int count = Collections.frequency(requestIPs,ip);
            ranked.put(ip,count);
        });

        return ranked.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public static String generateReport(List logList) {

        StringBuilder reportString = new StringBuilder("********* REPORT *********");

        reportString.append("\n\nNumber of unique IP addresses is: ");
        reportString.append("\n"+getUniqueIP(logList).size());
        reportString.append("\n\nTop 3 most visited URL's are:");
        getTop3AddressesByViews(logList).forEach(url->{
            reportString.append("\n"+url);
        });

        reportString.append("\n\nTop 3 most active IP's are:");
        getTop3IPsByActivity(logList).forEach(ip->{
            reportString.append("\n"+ip);
        });
        return reportString.toString();
    }
}
