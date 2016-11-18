package eu.quanticol.cgp.gef.editor.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import eu.quanticol.cgp.gef.LooksCatalog;
import eu.quanticol.cgp.gef.editor.figure.CGPCircleFigure;
import eu.quanticol.cgp.gef.editor.figure.CGPComponentFigure;
import eu.quanticol.cgp.gef.editor.figure.CGPFigure;

public class CGPComponentInstanceEditPart extends CGPLocatedElementEditPart {

	@Override
	protected IFigure createFigure() {
		CGPFigure figure = new CGPCircleFigure();
		figure.setColour(LooksCatalog.getColour("red"));
		return figure;
	}

}
