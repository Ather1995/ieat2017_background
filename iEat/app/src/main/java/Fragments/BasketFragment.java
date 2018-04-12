package Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ieat.HomeActivity;
import com.example.ieat.R;
import com.example.ieat.RecipeActivity;

import java.util.ArrayList;
import java.util.List;

import Adapter.BasketRecipeAdapter;
import Entity.Recipe;
import Util.Constant;
import Util.LogUtil;

public class BasketFragment extends Fragment {
    
    private RecyclerView basketRecyeclerView;
    private BasketRecipeAdapter basketRecipeAdapter;
    private List<Recipe> recipes = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,  Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_basket, null);
        return view;
    }
    @Override
    public void onStart(){
        super.onStart();
        initView();
    }
    
    private void initView(){
        if(recipes.size()>0){
            return;
        }
        basketRecyeclerView =  (RecyclerView) getView().findViewById(R.id.basket_recView);
        initRecipe();
        LogUtil.Log(getContext(),"RecipeNum",recipes.size()+"");
        basketRecipeAdapter = new BasketRecipeAdapter(getContext(), recipes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //设置布局管理器
        basketRecyeclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        basketRecyeclerView.setAdapter(basketRecipeAdapter);
        //设置增加或删除条目的动画
        basketRecyeclerView.setItemAnimator(new DefaultItemAnimator());
        basketRecipeAdapter.setOnItemClickListener(new BasketRecipeAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent=new Intent(getActivity(), RecipeActivity.class);
                startActivity(intent);
            }
            @Override
            public void onLongClick(int position) {
                Toast.makeText(getActivity(),"您长按点击了"+position+"行",Toast.LENGTH_SHORT).show();
            }
        });
        basketRecyeclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

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
    
    private void initRecipe(){
        ArrayList<Constant.Nutrtions> nutrs = new ArrayList<>();
        Recipe recipe = new Recipe("岩烤秋刀");
        recipe.setStars(4);
        recipe.setImgName("recipe_tankaoqiudao");
        nutrs.add(Constant.Nutrtions.MEAT);
        nutrs.add(Constant.Nutrtions.EGG);
        recipe.setNutrition(nutrs);
        ArrayList<String> ingrs = new ArrayList<>();
        String ingrs_qiudao[]={"南瓜","番茄","西蓝花","香菜","鸡蛋","牛奶","秋刀鱼"};
        for(String s:ingrs_qiudao){
            ingrs.add(s);
        }
        recipe.setIngredients(ingrs);
        recipes.add(recipe);
        ///////////////////////
        ArrayList<Constant.Nutrtions> nutrs1 = new ArrayList<>();
        Recipe recipe1 = new Recipe("南瓜蔬菜卷");
        recipe1.setStars(4);
        recipe1.setImgName("recipe_shucainanguajuan");
        nutrs1.add(Constant.Nutrtions.GRAIN);
        nutrs1.add(Constant.Nutrtions.EGG);
        recipe1.setNutrition(nutrs1);
        ArrayList<String> ingrs1 = new ArrayList<>();
        String ingrs_qiudao1[]={"南瓜","番茄","西蓝花","糯米粉","抹茶粉","香菜","鸡蛋","牛奶"};
        for(String s:ingrs_qiudao1){
            ingrs1.add(s);
        }
        recipe1.setIngredients(ingrs1);
        recipes.add(recipe1);

        /////////////////////
        ArrayList<Constant.Nutrtions> nutrs2 = new ArrayList<>();
        Recipe recipe2 = new Recipe("华府冰淇淋球");
        recipe2.setStars(3);
        recipe2.setImgName("recipe_huafubingqilinqiu");
        nutrs2.add(Constant.Nutrtions.MILK);
        nutrs2.add(Constant.Nutrtions.EGG);
        nutrs2.add(Constant.Nutrtions.GRAIN);
        recipe2.setNutrition(nutrs2);
        ArrayList<String> ingrs2 = new ArrayList<>();
        String ingrs_qiudao2[]={"糯米粉","抹茶粉","香菜","鸡蛋","牛奶","面粉","膨松剂","巧克力酱"};
        for(String s:ingrs_qiudao2){
            ingrs2.add(s);
        }
        recipe2.setIngredients(ingrs2);
        recipes.add(recipe2);


        ArrayList<Constant.Nutrtions> nutrs3 = new ArrayList<>();
        Recipe recipe3 = new Recipe("菲力牛排");
        recipe3.setStars(4);
        recipe3.setImgName("recipe_heihujiaofeiliniupai");
        nutrs3.add(Constant.Nutrtions.MEAT);
        nutrs3.add(Constant.Nutrtions.EGG);
        nutrs3.add(Constant.Nutrtions.MILK);
        recipe3.setNutrition(nutrs3);
        ArrayList<String> ingrs3 = new ArrayList<>();
        String ingrs_qiudao3[]={"里脊肉","黑胡椒粉","洋葱粒","黄油","意大利面","鸡蛋","番茄酱"};
        for(String s:ingrs_qiudao3){
            ingrs3.add(s);
        }
        recipe3.setIngredients(ingrs3);
        recipes.add(recipe3);

    }

    protected boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    @Override
    public void onPause(){
        super.onPause();
    }
}
