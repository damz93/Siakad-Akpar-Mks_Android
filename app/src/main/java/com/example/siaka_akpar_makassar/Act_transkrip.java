package com.example.siaka_akpar_makassar;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.siaka_akpar_makassar.Act_detail_khs.AmbilDataz;
import com.example.siaka_akpar_makassar.Act_mhs.AmbilData;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class Act_transkrip extends Activity {
	ListView lv;
	TextView ipkk;
	String nimz,smstrz,namaz;
	
	String url;
	JSONArray khs_aksz = null;
	JSONParser jParserz=null;
	JSONObject jsonz=null;
	ProgressDialog pDialogz;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.tamp_det_transkrip);
	    new AmbilDataz().execute();
	}
       public class AmbilDataz extends AsyncTask<String, String, String> {
    	   	Float ipk;String isib;Integer jnk=0,jsks=0,sks_lulus=0,sks_tlus=0,isks=0,ibobot=0,nk=0;
    		  ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

    		  @Override
    		  protected void onPreExecute() {
    		   super.onPreExecute();
    		   pDialogz = new ProgressDialog(Act_transkrip.this);
    		   pDialogz.setMessage("Loading Data ...");
    		   pDialogz.setIndeterminate(false);
    		   pDialogz.setCancelable(false);
    		   pDialogz.show();
    		  }

    		  @Override
    		  protected String doInBackground(String... arg0) {
    			  url = "http://siaka.akparmakassar.com/jsonx/transkrip.php";
    			  JSONParser jParser = new JSONParser();
    			  Act_set_get a = new Act_set_get();
    			  String aa = a.getnim();
    			  jsonz = jParser.ambilURL(url+"?nim="+aa);
    			  ipkk = (TextView)findViewById(R.id.tx_ipkTrs); 
    			  lv=(ListView)findViewById(R.id.list);    			  
    		   try {
    			   khs_aksz = jsonz.getJSONArray("trans");  
    			   String isi = "";
    			   String no="";
    		    for (int i = 0; i < khs_aksz.length(); i++) {
    		    	no = Integer.toString(i+1);
    		    	JSONObject c = khs_aksz.getJSONObject(i);
    		     
    		    	HashMap<String, String> map = new HashMap<String, String>();
    		    	isi = no+"\n"+"Kode : "+c.getString("kdmtk").toString().trim()+"\n"+"Mata Kuliah : "+c.getString("nmmtk").toString().trim()+
    		    			"\n"+"SKS: "+c.getString("sks").toString().trim()+"\n"+"Nilai: "+c.getString("nilai").toString().trim()+
    		    			"\n"+"Bobot: "+c.getString("bobot").toString().trim()+"\n"+"Bobot*SKS: "+c.getString("bs").toString().trim();
    		    	
    		    	isi = isi.replace("null", "-");
    		    	String bobot = c.getString("bobot").toString();
    		    	
    		    	String sks = c.getString("sks").toString();
    		    	String nilai = c.getString("nilai").toString();
    		    	Integer ja=0,jb=0,jc=0,jd=0,je=0,jt=0;
    		    	if (nilai.equalsIgnoreCase("A")){
    		    		ja+=1;
    		    	}
    		    	else if (nilai.equalsIgnoreCase("B")){
    		    		jb+=1;
    		    	
    		    	}
    		    	if (nilai.equalsIgnoreCase("C")){
    		    		jc+=1;
    		    	
    		    	}
    		    	if (nilai.equalsIgnoreCase("D")){
    		    		jd+=1;
    		    	}
    		    	if (nilai.equalsIgnoreCase("E")){
    		    		je+=1;
    		    	}
    		    	if (nilai.equalsIgnoreCase("T")){
    		    		jt+=1;
    		    	}
    		    	
    		    	
    		    	
    		    	/*if (sks.equalsIgnoreCase("null")){
    		    		sks = "0";
    		    	}*/
    		    	if (bobot.equalsIgnoreCase("null")){
    		    		bobot = "0";
    		    	}
    		    
    		    	isks = Integer.parseInt(sks);
    		    	ibobot = Integer.parseInt(bobot);
    		    	nk = isks * ibobot;     		  
    		    	jsks += isks;
    		    	
    		    	jnk = nk;
    		    	jnk += jnk;
    		    	DecimalFormat df = new DecimalFormat(".##");
    		    	ipk = (float) (jnk/jsks);
    		    	Float ip = Float.valueOf(df.format(ipk));
    		        
    		    	if (nk<=0){
    		    		sks_tlus+=isks;
    		    	}
    		    	sks_lulus = jsks-sks_tlus;
    		    	/*isib = "Index Prestasi Komulatif: "+ipk.toString()+"\n"+"SKS Program: "+jsks.toString()+
    		    			"\n"+"SKS Lulus: "+sks_lulus.toString()+
    		    			"\n"+"A : "+ja+"\n"+"B : "+jb+"\n"+"C : "+jc+"\n"+"D : "+jd+"\n"+"E : "+je;*/
    		    	isib = "  Index Prestasi Komulatif: "+ip.toString()+"\n"+"  SKS Program: "+jsks.toString()+
    		    			"\n"+"  SKS Lulus: "+sks_lulus.toString()+
    		    			"\n"+"  A : "+ja+"\n"+"  B : "+jb+"\n"+"  C : "+jc+"\n"+"  D : "+jd+"\n"+"  E : "+je;
    		    	
    		    	//map.put("ipk", ipk.toString());
    		    	map.put("isi", isi);
    		     
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
    		   // Toast.makeText(Act_transkrip.this, "IPK: "+ipk.toString(), Toast.LENGTH_LONG).show();
    		   	try{
    		 
    		   	ListAdapter adapter2 = new SimpleAdapter(getApplicationContext(),
    		   	     contactList, R.layout.tamp_transkrip,
    		   	     new String[] { "isi"}, 
    		   	     new int[] { R.id.isi_trans });

    		   	   lv.setAdapter(adapter2);
    		   	  // Toast.makeText(Act_transkrip.this, "IPK: "+ipk.toString(), Toast.LENGTH_LONG).show();
	    		    //Toast.makeText(Tes_det_kls.this, "Json: "+jsonz.toString()+" url: "+url, Toast.LENGTH_LONG).show();
    		   	   ipkk.setText(isib);
    		   	}
    		   	catch(Exception e){
    		   	//	Toast.makeText(Tes_det_kls.this, "error: "+e.getMessage(), Toast.LENGTH_LONG).show();
    		   	}
    		 
    		 
    		  }
    		 }  
    	}