package eu.quanticol.cgp.gef.editor.command;

import org.eclipse.gef.commands.Command;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.State;

public class CGPRemoveStateCommand extends Command {
	private ComponentPrototype component;
	private State removed;


	@Override
	public void execute() {
		removed.setComponent(null);
		component.getStates().remove(removed);
		
	}

	@Override
	public void undo() {
		component.getStates().add(removed);
	}

	public void setComponent(ComponentPrototype component) {
		this.component = component;
	}

	public void setState(State removed) {
		this.removed = removed;
	}

}
