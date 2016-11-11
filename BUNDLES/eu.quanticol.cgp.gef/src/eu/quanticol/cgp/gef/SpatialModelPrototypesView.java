package eu.quanticol.cgp.gef;

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
import eu.quanticol.cgp.model.CGPFactory;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.SpatialModel;

public class SpatialModelPrototypesView extends ViewPart {

	private Label label;
	private IPartListener2 listener;
	private SpatialModel selectedModel;
	private boolean flag;
	private CGPGraphicalEditor editor;
	private Composite container;
	private CTabFolder categoriesCTabFolder;
	private CTabItem componentsCTabItem;
	private CTabItem pathsCTabItem;
	private ScrolledComposite componentsScrolledComposite;
	private ScrolledComposite pathsScrolledComposite;

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
		Device display = Display.getCurrent();
		Color white = display.getSystemColor(SWT.COLOR_WHITE);
		Color red = display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);

		parent.setLayout(new FillLayout());
		setupContainer(parent);
		setupTabFolder();

		Button generateButton = new Button(this.container, SWT.NONE);
		generateButton.setText("Generate Code");
		
		componentsScrolledComposite = createScrolledComposite(categoriesCTabFolder, componentsCTabItem, white);
		pathsScrolledComposite = createScrolledComposite(categoriesCTabFolder, pathsCTabItem, white);

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

		Table componentsListTable = new Table(componentTableGroup, SWT.BORDER);
		// t.setSize(200,200);
		TableColumn componentsTableColumnName = new TableColumn(componentsListTable, SWT.CENTER);
		TableColumn componentsTableColumnDescription = new TableColumn(componentsListTable, SWT.CENTER);

		componentsTableColumnName.setText("Name");
		componentsTableColumnDescription.setText("Description");

		componentsTableColumnName.setWidth(70);
		componentsTableColumnDescription.setWidth(70);

		componentsListTable.setHeaderVisible(true);

		TableItem item1 = new TableItem(componentsListTable, SWT.NONE);
		item1.setText(new String[] { "Bus", "A bus prototype" });

		Button removeSelected = new Button(componentTableGroup, SWT.NONE);
		removeSelected.setText("Remove selected");

		Group labelledTextName = new Group(componentTableGroup, SWT.NONE);
		labelledTextName.setLayout(new FillLayout());
		Label newComponentNameLabel = new Label(labelledTextName, SWT.NONE);
		newComponentNameLabel.setText("Name");
		newComponentNameLabel.setSize(100, SWT.DEFAULT);
		Text newComponentName = new Text(labelledTextName, SWT.NONE);

		Group labelledTextDescription = new Group(componentTableGroup, SWT.NONE);
		labelledTextDescription.setLayout(new FillLayout());
		Label newComponentDescriptionLabel = new Label(labelledTextDescription, SWT.NONE);
		newComponentDescriptionLabel.setText("Description");
		newComponentDescriptionLabel.setSize(100, SWT.DEFAULT);
		Text newComponentDescription = new Text(labelledTextDescription, SWT.NONE);

		Group labelledMenuShapeGroup = new Group(componentTableGroup, SWT.NONE);
		labelledMenuShapeGroup.setLayout(new FillLayout());
		Label newComponentShapeLabel = new Label(labelledMenuShapeGroup, SWT.NONE);
		newComponentShapeLabel.setText("Shape");
		newComponentShapeLabel.setSize(100, SWT.DEFAULT);
		Combo shapeCombo = new Combo(labelledMenuShapeGroup, SWT.NONE);

		Group labelledMenuColourGroup = new Group(componentTableGroup, SWT.NONE);
		labelledMenuColourGroup.setLayout(new FillLayout());
		Label newComponentColourLabel = new Label(labelledMenuColourGroup, SWT.NONE);
		newComponentColourLabel.setText("Colour");
		newComponentColourLabel.setSize(100, SWT.DEFAULT);
		Combo colourCombo = new Combo(labelledMenuColourGroup, SWT.NONE);

		Button addNew = new Button(componentTableGroup, SWT.NONE);
		addNew.setText("Add new");

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

	protected void createComponentPrototype(String name, String description) {
		// ComponentPrototype cp =
		// CGPFactory.eINSTANCE.createComponentPrototype();
		// cp.setName(name);
		// cp.setDescription(description);
		// cp.setModel(selectedModel);
		editor.createComponentPrototype(name, description);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
