import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.*;

class FileUtilTest {

    FileUtil fileUtilUnderTest = new FileUtil();
    String filePath = "programming-task-example-data.log";

    @SneakyThrows
    @Test
    void readFileToStream_returns_stream() {
        //when
        Stream<String> logStream = fileUtilUnderTest.readFileToStream(filePath);
        List logList = logStream.collect(Collectors.toList());
        //then
        assertThat(logStream).isNotNull();
        assertThat(logList.size()).isGreaterThan(0);
    }

}