package eu.quanticol.cgp.gef.view.listeners;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import eu.quanticol.cgp.gef.editor.CGPGraphicalEditor;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.SpatialModel;

public class ComponentNameTextModifyListener implements ModifyListener {

	SpatialModel sm;
	Text newComponentNameText;
	Button addNew;
	
	
	public ComponentNameTextModifyListener(Text newComponentName, CGPGraphicalEditor editor, Button addNew) {
		this.sm = editor.getModel();
		this.newComponentNameText = newComponentName;
		this.addNew = addNew;
	}

	@Override
	public void modifyText(ModifyEvent e) {
		if(newComponentNameText.getMessage() == null || newComponentNameText.getMessage().isEmpty()){
			addNew.setEnabled(false);
			return;
		}
		boolean enabled = true;
		for(ComponentPrototype cp : sm.getComponentPrototypes()){
			if(newComponentNameText.getMessage().equals(cp.getName())){
				enabled = false;
			}
		}
		addNew.setEnabled(enabled);
	}

}
