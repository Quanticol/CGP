package eu.quanticol.cgp.gef.editor.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.LineAttributes;

import eu.quanticol.cgp.gef.editor.policy.CGPConnectionInstanceEditPolicy;

public class CGPConnectionEditPart extends AbstractConnectionEditPart {

	public CGPConnectionEditPart() {
		super();
	}
	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE, new ConnectionEndpointEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ROLE, new CGPConnectionInstanceEditPolicy());
		
	}

	@Override
	protected IFigure createFigure(){
		PolylineConnection connection = new PolylineConnection();
		connection.setLineAttributes(new LineAttributes(2.0f, SWT.CAP_ROUND, SWT.JOIN_ROUND));
		PolygonDecoration arrow = new PolygonDecoration();
		arrow.setTemplate(PolygonDecoration.TRIANGLE_TIP);
		arrow.setScale(5,2.5);
		connection.setTargetDecoration(arrow);
		return connection;
	}
	
}
