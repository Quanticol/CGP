/**
 */
package eu.quanticol.cgp.model.impl;

import eu.quanticol.cgp.model.CGPPackage;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.ConnectionInstance;
import eu.quanticol.cgp.model.ConnectionPrototype;
import eu.quanticol.cgp.model.LocatedElement;
import eu.quanticol.cgp.model.NodePrototype;
import eu.quanticol.cgp.model.SpatialModel;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Spatial Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.quanticol.cgp.model.impl.SpatialModelImpl#getNodePrototypes <em>Node Prototypes</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.impl.SpatialModelImpl#getComponentPrototypes <em>Component Prototypes</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.impl.SpatialModelImpl#getConnectionPrototypes <em>Connection Prototypes</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.impl.SpatialModelImpl#getLocatedElements <em>Located Elements</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.impl.SpatialModelImpl#getConnectionInstances <em>Connection Instances</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SpatialModelImpl extends MinimalEObjectImpl.Container implements SpatialModel {
	/**
	 * The cached value of the '{@link #getNodePrototypes() <em>Node Prototypes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodePrototypes()
	 * @generated
	 * @ordered
	 */
	protected EList<NodePrototype> nodePrototypes;

	/**
	 * The cached value of the '{@link #getComponentPrototypes() <em>Component Prototypes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentPrototypes()
	 * @generated
	 * @ordered
	 */
	protected EList<ComponentPrototype> componentPrototypes;

	/**
	 * The cached value of the '{@link #getConnectionPrototypes() <em>Connection Prototypes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionPrototypes()
	 * @generated
	 * @ordered
	 */
	protected EList<ConnectionPrototype> connectionPrototypes;

	/**
	 * The cached value of the '{@link #getLocatedElements() <em>Located Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocatedElements()
	 * @generated
	 * @ordered
	 */
	protected EList<LocatedElement> locatedElements;

	/**
	 * The cached value of the '{@link #getConnectionInstances() <em>Connection Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionInstances()
	 * @generated
	 * @ordered
	 */
	protected EList<ConnectionInstance> connectionInstances;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpatialModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGPPackage.Literals.SPATIAL_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NodePrototype> getNodePrototypes() {
		if (nodePrototypes == null) {
			nodePrototypes = new EObjectContainmentWithInverseEList<NodePrototype>(NodePrototype.class, this, CGPPackage.SPATIAL_MODEL__NODE_PROTOTYPES, CGPPackage.NODE_PROTOTYPE__MODEL);
		}
		return nodePrototypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ComponentPrototype> getComponentPrototypes() {
		if (componentPrototypes == null) {
			componentPrototypes = new EObjectContainmentWithInverseEList<ComponentPrototype>(ComponentPrototype.class, this, CGPPackage.SPATIAL_MODEL__COMPONENT_PROTOTYPES, CGPPackage.COMPONENT_PROTOTYPE__MODEL);
		}
		return componentPrototypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConnectionPrototype> getConnectionPrototypes() {
		if (connectionPrototypes == null) {
			connectionPrototypes = new EObjectContainmentWithInverseEList<ConnectionPrototype>(ConnectionPrototype.class, this, CGPPackage.SPATIAL_MODEL__CONNECTION_PROTOTYPES, CGPPackage.CONNECTION_PROTOTYPE__MODEL);
		}
		return connectionPrototypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LocatedElement> getLocatedElements() {
		if (locatedElements == null) {
			locatedElements = new EObjectContainmentWithInverseEList<LocatedElement>(LocatedElement.class, this, CGPPackage.SPATIAL_MODEL__LOCATED_ELEMENTS, CGPPackage.LOCATED_ELEMENT__MODEL);
		}
		return locatedElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConnectionInstance> getConnectionInstances() {
		if (connectionInstances == null) {
			connectionInstances = new EObjectContainmentWithInverseEList<ConnectionInstance>(ConnectionInstance.class, this, CGPPackage.SPATIAL_MODEL__CONNECTION_INSTANCES, CGPPackage.CONNECTION_INSTANCE__MODEL);
		}
		return connectionInstances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CGPPackage.SPATIAL_MODEL__NODE_PROTOTYPES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getNodePrototypes()).basicAdd(otherEnd, msgs);
			case CGPPackage.SPATIAL_MODEL__COMPONENT_PROTOTYPES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getComponentPrototypes()).basicAdd(otherEnd, msgs);
			case CGPPackage.SPATIAL_MODEL__CONNECTION_PROTOTYPES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getConnectionPrototypes()).basicAdd(otherEnd, msgs);
			case CGPPackage.SPATIAL_MODEL__LOCATED_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLocatedElements()).basicAdd(otherEnd, msgs);
			case CGPPackage.SPATIAL_MODEL__CONNECTION_INSTANCES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getConnectionInstances()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CGPPackage.SPATIAL_MODEL__NODE_PROTOTYPES:
				return ((InternalEList<?>)getNodePrototypes()).basicRemove(otherEnd, msgs);
			case CGPPackage.SPATIAL_MODEL__COMPONENT_PROTOTYPES:
				return ((InternalEList<?>)getComponentPrototypes()).basicRemove(otherEnd, msgs);
			case CGPPackage.SPATIAL_MODEL__CONNECTION_PROTOTYPES:
				return ((InternalEList<?>)getConnectionPrototypes()).basicRemove(otherEnd, msgs);
			case CGPPackage.SPATIAL_MODEL__LOCATED_ELEMENTS:
				return ((InternalEList<?>)getLocatedElements()).basicRemove(otherEnd, msgs);
			case CGPPackage.SPATIAL_MODEL__CONNECTION_INSTANCES:
				return ((InternalEList<?>)getConnectionInstances()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CGPPackage.SPATIAL_MODEL__NODE_PROTOTYPES:
				return getNodePrototypes();
			case CGPPackage.SPATIAL_MODEL__COMPONENT_PROTOTYPES:
				return getComponentPrototypes();
			case CGPPackage.SPATIAL_MODEL__CONNECTION_PROTOTYPES:
				return getConnectionPrototypes();
			case CGPPackage.SPATIAL_MODEL__LOCATED_ELEMENTS:
				return getLocatedElements();
			case CGPPackage.SPATIAL_MODEL__CONNECTION_INSTANCES:
				return getConnectionInstances();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CGPPackage.SPATIAL_MODEL__NODE_PROTOTYPES:
				getNodePrototypes().clear();
				getNodePrototypes().addAll((Collection<? extends NodePrototype>)newValue);
				return;
			case CGPPackage.SPATIAL_MODEL__COMPONENT_PROTOTYPES:
				getComponentPrototypes().clear();
				getComponentPrototypes().addAll((Collection<? extends ComponentPrototype>)newValue);
				return;
			case CGPPackage.SPATIAL_MODEL__CONNECTION_PROTOTYPES:
				getConnectionPrototypes().clear();
				getConnectionPrototypes().addAll((Collection<? extends ConnectionPrototype>)newValue);
				return;
			case CGPPackage.SPATIAL_MODEL__LOCATED_ELEMENTS:
				getLocatedElements().clear();
				getLocatedElements().addAll((Collection<? extends LocatedElement>)newValue);
				return;
			case CGPPackage.SPATIAL_MODEL__CONNECTION_INSTANCES:
				getConnectionInstances().clear();
				getConnectionInstances().addAll((Collection<? extends ConnectionInstance>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CGPPackage.SPATIAL_MODEL__NODE_PROTOTYPES:
				getNodePrototypes().clear();
				return;
			case CGPPackage.SPATIAL_MODEL__COMPONENT_PROTOTYPES:
				getComponentPrototypes().clear();
				return;
			case CGPPackage.SPATIAL_MODEL__CONNECTION_PROTOTYPES:
				getConnectionPrototypes().clear();
				return;
			case CGPPackage.SPATIAL_MODEL__LOCATED_ELEMENTS:
				getLocatedElements().clear();
				return;
			case CGPPackage.SPATIAL_MODEL__CONNECTION_INSTANCES:
				getConnectionInstances().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CGPPackage.SPATIAL_MODEL__NODE_PROTOTYPES:
				return nodePrototypes != null && !nodePrototypes.isEmpty();
			case CGPPackage.SPATIAL_MODEL__COMPONENT_PROTOTYPES:
				return componentPrototypes != null && !componentPrototypes.isEmpty();
			case CGPPackage.SPATIAL_MODEL__CONNECTION_PROTOTYPES:
				return connectionPrototypes != null && !connectionPrototypes.isEmpty();
			case CGPPackage.SPATIAL_MODEL__LOCATED_ELEMENTS:
				return locatedElements != null && !locatedElements.isEmpty();
			case CGPPackage.SPATIAL_MODEL__CONNECTION_INSTANCES:
				return connectionInstances != null && !connectionInstances.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SpatialModelImpl
