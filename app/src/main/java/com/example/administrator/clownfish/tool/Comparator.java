package com.example.administrator.clownfish.tool;

import com.example.administrator.clownfish.modle.SortModel;

/**
 * Created by hzwq on 2016/7/26.
 */
public class Comparator   implements java.util.Comparator<SortModel> {

    public int compare(SortModel o1, SortModel o2) {
        if (o1.getSortLetters().equals("@") || o2.getSortLetters().equals("#")) {
            return -1;
        } else if (o1.getSortLetters().equals("#")
                || o2.getSortLetters().equals("@")) {
            return 1;
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }
    }
}
