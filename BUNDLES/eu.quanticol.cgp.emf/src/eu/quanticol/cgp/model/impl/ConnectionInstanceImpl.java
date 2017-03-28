/**
 */
package eu.quanticol.cgp.model.impl;

import eu.quanticol.cgp.model.CGPPackage;
import eu.quanticol.cgp.model.ConnectionInstance;
import eu.quanticol.cgp.model.ConnectionPrototype;
import eu.quanticol.cgp.model.NodeInstance;
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
 * An implementation of the model object '<em><b>Connection Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.quanticol.cgp.model.impl.ConnectionInstanceImpl#getFrom <em>From</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.impl.ConnectionInstanceImpl#getTo <em>To</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.impl.ConnectionInstanceImpl#getPrototype <em>Prototype</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.impl.ConnectionInstanceImpl#getModel <em>Model</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConnectionInstanceImpl extends MinimalEObjectImpl.Container implements ConnectionInstance {
	/**
	 * The cached value of the '{@link #getFrom() <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrom()
	 * @generated
	 * @ordered
	 */
	protected NodeInstance from;

	/**
	 * The cached value of the '{@link #getTo() <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
	protected NodeInstance to;

	/**
	 * The cached value of the '{@link #getPrototype() <em>Prototype</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrototype()
	 * @generated
	 * @ordered
	 */
	protected ConnectionPrototype prototype;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectionInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGPPackage.Literals.CONNECTION_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodeInstance getFrom() {
		if (from != null && from.eIsProxy()) {
			InternalEObject oldFrom = (InternalEObject)from;
			from = (NodeInstance)eResolveProxy(oldFrom);
			if (from != oldFrom) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CGPPackage.CONNECTION_INSTANCE__FROM, oldFrom, from));
			}
		}
		return from;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodeInstance basicGetFrom() {
		return from;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFrom(NodeInstance newFrom, NotificationChain msgs) {
		NodeInstance oldFrom = from;
		from = newFrom;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CGPPackage.CONNECTION_INSTANCE__FROM, oldFrom, newFrom);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFrom(NodeInstance newFrom) {
		if (newFrom != from) {
			NotificationChain msgs = null;
			if (from != null)
				msgs = ((InternalEObject)from).eInverseRemove(this, CGPPackage.NODE_INSTANCE__OUTGOING_CONNECTIONS, NodeInstance.class, msgs);
			if (newFrom != null)
				msgs = ((InternalEObject)newFrom).eInverseAdd(this, CGPPackage.NODE_INSTANCE__OUTGOING_CONNECTIONS, NodeInstance.class, msgs);
			msgs = basicSetFrom(newFrom, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGPPackage.CONNECTION_INSTANCE__FROM, newFrom, newFrom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodeInstance getTo() {
		if (to != null && to.eIsProxy()) {
			InternalEObject oldTo = (InternalEObject)to;
			to = (NodeInstance)eResolveProxy(oldTo);
			if (to != oldTo) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CGPPackage.CONNECTION_INSTANCE__TO, oldTo, to));
			}
		}
		return to;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodeInstance basicGetTo() {
		return to;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTo(NodeInstance newTo, NotificationChain msgs) {
		NodeInstance oldTo = to;
		to = newTo;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CGPPackage.CONNECTION_INSTANCE__TO, oldTo, newTo);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTo(NodeInstance newTo) {
		if (newTo != to) {
			NotificationChain msgs = null;
			if (to != null)
				msgs = ((InternalEObject)to).eInverseRemove(this, CGPPackage.NODE_INSTANCE__INCOMING_CONNECTIONS, NodeInstance.class, msgs);
			if (newTo != null)
				msgs = ((InternalEObject)newTo).eInverseAdd(this, CGPPackage.NODE_INSTANCE__INCOMING_CONNECTIONS, NodeInstance.class, msgs);
			msgs = basicSetTo(newTo, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGPPackage.CONNECTION_INSTANCE__TO, newTo, newTo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionPrototype getPrototype() {
		if (prototype != null && prototype.eIsProxy()) {
			InternalEObject oldPrototype = (InternalEObject)prototype;
			prototype = (ConnectionPrototype)eResolveProxy(oldPrototype);
			if (prototype != oldPrototype) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CGPPackage.CONNECTION_INSTANCE__PROTOTYPE, oldPrototype, prototype));
			}
		}
		return prototype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionPrototype basicGetPrototype() {
		return prototype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrototype(ConnectionPrototype newPrototype) {
		ConnectionPrototype oldPrototype = prototype;
		prototype = newPrototype;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGPPackage.CONNECTION_INSTANCE__PROTOTYPE, oldPrototype, prototype));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpatialModel getModel() {
		if (eContainerFeatureID() != CGPPackage.CONNECTION_INSTANCE__MODEL) return null;
		return (SpatialModel)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModel(SpatialModel newModel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModel, CGPPackage.CONNECTION_INSTANCE__MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModel(SpatialModel newModel) {
		if (newModel != eInternalContainer() || (eContainerFeatureID() != CGPPackage.CONNECTION_INSTANCE__MODEL && newModel != null)) {
			if (EcoreUtil.isAncestor(this, newModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModel != null)
				msgs = ((InternalEObject)newModel).eInverseAdd(this, CGPPackage.SPATIAL_MODEL__CONNECTION_INSTANCES, SpatialModel.class, msgs);
			msgs = basicSetModel(newModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGPPackage.CONNECTION_INSTANCE__MODEL, newModel, newModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CGPPackage.CONNECTION_INSTANCE__FROM:
				if (from != null)
					msgs = ((InternalEObject)from).eInverseRemove(this, CGPPackage.NODE_INSTANCE__OUTGOING_CONNECTIONS, NodeInstance.class, msgs);
				return basicSetFrom((NodeInstance)otherEnd, msgs);
			case CGPPackage.CONNECTION_INSTANCE__TO:
				if (to != null)
					msgs = ((InternalEObject)to).eInverseRemove(this, CGPPackage.NODE_INSTANCE__INCOMING_CONNECTIONS, NodeInstance.class, msgs);
				return basicSetTo((NodeInstance)otherEnd, msgs);
			case CGPPackage.CONNECTION_INSTANCE__MODEL:
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
			case CGPPackage.CONNECTION_INSTANCE__FROM:
				return basicSetFrom(null, msgs);
			case CGPPackage.CONNECTION_INSTANCE__TO:
				return basicSetTo(null, msgs);
			case CGPPackage.CONNECTION_INSTANCE__MODEL:
				return basicSetModel(null, msgs);
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
			case CGPPackage.CONNECTION_INSTANCE__MODEL:
				return eInternalContainer().eInverseRemove(this, CGPPackage.SPATIAL_MODEL__CONNECTION_INSTANCES, SpatialModel.class, msgs);
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
			case CGPPackage.CONNECTION_INSTANCE__FROM:
				if (resolve) return getFrom();
				return basicGetFrom();
			case CGPPackage.CONNECTION_INSTANCE__TO:
				if (resolve) return getTo();
				return basicGetTo();
			case CGPPackage.CONNECTION_INSTANCE__PROTOTYPE:
				if (resolve) return getPrototype();
				return basicGetPrototype();
			case CGPPackage.CONNECTION_INSTANCE__MODEL:
				return getModel();
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
			case CGPPackage.CONNECTION_INSTANCE__FROM:
				setFrom((NodeInstance)newValue);
				return;
			case CGPPackage.CONNECTION_INSTANCE__TO:
				setTo((NodeInstance)newValue);
				return;
			case CGPPackage.CONNECTION_INSTANCE__PROTOTYPE:
				setPrototype((ConnectionPrototype)newValue);
				return;
			case CGPPackage.CONNECTION_INSTANCE__MODEL:
				setModel((SpatialModel)newValue);
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
			case CGPPackage.CONNECTION_INSTANCE__FROM:
				setFrom((NodeInstance)null);
				return;
			case CGPPackage.CONNECTION_INSTANCE__TO:
				setTo((NodeInstance)null);
				return;
			case CGPPackage.CONNECTION_INSTANCE__PROTOTYPE:
				setPrototype((ConnectionPrototype)null);
				return;
			case CGPPackage.CONNECTION_INSTANCE__MODEL:
				setModel((SpatialModel)null);
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
			case CGPPackage.CONNECTION_INSTANCE__FROM:
				return from != null;
			case CGPPackage.CONNECTION_INSTANCE__TO:
				return to != null;
			case CGPPackage.CONNECTION_INSTANCE__PROTOTYPE:
				return prototype != null;
			case CGPPackage.CONNECTION_INSTANCE__MODEL:
				return getModel() != null;
		}
		return super.eIsSet(featureID);
	}

} //ConnectionInstanceImpl
