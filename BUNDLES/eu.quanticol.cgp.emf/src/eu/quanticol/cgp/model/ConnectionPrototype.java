/**
 */
package eu.quanticol.cgp.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection Prototype</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.quanticol.cgp.model.ConnectionPrototype#getName <em>Name</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.ConnectionPrototype#getDescription <em>Description</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.ConnectionPrototype#getModel <em>Model</em>}</li>
 * </ul>
 *
 * @see eu.quanticol.cgp.model.CGPPackage#getConnectionPrototype()
 * @model
 * @generated
 */
public interface ConnectionPrototype extends EObject {
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
	 * @see eu.quanticol.cgp.model.CGPPackage#getConnectionPrototype_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.quanticol.cgp.model.ConnectionPrototype#getName <em>Name</em>}' attribute.
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
	 * @see eu.quanticol.cgp.model.CGPPackage#getConnectionPrototype_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link eu.quanticol.cgp.model.ConnectionPrototype#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eu.quanticol.cgp.model.SpatialModel#getConnectionPrototypes <em>Connection Prototypes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' container reference.
	 * @see #setModel(SpatialModel)
	 * @see eu.quanticol.cgp.model.CGPPackage#getConnectionPrototype_Model()
	 * @see eu.quanticol.cgp.model.SpatialModel#getConnectionPrototypes
	 * @model opposite="connectionPrototypes" required="true" transient="false"
	 * @generated
	 */
	SpatialModel getModel();

	/**
	 * Sets the value of the '{@link eu.quanticol.cgp.model.ConnectionPrototype#getModel <em>Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' container reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(SpatialModel value);

} // ConnectionPrototype
