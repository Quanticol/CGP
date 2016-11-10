package eu.quanticol.cgp.gef;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import eu.quanticol.cgp.gef.editor.CGPGraphicalEditor;
import eu.quanticol.cgp.model.CGPFactory;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.SpatialModel;

public class SpatialModelPrototypesView extends ViewPart {

	private Label label;
	private IPartListener2 listener;
	private SpatialModel selectedModel;
	private boolean flag;
	private CGPGraphicalEditor editor;

	public SpatialModelPrototypesView() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		final IWorkbenchPage page = window.getActivePage();
		this.listener = new IPartListener2() {
			
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
					setModel(editor.getModel());
					setEditor(editor);
				} 
			}
		};
		page.addPartListener(listener);
	}

	protected void setEditor(CGPGraphicalEditor editor) {
		this.editor = editor;
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		this.label = new Label(parent, SWT.NONE);
		this.label.setText("None...");
		Button button = new Button(parent, SWT.NONE);
		button.setText("Add Component Prototype");
		button.addListener(SWT.Selection, new Listener() {			
			
			private int counter = 0;
			
			@Override
			public void handleEvent(Event event) {
				createComponentPrototype("Component "+counter, "Prototype for component "+(counter++));
			}
			
		});
	}
	
	public void setModel( SpatialModel model ) {
		this.selectedModel = model;
		refreshElements();
	}
	
	private void refreshElements() {
		this.label.setText("Model selected!");
		
	}

	protected void createComponentPrototype( String name , String description ) {
//		ComponentPrototype cp = CGPFactory.eINSTANCE.createComponentPrototype();
//		cp.setName(name);
//		cp.setDescription(description);
//		cp.setModel(selectedModel);
		editor.createComponentPrototype(name, description);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
