package eu.quanticol.cgp.gef.editor.command;

import org.eclipse.gef.commands.Command;

import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.NodePrototype;
import eu.quanticol.cgp.model.State;

public class CGPAddNodeAllowanceCommand extends Command {

	ComponentPrototype cp;
	NodePrototype np;
	State s;
	
	
	@Override
	public void execute() {
		s.getAllowedNodes().add(np);
	}
	
	@Override
	public void undo() {
s.getAllowedNodes().remove(np);
	}

	public void setComponent(ComponentPrototype currentComponent) {
		this.cp = currentComponent;
		
	}

	public void setState(State selectedState) {
		// TODO Auto-generated method stub
		this.s = selectedState;
		
	}

	
	
	public void setNodePrototype(NodePrototype toBeAdded) {
this.np = toBeAdded;		
	}
	
}
