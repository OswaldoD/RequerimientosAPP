/**
 * Instituto Tecnol�gico de Costa Rica
 * Requerimientos de Software
 * Tarea 4 - Inicio de Sesi�n con Google
 * Integrantes: Oswaldo D�vila
 * 			    Luis Quiros
 * 
 * Para m�s sobre este tipo de conexi�n, se pueden consultar los
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

    private TextView welcome; //Para mostrar el estado de la conexi�n
    private GoogleApiClient mGoogleApiClient; // Conexi�n a Google API
    private SignInButton mSignInButton; // Boton Sign IN
    private View mSignOutButton; // Boton Sign OUT
   
    //Almacena la conexi�n
    private ConnectionResult mConnectionResult;
    
    //Manejo del boton para iniciar sesi�n
    private boolean mSignInClicked;
    
     // Requerido para manejar el error de conexi�n.
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
		 * Label para mostar el estado de la conexi�n
		 * */
		 welcome = (TextView) findViewById(R.id.welcome);
		
	}
	private void createButtons(){
		/**
		 * Botones de inicio de sesi�n y cerrar sesi�n
		 * */
        mSignInButton = (SignInButton) findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(this);
        
        mSignOutButton = findViewById(R.id.sign_out_button);
        mSignOutButton.setOnClickListener(this);
		
	}
	
	private void createConnection(){
		/**
		 * Conexi�n a Google API
		 * 
		 * */
		mGoogleApiClient = new GoogleApiClient.Builder(this)
		 				   .addApi(Plus.API, Plus.PlusOptions.builder()
		 				   .addActivityTypes(MomentUtil.ACTIONS).build())
		 				   .addScope(Plus.SCOPE_PLUS_LOGIN)
		 				   .addConnectionCallbacks(this)
		 				   .addOnConnectionFailedListener(this)
		 				   .build();
		
		/* Para inicar sesi�n al perfil o simple login
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
		 * Iniciar Conexi�n
		 * */
		super.onStart();
		mGoogleApiClient.connect();
	}
	
	@Override
	public void onStop(){
		/**
		 * Detener Conexi�n
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
		// Conexi�n Fallida
        if (!mIntentInProgress) {
            mConnectionResult = result;
            if (mSignInClicked) {
                resolveSignInError();
            }
            updateButtons(false ); //Ya inici� sesi�n?
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
        updateButtons(true); //Ya inici� sesi�n
        mSignInClicked = false;
		
		
	}

	@Override
	public void onConnectionSuspended(int cause) {
		// Conexi�n Suspendida
        welcome.setText(R.string.loading_status);
        mGoogleApiClient.connect();
        updateButtons(false /* isSignedIn */);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		/**
		 * Manejo de clicks del boton para inicar sesi�n y cerrar sesi�n
		 * */
		switch (v.getId()){
			case R.id.sign_in_button:
				// Se realizan las ejecuciones necesarias para realizar la conexi�n a Google API
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

            // Se resolvi� un error recuperable, se intenta volver a conectar
            if (resultCode == RESULT_OK) {
                if (!mGoogleApiClient.isConnected() && !mGoogleApiClient.isConnecting()) {
                    // Resolved a recoverable error, now try connect() again.
                    mGoogleApiClient.connect();
                }
                // No se esta en medio de resolver errores al iniciar sesi�n
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
		 * M�todo para manejar la visualizaci�n de los botones iniciar sesi�n y cerrar sesi�n
		 * */
		
		if (isSignedIn) {
			// Si inici� sesi�n, se habilita el botton de cerrar sesi�n y se desactiva iniciar sesi�n
            mSignInButton.setVisibility(View.INVISIBLE);
            mSignOutButton.setEnabled(true);
           
        } else {
            if (mConnectionResult == null) {
                // Se desactiva el boton iniciar sesi�n hasta esperar algun error
                mSignInButton.setVisibility(View.INVISIBLE);
                welcome.setText(getString(R.string.loading_status));
            } else {
                // Habilitar el boton de iniciar sesi�n.
                mSignInButton.setVisibility(View.VISIBLE);
                welcome.setText(getString(R.string.signed_out_status));
            }
            mSignOutButton.setEnabled(false);
        }
	}
	
	private void resolveSignInError(){
		/**
		 * Resolver error de iniciar sesi�n
		 * */
		if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, REQUEST_CODE_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
            	// La solicitud se cancel� antes de ser enviada
            	// Se regresa al estado original y se intenta volver a conectar
                mIntentInProgress = false;
                mGoogleApiClient.connect();
                Log.w(TAG, "Error sending the resolution Intent, connect() again.");
            }
        }
		
		
	}
	
}
