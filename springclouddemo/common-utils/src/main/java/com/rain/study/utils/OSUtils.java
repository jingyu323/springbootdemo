package com.rain.study.utils;

import com.sun.management.OperatingSystemMXBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OSUtils {

    private static Logger logger = LoggerFactory.getLogger(OSUtils.class);

    public static void init() {
        try {
            SystemInfo systemInfo = new SystemInfo();

            OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
            MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

            /* ============================== 操作系统信息 ============================= */
            logger.info("");
            logger.info("");
            logger.info("============================== 操作系统信息 =============================");
            // 操作系统
            String osName = System.getProperty("os.name");

            // 获得线程总数
            ThreadGroup parentThread;
            parentThread = Thread.currentThread().getThreadGroup();
            while (parentThread.getParent() != null) {
                parentThread = parentThread.getParent();
            }

            int totalThread = parentThread.activeCount();

            logger.info("操作系统: " + osName);
            logger.info("程序启动时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(new Date(ManagementFactory.getRuntimeMXBean().getStartTime())));
            logger.info("pid: " + System.getProperty("PID"));
            logger.info("总线程数: " + totalThread);

            // 磁盘使用情况
            File[] files = File.listRoots();
            for (File file : files) {
                String total = new DecimalFormat("#.#").format(file.getTotalSpace() * 1.0 / 1024 / 1024 / 1024) + "G";
                String free = new DecimalFormat("#.#").format(file.getFreeSpace() * 1.0 / 1024 / 1024 / 1024) + "G";
                String un = new DecimalFormat("#.#").format(file.getUsableSpace() * 1.0 / 1024 / 1024 / 1024) + "G";
                String path = file.getPath();
                logger.info(path + "总: " + total + ",可用空间: " + un + ",空闲空间: " + free);
                // logger.info("=============================================");
                logger.info("---------------------------------------------");
            }

            /* ============================== 堆内存信息 ============================= */
            logger.info("");
            logger.info("");
            logger.info("============================== 堆内存信息 =============================");
            // 椎内存使用情况
            MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();

            // 初始的总内存(B)
            long initTotalMemorySize = memoryUsage.getInit();

            // 最大可用内存(B)
            long maxMemorySize = memoryUsage.getMax();

            // 已使用的内存(B)
            long usedMemorySize = memoryUsage.getUsed();

            logger.info(
                    "初始的总内存(JVM): " + new DecimalFormat("#.#").format(initTotalMemorySize * 1.0 / 1024 / 1024) + "M");
            logger.info(
                    "最大可用内存(JVM): " + new DecimalFormat("#.#").format(maxMemorySize * 1.0 / 1024 / 1024) + "M");
            logger.info(
                    "已使用的内存(JVM): " + new DecimalFormat("#.#").format(usedMemorySize * 1.0 / 1024 / 1024) + "M");

            /* ============================== 物理内存信息 ============================= */
            logger.info("");
            logger.info("");
            logger.info("============================== 物理内存信息 =============================");
            // 总的物理内存(B)
            long totalMemorySizeByte = osmxb.getTotalPhysicalMemorySize();
            long totalMemorySizeByte2 = systemInfo.getHardware().getMemory().getTotal();

            // 总的物理内存
            String totalMemorySize =
                    new DecimalFormat("#.##").format(totalMemorySizeByte / 1024.0 / 1024 / 1024) + "G";
            String totalMemorySize2 =
                    new DecimalFormat("#.##").format(totalMemorySizeByte2 / 1024.0 / 1024 / 1024) + "G";

            // 剩余的物理内存(B)
            long freePhysicalMemorySizeByte = osmxb.getFreePhysicalMemorySize();
            long freePhysicalMemorySizeByte2 = systemInfo.getHardware().getMemory().getAvailable();

            // 剩余的物理内存
            String freePhysicalMemorySize =
                    new DecimalFormat("#.##").format(freePhysicalMemorySizeByte / 1024.0 / 1024 / 1024) + "G";
            String freePhysicalMemorySize2 =
                    new DecimalFormat("#.##").format(freePhysicalMemorySizeByte2 * 1.0 / 1024 / 1024 / 1024) + "G";

            // 已使用的物理内存
            String usedMemory =
                    new DecimalFormat("#.##").format((totalMemorySizeByte - freePhysicalMemorySizeByte) / 1024.0 / 1024 / 1024) + "G";
            String usedMemory2 =
                    new DecimalFormat("#.##").format((totalMemorySizeByte2 - freePhysicalMemorySizeByte2) * 1.0 / 1024 / 1024 / 1024) + "G";

            String memoryUsedRate = new DecimalFormat("#.##%").format(1 - (freePhysicalMemorySizeByte * 1.0 / totalMemorySizeByte));

            logger.info("总的物理内存: " + totalMemorySize);
            logger.info("总的物理内存: " + totalMemorySize2);
            logger.info("剩余的物理内存: " + freePhysicalMemorySize);
            logger.info("剩余的物理内存: " + freePhysicalMemorySize2);
            logger.info("已使用的物理内存: " + usedMemory);
            logger.info("已使用的物理内存: " + usedMemory2);
            logger.info("物理内存使用率: " + memoryUsedRate);

            /* ============================== JVM信息 ============================= */
            logger.info("");
            logger.info("");
            logger.info("============================== JVM信息 =============================");
            logger.info("JAVA_HOME: " + System.getProperty("java.home"));
            logger.info("JAVA_VERSION: " + System.getProperty("java.version"));
            logger.info("USER_HOME: " + System.getProperty("user.home"));
            logger.info("USER_NAME: " + System.getProperty("user.name"));

            /* ============================== CPU信息 ============================= */
            logger.info("");
            logger.info("");
            logger.info("============================== CPU信息 =============================");
            printlnCpuInfo(systemInfo);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void printlnCpuInfo(SystemInfo systemInfo) throws InterruptedException {
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        // 睡眠1s
        TimeUnit.SECONDS.sleep(1);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()]
                - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()]
                - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()]
                - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()]
                - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()]
                - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()]
                - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()]
                - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()]
                - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;

        logger.info("cpu核数: " + Runtime.getRuntime().availableProcessors());
        logger.info("cpu核数: " + processor.getLogicalProcessorCount());
        logger.info("cpu系统使用率: " + new DecimalFormat("#.##%").format(cSys * 1.0 / totalCpu));
        logger.info("cpu用户使用率: " + new DecimalFormat("#.##%").format(user * 1.0 / totalCpu));
        logger.info("cpu当前等待率: " + new DecimalFormat("#.##%").format(iowait * 1.0 / totalCpu));
        logger.info("cpu当前空闲率: " + new DecimalFormat("#.##%").format(idle * 1.0 / totalCpu));

    }

    /**
     * 打印 CPU 信息
     */
    public static Map<String, Object> getCpuInfo() throws InterruptedException {
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        // 睡眠1s
        TimeUnit.SECONDS.sleep(1);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()]
                - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()]
                - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()]
                - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()]
                - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()]
                - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()]
                - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()]
                - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()]
                - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;

        Map<String, Object> map = new HashMap();


        double totalUse = (totalCpu - idle) * 1.0 / totalCpu;
        if (totalUse < 0.01) {
            totalUse = 0.01;
        }
        map.put("totalCpu", new DecimalFormat("#0.000").format(totalUse));
        map.put("availableProcessors", Runtime.getRuntime().availableProcessors());
        logger.info("cpu核数: " + Runtime.getRuntime().availableProcessors());
        logger.info("cpu核数: " + processor.getLogicalProcessorCount());
        logger.info("cpu系统使用率: " + new DecimalFormat("#.##%").format(cSys * 1.0 / totalCpu));
        logger.info("cpu用户使用率: " + new DecimalFormat("#.##%").format(user * 1.0 / totalCpu));
        logger.info("cpu当前等待率: " + new DecimalFormat("#.##%").format(iowait * 1.0 / totalCpu));
        logger.info("cpu当前空闲率: " + new DecimalFormat("#.##%").format(idle * 1.0 / totalCpu));
        logger.info("cpu info is:{}", map);

        return map;

    }

    public static Map<String, Object> getMemoryInfo() {
        SystemInfo systemInfo = new SystemInfo();
        // 总的物理内存(B)
        long totalMemorySizeByte2 = systemInfo.getHardware().getMemory().getTotal();
        // 总的物理内存
        String totalMemorySize2 =
                new DecimalFormat("#.##").format(totalMemorySizeByte2 / 1024.0 / 1024 / 1024) + "G";

        // 剩余的物理内存(B)
        long freePhysicalMemorySizeByte2 = systemInfo.getHardware().getMemory().getAvailable();
        // 剩余的物理内存
        String freePhysicalMemorySize2 =
                new DecimalFormat("#.##").format(freePhysicalMemorySizeByte2 * 1.0 / 1024 / 1024 / 1024) + "G";
        // 已使用的物理内存
        String usedMemory2 = new DecimalFormat("#.##").format((totalMemorySizeByte2 - freePhysicalMemorySizeByte2) * 1.0 / 1024 / 1024 / 1024) + "G";
        String memoryUsedRate = new DecimalFormat("#.##").format(1 - (freePhysicalMemorySizeByte2 * 1.0 / totalMemorySizeByte2));
        logger.info("总的物理内存: " + totalMemorySize2);
        logger.info("剩余的物理内存: " + freePhysicalMemorySize2);
        logger.info("已使用的物理内存: " + usedMemory2);
        logger.info("物理内存使用率: " + memoryUsedRate);
        Map map = new HashMap();

        map.put("memoryUsedRate", memoryUsedRate);
        map.put("usedMemory", new DecimalFormat("#.##").format((totalMemorySizeByte2 - freePhysicalMemorySizeByte2) / 1024.0 / 1024 / 1024));
        logger.info("memory info is:{}", map);
        return map;

    }

    public static void main(String[] args) throws InterruptedException {
        getCpuInfo();
    }

    public static double getDiskUseage() throws IOException {
        DecimalFormat df = new DecimalFormat("#0.00");
        File[] disks = File.listRoots();
        long total = 0;
        double used = 0.0;
        for (File file : disks) {
            // 获取盘符
            logger.info(file.getCanonicalPath() + "   ");
            // 获取总容量
            long totalSpace = file.getTotalSpace();
            total = total + totalSpace;
            // 获取剩余容量
            long usableSpace = file.getUsableSpace();
            // 获取已经使用的容量
            long freeSpace = totalSpace - usableSpace;
            used = used + freeSpace;
            // 获取使用率
            float useRate = (float) ((freeSpace * 1.0 / totalSpace) * 100);
            logger.info("总容量： " + transformation(totalSpace));
            logger.info("已经使用： " + transformation(freeSpace));
            logger.info("剩余容量： " + transformation(usableSpace));
            logger.info("使用率： " + Double.parseDouble(df.format(useRate)) + "%   ");
        }

        logger.info("使用率： " + Double.parseDouble(df.format(used / total * 100)) + "%   ");

        return Double.parseDouble(df.format(used / total));

    }

    /**
     * 将字节容量转化为GB
     */
    public static String transformation(long size) {
        return size / 1024 / 1024 / 1024 + "GB" + "   ";
    }


}
