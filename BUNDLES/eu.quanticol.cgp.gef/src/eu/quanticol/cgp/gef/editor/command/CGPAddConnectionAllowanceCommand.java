package eu.quanticol.cgp.gef.editor.command;

import org.eclipse.gef.commands.Command;

import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.ConnectionPrototype;
import eu.quanticol.cgp.model.NodePrototype;
import eu.quanticol.cgp.model.State;

public class CGPAddConnectionAllowanceCommand extends Command {

	ComponentPrototype cp;
	ConnectionPrototype np;
	State s;
	
	
	@Override
	public void execute() {
		s.getAllowedConnections().add(np);
	}
	
	@Override
	public void undo() {
s.getAllowedConnections().remove(np);
	}

	public void setComponent(ComponentPrototype currentComponent) {
		this.cp = currentComponent;
		
	}

	public void setState(State selectedState) {
		// TODO Auto-generated method stub
		this.s = selectedState;
		
	}

	public void setConnectionPrototype(ConnectionPrototype toBeAdded) {
this.np = toBeAdded;		
	}
	
}
