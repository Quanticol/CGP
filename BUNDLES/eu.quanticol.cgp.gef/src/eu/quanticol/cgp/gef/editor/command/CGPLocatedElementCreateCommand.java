/**
 * 
 */
package eu.quanticol.cgp.gef.editor.command;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import eu.quanticol.cgp.model.LocatedElement;
import eu.quanticol.cgp.model.SpatialModel;

/**
 * @author loreti
 *
 */
public class CGPLocatedElementCreateCommand extends Command {
	
	private LocatedElement newElement;
	private SpatialModel model;
	private Point location;
	 
	@Override public void execute() {
//		newElement.setName(defaultName);
	    if( location != null) {
	    	newElement.setX(location.x);
	    	newElement.setY(location.y);
	    }
		newElement.setModel(model);
	  }
	 
	  @Override public void undo() {
		  newElement.setModel(null);
	  }
	 
	  public void setLocation(Point location) {
		  this.location = location;
	  }
	 
	  public void setParent(SpatialModel opd) {
	    this.model = opd;
	  }
	 
	  public void setLocatedElement(LocatedElement newElement) {
	    this.newElement = newElement;
	  }
	  

}
