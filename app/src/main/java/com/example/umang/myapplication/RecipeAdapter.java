package com.example.umang.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

public class RecipeAdapter extends ExpandableRecyclerAdapter<RecipeViewHolder, IngredientViewHolder> {

    private LayoutInflater mInflator;

    public RecipeAdapter(final Context context, @NonNull final List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflator = LayoutInflater.from(context);
        /*this.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onListItemExpanded(int position) {
                Log.i("positionlog", "Expanded :" + position);
                Recipe expandedRecipe = (Recipe) parentItemList.get(position);

                String toastMsg = "Expanded" + expandedRecipe.getName();
                Toast.makeText(context,
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onListItemCollapsed(int position) {
                Log.i("positionlog", "Collapsed :" + position);
                Recipe collapsedRecipe = (Recipe) parentItemList.get(position);

                String toastMsg = "Collapsed" + collapsedRecipe.getName();
                Toast.makeText(context,
                        toastMsg,
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });*/
    }

    @Override
    public RecipeViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View recipeView = mInflator.inflate(R.layout.recipe_view, parentViewGroup, false);
        return new RecipeViewHolder(recipeView);
    }

    @Override
    public IngredientViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View ingredientView = mInflator.inflate(R.layout.ingredient_view, childViewGroup, false);
        return new IngredientViewHolder(ingredientView);
    }

    @Override
    public void onBindParentViewHolder(RecipeViewHolder recipeViewHolder, int position, ParentListItem parentListItem) {
        Recipe recipe = (Recipe) parentListItem;
        recipeViewHolder.bind(recipe);
    }

    @Override
    public void onBindChildViewHolder(IngredientViewHolder ingredientViewHolder, int position, Object childListItem) {
        Ingredient ingredient = (Ingredient) childListItem;
        ingredientViewHolder.bind(ingredient);
    }
}
