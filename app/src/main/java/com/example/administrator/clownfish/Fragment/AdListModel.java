package com.example.administrator.clownfish.Fragment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：ClownFish
 * 类描述：
 * 创建人：WangQing
 * 创建时间：2016/5/18 16:01
 * 修改人：WangQing
 * 修改时间：2016/5/18 16:01
 * 修改备注：
 */
public class AdListModel  {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("infos")
    @Expose
    private List<Info> infos = new ArrayList<>();

    /**
     * @return The total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total The total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return The infos
     */
    public List<Info> getInfos() {
        return infos;
    }

    /**
     * @param infos The infos
     */
    public void setInfos(List<Info> infos) {
        this.infos = infos;
    }


    public class Info {

        @SerializedName("advertiseName")
        @Expose
        private String advertiseName;
        @SerializedName("advertiseLocation")
        @Expose
        private String advertiseLocation;
        @SerializedName("advertiseTitle")
        @Expose
        private String advertiseTitle;
        @SerializedName("advertiseLinkUrl")
        @Expose
        private String advertiseLinkUrl;
        @SerializedName("advertiseImageUrl")
        @Expose
        private String advertiseImageUrl;
        @SerializedName("advertiseOrder")
        @Expose
        private Integer advertiseOrder;
        @SerializedName("requiredAuthType")
        @Expose
        private Integer requiredAuthType;
        @SerializedName("updateDate")
        @Expose
        private Long updateDate;
        @SerializedName("advertiseWidth")
        @Expose
        private String advertiseWidth;
        @SerializedName("advertiseHeight")
        @Expose
        private String advertiseHeight;

        /**
         * @return The advertiseName
         */
        public String getAdvertiseName() {
            return advertiseName;
        }

        /**
         * @param advertiseName The advertiseName
         */
        public void setAdvertiseName(String advertiseName) {
            this.advertiseName = advertiseName;
        }

        /**
         * @return The advertiseLocation
         */
        public String getAdvertiseLocation() {
            return advertiseLocation;
        }

        /**
         * @param advertiseLocation The advertiseLocation
         */
        public void setAdvertiseLocation(String advertiseLocation) {
            this.advertiseLocation = advertiseLocation;
        }

        /**
         * @return The advertiseTitle
         */
        public String getAdvertiseTitle() {
            return advertiseTitle;
        }

        /**
         * @param advertiseTitle The advertiseTitle
         */
        public void setAdvertiseTitle(String advertiseTitle) {
            this.advertiseTitle = advertiseTitle;
        }

        /**
         * @return The advertiseLinkUrl
         */
        public String getAdvertiseLinkUrl() {
            return advertiseLinkUrl;
        }

        /**
         * @param advertiseLinkUrl The advertiseLinkUrl
         */
        public void setAdvertiseLinkUrl(String advertiseLinkUrl) {
            this.advertiseLinkUrl = advertiseLinkUrl;
        }

        /**
         * @return The advertiseImageUrl
         */
        public String getAdvertiseImageUrl() {
            return advertiseImageUrl;
        }

        /**
         * @param advertiseImageUrl The advertiseImageUrl
         */
        public void setAdvertiseImageUrl(String advertiseImageUrl) {
            this.advertiseImageUrl = advertiseImageUrl;
        }

        /**
         * @return The advertiseOrder
         */
        public Integer getAdvertiseOrder() {
            return advertiseOrder;
        }

        /**
         * @param advertiseOrder The advertiseOrder
         */
        public void setAdvertiseOrder(Integer advertiseOrder) {
            this.advertiseOrder = advertiseOrder;
        }

        /**
         * @return The requiredAuthType
         */
        public Integer getRequiredAuthType() {
            return requiredAuthType;
        }

        /**
         * @param requiredAuthType The requiredAuthType
         */
        public void setRequiredAuthType(Integer requiredAuthType) {
            this.requiredAuthType = requiredAuthType;
        }

        /**
         * @return The updateDate
         */
        public Long getUpdateDate() {
            return updateDate;
        }

        /**
         * @param updateDate The updateDate
         */
        public void setUpdateDate(Long updateDate) {
            this.updateDate = updateDate;
        }

        /**
         * @return The advertiseWidth
         */
        public String getAdvertiseWidth() {
            return advertiseWidth;
        }

        /**
         * @param advertiseWidth The advertiseWidth
         */
        public void setAdvertiseWidth(String advertiseWidth) {
            this.advertiseWidth = advertiseWidth;
        }

        /**
         * @return The advertiseHeight
         */
        public String getAdvertiseHeight() {
            return advertiseHeight;
        }

        /**
         * @param advertiseHeight The advertiseHeight
         */
        public void setAdvertiseHeight(String advertiseHeight) {
            this.advertiseHeight = advertiseHeight;
        }

    }

}