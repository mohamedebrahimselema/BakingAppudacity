package com.example.bakingappudacity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.example.bakingappudacity.R;
import com.example.bakingappudacity.fragments.RecipeStepDetailFragment;
import com.example.bakingappudacity.fragments.RecipeStepListFragment;
import com.example.bakingappudacity.fragments.StepTabFragment;
import com.example.bakingappudacity.models.Recipe;
import com.example.bakingappudacity.models.Step;
import com.example.bakingappudacity.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepListActivity extends AppCompatActivity implements StepTabFragment.StepListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @BindView(R.id.detail_container)
    FrameLayout detail_container;

    @Nullable @BindView(R.id.txt_help)
    TextView txt_help;

    private Recipe recipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_list);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().getParcelableExtra(Constants.RECIPE_EXTRA) != null){
            recipe=getIntent().getParcelableExtra(Constants.RECIPE_EXTRA);
            getSupportActionBar().setTitle(recipe.getName());
        }

        if (txt_help != null && getSupportFragmentManager().findFragmentByTag(Constants.DETAIL_TAG)!=null) {
            txt_help.setVisibility(View.GONE);
        }



        showRecipe();
    }

    private void showRecipe() {
        Bundle bundle=new Bundle();
        bundle.putParcelable(Constants.RECIPE_EXTRA,recipe);

        if (getSupportFragmentManager().findFragmentByTag(Constants.STEP_TAG)==null) {
            RecipeStepListFragment recipeStepListFragment = new RecipeStepListFragment();
            recipeStepListFragment.setArguments(bundle);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                    .replace(R.id.recipe_container, recipeStepListFragment,Constants.STEP_TAG);
            transaction.commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void stepClicked(Step step) {
        if (detail_container!=null){

            if (txt_help != null) {
                txt_help.setVisibility(View.GONE);
            }

            Bundle bundle=new Bundle();
            bundle.putParcelable(Constants.STEP_EXTRA,step);

            RecipeStepDetailFragment recipeStepDetailFragment=new RecipeStepDetailFragment();
            recipeStepDetailFragment.setArguments(bundle);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, recipeStepDetailFragment,Constants.DETAIL_TAG);
            transaction.commit();

        }else {
            Intent intent = new Intent(this, RecipeStepDetailActivity.class);
            intent.putExtra(Constants.STEP_EXTRA,step);
            intent.putExtra(Constants.RECIPE, recipe.getName());
            startActivity(intent);
        }
    }
}
