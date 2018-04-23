package atys;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.project.R;

import net.HttpMethod;
import net.NetConnection;

import common.Config;

public class updateAction extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.recvie_foodlist);
        final ProgressDialog pd=ProgressDialog.show(updateAction.this,getString(R.string.connecting),getString(R.string.connect_to_sever));
        send_info_to_sever();//当填写的账号信息完整时与服务器进行交互
        pd.dismiss();
//        Log.e("1111","22222222");
    }

    private void send_info_to_sever(){
        new NetConnection(Config.SERVE_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(updateAction.this,result,Toast.LENGTH_LONG).show();
                Log.e("send...","succeed");
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail(String result) {
                Toast.makeText(updateAction.this,result,Toast.LENGTH_LONG).show();
//            System.out.println("123");
                Log.e("send...","fail");
            }
        },Config.REQUEST_TYPE,Config.UPDATE_ACTION);
    }
}
