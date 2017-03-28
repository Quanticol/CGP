package eu.quanticol.cgp.gef.editor.figure;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ScalablePolygonShape;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class CGPStarFigure extends Figure implements CGPFigure {
	private Label label;
	private IFigure figure;
	private ConnectionAnchor connectionAnchor;
   
	public CGPStarFigure() {
		setLayoutManager(new XYLayout());
		figure = buildFigure();
		add(figure);
		label = new Label();
		add(label);
	}
 
	private IFigure buildFigure(){

    	ScalablePolygonShape myShape = new ScalablePolygonShape();

		
		myShape.addPoint(new Point(0,5));
		myShape.addPoint(new Point(4,6));
		myShape.addPoint(new Point(5,10));
		myShape.addPoint(new Point(6,6));
		
		myShape.addPoint(new Point(10,5));
		myShape.addPoint(new Point(6,4));
		myShape.addPoint(new Point(5,0));
		myShape.addPoint(new Point(4,4));
		myShape.addPoint(new Point(0,5));
    	return myShape;

	}	
	
	@Override 
	protected void paintFigure(Graphics graphics) {
		Rectangle r = getBounds().getCopy();
		setConstraint(figure, new Rectangle(0, 0, r.width, r.height));
		setConstraint(label, new Rectangle(0, 0, r.width, r.height));
	}
   
	@Override
	public void setLabel(String label) {
		this.label.setText("");
	}

	@Override
	public ConnectionAnchor getConnectionAnchor() {
		if (connectionAnchor == null) {
			connectionAnchor = new EllipseAnchor(this);
	    }
		return connectionAnchor;
	}

	@Override
	public void setColour(Color colour) {
		this.figure.setBackgroundColor(colour);
		this.figure.setForegroundColor(colour);
	}
}