/**
 */
package eu.quanticol.cgp.model.impl;

import eu.quanticol.cgp.model.CGPPackage;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.ConnectionPrototype;
import eu.quanticol.cgp.model.NodePrototype;
import eu.quanticol.cgp.model.State;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.quanticol.cgp.model.impl.StateImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.impl.StateImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.impl.StateImpl#getAllowedConnections <em>Allowed Connections</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.impl.StateImpl#getComponent <em>Component</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.impl.StateImpl#getAllowedNodes <em>Allowed Nodes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StateImpl extends MinimalEObjectImpl.Container implements State {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAllowedConnections() <em>Allowed Connections</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllowedConnections()
	 * @generated
	 * @ordered
	 */
	protected EList<ConnectionPrototype> allowedConnections;

	/**
	 * The cached value of the '{@link #getComponent() <em>Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponent()
	 * @generated
	 * @ordered
	 */
	protected ComponentPrototype component;

	/**
	 * The cached value of the '{@link #getAllowedNodes() <em>Allowed Nodes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllowedNodes()
	 * @generated
	 * @ordered
	 */
	protected EList<NodePrototype> allowedNodes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGPPackage.Literals.STATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGPPackage.STATE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGPPackage.STATE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConnectionPrototype> getAllowedConnections() {
		if (allowedConnections == null) {
			allowedConnections = new EObjectResolvingEList<ConnectionPrototype>(ConnectionPrototype.class, this, CGPPackage.STATE__ALLOWED_CONNECTIONS);
		}
		return allowedConnections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentPrototype getComponent() {
		if (component != null && component.eIsProxy()) {
			InternalEObject oldComponent = (InternalEObject)component;
			component = (ComponentPrototype)eResolveProxy(oldComponent);
			if (component != oldComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CGPPackage.STATE__COMPONENT, oldComponent, component));
			}
		}
		return component;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentPrototype basicGetComponent() {
		return component;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponent(ComponentPrototype newComponent) {
		ComponentPrototype oldComponent = component;
		component = newComponent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGPPackage.STATE__COMPONENT, oldComponent, component));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NodePrototype> getAllowedNodes() {
		if (allowedNodes == null) {
			allowedNodes = new EObjectResolvingEList<NodePrototype>(NodePrototype.class, this, CGPPackage.STATE__ALLOWED_NODES);
		}
		return allowedNodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CGPPackage.STATE__NAME:
				return getName();
			case CGPPackage.STATE__DESCRIPTION:
				return getDescription();
			case CGPPackage.STATE__ALLOWED_CONNECTIONS:
				return getAllowedConnections();
			case CGPPackage.STATE__COMPONENT:
				if (resolve) return getComponent();
				return basicGetComponent();
			case CGPPackage.STATE__ALLOWED_NODES:
				return getAllowedNodes();
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
			case CGPPackage.STATE__NAME:
				setName((String)newValue);
				return;
			case CGPPackage.STATE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case CGPPackage.STATE__ALLOWED_CONNECTIONS:
				getAllowedConnections().clear();
				getAllowedConnections().addAll((Collection<? extends ConnectionPrototype>)newValue);
				return;
			case CGPPackage.STATE__COMPONENT:
				setComponent((ComponentPrototype)newValue);
				return;
			case CGPPackage.STATE__ALLOWED_NODES:
				getAllowedNodes().clear();
				getAllowedNodes().addAll((Collection<? extends NodePrototype>)newValue);
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
			case CGPPackage.STATE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CGPPackage.STATE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case CGPPackage.STATE__ALLOWED_CONNECTIONS:
				getAllowedConnections().clear();
				return;
			case CGPPackage.STATE__COMPONENT:
				setComponent((ComponentPrototype)null);
				return;
			case CGPPackage.STATE__ALLOWED_NODES:
				getAllowedNodes().clear();
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
			case CGPPackage.STATE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CGPPackage.STATE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case CGPPackage.STATE__ALLOWED_CONNECTIONS:
				return allowedConnections != null && !allowedConnections.isEmpty();
			case CGPPackage.STATE__COMPONENT:
				return component != null;
			case CGPPackage.STATE__ALLOWED_NODES:
				return allowedNodes != null && !allowedNodes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", description: ");
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //StateImpl
