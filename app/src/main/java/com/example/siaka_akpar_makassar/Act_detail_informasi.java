package com.example.siaka_akpar_makassar;
import org.json.JSONArray;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Act_detail_informasi  extends Activity {
	public String kode;
	JSONArray artikel = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tamp_infox);
        TextView judul = (TextView)findViewById(R.id.judull);
		TextView isi = (TextView)findViewById(R.id.isii);
		TextView penulis = (TextView)findViewById(R.id.penuliss);
        Intent in = getIntent();
        String jdl = in.getStringExtra("judul");
        String pnl = in.getStringExtra("penulis");
        String is = in.getStringExtra("isi");
        judul.setText(jdl);
        isi.setText(is);
        penulis.setText(pnl);
	}
}
