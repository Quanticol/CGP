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
	private LocatedElement locatedElement;
	private SpatialModel spatialModel;
	private List<ConnectionInstance> connections;
	private Map<ConnectionInstance, NodeInstance> connectionSources;
	private Map<ConnectionInstance, NodeInstance> connectionTargets;

	@Override
	public void execute() {
		/*
		* In EMF, a model instance that is disconnected from all of the model’s
		*  resource is not saved and therefore will get deleted
		*/
		if(locatedElement instanceof NodeInstance){
		detachConnections();
		}
		locatedElement.setModel(null);
		
	}

	private void detachConnections() {
		if(!(locatedElement instanceof NodeInstance)){
		return;
		}
		
		NodeInstance nodeInstance = (NodeInstance)locatedElement;
		
		this.connections = new ArrayList<>();
		this.connectionSources = new HashMap<>();
		this.connectionTargets = new HashMap<>();
		
		connections.addAll(nodeInstance.getIncomingConnections());
		connections.addAll(nodeInstance.getOutgoingConnections());
		
		for(ConnectionInstance ci : connections){
			connectionSources.put(ci, ci.getFrom());
			connectionTargets.put(ci, ci.getTo());
			ci.setFrom(null);
			ci.setTo(null);
			ci.setModel(null);
		}
		
	}
	
	private void reattachConnections(){
		for(ConnectionInstance ci : connections){
			ci.setFrom(connectionSources.get(ci));
			ci.setTo(connectionTargets.get(ci));
			ci.setModel(spatialModel);
		}
	}

	@Override
	public void undo() {
		if(locatedElement instanceof NodeInstance){
			reattachConnections();
		}
		locatedElement.setModel(spatialModel);
	}

	public void setLocatedElement(LocatedElement locatedElement) {
		this.locatedElement = locatedElement;
		this.spatialModel = locatedElement.getModel();
	}
}
