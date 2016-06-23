package com.work.midori.Seller.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.work.midori.R;
import com.work.midori.Seller.Data.SellerInfomation;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by hanwe on 16/4/1.
 */
public class SellerAdapter extends RecyclerView.Adapter<SellerAdapter.ViewHolder>
{
    public interface OnSellerItemClickListener
    {
        void OnClickMail(String EmailAdress);

        void OnClickCall(String iPhoneNumber);
    }

    private ArrayList<SellerInfomation> data;
    private OnSellerItemClickListener onSellerItemClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public CircleImageView imageView;
        public TextView Name, Position, Address, Phone, Email;
        public ImageButton bu_Emial, bu_Call;

        public ViewHolder(View itemView)
        {
            super(itemView);

            imageView = (CircleImageView) itemView.findViewById(R.id.seller_card_imageView);

            Name = (TextView) itemView.findViewById(R.id.text_name);
            Position = (TextView) itemView.findViewById(R.id.text_position);
            Email = (TextView) itemView.findViewById(R.id.text_email);
            Phone = (TextView) itemView.findViewById(R.id.text_phone);
            Address = (TextView) itemView.findViewById(R.id.text_address);

            bu_Emial = (ImageButton) itemView.findViewById(R.id.button_mail);
            bu_Call = (ImageButton) itemView.findViewById(R.id.button_call);

            bu_Emial.setOnClickListener(this);
            bu_Call.setOnClickListener(this);

        }

        @Override
        public void onClick(View v)
        {
            if (data != null && data.size() > 0)
            {
                final SellerInfomation choseItem = data.get(getLayoutPosition());

                switch (v.getId())
                {
                    case R.id.button_mail:
                    {
                        onSellerItemClickListener.OnClickMail(choseItem.strEmail);
                        break;
                    }
                    case R.id.button_call:
                    {
                        onSellerItemClickListener.OnClickCall(choseItem.phoneNumber);
                        break;
                    }
                }
            }
        }
    }

    public SellerAdapter(OnSellerItemClickListener onSellerItemClickListener, ArrayList<SellerInfomation> data)
    {
        this.onSellerItemClickListener = onSellerItemClickListener;
        this.data = data;
    }

    @Override
    public SellerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sellercontact, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SellerAdapter.ViewHolder holder, int position)
    {
        final SellerInfomation SellerInfomation = data.get(position);

        holder.Name.setText(SellerInfomation.strName);
        holder.Position.setText(SellerInfomation.strPosition);
        holder.Email.setText(SellerInfomation.strEmail);
        holder.Phone.setText(SellerInfomation.phoneNumber);

    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }


}
