package br.livro.android.cap15.nativo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Exemplo que simula um File Explorer para navegar na estrutura de diretórios do emulador
 * 
 * @author rlecheta
 *
 */
public class ListaArquivos extends ListActivity {
	
	private static final String ID = "livroandroid";
	private List<String> items = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.lista_arquivos);

        //preenche a lista com o diretório raiz
        fillWithRoot();
    }

    private void fillWithRoot() {
    	fillList(new File("/").listFiles());
    }

    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		int idx = (int) getSelectedItemId();
		if (idx == 0) {
			fillWithRoot();
		} else {
			File file = new File(items.get(idx));
			if (file.isDirectory())
				fillList(file.listFiles());
			else
			{
			   Intent intent = new Intent(this,VisualizarArquivo.class);
			   intent.putExtra("arquivo", file);
			   startActivityForResult(intent,0);
			}
		}
	}

	/**
	 * Preenche a lista de arquivos
	 * 
	 * @param files
	 */
	private void fillList(File[] files) {
		items = new ArrayList<String>();
		items.add(getString(R.string.voltar));

		for (File file : files)
		{
			items.add(file.getPath());
		}

		//Cria o ArrayAdapter para visualizar a Lista
		ArrayAdapter<String> fileList = new ArrayAdapter<String>(this,R.layout.detalhes_arquivo, items);
		setListAdapter(fileList);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    super.onCreateOptionsMenu(menu);

	    menu.add(0, 0,0, "Deletar");
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

	    int id = item.getItemId();
		switch (id) {
	    	//Deleta o arquivo
		    case 0:
		    	int idx = getSelectedItemPosition();
		    	String path = items.get(idx);
				File file = new File(path);
				if(file.exists()){
					boolean deletou = file.delete();
					Log.i(ID,"deletou: " + deletou);
				}
				
				return true;
		    }
	    return false;
	}
}