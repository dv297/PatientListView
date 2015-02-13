package edu.sc.cse.rdc.todoapp;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PersonListAdapter extends ArrayAdapter<Person>{
		private final Context context;
		private ArrayList<Person> list;
	 	ViewHolder viewHolder;
	 	
	    private static class ViewHolder {
	        private TextView itemView;
	    }

	    public PersonListAdapter(Context context, ArrayList<Person> items) {
	        super(context, R.layout.activity_row_layout, items);
	        this.context = context;
	        list = items;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	      LayoutInflater inflater = (LayoutInflater) context
	          .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	      View rowView = inflater.inflate(R.layout.activity_row_layout, parent, false);
	      TextView textView = (TextView) rowView.findViewById(R.id.nameTextView);
	      textView.setText(list.get(position).getName());
	      return rowView;
	    }
	  } 


