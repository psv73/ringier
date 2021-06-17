package net.psv73.ringier.utils;

import java.util.Random;

public class GenerateIP {

    public static String createIP(String startIP, String endIP) {

        StringBuilder newIP = new StringBuilder();

        int minIP = 0;
        int maxIP = 255;

        String[] start = startIP.split("\\.");
        String[] end = endIP.split("\\.");

        for (int i = 0; i < 4; i++) {

            if (i == 0) {
                newIP.append(GenerateIP.randRange(Integer.parseInt(start[i]), Integer.parseInt(end[i]))).append(".");
            } else if (i == 3) {
                newIP.append(GenerateIP.randRange(minIP + 1, Integer.parseInt(end[i])));
            } else {
                newIP.append(GenerateIP.randRange(minIP, maxIP)).append(".");
            }
        }

        return newIP.toString();
    }

    public static int randRange(int low, int high) {

        int a = Math.min(low, high);
        int b = Math.max(low, high);

        return a + (new Random()).nextInt(b - a + 1);
    }

    public static long ipToLong(String stringIp) {

        String[] part = stringIp.split("\\.");
        long num = 0;

        for (int i = 0; i < part.length; i++) {
            int power = 3 - i;
            num += ((Integer.parseInt(part[i]) % 256 * Math.pow(256, power)));
        }

        return num;
    }

    public static String longToIp(long longIp) {

        long l = 256 * 256 * 256;

        StringBuffer stringBuffer = new StringBuffer();

        while (l > 0) {
            stringBuffer.append(longIp / l).append(".");
            longIp = longIp % l;// w ww. j  av a 2  s .c  om
            l /= 256;
        }

        stringBuffer.deleteCharAt(stringBuffer.length() - 1);

        return stringBuffer.toString();
    }
}
