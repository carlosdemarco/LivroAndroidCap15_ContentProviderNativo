package br.livro.android.cap15.nativo;

import java.io.IOException;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

public class ListaVideosSDCard extends ListaAudio {
	
	@Override
	protected Uri getUri() {
		return MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
	}
	@Override
	protected void play(String uri) throws IOException {
		Intent intent = new Intent(this,ExemploVideoView.class);
		intent.putExtra("video", uri.toString());
		startActivity(intent);
	}
}
