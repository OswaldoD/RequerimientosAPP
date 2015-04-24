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

public class Ver_Reservaciones extends ActionBarActivity implements OnClickListener{
	private ActionBar actionBar ;
	private TextView txtActiva1; 
	private TextView txtActiva2;
	private TextView txtActiva3;
	private TextView txtCancela1;
	private TextView txtCancela2;
	private TextView txtCancela3;
	private String webservice = "http://192.168.0.102/turuta/main.php";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver__reservaciones);
		
		actionBar = getSupportActionBar();
		actionBar.setDefaultDisplayHomeAsUpEnabled(true);
		actionBar.show();
		
		txtActiva1 = (TextView) findViewById(R.id.txtActiva1);
		txtActiva2 = (TextView) findViewById(R.id.txtActiva2);
		txtActiva3 = (TextView) findViewById(R.id.txtActiva3);
		
		txtCancela1 = (TextView) findViewById(R.id.txtCancelada1);
		txtCancela2 = (TextView) findViewById(R.id.txtCancelada2);
		txtCancela3 = (TextView) findViewById(R.id.txtCancelada3);
		
		Button btnActiva1 = (Button) findViewById(R.id.btnActiva1);
		btnActiva1.setOnClickListener(this);
		
		Button btnActiva2 = (Button) findViewById(R.id.btnActiva2);
		btnActiva2.setOnClickListener(this);
		
		Button btnActiva3 = (Button) findViewById(R.id.btnActiva3);
		btnActiva3.setOnClickListener(this);
		
		Button btnCancela1 = (Button) findViewById(R.id.btnCancela1);
		btnCancela1.setOnClickListener(this);
		
		Button btnCancela2 = (Button) findViewById(R.id.btnCancela2);
		btnCancela2.setOnClickListener(this);
		
		Button btnCancela3 = (Button) findViewById(R.id.btnCancela3);
		btnCancela3.setOnClickListener(this);
		
		
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
			startActivity(new Intent(this, Ver_Reservaciones.class));
			
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
			case R.id.btnActiva1:
				System.out.println("btn1");
				startActivity(new Intent(this, Ver_Reservacion.class));
				break;
			case R.id.btnActiva2:
				System.out.println("btn1");
				break;
			case R.id.btnActiva3:
				System.out.println("btn1");
				break;
			case R.id.btnCancela1:
				System.out.println("btn1");
				break;
			case R.id.btnCancela2:
				System.out.println("btn1");
				break;
			case R.id.btnCancela3:
				System.out.println("btn1");
				break;
		}
		
	}
}
