/**
 */
package eu.quanticol.cgp.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see eu.quanticol.cgp.model.CGPFactory
 * @model kind="package"
 * @generated
 */
public interface CGPPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "cgp";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.quanticol.eu/cgp";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "cgp";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CGPPackage eINSTANCE = eu.quanticol.cgp.model.impl.CGPPackageImpl.init();

	/**
	 * The meta object id for the '{@link eu.quanticol.cgp.model.impl.SpatialModelImpl <em>Spatial Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.quanticol.cgp.model.impl.SpatialModelImpl
	 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getSpatialModel()
	 * @generated
	 */
	int SPATIAL_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Node Prototypes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPATIAL_MODEL__NODE_PROTOTYPES = 0;

	/**
	 * The feature id for the '<em><b>Component Prototypes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPATIAL_MODEL__COMPONENT_PROTOTYPES = 1;

	/**
	 * The feature id for the '<em><b>Connection Prototypes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPATIAL_MODEL__CONNECTION_PROTOTYPES = 2;

	/**
	 * The feature id for the '<em><b>Located Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPATIAL_MODEL__LOCATED_ELEMENTS = 3;

	/**
	 * The feature id for the '<em><b>Connection Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPATIAL_MODEL__CONNECTION_INSTANCES = 4;

	/**
	 * The number of structural features of the '<em>Spatial Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPATIAL_MODEL_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Spatial Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPATIAL_MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.quanticol.cgp.model.impl.NodePrototypeImpl <em>Node Prototype</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.quanticol.cgp.model.impl.NodePrototypeImpl
	 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getNodePrototype()
	 * @generated
	 */
	int NODE_PROTOTYPE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_PROTOTYPE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_PROTOTYPE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_PROTOTYPE__MODEL = 2;

	/**
	 * The feature id for the '<em><b>Colour</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_PROTOTYPE__COLOUR = 3;

	/**
	 * The number of structural features of the '<em>Node Prototype</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_PROTOTYPE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Node Prototype</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_PROTOTYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.quanticol.cgp.model.impl.ComponentPrototypeImpl <em>Component Prototype</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.quanticol.cgp.model.impl.ComponentPrototypeImpl
	 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getComponentPrototype()
	 * @generated
	 */
	int COMPONENT_PROTOTYPE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PROTOTYPE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PROTOTYPE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PROTOTYPE__MODEL = 2;

	/**
	 * The feature id for the '<em><b>States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PROTOTYPE__STATES = 3;

	/**
	 * The feature id for the '<em><b>Init State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PROTOTYPE__INIT_STATE = 4;

	/**
	 * The feature id for the '<em><b>Colour</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PROTOTYPE__COLOUR = 5;

	/**
	 * The feature id for the '<em><b>Shape</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PROTOTYPE__SHAPE = 6;

	/**
	 * The number of structural features of the '<em>Component Prototype</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PROTOTYPE_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Component Prototype</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_PROTOTYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.quanticol.cgp.model.impl.ConnectionPrototypeImpl <em>Connection Prototype</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.quanticol.cgp.model.impl.ConnectionPrototypeImpl
	 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getConnectionPrototype()
	 * @generated
	 */
	int CONNECTION_PROTOTYPE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_PROTOTYPE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_PROTOTYPE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_PROTOTYPE__MODEL = 2;

	/**
	 * The feature id for the '<em><b>Colour</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_PROTOTYPE__COLOUR = 3;

	/**
	 * The number of structural features of the '<em>Connection Prototype</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_PROTOTYPE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Connection Prototype</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_PROTOTYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.quanticol.cgp.model.impl.LocatedElementImpl <em>Located Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.quanticol.cgp.model.impl.LocatedElementImpl
	 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getLocatedElement()
	 * @generated
	 */
	int LOCATED_ELEMENT = 5;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATED_ELEMENT__X = 0;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATED_ELEMENT__Y = 1;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATED_ELEMENT__MODEL = 2;

	/**
	 * The number of structural features of the '<em>Located Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATED_ELEMENT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Located Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATED_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.quanticol.cgp.model.impl.NodeInstanceImpl <em>Node Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.quanticol.cgp.model.impl.NodeInstanceImpl
	 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getNodeInstance()
	 * @generated
	 */
	int NODE_INSTANCE = 4;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_INSTANCE__X = LOCATED_ELEMENT__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_INSTANCE__Y = LOCATED_ELEMENT__Y;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_INSTANCE__MODEL = LOCATED_ELEMENT__MODEL;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_INSTANCE__PROTOTYPE = LOCATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_INSTANCE__INCOMING_CONNECTIONS = LOCATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_INSTANCE__OUTGOING_CONNECTIONS = LOCATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Node Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_INSTANCE_FEATURE_COUNT = LOCATED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Node Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_INSTANCE_OPERATION_COUNT = LOCATED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.quanticol.cgp.model.impl.ComponentInstanceImpl <em>Component Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.quanticol.cgp.model.impl.ComponentInstanceImpl
	 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getComponentInstance()
	 * @generated
	 */
	int COMPONENT_INSTANCE = 6;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INSTANCE__X = LOCATED_ELEMENT__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INSTANCE__Y = LOCATED_ELEMENT__Y;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INSTANCE__MODEL = LOCATED_ELEMENT__MODEL;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INSTANCE__PROTOTYPE = LOCATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Component Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INSTANCE_FEATURE_COUNT = LOCATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Component Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INSTANCE_OPERATION_COUNT = LOCATED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.quanticol.cgp.model.impl.ConnectionInstanceImpl <em>Connection Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.quanticol.cgp.model.impl.ConnectionInstanceImpl
	 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getConnectionInstance()
	 * @generated
	 */
	int CONNECTION_INSTANCE = 7;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_INSTANCE__FROM = 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_INSTANCE__TO = 1;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_INSTANCE__PROTOTYPE = 2;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_INSTANCE__MODEL = 3;

	/**
	 * The number of structural features of the '<em>Connection Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_INSTANCE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Connection Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_INSTANCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.quanticol.cgp.model.impl.StateImpl <em>State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.quanticol.cgp.model.impl.StateImpl
	 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getState()
	 * @generated
	 */
	int STATE = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Allowed Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__ALLOWED_CONNECTIONS = 2;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__COMPONENT = 3;

	/**
	 * The feature id for the '<em><b>Allowed Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__ALLOWED_NODES = 4;

	/**
	 * The number of structural features of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_OPERATION_COUNT = 0;


	/**
	 * The meta object id for the '{@link eu.quanticol.cgp.model.impl.ColourImpl <em>Colour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.quanticol.cgp.model.impl.ColourImpl
	 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getColour()
	 * @generated
	 */
	int COLOUR = 9;

	/**
	 * The feature id for the '<em><b>Red</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOUR__RED = 0;

	/**
	 * The feature id for the '<em><b>Green</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOUR__GREEN = 1;

	/**
	 * The feature id for the '<em><b>Blue</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOUR__BLUE = 2;

	/**
	 * The number of structural features of the '<em>Colour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOUR_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Colour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOUR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.quanticol.cgp.model.impl.ShapeImpl <em>Shape</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.quanticol.cgp.model.impl.ShapeImpl
	 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getShape()
	 * @generated
	 */
	int SHAPE = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Shape</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Shape</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHAPE_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link eu.quanticol.cgp.model.SpatialModel <em>Spatial Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Spatial Model</em>'.
	 * @see eu.quanticol.cgp.model.SpatialModel
	 * @generated
	 */
	EClass getSpatialModel();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.quanticol.cgp.model.SpatialModel#getNodePrototypes <em>Node Prototypes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Node Prototypes</em>'.
	 * @see eu.quanticol.cgp.model.SpatialModel#getNodePrototypes()
	 * @see #getSpatialModel()
	 * @generated
	 */
	EReference getSpatialModel_NodePrototypes();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.quanticol.cgp.model.SpatialModel#getComponentPrototypes <em>Component Prototypes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Component Prototypes</em>'.
	 * @see eu.quanticol.cgp.model.SpatialModel#getComponentPrototypes()
	 * @see #getSpatialModel()
	 * @generated
	 */
	EReference getSpatialModel_ComponentPrototypes();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.quanticol.cgp.model.SpatialModel#getConnectionPrototypes <em>Connection Prototypes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connection Prototypes</em>'.
	 * @see eu.quanticol.cgp.model.SpatialModel#getConnectionPrototypes()
	 * @see #getSpatialModel()
	 * @generated
	 */
	EReference getSpatialModel_ConnectionPrototypes();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.quanticol.cgp.model.SpatialModel#getLocatedElements <em>Located Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Located Elements</em>'.
	 * @see eu.quanticol.cgp.model.SpatialModel#getLocatedElements()
	 * @see #getSpatialModel()
	 * @generated
	 */
	EReference getSpatialModel_LocatedElements();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.quanticol.cgp.model.SpatialModel#getConnectionInstances <em>Connection Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connection Instances</em>'.
	 * @see eu.quanticol.cgp.model.SpatialModel#getConnectionInstances()
	 * @see #getSpatialModel()
	 * @generated
	 */
	EReference getSpatialModel_ConnectionInstances();

	/**
	 * Returns the meta object for class '{@link eu.quanticol.cgp.model.NodePrototype <em>Node Prototype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node Prototype</em>'.
	 * @see eu.quanticol.cgp.model.NodePrototype
	 * @generated
	 */
	EClass getNodePrototype();

	/**
	 * Returns the meta object for the attribute '{@link eu.quanticol.cgp.model.NodePrototype#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.quanticol.cgp.model.NodePrototype#getName()
	 * @see #getNodePrototype()
	 * @generated
	 */
	EAttribute getNodePrototype_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.quanticol.cgp.model.NodePrototype#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see eu.quanticol.cgp.model.NodePrototype#getDescription()
	 * @see #getNodePrototype()
	 * @generated
	 */
	EAttribute getNodePrototype_Description();

	/**
	 * Returns the meta object for the container reference '{@link eu.quanticol.cgp.model.NodePrototype#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Model</em>'.
	 * @see eu.quanticol.cgp.model.NodePrototype#getModel()
	 * @see #getNodePrototype()
	 * @generated
	 */
	EReference getNodePrototype_Model();

	/**
	 * Returns the meta object for the containment reference '{@link eu.quanticol.cgp.model.NodePrototype#getColour <em>Colour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Colour</em>'.
	 * @see eu.quanticol.cgp.model.NodePrototype#getColour()
	 * @see #getNodePrototype()
	 * @generated
	 */
	EReference getNodePrototype_Colour();

	/**
	 * Returns the meta object for class '{@link eu.quanticol.cgp.model.ComponentPrototype <em>Component Prototype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Prototype</em>'.
	 * @see eu.quanticol.cgp.model.ComponentPrototype
	 * @generated
	 */
	EClass getComponentPrototype();

	/**
	 * Returns the meta object for the attribute '{@link eu.quanticol.cgp.model.ComponentPrototype#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.quanticol.cgp.model.ComponentPrototype#getName()
	 * @see #getComponentPrototype()
	 * @generated
	 */
	EAttribute getComponentPrototype_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.quanticol.cgp.model.ComponentPrototype#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see eu.quanticol.cgp.model.ComponentPrototype#getDescription()
	 * @see #getComponentPrototype()
	 * @generated
	 */
	EAttribute getComponentPrototype_Description();

	/**
	 * Returns the meta object for the container reference '{@link eu.quanticol.cgp.model.ComponentPrototype#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Model</em>'.
	 * @see eu.quanticol.cgp.model.ComponentPrototype#getModel()
	 * @see #getComponentPrototype()
	 * @generated
	 */
	EReference getComponentPrototype_Model();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.quanticol.cgp.model.ComponentPrototype#getStates <em>States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>States</em>'.
	 * @see eu.quanticol.cgp.model.ComponentPrototype#getStates()
	 * @see #getComponentPrototype()
	 * @generated
	 */
	EReference getComponentPrototype_States();

	/**
	 * Returns the meta object for the reference '{@link eu.quanticol.cgp.model.ComponentPrototype#getInitState <em>Init State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Init State</em>'.
	 * @see eu.quanticol.cgp.model.ComponentPrototype#getInitState()
	 * @see #getComponentPrototype()
	 * @generated
	 */
	EReference getComponentPrototype_InitState();

	/**
	 * Returns the meta object for the containment reference '{@link eu.quanticol.cgp.model.ComponentPrototype#getColour <em>Colour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Colour</em>'.
	 * @see eu.quanticol.cgp.model.ComponentPrototype#getColour()
	 * @see #getComponentPrototype()
	 * @generated
	 */
	EReference getComponentPrototype_Colour();

	/**
	 * Returns the meta object for the containment reference '{@link eu.quanticol.cgp.model.ComponentPrototype#getShape <em>Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Shape</em>'.
	 * @see eu.quanticol.cgp.model.ComponentPrototype#getShape()
	 * @see #getComponentPrototype()
	 * @generated
	 */
	EReference getComponentPrototype_Shape();

	/**
	 * Returns the meta object for class '{@link eu.quanticol.cgp.model.ConnectionPrototype <em>Connection Prototype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection Prototype</em>'.
	 * @see eu.quanticol.cgp.model.ConnectionPrototype
	 * @generated
	 */
	EClass getConnectionPrototype();

	/**
	 * Returns the meta object for the attribute '{@link eu.quanticol.cgp.model.ConnectionPrototype#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.quanticol.cgp.model.ConnectionPrototype#getName()
	 * @see #getConnectionPrototype()
	 * @generated
	 */
	EAttribute getConnectionPrototype_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.quanticol.cgp.model.ConnectionPrototype#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see eu.quanticol.cgp.model.ConnectionPrototype#getDescription()
	 * @see #getConnectionPrototype()
	 * @generated
	 */
	EAttribute getConnectionPrototype_Description();

	/**
	 * Returns the meta object for the container reference '{@link eu.quanticol.cgp.model.ConnectionPrototype#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Model</em>'.
	 * @see eu.quanticol.cgp.model.ConnectionPrototype#getModel()
	 * @see #getConnectionPrototype()
	 * @generated
	 */
	EReference getConnectionPrototype_Model();

	/**
	 * Returns the meta object for the containment reference '{@link eu.quanticol.cgp.model.ConnectionPrototype#getColour <em>Colour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Colour</em>'.
	 * @see eu.quanticol.cgp.model.ConnectionPrototype#getColour()
	 * @see #getConnectionPrototype()
	 * @generated
	 */
	EReference getConnectionPrototype_Colour();

	/**
	 * Returns the meta object for class '{@link eu.quanticol.cgp.model.NodeInstance <em>Node Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node Instance</em>'.
	 * @see eu.quanticol.cgp.model.NodeInstance
	 * @generated
	 */
	EClass getNodeInstance();

	/**
	 * Returns the meta object for the reference '{@link eu.quanticol.cgp.model.NodeInstance#getPrototype <em>Prototype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Prototype</em>'.
	 * @see eu.quanticol.cgp.model.NodeInstance#getPrototype()
	 * @see #getNodeInstance()
	 * @generated
	 */
	EReference getNodeInstance_Prototype();

	/**
	 * Returns the meta object for the reference list '{@link eu.quanticol.cgp.model.NodeInstance#getIncomingConnections <em>Incoming Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incoming Connections</em>'.
	 * @see eu.quanticol.cgp.model.NodeInstance#getIncomingConnections()
	 * @see #getNodeInstance()
	 * @generated
	 */
	EReference getNodeInstance_IncomingConnections();

	/**
	 * Returns the meta object for the reference list '{@link eu.quanticol.cgp.model.NodeInstance#getOutgoingConnections <em>Outgoing Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outgoing Connections</em>'.
	 * @see eu.quanticol.cgp.model.NodeInstance#getOutgoingConnections()
	 * @see #getNodeInstance()
	 * @generated
	 */
	EReference getNodeInstance_OutgoingConnections();

	/**
	 * Returns the meta object for class '{@link eu.quanticol.cgp.model.LocatedElement <em>Located Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Located Element</em>'.
	 * @see eu.quanticol.cgp.model.LocatedElement
	 * @generated
	 */
	EClass getLocatedElement();

	/**
	 * Returns the meta object for the attribute '{@link eu.quanticol.cgp.model.LocatedElement#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see eu.quanticol.cgp.model.LocatedElement#getX()
	 * @see #getLocatedElement()
	 * @generated
	 */
	EAttribute getLocatedElement_X();

	/**
	 * Returns the meta object for the attribute '{@link eu.quanticol.cgp.model.LocatedElement#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see eu.quanticol.cgp.model.LocatedElement#getY()
	 * @see #getLocatedElement()
	 * @generated
	 */
	EAttribute getLocatedElement_Y();

	/**
	 * Returns the meta object for the container reference '{@link eu.quanticol.cgp.model.LocatedElement#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Model</em>'.
	 * @see eu.quanticol.cgp.model.LocatedElement#getModel()
	 * @see #getLocatedElement()
	 * @generated
	 */
	EReference getLocatedElement_Model();

	/**
	 * Returns the meta object for class '{@link eu.quanticol.cgp.model.ComponentInstance <em>Component Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Instance</em>'.
	 * @see eu.quanticol.cgp.model.ComponentInstance
	 * @generated
	 */
	EClass getComponentInstance();

	/**
	 * Returns the meta object for the reference '{@link eu.quanticol.cgp.model.ComponentInstance#getPrototype <em>Prototype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Prototype</em>'.
	 * @see eu.quanticol.cgp.model.ComponentInstance#getPrototype()
	 * @see #getComponentInstance()
	 * @generated
	 */
	EReference getComponentInstance_Prototype();

	/**
	 * Returns the meta object for class '{@link eu.quanticol.cgp.model.ConnectionInstance <em>Connection Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection Instance</em>'.
	 * @see eu.quanticol.cgp.model.ConnectionInstance
	 * @generated
	 */
	EClass getConnectionInstance();

	/**
	 * Returns the meta object for the reference '{@link eu.quanticol.cgp.model.ConnectionInstance#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From</em>'.
	 * @see eu.quanticol.cgp.model.ConnectionInstance#getFrom()
	 * @see #getConnectionInstance()
	 * @generated
	 */
	EReference getConnectionInstance_From();

	/**
	 * Returns the meta object for the reference '{@link eu.quanticol.cgp.model.ConnectionInstance#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To</em>'.
	 * @see eu.quanticol.cgp.model.ConnectionInstance#getTo()
	 * @see #getConnectionInstance()
	 * @generated
	 */
	EReference getConnectionInstance_To();

	/**
	 * Returns the meta object for the reference '{@link eu.quanticol.cgp.model.ConnectionInstance#getPrototype <em>Prototype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Prototype</em>'.
	 * @see eu.quanticol.cgp.model.ConnectionInstance#getPrototype()
	 * @see #getConnectionInstance()
	 * @generated
	 */
	EReference getConnectionInstance_Prototype();

	/**
	 * Returns the meta object for the container reference '{@link eu.quanticol.cgp.model.ConnectionInstance#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Model</em>'.
	 * @see eu.quanticol.cgp.model.ConnectionInstance#getModel()
	 * @see #getConnectionInstance()
	 * @generated
	 */
	EReference getConnectionInstance_Model();

	/**
	 * Returns the meta object for class '{@link eu.quanticol.cgp.model.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State</em>'.
	 * @see eu.quanticol.cgp.model.State
	 * @generated
	 */
	EClass getState();

	/**
	 * Returns the meta object for the attribute '{@link eu.quanticol.cgp.model.State#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.quanticol.cgp.model.State#getName()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.quanticol.cgp.model.State#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see eu.quanticol.cgp.model.State#getDescription()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Description();

	/**
	 * Returns the meta object for the reference list '{@link eu.quanticol.cgp.model.State#getAllowedConnections <em>Allowed Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Allowed Connections</em>'.
	 * @see eu.quanticol.cgp.model.State#getAllowedConnections()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_AllowedConnections();

	/**
	 * Returns the meta object for the reference '{@link eu.quanticol.cgp.model.State#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Component</em>'.
	 * @see eu.quanticol.cgp.model.State#getComponent()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_Component();

	/**
	 * Returns the meta object for the reference list '{@link eu.quanticol.cgp.model.State#getAllowedNodes <em>Allowed Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Allowed Nodes</em>'.
	 * @see eu.quanticol.cgp.model.State#getAllowedNodes()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_AllowedNodes();

	/**
	 * Returns the meta object for class '{@link eu.quanticol.cgp.model.Colour <em>Colour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Colour</em>'.
	 * @see eu.quanticol.cgp.model.Colour
	 * @generated
	 */
	EClass getColour();

	/**
	 * Returns the meta object for the attribute '{@link eu.quanticol.cgp.model.Colour#getRed <em>Red</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Red</em>'.
	 * @see eu.quanticol.cgp.model.Colour#getRed()
	 * @see #getColour()
	 * @generated
	 */
	EAttribute getColour_Red();

	/**
	 * Returns the meta object for the attribute '{@link eu.quanticol.cgp.model.Colour#getGreen <em>Green</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Green</em>'.
	 * @see eu.quanticol.cgp.model.Colour#getGreen()
	 * @see #getColour()
	 * @generated
	 */
	EAttribute getColour_Green();

	/**
	 * Returns the meta object for the attribute '{@link eu.quanticol.cgp.model.Colour#getBlue <em>Blue</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Blue</em>'.
	 * @see eu.quanticol.cgp.model.Colour#getBlue()
	 * @see #getColour()
	 * @generated
	 */
	EAttribute getColour_Blue();

	/**
	 * Returns the meta object for class '{@link eu.quanticol.cgp.model.Shape <em>Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Shape</em>'.
	 * @see eu.quanticol.cgp.model.Shape
	 * @generated
	 */
	EClass getShape();

	/**
	 * Returns the meta object for the attribute '{@link eu.quanticol.cgp.model.Shape#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.quanticol.cgp.model.Shape#getName()
	 * @see #getShape()
	 * @generated
	 */
	EAttribute getShape_Name();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CGPFactory getCGPFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link eu.quanticol.cgp.model.impl.SpatialModelImpl <em>Spatial Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.quanticol.cgp.model.impl.SpatialModelImpl
		 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getSpatialModel()
		 * @generated
		 */
		EClass SPATIAL_MODEL = eINSTANCE.getSpatialModel();

		/**
		 * The meta object literal for the '<em><b>Node Prototypes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPATIAL_MODEL__NODE_PROTOTYPES = eINSTANCE.getSpatialModel_NodePrototypes();

		/**
		 * The meta object literal for the '<em><b>Component Prototypes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPATIAL_MODEL__COMPONENT_PROTOTYPES = eINSTANCE.getSpatialModel_ComponentPrototypes();

		/**
		 * The meta object literal for the '<em><b>Connection Prototypes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPATIAL_MODEL__CONNECTION_PROTOTYPES = eINSTANCE.getSpatialModel_ConnectionPrototypes();

		/**
		 * The meta object literal for the '<em><b>Located Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPATIAL_MODEL__LOCATED_ELEMENTS = eINSTANCE.getSpatialModel_LocatedElements();

		/**
		 * The meta object literal for the '<em><b>Connection Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPATIAL_MODEL__CONNECTION_INSTANCES = eINSTANCE.getSpatialModel_ConnectionInstances();

		/**
		 * The meta object literal for the '{@link eu.quanticol.cgp.model.impl.NodePrototypeImpl <em>Node Prototype</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.quanticol.cgp.model.impl.NodePrototypeImpl
		 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getNodePrototype()
		 * @generated
		 */
		EClass NODE_PROTOTYPE = eINSTANCE.getNodePrototype();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_PROTOTYPE__NAME = eINSTANCE.getNodePrototype_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_PROTOTYPE__DESCRIPTION = eINSTANCE.getNodePrototype_Description();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_PROTOTYPE__MODEL = eINSTANCE.getNodePrototype_Model();

		/**
		 * The meta object literal for the '<em><b>Colour</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_PROTOTYPE__COLOUR = eINSTANCE.getNodePrototype_Colour();

		/**
		 * The meta object literal for the '{@link eu.quanticol.cgp.model.impl.ComponentPrototypeImpl <em>Component Prototype</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.quanticol.cgp.model.impl.ComponentPrototypeImpl
		 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getComponentPrototype()
		 * @generated
		 */
		EClass COMPONENT_PROTOTYPE = eINSTANCE.getComponentPrototype();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_PROTOTYPE__NAME = eINSTANCE.getComponentPrototype_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_PROTOTYPE__DESCRIPTION = eINSTANCE.getComponentPrototype_Description();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_PROTOTYPE__MODEL = eINSTANCE.getComponentPrototype_Model();

		/**
		 * The meta object literal for the '<em><b>States</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_PROTOTYPE__STATES = eINSTANCE.getComponentPrototype_States();

		/**
		 * The meta object literal for the '<em><b>Init State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_PROTOTYPE__INIT_STATE = eINSTANCE.getComponentPrototype_InitState();

		/**
		 * The meta object literal for the '<em><b>Colour</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_PROTOTYPE__COLOUR = eINSTANCE.getComponentPrototype_Colour();

		/**
		 * The meta object literal for the '<em><b>Shape</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_PROTOTYPE__SHAPE = eINSTANCE.getComponentPrototype_Shape();

		/**
		 * The meta object literal for the '{@link eu.quanticol.cgp.model.impl.ConnectionPrototypeImpl <em>Connection Prototype</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.quanticol.cgp.model.impl.ConnectionPrototypeImpl
		 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getConnectionPrototype()
		 * @generated
		 */
		EClass CONNECTION_PROTOTYPE = eINSTANCE.getConnectionPrototype();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_PROTOTYPE__NAME = eINSTANCE.getConnectionPrototype_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_PROTOTYPE__DESCRIPTION = eINSTANCE.getConnectionPrototype_Description();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION_PROTOTYPE__MODEL = eINSTANCE.getConnectionPrototype_Model();

		/**
		 * The meta object literal for the '<em><b>Colour</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION_PROTOTYPE__COLOUR = eINSTANCE.getConnectionPrototype_Colour();

		/**
		 * The meta object literal for the '{@link eu.quanticol.cgp.model.impl.NodeInstanceImpl <em>Node Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.quanticol.cgp.model.impl.NodeInstanceImpl
		 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getNodeInstance()
		 * @generated
		 */
		EClass NODE_INSTANCE = eINSTANCE.getNodeInstance();

		/**
		 * The meta object literal for the '<em><b>Prototype</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_INSTANCE__PROTOTYPE = eINSTANCE.getNodeInstance_Prototype();

		/**
		 * The meta object literal for the '<em><b>Incoming Connections</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_INSTANCE__INCOMING_CONNECTIONS = eINSTANCE.getNodeInstance_IncomingConnections();

		/**
		 * The meta object literal for the '<em><b>Outgoing Connections</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_INSTANCE__OUTGOING_CONNECTIONS = eINSTANCE.getNodeInstance_OutgoingConnections();

		/**
		 * The meta object literal for the '{@link eu.quanticol.cgp.model.impl.LocatedElementImpl <em>Located Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.quanticol.cgp.model.impl.LocatedElementImpl
		 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getLocatedElement()
		 * @generated
		 */
		EClass LOCATED_ELEMENT = eINSTANCE.getLocatedElement();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCATED_ELEMENT__X = eINSTANCE.getLocatedElement_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCATED_ELEMENT__Y = eINSTANCE.getLocatedElement_Y();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOCATED_ELEMENT__MODEL = eINSTANCE.getLocatedElement_Model();

		/**
		 * The meta object literal for the '{@link eu.quanticol.cgp.model.impl.ComponentInstanceImpl <em>Component Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.quanticol.cgp.model.impl.ComponentInstanceImpl
		 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getComponentInstance()
		 * @generated
		 */
		EClass COMPONENT_INSTANCE = eINSTANCE.getComponentInstance();

		/**
		 * The meta object literal for the '<em><b>Prototype</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_INSTANCE__PROTOTYPE = eINSTANCE.getComponentInstance_Prototype();

		/**
		 * The meta object literal for the '{@link eu.quanticol.cgp.model.impl.ConnectionInstanceImpl <em>Connection Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.quanticol.cgp.model.impl.ConnectionInstanceImpl
		 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getConnectionInstance()
		 * @generated
		 */
		EClass CONNECTION_INSTANCE = eINSTANCE.getConnectionInstance();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION_INSTANCE__FROM = eINSTANCE.getConnectionInstance_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION_INSTANCE__TO = eINSTANCE.getConnectionInstance_To();

		/**
		 * The meta object literal for the '<em><b>Prototype</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION_INSTANCE__PROTOTYPE = eINSTANCE.getConnectionInstance_Prototype();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION_INSTANCE__MODEL = eINSTANCE.getConnectionInstance_Model();

		/**
		 * The meta object literal for the '{@link eu.quanticol.cgp.model.impl.StateImpl <em>State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.quanticol.cgp.model.impl.StateImpl
		 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getState()
		 * @generated
		 */
		EClass STATE = eINSTANCE.getState();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__NAME = eINSTANCE.getState_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__DESCRIPTION = eINSTANCE.getState_Description();

		/**
		 * The meta object literal for the '<em><b>Allowed Connections</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__ALLOWED_CONNECTIONS = eINSTANCE.getState_AllowedConnections();

		/**
		 * The meta object literal for the '<em><b>Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__COMPONENT = eINSTANCE.getState_Component();

		/**
		 * The meta object literal for the '<em><b>Allowed Nodes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__ALLOWED_NODES = eINSTANCE.getState_AllowedNodes();

		/**
		 * The meta object literal for the '{@link eu.quanticol.cgp.model.impl.ColourImpl <em>Colour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.quanticol.cgp.model.impl.ColourImpl
		 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getColour()
		 * @generated
		 */
		EClass COLOUR = eINSTANCE.getColour();

		/**
		 * The meta object literal for the '<em><b>Red</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLOUR__RED = eINSTANCE.getColour_Red();

		/**
		 * The meta object literal for the '<em><b>Green</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLOUR__GREEN = eINSTANCE.getColour_Green();

		/**
		 * The meta object literal for the '<em><b>Blue</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLOUR__BLUE = eINSTANCE.getColour_Blue();

		/**
		 * The meta object literal for the '{@link eu.quanticol.cgp.model.impl.ShapeImpl <em>Shape</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.quanticol.cgp.model.impl.ShapeImpl
		 * @see eu.quanticol.cgp.model.impl.CGPPackageImpl#getShape()
		 * @generated
		 */
		EClass SHAPE = eINSTANCE.getShape();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SHAPE__NAME = eINSTANCE.getShape_Name();

	}

} //CGPPackage
