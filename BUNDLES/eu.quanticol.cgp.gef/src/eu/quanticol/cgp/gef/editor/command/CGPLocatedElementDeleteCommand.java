package eu.quanticol.cgp.gef.editor.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;

import eu.quanticol.cgp.model.ConnectionInstance;
import eu.quanticol.cgp.model.LocatedElement;
import eu.quanticol.cgp.model.NodeInstance;
import eu.quanticol.cgp.model.SpatialModel;

public class CGPLocatedElementDeleteCommand extends Command {
	protected LocatedElement locatedElement;
	protected SpatialModel spatialModel;
	protected List<ConnectionInstance> connections;
	protected Map<ConnectionInstance, NodeInstance> connectionSources;
	protected Map<ConnectionInstance, NodeInstance> connectionTargets;

	@Override
	public void execute() {
		/*
		* In EMF, a model instance that is disconnected from all of the model’s
		*  resource is not saved and therefore will get deleted
		*/

		locatedElement.setModel(null);
		
	}



	@Override
	public void undo() {

		locatedElement.setModel(spatialModel);
	}

	public void setLocatedElement(LocatedElement locatedElement) {
		this.locatedElement = locatedElement;
		this.spatialModel = locatedElement.getModel();
	}
}
