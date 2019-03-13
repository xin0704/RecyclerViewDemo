package com.xin.recyclerviewdemo.ui;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.github.promeg.pinyinhelper.Pinyin;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xin.recyclerviewdemo.R;
import com.xin.recyclerviewdemo.adapter.ExpandableItemAdapter;
import com.xin.recyclerviewdemo.entity.Province;
import com.xin.recyclerviewdemo.entity.item.AreaItem;
import com.xin.recyclerviewdemo.entity.item.CityItem;
import com.xin.recyclerviewdemo.entity.item.ProvinceItem;
import com.xin.recyclerviewdemo.others.LettersComparator;
import com.xin.recyclerviewdemo.view.CustomCircleView;
import com.xin.recyclerviewdemo.view.CustomSideBarView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IndexActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ExpandableItemAdapter mAdapter;

    private List<MultiItemEntity> mData = new ArrayList<>();

    private CustomSideBarView mSideBar;

    private CustomCircleView mCircleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initData();
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {

        recyclerView = findViewById(R.id.rv);
        //初始化适配器
        mAdapter = new ExpandableItemAdapter(mData);
        //初始化recyclerview布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //设置适配器
        recyclerView.setAdapter(mAdapter);
        //设置recyclerview布局管理器
        recyclerView.setLayoutManager(manager);

        //初始化字母索引
        mSideBar = findViewById(R.id.sidebar);
        //初始化选中字母提示view
        mCircleView = findViewById(R.id.cv);


        mSideBar.setSideBarListener(new CustomSideBarView.SideBarListener() {
            @Override
            public void itemSelected(String str) {

                //设置提示文字
                mCircleView.setText(str);

                //获取recycleview索引位置
                int index = mAdapter.getIndex(str);

                if (index != -1) {
                    LinearLayoutManager manager = (LinearLayoutManager)recyclerView.getLayoutManager();
                    //设置recyclerview滚动到指定位置
                    manager.scrollToPositionWithOffset(index, 0);
                }
            }

            @Override
            public void motionEventActionUp() {
                //当手指触摸事件移除时  隐藏布局
                mCircleView.setViewVisibility(View.GONE);
            }
        });

    }

    /**
     * 初始化数据
     */
    private void initData() {

        StringBuilder sb = new StringBuilder();

        //初始化asset资源文件管理器
        AssetManager manager = getAssets();

        try {
            //通过流读取数据
            BufferedReader br = new BufferedReader(new InputStreamReader(manager.open("city.json")));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "数据获取异常", Toast.LENGTH_SHORT).show();
            return;
        }

        try {

            //将读取到的数据转换成JsonArray
            JSONArray array = new JSONArray(sb.toString());
            //利用Gson解析数据
            List<Province> list = new Gson().fromJson(array.toString(), new TypeToken<List<Province>>() {
            }.getType());

            //汉字转拼音处理  便于后面按字母进行排序操作
            for (int i = 0; i < list.size(); i++) {

                //汉字转换成拼音
                String pinyin = Pinyin.toPinyin(list.get(i).getName(), "");
                //取第一个首字母
                String letters = pinyin.substring(0, 1).toUpperCase();
                // 正则表达式，判断首字母是否是英文字母
                if (letters.matches("[A-Z]")) {
                    list.get(i).setSortLetters(letters.toUpperCase());
                } else {
                    list.get(i).setSortLetters("#");
                }
            }
            //按字母排序
            Collections.sort(list, new LettersComparator());

            //开始填充数据
            for (int i = 0; i < list.size(); i++) {
                //初始化一级列表
                ProvinceItem provinceItem = new ProvinceItem(list.get(i).getName(),list.get(i).getSortLetters());

                for (int j = 0; j < list.get(i).getCity().size(); j++) {
                    //初始化二级列表
                    CityItem cityItem = new CityItem(list.get(i).getCity().get(j).getName());

                    for (int k = 0; k < list.get(i).getCity().get(j).getArea().size(); k++) {
                        //初始化三级列表
                        AreaItem areaItem = new AreaItem(list.get(i).getCity().get(j).getArea().get(k));
                        //填充二级列表中的数据
                        cityItem.addSubItem(areaItem);
                    }
                    //填充一级列表数据
                    provinceItem.addSubItem(cityItem);

                }

                mData.add(provinceItem);

            }


        } catch (JSONException e) {
            e.printStackTrace();

            Toast.makeText(this, "数据解析异常", Toast.LENGTH_SHORT).show();
            return;

        }
    }

}
