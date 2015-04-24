/**
 * Instituto Tecnológico de Costa Rica
 * Requerimientos de Software
 * Tarea 4 - Inicio de Sesión con Google
 * Integrantes: Oswaldo Dávila
 * 			    Luis Quiros
 * 
 * Para más sobre este tipo de conexión, se pueden consultar los
 * ejemplos de PlusSampleActivity que provee la API de Google
 * y la API de Google Play
 *
 * */
package com.requerimientos.login;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;



public class Login extends Activity implements OnClickListener,
		GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{
	
	/*
	 * Necesarios para manejar los eventos de actualizar la Activity
	 * */
	private static final String TAG  = "SignInActivity";
    private static final int DIALOG_GET_GOOGLE_PLAY_SERVICES = 1;
    private static final int REQUEST_CODE_SIGN_IN = 1;
    private static final int REQUEST_CODE_GET_GOOGLE_PLAY_SERVICES = 2;

    private TextView welcome; //Para mostrar el estado de la conexión
    private GoogleApiClient mGoogleApiClient; // Conexión a Google API
    private SignInButton mSignInButton; // Boton Sign IN
    private View mSignOutButton; // Boton Sign OUT
   
    //Almacena la conexión
    private ConnectionResult mConnectionResult;
    
    //Manejo del boton para iniciar sesión
    private boolean mSignInClicked;
    
     // Requerido para manejar el error de conexión.
    private boolean mIntentInProgress;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
		
		createLabels();
		
		createButtons();
       
		createConnection();
		
   
	}
	
	private void createLabels(){
		/**
		 * Label para mostar el estado de la conexión
		 * */
		 welcome = (TextView) findViewById(R.id.welcome);
		
	}
	private void createButtons(){
		/**
		 * Botones de inicio de sesión y cerrar sesión
		 * */
        mSignInButton = (SignInButton) findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(this);
        
        mSignOutButton = findViewById(R.id.sign_out_button);
        mSignOutButton.setOnClickListener(this);
		
	}
	
	private void createConnection(){
		/**
		 * Conexión a Google API
		 * 
		 * */
		mGoogleApiClient = new GoogleApiClient.Builder(this)
		 				   .addApi(Plus.API, Plus.PlusOptions.builder()
		 				   .addActivityTypes(MomentUtil.ACTIONS).build())
		 				   .addScope(Plus.SCOPE_PLUS_LOGIN)
		 				   .addConnectionCallbacks(this)
		 				   .addOnConnectionFailedListener(this)
		 				   .build();
		
		/* Para inicar sesión al perfil o simple login
		 * */
		ToggleButton scopeSelector = (ToggleButton) findViewById(R.id.scope_selection_toggle);
		scopeSelector.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
			if (checked) {
				mGoogleApiClient.disconnect();
				mGoogleApiClient = new GoogleApiClient.Builder(Login.this)
		           	               .addApi(Plus.API)
		           	               .addScope(Plus.SCOPE_PLUS_PROFILE)
		           	               .addConnectionCallbacks(Login.this)
		           	               .addOnConnectionFailedListener(Login.this)
		           	               .build();
				mGoogleApiClient.connect();
			}else{
				mGoogleApiClient.disconnect();
				mGoogleApiClient = new GoogleApiClient.Builder(Login.this)
		           	               .addApi(Plus.API, Plus.PlusOptions.builder()
		           	               .addActivityTypes(MomentUtil.ACTIONS).build())
		           	               .addScope(Plus.SCOPE_PLUS_LOGIN)
		           	               .addConnectionCallbacks(Login.this)
		           	               .addOnConnectionFailedListener(Login.this)
		           	               .build();
				mGoogleApiClient.connect();
				}
			}
		});        
	}
	
	@Override
	public void onStart(){
		/**
		 * Iniciar Conexión
		 * */
		super.onStart();
		mGoogleApiClient.connect();
	}
	
	@Override
	public void onStop(){
		/**
		 * Detener Conexión
		 * */
		mGoogleApiClient.disconnect();
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// Conexión Fallida
        if (!mIntentInProgress) {
            mConnectionResult = result;
            if (mSignInClicked) {
                resolveSignInError();
            }
            updateButtons(false ); //Ya inició sesión?
        }
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		// Metodo para conectar a Google API
        Person person = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
        String currentPersonName = person != null
                ? person.getDisplayName()
                : getString(R.string.unknown_person);
        welcome.setText(getString(R.string.signed_in_status, currentPersonName));
        updateButtons(true); //Ya inició sesión
        mSignInClicked = false;
		
		
	}

	@Override
	public void onConnectionSuspended(int cause) {
		// Conexión Suspendida
        welcome.setText(R.string.loading_status);
        mGoogleApiClient.connect();
        updateButtons(false /* isSignedIn */);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		/**
		 * Manejo de clicks del boton para inicar sesión y cerrar sesión
		 * */
		switch (v.getId()){
			case R.id.sign_in_button:
				// Se realizan las ejecuciones necesarias para realizar la conexión a Google API
				if(!mGoogleApiClient.isConnecting()){
					int available = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
					if (available != ConnectionResult.SUCCESS){
						showDialog(DIALOG_GET_GOOGLE_PLAY_SERVICES);
						return;
					}
					mSignInClicked = true;
					welcome.setText(getString(R.string.signing_in_status));
					resolveSignInError(); //define
				}
				break;
				
			case R.id.sign_out_button:
                if (mGoogleApiClient.isConnected()) {
                    Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                    mGoogleApiClient.reconnect();
                }
                break;
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		
		// No hay solicitudes activas
		if (requestCode == REQUEST_CODE_SIGN_IN
                || requestCode == REQUEST_CODE_GET_GOOGLE_PLAY_SERVICES) {
            mIntentInProgress = false; 

            // Se resolvió un error recuperable, se intenta volver a conectar
            if (resultCode == RESULT_OK) {
                if (!mGoogleApiClient.isConnected() && !mGoogleApiClient.isConnecting()) {
                    // Resolved a recoverable error, now try connect() again.
                    mGoogleApiClient.connect();
                }
                // No se esta en medio de resolver errores al iniciar sesión
            } else {
                mSignInClicked = false; 

                if (resultCode == RESULT_CANCELED) {
                    welcome.setText(getString(R.string.signed_out_status));
                } else {
                	welcome.setText(getString(R.string.sign_in_error_status));
                    Log.w(TAG, "Error during resolving recoverable error.");
                }
            }
        }
	}
	
	private void updateButtons(boolean isSignedIn){
		/**
		 * Método para manejar la visualización de los botones iniciar sesión y cerrar sesión
		 * */
		
		if (isSignedIn) {
			// Si inició sesión, se habilita el botton de cerrar sesión y se desactiva iniciar sesión
            mSignInButton.setVisibility(View.INVISIBLE);
            mSignOutButton.setEnabled(true);
           
        } else {
            if (mConnectionResult == null) {
                // Se desactiva el boton iniciar sesión hasta esperar algun error
                mSignInButton.setVisibility(View.INVISIBLE);
                welcome.setText(getString(R.string.loading_status));
            } else {
                // Habilitar el boton de iniciar sesión.
                mSignInButton.setVisibility(View.VISIBLE);
                welcome.setText(getString(R.string.signed_out_status));
            }
            mSignOutButton.setEnabled(false);
        }
	}
	
	private void resolveSignInError(){
		/**
		 * Resolver error de iniciar sesión
		 * */
		if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, REQUEST_CODE_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
            	// La solicitud se canceló antes de ser enviada
            	// Se regresa al estado original y se intenta volver a conectar
                mIntentInProgress = false;
                mGoogleApiClient.connect();
                Log.w(TAG, "Error sending the resolution Intent, connect() again.");
            }
        }
		
		
	}
	
}
