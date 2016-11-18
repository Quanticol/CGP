/**
 * 
 */
package eu.quanticol.cgp.gef.editor.figure;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * @author loreti
 *
 */
public class CGPComponentFigure extends Figure implements CGPFigure {
	private Label label;
	private RectangleFigure rectangle;
	private ConnectionAnchor connectionAnchor;
   
	public CGPComponentFigure() {
		setLayoutManager(new XYLayout());
		rectangle = new RectangleFigure();
		add(rectangle);
		label = new Label();
		add(label);
	}
 
	@Override 
	protected void paintFigure(Graphics graphics) {
		Rectangle r = getBounds().getCopy();
		setConstraint(rectangle, new Rectangle(0, 0, r.width, r.height));
		setConstraint(label, new Rectangle(0, 0, r.width, r.height));
	}
   
	@Override
	public void setLabel(String label) {
		this.label.setText(label);
	}

	@Override
	public ConnectionAnchor getConnectionAnchor() {
		if (connectionAnchor == null) {
		      connectionAnchor = new ChopboxAnchor(this);
		}
		return connectionAnchor;
	}

	@Override
	public void setColour(Color colour) {
		this.rectangle.setForegroundColor(colour);
		this.rectangle.setBackgroundColor(colour);
	}
}
