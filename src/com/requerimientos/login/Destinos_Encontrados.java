package com.requerimientos.login;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Destinos_Encontrados extends ActionBarActivity implements OnClickListener{
	private ActionBar actionBar;
	
	private TextView txtDest1; 
	private TextView txtDest2;
	private TextView txtDest3;
	private TextView txtDest4;
	private TextView txtDest5;
	
	private String webservice = "http://192.168.0.102/turuta/main.php";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_destinos__encontrados);
		
		actionBar = getSupportActionBar();
		actionBar.setDefaultDisplayHomeAsUpEnabled(true);
		actionBar.show();
	
		txtDest1 = (TextView) findViewById(R.id.btnCoinc1);
		txtDest2 = (TextView) findViewById(R.id.txtCoinc2);
		txtDest3 = (TextView) findViewById(R.id.txtCoinc3);
		txtDest4 = (TextView) findViewById(R.id.txtCoinc4);
		txtDest5 = (TextView) findViewById(R.id.txtCoinc5);
		
		/* Declaración de botones */
		Button btnCoinc1 = (Button) findViewById(R.id.btnCoinc1);
		btnCoinc1.setOnClickListener(this);
		
		Button btnCoinc2 = (Button) findViewById(R.id.btnCoinc2);
		btnCoinc2.setOnClickListener(this);
		
		Button btnCoinc3 = (Button) findViewById(R.id.btnCoinc3);
		btnCoinc3.setOnClickListener(this);
		
		Button btnCoinc4 = (Button) findViewById(R.id.btnCoinc4);
		btnCoinc4.setOnClickListener(this);
		
		Button btnCoinc5 = (Button) findViewById(R.id.btnCoinc5);
		btnCoinc5.setOnClickListener(this);
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.inicio, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()){
		case R.id.action_search:
			// abrir el menu de busqueda
			startActivity(new Intent(this, Buscar_Destino.class));
			//startActivity(new Intent(this, Destinos_Encontrados.class));
			//startActivity(new Intent(this, Mostrar_Destino.class));

			return true;
		case R.id.action_login:
			// abrir el menu de login
			startActivity(new Intent(this, Login.class));
			return true;
		case R.id.action_reserv:
			// menu de reservación
			//startActivity(new Intent(this, Reservar.class)); // en realidad llama a ver reservaciones
			//startActivity(new Intent(this, Reservar_Detalles.class));
			//startActivity(new Intent(this, Ver_Reservacion.class));
			//startActivity(new Intent(this, Ver_Reservaciones.class));
			
			return true;
		case R.id.action_profile:
			// menu de perfil
			return true;
		case R.id.action_home:
			// menu de inicio
			//startActivity(new Intent(this, Inicio.class)); en las demas pestañas si se llama esta actividad
			return true;
		case R.id.action_exit:
			finish();
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
	
	
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.btnCoinc1:
				System.out.println("btn1");
				startActivity(new Intent(this, Mostrar_Destino.class));

				break;
			case R.id.btnCoinc2:
				System.out.println("btn2");
				break;
			case R.id.btnCoinc3:
				System.out.println("btn3");
				break;
			case R.id.btnCoinc4:
				System.out.println("btn4");
				break;
			case R.id.btnCoinc5:
				System.out.println("btn5");
				break;
				
		
		}
		
	}
}
