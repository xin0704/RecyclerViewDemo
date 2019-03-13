package com.xin.recyclerviewdemo.others;

import com.xin.recyclerviewdemo.entity.Province;

import java.util.Comparator;

/**
 * Created by admin on 2019/3/12.
 */

public class LettersComparator implements Comparator<Province> {

    public int compare(Province o1, Province o2) {
        if (o1.getSortLetters().equals("@")
                || o2.getSortLetters().equals("#")) {
            return o2.getSortLetters().compareTo(o1.getSortLetters());
        } else if (o1.getSortLetters().equals("#")
                || o2.getSortLetters().equals("@")) {
            return o2.getSortLetters().compareTo(o1.getSortLetters());
        } else {
            return o1.getSortLetters().compareTo(o2.getSortLetters());
        }


    }

}