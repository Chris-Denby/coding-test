import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.*;

class LogUtilTest {

    Stream<String> logStream = null;
    LogUtil logUtilUnderTest = new LogUtil();
    String filePath = "programming-task-example-data.log";

    @Test
    void buildLog_builds_log_from_string() {
        //given
        String testLog = "177.71.128.21 - - [10/Jul/2018:22:21:28 +0200] \"GET /intranet-analytics/ HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0 (X11; U; Linux x86_64; fr-FR) AppleWebKit/534.7 (KHTML, like Gecko) Epiphany/2.30.6 Safari/534.7\"";
        //when
        Log log = logUtilUnderTest.buildLog(testLog);
        //then
        assertThat(log.getIp()).isEqualTo("177.71.128.21");
        assertThat(log.getUrl()).isEqualTo("/intranet-analytics/");
        assertThat(log.getDate().toString()).isEqualTo("2018-07-10");
    }

    @Test
    void buildLogList_creates_logs() {
        //given
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(filePath);
        logStream = new BufferedReader(new InputStreamReader(stream)).lines();
        //when
        List<Log> logList = logUtilUnderTest.buildLogList(logStream);
        //then
        assertThat(logList.size()).isEqualTo(23);
    }
}