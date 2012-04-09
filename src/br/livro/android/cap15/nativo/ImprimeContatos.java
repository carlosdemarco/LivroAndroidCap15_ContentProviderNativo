package br.livro.android.cap15.nativo;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;

/**
 * Exemplo de como ler os resultados do Cursor
 * 
 * Foi utilizada a Uri "content://com.android.contacts/contacts" para ler os contatos
 * 
 * 
 * @author rlecheta
 * 
 */
public class ImprimeContatos extends Activity {
	private static final String CATEGORIA = "livro";

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		TextView textView = new TextView(this);
		textView.setText("Verifique o LogCat");
		setContentView(textView);

		// Recupera a Uri, mesmo que "content://com.android.contacts/contacts". 
		Uri contatos = ContactsContract.Contacts.CONTENT_URI;
		Log.i(CATEGORIA,"Uri: " + contatos);

		// Recupera o Cursor para percorrer a lista de contatos.
		Cursor cursor = managedQuery(contatos, null, null, null,null);
		startManagingCursor(cursor);
		imprimeContatos(cursor);
	}

	private void imprimeContatos(Cursor cursor) {
		Log.i(CATEGORIA,"Logando os nomes dos contatos cadastrados...");

		int count = cursor.getCount();

		Log.i(CATEGORIA,"Foram encontrados "+count+" contatos.");

		while(cursor.moveToNext()){
			String nome = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

			Log.i(CATEGORIA,"Nome: " + nome);
		}
	}
}