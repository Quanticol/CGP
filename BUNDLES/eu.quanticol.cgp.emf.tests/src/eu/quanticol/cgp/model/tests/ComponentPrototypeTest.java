/**
 */
package eu.quanticol.cgp.model.tests;

import eu.quanticol.cgp.model.CGPFactory;
import eu.quanticol.cgp.model.ComponentPrototype;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Component Prototype</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ComponentPrototypeTest extends TestCase {

	/**
	 * The fixture for this Component Prototype test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentPrototype fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ComponentPrototypeTest.class);
	}

	/**
	 * Constructs a new Component Prototype test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentPrototypeTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Component Prototype test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(ComponentPrototype fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Component Prototype test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentPrototype getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(CGPFactory.eINSTANCE.createComponentPrototype());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //ComponentPrototypeTest
