package com.example.asus.my_app;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Activity1 extends AppCompatActivity implements View.OnClickListener{

    private TextView title, item_me,item_kecheng,item_faxian,item_weixin,etusername;
    private ViewPager vp;
    private OneFragment oneFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private ForthFragment forthFragmen;

    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;

    private MyDatabaseHelper dbHelper;


    String[] titles = new String[]{"首页", "课程", "发现", "我"};
    String name;
    String mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除工具栏
        getSupportActionBar().hide();
        setContentView(R.layout.activity11);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
//        name = bundle.getString("name");
        dbHelper = new MyDatabaseHelper(this, "Trainer.db", null, 1);


        try {
            name = bundle.getString("name");
        }catch (NullPointerException e){

        }
        initViews();

        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);
        vp.setOffscreenPageLimit(4);//ViewPager的缓存为4帧
        vp.setAdapter(mFragmentAdapter);
        vp.setCurrentItem(0);//初始设置ViewPager选中第一帧
        item_weixin.setTextColor(Color.parseColor("#66CDAA"));



        //ViewPager的监听事件
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                /*此方法在页面被选中时调用*/
                title.setText(titles[position]);
                changeTextColor(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                /*此方法是在状态改变的时候调用，其中arg0这个参数有三种状态（0，1，2）。
                arg0 ==1的时辰默示正在滑动，
                arg0==2的时辰默示滑动完毕了，
                arg0==0的时辰默示什么都没做。*/
            }
        });
    }

    /**
     * 初始化布局View
     */
    private void initViews() {
        title = (TextView) findViewById(R.id.title);

        item_me = (TextView) findViewById(R.id.item_me);
        item_weixin = (TextView) findViewById(R.id.item_weixin);
        item_kecheng = (TextView) findViewById(R.id.item_kecheng);
        item_faxian = (TextView) findViewById(R.id.item_faxian);


        item_me.setOnClickListener(this);
        item_weixin.setOnClickListener(this);
        item_kecheng.setOnClickListener(this);
        item_faxian.setOnClickListener(this);


        vp = (ViewPager) findViewById(R.id.mainViewPager);
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        forthFragmen = new ForthFragment();

        String AllDynamic="";
        try{
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //指明去查询MyDynamic表。
            Cursor cursor = db.query("Trainer", null, null, null, null, null, null);
            //调用moveToFirst()将数据指针移动到第一行的位置。
            if (cursor.moveToFirst()) {
                do {
                    //然后通过Cursor的getColumnIndex()获取某一列中所对应的位置的索引
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String age = cursor.getString(cursor.getColumnIndex("age"));
                    String style = cursor.getString(cursor.getColumnIndex("style"));
                    String price = cursor.getString(cursor.getColumnIndex("price"));

                    AllDynamic += "Name: "+name+"\r\n"+"Age: "+age+"\r\n"+"Style: "+style+"\r\n"+"Price(每小时）: "+price+"\r\n"+"\r\n";

                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch(NullPointerException e){

        }

        //给FragmentList添加数据
        mFragmentList.add(oneFragment);
        mFragmentList.add(twoFragment);
        mFragmentList.add(threeFragment);
        mFragmentList.add(forthFragmen);

        getmTitles();
        setmTitles(AllDynamic);
    }

    public String getmTitles(){
        return mTitle;
    }

    public void setmTitles(String title) {
        this.mTitle = title;
    }


    /**
     * 点击底部Text 动态修改ViewPager的内容
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_weixin:
                vp.setCurrentItem(0, true);
                break;
            case R.id.item_kecheng:
                vp.setCurrentItem(1, true);
                break;
            case R.id.item_faxian:
                vp.setCurrentItem(2, true);
                break;
            case R.id.item_me:
                vp.setCurrentItem(3, true);
                break;
        }
    }


    public class FragmentAdapter extends FragmentPagerAdapter {

        List<Fragment> fragmentList = new ArrayList<Fragment>();

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }

    private void changeTextColor(int position) {
        if (position == 0) {
            //item_weixin.setTextColor(Color.parseColor("#66CDAA"));
            item_weixin.setTextColor(Color.parseColor("#66CDAA"));
            item_kecheng.setTextColor(Color.parseColor("#000000"));
            item_faxian.setTextColor(Color.parseColor("#000000"));
            item_me.setTextColor(Color.parseColor("#000000"));
        } else if (position == 1) {
            item_kecheng.setTextColor(Color.parseColor("#66CDAA"));
            item_weixin.setTextColor(Color.parseColor("#000000"));
            item_faxian.setTextColor(Color.parseColor("#000000"));
            item_me.setTextColor(Color.parseColor("#000000"));
        } else if (position == 2) {
            item_faxian.setTextColor(Color.parseColor("#66CDAA"));
            item_weixin.setTextColor(Color.parseColor("#000000"));
            item_kecheng.setTextColor(Color.parseColor("#000000"));
            item_me.setTextColor(Color.parseColor("#000000"));
        } else if (position == 3) {
            item_me.setTextColor(Color.parseColor("#66CDAA"));
            item_weixin.setTextColor(Color.parseColor("#000000"));
            item_kecheng.setTextColor(Color.parseColor("#000000"));
            item_faxian.setTextColor(Color.parseColor("#000000"));
        }
    }

}
