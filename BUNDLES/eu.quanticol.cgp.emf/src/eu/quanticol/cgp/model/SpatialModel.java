/**
 */
package eu.quanticol.cgp.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Spatial Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.quanticol.cgp.model.SpatialModel#getNodePrototypes <em>Node Prototypes</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.SpatialModel#getComponentPrototypes <em>Component Prototypes</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.SpatialModel#getConnectionPrototypes <em>Connection Prototypes</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.SpatialModel#getLocatedElements <em>Located Elements</em>}</li>
 * </ul>
 *
 * @see eu.quanticol.cgp.model.CGPPackage#getSpatialModel()
 * @model
 * @generated
 */
public interface SpatialModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Node Prototypes</b></em>' containment reference list.
	 * The list contents are of type {@link eu.quanticol.cgp.model.NodePrototype}.
	 * It is bidirectional and its opposite is '{@link eu.quanticol.cgp.model.NodePrototype#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node Prototypes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Prototypes</em>' containment reference list.
	 * @see eu.quanticol.cgp.model.CGPPackage#getSpatialModel_NodePrototypes()
	 * @see eu.quanticol.cgp.model.NodePrototype#getModel
	 * @model opposite="model" containment="true"
	 * @generated
	 */
	EList<NodePrototype> getNodePrototypes();

	/**
	 * Returns the value of the '<em><b>Component Prototypes</b></em>' containment reference list.
	 * The list contents are of type {@link eu.quanticol.cgp.model.ComponentPrototype}.
	 * It is bidirectional and its opposite is '{@link eu.quanticol.cgp.model.ComponentPrototype#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Prototypes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Prototypes</em>' containment reference list.
	 * @see eu.quanticol.cgp.model.CGPPackage#getSpatialModel_ComponentPrototypes()
	 * @see eu.quanticol.cgp.model.ComponentPrototype#getModel
	 * @model opposite="model" containment="true"
	 * @generated
	 */
	EList<ComponentPrototype> getComponentPrototypes();

	/**
	 * Returns the value of the '<em><b>Connection Prototypes</b></em>' containment reference list.
	 * The list contents are of type {@link eu.quanticol.cgp.model.ConnectionPrototype}.
	 * It is bidirectional and its opposite is '{@link eu.quanticol.cgp.model.ConnectionPrototype#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection Prototypes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection Prototypes</em>' containment reference list.
	 * @see eu.quanticol.cgp.model.CGPPackage#getSpatialModel_ConnectionPrototypes()
	 * @see eu.quanticol.cgp.model.ConnectionPrototype#getModel
	 * @model opposite="model" containment="true"
	 * @generated
	 */
	EList<ConnectionPrototype> getConnectionPrototypes();

	/**
	 * Returns the value of the '<em><b>Located Elements</b></em>' containment reference list.
	 * The list contents are of type {@link eu.quanticol.cgp.model.LocatedElement}.
	 * It is bidirectional and its opposite is '{@link eu.quanticol.cgp.model.LocatedElement#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Located Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Located Elements</em>' containment reference list.
	 * @see eu.quanticol.cgp.model.CGPPackage#getSpatialModel_LocatedElements()
	 * @see eu.quanticol.cgp.model.LocatedElement#getModel
	 * @model opposite="model" containment="true"
	 * @generated
	 */
	EList<LocatedElement> getLocatedElements();

} // SpatialModel
