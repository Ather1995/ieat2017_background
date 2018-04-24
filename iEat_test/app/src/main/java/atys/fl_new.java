package atys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.project.R;

import net.HttpMethod;
import net.NetConnection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Adapter.SimpleAdapter;
import Entity.Data;
import Util.Constant;
import common.Config;

public class fl_new extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recvie_foodlist);
        initDatas();
        assignViews();
        initRecyclerview();
//        Log.e("1111","22222222");
    }

    private SimpleAdapter mAdapter;
    private void initRecyclerview() {
        //设置mRecyclerview的item向下排列
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mAdapter=new SimpleAdapter(this,R.layout.item_data,mDatas);
        mRecyclerview.setAdapter(mAdapter);
        //设置mRecyclerview的item单击事件
        mAdapter.setOnItemClickListener(new SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(fl_new.this,"toast"+position,Toast.LENGTH_SHORT).show();
/*
*点击菜品触发的事件
 *  */
                String userId="123";
                String foodId="987";
                String isCollection=Config.YES;

                Date time = new Date(System.currentTimeMillis());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String clickTime = sdf.format(time);
                String star="2";
                Log.e("clickTime",clickTime);

                send_info_to_sever(userId,foodId,isCollection,clickTime,star);//当填写的账号信息完整时与服务器进行交互

            }
        });
    }

    private List<Data> mDatas;
    //初始化数据
    private void initDatas() {
        mDatas=new ArrayList<>();
//        for (int i=0;i<11;i++){
//            mDatas.add(new Data("ddddd","title"+i));
//        }
        int i=0;
        for(String ts: Constant.Foods){
            mDatas.add(new Data(ts,"decri"+i));
            i++;
        }
    }

    private RecyclerView mRecyclerview;
    //初始化布局id
    private void assignViews() {
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
    }

//    private void send_info_to_sever(int userId,int foodId, boolean isCollection, String clickTime, int star){
//        new NetConnection(Config.SERVE_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
//            @Override
//            public void onSuccess(String result) {
//                Toast.makeText(fl_new.this,result,Toast.LENGTH_LONG).show();
//
//            }
//        }, new NetConnection.FailCallback() {
//            @Override
//            public void onFail(String result) {
//                Toast.makeText(fl_new.this,result,Toast.LENGTH_LONG).show();
//            }
//        },Config.REQUEST_TYPE,Config.FOOD_CLICK,
//                Config.USERID,userId,
//                Config.FOODID,foodId,
//                Config.ISCOLLECTION,);
//    }
private void send_info_to_sever(String userId,String foodId, String isCollection, String clickTime, String star){
    new NetConnection(Config.SERVE_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
        @Override
        public void onSuccess(String result) {
            Toast.makeText(fl_new.this,result,Toast.LENGTH_LONG).show();
            Log.e("send...","succeed");
        }
    }, new NetConnection.FailCallback() {
        @Override
        public void onFail(String result) {
            Toast.makeText(fl_new.this,result,Toast.LENGTH_LONG).show();
//            System.out.println("123");
            Log.e("send...","fail");
        }
    },Config.REQUEST_TYPE,Config.FOOD_CLICK,
            Config.USERID,userId,
            Config.FOODID,foodId,
            Config.CLICKTIME,clickTime,
            Config.ISCOLLECTION,isCollection,
            Config.STAR,star);
}

}
