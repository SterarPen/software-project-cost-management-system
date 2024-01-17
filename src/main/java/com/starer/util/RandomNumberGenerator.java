package com.starer.util;

import java.util.Random;

public class RandomNumberGenerator {
    public static String randomNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(12);
        for (int i = 0; i < 12; i++) {
            sb.append(random.nextInt(10)); // 生成0到9之间的随机数字
        }
        String twelveDigitNumber = sb.toString();
        return twelveDigitNumber;
    }

    public static String randomCode() {
        Random random = new Random();
        int randomNumber = 100000 + random.nextInt(900000);
        return String.valueOf(randomNumber);
    }
}
