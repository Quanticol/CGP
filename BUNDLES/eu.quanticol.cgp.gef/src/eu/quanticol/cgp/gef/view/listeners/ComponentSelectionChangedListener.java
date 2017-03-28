package eu.quanticol.cgp.gef.view.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import eu.quanticol.cgp.gef.SpatialModelPrototypesView;

public class ComponentSelectionChangedListener implements SelectionListener {

	SpatialModelPrototypesView view;
	
	
	
	public ComponentSelectionChangedListener(SpatialModelPrototypesView spatialModelPrototypesView) {
		this.view = spatialModelPrototypesView;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		this.view.refreshBehaviourOfComponentPrototypeWhenSelected();
		view.refreshMovementAllowanceTab();

	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
