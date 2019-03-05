/*
 * Copyright (c) 2011-2013 GoPivotal, Inc. All Rights Reserved. Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */
package com.zhxg.framework.base.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper for creating random and Type 1 (time-based) UUIDs.
 * 
 * @author Jon Brisbin
 */
public class UUIDUtils {

    private final static Logger logger = LoggerFactory.getLogger(UUIDUtils.class);

    private static boolean IS_THREADLOCALRANDOM_AVAILABLE = false;
    private static Random random;
    private static final long LEASTSIGBITS;
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static long lastTime;

    static {
        try {
            IS_THREADLOCALRANDOM_AVAILABLE = null != UUIDUtils.class.getClassLoader().loadClass(
                    "java.util.concurrent.ThreadLocalRandom");
        } catch (ClassNotFoundException e) {
            logger.error(UUIDUtils.class.getName() + ":::" + e);
        }

        final byte[] seed = new SecureRandom().generateSeed(8);
        LEASTSIGBITS = new BigInteger(seed).longValue();
        if (!IS_THREADLOCALRANDOM_AVAILABLE) {
            random = new Random(LEASTSIGBITS);
        }
    }

    private UUIDUtils() {
    }

    /**
     * Create a new random UUID.
     * 
     * @return the new UUID
     */
    public static String random() {
        final byte[] randomBytes = new byte[16];
        if (IS_THREADLOCALRANDOM_AVAILABLE) {
            ThreadLocalRandom.current().nextBytes(randomBytes);
        } else {
            random.nextBytes(randomBytes);
        }

        long mostSigBits = 0;
        for (int i = 0; i < 8; i++) {
            mostSigBits = mostSigBits << 8 | randomBytes[i] & 0xff;
        }
        long leastSigBits = 0;
        for (int i = 8; i < 16; i++) {
            leastSigBits = leastSigBits << 8 | randomBytes[i] & 0xff;
        }

        return new UUID(mostSigBits, leastSigBits).toString().replaceAll("-", "");
    }

    /**
     * Create a new time-based UUID.
     * 
     * @return the new UUID
     */
    public static String create() {
        long timeMillis = System.currentTimeMillis() * 10000 + 0x01B21DD213814000L;

        LOCK.lock();
        try {
            if (timeMillis > lastTime) {
                lastTime = timeMillis;
            } else {
                timeMillis = ++lastTime;
            }
        } finally {
            LOCK.unlock();
        }

        // time low
        long mostSigBits = timeMillis << 32;

        // time mid
        mostSigBits |= (timeMillis & 0xFFFF00000000L) >> 16;

        // time hi and version
        mostSigBits |= 0x1000 | timeMillis >> 48 & 0x0FFF; // version 1

        return new UUID(mostSigBits, LEASTSIGBITS).toString().replaceAll("-", "");
    }

    /**
     * 生成基于秒级的半随机码
     * 
     * @param maxLen
     *            编码的最大长度，该值最小为14。此值越大，秒级下并发概率越低
     * @param isLenFixed
     *            当前生成的编码长度是否固定。不固定时，所生成的编码位数在14-maxLen之间
     * @return
     */
    public static String getSecondBasedRandomCode(int maxLen, boolean isLenFixed) {
        String secondPart = DateUtil.DateToString(DateUtil.getSystemDate(), "yyyyMMddHHmmss");
        Random random = new Random(System.nanoTime());
        int a = random.nextInt((int) Math.pow(10.0, maxLen - 14.0) - 1);
        String ranPart;
        if (isLenFixed) {
            ranPart = lpad(maxLen - 14, a);
        } else {
            ranPart = String.valueOf(a);
        }
        return secondPart + ranPart;
    }

    private static String lpad(int length, int number) {
        if (length <= 0) {
            length = 1;
        }
        String f = "%0" + length + "d";
        return String.format(f, number);
    }
    /**
     * 获取32位uuid
     * 
     * @return
     */
    public static String getUuid() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }

}
