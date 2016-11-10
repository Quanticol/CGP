package eu.quanticol.cgp.gef.editor.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import eu.quanticol.cgp.gef.editor.figure.CGPNodeFigure;
import eu.quanticol.cgp.model.NodeInstance;

public class CGPNodeInstanceEditPart extends CGPLocatedElementEditPart {
	
	@Override
	protected IFigure createFigure() {
		return new CGPNodeFigure();
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}

}
