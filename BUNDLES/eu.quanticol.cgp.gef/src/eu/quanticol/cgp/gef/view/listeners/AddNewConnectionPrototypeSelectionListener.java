package eu.quanticol.cgp.gef.view.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import eu.quanticol.cgp.gef.SpatialModelPrototypesView;

public class AddNewConnectionPrototypeSelectionListener implements SelectionListener {
	SpatialModelPrototypesView view;
	public AddNewConnectionPrototypeSelectionListener(SpatialModelPrototypesView view) {
		super();
		this.view = view;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		view.createConnectionPrototype();
		view.refreshAddConnectionButton();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
