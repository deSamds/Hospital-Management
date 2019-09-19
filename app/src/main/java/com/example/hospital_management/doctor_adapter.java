package com.example.hospital_management;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class doctor_adapter extends BaseAdapter implements Filterable {

    Context c;
    ArrayList<Doctors> doc_array;
    LayoutInflater inflater;
    Doctors doctors;
    CustomFilter filter;
    ArrayList<Doctors> filterList;

    public doctor_adapter(Context context, ArrayList<Doctors> doctors) {
        this.c = context;
        this.doc_array = doctors;
        this.filterList = doc_array;
    }

    @Override
    public int getCount() {
        return doc_array.size();
    }

    @Override
    public Doctors getItem(int position) {
        return doc_array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return doc_array.indexOf(getItem(position));
    }

    @Override
    public View getView(final int position, View converterView, final ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (converterView == null) {
            converterView = inflater.inflate(R.layout.doctor_adapter_view_layout, null);
        }

        TextView dId = (TextView) converterView.findViewById(R.id.doc_id);
        TextView dName = (TextView) converterView.findViewById(R.id.doc_name);

        doctors = doc_array.get(position);
        dId.setText(doctors.getId());
        dName.setText(doctors.getName());


        return converterView;
    }

    @Override
    public Filter getFilter() {
        if(filter == null) {
            filter = new CustomFilter();
        }
        return filter;
    }

    //Inner Class
    class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            if(constraint != null && constraint.length()>0) {
                //constraint to upper
                constraint = constraint.toString().toUpperCase();

                ArrayList <Doctors> filters = new ArrayList<Doctors>();

                for(int i = 0 ; i < filterList.size() ; i++) {

                    if(filterList.get(i).getName().toUpperCase().contains(constraint)) {
                        Doctors doc = new Doctors(filterList.get(i).getId(),filterList.get(i).getName());
                        filters.add(doc);
                    }
                }
                results.count = filters.size();
                results.values = filters;
            }else {
                results.count = filterList.size();
                results.values = filterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            doc_array = (ArrayList<Doctors>)results.values;
            notifyDataSetChanged();
        }
    }
}
