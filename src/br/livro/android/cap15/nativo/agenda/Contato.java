package br.livro.android.cap15.nativo.agenda;

import java.util.List;

import android.content.ContentUris;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;

/**
 * Representa um contato da agenda
 * 
 * @author ricardo
 * 
 */
public class Contato {

	public long id;
	public String nome;
	public Bitmap foto;
	public List<String> fones;

	// Retorna a URI deste contato, ex: "content://com.android.contacts/contacts/1"
	public Uri getUri() {
		Uri uri = ContentUris.withAppendedId(Contacts.CONTENT_URI, id);
		return uri;
	}
}
