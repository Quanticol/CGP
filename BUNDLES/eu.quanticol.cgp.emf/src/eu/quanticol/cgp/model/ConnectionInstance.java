/**
 */
package eu.quanticol.cgp.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.quanticol.cgp.model.ConnectionInstance#getFrom <em>From</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.ConnectionInstance#getTo <em>To</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.ConnectionInstance#getPrototype <em>Prototype</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.ConnectionInstance#getModel <em>Model</em>}</li>
 * </ul>
 *
 * @see eu.quanticol.cgp.model.CGPPackage#getConnectionInstance()
 * @model
 * @generated
 */
public interface ConnectionInstance extends EObject {
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eu.quanticol.cgp.model.NodeInstance#getOutgoingConnections <em>Outgoing Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #setFrom(NodeInstance)
	 * @see eu.quanticol.cgp.model.CGPPackage#getConnectionInstance_From()
	 * @see eu.quanticol.cgp.model.NodeInstance#getOutgoingConnections
	 * @model opposite="outgoingConnections"
	 * @generated
	 */
	NodeInstance getFrom();

	/**
	 * Sets the value of the '{@link eu.quanticol.cgp.model.ConnectionInstance#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(NodeInstance value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eu.quanticol.cgp.model.NodeInstance#getIncomingConnections <em>Incoming Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(NodeInstance)
	 * @see eu.quanticol.cgp.model.CGPPackage#getConnectionInstance_To()
	 * @see eu.quanticol.cgp.model.NodeInstance#getIncomingConnections
	 * @model opposite="incomingConnections"
	 * @generated
	 */
	NodeInstance getTo();

	/**
	 * Sets the value of the '{@link eu.quanticol.cgp.model.ConnectionInstance#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(NodeInstance value);

	/**
	 * Returns the value of the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prototype</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prototype</em>' reference.
	 * @see #setPrototype(ConnectionPrototype)
	 * @see eu.quanticol.cgp.model.CGPPackage#getConnectionInstance_Prototype()
	 * @model
	 * @generated
	 */
	ConnectionPrototype getPrototype();

	/**
	 * Sets the value of the '{@link eu.quanticol.cgp.model.ConnectionInstance#getPrototype <em>Prototype</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prototype</em>' reference.
	 * @see #getPrototype()
	 * @generated
	 */
	void setPrototype(ConnectionPrototype value);

	/**
	 * Returns the value of the '<em><b>Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' reference.
	 * @see #setModel(SpatialModel)
	 * @see eu.quanticol.cgp.model.CGPPackage#getConnectionInstance_Model()
	 * @model
	 * @generated
	 */
	SpatialModel getModel();

	/**
	 * Sets the value of the '{@link eu.quanticol.cgp.model.ConnectionInstance#getModel <em>Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(SpatialModel value);

} // ConnectionInstance
