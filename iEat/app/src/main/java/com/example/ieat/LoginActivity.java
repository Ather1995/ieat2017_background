package com.example.ieat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import net.HttpMethod;
import net.NetConnection;

import Base.BaseActivity;
import Util.LogUtil;
import Util.ToastUtil;
import common.Config;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements OnClickListener{
    private EditText password;
    private EditText identity;
    private String login_type="tel";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        LogUtil.Log(this,"start","");
    }
    private void initView(){
        setContentView(R.layout.activity_login);
        ImageButton login = (ImageButton) findViewById(R.id.loginbutton);
        ImageButton register = (ImageButton) findViewById(R.id.registerbutton);
        ImageButton forgetPassword = (ImageButton) findViewById(R.id.forgetpassword);
        identity=(EditText)findViewById(R.id.identity);
        password = (EditText) findViewById(R.id.password);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        forgetPassword.setOnClickListener(this);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.loginbutton:
                ToastUtil.show(this,"login");


                if (TextUtils.isEmpty(password.getText())&&TextUtils.isEmpty(identity.getText())){
                    Toast.makeText(LoginActivity.this, R.string.account_or_password_cannot_be_empty,Toast.LENGTH_LONG).show();
                }else{
                    final ProgressDialog pd=ProgressDialog.show(LoginActivity.this,getString(R.string.connecting),getString(R.string.connect_to_sever));
                    send_login_info_to_sever(identity.getText().toString(),password.getText().toString(),login_type);//当填写的账号信息完整时与服务器进行交互
                    pd.dismiss();
                }


                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.registerbutton:
                ToastUtil.show(this,"register");

                if (TextUtils.isEmpty(password.getText())&&TextUtils.isEmpty(identity.getText())){
                    Toast.makeText(LoginActivity.this, R.string.account_or_password_cannot_be_empty,Toast.LENGTH_LONG).show();
                }else{
                    final ProgressDialog pd=ProgressDialog.show(LoginActivity.this,getString(R.string.connecting),getString(R.string.connect_to_sever));
                    send_register_info_to_sever(identity.getText().toString(),password.getText().toString(),login_type);//当填写的账号信息完整时与服务器进行交互
                    pd.dismiss();
                }


                break;
            case R.id.forgetpassword:
                ToastUtil.show(this,"forgetpassword");
                break;
            default:
                break;
        }
    }

    private void send_login_info_to_sever(String account,String password,String login_type){
        new NetConnection(Config.SERVE_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(LoginActivity.this,result,Toast.LENGTH_LONG).show();

            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail(String result) {
                Toast.makeText(LoginActivity.this,result,Toast.LENGTH_LONG).show();
            }
        },Config.REQUEST_TYPE,Config.LOGIN,
                Config.LOGIN_TYPE,login_type,
                Config.ACCOUNT,account,
                Config.PASSWORD,password);
    }

    private void send_register_info_to_sever(String account,String password,String register_type){
        new NetConnection(Config.SERVE_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(LoginActivity.this,result,Toast.LENGTH_LONG).show();
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail(String result) {
                Toast.makeText(LoginActivity.this,result,Toast.LENGTH_LONG).show();
            }
        },Config.REQUEST_TYPE,Config.REGISTER,
                Config.REGISTER_TYPE,register_type,
                Config.ACCOUNT,account,
                Config.PASSWORD,password);
    }
}

