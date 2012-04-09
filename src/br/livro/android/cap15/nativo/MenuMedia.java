package br.livro.android.cap15.nativo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Imagem  / Áudio / Vídeo 
 * 
 * @author rlecheta
 * 
 */
public class MenuMedia extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		String[] mStrings = new String[] {
				"Escolher Foto",
				"Imagens",
				"Áudio",
				"Áudio - SDCard",
				"Vídeo",
				"Vídeo - SDCard",
				"Voltar" };

		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mStrings));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				//Arquivos
				startActivity(new Intent(this,ListarImagensEscolher.class));
				break;
			case 1:
				//Imagens
				startActivity(new Intent(this,ListaImagens.class));
				break;
			case 2:
				//�udio
				startActivity(new Intent(this,ListaAudio.class));
				break;
			case 3:
				//�udio SD-Card
				startActivity(new Intent(this,ListaAudioSDCard.class));
				break;
			case 4:
				//V�deo
				startActivity(new Intent(this,ListaVideos.class));
				break;
			case 5:
				//V�deo SD-Card
				startActivity(new Intent(this,ListaVideosSDCard.class));
				break;
			default:
				
				//sair da Activity
				finish();
				break;
		}
	}
}