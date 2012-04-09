package br.livro.android.cap15.nativo;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Exemplo de ContentProvider nativo para ler os contatos da agenda
 * 
 * @author rlecheta
 * 
 */
public class ListaContatos extends ListActivity {

	private SimpleCursorAdapter listAdapter;
	private Cursor cursor;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Recupera a Uri, mesmo que "content://contacts/people". 
		Uri contatos = android.provider.Contacts.People.CONTENT_URI;

		// Gostaria de buscar apenas 1 ID ?
//		contatos.addId(1);

		// Recupera o Cursor para percorrer a lista de contatos.
		cursor = managedQuery(contatos, null, null, null,null);

        startManagingCursor(cursor);

        //Listar o nome do contato
        String[] from = new String[]{Contacts.People.NAME,Contacts.People.NUMBER};

        int[] to = new int[]{R.id.nome,R.id.fone};

        //liga as colunas com os elementos da tela
        this.listAdapter = new SimpleCursorAdapter(this, R.layout.lista_contatos, cursor, from, to);

        setListAdapter(listAdapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		Object o = this.getListAdapter().getItem(position);
		String valor = o.toString();

		Toast.makeText(this, valor + " - " + android.provider.Contacts.People.CONTENT_URI, Toast.LENGTH_SHORT).show();
	}
}