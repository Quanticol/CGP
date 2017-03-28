package eu.quanticol.cgp.gef.editor.command;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.gef.commands.Command;

import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.LocatedElement;
import eu.quanticol.cgp.model.NodeInstance;
import eu.quanticol.cgp.model.NodePrototype;
import eu.quanticol.cgp.model.SpatialModel;
import eu.quanticol.cgp.model.State;

public class CGPMakeStateInitCommand extends Command{
	private ComponentPrototype component;
	private State newInitState;
	private State oldInitState;


	@Override
	public void execute() {
		oldInitState = component.getInitState();
		component.setInitState(newInitState);
	}

	@Override
	public void undo() {
		component.setInitState(oldInitState);
	}

	public void setComponent(ComponentPrototype component) {
		this.component = component;
	}

	public void setNewInitState(State newInitState) {
		this.newInitState = newInitState;
	}


}
