package eu.quanticol.cgp.gef.view.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import eu.quanticol.cgp.gef.SpatialModelPrototypesView;

public class SaveCarmaFileListener implements SelectionListener {
	SpatialModelPrototypesView view;

	public SaveCarmaFileListener(SpatialModelPrototypesView spatialModelPrototypesView) {
		super();
		this.view = spatialModelPrototypesView;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		System.out.println("Saving Carma File");
		view.saveCarmaFile();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
