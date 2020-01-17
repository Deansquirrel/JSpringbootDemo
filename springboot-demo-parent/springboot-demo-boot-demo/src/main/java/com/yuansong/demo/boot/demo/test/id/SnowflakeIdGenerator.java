package com.yuansong.demo.boot.demo.test.id;

class SnowflakeIdGenerator implements IdGenerator {

    private final long workerId;
    private final static long TWEPOCH = 1361753741828L;
    private long sequence = 0L;
    /**
     * 数据中心ID的位数：5
     */
    private final static long WORKER_ID_BITS = 5L;
    /**
     * /机器ID的最大值：31
     */
    private final static long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    private final static long SEQUENCE_BITS = 10L;

    private final static long WORKER_ID_SHIFT = SEQUENCE_BITS;

    private final static long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    private final static long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    private long lastTimestamp = -1L;

    @Override
    public long nextId() {
        long timestamp = this.timeGen();

        if (this.lastTimestamp == timestamp) {
            this.sequence = (this.sequence + 1) & SEQUENCE_MASK;
            if (this.sequence == 0) {
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }

        if (timestamp < this.lastTimestamp) {
            try {
                throw new Exception(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                        this.lastTimestamp - timestamp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.lastTimestamp = timestamp;
        long nextId = ((timestamp - TWEPOCH << TIMESTAMP_LEFT_SHIFT)) | (this.workerId << WORKER_ID_SHIFT)
                | (this.sequence);

        return nextId;
    }

    public SnowflakeIdGenerator(final long workerId) {
        super();
        if (workerId > SnowflakeIdGenerator.MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",
                    SnowflakeIdGenerator.MAX_WORKER_ID));
        }
        this.workerId = workerId;
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();

        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }

        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

}
