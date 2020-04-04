package com.example.bakingappudacity.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.bakingappudacity.R;
import com.example.bakingappudacity.adapters.TabAdapter;
import com.example.bakingappudacity.models.Recipe;
import com.example.bakingappudacity.util.Constants;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeStepListFragment extends Fragment {

    @BindView(R.id.recipeViewPager)
    ViewPager recipeViewPager;
    @BindView(R.id.recipeTabLayout)
    TabLayout recipeTabLayout;

    private List<Fragment> tabFragmentsList=new ArrayList<>();
    private List<String> tabTitleList=new ArrayList<>();

    private Unbinder unbinder;
    private Recipe recipe;


    public RecipeStepListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().getParcelable(Constants.RECIPE_EXTRA) != null){
            recipe=getArguments().getParcelable(Constants.RECIPE_EXTRA);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step_list, container, false);
        unbinder=ButterKnife.bind(this,view);

        prepareTabDataResources();
        TabAdapter tabAdapter = new TabAdapter(getFragmentManager(), tabFragmentsList, tabTitleList);
        recipeViewPager.setAdapter(tabAdapter);
        recipeTabLayout.setTabTextColors(getResources().getColor(R.color.theme_secondary_text_inverted)
                ,getResources().getColor(R.color.theme_primary_text_inverted));
        recipeTabLayout.setupWithViewPager(recipeViewPager);

        return view;
    }

    private void prepareTabDataResources() {
        Bundle bundle=new Bundle();
        bundle.putParcelableArrayList(Constants.INGREDIENT_EXTRA,recipe.getIngredients());

        Bundle bundle1=new Bundle();
        bundle1.putParcelableArrayList(Constants.STEP_EXTRA,recipe.getSteps());

        IngredientTabFragment ingredientTabFragment=new IngredientTabFragment();
        ingredientTabFragment.setArguments(bundle);

        StepTabFragment stepTabFragment = new StepTabFragment();
        stepTabFragment.setArguments(bundle1);

        addTabData(stepTabFragment,"Steps");
        addTabData(ingredientTabFragment,"Ingredients");
    }

    private void addTabData(Fragment f, String title) {
        tabFragmentsList.add(f);
        tabTitleList.add(title);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }

}
