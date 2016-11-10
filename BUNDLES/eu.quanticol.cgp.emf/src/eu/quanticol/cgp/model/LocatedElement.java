/**
 */
package eu.quanticol.cgp.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Located Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.quanticol.cgp.model.LocatedElement#getX <em>X</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.LocatedElement#getY <em>Y</em>}</li>
 *   <li>{@link eu.quanticol.cgp.model.LocatedElement#getModel <em>Model</em>}</li>
 * </ul>
 *
 * @see eu.quanticol.cgp.model.CGPPackage#getLocatedElement()
 * @model abstract="true"
 * @generated
 */
public interface LocatedElement extends EObject {
	/**
	 * Returns the value of the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>X</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>X</em>' attribute.
	 * @see #setX(int)
	 * @see eu.quanticol.cgp.model.CGPPackage#getLocatedElement_X()
	 * @model
	 * @generated
	 */
	int getX();

	/**
	 * Sets the value of the '{@link eu.quanticol.cgp.model.LocatedElement#getX <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X</em>' attribute.
	 * @see #getX()
	 * @generated
	 */
	void setX(int value);

	/**
	 * Returns the value of the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Y</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Y</em>' attribute.
	 * @see #setY(int)
	 * @see eu.quanticol.cgp.model.CGPPackage#getLocatedElement_Y()
	 * @model
	 * @generated
	 */
	int getY();

	/**
	 * Sets the value of the '{@link eu.quanticol.cgp.model.LocatedElement#getY <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Y</em>' attribute.
	 * @see #getY()
	 * @generated
	 */
	void setY(int value);

	/**
	 * Returns the value of the '<em><b>Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eu.quanticol.cgp.model.SpatialModel#getLocatedElements <em>Located Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' container reference.
	 * @see #setModel(SpatialModel)
	 * @see eu.quanticol.cgp.model.CGPPackage#getLocatedElement_Model()
	 * @see eu.quanticol.cgp.model.SpatialModel#getLocatedElements
	 * @model opposite="locatedElements" required="true" transient="false"
	 * @generated
	 */
	SpatialModel getModel();

	/**
	 * Sets the value of the '{@link eu.quanticol.cgp.model.LocatedElement#getModel <em>Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' container reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(SpatialModel value);
	
	/**
	 * <!-- begin-user-doc -->
	 * Returns the name associated with a located element.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	String getName();


} // LocatedElement
