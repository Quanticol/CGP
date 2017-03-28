package eu.quanticol.cgp.gef;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import eu.quanticol.cgp.gef.editor.figure.CGPCircleFigure;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.ConnectionPrototype;
import eu.quanticol.cgp.model.NodePrototype;

public class LooksCatalog {
	private static Map<NodePrototype, Color> nodeColours = new HashMap<>();
	private static Map<ConnectionPrototype, Color> connectionColours = new HashMap<>();
	private static Map<ComponentPrototype, Color> componentColours = new HashMap<>();
	private static Color defaultColour;
	
	private static Map<String, IFigure> shapes = new HashMap<>();
	
	static void print(){
		System.out.println("LooksCatalog:");
		System.out.println("Nodes:");
		for(NodePrototype s : nodeColours.keySet()){
			System.out.println(s.getName() + " -> " + nodeColours.get(s).getRed() + ", "+nodeColours.get(s).getGreen() + ", "+ nodeColours.get(s).getBlue());
		}
		System.out.println("Connections:");
		for(ConnectionPrototype s : connectionColours.keySet()){
			System.out.println(s.getName() + " -> " + connectionColours.get(s).getRed() + ", "+connectionColours.get(s).getGreen() + ", "+ connectionColours.get(s).getBlue());
		}
		System.out.println("Components:");
		for(ComponentPrototype s : componentColours.keySet()){
			System.out.println(s.getName() + " -> " + componentColours.get(s).getRed() + ", "+componentColours.get(s).getGreen() + ", "+ componentColours.get(s).getBlue());
		}
	}
	
	static{
		Device device = Display.getCurrent();
		defaultColour =  new Color(device, 255, 0,0);		
	}

    
	public static void setColour1(NodePrototype np, Color c){
		nodeColours.put(np, c);
	}
	
	public static void setColour1(ConnectionPrototype cnp, Color c){
		connectionColours.put(cnp, c);
	}
	
	public static void setColour1(ComponentPrototype cp, Color c){
		componentColours.put(cp, c);
	}
	
	public static Color getDefaultColour1(){
		return defaultColour;
	}

	public static Color getNodeColor1(NodePrototype prototype) {
		Color result = nodeColours.get(prototype);
		if(result == null){
			return defaultColour;
		}
		return result;
	}

	public static Color getComponentColor1(ComponentPrototype prototype) {
		Color result = componentColours.get(prototype);
		if(result == null){
			return defaultColour;
		}
		return result;
	}

	public static Color getConnectionColor1(ConnectionPrototype prototype) {
		Color result = connectionColours.get(prototype);
		if(result == null){
			return defaultColour;
		}
		return result;
	}
}
