package com.requerimientos.login;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class Inicio extends ActionBarActivity implements OnClickListener {
	private ActionBar actionBar ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);
		actionBar = getSupportActionBar();
		actionBar.setDefaultDisplayHomeAsUpEnabled(true);
		
//		DISPLAY_SHOW_HOME;
		actionBar.show();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.inicio, menu);
		return super.onCreateOptionsMenu(menu);
		//getMenuInflater().inflate(R.menu.inicio, menu);
		//return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//int id = item.getItemId();
		switch (item.getItemId()){
			case R.id.action_search:
				// abrir el menu de busqueda
				return true;
			case R.id.action_login:
				// abrir el menu de login
				return true;
			case R.id.action_reserv:
				// menu de reservación
				return true;
			case R.id.action_profile:
				// menu de perfil
				return true;
			case R.id.action_home:
				// menu de inicio
				return true;
			case R.id.action_exit:
				finish();
				return true;
				
			default:
				return super.onOptionsItemSelected(item);
		
		
		}
		/*
		if (id == R.id.action_settings) {
			actionBar.hide();
			return true;
		}

		return super.onOptionsItemSelected(item);
		*/
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
