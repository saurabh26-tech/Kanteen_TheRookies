package com.TheRookies.kanteen.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.TheRookies.kanteen.R;
import com.baoyachi.stepview.VerticalStepView;

import java.util.ArrayList;
import java.util.List;


public class food extends Fragment {
    int status;

    VerticalStepView verticalStepView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_food, container, false);




        verticalStepView=view.findViewById(R.id.verticalstepview);

        List<String> sources = new ArrayList<>();
        sources.add("Order Placed");
        sources.add("Cooking");
        sources.add("Food is Ready");



        verticalStepView.setStepsViewIndicatorComplectingPosition(sources.size()-2).reverseDraw(false)
                .setStepViewTexts(sources)
                .setLinePaddingProportion(0.85f)
                .setStepsViewIndicatorCompletedLineColor(Color.parseColor("#2A2A72"))
                .setStepViewComplectedTextColor(Color.parseColor("#0EBFE9"))
                .setStepViewUnComplectedTextColor(Color.parseColor("#8B0000"))
                .setStepViewComplectedTextColor(Color.parseColor("#0EBFE9"))
                .setStepsViewIndicatorUnCompletedLineColor(Color.parseColor("#8B0000"))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getActivity(), R.drawable.check_circle))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(getActivity(), R.drawable.report))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(getActivity(), R.drawable.radio));
        verticalStepView.setStepsViewIndicatorComplectingPosition(status);







        return view;

    }
}