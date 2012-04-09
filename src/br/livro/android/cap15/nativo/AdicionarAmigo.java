package br.livro.android.cap15.nativo;

import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Permission: WRITE_CONTACTS
 * 
 * @author rlecheta
 *
 */
public class AdicionarAmigo extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.adicionar_contato);

		findViewById(R.id.adicionar).setOnClickListener(this);
	}

	/**
	 * @see android.view.View$OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View view) {
		// Cria os valores para adicionar
		ContentValues info = new ContentValues();

		info.put(Contacts.People.NAME, getValue(R.id.nome));

		ContentResolver contentResolver = this.getContentResolver();
		Uri novoAmigo = contentResolver.insert(Contacts.People.CONTENT_URI,info);

		if (novoAmigo != null) {
			// assign the new phone number to the person
			info.clear();
			
			List pathList = novoAmigo.getPathSegments();
			String pathLeaf = (String) pathList.get(pathList.size() - 1);

			info.put(Contacts.Phones.PERSON_ID, pathLeaf);
			info.put(Contacts.Phones.NUMBER, getValue(R.id.fone));
			// insert the new phone number in the database
			getContentResolver().insert(Contacts.Phones.CONTENT_URI, info);
			Toast.makeText(this,"Contato Adicionado com sucesso: \n" + novoAmigo,Toast.LENGTH_LONG).show();
		}
        
	}

	private String getValue(int id) {
		EditText text = (EditText) findViewById(id);
		return text.getText().toString();
	}
}
