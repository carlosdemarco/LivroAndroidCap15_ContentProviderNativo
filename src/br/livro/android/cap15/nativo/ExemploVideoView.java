package br.livro.android.cap15.nativo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Simples teste do VideoView
 * 
 * @author ricardo
 * 
 */
public class ExemploVideoView extends Activity {
	private static final String CATEGORIA = "livro";
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		
		Intent intent = getIntent();
		if(intent != null) {
			String s = intent.getStringExtra("video");
			Log.i(CATEGORIA,"Vídeo: " + s);
			
			VideoView v = new VideoView(this);
			setContentView(v);

			v.setVideoPath(s);
			v.setMediaController(new MediaController(this));
			v.requestFocus();
		}
	}
}
