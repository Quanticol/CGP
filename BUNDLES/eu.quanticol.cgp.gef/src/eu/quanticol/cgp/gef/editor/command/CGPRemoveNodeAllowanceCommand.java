package eu.quanticol.cgp.gef.editor.command;

import org.eclipse.gef.commands.Command;

import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.NodePrototype;
import eu.quanticol.cgp.model.State;

public class CGPRemoveNodeAllowanceCommand extends Command {

	ComponentPrototype cp;
	NodePrototype np;
	State s;
	
	
	@Override
	public void execute() {
		
	 s.getAllowedNodes().remove(np);
	}
	
	@Override
	public void undo() {
s.getAllowedNodes().add(np);
	}

	public void setComponent(ComponentPrototype currentComponent) {
		this.cp = currentComponent;
		
	}

	public void setState(State selectedState) {
		this.s = selectedState;
		
	}

	public void setNodePrototype(NodePrototype toBeRemoved) {
this.np = toBeRemoved;		
	}
	
}