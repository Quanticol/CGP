package eu.quanticol.cgp.gef.editor.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import eu.quanticol.cgp.gef.editor.command.CGPConnectionInstanceCreateCommand;
import eu.quanticol.cgp.model.ConnectionInstance;
import eu.quanticol.cgp.model.NodeInstance;
import eu.quanticol.cgp.model.SpatialModel;

public class NodeInstanceGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

	@Override
	protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
		CGPConnectionInstanceCreateCommand result = (CGPConnectionInstanceCreateCommand) request.getStartCommand();
		result.setTarget((NodeInstance) getHost().getModel());
		return result;
	}

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		CGPConnectionInstanceCreateCommand result = new CGPConnectionInstanceCreateCommand();
		result.setSource((NodeInstance) getHost().getModel());
		result.setConnectionInstance((ConnectionInstance) request.getNewObject());
		result.setSpatialModel(((NodeInstance) getHost().getModel()).getModel());
		request.setStartCommand(result);
		return result;

	}

	@Override
	protected Command getReconnectSourceCommand(ReconnectRequest arg0) {

		return null;
	}

	@Override
	protected Command getReconnectTargetCommand(ReconnectRequest arg0) {

		return null;
	}

}
