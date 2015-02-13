package edu.sc.cse.rdc.todoapp;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddPatientActivity extends ActionBarActivity {

	Button submitButton;
	EditText ageField;
	EditText nameField;
	EditText heightField;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_patient);
		
		ageField = (EditText) findViewById(R.id.fieldAge);
		nameField = (EditText) findViewById(R.id.fieldName);
		heightField = (EditText) findViewById(R.id.fieldHeight);

		
		submitButton = (Button) findViewById(R.id.addPatientButton);
		submitButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				String age = ageField.getText().toString();
				String name = nameField.getText().toString();
				String height = heightField.getText().toString();
				if(validForm())
				{
					completeForm(name,age,height);
				}
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_patient, menu);
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
	
	public void completeForm(String name, String age, String height)
	{
		Intent returnIntent = new Intent();
		returnIntent.putExtra("name", name);
		returnIntent.putExtra("age", age);
		returnIntent.putExtra("height", height);
		setResult(RESULT_OK,returnIntent);
		finish();
	}
	
	public boolean validForm()
	{
		String name = nameField.getText().toString();
		String age = ageField.getText().toString();
		String height = heightField.getText().toString();
		
		if(name.length() == 0 || age.length() == 0 || height.length() == 0)
		{
			return false;
		}
		return true;
	}
}
