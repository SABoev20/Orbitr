package com.backend.orbitr.bll.config;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;

public class SystemLoggerThread extends Thread{
    private final RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
    private final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
    private final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

    public void run() {
        while (true) {
            try {
                long uptime = runtimeMXBean.getUptime();
                System.out.println("Uptime: " + uptime + "ms");

                long usedMemory = memoryMXBean.getHeapMemoryUsage().getUsed();
                long maxMemory = memoryMXBean.getHeapMemoryUsage().getMax();
                System.out.println("Memory Usage: " + usedMemory + " bytes (Max: " + maxMemory + " bytes)");

                int threadCount = threadMXBean.getThreadCount();
                System.out.println("Active Threads: " + threadCount);

                Thread.sleep(600000); // 600000 milliseconds = 10 minutes
            } catch (InterruptedException e) {
                System.out.println("SystemHealthLogger interrupted");
                break;
            }
        }
    }
}
