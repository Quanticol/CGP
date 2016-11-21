package eu.quanticol.cgp.gef.view.listeners;

import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;

import eu.quanticol.cgp.gef.SpatialModelPrototypesView;
import eu.quanticol.cgp.gef.editor.CGPGraphicalEditor;

public class EditorContentChangedPartListener implements IPartListener2 {
	
	SpatialModelPrototypesView prototypesView;
	
	public EditorContentChangedPartListener(SpatialModelPrototypesView prototypesView) {
		super();
		this.prototypesView = prototypesView;
	}
	
	@Override
	public void partVisible(IWorkbenchPartReference partRef) {

	}

	@Override
	public void partOpened(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partInputChanged(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partHidden(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partDeactivated(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partClosed(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partBroughtToTop(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub

	}

	@Override
	public void partActivated(IWorkbenchPartReference partRef) {
		if (partRef.getPage().getActiveEditor() instanceof CGPGraphicalEditor) {
			CGPGraphicalEditor editor = (CGPGraphicalEditor) partRef.getPage().getActiveEditor();
			prototypesView.setModel(editor.getModel());
			prototypesView.setEditor(editor);
			editor.setView(prototypesView);
		}
	}

}
