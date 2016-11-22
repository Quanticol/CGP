package eu.quanticol.cgp.gef.editor.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;

import eu.quanticol.cgp.model.ComponentInstance;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.LocatedElement;
import eu.quanticol.cgp.model.SpatialModel;

public class CGPComponentPrototypeDeleteCommand extends Command{
	private ComponentPrototype componentPrototype;
	private SpatialModel spatialModel;
	List<ComponentInstance> toBeRemoved = new ArrayList<>();

	@Override
	public void execute() {
		/*
		* In EMF, a model instance that is disconnected from all of the model’s
		*  resource is not saved and therefore will get deleted
		*/
		
		
		for (LocatedElement le : spatialModel.getLocatedElements()){
			if(le instanceof ComponentInstance){
				if(((ComponentInstance)le).getPrototype().equals(componentPrototype)){
					toBeRemoved.add((ComponentInstance) le);
				}
			}
			
			
		}
		for(ComponentInstance ci : toBeRemoved){
			ci.setPrototype(null);
			ci.setModel(null);
		}
		componentPrototype.setModel(null);
	}

	@Override
	public void undo() {
		componentPrototype.setModel(spatialModel);
		for(ComponentInstance ci : toBeRemoved){
			ci.setModel(spatialModel);
			ci.setPrototype(componentPrototype);
		}
	}

	public void setLocatedElement(ComponentPrototype componentPrototype) {
		this.componentPrototype = componentPrototype;
		this.spatialModel = componentPrototype.getModel();
	}
}
