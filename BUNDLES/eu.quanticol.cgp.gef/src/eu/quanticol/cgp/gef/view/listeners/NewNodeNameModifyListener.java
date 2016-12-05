package eu.quanticol.cgp.gef.view.listeners;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

import eu.quanticol.cgp.gef.SpatialModelPrototypesView;

public class NewNodeNameModifyListener implements ModifyListener {

	SpatialModelPrototypesView view;
	
	

	public NewNodeNameModifyListener(SpatialModelPrototypesView spatialModelPrototypesView) {
		this.view = spatialModelPrototypesView;
	}

	@Override
	public void modifyText(ModifyEvent e) {
		view.refreshAddNodeButton();
	}

}
