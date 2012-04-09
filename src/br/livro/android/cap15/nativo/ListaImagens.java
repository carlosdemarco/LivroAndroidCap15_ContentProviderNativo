package br.livro.android.cap15.nativo;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

public class ListaImagens extends ListActivity {

	private static final String ID = "livro";

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		go(MediaStore.Images.Media.INTERNAL_CONTENT_URI);
		go(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		
	}

	private void go(Uri uri) {
		Log.i(ID,">> " + uri);
		Cursor cursor = managedQuery(uri, null, null, null, null);

        if (cursor != null) {
			startManagingCursor(cursor);
			
			while (cursor.moveToNext()) {
				String[] columnNames = cursor.getColumnNames();
				if(columnNames != null){
					for (int i = 0; i < columnNames.length; i++) {
						String nome = columnNames[i];
						int idx = cursor.getColumnIndex(nome);
						String valor = cursor.getString(idx);
						Log.i(ID,nome + " >> " + valor);		
					}
				}
			}
		}
	}
}
