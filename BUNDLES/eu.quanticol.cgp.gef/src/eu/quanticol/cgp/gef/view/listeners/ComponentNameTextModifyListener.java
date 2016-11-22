package eu.quanticol.cgp.gef.view.listeners;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import eu.quanticol.cgp.gef.SpatialModelPrototypesView;
import eu.quanticol.cgp.gef.editor.CGPGraphicalEditor;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.SpatialModel;

public class ComponentNameTextModifyListener implements ModifyListener {

	SpatialModelPrototypesView view;
	
	

	public ComponentNameTextModifyListener(SpatialModelPrototypesView spatialModelPrototypesView) {
		this.view = spatialModelPrototypesView;
	}

	@Override
	public void modifyText(ModifyEvent e) {
		view.refreshAddComponentButton();
	}

}
