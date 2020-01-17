package com.yuansong.demo.boot.demo.test.id;


import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class IdUtil {

    private static IdGenerator idGenerator;
    private static SnWorker snWorker = new SnWorker(1, 1);

    static {
        Properties props = System.getProperties();
        String workerId = props.getProperty("WORKER_ID");
        if (workerId != null && isNumeric(workerId)) {
            idGenerator = new SnowflakeIdGenerator(Long.parseLong(workerId));
        } else {
            idGenerator = new SnowflakeIdGenerator(1);
        }
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static synchronized long getId() {
        return idGenerator.nextId();
    }


    public static synchronized String getSn() {
        Long thisTime = Long.parseLong(new SimpleDateFormat("yyyyMMdd")
                .format(new Date()));
        BigInteger bigInteger = new BigInteger(thisTime.toString());
        long id = snWorker.nextId();
        bigInteger = bigInteger.multiply(
                BigInteger.valueOf((long) Math.pow(10, 12))).add(
                BigInteger.valueOf((id % 1000000000000L)));
        return bigInteger.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(IdUtil.getId());
        }
    }

}
