/**
 */
package eu.quanticol.cgp.model.impl;

import eu.quanticol.cgp.model.CGPPackage;
import eu.quanticol.cgp.model.Colour;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.Shape;
import eu.quanticol.cgp.model.SpatialModel;
import eu.quanticol.cgp.model.State;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Prototype</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.quanticol.cgp.model.impl.ComponentPrototypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.impl.ComponentPrototypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.impl.ComponentPrototypeImpl#getModel <em>Model</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.impl.ComponentPrototypeImpl#getStates <em>States</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.impl.ComponentPrototypeImpl#getInitState <em>Init State</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.impl.ComponentPrototypeImpl#getColour <em>Colour</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.impl.ComponentPrototypeImpl#getShape <em>Shape</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ComponentPrototypeImpl extends MinimalEObjectImpl.Container implements ComponentPrototype {
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
	 * The cached value of the '{@link #getStates() <em>States</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStates()
	 * @generated
	 * @ordered
	 */
	protected EList<State> states;

	/**
	 * The cached value of the '{@link #getInitState() <em>Init State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitState()
	 * @generated
	 * @ordered
	 */
	protected State initState;

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
	 * The cached value of the '{@link #getShape() <em>Shape</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShape()
	 * @generated
	 * @ordered
	 */
	protected Shape shape;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentPrototypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGPPackage.Literals.COMPONENT_PROTOTYPE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CGPPackage.COMPONENT_PROTOTYPE__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CGPPackage.COMPONENT_PROTOTYPE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpatialModel getModel() {
		if (eContainerFeatureID() != CGPPackage.COMPONENT_PROTOTYPE__MODEL) return null;
		return (SpatialModel)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModel(SpatialModel newModel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModel, CGPPackage.COMPONENT_PROTOTYPE__MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModel(SpatialModel newModel) {
		if (newModel != eInternalContainer() || (eContainerFeatureID() != CGPPackage.COMPONENT_PROTOTYPE__MODEL && newModel != null)) {
			if (EcoreUtil.isAncestor(this, newModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModel != null)
				msgs = ((InternalEObject)newModel).eInverseAdd(this, CGPPackage.SPATIAL_MODEL__COMPONENT_PROTOTYPES, SpatialModel.class, msgs);
			msgs = basicSetModel(newModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGPPackage.COMPONENT_PROTOTYPE__MODEL, newModel, newModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<State> getStates() {
		if (states == null) {
			states = new EObjectContainmentEList<State>(State.class, this, CGPPackage.COMPONENT_PROTOTYPE__STATES);
		}
		return states;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State getInitState() {
		if (initState != null && initState.eIsProxy()) {
			InternalEObject oldInitState = (InternalEObject)initState;
			initState = (State)eResolveProxy(oldInitState);
			if (initState != oldInitState) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CGPPackage.COMPONENT_PROTOTYPE__INIT_STATE, oldInitState, initState));
			}
		}
		return initState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State basicGetInitState() {
		return initState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitState(State newInitState) {
		State oldInitState = initState;
		initState = newInitState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGPPackage.COMPONENT_PROTOTYPE__INIT_STATE, oldInitState, initState));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CGPPackage.COMPONENT_PROTOTYPE__COLOUR, oldColour, newColour);
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
				msgs = ((InternalEObject)colour).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CGPPackage.COMPONENT_PROTOTYPE__COLOUR, null, msgs);
			if (newColour != null)
				msgs = ((InternalEObject)newColour).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CGPPackage.COMPONENT_PROTOTYPE__COLOUR, null, msgs);
			msgs = basicSetColour(newColour, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGPPackage.COMPONENT_PROTOTYPE__COLOUR, newColour, newColour));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Shape getShape() {
		return shape;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetShape(Shape newShape, NotificationChain msgs) {
		Shape oldShape = shape;
		shape = newShape;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CGPPackage.COMPONENT_PROTOTYPE__SHAPE, oldShape, newShape);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShape(Shape newShape) {
		if (newShape != shape) {
			NotificationChain msgs = null;
			if (shape != null)
				msgs = ((InternalEObject)shape).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CGPPackage.COMPONENT_PROTOTYPE__SHAPE, null, msgs);
			if (newShape != null)
				msgs = ((InternalEObject)newShape).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CGPPackage.COMPONENT_PROTOTYPE__SHAPE, null, msgs);
			msgs = basicSetShape(newShape, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGPPackage.COMPONENT_PROTOTYPE__SHAPE, newShape, newShape));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CGPPackage.COMPONENT_PROTOTYPE__MODEL:
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
			case CGPPackage.COMPONENT_PROTOTYPE__MODEL:
				return basicSetModel(null, msgs);
			case CGPPackage.COMPONENT_PROTOTYPE__STATES:
				return ((InternalEList<?>)getStates()).basicRemove(otherEnd, msgs);
			case CGPPackage.COMPONENT_PROTOTYPE__COLOUR:
				return basicSetColour(null, msgs);
			case CGPPackage.COMPONENT_PROTOTYPE__SHAPE:
				return basicSetShape(null, msgs);
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
			case CGPPackage.COMPONENT_PROTOTYPE__MODEL:
				return eInternalContainer().eInverseRemove(this, CGPPackage.SPATIAL_MODEL__COMPONENT_PROTOTYPES, SpatialModel.class, msgs);
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
			case CGPPackage.COMPONENT_PROTOTYPE__NAME:
				return getName();
			case CGPPackage.COMPONENT_PROTOTYPE__DESCRIPTION:
				return getDescription();
			case CGPPackage.COMPONENT_PROTOTYPE__MODEL:
				return getModel();
			case CGPPackage.COMPONENT_PROTOTYPE__STATES:
				return getStates();
			case CGPPackage.COMPONENT_PROTOTYPE__INIT_STATE:
				if (resolve) return getInitState();
				return basicGetInitState();
			case CGPPackage.COMPONENT_PROTOTYPE__COLOUR:
				return getColour();
			case CGPPackage.COMPONENT_PROTOTYPE__SHAPE:
				return getShape();
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
			case CGPPackage.COMPONENT_PROTOTYPE__NAME:
				setName((String)newValue);
				return;
			case CGPPackage.COMPONENT_PROTOTYPE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case CGPPackage.COMPONENT_PROTOTYPE__MODEL:
				setModel((SpatialModel)newValue);
				return;
			case CGPPackage.COMPONENT_PROTOTYPE__STATES:
				getStates().clear();
				getStates().addAll((Collection<? extends State>)newValue);
				return;
			case CGPPackage.COMPONENT_PROTOTYPE__INIT_STATE:
				setInitState((State)newValue);
				return;
			case CGPPackage.COMPONENT_PROTOTYPE__COLOUR:
				setColour((Colour)newValue);
				return;
			case CGPPackage.COMPONENT_PROTOTYPE__SHAPE:
				setShape((Shape)newValue);
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
			case CGPPackage.COMPONENT_PROTOTYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CGPPackage.COMPONENT_PROTOTYPE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case CGPPackage.COMPONENT_PROTOTYPE__MODEL:
				setModel((SpatialModel)null);
				return;
			case CGPPackage.COMPONENT_PROTOTYPE__STATES:
				getStates().clear();
				return;
			case CGPPackage.COMPONENT_PROTOTYPE__INIT_STATE:
				setInitState((State)null);
				return;
			case CGPPackage.COMPONENT_PROTOTYPE__COLOUR:
				setColour((Colour)null);
				return;
			case CGPPackage.COMPONENT_PROTOTYPE__SHAPE:
				setShape((Shape)null);
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
			case CGPPackage.COMPONENT_PROTOTYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CGPPackage.COMPONENT_PROTOTYPE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case CGPPackage.COMPONENT_PROTOTYPE__MODEL:
				return getModel() != null;
			case CGPPackage.COMPONENT_PROTOTYPE__STATES:
				return states != null && !states.isEmpty();
			case CGPPackage.COMPONENT_PROTOTYPE__INIT_STATE:
				return initState != null;
			case CGPPackage.COMPONENT_PROTOTYPE__COLOUR:
				return colour != null;
			case CGPPackage.COMPONENT_PROTOTYPE__SHAPE:
				return shape != null;
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

} //ComponentPrototypeImpl
