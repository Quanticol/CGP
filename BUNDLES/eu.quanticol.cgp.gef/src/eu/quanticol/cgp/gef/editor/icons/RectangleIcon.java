package eu.quanticol.cgp.gef.editor.icons;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class RectangleIcon implements CustomIcon{
	
	@Override
	public Image getImage16(RGB colour, Display display) {
		RGB white = new RGB(255,255,255);
		RGB black = new RGB(0,0,0);
		
		PaletteData paletteData = new PaletteData(
		        new RGB[] {white, colour, black});
		    ImageData imageData = new ImageData(16,16,4,paletteData);
		    
    
		    int[] pixelMap = new int[]{
		    		2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
		    		
		    };
		    
		    imageData.setPixels(0, 0, 256, pixelMap, 0);
		    imageData.transparentPixel = 0;
		    Image image = new Image(display,imageData);
		return image;
	}

	@Override
	public Image getImage24(RGB colour, Display display) {
		RGB white = new RGB(255,255,255);
		RGB black = new RGB(0,0,0);
		
		PaletteData paletteData = new PaletteData(
		        new RGB[] {white, colour, black});
		    ImageData imageData = new ImageData(24,24,4,paletteData);
		    
    
		    int[] pixelMap = new int[]{
		    		2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,
		    		2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
		    		
		    };
		    
		    imageData.setPixels(0, 0, 24*24, pixelMap, 0);
		    imageData.transparentPixel = 0;
		    Image image = new Image(display,imageData);
		return image;
	}




	
	
	
}
