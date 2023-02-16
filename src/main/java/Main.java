import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        FileUtil fileUtil = new FileUtil();

        String filePath = "programming-task-example-data.log";
        try {
            List logList = LogUtil.buildLogList(fileUtil.readFileToStream(filePath));
            System.out.println(ReportUtil.generateReport(logList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
