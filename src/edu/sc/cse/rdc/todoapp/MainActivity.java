package edu.sc.cse.rdc.todoapp;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	// 1. Define every element that we want interactions with
	Button submitButton;
	ListView personListView;
	
	// We made these static so that they could be altered
	// from the removePatient method that will be called 
	// in other activities
	static ArrayList<Person> personList;
	static PersonListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		personList = new ArrayList<Person>();
		
		// 2. "Find" the element (Initialize/link)
		submitButton = (Button) findViewById(R.id.submitButton);
		personListView = (ListView) findViewById(R.id.personListView);

		
		// 3. Connect the ListView to an Adapter, then connect that adapter to the view itself
		adapter = new PersonListAdapter(this, personList);
		personListView.setAdapter(adapter);
		
		// 4. Give an action to the button
		submitButton.setOnClickListener(new OnClickListener(){

			// When the button is clicked, everything in the following method will be performed
			@Override
			public void onClick(View v) {
				goToForm();
			}});
		
		// 5. Give the list view items some action when clicked on
		personListView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// We pass in the index just in case we need to delete this patient
				goToViewData(position);
				
			}
			
		});
		
	}
	
	/**
	 * This method starts a new intent, and then starts the activity associated with the intent
	 */
	public void goToForm()
	{
		Intent i = new Intent(this, AddPatientActivity.class);
		startActivityForResult(i, 0);
	}
	
	/*
	 * This method takes you to see the patient's data.
	 * The index we pass in is the index of the patient in patientListView.
	 */
	public void goToViewData(int index)
	{
		Intent i = new Intent(this, ViewPatientActivity.class);
		Person person = personList.get(index);
		i.putExtra("name", person.getName());
		i.putExtra("age", person.getAge());
		i.putExtra("height", person.getHeight());
		i.putExtra("index", index);
		startActivity(i);
	}
	
	/**
	 * When we start the activity, things can either go well (RESULT_OK) or not (RESULT_CANCELED)
	 * These two if statements handle what happens
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
        if(resultCode == RESULT_OK)
        {
            String name_val = data.getStringExtra("name");
            int age_val = Integer.parseInt((data.getStringExtra("age")));
            double height_val = Double.parseDouble(data.getStringExtra("height"));
            
            
        	// We assume that data validation is done on the actual form this data is collected            
            Person p = new Person(name_val, age_val, height_val);
            personList.add(p);
			adapter.notifyDataSetChanged();

            
        }
        if (resultCode == RESULT_CANCELED) 
        {
            //Write your code if there's no result
        }
    }
	
	/**
	 * The following code is just filler code, don't necessarily worry about it for now
	 * ------------------------------------------------------------------------------
	 * ------------------------------------------------------------------------------
	 * ------------------------------------------------------------------------------
	 * ------------------------------------------------------------------------------
	 * ------------------------------------------------------------------------------
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public static void removePatient(int index)
	{
		personList.remove(index);
		adapter.notifyDataSetChanged();
	}
}
