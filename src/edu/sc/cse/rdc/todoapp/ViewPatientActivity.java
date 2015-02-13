package edu.sc.cse.rdc.todoapp;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ViewPatientActivity extends ActionBarActivity {

	TextView nameField, ageField, heightField;
	Button deletePatientButton;
	int index;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_patient);
		Bundle extras = getIntent().getExtras();
		String name = extras.getString("name");
		int age = extras.getInt("age");
		double height = extras.getDouble("height");
		index = extras.getInt("index");
		
		nameField = (TextView) findViewById(R.id.nameField);
		ageField = (TextView) findViewById(R.id.ageField);
		heightField = (TextView) findViewById(R.id.heightField);
		
		nameField.setText(name);
		ageField.setText(age + "");
		heightField.setText(height + "");
		
		deletePatientButton = (Button) findViewById(R.id.patientDeleteButton);
		deletePatientButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createAlert();

			}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_patient, menu);
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
	
	public void createAlert()
	{
		// What we are trying to do is create a confirmation box
		// Basically says "Are you sure you want to do this?"
		
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Delete Patient");
		alert.setMessage("Are you sure you want to delete this patient?");
		alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				MainActivity.removePatient(index);
				finish();
			}
		});
		
		alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		alert.show();
	}
}
