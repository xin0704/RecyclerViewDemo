package com.xin.recyclerviewdemo.entity.item;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xin.recyclerviewdemo.adapter.ExpandableItemAdapter;

/**
 * Created by admin on 2019/3/12.
 */
public class AreaItem implements MultiItemEntity {

    public String name;

    public AreaItem(String name) {

        this.name = name;
    }


    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_AREA;
    }

}
