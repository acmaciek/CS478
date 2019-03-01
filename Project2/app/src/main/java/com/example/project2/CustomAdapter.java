package com.example.project2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;


//class that allows to display imge and two text fields in the same cell of list view
class CustomAdapter extends BaseAdapter {
    Data data = new Data();
    private Context context;

    public CustomAdapter(Context context) {
        this.context = context; //context passed from Main Activity
    }

    @Override
    public int getCount() {
        return data.phones.length;//length of the phones array
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //initialize
        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView textViewModel = convertView.findViewById(R.id.textView_model);
        TextView textViewDescription = convertView.findViewById(R.id.textView_description);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //create new layout inflater from the Main Activity Context
        convertView = inflater.inflate(R.layout.customlayout, null);

        imageView.setImageResource(data.images[position]); //display image of the device
        textViewModel.setText(data.phones[position]); //display the name of the device
        textViewDescription.setText(data.descriptions[position]); //display the description of the device
        return convertView;
    }
}