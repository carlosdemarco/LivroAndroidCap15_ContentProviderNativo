package br.livro.android.cap15.nativo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class BuscaContatoWhere extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.busca_contato);
	
		findViewById(R.id.buscar).setOnClickListener(this);
	}
	
	/**
	 * @see android.view.View$OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View view) {
		EditText nomeText = (EditText) findViewById(R.id.nome);
		String nomeDigitato = nomeText.getText().toString();
		
		// Faz a pesquisa, semelhante a: SELECT * FROM CONTATOS WHERE NOME = ?
		Cursor c = managedQuery(android.provider.Contacts.People.CONTENT_URI, null,Contacts.People.NAME + " = '"+nomeDigitato+"'", null, null);

		if(c == null){
			Toast.makeText(this,"Nenhum contato encontrado",Toast.LENGTH_SHORT).show();
			return;
		}
		
        startManagingCursor(c);

        boolean encontrou = c.moveToFirst();

        if(encontrou){
        	
        	String nome = c.getString(c.getColumnIndexOrThrow(Contacts.People.NAME));
        	String fone = c.getString(c.getColumnIndexOrThrow(Contacts.People.NUMBER));

        	Intent intent = new Intent(this,VisualizarContato.class);
        	intent.putExtra("nome", nome);
        	intent.putExtra("fone", fone);
        	
        	startActivityForResult(intent,1);
        } else {
        	Toast.makeText(this,"Nenhum contato encontrado com o nome informado",Toast.LENGTH_SHORT).show();
        }
	}
}
