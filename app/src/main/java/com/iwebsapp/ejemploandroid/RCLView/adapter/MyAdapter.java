package com.iwebsapp.ejemploandroid.RCLView.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;
import com.iwebsapp.ejemploandroid.R;
import com.iwebsapp.ejemploandroid.RCLView.model.Item;

import java.util.List;

import okhttp3.internal.Util;

class MyViewHolderWithouChild extends RecyclerView.ViewHolder {

    TextView textView;

    MyViewHolderWithouChild(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
    }
}

class MyViewHolderWithChild extends RecyclerView.ViewHolder{

    public TextView textView, textViewChild;
    public RelativeLayout button;
    public ExpandableLinearLayout expandableLayout;

    public MyViewHolderWithChild(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
        textViewChild = itemView.findViewById(R.id.textViewChild);
        button = itemView.findViewById(R.id.button);
        expandableLayout = itemView.findViewById(R.id.expandableLayout);
    }
}

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Item> items;
    Context context;
    SparseBooleanArray expandState = new SparseBooleanArray();

    public MyAdapter(List<Item> items) {
        this.items = items;
        for (int i=0; i<items.size(); i++){
            expandState.append(i, false);
        }
    }

    @Override
    public int getItemViewType(int position) {
       if (items.get(position).isExpandable())
           return 1;
       else
           return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        if (viewType == 0){
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.item_without_child, parent, false);
            return new MyViewHolderWithouChild(view);
        }else {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.item_with_child, parent, false);
            return new MyViewHolderWithChild(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()){
            case 0:{
                MyViewHolderWithouChild viewHolder = (MyViewHolderWithouChild)holder;
                Item item = items.get(position);
                viewHolder.setIsRecyclable(false);
                viewHolder.textView.setText(item.getText());
            }break;
            case 1:{
                final MyViewHolderWithChild viewHolder = (MyViewHolderWithChild)holder;
                Item item = items.get(position);
                viewHolder.setIsRecyclable(false);
                viewHolder.textView.setText(item.getText());

                viewHolder.expandableLayout.setInRecyclerView(true);
                viewHolder.expandableLayout.setExpanded(expandState.get(position));
                viewHolder.button.setRotation(expandState.get(position)?180f:0f);
                viewHolder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewHolder.expandableLayout.toggle();
                    }
                });
                viewHolder.textViewChild.setText(items.get(position).getDescription());
                viewHolder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {

                    @Override
                    public void onPreOpen() {
                        changeRotate(viewHolder.button, 0f, 180f).start();
                        expandState.put(position, true);
                    }

                    @Override
                    public void onPreClose() {
                        changeRotate(viewHolder.button, 180f, 0f).start();
                        expandState.put(position, false );
                    }

                });
            }break;
            default:
                break;
        }

    }

    private ObjectAnimator changeRotate(RelativeLayout button, float from, float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(button, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
