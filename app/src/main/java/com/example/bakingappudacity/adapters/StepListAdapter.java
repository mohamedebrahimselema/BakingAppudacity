package com.example.bakingappudacity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingappudacity.R;
import com.example.bakingappudacity.models.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.RecipeViewHolder> {

    private ArrayList<Step> stepArrayList;
    private StepSelectedListener stepSelectedListener;

    public interface StepSelectedListener{
        void onStepSelected(int position);
    }


    public StepListAdapter(ArrayList<Step> stepArrayList, StepSelectedListener stepSelectedListener) {
        this.stepArrayList = stepArrayList;
        this.stepSelectedListener = stepSelectedListener;
    }


    public class RecipeViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {


        @BindView(R.id.step_name) TextView step_name;
        @BindView(R.id.step_video) TextView step_video;
        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            stepSelectedListener.onStepSelected(position);
        }
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_step,viewGroup,false);

        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder recipeViewHolder, int i) {
        Step step=stepArrayList.get(i);
        String step_video="Video: YES";
        if (step.getVideoURL().isEmpty()){
            step_video="Video: NO";
        }

        recipeViewHolder.step_name.setText(step.getShortDescription());
        recipeViewHolder.step_video.setText(step_video);
    }

    @Override
    public int getItemCount() {
        return stepArrayList.size();
    }
}
