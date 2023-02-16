import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class ReportUtilTest {

    static List<Log> logList;

    @BeforeAll
    static void setup() {
        logList = Arrays.asList(new Log[]{
                Log.builder().url("/").ip("1.1.1.1").date(LocalDate.now()).build(),
                Log.builder().url("http://google.com").ip("1.1.1.1").date(LocalDate.now()).build(),
                Log.builder().url("/hello-world").ip("1.1.1.2").date(LocalDate.now()).build(),
                Log.builder().url("/hello-world").ip("1.1.1.3").date(LocalDate.now()).build(),
        });
    }

    @Test
    void getUniqueIP_removes_duplicates() {
        //given
        List<String> uniqueIPList;
        //when
        uniqueIPList = ReportUtil.getUniqueIP(logList);
        //then
        assertThat(Collections.frequency(uniqueIPList,"1.1.1.1")).isEqualTo(1);

    }

    @Test
    void getUniqueURL_removes_duplicates() {
        //given
        List<String> uniqueURLList;
        //when
        uniqueURLList = ReportUtil.getUniqueURL(logList);
        //then
        assertThat(Collections.frequency(uniqueURLList,"/hello-world")).isEqualTo(1);
    }

    @Test
    void getTop3AddressesByViews_returns_3_or_less() {
        //given
        List<String> top3AddressesList;
        //when
        top3AddressesList = ReportUtil.getTop3AddressesByViews(logList);
        //then
        assertThat(top3AddressesList.size()).isLessThanOrEqualTo(3);
    }

    @Test
    void getTop3IPsByActivity_returns_3_or_lesss() {
        //given
        List<String> top3IPList;
        //when
        top3IPList = ReportUtil.getTop3IPsByActivity(logList);
        //then
        assertThat(top3IPList.size()).isLessThanOrEqualTo(3);
    }

    @Test
    void generateReport_builds_string() {
        //given
        String report;
        //when
        report = ReportUtil.generateReport(logList);
        //then
        assertThat(report.lines().count()).isGreaterThanOrEqualTo(7);
    }
}