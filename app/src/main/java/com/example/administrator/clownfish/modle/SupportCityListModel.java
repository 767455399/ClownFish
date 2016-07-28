package com.example.administrator.clownfish.modle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzwq on 2016/7/26.
 */
public class SupportCityListModel {

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

        @SerializedName("letter")
        @Expose
        private String letter;
        @SerializedName("name")
        @Expose
        private String name;

        /**
         * @return The letter
         */
        public String getLetter() {
            return letter;
        }

        /**
         * @param letter The letter
         */
        public void setLetter(String letter) {
            this.letter = letter;
        }

        /**
         * @return The name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name The name
         */
        public void setName(String name) {
            this.name = name;
        }

    }

}
