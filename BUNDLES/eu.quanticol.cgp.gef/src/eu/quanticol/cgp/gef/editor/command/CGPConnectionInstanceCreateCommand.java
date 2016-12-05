package eu.quanticol.cgp.gef.editor.command;

import org.eclipse.gef.commands.Command;

import eu.quanticol.cgp.model.ConnectionInstance;
import eu.quanticol.cgp.model.NodeInstance;
import eu.quanticol.cgp.model.SpatialModel;

public class CGPConnectionInstanceCreateCommand extends Command {

	private NodeInstance source;
	private NodeInstance target;
	private ConnectionInstance connectionInstance;
	private SpatialModel spatialModel;

	@Override
	public boolean canExecute() {
		return (source != null) && (target != null) && (connectionInstance != null) && (!source.equals(target));
	}
	
	@Override
	public void execute() {
		connectionInstance.setFrom(source);
		connectionInstance.setTo(target);
		connectionInstance.setModel(spatialModel);
	}
	
	@Override
	public void undo() {
		connectionInstance.getFrom().getOutgoingConnections().remove(connectionInstance);
		connectionInstance.getTo().getIncomingConnections().remove(connectionInstance);
		connectionInstance.setFrom(null);
		connectionInstance.setTo(null);
		connectionInstance.setModel(null);
	}

	public void setSource(NodeInstance source) {
		this.source = source;
	}

	public void setTarget(NodeInstance target) {
		this.target = target;
	}

	public void setConnectionInstance(ConnectionInstance connectionInstance) {
		this.connectionInstance = connectionInstance;
	}

	public void setSpatialModel(SpatialModel spatialModel) {
		this.spatialModel = spatialModel;
	}
	
	
	
}
