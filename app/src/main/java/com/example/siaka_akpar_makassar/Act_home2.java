package com.example.siaka_akpar_makassar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class Act_home2 extends Activity implements View.OnClickListener{
	Button btn_login,btn_inf, btn_mhs, btn_dsn, btn_mtk, btn_khs, btn_trans, btn_krs, btn_kl;
	Intent ab=null;
	private final int SPLASH_DISPLAY_LENGTH2 = 4000;
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tamp_home2);
		btn_login = (Button)findViewById(R.id.btn_lgin);
		btn_login.setOnClickListener(this);
		btn_inf = (Button)findViewById(R.id.btn_info2);
		btn_inf.setOnClickListener(this);
		btn_dsn = (Button)findViewById(R.id.btn_dosn2);
		btn_dsn.setOnClickListener(this);
		btn_mtk = (Button)findViewById(R.id.btn_mtkul2);
		btn_mtk.setOnClickListener(this);
		btn_khs = (Button)findViewById(R.id.btn_khs2);
		btn_khs.setOnClickListener(this);
		btn_trans = (Button)findViewById(R.id.btn_tskp2);
		btn_trans.setOnClickListener(this);
		btn_krs = (Button)findViewById(R.id.btn_krs2);
		btn_krs.setOnClickListener(this);
		btn_mhs = (Button)findViewById(R.id.btn_mhs2);
		btn_mhs.setOnClickListener(this);
		btn_kl = (Button)findViewById(R.id.btn_klr2);
		btn_kl.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		if (v == btn_login){	
			finish();
			ab = new Intent(this,Act_login.class);
			startActivity(ab);
		}
		else if (v == btn_mtk){			
			ab = new Intent(this,Act_matkul.class);
			startActivity(ab);
		}
		else if (v == btn_inf){
			ab = new Intent(this,Act_informasi.class);
			startActivity(ab);
		}
		else if (v == btn_dsn){
			ab = new Intent(this,Act_dosen.class);
			startActivity(ab);
		}
		//4 di bawah ini hanyalah tess2
		else if (v == btn_kl){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Konfirmasi");
			builder.setMessage("Yakin ingin keluar dari aplikasi?")
			.setCancelable(false)
			.setNegativeButton("Ya",new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int id) {
					finish();
				}
			})
			.setPositiveButton("Tidak",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					dialog.cancel();
				}	
			 })
			.show();
		}
		else{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Peringatan");
			builder.setMessage("Maaf... Anda harus login sebagai mahasiswa untuk menggunakan fitur ini..")
			.setCancelable(false)
			.setPositiveButton("Ke Form Login",new DialogInterface.OnClickListener() {
  				public void onClick(DialogInterface dialog,int id) {
  					finish();
  					Intent ab = new Intent(Act_home2.this,Act_login.class);
		        	startActivity(ab);
		            dialog.dismiss();
  				}
  				})
  					.setNegativeButton("Tidak",new DialogInterface.OnClickListener(){
  				public void onClick(DialogInterface dialog, int id) {
  					dialog.cancel();}}).show();
		}
		
		
	}
}