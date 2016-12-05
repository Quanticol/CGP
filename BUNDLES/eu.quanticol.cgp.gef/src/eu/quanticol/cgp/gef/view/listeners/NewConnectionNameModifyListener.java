package eu.quanticol.cgp.gef.view.listeners;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

import eu.quanticol.cgp.gef.SpatialModelPrototypesView;

public class NewConnectionNameModifyListener implements ModifyListener {

	SpatialModelPrototypesView view;
	
	
	public NewConnectionNameModifyListener(SpatialModelPrototypesView spatialModelPrototypesView) {
		this.view = spatialModelPrototypesView;
	}

	@Override
	public void modifyText(ModifyEvent e) {
		view.refreshAddConnectionButton();
	}


}
