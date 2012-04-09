package br.livro.android.cap15.nativo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class VisualizarArquivo extends Activity {

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		File arquivo = null;

		//pega a intent que iniciou a activity
		Intent intent = getIntent();
		
		if (intent != null) {
			//se existe os parametros nome e fone é pq outra intent chamou esta
			//a de busca...
			Bundle extras = intent.getExtras();
			if (extras != null) {
				arquivo = (File) extras.get("arquivo");
			}
		}else{
			//file:///tmp/nome.txt
			arquivo = new File("/tmp/nome.txt");
		}
		
		try {
			TextView view = new TextView(this);
			view.setText("arquivo hello");

			//Cria a URI: "file://xxx"
			Uri a = Uri.fromFile(arquivo);
			
			InputStream is = getContentResolver().openInputStream(a);
			StringBuffer sb = readStreamAsString(is);

			view.setText(sb.toString());

			setContentView(view);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private StringBuffer readStreamAsString(InputStream is) {

		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			String str;
			while ((str = in.readLine()) != null) {
				sb.append(str);
				sb.append("\n");
			}
			in.close();
		} catch (IOException e) {
		}
		return sb;
	}
}
