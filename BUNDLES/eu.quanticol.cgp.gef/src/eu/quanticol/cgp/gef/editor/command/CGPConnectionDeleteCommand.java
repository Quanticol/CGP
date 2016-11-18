package eu.quanticol.cgp.gef.editor.command;

import org.eclipse.gef.commands.Command;

import eu.quanticol.cgp.model.ConnectionInstance;
import eu.quanticol.cgp.model.NodeInstance;
import eu.quanticol.cgp.model.SpatialModel;

public class CGPConnectionDeleteCommand extends Command {
	private ConnectionInstance connectionInstance;
	private SpatialModel spatialModel;
	private NodeInstance sourceNodeInstance;
	private NodeInstance targetNodeInstance;
	
	@Override
	public boolean canExecute() {
		return (connectionInstance != null);
	}
	
	@Override
	public void execute() {
	spatialModel = connectionInstance.getModel();
	sourceNodeInstance = connectionInstance.getFrom();
	targetNodeInstance = connectionInstance.getTo();
	
	connectionInstance.setFrom(null);
	connectionInstance.setTo(null);
	connectionInstance.setModel(null);
	
	}
	
	@Override
	public void undo() {
		connectionInstance.setFrom(sourceNodeInstance);
		connectionInstance.setTo(targetNodeInstance);
		connectionInstance.setModel(spatialModel);
	}
	
	public void setConnectionInstance(ConnectionInstance connectionInstance){
		this.connectionInstance = connectionInstance;
	}
}
