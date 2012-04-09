package br.livro.android.cap15.nativo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Exemplo que visualiza os contatos da agenda.
 * 
 * Depois de escolher um contato exibe o nome em uma alerta.
 * 
 * @author ricardo
 * 
 */
public class ListarContatosEscolher extends Activity {
	// Constante para identificar a sub-activity lançada
	private static final int SELECIONAR_CONTATO = 1;
	private static final String CATEGORIA = "livro";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView view = new TextView(this);
		view.setText("Teste contatos");
		setContentView(view);
		
		// Visualizar o Contato 1 da lista de contatos
		Uri uri = android.provider.Contacts.People.CONTENT_URI;

		// Cria a Intent
		Intent it = new Intent(Intent.ACTION_PICK,uri);

		// Envia a mensagem assíncrona ao sistema operacional
		startActivityForResult(it,SELECIONAR_CONTATO); 
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent it) {
		//imprimirNome(intent);

		if(it == null){
			Toast.makeText(this, "Nenhum contato", Toast.LENGTH_SHORT).show();
			return;
		}

		// URI que identifica o contato
		Uri uri = it.getData();

		// Solicita o Android para visualizar o contato
//		startActivity(new Intent(Intent.ACTION_VIEW,uri)); 

		imprimirNome(uri);
	}

	private void imprimirNome(Uri uri) {

		Log.i(CATEGORIA,"Visualizando contato: " + uri);

		// Busca o contato no banco de dados
		Cursor c = managedQuery(uri, null, null, null, null);
		
		boolean encontrou = c.moveToNext();
		
		if(encontrou) {

			int index = c.getColumnIndexOrThrow(android.provider.Contacts.PeopleColumns.NAME); 
			
			String nome = c.getString(index);

			Toast.makeText(this, "Nome: " + nome, Toast.LENGTH_SHORT).show();
		}
	}
}