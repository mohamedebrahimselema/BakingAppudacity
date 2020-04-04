package com.example.bakingappudacity.fragments;


import android.content.Context;
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
import com.example.bakingappudacity.adapters.StepListAdapter;
import com.example.bakingappudacity.models.Step;
import com.example.bakingappudacity.util.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StepTabFragment extends Fragment implements StepListAdapter.StepSelectedListener{


    @BindView(R.id.step_recycler)
    RecyclerView step_recycler;
    private ArrayList<Step> stepArrayList;
    private Unbinder unbinder;
    private StepListener stepListener;

    public interface StepListener{
        void stepClicked(Step step);
    }

    public StepTabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().getParcelableArrayList(Constants.STEP_EXTRA) != null){
            stepArrayList=getArguments().getParcelableArrayList(Constants.STEP_EXTRA);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step_tab, container, false);
        unbinder=ButterKnife.bind(this,view);

        prepareList();
        return view;
    }

    private void prepareList() {
        StepListAdapter stepListAdapter = new StepListAdapter(stepArrayList, this);
        step_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        step_recycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        step_recycler.setAdapter(stepListAdapter);
    }

    @Override
    public void onStepSelected(int position) {
        Step step=stepArrayList.get(position);
        stepListener.stepClicked(step);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            stepListener = (StepListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement StepListener");
        }
    }
}
