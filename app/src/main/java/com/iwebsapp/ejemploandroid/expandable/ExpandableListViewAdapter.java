package com.iwebsapp.ejemploandroid.expandable;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.iwebsapp.ejemploandroid.R;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listaDataHeader;
    private List<String> listText;
    //private HashMap<String, List<String>> listHashMap;

    /*String[] faqs = {
            "Question number 1",
            "Question number 2",
            "Question number 3"
    };
    String[][] answer = {{"Answer to question number 1"},
            {"Answer to question number 2"},
            {"Answer to question number 3"}
    };*/

    public ExpandableListViewAdapter(Context context, List<String> listaDataHeader, List<String> listText) {
        this.context = context;
        this.listaDataHeader = listaDataHeader;
        this.listText = listText;
    }

   /* ExpandableListViewAdapter(Context context) {
        this.context = context;
    }*/

    @Override
    public int getGroupCount() {
        //return faqs.length;
        return  listaDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        //return answer[i].length;
        //return Objects.requireNonNull(listHashMap.get(listaDataHeader.get(i))).size();
        return listText.get(i).length();
    }

    @Override
    public Object getGroup(int i) {
        //return faqs[i];
        return listaDataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        //return answer[i][i1];
        //return listHashMap.get(listaDataHeader.get(i)).get(i1);
        return listText.get(i).length();
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String questionFaq = (String)getGroup(i);
        if (view==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_faqs, null);
        }
        TextView questionFaqs = view.findViewById(R.id.txtTitle);
        questionFaqs.setTypeface(null, Typeface.BOLD);
        questionFaqs.setText(questionFaq);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final String answerFaqs = (String)getChild(i,i1);
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_faqs_answer, null);
        }
        TextView txtAnswerFaq = view.findViewById(R.id.txtQuestion);
        txtAnswerFaq.setText(answerFaqs);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
