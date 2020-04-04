package com.example.bakingappudacity.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.example.bakingappudacity.R;
import com.example.bakingappudacity.fragments.RecipeStepDetailFragment;
import com.example.bakingappudacity.models.Step;
import com.example.bakingappudacity.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String recipe_name;
    private Step step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        boolean landScape;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            landScape =true;
            addFullScreenParameters();
        }else{
            landScape =false;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_detail);
        ButterKnife.bind(this);

        if (getIntent().getParcelableExtra(Constants.STEP_EXTRA)!=null){
            step=getIntent().getParcelableExtra(Constants.STEP_EXTRA);
        }

        if (getIntent().getStringExtra(Constants.RECIPE) != null){
            recipe_name=getIntent().getStringExtra(Constants.RECIPE);
        }

        if (!landScape) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(recipe_name);
        }else {
            toolbar.setVisibility(View.GONE);
        }

        showDetail();

    }

    private void addFullScreenParameters(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void showDetail() {
        Bundle bundle=new Bundle();
        bundle.putParcelable(Constants.STEP_EXTRA,step);

        if (getSupportFragmentManager().findFragmentByTag(Constants.STEP_DETAIL_TAG)==null) {

            RecipeStepDetailFragment recipeStepDetailFragment = new RecipeStepDetailFragment();
            recipeStepDetailFragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                    .replace(R.id.recipe_container, recipeStepDetailFragment,Constants.STEP_DETAIL_TAG);
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
}
