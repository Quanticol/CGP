/**
 */
package eu.quanticol.cgp.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.quanticol.cgp.model.ComponentInstance#getPrototype <em>Prototype</em>}</li>
 * </ul>
 *
 * @see eu.quanticol.cgp.model.CGPPackage#getComponentInstance()
 * @model
 * @generated
 */
public interface ComponentInstance extends LocatedElement {
	/**
	 * Returns the value of the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prototype</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prototype</em>' reference.
	 * @see #setPrototype(ComponentPrototype)
	 * @see eu.quanticol.cgp.model.CGPPackage#getComponentInstance_Prototype()
	 * @model
	 * @generated
	 */
	ComponentPrototype getPrototype();

	/**
	 * Sets the value of the '{@link eu.quanticol.cgp.model.ComponentInstance#getPrototype <em>Prototype</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prototype</em>' reference.
	 * @see #getPrototype()
	 * @generated
	 */
	void setPrototype(ComponentPrototype value);

} // ComponentInstance
