package com.example.administrator.clownfish.modle;

import java.util.List;

/**
 * Created by hzwq on 2016/7/9.
 */
public class StoreInformationModle {

    /**
     * agent : null
     * contectId : null
     * contectId2 : null
     * contectList : null
     * distance : 0
     * inOut : 0
     * isAll : 0
     * l1 : []
     * l2 : []
     * l3 : []
     * latitude : null
     * lineId : null
     * lines : []
     * list : [{"id":"FAC46243-CA34-48A9-8BB5-F3380F03FFB5","salesMan":"程从兵","sn":"浦阳嘉佰乐超市","stn":"已审核","t":"2016-07-02 08:20"},{"id":"E091D52D-2DBF-4230-8578-92E48649F475","salesMan":"程从兵","sn":"楼塔楼芹商行","stn":"已审核","t":"2016-02-29 10:32"},{"id":"0696E8C8-80C9-49C0-8830-34E5AE63AF97","salesMan":"程从兵","sn":"楼塔福薏欢","stn":"已审核","t":"2015-12-14 10:38"},{"id":"2ECDAEE9-5B3C-4449-9959-EAD3C6E99DA2","salesMan":"程从兵","sn":"新街府前87号","stn":"已审核","t":"2015-12-06 10:53"},{"id":"1F93C242-72C4-497E-A904-0AC5E05ADDE6","salesMan":"程从兵","sn":"新街长山超级购购物广场","stn":"已审核","t":"2015-11-26 08:44"},{"id":"BC1E79BB-0AEB-4B80-99B4-4EAB669EB1C0","salesMan":"程从兵","sn":"中商超市长河店","stn":"已审核","t":"2015-11-24 12:50"},{"id":"38149F27-20B1-428C-A134-7E632CF68946","salesMan":"程从兵","sn":"八大村易买盛","stn":"已审核","t":"2015-10-16 09:58"},{"id":"36ECFA51-8B1F-4611-9479-9D74CF195A57","salesMan":"程从兵","sn":"崇化中商","stn":"已审核","t":"2015-09-26 17:10"},{"id":"AF80E97F-E1DC-4CAE-90E1-9E05BC9F7775","salesMan":"程从兵","sn":"瓜沥世纪华联东灵路店","stn":"已审核","t":"2015-08-17 11:29"},{"id":"B9CEE593-C2E0-4C1F-AE74-ACC6485B3DE8","salesMan":"程从兵","sn":"头蓬迦特购物中心","stn":"已审核","t":"2015-07-14 09:45"}]
     * longitude : null
     * lxr2 : null
     * mobile : null
     * mobile2 : null
     * ns : null
     * pageCount : 10
     * pageIndex : 1
     * pageSize : 10
     * phone : null
     * phone2 : null
     * position : null
     * position2 : null
     * realName : null
     * salesManId : null
     * shopPhone : null
     * simpleAgentInfo : []
     * simpleLineInfo : []
     * simpleShopInfo : []
     * success : true
     * total : 99
     * visitInfo : null
     */

    private Object agent;
    private Object contectId;
    private Object contectId2;
    private Object contectList;
    private int distance;
    private int inOut;
    private int isAll;
    private Object latitude;
    private Object lineId;
    private Object longitude;
    private Object lxr2;
    private Object mobile;
    private Object mobile2;
    private Object ns;
    private int pageCount;
    private int pageIndex;
    private int pageSize;
    private Object phone;
    private Object phone2;
    private Object position;
    private Object position2;
    private Object realName;
    private Object salesManId;
    private Object shopPhone;
    private boolean success;
    private int total;
    private Object visitInfo;
    private List<?> l1;
    private List<?> l2;
    private List<?> l3;
    private List<?> lines;
    /**
     * id : FAC46243-CA34-48A9-8BB5-F3380F03FFB5
     * salesMan : 程从兵
     * sn : 浦阳嘉佰乐超市
     * stn : 已审核
     * t : 2016-07-02 08:20
     */

    private List<ListBean> list;
    private List<?> simpleAgentInfo;
    private List<?> simpleLineInfo;
    private List<?> simpleShopInfo;

    public Object getAgent() {
        return agent;
    }

    public void setAgent(Object agent) {
        this.agent = agent;
    }

    public Object getContectId() {
        return contectId;
    }

    public void setContectId(Object contectId) {
        this.contectId = contectId;
    }

    public Object getContectId2() {
        return contectId2;
    }

    public void setContectId2(Object contectId2) {
        this.contectId2 = contectId2;
    }

    public Object getContectList() {
        return contectList;
    }

    public void setContectList(Object contectList) {
        this.contectList = contectList;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getInOut() {
        return inOut;
    }

    public void setInOut(int inOut) {
        this.inOut = inOut;
    }

    public int getIsAll() {
        return isAll;
    }

    public void setIsAll(int isAll) {
        this.isAll = isAll;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public Object getLineId() {
        return lineId;
    }

    public void setLineId(Object lineId) {
        this.lineId = lineId;
    }

    public Object getLongitude() {
        return longitude;
    }

    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }

    public Object getLxr2() {
        return lxr2;
    }

    public void setLxr2(Object lxr2) {
        this.lxr2 = lxr2;
    }

    public Object getMobile() {
        return mobile;
    }

    public void setMobile(Object mobile) {
        this.mobile = mobile;
    }

    public Object getMobile2() {
        return mobile2;
    }

    public void setMobile2(Object mobile2) {
        this.mobile2 = mobile2;
    }

    public Object getNs() {
        return ns;
    }

    public void setNs(Object ns) {
        this.ns = ns;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getPhone2() {
        return phone2;
    }

    public void setPhone2(Object phone2) {
        this.phone2 = phone2;
    }

    public Object getPosition() {
        return position;
    }

    public void setPosition(Object position) {
        this.position = position;
    }

    public Object getPosition2() {
        return position2;
    }

    public void setPosition2(Object position2) {
        this.position2 = position2;
    }

    public Object getRealName() {
        return realName;
    }

    public void setRealName(Object realName) {
        this.realName = realName;
    }

    public Object getSalesManId() {
        return salesManId;
    }

    public void setSalesManId(Object salesManId) {
        this.salesManId = salesManId;
    }

    public Object getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(Object shopPhone) {
        this.shopPhone = shopPhone;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getVisitInfo() {
        return visitInfo;
    }

    public void setVisitInfo(Object visitInfo) {
        this.visitInfo = visitInfo;
    }

    public List<?> getL1() {
        return l1;
    }

    public void setL1(List<?> l1) {
        this.l1 = l1;
    }

    public List<?> getL2() {
        return l2;
    }

    public void setL2(List<?> l2) {
        this.l2 = l2;
    }

    public List<?> getL3() {
        return l3;
    }

    public void setL3(List<?> l3) {
        this.l3 = l3;
    }

    public List<?> getLines() {
        return lines;
    }

    public void setLines(List<?> lines) {
        this.lines = lines;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<?> getSimpleAgentInfo() {
        return simpleAgentInfo;
    }

    public void setSimpleAgentInfo(List<?> simpleAgentInfo) {
        this.simpleAgentInfo = simpleAgentInfo;
    }

    public List<?> getSimpleLineInfo() {
        return simpleLineInfo;
    }

    public void setSimpleLineInfo(List<?> simpleLineInfo) {
        this.simpleLineInfo = simpleLineInfo;
    }

    public List<?> getSimpleShopInfo() {
        return simpleShopInfo;
    }

    public void setSimpleShopInfo(List<?> simpleShopInfo) {
        this.simpleShopInfo = simpleShopInfo;
    }

    public static class ListBean {
        private String id;
        private String salesMan;
        private String sn;
        private String stn;
        private String t;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSalesMan() {
            return salesMan;
        }

        public void setSalesMan(String salesMan) {
            this.salesMan = salesMan;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getStn() {
            return stn;
        }

        public void setStn(String stn) {
            this.stn = stn;
        }

        public String getT() {
            return t;
        }

        public void setT(String t) {
            this.t = t;
        }
    }
}
