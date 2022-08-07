package com.example.siaka_akpar_makassar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Act_home extends Activity implements View.OnClickListener{
	Button btn_login,btn_inf, btn_mhs, btn_dsn, btn_mtk, btn_khs, btn_trans, btn_krs, btn_klr, btn_kls,logut;
	Intent ab=null;
	TextView txnim;
	private final int SPLASH_DISPLAY_LENGTH2 = 4000;
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tamp_home);
		btn_login = (Button)findViewById(R.id.btn_lgut);
		btn_login.setOnClickListener(this);
		btn_inf = (Button)findViewById(R.id.btn_info);
		btn_inf.setOnClickListener(this);
		btn_dsn = (Button)findViewById(R.id.btn_dosn);
		btn_dsn.setOnClickListener(this);
		btn_mtk = (Button)findViewById(R.id.btn_mtkul);
		btn_mtk.setOnClickListener(this);
		btn_khs = (Button)findViewById(R.id.btn_khs);
		btn_khs.setOnClickListener(this);
		btn_trans = (Button)findViewById(R.id.btn_tskp);
		btn_trans.setOnClickListener(this);
		btn_krs = (Button)findViewById(R.id.btn_krs);
		btn_krs.setOnClickListener(this);
		btn_mhs = (Button)findViewById(R.id.btn_mhs);
		btn_mhs.setOnClickListener(this);
		btn_klr = (Button)findViewById(R.id.btn_klr);
		btn_klr.setOnClickListener(this);
		btn_kls = (Button)findViewById(R.id.btn_krs);
		btn_kls.setOnClickListener(this);
		
	
		Act_set_get stg = new Act_set_get();
		txnim = (TextView)findViewById(R.id.txt_slmat);
		logut = (Button)findViewById(R.id.btn_lgut);
		logut.setOnClickListener(this);
		txnim.setText("Nim aktif: "+stg.getnim());
	}

	@Override
	public void onClick(View d) {
		// TODO Auto-generated method stub
		if (d == logut){
			finish();
			Intent a = new Intent(Act_home.this,Act_home2.class);
			startActivity(a);	
		}
		else if (d == btn_mtk){			
			ab = new Intent(this,Act_matkul.class);
			startActivity(ab);
		}
		else if (d == btn_inf){
			ab = new Intent(this,Act_informasi.class);
			startActivity(ab);
		}
		else if (d == btn_dsn){
			ab = new Intent(this,Act_dosen.class);
			startActivity(ab);
		}
		else if (d == btn_khs){
			ab = new Intent(this, Act_khs.class);
			startActivity(ab);
		}
		else if (d == btn_inf){
			ab = new Intent(this,Act_informasi.class);
			startActivity(ab);
		}
		else if (d == btn_trans){
			ab = new Intent(this,Act_transkrip.class);
			startActivity(ab);
		}
		else if (d == btn_kls){
			ab = new Intent(this,Act_kelas.class);
			startActivity(ab);
		}
		else if (d == btn_klr){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
		else if (d == btn_mhs){
			ab = new Intent(this, Act_mhs.class);
			startActivity(ab);
		}
		
		
	}
}
