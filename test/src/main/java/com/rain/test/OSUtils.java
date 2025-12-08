package com.rain.test;

import com.sun.management.OperatingSystemMXBean;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OSUtils {

    public static void main(String[] args) {
        System.out.println("");
        init();
    }

    public static void init() {
            try {
                SystemInfo systemInfo = new SystemInfo();

                OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
                MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

                /* ============================== 操作系统信息 ============================= */
                System.err.println("");
                System.err.println("");
                System.err.println("============================== 操作系统信息 =============================");
                // 操作系统
                String osName = System.getProperty("os.name");

                // 获得线程总数
                ThreadGroup parentThread;
                parentThread = Thread.currentThread().getThreadGroup();
                while (parentThread.getParent() != null) {
                    parentThread = parentThread.getParent();
                }

                int totalThread = parentThread.activeCount();

                System.err.println("操作系统: " + osName);
                System.err.println("程序启动时间: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .format(new Date(ManagementFactory.getRuntimeMXBean().getStartTime())));
                System.err.println("pid: " + System.getProperty("PID"));
                System.err.println("总线程数: " + totalThread);

                // 磁盘使用情况
                File[] files = File.listRoots();
                for (File file : files) {
                    String total = new DecimalFormat("#.#").format(file.getTotalSpace() * 1.0 / 1024 / 1024 / 1024) + "G";
                    String free = new DecimalFormat("#.#").format(file.getFreeSpace() * 1.0 / 1024 / 1024 / 1024) + "G";
                    String un = new DecimalFormat("#.#").format(file.getUsableSpace() * 1.0 / 1024 / 1024 / 1024) + "G";
                    String path = file.getPath();
                    System.err.println(path + "总: " + total + ",可用空间: " + un + ",空闲空间: " + free);
                    // System.err.println("=============================================");
                    System.err.println("---------------------------------------------");
                }

                /* ============================== 堆内存信息 ============================= */
                System.err.println("");
                System.err.println("");
                System.err.println("============================== 堆内存信息 =============================");
                // 椎内存使用情况
                MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();

                // 初始的总内存(B)
                long initTotalMemorySize = memoryUsage.getInit();

                // 最大可用内存(B)
                long maxMemorySize = memoryUsage.getMax();

                // 已使用的内存(B)
                long usedMemorySize = memoryUsage.getUsed();

                System.err.println(
                        "初始的总内存(JVM): " + new DecimalFormat("#.#").format(initTotalMemorySize * 1.0 / 1024 / 1024) + "M");
                System.err.println(
                        "最大可用内存(JVM): " + new DecimalFormat("#.#").format(maxMemorySize * 1.0 / 1024 / 1024) + "M");
                System.err.println(
                        "已使用的内存(JVM): " + new DecimalFormat("#.#").format(usedMemorySize * 1.0 / 1024 / 1024) + "M");

                /* ============================== 物理内存信息 ============================= */
                System.err.println("");
                System.err.println("");
                System.err.println("============================== 物理内存信息 =============================");
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

                System.err.println("总的物理内存: " + totalMemorySize);
                System.err.println("总的物理内存: " + totalMemorySize2);
                System.err.println("剩余的物理内存: " + freePhysicalMemorySize);
                System.err.println("剩余的物理内存: " + freePhysicalMemorySize2);
                System.err.println("已使用的物理内存: " + usedMemory);
                System.err.println("已使用的物理内存: " + usedMemory2);
                System.err.println("物理内存使用率: " + memoryUsedRate);

                /* ============================== JVM信息 ============================= */
                System.err.println("");
                System.err.println("");
                System.err.println("============================== JVM信息 =============================");
                System.err.println("JAVA_HOME: " + System.getProperty("java.home"));
                System.err.println("JAVA_VERSION: " + System.getProperty("java.version"));
                System.err.println("USER_HOME: " + System.getProperty("user.home"));
                System.err.println("USER_NAME: " + System.getProperty("user.name"));

                /* ============================== CPU信息 ============================= */
                System.err.println("");
                System.err.println("");
                System.err.println("============================== CPU信息 =============================");
                printlnCpuInfo(systemInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
       
    }

    /**
     * 打印 CPU 信息
     * @param systemInfo s
     */
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

        System.err.println("cpu核数: " + Runtime.getRuntime().availableProcessors());
        System.err.println("cpu核数: " + processor.getLogicalProcessorCount());
        System.err.println("cpu系统使用率: " + new DecimalFormat("#.##%").format(cSys * 1.0 / totalCpu));
        System.err.println("cpu用户使用率: " + new DecimalFormat("#.##%").format(user * 1.0 / totalCpu));
        System.err.println("cpu当前等待率: " + new DecimalFormat("#.##%").format(iowait * 1.0 / totalCpu));
        System.err.println("cpu当前空闲率: " + new DecimalFormat("#.##%").format(idle * 1.0 / totalCpu));

    }


}
