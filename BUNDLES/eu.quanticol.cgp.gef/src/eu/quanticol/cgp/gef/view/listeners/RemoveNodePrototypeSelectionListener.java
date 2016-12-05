package eu.quanticol.cgp.gef.view.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import eu.quanticol.cgp.gef.SpatialModelPrototypesView;

public class RemoveNodePrototypeSelectionListener implements SelectionListener {
	SpatialModelPrototypesView view;
	
	public RemoveNodePrototypeSelectionListener(SpatialModelPrototypesView view) {
		super();
		this.view = view;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		view.removeNodePrototype();
		view.refreshAddNodeButton();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}
}
