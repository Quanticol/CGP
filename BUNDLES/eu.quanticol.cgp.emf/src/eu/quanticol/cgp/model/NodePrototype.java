/**
 */
package eu.quanticol.cgp.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node Prototype</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.quanticol.cgp.model.NodePrototype#getName <em>Name</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.NodePrototype#getDescription <em>Description</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.NodePrototype#getModel <em>Model</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.NodePrototype#getColour <em>Colour</em>}</li>
 * </ul>
 *
 * @see eu.quanticol.cgp.model.CGPPackage#getNodePrototype()
 * @model
 * @generated
 */
public interface NodePrototype extends EObject {
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
	 * @see eu.quanticol.cgp.model.CGPPackage#getNodePrototype_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.quanticol.cgp.model.NodePrototype#getName <em>Name</em>}' attribute.
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
	 * @see eu.quanticol.cgp.model.CGPPackage#getNodePrototype_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link eu.quanticol.cgp.model.NodePrototype#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eu.quanticol.cgp.model.SpatialModel#getNodePrototypes <em>Node Prototypes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' container reference.
	 * @see #setModel(SpatialModel)
	 * @see eu.quanticol.cgp.model.CGPPackage#getNodePrototype_Model()
	 * @see eu.quanticol.cgp.model.SpatialModel#getNodePrototypes
	 * @model opposite="nodePrototypes" required="true" transient="false"
	 * @generated
	 */
	SpatialModel getModel();

	/**
	 * Sets the value of the '{@link eu.quanticol.cgp.model.NodePrototype#getModel <em>Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' container reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(SpatialModel value);

	/**
	 * Returns the value of the '<em><b>Colour</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Colour</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Colour</em>' containment reference.
	 * @see #setColour(Colour)
	 * @see eu.quanticol.cgp.model.CGPPackage#getNodePrototype_Colour()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Colour getColour();

	/**
	 * Sets the value of the '{@link eu.quanticol.cgp.model.NodePrototype#getColour <em>Colour</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Colour</em>' containment reference.
	 * @see #getColour()
	 * @generated
	 */
	void setColour(Colour value);

} // NodePrototype
