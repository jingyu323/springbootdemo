
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

/**
 * description
 *
 * @author yanzy
 * @date 2019/1/16 10:32
 */
public class LinuxCpuInternal {

    /**
     * 获取磁盘使用率
     *
     * @return int
     * @author yanzy
     * @version 1.0
     * @date 2019/1/16 15:29
     */
    public static int getDiskUsage() throws Exception {
        double totalHD = 0;
        double usedHD = 0;
        Runtime rt = Runtime.getRuntime();
        // df -hl 查看硬盘空间
        Process p = rt.exec("df -hl");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str = null;
            String[] strArray = null;
            while ((str = in.readLine()) != null) {
                int m = 0;
                strArray = str.split(" ");
                for (String tmp : strArray) {
                    if (tmp.trim().length() == 0)
                        continue;
                    ++m;
                    if (tmp.indexOf("G") != -1) {
                        if (m == 2) {
                            if (!tmp.equals("") && !tmp.equals("0"))
                                totalHD += Double.parseDouble(tmp.substring(0, tmp.length() - 1)) * 1024;
                        }
                        if (m == 3) {
                            if (!tmp.equals("none") && !tmp.equals("0"))
                                usedHD += Double.parseDouble(tmp.substring(0, tmp.length() - 1)) * 1024;
                        }
                    }
                    if (tmp.indexOf("M") != -1) {
                        if (m == 2) {
                            if (!tmp.equals("") && !tmp.equals("0"))
                                totalHD += Double.parseDouble(tmp.substring(0, tmp.length() - 1));
                        }
                        if (m == 3) {
                            if (!tmp.equals("none") && !tmp.equals("0"))
                                usedHD += Double.parseDouble(tmp.substring(0, tmp.length() - 1));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            in.close();
        }
        // 保留2位小数
        double precent = (usedHD / totalHD) * 100;
        BigDecimal b1 = new BigDecimal(precent);
        precent = b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return (int) precent;
    }

    /**
     * 获取cpu使用率
     *
     * @return int
     * @author yanzy
     * @version 1.0
     * @date 2019/1/16 15:31
     */
    public static int getCpuUsage() {
        try {
            Map<?, ?> map1 = LinuxCpuInternal.getCpuinfo();
            Thread.sleep(5 * 1000);
            Map<?, ?> map2 = LinuxCpuInternal.getCpuinfo();

            long user1 = Long.parseLong(map1.get("user").toString());
            long nice1 = Long.parseLong(map1.get("nice").toString());
            long system1 = Long.parseLong(map1.get("system").toString());
            long idle1 = Long.parseLong(map1.get("idle").toString());

            long user2 = Long.parseLong(map2.get("user").toString());
            long nice2 = Long.parseLong(map2.get("nice").toString());
            long system2 = Long.parseLong(map2.get("system").toString());
            long idle2 = Long.parseLong(map2.get("idle").toString());

            long total1 = user1 + system1 + nice1;
            long total2 = user2 + system2 + nice2;
            float total = total2 - total1;

            long totalIdle1 = user1 + nice1 + system1 + idle1;
            long totalIdle2 = user2 + nice2 + system2 + idle2;
            float totalidle = totalIdle2 - totalIdle1;

            float cpusage = (total / totalidle) * 100;
            return (int) cpusage;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 获取CPU使用信息
     *
     * @return java.util.Map
     * @author yanzy
     * @version 1.0
     * @date 2019/1/16 15:31
     */
    public static Map<?, ?> getCpuinfo() {
        InputStreamReader inputs = null;
        BufferedReader buffer = null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            inputs = new InputStreamReader(new FileInputStream("/proc/stat"));
            buffer = new BufferedReader(inputs);
            String line = "";
            while (true) {
                line = buffer.readLine();
                if (line == null) {
                    break;
                }
                if (line.startsWith("cpu")) {
                    StringTokenizer tokenizer = new StringTokenizer(line);
                    List<String> temp = new ArrayList<String>();
                    while (tokenizer.hasMoreElements()) {
                        String value = tokenizer.nextToken();
                        temp.add(value);
                    }
                    map.put("user", temp.get(1));
                    map.put("nice", temp.get(2));
                    map.put("system", temp.get(3));
                    map.put("idle", temp.get(4));
                    map.put("iowait", temp.get(5));
                    map.put("irq", temp.get(6));
                    map.put("softirq", temp.get(7));
                    map.put("stealstolen", temp.get(8));
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                inputs.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return map;
    }


    /**
     * 获取内存使用率
     *
     * @return int
     * @author yanzy
     * @version 1.0
     * @date 2019/1/16 15:32
     */
    public static int getMemoryUsage() {
        Map<String, Object> map = new HashMap<String, Object>();
        InputStreamReader inputs = null;
        BufferedReader buffer = null;
        try {
            inputs = new InputStreamReader(new FileInputStream("/proc/meminfo"));
            buffer = new BufferedReader(inputs);
            String line = "";
            while (true) {
                line = buffer.readLine();
                if (line == null)
                    break;
                int beginIndex = 0;
                int endIndex = line.indexOf(":");
                if (endIndex != -1) {
                    String key = line.substring(beginIndex, endIndex);
                    beginIndex = endIndex + 1;
                    endIndex = line.length();
                    String memory = line.substring(beginIndex, endIndex);
                    String value = memory.replace("kB", "").trim();
                    map.put(key, value);
                }
            }

            long memTotal = Long.parseLong(map.get("MemTotal").toString());
            long memFree = Long.parseLong(map.get("MemFree").toString());
            long memused = memTotal - memFree;
            long buffers = Long.parseLong(map.get("Buffers").toString());
            long cached = Long.parseLong(map.get("Cached").toString());

            double usage = (double) (memused - buffers - cached) / memTotal * 100;
            return (int) usage;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                inputs.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
       int meUse = getMemoryUsage();
        System.out.println("int meUse:"+meUse);
        int diskUsage = getDiskUsage();

        System.out.println("int diskUsage :"+diskUsage);
    }
}


