package eu.quanticol.cgp.gef.editor.command;

import org.eclipse.gef.commands.Command;

import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.NodePrototype;
import eu.quanticol.cgp.model.SpatialModel;

public class CGPNodePrototypeCreateCommand extends Command {
	private NodePrototype newElement;
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
	 
	  public void setNodePrototype(NodePrototype newElement) {
	    this.newElement = newElement;
	  }
}
