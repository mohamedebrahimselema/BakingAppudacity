package com.example.bakingappudacity.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.bakingappudacity.R;
import com.example.bakingappudacity.models.Recipe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    private ArrayList<Recipe> recipeArrayList;
    private RecipeSelectedListener recipeSelectedListener;

    public interface RecipeSelectedListener{
        void onRecipeSelected(int position);

        void onOptionMenuIconClick(TextView opt_recipe, int adapterPosition);
    }


    public RecipeListAdapter(ArrayList<Recipe> recipeArrayList, RecipeSelectedListener recipeSelectedListener) {
        this.recipeArrayList = recipeArrayList;
        this.recipeSelectedListener = recipeSelectedListener;
    }


    public class RecipeViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {


        @BindView(R.id.recipe_name) TextView recipe_name;
        @BindView(R.id.recipe_ingredient) TextView recipe_ingredient;
        @BindView(R.id.recipe_step) TextView recipe_step;
        @BindView(R.id.opt_recipe) TextView opt_recipe;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            recipeSelectedListener.onRecipeSelected(position);
        }
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_recipe,viewGroup,false);

        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecipeViewHolder recipeViewHolder, int i) {
        Recipe recipe=recipeArrayList.get(i);

        String ing=recipe.getIngredients().size() + " " +"Ingredients";
        String steps=recipe.getSteps().size() + " " +"Steps";

        recipeViewHolder.recipe_name.setText(recipe.getName());
        recipeViewHolder.recipe_ingredient.setText(ing);
        recipeViewHolder.recipe_step.setText(steps);

        recipeViewHolder.opt_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeSelectedListener.onOptionMenuIconClick(recipeViewHolder.opt_recipe,recipeViewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeArrayList.size();
    }
}
