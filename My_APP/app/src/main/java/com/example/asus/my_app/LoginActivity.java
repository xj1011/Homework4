package com.example.asus.my_app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText etusername;
    private EditText etpassword;
    private Button login;
    private Button sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //这里的AppLication ID 写上自己创建项目得到的那个AppLication ID
        Bmob.initialize(this, "1a7a1cf93d94f96b0d868f364a7279e9");
        initialize();
        initView();
    }

    private void initView() {

    }

    private void initialize() {

        etusername = (EditText) findViewById(R.id.et_username);
        etpassword = (EditText) findViewById(R.id.et_password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        sign = (Button) findViewById(R.id.sign);
        sign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                final String username = etusername.getText().toString();
                String password = etpassword.getText().toString();

                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){
                    final BmobUser bmobUser = new BmobUser();
                    bmobUser.setUsername(username);
                    bmobUser.setPassword(password);

                    bmobUser.login(LoginActivity.this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            //获取到当前用户的信息
                            User user = BmobUser.getCurrentUser(LoginActivity.this,User.class);
                            //登录成功
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this,Activity1.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("name", username);
                            intent.putExtras(bundle);
                            startActivity(intent);

//                            Intent intent = new Intent(LoginActivity.this,MainInfoActivity.class);
//                            intent.putExtra("user",user);
//                            startActivity(intent);

                        }

                        @Override
                        public void onFailure(int i, String s) {
                            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
                break;
            case R.id.sign:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
