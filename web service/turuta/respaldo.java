package com.requerimientos.login;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;










/*************************/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

@SuppressLint("NewApi")
public class Inicio extends ActionBarActivity implements OnClickListener {
	private ActionBar actionBar ;
	private ListView listado;

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    if (android.os.Build.VERSION.SDK_INT > 9) {
	        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	        StrictMode.setThreadPolicy(policy);
	      }
	    
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);
		

		
		
		actionBar = getSupportActionBar();
		actionBar.setDefaultDisplayHomeAsUpEnabled(true);
		
//		DISPLAY_SHOW_HOME;
		actionBar.show();
		
		// conexion a php
		HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://192.168.0.102/android/WebServicePrueba.php");
		TextView textView = (TextView) findViewById(R.id.textView2);
		
	    try
	        {
	        	HttpResponse response = httpClient.execute(httpPost);
	        	String jsonResult = inputStreamToString(response.getEntity().getContent()).toString();
	        	//String jsonResult ;
	        	//JSONObject object;
	        	JSONObject object = new JSONObject(jsonResult);
	        	
	        	String name = object.getString("nombre");
	        	String version = object.getString("blog");
	        	
	        	textView.setText(name + " - " + version);
	        }
	    /*
	        catch(JSONException e)
	        {
	        	textView.setText("Ocurrio un error 1.." + e.getMessage());
	        	e.printStackTrace();
	        }*/
	    catch(Exception e){
	    	System.out.println("esta aca??");
	    	e.printStackTrace();
	    	//Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
	    }
	    /*
	        catch(ClientProtocolException e)
	        {
	        	textView.setText("Ocurrio un error 2..");
	        	e.printStackTrace();
	        }
	        catch(IOException e)
	        {
	        	textView.setText("Ocurrio un error 3.." + e.getMessage());
	        	e.printStackTrace();
	        }*/
	
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
	
	private StringBuilder inputStreamToString(InputStream is)
    {
    	String rLine = "";
    	StringBuilder answer = new StringBuilder();
    	BufferedReader rd = new BufferedReader(new InputStreamReader(is));
    	
    	try
    	{
    		while ((rLine = rd.readLine()) != null)
    		{
    			answer.append(rLine);
    		}
    	}
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}
    	return answer;
    }
	public void OptDatos(){
		AsyncHttpClient client = new AsyncHttpClient();
		String url = "http://192.168.0.102/turuta/main.php";
		
		RequestParams parametros = new RequestParams();
		parametros.put("Edad", 18);
		
		client.post(url, parametros, new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				// TODO Auto-generated method stub
				if (statusCode == 200){
					// llamar a funcion...
					cargaLista(obtDatosJSON(new String (responseBody)));
				}
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody,
					Throwable error) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		
	}
	public void cargaLista(ArrayList<String> datos){
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
		listado.setAdapter(adapter);
		
		
	}
	
	public ArrayList<String> obtDatosJSON (String response){
		ArrayList<String> listado = new ArrayList<String>();
		
		try{
			JSONArray jsonArray = new JSONArray(response);
			String texto;
			for(int i=0; i<jsonArray.length(); i++){
				texto = jsonArray.getJSONObject(i).getString("Nombre") + " " +
						jsonArray.getJSONObject(i).getString("Apellido") + " " +
						jsonArray.getJSONObject(i).getString("Edad")+ " ";
				listado.add(texto);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return listado;
	}
	
	
}
