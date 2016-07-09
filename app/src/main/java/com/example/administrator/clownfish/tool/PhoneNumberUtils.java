package com.example.administrator.clownfish.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hzwq on 2016/7/9.
 */
public class PhoneNumberUtils {

    /**
     * 验证手机号
     * 130-139
     * 150-159
     * 170-179
     * 180-189
     *
     * @param mobiles
     * @return
     */

    public static boolean isMobileNO(String mobiles) {

        //    Pattern pattern = Pattern.compile(BaseApplication.getContext().getString(R.string.phone_number_regular));
        Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(mobiles);
        return matcher.matches();
    }

}
