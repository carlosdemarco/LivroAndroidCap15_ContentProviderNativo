package br.livro.android.cap15.nativo;

import android.net.Uri;
import android.provider.MediaStore;

/**
 * Exemplo que lista os arquivos de áudios externos "SD-Card"
 * 
 * Ao selecionar algum áudio da lista toca o mesmo com o MediaPlayer
 * 
 * @author ricardo
 *
 */
public class ListaAudioSDCard extends ListaAudio {
	@Override
	protected Uri getUri() {
		return MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	}
}
