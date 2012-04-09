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
public class MenuContatos extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String[] ops = new String[] { 
				"Listar Todos",
				"Listar Contatos Escolher",
				"Buscar o Contato 1",
				"Buscar por SQL",
				"Adicionar Amigo",
				"Voltar"};

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, ops));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				//Todos
				startActivity(new Intent(this,ListaContatos.class));
				break;
			case 1:
				//ACTION_PICK Dispara Intent para escolher contato
				startActivity(new Intent(this,ListarContatosEscolher.class));
				break;
			case 2:
				//Buscar Contato 1
				startActivity(new Intent(this,VisualizarContato.class));
				break;
			case 3:
				//Buscar Com SQL utilizando uma clasula WHERE
				startActivity(new Intent(this,BuscaContatoWhere.class));
				break;
			case 4:
				//Adicinar um novo amigo utilizando o ContentProvider
				startActivity(new Intent(this,AdicionarAmigo.class));
				break;
			default:
				finish();
				break;
		}
	}
}