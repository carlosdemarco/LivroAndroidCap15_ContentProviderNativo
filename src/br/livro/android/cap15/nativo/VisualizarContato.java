package br.livro.android.cap15.nativo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Visualizar o Contato com o ID=1, com o cursor
 * 
 * @author rlecheta
 *
 */
public class VisualizarContato extends Activity implements OnClickListener {

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		setContentView(R.layout.detalhes_contato);

		//configura o listener do botão voltar
		Button voltar = (Button) findViewById(R.id.voltar);
		voltar.setOnClickListener(this);
		
		String nome = null;
		String fone = null;

		//pega a intent que iniciou a activity
		Intent intent = getIntent();
		
		if (intent != null) {
			//se existe os parametros nome e fone é pq outra intent chamou esta
			//a de busca...
			Bundle extras = intent.getExtras();
			if (extras != null) {
				nome = extras.getString("nome");
				fone = extras.getString("fone");
				if (nome != null && fone != null) {
					atualizaValores(nome, fone);
					return;
				}
			}
		}
		// Mesmo que "content://contacts/people/1". 
		Uri amigo1 = Uri.withAppendedPath(android.provider.Contacts.People.CONTENT_URI, "1");

		// Recupera o Cursor para percorrer a lista de contatos.
		Cursor c = managedQuery(amigo1, null, null, null, null);

        startManagingCursor(c);
        
        boolean encontrou = c.moveToFirst();

        if(encontrou){
        	
        	nome = c.getString(c.getColumnIndexOrThrow(Contacts.People.NAME));
        	fone = c.getString(c.getColumnIndexOrThrow(Contacts.People.NUMBER));
        	
        	atualizaValores(nome, fone);
        } else {
        	Toast.makeText(this,"Nenhum contato encontado com ID=1",Toast.LENGTH_LONG).show();
        }
	}

	private void atualizaValores(String nome, String fone) {
		TextView textNome = (TextView) findViewById(R.id.nome);
		textNome.setText(nome);

		TextView textFone = (TextView) findViewById(R.id.fone);
		textFone.setText(fone);
	}

	/**
	 * @see android.view.View$OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View view) {
		finish();
	}
}
