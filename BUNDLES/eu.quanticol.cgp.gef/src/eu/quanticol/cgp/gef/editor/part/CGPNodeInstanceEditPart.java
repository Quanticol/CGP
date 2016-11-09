package eu.quanticol.cgp.gef.editor.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import eu.quanticol.cgp.gef.editor.figure.CGPNodeFigure;
import eu.quanticol.cgp.model.NodeInstance;

public class CGPNodeInstanceEditPart extends AbstractGraphicalEditPart {
	
	@Override
	protected IFigure createFigure() {
		return new CGPNodeFigure();
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}

	@Override protected void refreshVisuals() {
		CGPNodeFigure figure = (CGPNodeFigure) getFigure();
	    NodeInstance model = (NodeInstance) getModel();
	    CGPSpatialModelEditPart parent = (CGPSpatialModelEditPart) getParent();
	     
	    figure.getLabel().setText(model.getPrototype().getName());
	    Rectangle layout = new Rectangle(model.getX(), model.getY(), 50, 50);
	    parent.setLayoutConstraint(this, figure, layout);
	}

}
