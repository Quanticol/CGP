package eu.quanticol.cgp.gef.editor.part;

import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Display;

import eu.quanticol.cgp.gef.LooksCatalog;
import eu.quanticol.cgp.gef.editor.figure.CGPCircleFigure;
import eu.quanticol.cgp.gef.editor.figure.CGPFigure;
import eu.quanticol.cgp.gef.editor.figure.CGPNodeFigure;
import eu.quanticol.cgp.gef.editor.policy.CGPLocatedElementEditPolicy;
import eu.quanticol.cgp.gef.editor.policy.CGPNodeInstanceEditPolicy;
import eu.quanticol.cgp.gef.editor.policy.NodeInstanceGraphicalNodeEditPolicy;
import eu.quanticol.cgp.model.Colour;
import eu.quanticol.cgp.model.ConnectionInstance;
import eu.quanticol.cgp.model.NodeInstance;

public class CGPNodeInstanceEditPart extends CGPLocatedElementEditPart implements NodeEditPart {
	
	private Color colourToColor(Colour colour){
		Device display = Display.getCurrent();
		return new Color(display, colour.getRed(),colour.getGreen(),colour.getBlue() );
	}


	@Override
	protected IFigure createFigure() {
		CGPFigure figure = new CGPNodeFigure();
		NodeInstance model = (NodeInstance)getModel();
		figure.setColour(colourToColor(model.getPrototype().getColour()));
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy("Snap Feedback", new SnapFeedbackPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new CGPNodeInstanceEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new NodeInstanceGraphicalNodeEditPolicy());
	}

	@Override
	protected List<ConnectionInstance> getModelSourceConnections() {
		NodeInstance model = (NodeInstance)getModel();
		return model.getOutgoingConnections();
	}
	
	@Override
	protected List<ConnectionInstance> getModelTargetConnections() {
		NodeInstance model = (NodeInstance)getModel();
		return model.getIncomingConnections();
	}
	
	
	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart arg0) {
		return ((CGPNodeFigure)getFigure()).getConnectionAnchor();
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request arg0) {
		return ((CGPNodeFigure)getFigure()).getConnectionAnchor();
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart arg0) {
		return ((CGPNodeFigure)getFigure()).getConnectionAnchor();
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request arg0) {
		return ((CGPNodeFigure)getFigure()).getConnectionAnchor();
	}

}
