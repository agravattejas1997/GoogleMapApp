package com.learn.googlemapapp.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.learn.googlemapapp.models.PlaceApi;

import java.util.ArrayList;

public class PlaceApiSuggestAdapter extends ArrayAdapter implements Filterable {
    ArrayList<String> results = new ArrayList<>();
    int resource;
    String in;

    PlaceApi placeApi = new PlaceApi();
    Context context;




    public PlaceApiSuggestAdapter(Context context, int resource) {
        super(context, resource);

        this.context =context;
        this.resource=resource;
    }


    public PlaceApiSuggestAdapter(Context context, int resource,String in) {
        super(context, resource);

        this.context =context;
        this.resource=resource;
        this.in = in;

    }


    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if (constraint!=null || in!=null)
                {
                    results=placeApi.autoComplete(constraint.toString());

                    filterResults.values = results;
                    filterResults.count = results.size();

                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (results!=null && results.count>0)
                {
                    notifyDataSetChanged();
                }
                else
                    notifyDataSetInvalidated();
            }
        };
        return filter;
    }

    @Override
    public int getCount() {

        return results.size();
    }

    @Override
    public String getItem(int position) {

        return results.get(position);
    }

}
