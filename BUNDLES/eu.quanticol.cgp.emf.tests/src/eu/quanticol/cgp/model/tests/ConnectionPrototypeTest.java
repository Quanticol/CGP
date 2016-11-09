/**
 */
package eu.quanticol.cgp.model.tests;

import eu.quanticol.cgp.model.CGPFactory;
import eu.quanticol.cgp.model.ConnectionPrototype;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Connection Prototype</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConnectionPrototypeTest extends TestCase {

	/**
	 * The fixture for this Connection Prototype test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectionPrototype fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ConnectionPrototypeTest.class);
	}

	/**
	 * Constructs a new Connection Prototype test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionPrototypeTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Connection Prototype test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(ConnectionPrototype fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Connection Prototype test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectionPrototype getFixture() {
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
		setFixture(CGPFactory.eINSTANCE.createConnectionPrototype());
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

} //ConnectionPrototypeTest
