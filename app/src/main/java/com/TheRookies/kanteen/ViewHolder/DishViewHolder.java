package com.TheRookies.kanteen.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.TheRookies.kanteen.Interface.ItemClickListner;
import com.TheRookies.kanteen.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class DishViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView DishName,price,cusineName,rating;
    public CircleImageView DishImage;
    public ItemClickListner listner;

    public DishViewHolder(@NonNull View itemView) {
        super(itemView);
        DishImage =  itemView.findViewById(R.id.all_menu_image);
        DishName =  itemView.findViewById(R.id.dish_name_allmenu);
        price =  itemView.findViewById(R.id.all_menu_price);
        cusineName =  itemView.findViewById(R.id.cusine_allmenu);
        rating =  itemView.findViewById(R.id.all_menu_rating);




    }

    public void setItemClickListner(ItemClickListner listner) {
        this.listner = listner;

    }

    @Override
    public void onClick(View view) {
        listner.onClick(view, getAdapterPosition(), false);

    }
}