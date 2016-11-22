package eu.quanticol.cgp.gef.view.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import eu.quanticol.cgp.gef.SpatialModelPrototypesView;

public class RemoveComponentListener implements SelectionListener {

	SpatialModelPrototypesView view;
	
	public RemoveComponentListener(SpatialModelPrototypesView view) {
		super();
		this.view = view;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		view.removeComponentPrototype();
		view.refreshAddComponentButton();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
