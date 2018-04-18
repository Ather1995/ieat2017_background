package atys;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.R;

import net.HttpMethod;
import net.NetConnection;

import common.Config;
public class AtyRegister extends AppCompatActivity{

    private EditText etAccount=null;
    private EditText etPassword=null;



    private String register_type=Config.TEL;//需要从前台获取注册类型，这里先假设是电话号码注册




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_login);

        etAccount=(EditText) findViewById(R.id.et_account);
        etPassword=(EditText)findViewById(R.id.et_password);

        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etPassword.getText())&&TextUtils.isEmpty(etAccount.getText())){
                    Toast.makeText(AtyRegister.this, R.string.account_or_password_cannot_be_empty,Toast.LENGTH_LONG).show();
                }else{
                    final ProgressDialog pd=ProgressDialog.show(AtyRegister.this,getString(R.string.connecting),getString(R.string.connect_to_sever));
                    send_info_to_sever(etAccount.getText().toString(),etPassword.getText().toString(),register_type);//当填写的账号信息完整时与服务器进行交互
                    pd.dismiss();
                }
            }
        });
    }

    private void send_info_to_sever(String account,String password,String register_type){
        new NetConnection(Config.SERVE_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(AtyRegister.this,result,Toast.LENGTH_LONG).show();
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail(String result) {
                Toast.makeText(AtyRegister.this,result,Toast.LENGTH_LONG).show();
            }
        },Config.REQUEST_TYPE,Config.REGISTER,
                Config.REGISTER_TYPE,register_type,
                Config.ACCOUNT,account,
                Config.PASSWORD,password);
    }
}
