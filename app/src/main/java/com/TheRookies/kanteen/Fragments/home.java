package com.TheRookies.kanteen.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.TheRookies.kanteen.Model.Dish;
import com.TheRookies.kanteen.Model.Food;
import com.TheRookies.kanteen.R;
import com.TheRookies.kanteen.ViewHolder.CuisineViewHolder;
import com.TheRookies.kanteen.ViewHolder.DishViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;



public class home extends Fragment {
    RecyclerView CuisineList,DishList;
    DatabaseReference CuisineRef,DishRef;
    RecyclerView.LayoutManager layoutManager,alllayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);


        CuisineList=view.findViewById(R.id.cuisine);
        CuisineRef = FirebaseDatabase.getInstance().getReference().child("Cuisine");
        layoutManager = new LinearLayoutManager(getActivity());
        CuisineList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        CuisineList.setLayoutManager(layoutManager);

        DishList=view.findViewById(R.id.all_menu_recycler);
        DishRef = FirebaseDatabase.getInstance().getReference().child("Dishes");
        alllayoutManager = new LinearLayoutManager(getActivity());
        DishList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        DishList.setLayoutManager(alllayoutManager);

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        FirebaseRecyclerOptions<Food> options =
                new FirebaseRecyclerOptions .Builder<Food>()
                        .setQuery(CuisineRef, Food.class)
                        .build();

        FirebaseRecyclerAdapter<Food, CuisineViewHolder> adapter =
                new FirebaseRecyclerAdapter<Food, CuisineViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull CuisineViewHolder holder, int position, @NonNull final Food model) {
                        holder.CuisineName.setText(model.getCuisinename());

                        Picasso.get().load(model.getCuisineimageurl()).placeholder(R.drawable.kanteenanim).into(holder.CuisineImage);


//                        holder.itemView.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//
//                            }
//                        });


                    }

                    @NonNull
                    @Override
                    public CuisineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cuisine_layout, parent, false);
                        CuisineViewHolder holder = new CuisineViewHolder(view);
                        return holder;
                    }
                };
        CuisineList.setAdapter(adapter);
        adapter.startListening();
        FirebaseRecyclerOptions<Dish> menuoptions =
                new FirebaseRecyclerOptions .Builder<Dish>()
                        .setQuery(DishRef, Dish.class)
                        .build();

        FirebaseRecyclerAdapter<Dish, DishViewHolder> menuadapter =
                new FirebaseRecyclerAdapter<Dish, DishViewHolder>(menuoptions) {
                    @Override
                    protected void onBindViewHolder(@NonNull DishViewHolder holder, int position, @NonNull final Dish model) {
                        holder.cusineName.setText(model.getCuisinename());
                        holder.DishName.setText(model.getDishname());
                        holder.price.setText(model.getDishprice());
                        holder.rating.setText(model.getRatings());


                        Picasso.get().load(model.getDishImageUrl()).placeholder(R.drawable.kanteenanim).into(holder.DishImage);


                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

//                                Intent intent = new Intent(getActivity(), AddDishActivity.class);
//                                intent.putExtra("CuisineName", model.getCuisinename());
//                                startActivity(intent);




                            }
                        });


                    }

                    @NonNull
                    @Override
                    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allmenu_layout, parent, false);
                        DishViewHolder holder = new DishViewHolder(view);
                        return holder;
                    }
                };
        DishList.setAdapter(menuadapter);

        menuadapter.startListening();
    }
}
