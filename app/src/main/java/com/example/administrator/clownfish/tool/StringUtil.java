package com.example.administrator.clownfish.tool;

import android.text.TextUtils;

/**
 * Created by hzwq on 2016/7/18.
 */
public class StringUtil {


    /**
     * @param str
     * @param frontVisibleLength
     * @param behindVisibleLength
     * @return
     */
    public static String invisibleWord(String str, int frontVisibleLength, int behindVisibleLength) {
        try {
            if (str.length() > (frontVisibleLength + behindVisibleLength)) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < (str.length() - frontVisibleLength - behindVisibleLength); i++) {
                    sb.append("*");
                }
                str = str.substring(0, frontVisibleLength) + sb.toString() + str.substring(str.length() - behindVisibleLength, str.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return str;
    }

    public static String substring(String str, int start, int end) {
        try {
            if (str.length() > (end - start)) {
                str = str.substring(start, end);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return str;
    }


    /**
     * 去掉所有空格
     *
     * @param string
     * @return
     */
    public static String removeSpace(String string) {
        String noSpaceStr = "";
        if (!TextUtils.isEmpty(string)) {
            noSpaceStr = string.trim().replace(" ", "");
        }
        return noSpaceStr;
    }

}
