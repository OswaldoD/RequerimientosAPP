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



/*************************/
import java.util.ArrayList;

import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class Inicio extends ActionBarActivity implements OnClickListener {
	private ActionBar actionBar ;
	private ListView listado;
	private TextView textView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);
		
		actionBar = getSupportActionBar();
		actionBar.setDefaultDisplayHomeAsUpEnabled(true);
		
//		DISPLAY_SHOW_HOME;
		actionBar.show();
	
		textView = (TextView) findViewById(R.id.textView2);
	//	OptDatos();
	    
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
				//startActivity(new Intent(this, Buscar_Destino.class));
				startActivity(new Intent(this, Destinos_Encontrados.class));
				//startActivity(new Intent(this, Mostrar_Destino.class));

				return true;
			case R.id.action_login:
				// abrir el menu de login
				startActivity(new Intent(this, Login.class));
				return true;
			case R.id.action_reserv:
				// menu de reservaci�n
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
				//startActivity(new Intent(this, Inicio.class)); en las demas pesta�as si se llama esta actividad
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
	
	public void OptDatos(){
		AsyncHttpClient client = new AsyncHttpClient();
		String url = "http://192.168.0.102/turuta/main.php";
		
		RequestParams parametros = new RequestParams();
		parametros.put("Email", "dreammicro7@gmail.com");
		
		client.post(url, parametros, new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				// TODO Auto-generated method stub
				if (statusCode == 200){
					cargaLista(obtDatosJSON(new String (responseBody)));
				}
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody,
					Throwable error) {
				// TODO Auto-generated method stub
				error.printStackTrace();
				
			}
			
		});
		
		
		
	}
	public void cargaLista(ArrayList<String> datos){
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
		//listado.setAdapter(adapter);
		textView.setText(datos.get(0));
		
		
	}
	/*
	public ArrayList<String> obtDatosJSON (String response){
		ArrayList<String> listado = new ArrayList<String>();
		
		try{
			JSONArray jsonArray = new JSONArray(response);
			String texto;
			for(int i=0; i<jsonArray.length(); i++){
				
				texto = jsonArray.getJSONArray(i).getString(i) + " ";
						//jsonArray.getJSONArray(index)
						//jsonArray.getJSONArray(i).getString("correo") + " " +
						//jsonArray.getJSONArray(i).getString("id")+ " ";
				listado.add(texto);
				
				texto = jsonArray.getJSONObject(i).getString("Nombre") + " " +
						jsonArray.getJSONObject(i).getString("correo") + " " +
						jsonArray.getJSONObject(i).getString("id")+ " ";
				listado.add(texto);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return listado;
	}*/
	
	public ArrayList<String> obtDatosJSON (String response){
		ArrayList<String> listado = new ArrayList<String>();
		
		try{
			JSONObject jsonArray = new JSONObject(response);
			String texto;
			for(int i=0; i<jsonArray.length(); i++){
				
				
				
				texto = jsonArray.getString("Nombre") + " " +
						jsonArray.getString("correo") + " " +
						jsonArray.getString("id")+ " ";
				listado.add(texto);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return listado;
	}
	
}
