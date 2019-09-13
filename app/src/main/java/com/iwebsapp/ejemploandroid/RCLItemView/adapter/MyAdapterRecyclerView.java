package com.iwebsapp.ejemploandroid.RCLItemView.adapter;

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
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;
import com.iwebsapp.ejemploandroid.R;
import com.iwebsapp.ejemploandroid.RCLItemView.model.ItemRecyclerView;

import java.util.List;

class MyViewHolderWithChild extends RecyclerView.ViewHolder{
    TextView textView, textViewChild;
    RelativeLayout button;
    ExpandableLinearLayout expandableLayout;

    MyViewHolderWithChild(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
        textViewChild = itemView.findViewById(R.id.textViewChild);
        button = itemView.findViewById(R.id.button);
        expandableLayout = itemView.findViewById(R.id.expandableLayout);
    }
}

public class MyAdapterRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ItemRecyclerView> items;
    private Context context;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    public MyAdapterRecyclerView(List<ItemRecyclerView> items) {
        this.items = items;
        for (int i=0; i<items.size(); i++){
            expandState.append(i, false);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_with_child, parent, false);
        return new MyViewHolderWithChild(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolderWithChild viewHolder = (MyViewHolderWithChild)holder;
        ItemRecyclerView item = items.get(position);
        viewHolder.setIsRecyclable(false);
        viewHolder.textView.setText(item.getQuestion());

        viewHolder.expandableLayout.setInRecyclerView(true);
        viewHolder.expandableLayout.setExpanded(expandState.get(position));
        viewHolder.button.setRotation(expandState.get(position)?180f:0f);
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.expandableLayout.toggle();
            }
        });

        viewHolder.textViewChild.setText(items.get(position).getAnswer());
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
