package Adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.text.method.BaseKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ieat.R;

import org.w3c.dom.Text;

import java.util.List;

import Entity.Food;
import Entity.Recipe;
import Util.LogUtil;
import Util.XCRoundRectImageView;

/**
 * Created by fanmiaomiao on 2018/3/28.
 */

public class BasketRecipeAdapter extends RecyclerView.Adapter<BasketRecipeAdapter.BasketViewHolder> {
    Context mContext;
    List<Recipe> mDatas;
    private LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;
    public BasketRecipeAdapter(Context context, List<Recipe> datas) {
        this.mContext = context;
        this.mDatas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(BasketViewHolder holder, final int position) {
        holder.textView.setText(mDatas.get(position).getRecipeName());
        int resID = mContext.getResources().getIdentifier(mDatas.get(position).getImgName(), "drawable", mContext.getPackageName());
        holder.imageView.setBackground(mContext.getResources().getDrawable(resID));
        for(int i=0;i<5;i++){
            if(i>=mDatas.get(position).getStars()){
                int resIDD = mContext.getResources().getIdentifier("star_dark", "drawable", mContext.getPackageName());
                holder.stars[i].setBackground(mContext.getResources().getDrawable(resIDD));
            }
            else{
                int resIDD = mContext.getResources().getIdentifier("star_light", "drawable", mContext.getPackageName());
                holder.stars[i].setBackground(mContext.getResources().getDrawable(resIDD));
            }
        }
        String mIngrs = "";
        for(String s:mDatas.get(position).getIngredients()){
            mIngrs+=s+"、";
        }
        holder.textIngres.setText(mIngrs);
        for(int i=0;i<4;i++){
            if(i>=mDatas.get(position).getNutrition().size()){
                holder.nutrType[i].setBackground(null);
            }
            else{
                int resIDD = mContext.getResources().getIdentifier(mDatas.get(position).getNutrition().get(i).toString(), "drawable", mContext.getPackageName());
                holder.nutrType[i].setBackground(mContext.getResources().getDrawable(resIDD));
            }
        }
        if( mOnItemClickListener!= null){
            holder.itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });
            holder.itemView.setOnLongClickListener( new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(position);
                    return false;
                }
            });
        }
    }

    @Override
    public BasketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.basket_rec_layout, parent, false);
        BasketViewHolder holder = new BasketViewHolder(view);
        return holder;
    }


    class BasketViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private TextView textNuView;
        private TextView textIngres;
        private TextView textIngresType;
        private ImageView [] stars = {null,null,null,null,null};
        private ImageView [] nutrType = {null,null,null,null};
        private XCRoundRectImageView imageView;
        public BasketViewHolder(View view) {
            super(view);
            imageView = (XCRoundRectImageView) view.findViewById(R.id.basket_rec_img);
            imageView.setType(XCRoundRectImageView.TYPE_ROUND);
            imageView.setRoundRadius(20);
            textView = (TextView) view.findViewById(R.id.basket_rec_text);
            textNuView = (TextView) view.findViewById(R.id.nutrtionType);
            textNuView.getPaint().setFakeBoldText(true);
            textIngres = (TextView) view.findViewById(R.id.ingres);
            textIngresType = (TextView) view.findViewById(R.id.ingresType);
            textIngresType.getPaint().setFakeBoldText(true);
            stars[0] = (ImageView) view.findViewById(R.id.Star1);
            stars[1] = (ImageView) view.findViewById(R.id.Star2);
            stars[2] = (ImageView) view.findViewById(R.id.Star3);
            stars[3] = (ImageView) view.findViewById(R.id.Star4);
            stars[4] = (ImageView) view.findViewById(R.id.Star5);
            nutrType[0] = (ImageView) view.findViewById(R.id.basket_type1);
            nutrType[1] = (ImageView) view.findViewById(R.id.basket_type2);
            nutrType[2] = (ImageView) view.findViewById(R.id.basket_type3);
            nutrType[3] = (ImageView) view.findViewById(R.id.basket_type4);
        }
    }

    public interface OnItemClickListener{
        void onClick( int position);
        void onLongClick( int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }
}
