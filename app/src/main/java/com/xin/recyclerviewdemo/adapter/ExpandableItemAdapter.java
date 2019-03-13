package com.xin.recyclerviewdemo.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.xin.recyclerviewdemo.R;
import com.xin.recyclerviewdemo.entity.item.AreaItem;
import com.xin.recyclerviewdemo.entity.item.CityItem;
import com.xin.recyclerviewdemo.entity.item.ProvinceItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2019/3/12.
 */

public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_PROVINCE = 0;
    public static final int TYPE_CITY = 1;
    public static final int TYPE_AREA = 2;

    private List<MultiItemEntity> data = new ArrayList<>();

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ExpandableItemAdapter(List<MultiItemEntity> data) {
        super(data);
        this.data = data;

        addItemType(TYPE_PROVINCE, R.layout.item_province);
        addItemType(TYPE_CITY, R.layout.item_city);
        addItemType(TYPE_AREA, R.layout.item_area);

    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {

            case TYPE_PROVINCE:

                final ProvinceItem provinceItem = (ProvinceItem) item;

                holder.setText(R.id.title, provinceItem.name)
                        .setImageResource(R.id.iv, provinceItem.isExpanded() ? R.mipmap.arrow_b : R.mipmap.arrow_r);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int pos = holder.getAdapterPosition();

                        if (provinceItem.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                break;
            case TYPE_CITY:

                final CityItem cityItem = (CityItem) item;

                holder.setText(R.id.title, cityItem.name)
                        .setImageResource(R.id.iv, cityItem.isExpanded() ? R.mipmap.arrow_b : R.mipmap.arrow_r);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int pos = holder.getAdapterPosition();

                        if (cityItem.isExpanded()) {
                            collapse(pos, false);
                        } else {
                            expand(pos, false);
                        }
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        //此处是长按事件
                        return true;
                    }
                });
                break;
            case TYPE_AREA:

                final AreaItem areaItem = (AreaItem) item;

                holder.setText(R.id.tv, areaItem.name);

                break;
        }
    }

    /**
     * 根据字母索引
     * @param letter
     * @return
     */
    public int getIndex(String letter) {
        for (MultiItemEntity entity : data) {
            if (entity.getItemType() == TYPE_PROVINCE){
                ProvinceItem item = (ProvinceItem) entity;
                if (item.getSortLetters().equals(letter)) {
                    return data.indexOf(entity);
                }
            }
        }
        return -1;
    }

}
