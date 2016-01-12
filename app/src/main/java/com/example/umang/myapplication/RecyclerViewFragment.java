package com.example.umang.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.umang.myapplication.TestRecyclerViewAdapter.*;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class RecyclerViewFragment extends Fragment {

    /*private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;*/
    //private RecipeAdapter mAdapter;
    private RecyclerViewMaterialAdapter mAdapter;
    
    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Ingredient beef = new Ingredient("beef");
        Ingredient cheese = new Ingredient("cheese");
        Ingredient salsa = new Ingredient("salsa");
        Ingredient tortilla = new Ingredient("tortilla");
        Ingredient ketchup = new Ingredient("ketchup");
        Ingredient bun = new Ingredient("bun");

        Recipe taco = new Recipe("taco", Arrays.asList(beef, cheese, salsa, tortilla));
        Recipe quesadilla = new Recipe("quesadilla", Arrays.asList(cheese, tortilla));
        Recipe burger = new Recipe("burger", Arrays.asList(beef, cheese, ketchup, bun));
        final List<Recipe> recipes = Arrays.asList(taco, quesadilla, burger);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        final RecipeAdapter recipeAdapter = new RecipeAdapter(getActivity(), recipes);
        recipeAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onListItemExpanded(int position) {
                Log.i("positionlog", "Expanded :" + position);
                Recipe expandedRecipe = (Recipe) recipes.get(position);
                recipeAdapter.notifyDataSetChanged();
                String toastMsg = "Expanded" + expandedRecipe.getName();
                Toast.makeText(getContext(),
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onListItemCollapsed(int position) {
                Log.i("positionlog", "Collapsed :" + position);
                Recipe collapsedRecipe = (Recipe) recipes.get(position);
                recipeAdapter.notifyDataSetChanged();
                String toastMsg = "Collapsed" + collapsedRecipe.getName();
                Toast.makeText(getContext(),
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
        mAdapter = new RecyclerViewMaterialAdapter(recipeAdapter);
        recyclerView.setAdapter(mAdapter);
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), recyclerView, null);
/*

        mAdapter = new RecipeAdapter(getActivity(), recipes);
        mAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onListItemExpanded(int position) {
                Recipe expandedRecipe = recipes.get(position);

                String toastMsg = "Expanded" + expandedRecipe.getName();
                Toast.makeText(getActivity(),
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onListItemCollapsed(int position) {
                Recipe collapsedRecipe = recipes.get(position);

                String toastMsg = "Collapsed" + collapsedRecipe.getName();
                Toast.makeText(getActivity(),
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

                                                                                                                                                                                                                                            recyclerView.setAdapter(mAdapter);
        mAdapter2 = new RecyclerViewMaterialAdapter(mAdapter);
        mAdapter2.notifyDataSetChanged();
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), recyclerView, null);
*/


        /*
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        //mAdapter = new RecyclerViewMaterialAdapter(new TestRecyclerViewAdapter(mContentItems));
        mRecyclerView.setAdapter(mAdapter);

        List<Item> dataList = new ArrayList<>();
        dataList.add(new Item(TYPE_PARENT, "parent1"));
        dataList.add(new Item(TYPE_CELL, "child"));
        dataList.add(new Item(TYPE_CELL, "child"));
        dataList.add(new Item(TYPE_CELL, "child"));
        dataList.add(new Item(TYPE_CELL, "child"));
        dataList.add(new Item(TYPE_CELL, "child"));
        dataList.add(new Item(TYPE_PARENT, "parent2"));
        dataList.add(new Item(TYPE_CELL, "child"));
        dataList.add(new Item(TYPE_CELL, "child"));
        dataList.add(new Item(TYPE_CELL, "child"));
        dataList.add(new Item(TYPE_PARENT, "parent3"));
        dataList.add(new Item(TYPE_CELL, "child"));
        dataList.add(new Item(TYPE_CELL, "child"));
        dataList.add(new Item(TYPE_CELL, "child"));
        dataList.add(new Item(TYPE_CELL, "child"));
        dataList.add(new Item(TYPE_CELL, "child"));
        dataList.add(new Item(TYPE_CELL, "child"));
        dataList.add(new Item(TYPE_CELL, "child"));
        dataList.add(new Item(TYPE_CELL, "child"));
        dataList.add(new Item(TYPE_PARENT, "parent4"));
        dataList.add(new Item(TYPE_CELL, "child"));
        dataList.add(new Item(TYPE_CELL, "child"));
        mAdapter = new RecyclerViewMaterialAdapter(new TestRecyclerViewAdapter(dataList));
        mRecyclerView.setAdapter(mAdapter);
        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);*/
    }
}