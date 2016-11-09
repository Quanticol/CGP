/**
 */
package eu.quanticol.cgp.model.impl;

import eu.quanticol.cgp.model.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CGPFactoryImpl extends EFactoryImpl implements CGPFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CGPFactory init() {
		try {
			CGPFactory theCGPFactory = (CGPFactory)EPackage.Registry.INSTANCE.getEFactory(CGPPackage.eNS_URI);
			if (theCGPFactory != null) {
				return theCGPFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CGPFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CGPFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CGPPackage.SPATIAL_MODEL: return createSpatialModel();
			case CGPPackage.NODE_PROTOTYPE: return createNodePrototype();
			case CGPPackage.COMPONENT_PROTOTYPE: return createComponentPrototype();
			case CGPPackage.CONNECTION_PROTOTYPE: return createConnectionPrototype();
			case CGPPackage.NODE_INSTANCE: return createNodeInstance();
			case CGPPackage.COMPONENT_INSTANCE: return createComponentInstance();
			case CGPPackage.CONNECTION_INSTANCE: return createConnectionInstance();
			case CGPPackage.STATE: return createState();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpatialModel createSpatialModel() {
		SpatialModelImpl spatialModel = new SpatialModelImpl();
		return spatialModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodePrototype createNodePrototype() {
		NodePrototypeImpl nodePrototype = new NodePrototypeImpl();
		return nodePrototype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentPrototype createComponentPrototype() {
		ComponentPrototypeImpl componentPrototype = new ComponentPrototypeImpl();
		return componentPrototype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionPrototype createConnectionPrototype() {
		ConnectionPrototypeImpl connectionPrototype = new ConnectionPrototypeImpl();
		return connectionPrototype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodeInstance createNodeInstance() {
		NodeInstanceImpl nodeInstance = new NodeInstanceImpl();
		return nodeInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentInstance createComponentInstance() {
		ComponentInstanceImpl componentInstance = new ComponentInstanceImpl();
		return componentInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionInstance createConnectionInstance() {
		ConnectionInstanceImpl connectionInstance = new ConnectionInstanceImpl();
		return connectionInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State createState() {
		StateImpl state = new StateImpl();
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CGPPackage getCGPPackage() {
		return (CGPPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CGPPackage getPackage() {
		return CGPPackage.eINSTANCE;
	}

} //CGPFactoryImpl
