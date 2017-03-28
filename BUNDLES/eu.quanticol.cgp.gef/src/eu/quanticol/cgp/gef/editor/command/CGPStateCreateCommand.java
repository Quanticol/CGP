package eu.quanticol.cgp.gef.editor.command;

import org.eclipse.gef.commands.Command;

import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.State;

public class CGPStateCreateCommand  extends Command {
	private State newElement;
	private ComponentPrototype parent;
	//private SpatialModel model;
	private String name;
	private String description;
	 
	@Override public void execute() {
        System.out.println("Creating new state");
    	newElement.setName(name);
    	newElement.setDescription(description);
		newElement.setComponent(parent);
		parent.getStates().add(newElement);
	  }
	 
	  @Override public void undo() {
		  newElement.setComponent(null);
		  parent.getStates().remove(newElement);
	  }
	 
	  public void setData(String name , String description) {
		  this.name = name;
		  this.description = description;
	  }
	 
	  public void setParent(ComponentPrototype opd) {
	    this.parent = opd;
	  }
	 
	  public void setState(State newElement) {
	    this.newElement = newElement;
	  }
}
