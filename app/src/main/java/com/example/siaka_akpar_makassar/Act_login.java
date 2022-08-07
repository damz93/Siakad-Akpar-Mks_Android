package com.example.siaka_akpar_makassar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Act_login extends Activity implements View.OnClickListener{
	private EditText txtUser;
	private EditText txtPassword;
	JSONArray str_login = null;
	private TextView txtStatus;
	private Button btnLgn,btnClr;
	String var_usr, var_pass;
	private String surl = "http://siaka.akparmakassar.com/jsonx/login.php";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tamp_login);
		btnLgn = (Button)findViewById(R.id.btn_login);
		btnClr = (Button)findViewById(R.id.btn_clear);
		txtUser = (EditText)findViewById(R.id.edt_nim);
		txtPassword = (EditText)findViewById(R.id.edt_pass);
		txtStatus = (TextView)findViewById(R.id.txt_alert);
		btnLgn.setOnClickListener(this);
		btnClr.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		var_usr = txtUser.getText().toString();
		var_pass = txtPassword.getText().toString();
		
			if (v==btnLgn){
				if ((var_pass.length()>0)&&(var_pass.length()>0)){
					readWebpage(v);
				}
				else{
					Toast.makeText(Act_login.this, "Lengkapi field NIM maupun Password yang disediakan", Toast.LENGTH_LONG).show();
				}
					
			}
			else if (v==btnClr){
				if((var_usr.length()>0)||(var_pass.length()>0)){
					kosong();
				}
				else{
					finish();
					Intent a= new Intent(Act_login.this,Act_home2.class);
					startActivity(a);
				}
			}		
	}
	public String getRequest(String Url){
	       String sret;
	        HttpClient client = new DefaultHttpClient();
	        HttpGet request = new HttpGet(Url);
	        try{
	            HttpResponse response = client.execute(request);
	            sret= request(response);
	        }catch(Exception ex){
	           sret= "Failed Connect to server!";
	        }
	        return sret;

	}
	public static String request(HttpResponse response){
        String result = "";
        try{
            InputStream in = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                str.append(line + "\n");
            }
            in.close();
            result = str.toString();
        }catch(Exception ex){
            result = "Error";
        }
        return result;
    }
	private class CallWebPageTask extends AsyncTask<String, Void, String> {

		private ProgressDialog dialog;
		protected Context applicationContext;

		@Override
		protected void onPreExecute() {
			this.dialog = ProgressDialog.show(applicationContext, "Login Process", "Please Wait...", true);
		}

	    @Override
	    protected String doInBackground(String... urls) {
	      String response = "";
	      response = getRequest(urls[0]);
	      return response;
			
	    }

	    @Override
	    protected void onPostExecute(String result) {
	    	this.dialog.cancel();
	    	txtStatus.setText(result);
	    	String anu = txtStatus.getText().toString();	    	
	    	if (anu.substring(27,29).trim().equals("ok")){
	    		Act_set_get stg = new Act_set_get();
	    		stg.setbulan(txtUser.getText().toString());
	    		
	    		Intent a = null;
	    		a = new Intent(Act_login.this, Act_home.class);
	    		finish();
	    		startActivity(a);
	    		Toast.makeText(Act_login.this, "Selamat datang, "+txtUser.getText().toString(), Toast.LENGTH_LONG).show();
	    	}
	    	else{
	    		AlertDialog alertDialog = new AlertDialog.Builder(Act_login.this).create();
				alertDialog.setTitle("Peringatan");
				alertDialog.setMessage("Login gagal, pastikan Nim & Password sesuai...");
				alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int which) {
				        	dialog.dismiss();
				        }
				    });
				alertDialog.show();
				kosong();
	    	}
	    		
	    }
	  }
	private void kosong(){
		txtUser.setText("");
		txtPassword.setText("");
		txtUser.requestFocus();
	}
	public void readWebpage(View view) {
	    CallWebPageTask task = new CallWebPageTask();
	    task.applicationContext = Act_login.this;

	    String url =surl+"?usr="+txtUser.getText().toString()+"&psw="+txtPassword.getText().toString();
	    task.execute(new String[] { url });
;
	  }
}
