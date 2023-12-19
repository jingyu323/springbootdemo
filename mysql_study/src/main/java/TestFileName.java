import java.io.*;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.*;

public class TestFileName {

    public static void main(String[] args) throws IOException, InterruptedException {

        long start = System.currentTimeMillis();
        String[] cpu = {"/bin/sh", "-c", "convmv -f GBK -t UTF-8 -r --notest /storage/test_file "};
        File test = new File("/storage/test_file");
        for (File file : test.listFiles()) {

            System.out.println(file.getName());
        }
        execCommand(cpu);
        long end = System.currentTimeMillis();


        for (File file : test.listFiles()) {

            System.out.println(file.getName());
        }
        System.out.println(start - end);

    }

    public static String execCommand(String[] commands) throws IOException, InterruptedException {
        Process process = null;
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            process = new ProcessBuilder(commands).start();
            // 获取标准输入流 process.getInputStream()
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            // 等待进程结束
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Command exited with non-zero status code: " + exitCode);


            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to execute command: " + e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (process != null) {
                process.destroy();
            }
        }
        return sb.toString();
    }


}
