package com.example.asus.my_app;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Date;
import java.text.SimpleDateFormat;


public class TrainerActivity extends AppCompatActivity {


    private MyDatabaseHelper dbHelper;
    private EditText name;
    private EditText age;
    private EditText style;
    private EditText price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainer);

        //db = this.openOrCreateDatabase("myMusic",MODE_PRIVATE,null);//判断数据库是否存在不存在就创建
        //dbHelper = this.openOrCreateDatabase("myMusic",MODE_PRIVATE,null);
        dbHelper = new MyDatabaseHelper(this, "Trainer.db", null, 1);

//        Button createDatabase = (Button) findViewById(R.id.create_database);
//        createDatabase.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dbHelper.getWritableDatabase();
//            }
//        });


        name = (EditText) findViewById(R.id.editText);
        age = (EditText) findViewById(R.id.editText2);
        style = (EditText) findViewById(R.id.editText3);
        price = (EditText) findViewById(R.id.editText4);



        Button addData = (Button) findViewById(R.id.send_button);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getWritableDatabase()会返回一个SQLiteDatabase对象
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                //获取作者和动态内容
                final String name1 = name.getText().toString();
                final String age1 = age.getText().toString();
                final String style1 = style.getText().toString();
                final String price1 = price.getText().toString();

//                //获取系统时间
//                SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
//                Date curDate =  new Date(System.currentTimeMillis());
//                String   time_now   =   formatter.format(curDate);

                values.put("name",name1);
                values.put("age",age1);
                values.put("style",style1);
                values.put("price",price1);
                //insert（）方法中第一个参数是表名，第二个参数是表示给表中未指定数据的自动赋值为NULL。第三个参数是一个ContentValues对象
                db.insert("Trainer", null, values);
                values.clear();

            }
        });


        Button back = (Button) findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainerActivity.this, Activity1.class);
                startActivity(intent);
            }
        });

        Button queryButton = (Button) findViewById(R.id.button3);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //指明去查询Book表。
                Cursor cursor = db.query("Trainer", null, null, null, null, null, null);
                //调用moveToFirst()将数据指针移动到第一行的位置。
                if (cursor.moveToFirst()) {
                    do {
                        //然后通过Cursor的getColumnIndex()获取某一列中所对应的位置的索引
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String age = cursor.getString(cursor.getColumnIndex("age"));
                        String style = cursor.getString(cursor.getColumnIndex("style"));
                        String price = cursor.getString(cursor.getColumnIndex("price"));
                        Log.d("TrainerActivity", "author is " + name);
                        Log.d("TrainerActivity", "time is " + age);
                        Log.d("TrainerActivity", "dynamic is " + style);
                        Log.d("TrainerActivity", "dynamic is " + price);

                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

    }

}
