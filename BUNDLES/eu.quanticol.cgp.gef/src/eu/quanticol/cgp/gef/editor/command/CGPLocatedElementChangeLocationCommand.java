package eu.quanticol.cgp.gef.editor.command;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;


import eu.quanticol.cgp.model.LocatedElement;

public class CGPLocatedElementChangeLocationCommand extends Command {

	private Rectangle oldLocation;
	private Rectangle newLocation;
	LocatedElement model;
	
	@Override public void execute() {
	    if(oldLocation == null) {
	    	
	    	oldLocation = new Rectangle(model.getLocation().x, model.getLocation().y, 0,0);
	    }
	    model.setLocation(new Point(newLocation.x, newLocation.y));
	  }
	 
	  @Override public void undo() {
	    model.setLocation(new Point(oldLocation.x, oldLocation.y));
	  }
	 
	  public void setModel(LocatedElement model) {
	    this.model = model;
	  }
	   
	  public void setNewLocation(Rectangle newLocation) {
	    this.newLocation = newLocation;
	  }
	
}
