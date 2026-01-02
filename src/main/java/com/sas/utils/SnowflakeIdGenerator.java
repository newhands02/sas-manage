package com.sas.utils;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 雪花算法ID生成器
 */
public class SnowflakeIdGenerator {

    // 起始时间戳 (2020-01-01 00:00:00)
    private static final long START_TIMESTAMP = 1577808000000L;

    // 各部分位数
    private static final long SEQUENCE_BIT = 12; // 序列号占用的位数
    private static final long WORKER_BIT = 5;   // 机器ID占用的位数
    private static final long DATACENTER_BIT = 5; // 数据中心ID占用的位数

    // 最大值
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);
    private static final long MAX_WORKER = ~(-1L << WORKER_BIT);
    private static final long MAX_DATACENTER = ~(-1L << DATACENTER_BIT);

    // 位移
    private static final long WORKER_SHIFT = SEQUENCE_BIT;
    private static final long DATACENTER_SHIFT = SEQUENCE_BIT + WORKER_BIT;
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BIT + WORKER_BIT + DATACENTER_BIT;

    // 数据中心ID和机器ID
    private final long datacenterId;
    private final long workerId;

    // 序列号
    private long sequence = 0L;

    // 上一次时间戳
    private long lastTimestamp = -1L;

    /**
     * 构造函数
     * @param datacenterId 数据中心ID (0~31)
     * @param workerId 机器ID (0~31)
     */
    public SnowflakeIdGenerator(long datacenterId, long workerId) {
        if (datacenterId > MAX_DATACENTER || datacenterId < 0) {
            throw new IllegalArgumentException("数据中心ID不能大于" + MAX_DATACENTER + "或小于0");
        }
        if (workerId > MAX_WORKER || workerId < 0) {
            throw new IllegalArgumentException("机器ID不能大于" + MAX_WORKER + "或小于0");
        }
        this.datacenterId = datacenterId;
        this.workerId = workerId;
    }

    /**
     * 生成下一个ID
     * @return 生成的ID
     */
    public synchronized long nextId() {
        long currentTimestamp = timeGen();

        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过
        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("时钟回退，拒绝生成ID");
        }

        // 如果是同一时间生成的，则进行毫秒内序列
        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒，获取新的时间戳
                currentTimestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        // 移位并通过或运算拼到一起组成64位的ID
        return ((currentTimestamp - START_TIMESTAMP) << TIMESTAMP_SHIFT)
                | (datacenterId << DATACENTER_SHIFT)
                | (workerId << WORKER_SHIFT)
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间戳
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 获取机器ID
     * @return 机器ID
     */
    public static long getWorkerId() {
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            return Math.abs(hostName.hashCode()) % MAX_WORKER;
        } catch (Exception e) {
            return 1L;
        }
    }

    /**
     * 获取数据中心ID
     * @return 数据中心ID
     */
    public static long getDatacenterId() {
        try {
            NetworkInterface network = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            if (network == null) {
                return 1L;
            }
            byte[] mac = network.getHardwareAddress();
            if (mac == null || mac.length == 0) {
                return 1L;
            }
            long id = ((0x000000FF & (long) mac[mac.length - 1])
                    | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
            return Math.abs(id) % MAX_DATACENTER;
        } catch (Exception e) {
            return 1L;
        }
    }

    /**
     * 静态工厂方法，自动获取机器ID和数据中心ID
     * @return SnowflakeIdGenerator实例
     */
    public static SnowflakeIdGenerator getInstance() {
        return new SnowflakeIdGenerator(getDatacenterId(), getWorkerId());
    }
}
