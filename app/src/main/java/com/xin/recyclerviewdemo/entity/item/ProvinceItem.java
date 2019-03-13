package com.xin.recyclerviewdemo.entity.item;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xin.recyclerviewdemo.adapter.ExpandableItemAdapter;

/**
 * Created by admin on 2019/3/12.
 */
public class ProvinceItem extends AbstractExpandableItem<CityItem> implements MultiItemEntity {

    public String name;

    private String sortLetters;

    public ProvinceItem(String name,String sortLetters) {
        this.name = name;
        this.sortLetters = sortLetters;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_PROVINCE;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }
}
