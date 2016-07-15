package com.example.administrator.clownfish.modle;

import java.util.List;

/**
 * Created by hzwq on 2016/7/15.
 */
public class ProductInformationModle {

    /**
     * leaveCount : null
     * mainExpress : null
     * saleAmount : null
     * score : null
     * scoreList : [["1372BFDF-1129-4226-B5BF-5AAABED17952","安达露茜-橄榄油","0.0000",0],["2CB713F4-50C1-4D43-8653-BF4A649ECF40","福临门-AE浓香营养菜籽油","14.0000",9],["195FB0EF-6ECD-451D-A778-20B861445306","福临门-茶籽橄榄调和油","-4.7500",0],["9872FB90-724D-4311-9DD6-038FA0B1A8E6","福临门-纯香菜籽油","9.0000",4],["53C02A7E-5618-4F1E-B45B-2419FF165BC7","福临门-橄榄油","0.0000",0],["6CE04900-8FBE-4239-952E-201977E9E6FF","福临门-花生油","0.0000",0],["34F95F45-1E0A-456A-AC2A-53A54E4FE5AB","福临门-家香味浓香压榨菜籽油","209.5000",408.5],["F2C91715-CEAF-43AA-9DBD-C227CF181DF9","福临门-葵花籽油","311.8300",715.49],["4CE571C4-74DA-45EB-82A4-F5E8D0BA157B","福临门-天然谷物调和油","840.5800",438.41],["EA69FAE3-0736-400A-98EB-5F84D0EA3341","福临门-压榨菜籽油","31.5000",-177],["F15D8543-788F-4951-AB6F-00867D5684E1","福临门-一级大豆油","157.0000",68.5],["BB91D295-EB53-4C58-8D58-4C46D40FF0BA","福临门-玉米油","551.3300",457],["E55706C4-EAD3-4AA6-836F-A6C7C0D702AD","福临门-藻油DHA调和油","0.0000",0],["0AFA95EF-038F-4DF4-A831-70BF8E4A992D","福临门-脂肪酸均衡调和油","9.7500",0],["7085F2E0-5A11-4346-8BB0-BA19FF6805C3","四海-一级大豆油","18.0000",-110],["9297B9B2-46E1-49A1-B12C-E9BFA224F402","五湖-一级大豆油","120.0000",110]]
     * signErrorCount : null
     * success : true
     * uid : null
     * userName : null
     * yearMonthInfo : 2015-01-06
     */

    private Object leaveCount;
    private Object mainExpress;
    private Object saleAmount;
    private Object score;
    private Object signErrorCount;
    private boolean success;
    private Object uid;
    private Object userName;
    private String yearMonthInfo;
    private List<List<String>> scoreList;

    public Object getLeaveCount() {
        return leaveCount;
    }

    public void setLeaveCount(Object leaveCount) {
        this.leaveCount = leaveCount;
    }

    public Object getMainExpress() {
        return mainExpress;
    }

    public void setMainExpress(Object mainExpress) {
        this.mainExpress = mainExpress;
    }

    public Object getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Object saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Object getScore() {
        return score;
    }

    public void setScore(Object score) {
        this.score = score;
    }

    public Object getSignErrorCount() {
        return signErrorCount;
    }

    public void setSignErrorCount(Object signErrorCount) {
        this.signErrorCount = signErrorCount;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getUid() {
        return uid;
    }

    public void setUid(Object uid) {
        this.uid = uid;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public String getYearMonthInfo() {
        return yearMonthInfo;
    }

    public void setYearMonthInfo(String yearMonthInfo) {
        this.yearMonthInfo = yearMonthInfo;
    }

    public List<List<String>> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<List<String>> scoreList) {
        this.scoreList = scoreList;
    }
}
