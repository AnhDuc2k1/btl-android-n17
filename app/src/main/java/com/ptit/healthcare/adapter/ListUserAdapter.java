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
import com.ptit.healthcare.entities.User;

import java.util.List;

public class ListUserAdapter extends ArrayAdapter<User> {

    public ListUserAdapter(Context context, List<User> listUser) {
        super(context, R.layout.custom_item_listview, listUser);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        User user = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_item_listview, parent, false);
        }

        TextView userID = convertView.findViewById(R.id.userId);
        TextView name = convertView.findViewById(R.id.name);

        userID.setText("User ID: " + String.valueOf(user.getId()));
        name.setText("Username: " + user.getUsername());

        return convertView;
    }

}
