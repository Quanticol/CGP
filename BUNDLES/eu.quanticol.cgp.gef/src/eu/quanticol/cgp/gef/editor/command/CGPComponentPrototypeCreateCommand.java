/**
 * 
 */
package eu.quanticol.cgp.gef.editor.command;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.LocatedElement;
import eu.quanticol.cgp.model.SpatialModel;

/**
 * @author loreti
 *
 */
public class CGPComponentPrototypeCreateCommand extends Command {
	
	private ComponentPrototype newElement;
	private SpatialModel model;
	private String name;
	private String description;
	 
	@Override public void execute() {
//		newElement.setName(defaultName);
    	newElement.setName(name);
    	newElement.setDescription(description);
		newElement.setModel(model);
	  }
	 
	  @Override public void undo() {
		  newElement.setModel(null);
	  }
	 
	  public void setData(String name , String description) {
		  this.name = name;
		  this.description = description;
	  }
	 
	  public void setParent(SpatialModel opd) {
	    this.model = opd;
	  }
	 
	  public void setComponentPrototype(ComponentPrototype newElement) {
	    this.newElement = newElement;
	  }
	  

}
