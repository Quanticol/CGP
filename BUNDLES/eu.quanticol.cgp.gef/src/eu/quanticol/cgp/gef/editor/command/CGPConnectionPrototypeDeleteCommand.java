package eu.quanticol.cgp.gef.editor.command;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.gef.commands.Command;

import eu.quanticol.cgp.model.ConnectionInstance;
import eu.quanticol.cgp.model.ConnectionPrototype;
import eu.quanticol.cgp.model.LocatedElement;
import eu.quanticol.cgp.model.NodeInstance;
import eu.quanticol.cgp.model.NodePrototype;
import eu.quanticol.cgp.model.SpatialModel;

public class CGPConnectionPrototypeDeleteCommand extends Command {
	private ConnectionPrototype connectionPrototype;
	private SpatialModel spatialModel;
	Map<ConnectionInstance, CGPConnectionInstanceDeleteCommand> deleteCommandPerInstance = new HashMap<>();
	@Override
	public void execute() {
		/*
		* In EMF, a model instance that is disconnected from all of the model’s
		*  resource is not saved and therefore will get deleted
		*/	
		
		for (ConnectionPrototype ci : spatialModel.getConnectionPrototypes()){
			if(ci instanceof ConnectionInstance){
				if(((ConnectionInstance)ci).getPrototype().equals(connectionPrototype)){
					CGPConnectionInstanceDeleteCommand command = new CGPConnectionInstanceDeleteCommand();
					command.setConnectionInstance((ConnectionInstance) ci);
					deleteCommandPerInstance.put((ConnectionInstance) ci, command);
					
				}
			}
			
			
		}
		for(ConnectionInstance ci : deleteCommandPerInstance.keySet()){
			deleteCommandPerInstance.get(ci).execute();
			
		}
		connectionPrototype.setModel(null);
	}

	@Override
	public void undo() {
		connectionPrototype.setModel(spatialModel);
		for(ConnectionInstance ci : deleteCommandPerInstance.keySet()){
			deleteCommandPerInstance.get(ci).undo();
			
		}
	}

	public void setConnectionPrototype(ConnectionPrototype connectionPrototype) {
		this.connectionPrototype = connectionPrototype;
		this.spatialModel = connectionPrototype.getModel();
	}
}
