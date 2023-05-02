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
import com.ptit.healthcare.entities.Article;

import java.util.List;

public class ListArticleAdapter extends ArrayAdapter<Article> {


    public ListArticleAdapter(Context context, List<Article> listBS){
        super(context, R.layout.activity_view_detail,listBS);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Article article = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_view_detail,parent,false);
        }

        TextView tenBaiBao = convertView.findViewById(R.id.name);

        tenBaiBao.setText(article.getName());

        return convertView;
    }
}
