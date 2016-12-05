package eu.quanticol.cgp.gef.editor.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.Command;

import eu.quanticol.cgp.model.ComponentInstance;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.LocatedElement;
import eu.quanticol.cgp.model.NodeInstance;
import eu.quanticol.cgp.model.NodePrototype;
import eu.quanticol.cgp.model.SpatialModel;

public class CGPNodePrototypeDeleteCommand extends Command{
	private NodePrototype nodePrototype;
	private SpatialModel spatialModel;
	//List<NodeInstance> toBeRemoved = new ArrayList<>();
	Map<NodeInstance, CGPNodeInstanceDeleteCommand> deleteCommandPerInstance = new HashMap<>();

	@Override
	public void execute() {
		/*
		* In EMF, a model instance that is disconnected from all of the model’s
		*  resource is not saved and therefore will get deleted
		*/	
		
		for (LocatedElement le : spatialModel.getLocatedElements()){
			if(le instanceof NodeInstance){
				if(((NodeInstance)le).getPrototype().equals(nodePrototype)){
					CGPNodeInstanceDeleteCommand command = new CGPNodeInstanceDeleteCommand();
					command.setLocatedElement((NodeInstance) le);
					deleteCommandPerInstance.put((NodeInstance) le, command);
					
				}
			}
			
			
		}
		for(NodeInstance ci : deleteCommandPerInstance.keySet()){
			deleteCommandPerInstance.get(ci).execute();
			
		}
		nodePrototype.setModel(null);
	}

	@Override
	public void undo() {
		nodePrototype.setModel(spatialModel);
		for(NodeInstance ci : deleteCommandPerInstance.keySet()){
			deleteCommandPerInstance.get(ci).undo();
			
		}
	}

	public void setNodePrototype(NodePrototype nodePrototype) {
		this.nodePrototype = nodePrototype;
		this.spatialModel = nodePrototype.getModel();
	}
}
