package eu.quanticol.cgp.gef.editor.command;

import org.eclipse.gef.commands.Command;

import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.LocatedElement;
import eu.quanticol.cgp.model.SpatialModel;

public class CGPComponentPrototypeDeleteCommand extends Command{
	private ComponentPrototype componentPrototype;
	private SpatialModel spatialModel;

	@Override
	public void execute() {
		/*
		* In EMF, a model instance that is disconnected from all of the model’s
		*  resource is not saved and therefore will get deleted
		*/
		componentPrototype.setModel(null);
		
	}

	@Override
	public void undo() {
		componentPrototype.setModel(spatialModel);
	}

	public void setLocatedElement(ComponentPrototype componentPrototype) {
		this.componentPrototype = componentPrototype;
		this.spatialModel = componentPrototype.getModel();
	}
}
