package eu.quanticol.cgp.gef.editor.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import eu.quanticol.cgp.gef.editor.figure.CGPComponentFigure;

public class CGPComponentInstanceEditPart extends CGPLocatedElementEditPart {

	@Override
	protected IFigure createFigure() {
		return new CGPComponentFigure();
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub

	}

}
