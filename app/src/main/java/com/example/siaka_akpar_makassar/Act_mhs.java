package com.example.siaka_akpar_makassar;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Act_mhs extends Activity {
	 ProgressDialog pDialog;
	 JSONObject json = null;
	 ListView lv;
	 TextView cont,combl;
	 String isim="",comblang="";
	 JSONArray contacts = null;
	 public int isi;
	 public String isiiii,kode;
      
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tamp_inf);
        lv=(ListView)findViewById(R.id.list);
        new AmbilData().execute();
    }
	    
	 public class AmbilData extends AsyncTask<String, String, String> {

	   
	  ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
	  
	  @Override
	  protected void onPreExecute() {
	   super.onPreExecute();
	   pDialog = new ProgressDialog(Act_mhs.this);
	   pDialog.setMessage("Loading Data ...");
	   pDialog.setIndeterminate(false);
	   pDialog.setCancelable(false);
	   pDialog.show();
	  }

	  @Override
	  protected String doInBackground(String... arg0) {
	   
	  String url = "http://siaka.akparmakassar.com/jsonx/mahasiswa.php";
      cont = (TextView)findViewById(R.id.tx_isimhs);
	  combl = (TextView)findViewById(R.id.tx_namax);

	  JSONParser jParser = new JSONParser();

	  Act_set_get a = new Act_set_get();
	  String aa = a.getnim();
	   json = jParser.ambilURL(url+"?nim="+aa);
	   
	   try {
	    contacts = json.getJSONArray("mahasiswa");

	    for (int i = 0; i < contacts.length(); i++) {
	     JSONObject c = contacts.getJSONObject(i);
	     HashMap<String, String> map = new HashMap<String, String>();
	     /*String nama = "1. Nama               : "+c.getString("nmmhs").toString();
	     String stb =  "2. Nim                  : "+c.getString("stb").toString();
	     String prodi= "3. Program Studi: "+c.getString("nmprgstd").toString();
	     String agama ="4. Agama              : "+c.getString("agama").toString();
	     String t4 = c.getString("tplhr").trim();	     
	     String tgl = c.getString("tglhr").trim();	     
	     String tggl = "5. Tempat/Tanggal Lahir: "+t4+", "+tgl;
	     String jenis = "6. Jenis Kelamin    : "+c.getString("jkel").toString();
	     String status = "7. Status Perkawinan : "+c.getString("sts_kawin").toString();
	     String janak = "8. Jumlah Anak        : "+c.getString("jumlah_anak").toString()+"\n"+
	     "9. Alamat Rumah Tetap/Orang Tua: "+c.getString("alm_rt").toString()+"\n"+"/Kode Pos: "+c.getString("kdpos_rt").toString()+
	     "\n"+"Kota: "+c.getString("kota_rt").toString()+"\n"+"/Kode Pos: "+c.getString("kdposkota_rt").toString();*/
	     isim = " :  "+c.getString("nmmhs").toString()+"\n"+" :  "+c.getString("stb").toString()+"\n"+
	    		 " :  "+c.getString("nmprgstd").toString()+"\n"+" :  "+c.getString("agama").toString()+"\n"+
	    		 " :  "+c.getString("tplhr").toString()+"/ "+c.getString("tglhr")+"\n"+
	     		 " :  "+c.getString("jkel").toString()+"\n"+" :  "+c.getString("sts_kawin").toString()+"\n"+
	     		" :  "+c.getString("jumlah_anak").toString()+"\n"+" :  "+c.getString("alm_rt").toString()+"/Kode Pos: "+c.getString("kdpos_rt").toString()+
	     		"\n"+"    Kota: "+c.getString("kota_rt").toString()+"/Kode Pos: "+c.getString("kdposkota_rt").toString()+
	     		"\n"+" :  "+c.getString("alm_ts1").toString()
	     		+"1./Kode Pos: "+c.getString("kdpos_ts1").toString()+
	     		"\n"+"    Kota: "+c.getString("kota_ts1").toString()+"/No telp: "+c.getString("telp_ts1").toString()+"\n"
	     		+"    2."+"\n"+"    Kota:"+c.getString("kota_ts2").toString()+"    / No. Telp.: "+c.getString("telp_ts2").toString()+"\n"
	     		+"    3."+"\n"+"    Kota:" +c.getString("kota_ts3").toString()+"    / No. Telp.: "+c.getString("telp_ts3").toString();
	     
	     comblang = "1. Nama Lengkap"+"\n"+"2. Nim"+"\n"+"3. Program Study"+"\n"+"4. Agama"+"\n"+
	    		 	"5. Tempat/Tanggal Lahir"+"\n"+"6. Jenis Kelamin"+"\n"+"7. Status Perkawinan"+"\n"+"8. Jumlah Anak"+"\n"+
	    		 	"9. Alamat Rumah Tetap/Orang Tua"+"\n\n"+"10.Alamat Tinggal Sementara";
	    		/*
	    		 * alm_ts1   kdpos_ts1 kota_ts1   telp_ts1  kota_ts2   telp_ts2  kota_ts3   telp_ts3
	    		 * 
	    		 * */ 		    
	    
	     
	     isim = isim.replace("null", "-");	     
	     
	     map.put("comcom", comblang);
	     map.put("semuaa", isim);	     
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
	   	pDialog.dismiss();
	    //Toast.makeText(Act_mhs.this, "isi dari mahasiswa: "+json.toString(), Toast.LENGTH_LONG).show();
	   	ListAdapter adapter2 = new SimpleAdapter(getApplicationContext(),
	     contactList, R.layout.tamp_mhs,
	  //   new String[] { "nmmhs", "stb","nmprgstd","agama","tplhr" ,"jkel","sts_kawin","jumlah_anak"}, 
	   //  new int[] { R.id.tx_namax,R.id.tx_nimx,R.id.tx_prodx,R.id.tx_agamax,R.id.tx_t4glx,R.id.tx_jnsx,R.id.tx_sttusx,R.id.tx_jumlx });
	     	new String[] {"semuaa","comcom"},
	   		new int[]{R.id.tx_isimhs,R.id.tx_namax});
	   lv.setAdapter(adapter2);
	   	//cont.setText(isim);
	   //	combl.setText(comblang);
	   	
	  }
	 }    
}
