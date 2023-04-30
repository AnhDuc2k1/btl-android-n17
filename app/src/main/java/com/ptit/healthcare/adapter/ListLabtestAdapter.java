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
import com.ptit.healthcare.entities.Labtest;
import com.ptit.healthcare.utils.FormatCurrency;

import java.util.List;

public class ListLabtestAdapter extends ArrayAdapter<Labtest> {

    public ListLabtestAdapter(Context context, List<Labtest> labtests) {
        super(context, R.layout.custom_labtest_item, labtests);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Labtest labtest = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_labtest_item, parent, false);

        }

        TextView labtestName = convertView.findViewById(R.id.labtestName);
        TextView price = convertView.findViewById(R.id.price);

        labtestName.setText(labtest.getName());
        price.setText("Giá: " + FormatCurrency.formatCurrencyVN(labtest.getPrice()) + "VNĐ");

        return convertView;
    }
}
