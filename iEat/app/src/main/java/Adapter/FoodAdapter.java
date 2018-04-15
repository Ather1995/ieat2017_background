package Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.ieat.R;

import java.util.List;

import Entity.Food;

/**
 * Created by lenovo on 2018/3/20.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    Context mContext;
    List<Food> mDatas;
    private LayoutInflater inflater;

    public FoodAdapter(Context context, List<Food> datas){
        this. mContext=context;
        this. mDatas=datas;
        inflater=LayoutInflater. from(mContext);
    }
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(FoodViewHolder holder, final int position) {
        int resID = mContext.getResources().getIdentifier(mDatas.get(position).toString(), "drawable", mContext.getPackageName());
        holder.imageView_img.setBackground(mContext.getResources().getDrawable(resID));
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.food_rec_layout,parent, false);
        FoodViewHolder holder= new FoodViewHolder(view);
        return holder;
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView_img;

        public FoodViewHolder(View view) {
            super(view);
            imageView_img=(ImageView) view.findViewById(R.id.food_rec_img);
        }
    }
}
