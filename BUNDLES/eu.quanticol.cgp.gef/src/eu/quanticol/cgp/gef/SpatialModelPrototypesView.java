package eu.quanticol.cgp.gef;

import java.util.ArrayList;
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
import eu.quanticol.cgp.gef.editor.command.CGPConnectionPrototypeCreateCommand;
import eu.quanticol.cgp.gef.editor.command.CGPConnectionPrototypeDeleteCommand;
import eu.quanticol.cgp.gef.editor.command.CGPNodePrototypeCreateCommand;
import eu.quanticol.cgp.gef.editor.command.CGPNodePrototypeDeleteCommand;
import eu.quanticol.cgp.gef.view.listeners.AddNewComponentPrototypeListener;
import eu.quanticol.cgp.gef.view.listeners.AddNewConnectionPrototypeSelectionListener;
import eu.quanticol.cgp.gef.view.listeners.AddNewNodePrototypeSelectionListener;
import eu.quanticol.cgp.gef.view.listeners.ComponentNameTextModifyListener;
import eu.quanticol.cgp.gef.view.listeners.EditorContentChangedPartListener;
import eu.quanticol.cgp.gef.view.listeners.NewConnectionNameModifyListener;
import eu.quanticol.cgp.gef.view.listeners.NewNodeNameModifyListener;
import eu.quanticol.cgp.gef.view.listeners.RemoveComponentListener;
import eu.quanticol.cgp.gef.view.listeners.RemoveConnectionPrototypeSelectionListener;
import eu.quanticol.cgp.gef.view.listeners.RemoveNodePrototypeSelectionListener;
import eu.quanticol.cgp.model.CGPFactory;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.ConnectionPrototype;
import eu.quanticol.cgp.model.NodePrototype;
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
	 
	/* *** main controls *** */
	/* */
	private Composite mainContainer;
	private CTabFolder categoriesCTabFolder;
	private CTabItem componentsCTabItem;
	private CTabItem pathsCTabItem;
	/* *** *** *** */
	
    /* *** components tab elements *** */
	/* */
	//main components tab controls
	private ScrolledComposite componentsScrolledComposite;
	private Table componentsListTable;
	private Group componentTableGroup;
	private Composite componentsContainerComposite;
	/* */
	//adding a new component controls
	private Text newComponentDescription;
	private Text newComponentName;	
	private Button addNewComponent;
	private Group newComponentLabelledTextName;
	private Label newComponentNameLabel;
	private Group newComponentLabelledTextDescription;
	private Label newComponentDescriptionLabel;
	private Group newComponentLabelledMenuShapeGroup;
	private Label newComponentShapeLabel;
	private Combo newComponentShapeCombo;
	private Group newComponentLabelledMenuColourGroup;
	private Label newComponentColourLabel;
	private Combo newComponentColourCombo;
	private Button removeSelectedComponent;
	/* */
	//component behaviour controls
	private Group componentBehaviourGroup;
	private ExpandBar behaviourExpandBar;
	private ExpandItem statesExpandItem;
	private Composite statesComposite;
	private Table statesListTable;
	private TableColumn statesNameColumn;
	private Button makeInit;
	private Button removeState;
	private Group labelledNewStateName;
	private Label newStateNameLabel;
	private Text newStateNameLabelText;
	private Button addState;
	private ExpandItem otherBehaviourExpandItem;
	private Composite otherBehaviourComposite;
	private Group statesAllowedConnectionsGroup;
	/* *** *** *** */
	
	/* *** paths tab controls *** */
	/* */
	//main paths tab controls
	private ScrolledComposite pathsScrolledComposite;
	private Composite pathsContainerComposite;
	private Group nodesTableGroup;
	private Table nodesListTable;
	private Button removeNodePrototype;
	private Group connectionsTableGroup;
	private Table connectionsListTable;
	private Button addNewNode;
	private Button removeConnectionPrototype;
	private Group labelledNewNodeName;
	private Label newNodeNameLabel;
	private Text newNodeName;
	private Group labelledNewNodeDescription;
	private Label newNodeDescriptionLabel;
	private Text newNodeDescription;	
	private Button addNewConnection;
	private Group labelledNewConnectionName;
	private Label newConnectionNameLabel;
	private Text newConnectionName;
	private Group labelledNewConnectionDescription;
	private Label newConnectionDescriptionLabel;
	private Text newConnectionDescription;
	/* *** *** *** */






	
	public SpatialModelPrototypesView() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		final IWorkbenchPage page = window.getActivePage();
		this.partListener = new EditorContentChangedPartListener(this);
		page.addPartListener(partListener);
		
	}

	public void setEditor(CGPGraphicalEditor editor) {
		this.editor = editor;
		refresh();
	}

	@Override
	public void createPartControl(Composite parent) {
		Device display = Display.getCurrent();
		Color white = display.getSystemColor(SWT.COLOR_WHITE);
		Color red = display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);

		parent.setLayout(new FillLayout());
		setupContainer(parent);
		setupTabFolder();
		
		pathsScrolledComposite = createScrolledComposite(categoriesCTabFolder, pathsCTabItem, white);
		pathsContainerComposite = new Composite(pathsScrolledComposite, SWT.NONE);
		pathsContainerComposite.setLayout(new FillLayout());
		pathsScrolledComposite.setContent(pathsContainerComposite);
		pathsContainerComposite.setBackground(white);
	
		nodesTableGroup = new Group(pathsContainerComposite, SWT.NONE);
		nodesTableGroup.setBackground(white);
		nodesTableGroup.setText("Nodes");
		nodesTableGroup.setBackground(white);
		nodesTableGroup.setLayout(new GridLayout());
		
		setupNodesTable();
		
		removeNodePrototype = new Button(nodesTableGroup, SWT.NONE);
		removeNodePrototype.setText("Remove");
		removeNodePrototype.addSelectionListener(new RemoveNodePrototypeSelectionListener(this));

	    labelledNewNodeName = new Group(nodesTableGroup, SWT.NONE);
	    labelledNewNodeName.setLayout(new FillLayout());
	    newNodeNameLabel = new Label(labelledNewNodeName, SWT.NONE);
		newNodeNameLabel.setText("Name");
		newNodeName  = new Text(labelledNewNodeName, SWT.NONE);
		newNodeName.addModifyListener(new NewNodeNameModifyListener(this));
		
		labelledNewNodeDescription = new Group(nodesTableGroup, SWT.NONE);
		labelledNewNodeDescription.setLayout(new FillLayout());
		newNodeDescriptionLabel = new Label(labelledNewNodeDescription, SWT.NONE);
		newNodeDescriptionLabel.setText("Description");
		newNodeDescription = new Text(labelledNewNodeDescription, SWT.NONE);
		newNodeDescription.addModifyListener(new NewNodeNameModifyListener(this));
		
		
		addNewNode = new Button(nodesTableGroup, SWT.NONE);
		addNewNode.setText("Add");
		addNewNode.addSelectionListener(new AddNewNodePrototypeSelectionListener(this));
		
		connectionsTableGroup = new Group(pathsContainerComposite, SWT.NONE);
		connectionsTableGroup.setBackground(white);
		connectionsTableGroup.setText("Connections");
		connectionsTableGroup.setBackground(white);
		connectionsTableGroup.setLayout(new GridLayout());
		setupConnectionsTable();
		
		removeConnectionPrototype = new Button(connectionsTableGroup, SWT.NONE);
		removeConnectionPrototype.setText("Remove");
		removeConnectionPrototype.addSelectionListener(new RemoveConnectionPrototypeSelectionListener(this));

	    labelledNewConnectionName = new Group(connectionsTableGroup, SWT.NONE);
	    labelledNewConnectionName.setLayout(new FillLayout());
	    newConnectionNameLabel = new Label(labelledNewConnectionName, SWT.NONE);
		newConnectionNameLabel.setText("Name");
		newConnectionName  = new Text(labelledNewConnectionName, SWT.NONE);
		newConnectionName.addModifyListener(new NewConnectionNameModifyListener(this));
		
		labelledNewConnectionDescription = new Group(connectionsTableGroup, SWT.NONE);
		labelledNewConnectionDescription.setLayout(new FillLayout());
		newConnectionDescriptionLabel = new Label(labelledNewConnectionDescription, SWT.NONE);
		newConnectionDescriptionLabel.setText("Description");
		newConnectionDescription = new Text(labelledNewConnectionDescription, SWT.NONE);
		newConnectionDescription.addModifyListener(new NewConnectionNameModifyListener(this));
		
		
		addNewConnection = new Button(connectionsTableGroup, SWT.NONE);
		addNewConnection.setText("Add");
		addNewConnection.addSelectionListener(new AddNewConnectionPrototypeSelectionListener(this));
		
		
		componentsScrolledComposite = createScrolledComposite(categoriesCTabFolder, componentsCTabItem, white);
		categoriesCTabFolder.setSelection(componentsCTabItem);
		componentsContainerComposite = new Composite(componentsScrolledComposite, SWT.NONE);
		componentsContainerComposite.setLayout(new FillLayout());
		componentsScrolledComposite.setContent(componentsContainerComposite);

		componentsContainerComposite.setBackground(white);
		componentTableGroup = new Group(componentsContainerComposite, SWT.NONE);

		componentTableGroup.setBackground(white);
		componentTableGroup.setText("Components");
		componentTableGroup.setBackground(white);
		componentTableGroup.setLayout(new GridLayout());
		componentBehaviourGroup = new Group(componentsContainerComposite, SWT.NONE);
		componentBehaviourGroup.setLayout(new FillLayout());
		//componentBehaviourGroup.setSize(200, 500);
		componentBehaviourGroup.setText("<Component_name> behaviour");
		componentBehaviourGroup.setBackground(white);

		behaviourExpandBar = new ExpandBar(componentBehaviourGroup, SWT.V_SCROLL);
		statesExpandItem = new ExpandItem(behaviourExpandBar, SWT.NONE, 0);
		statesExpandItem.setExpanded(true);
		statesExpandItem.setHeight(200);
		statesExpandItem.setText("States");

		statesComposite = new Composite(behaviourExpandBar, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		statesComposite.setLayout(gridLayout);

		statesListTable = new Table(statesComposite, SWT.BORDER);
		statesNameColumn = new TableColumn(statesListTable, SWT.CENTER);

		statesNameColumn.setText("Name");

		//TableItem state1 = new TableItem(statesListTable, SWT.NONE);
		//state1.setText(new String[] { "S" });

		makeInit = new Button(statesComposite, SWT.NONE);
		makeInit.setText("Init");
		makeInit.setSize(200, 50);
		removeState = new Button(statesComposite, SWT.NONE);
		removeState.setText("Remove");
		removeState.setSize(200, 50);

		labelledNewStateName = new Group(statesComposite, SWT.NONE);
		labelledNewStateName.setLayout(new FillLayout());
		newStateNameLabel = new Label(labelledNewStateName, SWT.NONE);
		newStateNameLabel.setText("Name");
		newStateNameLabel.setSize(100, SWT.DEFAULT);
		newStateNameLabelText = new Text(labelledNewStateName, SWT.NONE);

		addState = new Button(statesComposite, SWT.NONE);
		addState.setText("Add");
		addState.setSize(200, 50);

		statesExpandItem.setControl(statesComposite);
		

		statesAllowedConnectionsGroup = new Group(statesComposite, SWT.NONE);
		Button checkboxPath1 = new Button(statesAllowedConnectionsGroup, SWT.CHECK);
		checkboxPath1.setText("Slow lane");
		Button checkboxPath2 = new Button(statesAllowedConnectionsGroup, SWT.CHECK);
		checkboxPath2.setText("Fast lane");

		otherBehaviourExpandItem = new ExpandItem(behaviourExpandBar, SWT.NONE, 1);
		otherBehaviourExpandItem.setText("Custom behaviour");
		otherBehaviourComposite = new Composite(behaviourExpandBar, SWT.NONE);
		otherBehaviourExpandItem.setControl(otherBehaviourComposite);
		// item0.setImage(image);

		setupComponentsTable();
		

		removeSelectedComponent = new Button(componentTableGroup, SWT.NONE);
		removeSelectedComponent.setText("Remove selected");
		removeSelectedComponent.addSelectionListener(new RemoveComponentListener(this));

		addNewComponent = new Button(componentTableGroup, SWT.NONE);
		addNewComponent.setText("Add new");

		newComponentLabelledTextName = new Group(componentTableGroup, SWT.NONE);
		newComponentLabelledTextName.setLayout(new FillLayout());
		newComponentNameLabel = new Label(newComponentLabelledTextName, SWT.NONE);
		newComponentNameLabel.setText("Name");
		newComponentNameLabel.setSize(100, SWT.DEFAULT);
		newComponentName = new Text(newComponentLabelledTextName, SWT.NONE);
		ComponentNameTextModifyListener modifyListenerForRefreshingAddNewComponentButton =new ComponentNameTextModifyListener(this);
		newComponentName.addModifyListener(modifyListenerForRefreshingAddNewComponentButton);

		newComponentLabelledTextDescription = new Group(componentTableGroup, SWT.NONE);
		newComponentLabelledTextDescription.setLayout(new FillLayout());
		newComponentDescriptionLabel = new Label(newComponentLabelledTextDescription, SWT.NONE);
		newComponentDescriptionLabel.setText("Description");
		newComponentDescriptionLabel.setSize(100, SWT.DEFAULT);
		newComponentDescription = new Text(newComponentLabelledTextDescription, SWT.NONE);
		newComponentDescription.addModifyListener(modifyListenerForRefreshingAddNewComponentButton);
		
		
		newComponentLabelledMenuShapeGroup = new Group(componentTableGroup, SWT.NONE);
		newComponentLabelledMenuShapeGroup.setLayout(new FillLayout());
		newComponentShapeLabel = new Label(newComponentLabelledMenuShapeGroup, SWT.NONE);
		newComponentShapeLabel.setText("Shape");
		newComponentShapeLabel.setSize(100, SWT.DEFAULT);
		newComponentShapeCombo = new Combo(newComponentLabelledMenuShapeGroup, SWT.NONE);
		newComponentShapeCombo.add("rectangle");
		newComponentShapeCombo.add("circle");
		newComponentShapeCombo.add("triangle");
		newComponentShapeCombo.select(0);

		newComponentLabelledMenuColourGroup = new Group(componentTableGroup, SWT.NONE);
		newComponentLabelledMenuColourGroup.setLayout(new FillLayout());
		newComponentColourLabel = new Label(newComponentLabelledMenuColourGroup, SWT.NONE);
		newComponentColourLabel.setText("Colour");
		newComponentColourLabel.setSize(100, SWT.DEFAULT);
		newComponentColourCombo = new Combo(newComponentLabelledMenuColourGroup, SWT.NONE);
		newComponentColourCombo.add("red");
		newComponentColourCombo.add("green");
		newComponentColourCombo.add("blue");
		newComponentColourCombo.select(0);

//		String name = newComponentName.getText();
//		String description = newComponentDescription.getText();
//
//		String shape = newComponentShapeCombo.getItem(newComponentShapeCombo.getSelectionIndex());
//		String colour = newComponentColourCombo.getItem(newComponentColourCombo.getSelectionIndex());
		addNewComponent.addSelectionListener(new AddNewComponentPrototypeListener(this));

		
	}

	private void setupComponentsTable() {
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
	
	private void setupNodesTable() {
		nodesListTable = new Table(nodesTableGroup, SWT.BORDER);
		// t.setSize(200,200);
		TableColumn nameColumn = new TableColumn(nodesListTable, SWT.CENTER);
		TableColumn descriptionColumn = new TableColumn(nodesListTable, SWT.CENTER);

		nameColumn.setText("Name");
		descriptionColumn.setText("Description");

		nameColumn.setWidth(70);
		descriptionColumn.setWidth(70);
		nodesListTable.setHeaderVisible(true);
	}
	
	private void setupConnectionsTable() {
		connectionsListTable = new Table(connectionsTableGroup, SWT.BORDER);

		TableColumn nameColumn = new TableColumn(connectionsListTable, SWT.CENTER);
		TableColumn descriptionColumn = new TableColumn(connectionsListTable, SWT.CENTER);

		nameColumn.setText("Name");
		descriptionColumn.setText("Description");

		nameColumn.setWidth(70);
		descriptionColumn.setWidth(70);
		connectionsListTable.setHeaderVisible(true);
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
		categoriesCTabFolder = new CTabFolder(mainContainer, SWT.TOP);
		categoriesCTabFolder.setLayout(new FillLayout());
		componentsCTabItem = new CTabItem(categoriesCTabFolder, SWT.NONE, 0);
		componentsCTabItem.setText("Components");
		pathsCTabItem = new CTabItem(categoriesCTabFolder, SWT.NONE, 1);
		pathsCTabItem.setText("Paths");
	}

	private void setupContainer(Composite parent) {
		mainContainer = new Composite(parent, SWT.NONE);

		Device display = Display.getCurrent();
		Color white = display.getSystemColor(SWT.COLOR_WHITE);
		mainContainer.setBackground(white);
		mainContainer.setLayout(new FillLayout());

	}

	public void setModel(SpatialModel model) {
		this.selectedModel = model;
		refreshElements();
	}

	private void refreshElements() {
		// this.label.setText("Model selected!");

	}

	public void removeNodePrototype(){
		CGPNodePrototypeDeleteCommand command = new CGPNodePrototypeDeleteCommand();
		List<NodePrototype> toBeDeleted = new ArrayList<>();
		List<String> selectedComponentNames = new ArrayList<>();
		int[] selectedIndices = this.nodesListTable.getSelectionIndices();

		for (int id : selectedIndices) {
			String selected = this.nodesListTable.getItem(id).getText();
			selectedComponentNames.add(selected);
		}

		for (NodePrototype np : this.getEditor().getModel().getNodePrototypes()) {
			for (String selectedName : selectedComponentNames) {
				if (np.getName() != null && np.getName().equals(selectedName)) {
					toBeDeleted.add(np);
				}
				else if(selectedName.contains("" + np.hashCode())){
					toBeDeleted.add(np);
				}

			}
		}
		if (toBeDeleted.isEmpty()) {
			return;
		} else {
			for (NodePrototype np : toBeDeleted) {
				command.setNodePrototype(np);
				editor.deleteNodePrototype(command);
			}
		}
	}
	
	public void createComponentPrototype() {

		CGPComponentPrototypeCreateCommand command = new CGPComponentPrototypeCreateCommand();
		command.setComponentPrototype(CGPFactory.eINSTANCE.createComponentPrototype());
		command.setData(newComponentName.getText(), newComponentDescription.getText());
		command.setParent(this.selectedModel);
		editor.createComponentPrototype(command);
	}

	public void removeComponentPrototype() {
		CGPComponentPrototypeDeleteCommand command = new CGPComponentPrototypeDeleteCommand();
		List<ComponentPrototype> toBeDeleted = new ArrayList<>();

		// int selectionCount = this.componentsListTable.getSelectionCount();
		List<String> selectedComponentNames = new ArrayList<>();
		int[] selectedIndices = this.componentsListTable.getSelectionIndices();

		for (int id : selectedIndices) {

			String selected = this.componentsListTable.getItem(id).getText();
			selectedComponentNames.add(selected);
		}

		for (ComponentPrototype cp : this.getEditor().getModel().getComponentPrototypes()) {
			for (String selectedName : selectedComponentNames) {
				if (cp.getName() != null && cp.getName().equals(selectedName)) {
					toBeDeleted.add(cp);
				}
				else if(selectedName.contains("" + cp.hashCode())){
					toBeDeleted.add(cp);
				}

			}
		}
		if (toBeDeleted.isEmpty()) {
			return;
		} else {
			for (ComponentPrototype cp : toBeDeleted) {
				command.setComponentPrototype(cp);
				editor.deleteComponentPrototype(command);
			}
		}

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
		refreshNodesTable();
		refreshConnectionsTable();
		refreshAddComponentButton();
		refreshAddNodeButton();
		refreshAddConnectionButton();
	}

	public boolean validateAddComponentContent(){
		boolean result = true;
		if(this.newComponentName == null || this.newComponentDescription == null
				|| this.newComponentName.getText().equals("") || this.newComponentDescription.getText().equals("")){		
			return false;
		}
			
		List<ComponentPrototype> existingComponents = this.selectedModel.getComponentPrototypes();
		
		for(ComponentPrototype cp : existingComponents){
			if(cp.getName() == null || cp.getName().equals("")){
				if(this.newComponentName.getText().contains( "" + cp.hashCode()))
				result = false;
					
			}
			else if(cp.getName().equals(this.newComponentName.getText())){
				result = false;
			}
		}
		
		
		return result;
	}
	
	public void refreshAddComponentButton(){
		if(addNewComponent == null){
			return;
		}
		
		if(validateAddComponentContent()){
			this.addNewComponent.setEnabled(true);
		}
		else{
			this.addNewComponent.setEnabled(false);
		}
	}
	
	private void refreshComponentTable() {
		if(componentsListTable == null){
			return;
		}
		SpatialModel sm = this.editor.getModel();
		List<ComponentPrototype> prototypes = sm.getComponentPrototypes();
		componentsListTable.removeAll();

		for (ComponentPrototype prototype : prototypes) {
			TableItem item = new TableItem(componentsListTable, SWT.NONE);
			String name = prototype.getName();
			if(prototype.getName() == null || prototype.getName().equals("")){
				name = "Unnamed#" + prototype.hashCode() + "";
			}
			item.setText(new String[] { name, prototype.getDescription() });
		}
		componentsListTable.redraw();

	}
	
	private void refreshNodesTable(){
		if(nodesListTable == null){
			return;
		}
		SpatialModel sm = this.editor.getModel();
		List<NodePrototype> prototypes = sm.getNodePrototypes();
		nodesListTable.removeAll();

		for (NodePrototype prototype : prototypes) {
			TableItem item = new TableItem(nodesListTable, SWT.NONE);
			String name = prototype.getName();
			if(prototype.getName() == null || prototype.getName().equals("")){
				name = "Unnamed#" + prototype.hashCode() + "";
			}
			item.setText(new String[] { name, prototype.getDescription() });
			
		}
		nodesListTable.redraw();
	}
	
	private void refreshConnectionsTable(){
		if(connectionsListTable == null){
			return;
		}
		SpatialModel sm = this.editor.getModel();
		List<ConnectionPrototype> prototypes = sm.getConnectionPrototypes();
		connectionsListTable.removeAll();

		for (ConnectionPrototype prototype : prototypes) {
			TableItem item = new TableItem(connectionsListTable, SWT.NONE);
			String name = prototype.getName();
			if(prototype.getName() == null || prototype.getName().equals("")){
				name = "Unnamed#" + prototype.hashCode() + "";
			}
			item.setText(new String[] { name, prototype.getDescription() });
		}
		connectionsListTable.redraw();
	}

	public void refreshAddNodeButton() {
		if(addNewNode == null){
			return;
		}
		
		if(validateAddNodeContent()){
			this.addNewNode.setEnabled(true);
		}
		else{
			this.addNewNode.setEnabled(false);
		}
		
	}

	private boolean validateAddNodeContent() {
		boolean result = true;
		if(this.newNodeName == null || this.newNodeDescription == null
				|| this.newNodeName.getText().equals("") || this.newNodeDescription.getText().equals("")){		
			return false;
		}
			
		List<NodePrototype> existingNodes = this.selectedModel.getNodePrototypes();
		
		for(NodePrototype np : existingNodes){
			if(np.getName() == null || np.getName().equals("")){
				if(this.newNodeName.getText().contains( "" + np.hashCode()))
				result = false;
					
			}
			else if(np.getName().equals(this.newNodeName.getText())){
				result = false;
			}
		}
		
		return result;
	}

	public void createNodePrototype() {
		CGPNodePrototypeCreateCommand command = new CGPNodePrototypeCreateCommand();
		command.setNodePrototype(CGPFactory.eINSTANCE.createNodePrototype());
		command.setData(newNodeName.getText(), newNodeDescription.getText());
		command.setParent(this.selectedModel);
		editor.createNodePrototype(command);
		
	}

	public void createConnectionPrototype() {
		CGPConnectionPrototypeCreateCommand command = new CGPConnectionPrototypeCreateCommand();
		command.setConnectionPrototype(CGPFactory.eINSTANCE.createConnectionPrototype());
		command.setData(newConnectionName.getText(), newConnectionDescription.getText());
		command.setParent(this.selectedModel);
		editor.createConnectionPrototype(command);
		
	}

	public void refreshAddConnectionButton() {
		if(addNewConnection == null){
			return;
		}
		
		if(validateAddConnectionContent()){
			this.addNewConnection.setEnabled(true);
		}
		else{
			this.addNewConnection.setEnabled(false);
		}
		
	}

	private boolean validateAddConnectionContent() {
		boolean result = true;
		if(this.newConnectionName == null || this.newConnectionDescription == null
				|| this.newConnectionName.getText().equals("") || this.newConnectionDescription.getText().equals("")){		
			return false;
		}
			
		List<ConnectionPrototype> existingConnectionPrototypes = this.selectedModel.getConnectionPrototypes();
		
		for(ConnectionPrototype cp : existingConnectionPrototypes){
			if(cp.getName() == null || cp.getName().equals("")){
				if(this.newConnectionName.getText().contains( "" + cp.hashCode()))
				result = false;
					
			}
			else if(cp.getName().equals(this.newConnectionName.getText())){
				result = false;
			}
		}
		
		return result;
	}

	public void removeConnectionPrototype() {
		CGPConnectionPrototypeDeleteCommand command = new CGPConnectionPrototypeDeleteCommand();
		List<ConnectionPrototype> toBeDeleted = new ArrayList<>();
		List<String> selectedConnectionNames = new ArrayList<>();
		int[] selectedIndices = this.connectionsListTable.getSelectionIndices();

		for (int id : selectedIndices) {
			String selected = this.connectionsListTable.getItem(id).getText();
			selectedConnectionNames.add(selected);
		}

		for (ConnectionPrototype np : this.getEditor().getModel().getConnectionPrototypes()) {
			for (String selectedName : selectedConnectionNames) {
				if (np.getName() != null && np.getName().equals(selectedName)) {
					toBeDeleted.add(np);
				}
				else if(selectedName.contains("" + np.hashCode())){
					toBeDeleted.add(np);
				}

			}
		}
		if (toBeDeleted.isEmpty()) {
			return;
		} else {
			for (ConnectionPrototype np : toBeDeleted) {
				command.setConnectionPrototype(np);
				editor.deleteConnectionPrototype(command);
			}
		}
		
	}

}
