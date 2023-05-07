package com.ptit.healthcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ptit.healthcare.R;
import com.ptit.healthcare.database.DepartmentQuery;
import com.ptit.healthcare.entities.Article;
import com.ptit.healthcare.entities.Department;

import java.util.List;

public class ListArticleAdapter extends ArrayAdapter<Article> {

    public ListArticleAdapter(Context context, List<Article> listBB){
        super(context, R.layout.list_view_article, listBB);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Article article = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_article, parent, false);
        }

        TextView tenBB=convertView.findViewById(R.id.textViewTenBB);

        //DepartmentQuery departmentQuery = new DepartmentQuery(convertView.getContext());

        tenBB.setText(article.getName_article());


        return convertView;
    }
}
