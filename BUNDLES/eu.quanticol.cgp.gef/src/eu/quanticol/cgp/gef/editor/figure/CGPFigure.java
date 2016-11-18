package eu.quanticol.cgp.gef.editor.figure;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Color;

public interface CGPFigure extends IFigure {

	public void setLabel( String label );
	public void setColour(Color colour);
	public ConnectionAnchor getConnectionAnchor();
	
}
