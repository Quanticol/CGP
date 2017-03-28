package eu.quanticol.cgp.gef.editor.command;

import org.eclipse.gef.commands.Command;

import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.ConnectionPrototype;
import eu.quanticol.cgp.model.NodePrototype;
import eu.quanticol.cgp.model.State;

public class CGPRemoveConnectionAllowanceCommand  extends Command {

	ComponentPrototype cp;
	ConnectionPrototype np;
	State s;
	
	
	@Override
	public void execute() {
		
		
		s.getAllowedConnections().remove(np);
	}
	
	@Override
	public void undo() {
s.getAllowedConnections().add(np);
	}

	public void setComponent(ComponentPrototype currentComponent) {
		this.cp = currentComponent;
		
	}

	public void setState(State selectedState) {
		this.s = selectedState;
		
	}

	public void setConnectionPrototype(ConnectionPrototype toBeRemoved) {
this.np = toBeRemoved;		
	}
	
}