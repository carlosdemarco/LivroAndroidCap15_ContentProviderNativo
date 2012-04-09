package br.livro.android.cap15.nativo;

import java.io.IOException;

import android.app.ListActivity;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Exemplo que lista os arquivos de áudios internos do Android utilizando um provedor de conteúdo.
 * 
 * Ao selecionar algum áudio da lista toca o mesmo com o MediaPlayer
 * 
 * @author ricardo
 *
 */
public class ListaAudio extends ListActivity {

	private static final String CATEGORIA = "livro";
	private Cursor mCursor;
	private SimpleCursorAdapter listAdapter;
	private MediaPlayer player;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		Uri uri = getUri();
		Log.i(CATEGORIA,"Áudio URI: " + uri);
		mCursor = managedQuery(uri, null, null, null, null);

		startManagingCursor(mCursor);

        String[] from = new String[]{MediaStore.Audio.AudioColumns.DISPLAY_NAME,MediaStore.Audio.AudioColumns.TITLE};

        int[] to = new int[]{R.id.nome,R.id.fone};

        //liga as colunas com os elementos da tela
        this.listAdapter = new SimpleCursorAdapter(this, R.layout.lista_contatos, mCursor, from, to);

        setListAdapter(listAdapter);
	}

	/**
	 * Retorna a URI para buscar arquivos de Áudio.
	 * 
	 * Default áudio interno
	 * 
	 * @return
	 */
	protected Uri getUri() {
		return MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Log.i(CATEGORIA,"Musica: " + id);

        //Como foi utilizado o SimpleCursorAdapter já está posicionado no item selecionado

		long _id = mCursor.getLong(mCursor.getColumnIndex(MediaStore.Audio.AudioColumns._ID));
		Log.i(CATEGORIA,"_id: " + _id);

		String uri = mCursor.getString(mCursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA));

		try {
			Log.i(CATEGORIA,"Tocando: " + uri);
			
			play(uri);
		} catch (Exception e) {
			Log.e(CATEGORIA,e.getMessage(),e);
		}
	}

	protected void play(String uri) throws IOException {
		if(player == null){
			player = new MediaPlayer();
		} else {
			player.stop();
			player.reset();
		}

		player.setDataSource(uri);
		player.prepare();
		player.start();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(player != null){
			player.stop();
			player.release();
		}
		player = null;
	}
}
