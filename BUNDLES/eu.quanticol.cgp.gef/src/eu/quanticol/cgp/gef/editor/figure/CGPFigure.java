package eu.quanticol.cgp.gef.editor.figure;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;

public interface CGPFigure extends IFigure {

	public void setLabel( String label );
	public ConnectionAnchor getConnectionAnchor();
	
}
