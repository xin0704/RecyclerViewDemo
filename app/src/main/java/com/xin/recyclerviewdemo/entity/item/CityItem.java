package com.xin.recyclerviewdemo.entity.item;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xin.recyclerviewdemo.adapter.ExpandableItemAdapter;

/**
 * Created by admin on 2019/3/12.
 */
public class CityItem extends AbstractExpandableItem<AreaItem> implements MultiItemEntity {
    public String name;

    public CityItem(String name) {
        this.name = name;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_CITY;
    }

    @Override
    public int getLevel() {
        return 1;
    }

}
