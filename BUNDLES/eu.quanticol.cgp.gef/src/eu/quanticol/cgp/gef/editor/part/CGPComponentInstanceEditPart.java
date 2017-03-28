package eu.quanticol.cgp.gef.editor.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Display;

import eu.quanticol.cgp.gef.LooksCatalog;
import eu.quanticol.cgp.gef.editor.figure.CGPCircleFigure;
import eu.quanticol.cgp.gef.editor.figure.CGPComponentFigure;
import eu.quanticol.cgp.gef.editor.figure.CGPFigure;
import eu.quanticol.cgp.gef.editor.figure.CGPNodeFigure;
import eu.quanticol.cgp.gef.editor.figure.CGPRectangleFigure;
import eu.quanticol.cgp.gef.editor.figure.CGPStarFigure;
import eu.quanticol.cgp.model.Colour;
import eu.quanticol.cgp.model.ComponentInstance;
import eu.quanticol.cgp.model.NodeInstance;
import eu.quanticol.cgp.model.Shape;

public class CGPComponentInstanceEditPart extends CGPLocatedElementEditPart {


	private Color colourToColor(Colour colour){
		Device display = Display.getCurrent();
		return new Color(display, colour.getRed(),colour.getGreen(),colour.getBlue() );
	}



	@Override
	protected IFigure createFigure() {
		ComponentInstance model = (ComponentInstance)getModel();
		Shape shape = model.getPrototype().getShape();
		
		//default
		CGPFigure figure = new CGPCircleFigure();
		if(shape.getName().equals("circle")){
			figure = new CGPCircleFigure();
		}
		else if(shape.getName().equals("rectangle")){
			figure = new CGPRectangleFigure();
		}
		else if(shape.getName().equals("star")){
			figure = new CGPStarFigure();
		}
		
		figure.setColour(colourToColor(model.getPrototype().getColour()));
		return figure;
	}

}
