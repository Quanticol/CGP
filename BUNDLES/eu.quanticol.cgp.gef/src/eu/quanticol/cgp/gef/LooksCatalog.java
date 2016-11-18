package eu.quanticol.cgp.gef;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import eu.quanticol.cgp.gef.editor.figure.CGPCircleFigure;

public class LooksCatalog {
	private static Map<String, Color> colours = new HashMap<>();
	private static Map<String, IFigure> shapes = new HashMap<>();
	
	static{
		Device device = Display.getCurrent();
		colours.put("red", new Color(device, 255, 0,0));
		colours.put("green", new Color(device, 0, 255,0));
		colours.put("blue", new Color(device, 0, 0,255));
		
		
		
	}

	public static Color getColour(String colourName){
		return colours.get(colourName);
	}
	
	public static Color getDefaultColour(){
		return colours.get("red");
	}
}
