package eu.quanticol.cgp.gef;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import eu.quanticol.cgp.gef.editor.CGPGraphicalEditor;
import eu.quanticol.cgp.gef.editor.command.CGPComponentPrototypeCreateCommand;
import eu.quanticol.cgp.gef.editor.command.CGPComponentPrototypeDeleteCommand;
import eu.quanticol.cgp.gef.view.listeners.AddNewComponentPrototypeListener;
import eu.quanticol.cgp.gef.view.listeners.ComponentNameTextModifyListener;
import eu.quanticol.cgp.gef.view.listeners.EditorContentChangedPartListener;
import eu.quanticol.cgp.gef.view.listeners.RemoveComponentListener;
import eu.quanticol.cgp.model.CGPFactory;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.SpatialModel;

/**
 * @author natalia
 *
 */
public class SpatialModelPrototypesView extends ViewPart {


	private IPartListener2 partListener;
	
	/**
	 * The EMF model associated with the active editor.
	 */
	private SpatialModel selectedModel;

	private CGPGraphicalEditor editor;
	private Composite container;
	private CTabFolder categoriesCTabFolder;
	private CTabItem componentsCTabItem;
	private CTabItem pathsCTabItem;
	private ScrolledComposite componentsScrolledComposite;
	private ScrolledComposite pathsScrolledComposite;
	private Text newComponentDescription;
	private Text newComponentName;

	private Table componentsListTable;

	public SpatialModelPrototypesView() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		final IWorkbenchPage page = window.getActivePage();
		this.partListener = new EditorContentChangedPartListener(this);
		page.addPartListener(partListener);
	}

	public void setEditor(CGPGraphicalEditor editor) {
		this.editor = editor;
	}

	@Override
	public void createPartControl(Composite parent) {
		Device display = Display.getCurrent();
		Color white = display.getSystemColor(SWT.COLOR_WHITE);
		Color red = display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);

		parent.setLayout(new FillLayout());
		setupContainer(parent);
		setupTabFolder();

		
		
		componentsScrolledComposite = createScrolledComposite(categoriesCTabFolder, componentsCTabItem, white);
		pathsScrolledComposite = createScrolledComposite(categoriesCTabFolder, pathsCTabItem, white);
		categoriesCTabFolder.setSelection(componentsCTabItem);
		Composite componentsContainerComposite = new Composite(componentsScrolledComposite, SWT.NONE);
		componentsContainerComposite.setLayout(new FillLayout());
		componentsScrolledComposite.setContent(componentsContainerComposite);
		
		
		//
		componentsContainerComposite.setBackground(white);
		Group componentTableGroup = new Group(componentsContainerComposite, SWT.NONE);
		// componentTableGroup.setSize(200,SWT.DEFAULT);
		componentTableGroup.setBackground(white);
		componentTableGroup.setText("Components");
		componentTableGroup.setBackground(white);
		componentTableGroup.setLayout(new GridLayout());
		Group componentBehaviourGroup = new Group(componentsContainerComposite, SWT.NONE);
		componentBehaviourGroup.setLayout(new FillLayout());
		componentBehaviourGroup.setSize(200, 500);
		componentBehaviourGroup.setText("<Component_name> behaviour");
		componentBehaviourGroup.setBackground(white);
		

		ExpandBar behaviourExpandBar = new ExpandBar(componentBehaviourGroup, SWT.V_SCROLL);
		ExpandItem statesExpandItem = new ExpandItem(behaviourExpandBar, SWT.NONE, 0);
		statesExpandItem.setExpanded(true);
		statesExpandItem.setHeight(200);
		statesExpandItem.setText("States");
		// item0.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		Composite statesComposite = new Composite(behaviourExpandBar, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		statesComposite.setLayout(gridLayout);

		Table statesListTable = new Table(statesComposite, SWT.BORDER);
		// t.setSize(200,200);
		TableColumn statesNameColumn = new TableColumn(statesListTable, SWT.CENTER);

		statesNameColumn.setText("Name");

		TableItem state1 = new TableItem(statesListTable, SWT.NONE);
		state1.setText(new String[] {"S"});

		Button makeInit = new Button(statesComposite, SWT.NONE);
		makeInit.setText("Init");
		makeInit.setSize(200, 50);
		Button removeState = new Button(statesComposite, SWT.NONE);
		removeState.setText("Remove");
		removeState.setSize(200, 50);
		
		
		Group labelledNewStateName = new Group(statesComposite, SWT.NONE);
		labelledNewStateName.setLayout(new FillLayout());
		Label newStateNameLabel = new Label(labelledNewStateName, SWT.NONE);
		newStateNameLabel.setText("Name");
		newStateNameLabel.setSize(100, SWT.DEFAULT);
		Text newStateNameLabelText = new Text(labelledNewStateName, SWT.NONE);

		Button addState = new Button(statesComposite, SWT.NONE);
		addState.setText("Add");
		addState.setSize(200, 50);

		statesExpandItem.setControl(statesComposite);
		// item0.setImage(image);

		Group statesAllowedPathsGroup = new Group(statesComposite, SWT.NONE);
		Button checkboxPath1 = new Button(statesAllowedPathsGroup, SWT.CHECK);
		checkboxPath1.setText("Slow lane");
		Button checkboxPath2 = new Button(statesAllowedPathsGroup, SWT.CHECK);
		checkboxPath2.setText("Fast lane");
		
		ExpandItem otherBehaviourExpandItem = new ExpandItem(behaviourExpandBar, SWT.NONE, 1);
		otherBehaviourExpandItem.setText("Custom behaviour");
		// item0.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		Composite otherBehaviourComposite = new Composite(behaviourExpandBar, SWT.NONE);
		otherBehaviourExpandItem.setControl(otherBehaviourComposite);
		// item0.setImage(image);

		setupComponentsTable(componentTableGroup);

		

		TableItem item1 = new TableItem(componentsListTable, SWT.NONE);
		item1.setText(new String[] { "Bus", "A bus prototype" });

		Button removeSelected = new Button(componentTableGroup, SWT.NONE);
		removeSelected.setText("Remove selected");
		removeSelected.addSelectionListener(new RemoveComponentListener(this));

		Button addNew = new Button(componentTableGroup, SWT.NONE);
		addNew.setText("Add new");
		
		Group labelledTextName = new Group(componentTableGroup, SWT.NONE);
		labelledTextName.setLayout(new FillLayout());
		Label newComponentNameLabel = new Label(labelledTextName, SWT.NONE);
		newComponentNameLabel.setText("Name");
		newComponentNameLabel.setSize(100, SWT.DEFAULT);
		newComponentName = new Text(labelledTextName, SWT.NONE);
	//	newComponentName.addModifyListener(new ComponentNameTextModifyListener(newComponentName, editor, addNew));

		Group labelledTextDescription = new Group(componentTableGroup, SWT.NONE);
		labelledTextDescription.setLayout(new FillLayout());
		Label newComponentDescriptionLabel = new Label(labelledTextDescription, SWT.NONE);
		newComponentDescriptionLabel.setText("Description");
		newComponentDescriptionLabel.setSize(100, SWT.DEFAULT);
		newComponentDescription = new Text(labelledTextDescription, SWT.NONE);

		Group labelledMenuShapeGroup = new Group(componentTableGroup, SWT.NONE);
		labelledMenuShapeGroup.setLayout(new FillLayout());
		Label newComponentShapeLabel = new Label(labelledMenuShapeGroup, SWT.NONE);
		newComponentShapeLabel.setText("Shape");
		newComponentShapeLabel.setSize(100, SWT.DEFAULT);
		Combo newComponentShapeCombo = new Combo(labelledMenuShapeGroup, SWT.NONE);
		newComponentShapeCombo.add("rectangle");
		newComponentShapeCombo.add("circle");
		newComponentShapeCombo.add("triangle");
		newComponentShapeCombo.select(0);
		
		
		Group labelledMenuColourGroup = new Group(componentTableGroup, SWT.NONE);
		labelledMenuColourGroup.setLayout(new FillLayout());
		Label newComponentColourLabel = new Label(labelledMenuColourGroup, SWT.NONE);
		newComponentColourLabel.setText("Colour");
		newComponentColourLabel.setSize(100, SWT.DEFAULT);
		Combo newComponentColourCombo = new Combo(labelledMenuColourGroup, SWT.NONE);
		newComponentColourCombo.add("red");
		newComponentColourCombo.add("green");
		newComponentColourCombo.add("blue");
		newComponentColourCombo.select(0);
		
		
		String name = newComponentName.getText();
		String description = newComponentDescription.getText();
		
		String shape = newComponentShapeCombo.getItem(newComponentShapeCombo.getSelectionIndex());
		String colour = newComponentColourCombo.getItem(newComponentColourCombo.getSelectionIndex());
		addNew.addSelectionListener(new AddNewComponentPrototypeListener(this));

		//

		Composite pathsContainerComposite = new Composite(componentsScrolledComposite, SWT.NONE);
		pathsContainerComposite.setLayout(new FillLayout());
		pathsScrolledComposite.setContent(pathsContainerComposite);

		// Label componentsLabel = new Label(componentsContainerComposite,
		// SWT.NONE);
		// componentsLabel.setSize(200,200);
		// componentsLabel.setText("Components tab composite");
		//
		//
		//
		//
		// // componentsCTabItem
		//
		// this.label = new Label(componentsContainerComposite, SWT.NONE);
		// this.label.setText("None...");
		// this.label.setSize(200,200);
		//
		// Button button = new Button(componentsContainerComposite, SWT.NONE);
		// button.setText("Add Component Prototype");
		// button.setSize(200,50);
		// button.addListener(SWT.Selection, new Listener() {
		//
		// private int counter = 0;
		//
		// @Override
		// public void handleEvent(Event event) {
		// createComponentPrototype("Component "+counter, "Prototype for
		// component "+(counter++));
		// }
		//
		// });
		// Label label = new Label( scrolledCompositeContainer, SWT.NONE );
		// label.setBackground( display.getSystemColor( SWT.COLOR_DARK_GREEN )
		// );
		// label.setSize( 400, 400 );

	}

	private void setupComponentsTable(Group componentTableGroup) {
		componentsListTable = new Table(componentTableGroup, SWT.BORDER);
		// t.setSize(200,200);
		TableColumn componentsTableColumnName = new TableColumn(componentsListTable, SWT.CENTER);
		TableColumn componentsTableColumnDescription = new TableColumn(componentsListTable, SWT.CENTER);

		componentsTableColumnName.setText("Name");
		componentsTableColumnDescription.setText("Description");

		componentsTableColumnName.setWidth(70);
		componentsTableColumnDescription.setWidth(70);
		componentsListTable.setHeaderVisible(true);
	}

	private ScrolledComposite createScrolledComposite(Composite parent, CTabItem cTabItem, Color white) {
		ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.V_SCROLL);
		scrolledComposite.setAlwaysShowScrollBars(true);
		scrolledComposite.setBackground(white);
		scrolledComposite.setLayout(new FillLayout());
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		cTabItem.setControl(scrolledComposite);

		scrolledComposite.addListener(SWT.Resize, event -> {
			int width = scrolledComposite.getParent().getClientArea().width;
			scrolledComposite.setMinSize(parent.computeSize(width, SWT.DEFAULT));
		});

		return scrolledComposite;
	}

	private void setupTabFolder() {
		categoriesCTabFolder = new CTabFolder(container, SWT.TOP);
		categoriesCTabFolder.setLayout(new FillLayout());
		componentsCTabItem = new CTabItem(categoriesCTabFolder, SWT.NONE, 0);
		componentsCTabItem.setText("Components");
		pathsCTabItem = new CTabItem(categoriesCTabFolder, SWT.NONE, 1);
		pathsCTabItem.setText("Paths");
	}

	private void setupContainer(Composite parent) {
		container = new Composite(parent, SWT.NONE);

		Device display = Display.getCurrent();
		Color white = display.getSystemColor(SWT.COLOR_WHITE);
		container.setBackground(white);
		container.setLayout(new FillLayout());

	}

	public void setModel(SpatialModel model) {
		this.selectedModel = model;
		refreshElements();
	}

	private void refreshElements() {
		//this.label.setText("Model selected!");

	}

	public void createComponentPrototype() {

		CGPComponentPrototypeCreateCommand command = new CGPComponentPrototypeCreateCommand();
		command.setComponentPrototype( CGPFactory.eINSTANCE.createComponentPrototype() );
		command.setData(newComponentName.getText(), newComponentDescription.getText());
		command.setParent(this.selectedModel);
		editor.createComponentPrototype(command);
	}

	public void removeComponentPrototype() {
		CGPComponentPrototypeDeleteCommand command = new CGPComponentPrototypeDeleteCommand();
		ComponentPrototype toBeDeleted = null;
		for(ComponentPrototype cp : this.getEditor().getModel().getComponentPrototypes()){
			if(cp.getName() == null){}
			else if (cp.getName().equals(this.componentsListTable.getItem(this.componentsListTable.getSelectionIndex()).getText())){
				toBeDeleted = cp;
			}
		}
		if(toBeDeleted == null){
			return;
		}
		command.setLocatedElement(toBeDeleted);
		editor.deleteComponentPrototype(command);
		
	}
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public CGPGraphicalEditor getEditor() {
		
		return this.editor;
	}

	public void refresh() {
		refreshComponentTable();		
	}

	private void refreshComponentTable() {
		SpatialModel sm = this.editor.getModel();
		List<ComponentPrototype> prototypes = sm.getComponentPrototypes();
		componentsListTable.removeAll();
		
		for(ComponentPrototype prototype : prototypes){
			TableItem item = new TableItem(componentsListTable, SWT.NONE);
			item.setText(new String[] { prototype.getName(), prototype.getDescription() });
		}
		componentsListTable.redraw();
		
		
	}

	

}
