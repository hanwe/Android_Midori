package com.work.midori.Main.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.work.midori.Main.ProductsInfomationClass;
import com.work.midori.R;
import com.work.midori.Util.Configuration;

import java.util.ArrayList;

/**
 * Created by hanwe on 16/4/21.
 */
public class MianCardAdapter extends RecyclerView.Adapter<MianCardAdapter.ViewHolder>
{
    public interface OnMainItemClickListener
    {
        void OnItemClick(View view, int iPosition);
    }

    private ArrayList<ProductsInfomationClass> mProductList;
    private OnMainItemClickListener onMainItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private ImageView imageView;
        private TextView tvTitle;
        private TextView tvContent;

        public ViewHolder(View itemView)
        {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.main_card_imageView);
            tvTitle = (TextView) itemView.findViewById(R.id.main_card_title);
            tvContent = (TextView) itemView.findViewById(R.id.main_card_content);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v)
        {
            switch (getLayoutPosition())
            {
                case 0:
                {
                    onMainItemClickListener.OnItemClick(v, Configuration.HomeEquipment);
                    break;
                }
                case 1:
                {
                    onMainItemClickListener.OnItemClick(v, Configuration.RecycledWater);
                    break;
                }
                case 2:
                {
                    onMainItemClickListener.OnItemClick(v, Configuration.WaterTreatment);
                    break;
                }
                case 3:
                {
                    onMainItemClickListener.OnItemClick(v, Configuration.WaterTreatmentRecycle);
                    break;
                }
            }



        }
    }

    public MianCardAdapter(ArrayList<ProductsInfomationClass> mProductList, OnMainItemClickListener onItemClickListener)
    {
        this.mProductList = mProductList;
        this.onMainItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_care_view, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        if(mProductList.size() > 0)
        {
            holder.imageView.setImageDrawable(mProductList.get(position).dImageResouce);
            holder.tvTitle.setText(mProductList.get(position).strTitle);
            holder.tvContent.setText(mProductList.get(position).strContent);
        }
    }

    @Override
    public int getItemCount()
    {
        return mProductList.size();
    }


}
