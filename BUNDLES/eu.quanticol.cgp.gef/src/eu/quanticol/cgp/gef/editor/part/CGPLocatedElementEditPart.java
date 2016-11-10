package eu.quanticol.cgp.gef.editor.part;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import eu.quanticol.cgp.gef.editor.figure.CGPFigure;
import eu.quanticol.cgp.model.LocatedElement;

public abstract class CGPLocatedElementEditPart extends AbstractGraphicalEditPart {
	
	@Override protected void refreshVisuals() {
		CGPFigure figure = (CGPFigure) getFigure();
	    LocatedElement model = (LocatedElement) getModel();
	    CGPSpatialModelEditPart parent = (CGPSpatialModelEditPart) getParent();

	    figure.setLabel(model.getName());
	    Rectangle layout = new Rectangle(model.getX(), model.getY(), 25, 25);
	    parent.setLayoutConstraint(this, figure, layout);
	}

}
