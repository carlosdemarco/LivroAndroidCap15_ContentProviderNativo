package br.livro.android.cap15.nativo.agenda;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;

/**
 * Implementa com o Content Provider do Android 2.x
 * 
 * content://com.android.contacts/contacts
 * 
 * @author ricardo
 * 
 */
public class Agenda {
	// content://com.android.contacts/contacts
	private static final Uri URI = ContactsContract.Contacts.CONTENT_URI;
	
	public List<Contato> getContatos(Context context) {

		List<Contato> contatos = new ArrayList<Contato>();

		// Recupera o Cursor para percorrer a lista de contatos
		Cursor c = context.getContentResolver().query(URI, null, null, null,
				null);

		try {
			while (c.moveToNext()) {
				Contato a = lerContato(context, c);
				contatos.add(a);
			}
		} finally {
			// Fecha o Cursor
			c.close();
		}

		return contatos;
	}

	public Contato lerContato(Context context, Cursor cursor) {
		Contato c = new Contato();

		// Id e nome
		long id = cursor.getLong(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
		c.id = id;
		
		String nome = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
		c.nome = nome;

		// Fone
		boolean temFone = "1".equals(cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
		if (temFone) {
			List<String> fones = loadFones(context, id);
			c.fones = fones;
		}
		
		// Foto
		Bitmap b = lerFoto(context, id);
		c.foto = b;

		return c;
	}

	// Busca os telefones na tabela 'ContactsContract.CommonDataKinds.Phone'
	private List<String> loadFones(Context context, long id) {
		List<String> fones = new ArrayList<String>();
	
		Cursor cursor = context.getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
				ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
				null, null);
	
		try {
			while (cursor.moveToNext()) {
				int coluna = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
				String fone = cursor.getString(coluna);
				fones.add(fone);
			}
		} finally {
			cursor.close();
		}
		
		return fones;
	}

	// Le a foto de um contato
	private Bitmap lerFoto(Context context, long id) {
		// Cria a Uri para o id fornecido
		Uri uri = ContentUris.withAppendedId(Contacts.CONTENT_URI, id);
		ContentResolver contentResolver = context.getContentResolver();
		InputStream in = ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, uri);
		if (in != null) {
			Bitmap foto = BitmapFactory.decodeStream(in);
			return foto;
		}
		return null;
	}
}