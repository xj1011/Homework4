package com.example.asus.my_app;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.listener.SaveListener;
import java.io.File;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

import cn.bmob.v3.listener.UploadFileListener;

public class RegisterActivity extends AppCompatActivity implements  View.OnClickListener{


    private EditText etusername;
    private EditText etpassword;
    private EditText etemail;
    private EditText eticon;
    private Button register;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initialize();
    }

    private void initialize() {

        etusername = (EditText) findViewById(R.id.et_username);
        etpassword = (EditText) findViewById(R.id.et_password);
        etemail = (EditText) findViewById(R.id.et_email);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);
        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
               // registerData();
                signup();
                break;
            case R.id.cancel:

                break;
        }
    }

    public  void  signup(){


        BmobUser user =new BmobUser();
        user.setUsername(etusername.getText().toString());
        user.setPassword(etpassword.getText().toString());
        user.setEmail(etemail.getText().toString());
//        user.setMobilePhoneNumber(phon.getText().toString());




        user.signUp(RegisterActivity.this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                //onSignupSuccess();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(RegisterActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }



    /**
     * 注册
     */
    private void registerData() {

        final String name = etusername.getText().toString();
        final String password = etpassword.getText().toString();
        final String email = etemail.getText().toString();
        String path ;

        //获取图片文件的路径(我在个文件路径下面放了一个1.jpg图片)
        path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/1.jpg";

        final BmobFile bmobFile = new BmobFile(new File(path));
        bmobFile.upload(this, new UploadFileListener() {
            @Override
            public void onSuccess() {
                //上传成功
                User user = new User();
                user.setUsername(name);
                //user.setIcon(icon);
                user.setEmail(email);
                user.setPassword(password);
                user.setIcon(bmobFile);
                //注册(这个是已经提供好的接口)
                user.signUp(RegisterActivity.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(RegisterActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onFailure(int i, String s) {
                Log.d("RegisterActivity", "报错了" + s.toString());
            }
        });
    }
}
