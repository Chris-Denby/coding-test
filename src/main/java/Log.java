import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Log implements Comparable {

    @Builder.Default
    private String ip = null;
    @Builder.Default
    private String url = null;
    @Builder.Default
    private LocalDate date = null;

    @Override
    public String toString() {
        return date + " " + ip + " " + url;
    }

    @Override
    public int compareTo(Object otherLog ) {
        return this.ip.compareTo(((Log) otherLog).ip);
    }
}
