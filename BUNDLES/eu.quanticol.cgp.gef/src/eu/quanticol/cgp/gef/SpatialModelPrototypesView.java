package eu.quanticol.cgp.gef;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
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

import eu.quanticol.carma.core.CarmaModelToCode;
import eu.quanticol.carma.core.carma.Model;
import eu.quanticol.cgp.gef.editor.CGPGraphicalEditor;
import eu.quanticol.cgp.gef.editor.command.CGPAddConnectionAllowanceCommand;
import eu.quanticol.cgp.gef.editor.command.CGPAddNodeAllowanceCommand;
import eu.quanticol.cgp.gef.editor.command.CGPComponentPrototypeCreateCommand;
import eu.quanticol.cgp.gef.editor.command.CGPComponentPrototypeDeleteCommand;
import eu.quanticol.cgp.gef.editor.command.CGPConnectionPrototypeCreateCommand;
import eu.quanticol.cgp.gef.editor.command.CGPConnectionPrototypeDeleteCommand;
import eu.quanticol.cgp.gef.editor.command.CGPMakeStateInitCommand;
import eu.quanticol.cgp.gef.editor.command.CGPNodePrototypeCreateCommand;
import eu.quanticol.cgp.gef.editor.command.CGPNodePrototypeDeleteCommand;
import eu.quanticol.cgp.gef.editor.command.CGPRemoveConnectionAllowanceCommand;
import eu.quanticol.cgp.gef.editor.command.CGPRemoveNodeAllowanceCommand;
import eu.quanticol.cgp.gef.editor.command.CGPRemoveStateCommand;
import eu.quanticol.cgp.gef.editor.command.CGPStateCreateCommand;
import eu.quanticol.cgp.gef.view.listeners.AddNewComponentPrototypeListener;
import eu.quanticol.cgp.gef.view.listeners.AddNewConnectionPrototypeSelectionListener;
import eu.quanticol.cgp.gef.view.listeners.AddNewNodePrototypeSelectionListener;
import eu.quanticol.cgp.gef.view.listeners.AddNewStateSelectionListener;
import eu.quanticol.cgp.gef.view.listeners.AddNodeAllowanceListener;
import eu.quanticol.cgp.gef.view.listeners.AddConnectionAllowanceListener;
import eu.quanticol.cgp.gef.view.listeners.ComponentNameTextModifyListener;
import eu.quanticol.cgp.gef.view.listeners.ComponentSelectionChangedListener;
import eu.quanticol.cgp.gef.view.listeners.EditorContentChangedPartListener;
import eu.quanticol.cgp.gef.view.listeners.MakeInitSelectionListener;
import eu.quanticol.cgp.gef.view.listeners.NewConnectionNameModifyListener;
import eu.quanticol.cgp.gef.view.listeners.NewNodeNameModifyListener;
import eu.quanticol.cgp.gef.view.listeners.NewStateNameModifyTextListener;
import eu.quanticol.cgp.gef.view.listeners.RemoveComponentListener;
import eu.quanticol.cgp.gef.view.listeners.RemoveConnectionPrototypeSelectionListener;
import eu.quanticol.cgp.gef.view.listeners.RemoveNodePrototypeSelectionListener;
import eu.quanticol.cgp.gef.view.listeners.RemoveSelectedConnectionAllowanceListener;
import eu.quanticol.cgp.gef.view.listeners.RemoveSelectedNodeAllowanceListener;
import eu.quanticol.cgp.gef.view.listeners.RemoveStateButtonSelectionListener;
import eu.quanticol.cgp.gef.view.listeners.SaveCarmaFileListener;
import eu.quanticol.cgp.gef.view.listeners.StatesTableSelectionChangedListener;
import eu.quanticol.cgp.model.CGPFactory;
import eu.quanticol.cgp.model.Colour;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.ConnectionPrototype;
import eu.quanticol.cgp.model.NodePrototype;
import eu.quanticol.cgp.model.Shape;
import eu.quanticol.cgp.model.SpatialModel;
import eu.quanticol.cgp.model.State;
import eu.quanticol.cgp.model.generator.CGPModelAdaptor;
import eu.quanticol.cgp.model.generator.CGPModelToCarmaModel;
import eu.quanticol.cgp.model.generator.CarmaCoreToCGPRevised;

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
	private CTabItem generationCTabItem;
	/* *** *** *** */
	
	 /* *** generation tab elements *** */
     /* */
	private ScrolledComposite generationScrolledComposite;
	private Composite generationContainerComposite;
	private Button generateCarmaCode;
	private Text savePath;
	
	 /* */
	
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
	private TableColumn statesDescColumn;
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
	/* */
	//path nodes controls	
	private Group nodesTableGroup;
	private Table nodesListTable;
	private Button removeNodePrototype;
	private Button addNewNode;
	private Group labelledNewNodeName;
	private Label newNodeNameLabel;
	private Text newNodeName;
	private Group labelledNewNodeDescription;
	private Label newNodeDescriptionLabel;
	private Text newNodeDescription;
	/* */
	//path connections controls	
	private Button addNewConnection;
	private Group labelledNewConnectionName;
	private Label newConnectionNameLabel;
	private Text newConnectionName;
	private Group labelledNewConnectionDescription;
	private Label newConnectionDescriptionLabel;
	private Text newConnectionDescription;
	private Group connectionsTableGroup;
	private Table connectionsListTable;
	private Button removeConnectionPrototype;
	/* *** *** *** */

	/* */
	//movement rules controls
	private Table statesListForAllowanceTable;
	private TableColumn statesListForAllowanceNameColumn;
	private TableColumn statesListForAllowanceDescColumn;
	
	
	private Group allowedMovementGroup;
	
	private Table nodesListForAllowanceTable;
	private TableColumn nodesListForAllowanceNameColumn;
	private TableColumn nodesListForAllowanceDescColumn;

	private Table connectionsListForAllowanceTable;
	private TableColumn connectionsListForAllowanceNameColumn;
	private TableColumn connectionsListForAllowanceDescColumn;
	
	private Combo addNodeAllowanceCombo;
	private Button addNodeAllowance;
	private Button removeSelectedNodeAllowance;
	
	private Combo addConnectionAllowanceCombo;
	private Button addConnectionAllowance;
	private Button removeSelectedConnectionAllowance;

	private Group allowedNodesGroup;

	private Group allowedConnectionsGroup;

	private Composite addComboCompositeNode;

	private Composite addComboCompositeConnection;

	/* *** *** *** */

	
	/* *** **/
	// colour selection for nodes
	Label nodeColourLabel;
	Button nodeColourButton;
	Color nodeColourSelected;
	Map<NodePrototype, Color> nodeColours = new HashMap<>();
	
	/* *** **/
	// colour selection for connections
	Label connectionColourLabel;
	Button connectionColourButton;
	Color connectionColourSelected;
	
	/* *** **/
	// colour selection for components
	Label componentColourLabel;
	Button componentColourButton;
	Color componentColourSelected;

	private Group saveGroup;
	
	
	
	private void setupComponentColourElements(){
		Device display = Display.getCurrent();
		Shell shell = new Shell();
		
		//------ nodes
		componentColourSelected = new Color(display, new RGB(0, 255, 0));

	    // Use a label full of spaces to show the color
		componentColourLabel = new Label(newComponentLabelledMenuColourGroup, SWT.NONE);
		componentColourLabel.setText("                              ");
		componentColourLabel.setBackground(componentColourSelected);

		componentColourButton = new Button(newComponentLabelledMenuColourGroup, SWT.PUSH);
		componentColourButton.setText("Color...");
		componentColourButton.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent event) {
	        // Create the color-change dialog
	        ColorDialog dlg = new ColorDialog(shell);

	        // Set the selected color in the dialog from
	        // user's selected color
	        dlg.setRGB(componentColourLabel.getBackground().getRGB());

	        // Change the title bar text
	        dlg.setText("Choose a Color");

	        // Open the dialog and retrieve the selected color
	        RGB rgb = dlg.open();
	        if (rgb != null) {
	          // Dispose the old color, create the
	          // new one, and set into the label
	        	componentColourSelected.dispose();
	        	componentColourSelected = new Color(newComponentLabelledMenuColourGroup.getDisplay(), rgb);
	        	componentColourLabel.setBackground(componentColourSelected);
	        }
	      }
	    });
	}
	
	private void setupNodeColourElements(){
		Device display = Display.getCurrent();
		Shell shell = new Shell();
		
		//------ nodes
		nodeColourSelected = new Color(display, new RGB(0, 255, 0));

	    // Use a label full of spaces to show the color
	    nodeColourLabel = new Label(nodesTableGroup, SWT.NONE);
	    nodeColourLabel.setText("                              ");
	    nodeColourLabel.setBackground(nodeColourSelected);

	    nodeColourButton = new Button(nodesTableGroup, SWT.PUSH);
	    nodeColourButton.setText("Color...");
	    nodeColourButton.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent event) {
	        // Create the color-change dialog
	        ColorDialog dlg = new ColorDialog(shell);

	        // Set the selected color in the dialog from
	        // user's selected color
	        dlg.setRGB(nodeColourLabel.getBackground().getRGB());

	        // Change the title bar text
	        dlg.setText("Choose a Color");

	        // Open the dialog and retrieve the selected color
	        RGB rgb = dlg.open();
	        if (rgb != null) {
	          // Dispose the old color, create the
	          // new one, and set into the label
	        	nodeColourSelected.dispose();
	        	nodeColourSelected = new Color(nodesTableGroup.getDisplay(), rgb);
	          nodeColourLabel.setBackground(nodeColourSelected);
	        }
	      }
	    });
	}
	private void setupConnectionsColourElements(){
		Device display = Display.getCurrent();
		Shell shell = new Shell();
	  //------ connections
	  		connectionColourSelected = new Color(display, new RGB(0, 0, 255));

	  	    // Use a label full of spaces to show the color
	  		connectionColourLabel = new Label(connectionsTableGroup, SWT.NONE);
	  		connectionColourLabel.setText("                              ");
	  		connectionColourLabel.setBackground(connectionColourSelected);

	  		connectionColourButton = new Button(connectionsTableGroup, SWT.PUSH);
	  		connectionColourButton.setText("Color...");
	  		connectionColourButton.addSelectionListener(new SelectionAdapter() {
	  	      public void widgetSelected(SelectionEvent event) {
	  	        // Create the color-change dialog
	  	        ColorDialog dlg = new ColorDialog(shell);

	  	        // Set the selected color in the dialog from
	  	        // user's selected color
	  	        dlg.setRGB(connectionColourLabel.getBackground().getRGB());

	  	        // Change the title bar text
	  	        dlg.setText("Choose a Color");

	  	        // Open the dialog and retrieve the selected color
	  	        RGB rgb = dlg.open();
	  	        if (rgb != null) {
	  	          // Dispose the old color, create the
	  	          // new one, and set into the label
	  	        	connectionColourSelected.dispose();
	  	        	connectionColourSelected = new Color(connectionsTableGroup.getDisplay(), rgb);
	  	        	connectionColourLabel.setBackground(connectionColourSelected);
	  	        }
	  	      }
	  	    });
	    
	}
	
	private void setupMovementRulesElements(){
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		otherBehaviourComposite.setLayout(layout);
		
		otherBehaviourComposite.setVisible(true);
		//otherBehaviourComposite.setSize(70, 70);
		
		statesListForAllowanceTable = new Table(otherBehaviourComposite, SWT.NONE);
		statesListForAllowanceTable.setSize(70, 100);
		
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_table.heightHint = 100;
		statesListForAllowanceTable.setLayoutData(gd_table);
		
		
		statesListForAllowanceNameColumn = new TableColumn(statesListForAllowanceTable, SWT.NONE);
		statesListForAllowanceNameColumn.setText("State");
        statesListForAllowanceDescColumn = new TableColumn(statesListForAllowanceTable, SWT.NONE);;
        statesListForAllowanceDescColumn.setText("Init?");
        
        statesListForAllowanceNameColumn.setWidth(40);
        statesListForAllowanceDescColumn.setWidth(30);
		statesListForAllowanceTable.setHeaderVisible(true);
		
		statesListForAllowanceTable.addSelectionListener(new StatesListForAllowanceTableSelectionChangedLIstenter(this));
        
//		
        allowedMovementGroup = new Group(otherBehaviourComposite, SWT.NONE);
        allowedNodesGroup = new Group(allowedMovementGroup, SWT.NONE);
        allowedNodesGroup.setLayout(new GridLayout());
        allowedNodesGroup.setText("Nodes");
        allowedConnectionsGroup = new Group(allowedMovementGroup, SWT.NONE);
        allowedConnectionsGroup.setLayout(new GridLayout());
        allowedConnectionsGroup.setText("Connections");
      
        GridLayout groupLayout = new GridLayout();
        groupLayout.numColumns =2;
        allowedMovementGroup.setLayout(groupLayout);
        allowedMovementGroup.setText("Movement of the < C > component");
        
        nodesListForAllowanceTable = new Table(allowedNodesGroup, SWT.NONE); ;
        
        nodesListForAllowanceTable.setHeaderVisible(false);
        nodesListForAllowanceTable.setSize(100, 100);
        
        nodesListForAllowanceNameColumn = new TableColumn(nodesListForAllowanceTable, SWT.NONE);;
        nodesListForAllowanceDescColumn = new TableColumn(nodesListForAllowanceTable, SWT.NONE);;
//
        nodesListForAllowanceNameColumn.setWidth(70);
        nodesListForAllowanceDescColumn.setWidth(5);
        
        connectionsListForAllowanceTable= new Table(allowedConnectionsGroup, SWT.NONE); ;
        connectionsListForAllowanceNameColumn = new TableColumn(connectionsListForAllowanceTable, SWT.NONE);
        connectionsListForAllowanceDescColumn= new TableColumn(connectionsListForAllowanceTable, SWT.NONE);
        
        connectionsListForAllowanceTable.setHeaderVisible(false);
        connectionsListForAllowanceTable.setSize(100,100);
        connectionsListForAllowanceNameColumn.setWidth(70);
        connectionsListForAllowanceDescColumn.setWidth(5);
//		
        
        addComboCompositeNode = new Composite(allowedNodesGroup, SWT.NONE);
        addComboCompositeNode.setLayout(new GridLayout(1, false));
		addNodeAllowanceCombo = new Combo(addComboCompositeNode, SWT.NONE);
		addNodeAllowanceCombo.setSize(100,25);
		
		GridData gdCombo1 = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_table.heightHint = 70;
		gd_table.minimumWidth= 70;
		
		
     	addNodeAllowance = new Button(addComboCompositeNode, SWT.NONE);
     	
     	
     	addNodeAllowance.setText("Add");
     	addNodeAllowance.setVisible(true);
     	
     	addNodeAllowance.addSelectionListener(new AddNodeAllowanceListener(this));
     	
        removeSelectedNodeAllowance = new Button(allowedNodesGroup, SWT.NONE);
        removeSelectedNodeAllowance.setText("Remove selected");
        removeSelectedNodeAllowance.setVisible(true);
        removeSelectedNodeAllowance.addSelectionListener(new RemoveSelectedNodeAllowanceListener(this));
//		
        addComboCompositeConnection = new Composite(allowedConnectionsGroup, SWT.NONE);
        addComboCompositeConnection.setLayout(new GridLayout(1, false));
        addConnectionAllowanceCombo =new Combo(addComboCompositeConnection, SWT.NONE);
        addConnectionAllowanceCombo.setSize(100, 25);
        addConnectionAllowance = new Button(addComboCompositeConnection, SWT.NONE);
        addConnectionAllowance.setText("Add");
        addConnectionAllowance.setVisible(true);
        addConnectionAllowance.addSelectionListener(new AddConnectionAllowanceListener(this));
        
        removeSelectedConnectionAllowance= new Button(allowedConnectionsGroup, SWT.NONE);
        removeSelectedConnectionAllowance.setText("Remove selected");
        removeSelectedConnectionAllowance.setVisible(true);
        removeSelectedConnectionAllowance.addSelectionListener(new RemoveSelectedConnectionAllowanceListener(this));
        
        addNodeAllowanceCombo.setLayoutData(gdCombo1);
		addConnectionAllowanceCombo.setLayoutData(gdCombo1);
	}

	

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
		
		generationScrolledComposite = createScrolledComposite(categoriesCTabFolder, generationCTabItem, white);
		generationContainerComposite = new Composite(generationScrolledComposite, SWT.NONE);
		generationContainerComposite.setLayout(new FillLayout());
		generationScrolledComposite.setContent(generationContainerComposite);
		generationContainerComposite.setBackground(white);
		
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
		componentsScrolledComposite.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));
		GridLayout gridL2 = new GridLayout();
		gridL2.numColumns = 1;
		componentsScrolledComposite.setLayout(gridL2);
		
		
		categoriesCTabFolder.setSelection(componentsCTabItem);
		componentsContainerComposite = new Composite(componentsScrolledComposite, SWT.NONE);
		componentsContainerComposite.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false));
		GridLayout gridL = new GridLayout();
		gridL.numColumns = 2; //this makes 2 colums
		gridL.makeColumnsEqualWidth = false;
		
		componentsContainerComposite.setLayout(gridL);
		
		componentsScrolledComposite.setContent(componentsContainerComposite);

		componentsContainerComposite.setBackground(white);
		
		
		componentTableGroup = new Group(componentsContainerComposite, SWT.NONE);
		Point size = componentsContainerComposite.getSize();
		GridData gdata1 = new GridData(SWT.FILL, SWT.FILL, true, true);
		gdata1.widthHint = (int)(size.x * 0.20);
        componentTableGroup.setLayoutData(gdata1);
		
		componentBehaviourGroup = new Group(componentsContainerComposite, SWT.NONE);

		GridData gdata2 = 	new GridData(SWT.FILL, SWT.FILL, true, false);
	//			gdata2.widthHint = (int)(size.x * 0.80);
		componentBehaviourGroup.setLayoutData(gdata2);
		componentTableGroup.setBackground(white);
		componentTableGroup.setText("Components");
		componentTableGroup.setBackground(white);
		componentTableGroup.setLayout(new GridLayout());
		
	//	Display display = Display.getDefault();
		

		
		
		
		
		
		
		componentBehaviourGroup.setLayout(new FillLayout());
		//componentBehaviourGroup.setSize(200, 500);
		componentBehaviourGroup.setText("<Component_name> behaviour");
		componentBehaviourGroup.setBackground(white);

		behaviourExpandBar = new ExpandBar(componentBehaviourGroup, SWT.V_SCROLL);
		statesExpandItem = new ExpandItem(behaviourExpandBar, SWT.NONE, 0);
		statesExpandItem.setExpanded(true);
		statesExpandItem.setHeight(200);
		statesExpandItem.setText("States");


		otherBehaviourExpandItem = new ExpandItem(behaviourExpandBar, SWT.NONE, 1);
		otherBehaviourExpandItem.setExpanded(false);
		otherBehaviourExpandItem.setHeight(300);
		otherBehaviourExpandItem.setText("Movement rules");
		
		otherBehaviourComposite = new Composite(behaviourExpandBar, SWT.NONE);
		otherBehaviourExpandItem.setControl(otherBehaviourComposite);
		// item0.setImage(image);

		setupMovementRulesElements();
		
		
		statesComposite = new Composite(behaviourExpandBar, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		statesComposite.setLayout(gridLayout);

	
		setupStatesTable();


		makeInit = new Button(statesComposite, SWT.NONE);
		makeInit.setText("Init");
		makeInit.setSize(200, 50);
		makeInit.addSelectionListener(new MakeInitSelectionListener(this));
		
		
		removeState = new Button(statesComposite, SWT.NONE);
		removeState.setText("Remove");
		removeState.setSize(200, 50);
        removeState.addSelectionListener(new RemoveStateButtonSelectionListener(this));
		
		
		
		labelledNewStateName = new Group(statesComposite, SWT.NONE);
		labelledNewStateName.setLayout(new FillLayout());
		newStateNameLabel = new Label(labelledNewStateName, SWT.NONE);
		newStateNameLabel.setText("Name");
		newStateNameLabel.setSize(100, SWT.DEFAULT);
		newStateNameLabelText = new Text(labelledNewStateName, SWT.NONE);

		newStateNameLabelText.addModifyListener(new NewStateNameModifyTextListener(this));
		
		addState = new Button(statesComposite, SWT.NONE);
		addState.setText("Add");
		addState.setSize(200, 50);
		addState.addSelectionListener(new AddNewStateSelectionListener(this));
		statesExpandItem.setControl(statesComposite);
		

//		statesAllowedConnectionsGroup = new Group(statesComposite, SWT.NONE);
//		Button checkboxPath1 = new Button(statesAllowedConnectionsGroup, SWT.CHECK);
//		checkboxPath1.setText("Slow lane");
//		Button checkboxPath2 = new Button(statesAllowedConnectionsGroup, SWT.CHECK);
//		checkboxPath2.setText("Fast lane");

		
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
		newComponentShapeCombo.add("circe");
		newComponentShapeCombo.add("rectangle");
		newComponentShapeCombo.add("star");
		newComponentShapeCombo.select(0);

		newComponentLabelledMenuColourGroup = new Group(componentTableGroup, SWT.NONE);
		newComponentLabelledMenuColourGroup.setLayout(new FillLayout());
		newComponentColourLabel = new Label(newComponentLabelledMenuColourGroup, SWT.NONE);
		newComponentColourLabel.setText("Colour");
		newComponentColourLabel.setSize(100, SWT.DEFAULT);
//		newComponentColourCombo = new Combo(newComponentLabelledMenuColourGroup, SWT.NONE);
//		newComponentColourCombo.add("red");
//		newComponentColourCombo.add("green");
//		newComponentColourCombo.add("blue");
//		newComponentColourCombo.select(0);

//		String name = newComponentName.getText();
//		String description = newComponentDescription.getText();
//
//		String shape = newComponentShapeCombo.getItem(newComponentShapeCombo.getSelectionIndex());
//		String colour = newComponentColourCombo.getItem(newComponentColourCombo.getSelectionIndex());
		addNewComponent.addSelectionListener(new AddNewComponentPrototypeListener(this));

		saveGroup = new Group(generationContainerComposite, SWT.NONE);
		saveGroup.setLayout(new GridLayout());
		savePath = new Text(saveGroup, SWT.NONE);
		savePath.setText("Browse for filename");
		savePath.setEditable(false);
		savePath.setSize(1000, 50);
		
		generateCarmaCode = new Button(saveGroup, SWT.NONE);
		
		generateCarmaCode.setText("Browse...");
		setupNodeColourElements();
		setupConnectionsColourElements();
		setupComponentColourElements();
		
		generateCarmaCode.addSelectionListener(new SaveCarmaFileListener(this));
		
	}

	private void setupStatesTable() {
		statesListTable = new Table(statesComposite, SWT.BORDER);
		// t.setSize(200,200);
		statesNameColumn = new TableColumn(statesListTable, SWT.CENTER);
		statesDescColumn = new TableColumn(statesListTable, SWT.CENTER);

		statesNameColumn.setText("Name");
		statesDescColumn.setText("Init?");

		statesNameColumn.setWidth(70);
		statesDescColumn.setWidth(70);
		statesListTable.setHeaderVisible(true);
		statesListTable.addSelectionListener(new StatesTableSelectionChangedListener(this));
		
		



		
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
		componentsListTable.addSelectionListener(new ComponentSelectionChangedListener(this));
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
	    GridLayout gL = new GridLayout();
	    gL.numColumns = 2;
	    		
		
		categoriesCTabFolder.setLayout(gL);
		
		
		componentsCTabItem = new CTabItem(categoriesCTabFolder, SWT.NONE, 0);
		
		componentsCTabItem.setText("Components");
		pathsCTabItem = new CTabItem(categoriesCTabFolder, SWT.NONE, 1);
		pathsCTabItem.setText("Paths");
		generationCTabItem =  new CTabItem(categoriesCTabFolder, SWT.NONE, 2);
		generationCTabItem.setText("Generation");
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
		ComponentPrototype newComponentPrototype = CGPFactory.eINSTANCE.createComponentPrototype();
		
		Colour colour = CGPFactory.eINSTANCE.createColour();
		colour.setRed(componentColourSelected.getRed());
		colour.setGreen(componentColourSelected.getGreen());
		colour.setBlue(componentColourSelected.getBlue());
		newComponentPrototype.setColour(colour);
		
		Shape shape = CGPFactory.eINSTANCE.createShape();
		String shapeName = newComponentShapeCombo.getItem(newComponentShapeCombo.getSelectionIndex());
		if(shapeName == null || shapeName.equals("")){
			shapeName = "circle";
		}
		shape.setName(shapeName);
		
		
		newComponentPrototype.setShape(shape);
		
		command.setComponentPrototype(newComponentPrototype);
		command.setStubInitState(CGPFactory.eINSTANCE.createState());
		command.setData(newComponentName.getText(), newComponentDescription.getText());
		command.setParent(this.selectedModel);
		editor.createComponentPrototype(command);
		
		
	}

	public void refreshBehaviourOfComponentPrototypeWhenSelected(){
		
		int[] selectedIndices = this.componentsListTable.getSelectionIndices();
		String selected= null;
		
		if(selectedIndices.length==0){
			addState.setEnabled(false);
			makeInit.setEnabled(false);
			removeState.setEnabled(false);
			newStateNameLabelText.setEnabled(false);
			statesListTable.setEnabled(false);
			componentBehaviourGroup.setText("Select a component....");
		}
		
		if(selectedIndices.length>0){
			
			if(validateAddState()){
			    addState.setEnabled(true);
			}
			
			makeInit.setEnabled(true);
			removeState.setEnabled(true);
			newStateNameLabelText.setEnabled(true);
			statesListTable.setEnabled(true);
		selected = this.componentsListTable.getItem(selectedIndices[0]).getText();
		
		}
		
		if(selected != null){
			componentBehaviourGroup.setText(selected + "'s behaviour");
		}
	
		//add States to the table
		//statesListTable
		
		refreshStatesTable(selected);
		handleStatesTableSelectionChanged();
		
		
		
	}

	
	
	public void refreshAddStateButton(){
	//	System.out.println("State name text modified");
		
		if(validateAddState()){
			addState.setEnabled(true);
		}
		else{
			addState.setEnabled(false);
		}
	}
	
	private boolean validateAddState() {
		if(newStateNameLabelText.getText() == null || newStateNameLabelText.getText().equals("")){
		return false;
		}
		boolean result = true;
		
		for(State existing : getSelectedComponent().getStates()){
			
			if(existing.getName().equals(newStateNameLabelText.getText())){
			result = false;
			}
		}
		
		return result;
	}

	private void refreshStatesTable(String selectedComponentPrototypeName) {
		if(statesListTable == null){
			return;
		}
		SpatialModel sm = this.editor.getModel();
		List<ComponentPrototype> prototypes = sm.getComponentPrototypes();
		
		ComponentPrototype selectedComponentPrototype = null;
		
		for(ComponentPrototype comPro : prototypes){
			if(comPro.getName().equals(selectedComponentPrototypeName)){
				selectedComponentPrototype = comPro;
			}
		}
		
		if(selectedComponentPrototype == null){
			return;
		}
			
		statesListTable.removeAll();

		for (State state : selectedComponentPrototype.getStates()) {
	//		System.out.println("Refreshing state table = Adding " + state.getName() + "to states table");
			TableItem item = new TableItem(statesListTable, SWT.NONE);
			String name = state.getName();
			if(state.getName() == null || state.getName().equals("")){
				name = "" + state.hashCode() + "";
			}
			if(name.equals(selectedComponentPrototype.getInitState().getName())){
				item.setText(new String[] { name, "INIT" });
			}
			else{
			    item.setText(new String[] { name, "" });
			}
		}
		statesListTable.redraw();
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
		refreshBehaviourOfComponentPrototypeWhenSelected();
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
		
		refreshBehaviourOfComponentPrototypeWhenSelected();
		handleStatesTableSelectionChanged();
		
	}

	public boolean validateAddComponentContent(){
		boolean result = true;
		if(this.newComponentName == null || this.newComponentDescription == null
				|| this.newComponentName.getText().equals("") || this.newComponentDescription.getText().equals("")){		
			return false;
		}
			
		if(this.selectedModel == null){
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
		NodePrototype newNodePrototype = CGPFactory.eINSTANCE.createNodePrototype();
		Colour colour = CGPFactory.eINSTANCE.createColour();
		colour.setRed(nodeColourSelected.getRed());
		colour.setGreen(nodeColourSelected.getGreen());
		colour.setBlue(nodeColourSelected.getBlue());
		newNodePrototype.setColour(colour);
		
		command.setNodePrototype(newNodePrototype);
		command.setData(newNodeName.getText(), newNodeDescription.getText());
		command.setParent(this.selectedModel);
		editor.createNodePrototype(command);
		
	}

	public void createConnectionPrototype() {
		
		
		CGPConnectionPrototypeCreateCommand command = new CGPConnectionPrototypeCreateCommand();
		ConnectionPrototype newConnectionPrototype = CGPFactory.eINSTANCE.createConnectionPrototype();
		
		
		
		Colour colour = CGPFactory.eINSTANCE.createColour();
		colour.setRed(connectionColourSelected.getRed());
		colour.setGreen(connectionColourSelected.getGreen());
		colour.setBlue(connectionColourSelected.getBlue());
		
		newConnectionPrototype.setColour(colour);
		
		command.setConnectionPrototype(newConnectionPrototype);
		
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

	public void saveCarmaFile() {
		SpatialModel currentModel = editor.getModel();
		//CGPModelAdaptor adaptor = new CarmaCoreToCGPRevised(currentModel);
		CGPModelAdaptor adaptor = new CGPModelToCarmaModel(currentModel);
		Model m = adaptor.getCarmaModel();
		CarmaModelToCode s = new CarmaModelToCode();
		CharSequence model = s.modelToCode(m);
		
		Display display = Display.getCurrent();
		Shell shell = new Shell (display);
		//shell.open ();
		
		
		FileDialog dialog = new FileDialog (shell, SWT.SAVE);
		String [] filterNames = new String [] {"Carma Files","Text Files", "All Files (*)"};
		String [] filterExtensions = new String [] {"*.carma","*.txt", "*"};
		String filterPath = "/";
		String platform = SWT.getPlatform();
		if (platform.equals("win32")) {
			filterNames = new String [] {"Carma Files","Text Files", "All Files (*.*)"};
			filterExtensions = new String [] {"*.carma","*.txt", "*.*"};
			filterPath = "c:\\";
		}
		dialog.setFilterNames (filterNames);
		dialog.setFilterExtensions (filterExtensions);
		dialog.setFilterPath (filterPath);
		dialog.setFileName ("generatedModel");
		String chosenPath = dialog.open ();
		shell.dispose();
		while (!shell.isDisposed () || chosenPath == null || chosenPath.equals("")) {
			if (!display.readAndDispatch ()) 
			{display.sleep ();}
			
		}
		display.wake();
		
		
		savePath.setText("Saved: " + chosenPath);
		savePath.getParent().redraw();
		savePath.redraw();
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			//String content = "This is the content to write into file\n";

			fw = new FileWriter(chosenPath);
			bw = new BufferedWriter(fw);
			bw.write(model.toString());

			//System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

		
		
		System.out.println(model);
		
	}

	public void addNewStateToSelectedComponent() {
		if(statesListTable == null){
			return;
		}
		
		if(newStateNameLabelText == null || newStateNameLabelText.equals("")){
			return;
		}
		
		int[] selectedIndices = this.componentsListTable.getSelectionIndices();
		String selected= null;
		
		if(selectedIndices.length==0){
                     return;
		}
		
		if(selectedIndices.length>0){

		selected = this.componentsListTable.getItem(selectedIndices[0]).getText();
		
		}
		
		if(selected != null){
			
		}
	
		SpatialModel sm = this.editor.getModel();
		List<ComponentPrototype> prototypes = sm.getComponentPrototypes();
		
		ComponentPrototype selectedComponentPrototype = null;
		
		for(ComponentPrototype comPro : prototypes){
			if(comPro.getName().equals(selected)){
				selectedComponentPrototype = comPro;
			}
		}
		
		if(selectedComponentPrototype == null){
			return;
		}
		
		createState(selectedComponentPrototype);
		
		refreshStatesTable(selectedComponentPrototype.getName());
		
		handleStatesTableSelectionChanged();
		refreshAddStateButton();
		
	}

	public void createState(ComponentPrototype parent) {

		
		
		CGPStateCreateCommand command = new CGPStateCreateCommand();
		command.setParent(parent);
		command.setData(newStateNameLabelText.getText(), "");
		State state = CGPFactory.eINSTANCE.createState();
		command.setState(state);
		editor.createStatePrototype(command);
	}
	
	public void handleStatesTableSelectionChanged() {
		
		if(statesListTable == null){
			return;
		}
		
		if(newStateNameLabelText == null || newStateNameLabelText.equals("")){
			return;
		}
		
		int[] selectedIndices = this.statesListTable.getSelectionIndices();
		
		
		if(selectedIndices.length==0){
			removeState.setEnabled(false);
			makeInit.setEnabled(false);
			return;
		}
		
		if(selectedIndices.length>0){
			
			makeInit.setEnabled(true);
		    if(getSelectedComponent() == null || getSelectedComponent().getInitState() == null || getSelectedState() ==null){
		    	return;
		    }
		  if(  getSelectedComponent().getInitState().getName().equals(getSelectedState().getName())){
			  removeState.setEnabled(false);
		  }
		  else{
			  removeState.setEnabled(true);
		  }
		
		}
		
		
	
		
	}

	
	public void makeSelectedStateInit() {
		ComponentPrototype selected = getSelectedComponent();
		
		if(selected == null){
			return;
		}
		
		State selectedState = getSelectedState();
		
		CGPMakeStateInitCommand command = new CGPMakeStateInitCommand();
		command.setComponent(selected);
		command.setNewInitState(selectedState);
		
		editor.makeStateInit(command);
		
		refreshStatesTable(selected.getName());
		handleStatesTableSelectionChanged();
	}

	private State getSelectedState() {
		ComponentPrototype cp =getSelectedComponent();
		

		
		if(cp == null){
			return null;
		}
		State selected = null;
		
		int[] selectedIndices = this.statesListTable.getSelectionIndices();
		
		if(selectedIndices.length==0 || selectedIndices.length>1){
            return null;
        }
		else{
			String selectedStateName = this.statesListTable.getItem(selectedIndices[0]).getText();
			for(State s : cp.getStates()){
				if(s.getName().equals(selectedStateName)){
					selected = s;
				}
			}
		    return selected;
		}
		
	}

	private ComponentPrototype getSelectedComponent() {
		if(statesListTable == null){
			return null;
		}
		
		if(newStateNameLabelText == null || newStateNameLabelText.equals("")){
			return null;
		}
		
		int[] selectedIndices = this.componentsListTable.getSelectionIndices();
		String selected= null;
		
		if(selectedIndices.length==0){
                     return null;
		}
		
		if(selectedIndices.length>0){

		selected = this.componentsListTable.getItem(selectedIndices[0]).getText();
		
		}
         ComponentPrototype selectedComponentPrototype = null;
		
		for(ComponentPrototype comPro : this.editor.getModel().getComponentPrototypes()){
			if(comPro.getName().equals(selected)){
				selectedComponentPrototype = comPro;
			}
		}
		
		return selectedComponentPrototype;

	}

	public void removeState() {
		
		ComponentPrototype selectedPrototype = getSelectedComponent();
		State selected = getSelectedState();
		
		CGPRemoveStateCommand command = new CGPRemoveStateCommand();
		command.setComponent(selectedPrototype);
		command.setState(selected);
		
		statesListTable.removeAll();
		statesListTable.redraw();
		
		editor.removeSelectedState(command);
		
		for (State state : selectedPrototype.getStates()) {
		//	System.out.println("Refreshing state table = Adding " + state.getName() + "to states table");
			TableItem item = new TableItem(statesListTable, SWT.NONE);
			String name = state.getName();
			if(state.getName() == null || state.getName().equals("")){
				name = "" + state.hashCode() + "";
			}
			if(name.equals(selectedPrototype.getInitState().getName())){
				item.setText(new String[] { name, "INIT" });
			}
			else{
			    item.setText(new String[] { name, "" });
			}
		}
		statesListTable.redraw();
		
		
		
		refreshAddStateButton();
		handleStatesTableSelectionChanged();
		
	}


	public void refreshMovementAllowanceTab() {
		ComponentPrototype currentComponent = getSelectedComponent();
		
		statesListForAllowanceTable.removeAll();

		if(currentComponent == null){
			return;
		}
		
		for (State state : currentComponent.getStates()) {
		//	System.out.println("Refreshing state table = Adding " + state.getName() + "to states table");
			TableItem item = new TableItem(statesListForAllowanceTable, SWT.NONE);
			String name = state.getName();
			if(state.getName() == null || state.getName().equals("")){
				name = "" + state.hashCode() + "";
			}
			if(name.equals(currentComponent.getInitState().getName())){
				item.setText(new String[] { name, "INIT" });
			}
			else{
			    item.setText(new String[] { name, "" });
			}
			
		}
		statesListForAllowanceTable.redraw();
		
		allowedMovementGroup.setText("Movement rules of component " + currentComponent.getName());
		
		populateCombosAllowance();
		
	}


	public void populateCombosAllowance() {
		ComponentPrototype currentComponent = getSelectedComponent();
		if(currentComponent == null){
			return;
		}
		State selectedState = null;
		
		
		if (statesListForAllowanceTable.getSelectionIndices().length ==0){
			return;
		}
		
		int selectedStateId = statesListForAllowanceTable.getSelectionIndices()[0];
		
		String selectedStateName = statesListForAllowanceTable.getItem(selectedStateId).getText();
		
		for(State s : currentComponent.getStates()){
			if(s.getName().equals(selectedStateName)){
				selectedState = s;
			}
		}
		
		if(selectedState == null){
			return;
		}
		
		
		allowedConnectionsGroup.setText("Connections (" + selectedState.getName() + ")");
		allowedNodesGroup.setText("Nodes (" + selectedState.getName() + ")");
		
	//	System.out.print("Preparing to populate combos");
		
		addNodeAllowanceCombo.removeAll();
		addConnectionAllowanceCombo.removeAll();
		
		for(NodePrototype np : this.editor.getModel().getNodePrototypes()){
			boolean alreadyAllowed = false;
			
			for(NodePrototype aNode : selectedState.getAllowedNodes()){
				if(aNode.getName().equals(np.getName())){
					alreadyAllowed = true;
				}
			}
			
		    if(!alreadyAllowed){
				addNodeAllowanceCombo.add(np.getName());
				
			//	System.out.print("Addinf combo value" + np.getName());
		    }
		}
		
		if(addNodeAllowanceCombo.getItemCount() > 0){
		addNodeAllowanceCombo.select(0);
		}
		
		for(ConnectionPrototype np : this.editor.getModel().getConnectionPrototypes()){
			boolean alreadyAllowed = false;
			
			for(ConnectionPrototype aConn : selectedState.getAllowedConnections()){
				if(aConn.getName().equals(np.getName())){
					alreadyAllowed = true;
				}
			}
			
		    if(!alreadyAllowed){
				addConnectionAllowanceCombo.add(np.getName());
				//System.out.print("Addinf combo value" + np.getName());
		    }
		}
		if(addConnectionAllowanceCombo.getItemCount() > 0){
			addConnectionAllowanceCombo.select(0);
		}
		
		populateAllowedNodesAndConnectionsTabs();
	}
	
	public void populateAllowedNodesAndConnectionsTabs(){
		ComponentPrototype currentComponent = getSelectedComponent();
		State selectedState = null;
		if(currentComponent == null){
			return;
		}
		
		if (statesListForAllowanceTable.getSelectionIndices().length ==0){
			return;
		}
		
		int selectedStateId = statesListForAllowanceTable.getSelectionIndices()[0];
		
		String selectedStateName = statesListForAllowanceTable.getItem(selectedStateId).getText();
		
		for(State s : currentComponent.getStates()){
			if(s.getName().equals(selectedStateName)){
				selectedState = s;
			}
		}
		
		if(selectedState == null){
			return;
		}
		
		connectionsListForAllowanceTable.removeAll();
		nodesListForAllowanceTable.removeAll();
		
		for(NodePrototype np : selectedState.getAllowedNodes()){
			//System.out.println("Refreshing allowed nodes table = Adding " + np.getName() + "to states table");
			TableItem item = new TableItem(nodesListForAllowanceTable, SWT.NONE);
			String name = np.getName();
			if(np.getName() == null || np.getName().equals("")){
				
			}

			else{
			    item.setText(new String[] { name, "" });
			}
		}
		nodesListForAllowanceTable.redraw();
		
		
		for(ConnectionPrototype cp : selectedState.getAllowedConnections()){
			//System.out.println("Refreshing state table = Adding " + state.getName() + "to states table");
			TableItem item = new TableItem(connectionsListForAllowanceTable, SWT.NONE);
			String name = cp.getName();
			if(cp.getName() == null || cp.getName().equals("")){
				
			}

			else{
			    item.setText(new String[] { name, "" });
			}
		}
		connectionsListForAllowanceTable.redraw();
	}



	public void addNodeAllowance() {
		ComponentPrototype currentComponent = getSelectedComponent();
		State selectedState = null;
		if(currentComponent == null){
			return;
		}
		
		if (statesListForAllowanceTable.getSelectionIndices().length ==0){
			return;
		}
		
		int selectedStateId = statesListForAllowanceTable.getSelectionIndices()[0];
		
		String selectedStateName = statesListForAllowanceTable.getItem(selectedStateId).getText();
		
		for(State s : currentComponent.getStates()){
			if(s.getName().equals(selectedStateName)){
				selectedState = s;
			}
		}
		
		if(selectedState == null){
			return;
		}
		String selectedNodeType = null;
		int selectionId = addNodeAllowanceCombo.getSelectionIndex();
		
		
		if(addNodeAllowanceCombo.getItemCount() > selectionId && selectionId >=0){
			selectedNodeType = addNodeAllowanceCombo.getItem(selectionId);
		}
		else{
			return;
		}
		
		
		if(selectedNodeType == null || selectedNodeType.equals("")){
			return;
		}
		NodePrototype toBeAdded = null;
		
		for(NodePrototype np : editor.getModel().getNodePrototypes()){
			if(np.getName().equals(selectedNodeType)){
				toBeAdded = np;
			}
		}
		 if(toBeAdded == null){
			 return;
		 }
		
		 CGPAddNodeAllowanceCommand command = new CGPAddNodeAllowanceCommand();
		 command.setComponent(currentComponent);
		 command.setState(selectedState);
		 command.setNodePrototype(toBeAdded);
		 
		 editor.addNodePrototypeAllowance(command);
		 
		 populateAllowedNodesAndConnectionsTabs();
		 populateCombosAllowance();
		 
//		 CGPRemoveStateCommand command = new CGPRemoveStateCommand();
//			command.setComponent(selectedPrototype);
//			command.setState(selected);
//			
//			statesListTable.removeAll();
//			statesListTable.redraw();
//			
//			editor.removeSelectedState(command);
//		 
		 
		 
	}
	

	public void addConnectionAllowance() {
		ComponentPrototype currentComponent = getSelectedComponent();
		State selectedState = null;
		if(currentComponent == null){
			return;
		}
		
		if (statesListForAllowanceTable.getSelectionIndices().length ==0){
			return;
		}
		
		int selectedStateId = statesListForAllowanceTable.getSelectionIndices()[0];
		
		String selectedStateName = statesListForAllowanceTable.getItem(selectedStateId).getText();
		
		for(State s : currentComponent.getStates()){
			if(s.getName().equals(selectedStateName)){
				selectedState = s;
			}
		}
		
		if(selectedState == null){
			return;
		}
		String selectedConnectionType = null;
		int selectionId = addConnectionAllowanceCombo.getSelectionIndex();
		
		
		if(addConnectionAllowanceCombo.getItemCount() > selectionId && selectionId >=0){
			selectedConnectionType = addConnectionAllowanceCombo.getItem(selectionId);
		}
		else{
			return;
		}
		
		
		if(selectedConnectionType == null || selectedConnectionType.equals("")){
			return;
		}
		ConnectionPrototype toBeAdded = null;
		
		for(ConnectionPrototype np : editor.getModel().getConnectionPrototypes()){
			if(np.getName().equals(selectedConnectionType)){
				toBeAdded = np;
			}
		}
		 if(toBeAdded == null){
			 return;
		 }
		
		 CGPAddConnectionAllowanceCommand command = new CGPAddConnectionAllowanceCommand();
		 command.setComponent(currentComponent);
		 command.setState(selectedState);
		 command.setConnectionPrototype(toBeAdded);
		 
		 editor.addConnectionPrototypeAllowance(command);
		 
		 populateAllowedNodesAndConnectionsTabs();
		 populateCombosAllowance();
		 
//		 CGPRemoveStateCommand command = new CGPRemoveStateCommand();
//			command.setComponent(selectedPrototype);
//			command.setState(selected);
//			
//			statesListTable.removeAll();
//			statesListTable.redraw();
//			
//			editor.removeSelectedState(command);
//		 
		 
		 
	}



	public void removeSelectedNodeAllowance() {
		
		//System.out.println("REmoving node allowance");		
		ComponentPrototype currentComponent = getSelectedComponent();
		State selectedState = null;
		if(currentComponent == null){
			return;
		}
	//	System.out.print("Still here 2");
		if (statesListForAllowanceTable.getSelectionIndices().length ==0){
			return;
		}
		
		int selectedStateId = statesListForAllowanceTable.getSelectionIndices()[0];
		
		String selectedStateName = statesListForAllowanceTable.getItem(selectedStateId).getText();
		
		for(State s : currentComponent.getStates()){
			if(s.getName().equals(selectedStateName)){
				selectedState = s;
			}
		}
	//	System.out.print("Still here 3");
		if(selectedState == null){
			return;
		}
		
		if(nodesListForAllowanceTable.getSelectionCount() < 1){
			return;
		}
		
		int selectedNodeAllowanceId = nodesListForAllowanceTable.getSelectionIndices()[0];
		
	
		
		String selectedNodeAllowanceName = nodesListForAllowanceTable.getItem(selectedNodeAllowanceId).getText();
		
		NodePrototype toBeRemoved =null;
		
		for(NodePrototype np : selectedState.getAllowedNodes()){
			if(np.getName().equals(selectedNodeAllowanceName)){
				toBeRemoved = np;
			}
		}
	//	System.out.print("Still here 4");
		if(toBeRemoved == null){
			return;
		}
		
	//	System.out.print("Still here 5");
		
		CGPRemoveNodeAllowanceCommand command = new CGPRemoveNodeAllowanceCommand();
		 command.setComponent(currentComponent);
		 command.setState(selectedState);
		 command.setNodePrototype(toBeRemoved);
		 
		 editor.removeNodePrototypeAllowance(command);
		 
		 populateAllowedNodesAndConnectionsTabs();
		 populateCombosAllowance();
		
		
	}
	
	
public void removeSelectedConnectionAllowance() {
		
		
		ComponentPrototype currentComponent = getSelectedComponent();
		State selectedState = null;
		if(currentComponent == null){
			return;
		}

		if (statesListForAllowanceTable.getSelectionIndices().length ==0){
			return;
		}
		
		int selectedStateId = statesListForAllowanceTable.getSelectionIndices()[0];
		
		String selectedStateName = statesListForAllowanceTable.getItem(selectedStateId).getText();
		
		for(State s : currentComponent.getStates()){
			if(s.getName().equals(selectedStateName)){
				selectedState = s;
			}
		}

		if(selectedState == null){
			return;
		}
		
		if(connectionsListForAllowanceTable.getSelectionCount() < 1){
			return;
		}
		
		int selectedConnectionAllowanceId = connectionsListForAllowanceTable.getSelectionIndices()[0];
		
	
		
		String selectedConnectionAllowanceName = connectionsListForAllowanceTable.getItem(selectedConnectionAllowanceId).getText();
		
		ConnectionPrototype toBeRemoved =null;
		
		for(ConnectionPrototype np : selectedState.getAllowedConnections()){
			if(np.getName().equals(selectedConnectionAllowanceName)){
				toBeRemoved = np;
			}
		}

		if(toBeRemoved == null){
			return;
		}
		

		
		CGPRemoveConnectionAllowanceCommand command = new CGPRemoveConnectionAllowanceCommand();
		 command.setComponent(currentComponent);
		 command.setState(selectedState);
		 command.setConnectionPrototype(toBeRemoved);
		 
		 editor.removeConnectionPrototypeAllowance(command);
		 
		 populateAllowedNodesAndConnectionsTabs();
		 populateCombosAllowance();
		
		
	}

public Map<NodePrototype, Color> getColourMap() {
	
	return this.nodeColours;
}
	
}
