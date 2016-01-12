package com.example.umang.myapplication;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class TestRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Item> dataList;

    static final int TYPE_PARENT = 0;
    static final int TYPE_CELL = 1;

    public TestRecyclerViewAdapter(List<Item> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).type;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_PARENT: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card_big, parent, false);
                ParentViewHolder parentViewHolder = new ParentViewHolder(view);
                return parentViewHolder;
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_card_small, parent, false);
                ChildViewHolder childViewHolder = new ChildViewHolder(view);
                return childViewHolder;
            }
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = dataList.get(position);
        switch (getItemViewType(position)) {
            case TYPE_PARENT:
                final ParentViewHolder parentViewHolder = (ParentViewHolder) holder;
                parentViewHolder.refferalItem = item;
                parentViewHolder.txtParent.setText(item.text);
                parentViewHolder.txtParent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("iiiiiiiiiii","iiiiiiiiiiiii");
                        if (item.invisibleChildren == null) {
                            item.invisibleChildren = new ArrayList<Item>();
                            int count = 0;
                            int pos = dataList.indexOf(parentViewHolder.refferalItem);
                            while (dataList.size() > pos + 1 && dataList.get(pos + 1).type == TYPE_CELL) {
                                item.invisibleChildren.add(dataList.remove(pos + 1));
                                count++;
                            }
                            notifyItemRangeRemoved(pos + 1, count);
                        } else {
                            int pos = dataList.indexOf(parentViewHolder.refferalItem);
                            int index = pos + 1;
                            for (Item i : item.invisibleChildren) {
                                dataList.add(index, i);
                                index++;
                            }
                            notifyItemRangeInserted(pos + 1, index - pos - 1);
                            item.invisibleChildren = null;
                        }
                    }
                });
                break;
            case TYPE_CELL:
                ChildViewHolder childViewHolder = (ChildViewHolder) holder;
                childViewHolder.refferalItem = item;
                childViewHolder.txtChild.setText(item.text);

                break;
        }
    }

    public static class Item {
        public int type;
        public String text;
        public List<Item> invisibleChildren;

        public Item() {
        }

        public Item(int type, String text) {
            this.type = type;
            this.text = text;
        }
    }

    private static class ParentViewHolder extends RecyclerView.ViewHolder {
        public TextView txtParent;
        public Item refferalItem;

        public ParentViewHolder(View itemView) {
            super(itemView);
            txtParent = (TextView) itemView.findViewById(R.id.txtParent);
        }
    }

    private static class ChildViewHolder extends RecyclerView.ViewHolder {
        public TextView txtChild;
        public Item refferalItem;

        public ChildViewHolder(View itemView) {
            super(itemView);
            txtChild = (TextView) itemView.findViewById(R.id.txtChild);
        }
    }
}