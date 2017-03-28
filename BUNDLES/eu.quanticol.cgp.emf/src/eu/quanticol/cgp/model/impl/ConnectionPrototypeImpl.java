/**
 */
package eu.quanticol.cgp.model.impl;

import eu.quanticol.cgp.model.CGPPackage;
import eu.quanticol.cgp.model.Colour;
import eu.quanticol.cgp.model.ConnectionPrototype;
import eu.quanticol.cgp.model.SpatialModel;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection Prototype</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.quanticol.cgp.model.impl.ConnectionPrototypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.impl.ConnectionPrototypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.impl.ConnectionPrototypeImpl#getModel <em>Model</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.impl.ConnectionPrototypeImpl#getColour <em>Colour</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConnectionPrototypeImpl extends MinimalEObjectImpl.Container implements ConnectionPrototype {
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
	 * The cached value of the '{@link #getColour() <em>Colour</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColour()
	 * @generated
	 * @ordered
	 */
	protected Colour colour;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectionPrototypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGPPackage.Literals.CONNECTION_PROTOTYPE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CGPPackage.CONNECTION_PROTOTYPE__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CGPPackage.CONNECTION_PROTOTYPE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpatialModel getModel() {
		if (eContainerFeatureID() != CGPPackage.CONNECTION_PROTOTYPE__MODEL) return null;
		return (SpatialModel)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModel(SpatialModel newModel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModel, CGPPackage.CONNECTION_PROTOTYPE__MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModel(SpatialModel newModel) {
		if (newModel != eInternalContainer() || (eContainerFeatureID() != CGPPackage.CONNECTION_PROTOTYPE__MODEL && newModel != null)) {
			if (EcoreUtil.isAncestor(this, newModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModel != null)
				msgs = ((InternalEObject)newModel).eInverseAdd(this, CGPPackage.SPATIAL_MODEL__CONNECTION_PROTOTYPES, SpatialModel.class, msgs);
			msgs = basicSetModel(newModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGPPackage.CONNECTION_PROTOTYPE__MODEL, newModel, newModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Colour getColour() {
		return colour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetColour(Colour newColour, NotificationChain msgs) {
		Colour oldColour = colour;
		colour = newColour;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CGPPackage.CONNECTION_PROTOTYPE__COLOUR, oldColour, newColour);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColour(Colour newColour) {
		if (newColour != colour) {
			NotificationChain msgs = null;
			if (colour != null)
				msgs = ((InternalEObject)colour).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CGPPackage.CONNECTION_PROTOTYPE__COLOUR, null, msgs);
			if (newColour != null)
				msgs = ((InternalEObject)newColour).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CGPPackage.CONNECTION_PROTOTYPE__COLOUR, null, msgs);
			msgs = basicSetColour(newColour, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGPPackage.CONNECTION_PROTOTYPE__COLOUR, newColour, newColour));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CGPPackage.CONNECTION_PROTOTYPE__MODEL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetModel((SpatialModel)otherEnd, msgs);
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
			case CGPPackage.CONNECTION_PROTOTYPE__MODEL:
				return basicSetModel(null, msgs);
			case CGPPackage.CONNECTION_PROTOTYPE__COLOUR:
				return basicSetColour(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case CGPPackage.CONNECTION_PROTOTYPE__MODEL:
				return eInternalContainer().eInverseRemove(this, CGPPackage.SPATIAL_MODEL__CONNECTION_PROTOTYPES, SpatialModel.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CGPPackage.CONNECTION_PROTOTYPE__NAME:
				return getName();
			case CGPPackage.CONNECTION_PROTOTYPE__DESCRIPTION:
				return getDescription();
			case CGPPackage.CONNECTION_PROTOTYPE__MODEL:
				return getModel();
			case CGPPackage.CONNECTION_PROTOTYPE__COLOUR:
				return getColour();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CGPPackage.CONNECTION_PROTOTYPE__NAME:
				setName((String)newValue);
				return;
			case CGPPackage.CONNECTION_PROTOTYPE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case CGPPackage.CONNECTION_PROTOTYPE__MODEL:
				setModel((SpatialModel)newValue);
				return;
			case CGPPackage.CONNECTION_PROTOTYPE__COLOUR:
				setColour((Colour)newValue);
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
			case CGPPackage.CONNECTION_PROTOTYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CGPPackage.CONNECTION_PROTOTYPE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case CGPPackage.CONNECTION_PROTOTYPE__MODEL:
				setModel((SpatialModel)null);
				return;
			case CGPPackage.CONNECTION_PROTOTYPE__COLOUR:
				setColour((Colour)null);
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
			case CGPPackage.CONNECTION_PROTOTYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CGPPackage.CONNECTION_PROTOTYPE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case CGPPackage.CONNECTION_PROTOTYPE__MODEL:
				return getModel() != null;
			case CGPPackage.CONNECTION_PROTOTYPE__COLOUR:
				return colour != null;
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

} //ConnectionPrototypeImpl
