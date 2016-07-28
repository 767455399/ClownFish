package com.example.administrator.clownfish.view;

import com.example.administrator.clownfish.modle.SupportCityListModel;

import java.util.Comparator;

/**
 * Created by hzwq on 2016/7/26.
 */
public class PinyinComparator  implements Comparator<SupportCityListModel.Info> {


    @Override
    public int compare(SupportCityListModel.Info lhs, SupportCityListModel.Info rhs) {
        return lhs.getLetter().compareTo(rhs.getLetter());
    }
}
