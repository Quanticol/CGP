/**
 */
package eu.quanticol.cgp.model.tests;

import eu.quanticol.cgp.model.CGPFactory;
import eu.quanticol.cgp.model.ConnectionInstance;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Connection Instance</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConnectionInstanceTest extends TestCase {

	/**
	 * The fixture for this Connection Instance test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectionInstance fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ConnectionInstanceTest.class);
	}

	/**
	 * Constructs a new Connection Instance test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionInstanceTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Connection Instance test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(ConnectionInstance fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Connection Instance test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectionInstance getFixture() {
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
		setFixture(CGPFactory.eINSTANCE.createConnectionInstance());
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

} //ConnectionInstanceTest
