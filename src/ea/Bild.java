package ea;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.*;
import android.net.Uri;
import ea.android.GameActivity;
import ea.android.Spiel;
import ea.internal.collision.Collider;

@SuppressWarnings("serial")
public class Bild extends Raum {

	private Bitmap bmp;
	
	public Bild(String verzeichnis) {
		this(0, 0, verzeichnis);
	}
	
	public Bild(float x, float y, String verzeichnis) {
		super.position = new Punkt(x, y);
		bitmapLaden(verzeichnis);
	}
	
	private void bitmapLaden(String name)
	{
		try {
	        AssetManager assetManager = Spiel.instanz().zeichnerGeben().getContext().getAssets();
	        InputStream inputStream = assetManager.open(name);
	        
	        bmp = BitmapFactory.decodeStream(inputStream);
	        inputStream.close();
	      } 
		  catch (IOException e) {
	        
	      }
	}
	
	@Override
	public void zeichnen(Canvas g, BoundingRechteck r) {
		if (r.schneidetBasic(this.dimension())) {
			if(bmp != null)
				g.drawBitmap(bmp, position.x - r.x, position.y - r.y, new Paint());
		}
	}

	@Override
	public BoundingRechteck dimension() {
		return new BoundingRechteck(position.x, position.y, bmp.getWidth(), bmp.getHeight());
	}

	@Override
	public Collider erzeugeCollider() {
		return erzeugeLazyCollider();
	}
	
	public Bitmap bild() {
		return bmp;
	}
	
	public void bildAendern(String pfad)
	{
		bitmapLaden(pfad);
	}
}
