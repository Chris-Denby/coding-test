import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class FileUtil {
    public Stream<String> readFileToStream(String path) throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(path);
        return new BufferedReader(new InputStreamReader(stream)).lines();
    }
}

