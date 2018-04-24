package Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ieat.HomeActivity;
import com.example.ieat.R;
import com.example.ieat.RecipeActivity;
import com.example.ieat.RecipeIndexActivity;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import Adapter.BasketRecipeAdapter;
import Adapter.MyRecyclerAdapter;
import Util.MyBanner;
import Util.ToastUtil;

/**
 * Created by fanmiaomiao on 2018/3/12.
 */

public class HomeFragment extends Fragment {
    private MyBanner banner;
    //设置图片资源:url或本地资源
    String[] images_ = new String[]{
            "http://182.254.220.56:8080/ieat/ieat_bnk.png",
            "http://182.254.220.56:8080/ieat/ieat_bnk.png",
            "http://182.254.220.56:8080/ieat/ieat_bnk.png"};

    private RecyclerView recyclerView;
    private List<String> mDatas;
    private MyRecyclerAdapter recycleAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart(){
        super.onStart();
        initBanner();
        initView();
    }
    private void initView(){

        recyclerView = (RecyclerView) getView().findViewById(R.id.home_recView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch(newState)
                {
                    case 0:
                        if(isSlideToBottom(recyclerView)) {
                            ((HomeActivity) getActivity()).hideBottom();
                        }
                        else{
                            ((HomeActivity) getActivity()).showBottom();
                        }
                        break;
                    case 1:
                        ((HomeActivity) getActivity()).hideBottom();
                        break;
                    case 2:
                        break;
                }
            }
        });
        initData();
        recycleAdapter= new MyRecyclerAdapter(getContext(), mDatas );
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recyclerView.setAdapter(recycleAdapter);
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recycleAdapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                if(position!=0){
                    return;
                }
                Intent intent=new Intent(getActivity(), RecipeIndexActivity.class);
                startActivity(intent);
            }
            @Override
            public void onLongClick(int position) {
            }
        });
    }
    private void initData() {
        mDatas = new ArrayList<String>();
        for ( int i=0; i < 4; i++) {
            mDatas.add("home_rec_"+ (i%4 + 1));
        }
    }

    private void initBanner(){
        banner = (MyBanner) getView().findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.isAutoPlay(true) ;
        banner.setDelayTime(1500);
        banner.setImageLoader(new GlideImageLoader());
        List<String> images = new ArrayList<>();
        for(String ss: images_){
            images.add(ss);
        }
        banner.setImages(images);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                ToastUtil.show(getContext(),"Banner"+position);
            }
        });
        banner.start();
        ImageButton button = (ImageButton) getView().findViewById(R.id.right_banner);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                banner.nextPage();
            }
        });
        ImageButton button_ = (ImageButton) getView().findViewById(R.id.left_banner);
        button_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                banner.lastPage();
            }
        });
    }
    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

}
