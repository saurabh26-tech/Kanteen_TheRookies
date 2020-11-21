package com.TheRookies.kanteen.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.TheRookies.kanteen.Interface.ItemClickListner;
import com.TheRookies.kanteen.R;


public class CuisineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView CuisineName;
    public ImageView CuisineImage;
    public ItemClickListner listner;

    public CuisineViewHolder(@NonNull View itemView) {
        super(itemView);
        CuisineImage = (ImageView) itemView.findViewById(R.id.cuisine_image);
        CuisineName = (TextView) itemView.findViewById(R.id.cuisine_name);

    }

    public void setItemClickListner(ItemClickListner listner) {
        this.listner = listner;

    }

    @Override
    public void onClick(View view) {
        listner.onClick(view, getAdapterPosition(), false);

    }
}

