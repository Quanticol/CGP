/**
 */
package eu.quanticol.cgp.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.quanticol.cgp.model.NodeInstance#getPrototype <em>Prototype</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.NodeInstance#getIncomingConnections <em>Incoming Connections</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.NodeInstance#getOutgoingConnections <em>Outgoing Connections</em>}</li>
 * </ul>
 *
 * @see eu.quanticol.cgp.model.CGPPackage#getNodeInstance()
 * @model
 * @generated
 */
public interface NodeInstance extends LocatedElement {
	/**
	 * Returns the value of the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prototype</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prototype</em>' reference.
	 * @see #setPrototype(NodePrototype)
	 * @see eu.quanticol.cgp.model.CGPPackage#getNodeInstance_Prototype()
	 * @model
	 * @generated
	 */
	NodePrototype getPrototype();

	/**
	 * Sets the value of the '{@link eu.quanticol.cgp.model.NodeInstance#getPrototype <em>Prototype</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prototype</em>' reference.
	 * @see #getPrototype()
	 * @generated
	 */
	void setPrototype(NodePrototype value);

	/**
	 * Returns the value of the '<em><b>Incoming Connections</b></em>' reference list.
	 * The list contents are of type {@link eu.quanticol.cgp.model.ConnectionInstance}.
	 * It is bidirectional and its opposite is '{@link eu.quanticol.cgp.model.ConnectionInstance#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Connections</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming Connections</em>' reference list.
	 * @see eu.quanticol.cgp.model.CGPPackage#getNodeInstance_IncomingConnections()
	 * @see eu.quanticol.cgp.model.ConnectionInstance#getTo
	 * @model opposite="to"
	 * @generated
	 */
	EList<ConnectionInstance> getIncomingConnections();

	/**
	 * Returns the value of the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * The list contents are of type {@link eu.quanticol.cgp.model.ConnectionInstance}.
	 * It is bidirectional and its opposite is '{@link eu.quanticol.cgp.model.ConnectionInstance#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing Connections</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Connections</em>' containment reference list.
	 * @see eu.quanticol.cgp.model.CGPPackage#getNodeInstance_OutgoingConnections()
	 * @see eu.quanticol.cgp.model.ConnectionInstance#getFrom
	 * @model opposite="from" containment="true"
	 * @generated
	 */
	EList<ConnectionInstance> getOutgoingConnections();

} // NodeInstance
