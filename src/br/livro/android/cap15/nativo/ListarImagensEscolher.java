package br.livro.android.cap15.nativo;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Mostra como disparar uma Intent para o Android para escolher uma foto
 * 
 * @author ricardo
 * 
 */
public class ListarImagensEscolher extends Activity {
	// Constante para identificar a sub-activity lançada
	private static final String CATEGORIA = "livro";
	public static final String IMAGE_MIME_TYPE = "image/*";
	private ImageView imgView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		imgView = new ImageView(this);
		imgView.setImageResource(R.drawable.icon);
		setContentView(imgView);

		Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        innerIntent.setType(IMAGE_MIME_TYPE);
        Intent wrapperIntent = Intent.createChooser(innerIntent, null);
        startActivityForResult(wrapperIntent, 3);
		
//		// Visualizar o Contato 1 da lista de contatos
//		Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//
//		// Cria a Intent
//		Intent it = new Intent(Intent.ACTION_PICK,uri);
//
//		// Envia a mensagem ass�ncrona ao sistema operacional
//		startActivityForResult(it,SELECIONAR_CONTATO); 
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
		Toast.makeText(this, "Uri: " + uri, Toast.LENGTH_SHORT).show();

		Bitmap bitmap = getImageFromUri(this, uri, 200, 200);
		imgView.setImageBitmap(bitmap);
	}

	public static Bitmap getImageFromUri(Context context, Uri uri, int width, int height) {
        ParcelFileDescriptor pfd;

        try {
            pfd = context.getContentResolver().openFileDescriptor(uri, "r");
        } catch(FileNotFoundException fnfe) {
            Log.e(CATEGORIA, fnfe.getLocalizedMessage());
            return null;
        }

        //get the dimensions of the image
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(pfd.getFileDescriptor(), null, opts);

        // get the image and scale it appropriately
        opts.inJustDecodeBounds = false;
        opts.inSampleSize = Math.max(opts.outWidth/width, opts.outHeight/height);

        Bitmap bmp = BitmapFactory.decodeFileDescriptor(pfd.getFileDescriptor(), null, opts);
        if (bmp == null) {
            return null;
        }

        return Bitmap.createScaledBitmap(bmp,width, height, false);
    }
}