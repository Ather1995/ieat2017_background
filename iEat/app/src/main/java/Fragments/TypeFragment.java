package Fragments;

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
import android.widget.Button;
import android.widget.ListView;

import com.example.ieat.HomeActivity;
import com.example.ieat.R;
import com.example.ieat.SettingActivity;

import java.util.ArrayList;
import java.util.List;

import Adapter.FoodAdapter;
import Adapter.MyRecyclerAdapter;
import Entity.Food;
import Util.Constant;
import Util.XCRoundRectImageView;

/**
 * Created by fanmiaomiao on 2018/3/12.
 */

public class TypeFragment extends Fragment {

    private List<Food> foodList = new ArrayList<>();
    private RecyclerView foodRecyclerView = null;
    private FoodAdapter foodAdapter = null;
    private Button settingButton = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_type,container,false);
        return view;
    }
    @Override
    public void onStart(){
        super.onStart();
        initView();
    }
    private void initView(){
        settingButton = (Button) getView().findViewById(R.id.press_set);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
        foodRecyclerView = (RecyclerView) getView().findViewById(R.id.food_recView);
        initFood();
        foodAdapter = new FoodAdapter(getContext(), foodList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //设置布局管理器
        foodRecyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        foodRecyclerView.setAdapter(foodAdapter);
        //设置增加或删除条目的动画
        foodRecyclerView.setItemAnimator(new DefaultItemAnimator());
        foodRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

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
    }
    private void initFood(){
        int i=0;
        for(String ts: Constant.Foods){
            foodList.add(new Food(ts,i,i));
            i++;
        }
    }
    @Override
    public void onPause() {
        super.onPause();
    }

    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }
}
