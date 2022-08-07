package com.example.siaka_akpar_makassar;

import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import com.example.siaka_akpar_makassar.Act_detail_kelas.AmbilDataz;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
public class Tes_det_kls  extends Activity {
	ListView lv;
	String nimz,smstrz,namaz;
	
	String url;
	JSONArray khs_aksz = null;
	JSONParser jParserz=null;
	JSONObject jsonz=null;
	ProgressDialog pDialogz;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.tamp_infdet);
        Bundle b = getIntent().getExtras();
		smstrz = b.getString("kd_smtz");
		nimz = b.getString("nimz");
		//http://siaka.akparmakassar.com/jsonx/dks.php?stbz=11.31015&semesz=20141
		url = b.getString("url");
		
		//Toast.makeText(Tes_det_kls.this, "semester: "+smstrz+" nim: "+nimz+" url: "+url, Toast.LENGTH_LONG).show();
        new AmbilDataz().execute();
	}
       public class AmbilDataz extends AsyncTask<String, String, String> {
    	   
    		  ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

    		  @Override
    		  protected void onPreExecute() {
    		   super.onPreExecute();
    		   pDialogz = new ProgressDialog(Tes_det_kls.this);
    		   pDialogz.setMessage("Loading Data ...");
    		   pDialogz.setIndeterminate(false);
    		   pDialogz.setCancelable(false);
    		   pDialogz.show();
    		  }

    		  @Override
    		  protected String doInBackground(String... arg0) {
    		        
    	/*					
    				//urlz = url+"?nim="+nimz+"&semesz="+smstrz;
    		   lv=(ListView)findViewById(R.id.list);
    		  JSONParser jParser = new JSONParser();
    		   //jsonz = jParser.ambilURL(url+"?nim="+nimz+"&smest="+smstrz);
    		  //jsonz = jParser.ambilURL(URLProcessing.(url+"?nim="+nimz+"&smest="+smstrz));
    		*/   
    			  lv=(ListView)findViewById(R.id.list);
    			  JSONParser jParser = new JSONParser();
       		   jsonz = jParser.ambilURL(url+"?nim="+nimz+"&smest="+smstrz);
    		   try {
    			   khs_aksz = jsonz.getJSONArray("dkelas");  
    			   String isi = "";
    			   String no="";
    		    for (int i = 0; i < khs_aksz.length(); i++) {
    		    	no = Integer.toString(i+1);
    		     JSONObject c = khs_aksz.getJSONObject(i);
    		     
    		     HashMap<String, String> map = new HashMap<String, String>();
    		     //= "Kode Mata Kuliah "+c.getString("kdmtk").toString();
    		     //isi += "MK : "+c.getString("mk")+"\n"+"Jadwal : "+c.getString("jadwal")+"\n"+"Jumlah SKS : "+c.getString("sks")+"\n"+"Semester : "+c.getString("smt")+"\n"+"Dosen : "+c.getString("dosen")+"\n\n\n";
    		     isi = no+"\n"+"Kode : "+c.getString("kdmtk").toString().trim()+"\n"+"Mata Kuliah : "+c.getString("nmtk").toString().trim()+
    		    		 "\n"+"Kelas: "+c.getString("kdkls").toString().trim()+"\n"+"Hari: "+c.getString("hari").toString().trim()+
    		     		"\n"+"Jam: "+c.getString("jam").toString().trim()+"\n"+"Ruangan: "+c.getString("rng").toString().trim()+
    		     		"\n"+"Nama Dosen: "+c.getString("nmdos").toString().trim();
    		    isi = isi.replace("null", "-");		 
    		     map.put("kode", isi);
    		     contactList.add(map);
    		    }

    		   } catch (JSONException e) {    		     
    		   }
    		   return null;
    		  }

    		  @Override
    		  protected void onPostExecute(String result) {
    		   // TODO Auto-generated method stub
    		   super.onPostExecute(result);
    		   	pDialogz.dismiss();
    		  //  Toast.makeText(Tes_det_kls.this, "Json: "+jsonz.toString()+" url: "+url+"?nim="+nimz+"&smest="+smstrz, Toast.LENGTH_LONG).show();
    		   	try{
    		 //  	isi = (TextView)findViewById(R.id.isi_kelas); 
    		   	ListAdapter adapter2 = new SimpleAdapter(getApplicationContext(),
    		   	     contactList, R.layout.tamp_kelas,
    		   	     new String[] { "kode"}, 
    		   	     new int[] { R.id.isi_kelas });

    		   	   lv.setAdapter(adapter2);
	    		    //Toast.makeText(Tes_det_kls.this, "Json: "+jsonz.toString()+" url: "+url, Toast.LENGTH_LONG).show();
    		   	}
    		   	catch(Exception e){
    		   	//	Toast.makeText(Tes_det_kls.this, "error: "+e.getMessage(), Toast.LENGTH_LONG).show();
    		   	}
    		 
    		 
    		  }
    		 }  
    	}