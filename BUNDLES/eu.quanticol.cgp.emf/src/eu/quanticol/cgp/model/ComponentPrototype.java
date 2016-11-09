/**
 */
package eu.quanticol.cgp.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Prototype</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.quanticol.cgp.model.ComponentPrototype#getName <em>Name</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.ComponentPrototype#getDescription <em>Description</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.ComponentPrototype#getModel <em>Model</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.ComponentPrototype#getStates <em>States</em>}</li>
 * </ul>
 *
 * @see eu.quanticol.cgp.model.CGPPackage#getComponentPrototype()
 * @model
 * @generated
 */
public interface ComponentPrototype extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see eu.quanticol.cgp.model.CGPPackage#getComponentPrototype_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.quanticol.cgp.model.ComponentPrototype#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see eu.quanticol.cgp.model.CGPPackage#getComponentPrototype_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link eu.quanticol.cgp.model.ComponentPrototype#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eu.quanticol.cgp.model.SpatialModel#getComponentPrototypes <em>Component Prototypes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' container reference.
	 * @see #setModel(SpatialModel)
	 * @see eu.quanticol.cgp.model.CGPPackage#getComponentPrototype_Model()
	 * @see eu.quanticol.cgp.model.SpatialModel#getComponentPrototypes
	 * @model opposite="componentPrototypes" required="true" transient="false"
	 * @generated
	 */
	SpatialModel getModel();

	/**
	 * Sets the value of the '{@link eu.quanticol.cgp.model.ComponentPrototype#getModel <em>Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' container reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(SpatialModel value);

	/**
	 * Returns the value of the '<em><b>States</b></em>' containment reference list.
	 * The list contents are of type {@link eu.quanticol.cgp.model.State}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>States</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>States</em>' containment reference list.
	 * @see eu.quanticol.cgp.model.CGPPackage#getComponentPrototype_States()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<State> getStates();

} // ComponentPrototype
