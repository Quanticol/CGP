package eu.quanticol.cgp.gef.editor.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.widgets.Display;

import eu.quanticol.cgp.gef.editor.figure.CGPFigure;
import eu.quanticol.cgp.gef.editor.figure.CGPNodeFigure;
import eu.quanticol.cgp.gef.editor.policy.CGPConnectionInstanceEditPolicy;
import eu.quanticol.cgp.model.Colour;
import eu.quanticol.cgp.model.ConnectionInstance;
import eu.quanticol.cgp.model.NodeInstance;

public class CGPConnectionEditPart extends AbstractConnectionEditPart {

	private Color colourToColor(Colour colour){
		Device display = Display.getCurrent();
		return new Color(display, colour.getRed(),colour.getGreen(),colour.getBlue() );
	}


	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new CGPConnectionInstanceEditPolicy());
		
	}

	@Override
	protected IFigure createFigure(){
		PolylineConnection connection = new PolylineConnection();
		ConnectionInstance model = (ConnectionInstance)getModel();
		connection.setForegroundColor(colourToColor(model.getPrototype().getColour()));
		connection.setBackgroundColor(colourToColor(model.getPrototype().getColour()));
		
		System.out.println("Using connection colour " + model.getPrototype().getColour().getRed() + " " + model.getPrototype().getColour().getGreen() + " " +model.getPrototype().getColour().getBlue());
		
		connection.setLineAttributes(new LineAttributes(2.0f, SWT.CAP_ROUND, SWT.JOIN_ROUND));
		PolygonDecoration arrow = new PolygonDecoration();
		arrow.setTemplate(PolygonDecoration.TRIANGLE_TIP);
		arrow.setScale(5,2.5);
		connection.setTargetDecoration(arrow);
		return connection;
	}
	
}
