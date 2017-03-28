package eu.quanticol.cgp.gef.editor.icons;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class ConnectionIcon implements CustomIcon {

	@Override
	public Image getImage16(RGB colour, Display display) {
		RGB white = new RGB(255,255,255);
		RGB black = new RGB(0,0,0);
		
		PaletteData paletteData = new PaletteData(
		        new RGB[] {white, colour, black});
		    ImageData imageData = new ImageData(16,16,4,paletteData);
		    
    
		    int[] pixelMap = new int[]{
		    		0,0,0,0,0,0,0,1, 1,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,1, 1,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,1, 1,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,1, 1,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,1, 1,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,1, 1,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,1, 1,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,1, 1,0,0,0,0,0,0,0,		
		    		0,0,0,0,0,0,0,1, 1,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,1, 1,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,1, 1,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,1, 1,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,1, 1,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,1, 1,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,1, 1,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,1, 1,0,0,0,0,0,0,0,
		    		
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
		    if(imageData.getRGBs() != null){
		    	//System.out.println("NOT NULL IN HERE");
		    }
		    else{
		    	//System.out.println("Hello this is null");
		    }
		    
    
		    int[] pixelMap = new int[]{
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		0,0,0,0,0,0,0,0,0,0,1,1, 1,1,0,0,0,0,0,0,0,0,0,0,
		    		
		    		
		    };
		    
		    imageData.setPixels(0, 0, 24*24, pixelMap, 0);
		    imageData.transparentPixel = 0;
		    Image image = new Image(display,imageData);
		return image;
	}

}