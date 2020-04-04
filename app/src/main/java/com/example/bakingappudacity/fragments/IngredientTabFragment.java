package com.example.bakingappudacity.fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bakingappudacity.R;
import com.example.bakingappudacity.adapters.IngredientListAdapter;
import com.example.bakingappudacity.models.Ingredient;
import com.example.bakingappudacity.util.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class IngredientTabFragment extends Fragment {

    @BindView(R.id.ingredient_recycler)
    RecyclerView ingredient_recycler;
    private ArrayList<Ingredient> ingredientArrayList;
    private Unbinder unbinder;

    public IngredientTabFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().getParcelableArrayList(Constants.INGREDIENT_EXTRA) != null){
            ingredientArrayList=getArguments().getParcelableArrayList(Constants.INGREDIENT_EXTRA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ingredient_tab, container, false);
        unbinder=ButterKnife.bind(this,view);

        prepareList();
        return view;
    }

    private void prepareList() {
        IngredientListAdapter ingredientListAdapter = new IngredientListAdapter(ingredientArrayList);
        ingredient_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        ingredient_recycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        ingredient_recycler.setAdapter(ingredientListAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }
}
