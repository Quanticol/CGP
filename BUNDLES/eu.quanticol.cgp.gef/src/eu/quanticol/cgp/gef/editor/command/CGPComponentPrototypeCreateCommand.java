/**
 * 
 */
package eu.quanticol.cgp.gef.editor.command;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.LocatedElement;
import eu.quanticol.cgp.model.SpatialModel;
import eu.quanticol.cgp.model.State;

/**
 * @author loreti
 *
 */
public class CGPComponentPrototypeCreateCommand extends Command {
	
	private ComponentPrototype newElement;
	private SpatialModel model;
	private String name;
	private String description;
	private State initState; 
	
	@Override public void execute() {
//		newElement.setName(defaultName);
    	newElement.setName(name);
    	newElement.setDescription(description);
		newElement.setModel(model);
		newElement.getStates().add(initState);
		newElement.setInitState(initState);
		initState.setComponent(newElement);
		
	  }
	 
	  @Override public void undo() {
		  initState.setComponent(null);
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

	public void setStubInitState(State state) {
		initState = state;
		initState.setName("S");
		initState.setDescription("");
	}
	  

}
