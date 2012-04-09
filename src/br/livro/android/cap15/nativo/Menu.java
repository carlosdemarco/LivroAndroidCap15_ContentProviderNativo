package br.livro.android.cap15.nativo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplos de ContentProvider
 * 
 * @author rlecheta
 * 
 */
public class Menu extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String[] ops = new String[] { 
				"Contatos", 
				"Arquivos",
				"Imagens / Áudio / Vídeo",
				"Carros - Customizado",
				"Sair" };

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, ops));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				//Contatos
				startActivity(new Intent(this,MenuContatos.class));
				break;
			case 1:
				//Arquivos
				startActivity(new Intent(this,ListaArquivos.class));
				break;
			case 2:
				//Imagens
				startActivity(new Intent(this,MenuMedia.class));
				break;
			case 3:
				//Carros
				startActivity(new Intent("CADASTRO_CARROS"));
				break;
			default:
				
				//sair da Activity
				finish();
				break;
		}
	}
}