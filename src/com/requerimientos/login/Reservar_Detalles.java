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

public class Reservar_Detalles extends ActionBarActivity implements OnClickListener {
	
	private ActionBar actionBar ;
	//private ListView listado;
	private TextView txtNombreMostrar; 
	private TextView txtLugarHMostrar;
	private TextView txtHoraCMostrar;
	private TextView txtDetallesMostrar;
	private String webservice = "http://192.168.0.102/turuta/main.php";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reservar__detalles);
		
		actionBar = getSupportActionBar();
		actionBar.setDefaultDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
		txtNombreMostrar = (TextView) findViewById(R.id.txtNombreMostrar);
		txtLugarHMostrar = (TextView) findViewById(R.id.txtLugarHMostrar);
		txtHoraCMostrar = (TextView) findViewById(R.id.txtHoraCMostrar);
		txtDetallesMostrar = (TextView) findViewById(R.id.txtDetallesMostrar);
		
		Button reservar = (Button) findViewById(R.id.btnReservar);
		reservar.setOnClickListener(this);
		
		Button regresar = (Button) findViewById(R.id.btnRegresar);
		regresar.setOnClickListener(this);
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
			startActivity(new Intent(this, Inicio.class)); 
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
			case R.id.btnReservar:
				System.out.println("reservar");
				break;
			case R.id.btnRegresar:
				System.out.println("regresar");
				break;
		}
		
	}
}
