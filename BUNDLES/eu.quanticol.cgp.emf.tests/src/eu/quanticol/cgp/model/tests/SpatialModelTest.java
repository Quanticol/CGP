/**
 */
package eu.quanticol.cgp.model.tests;

import eu.quanticol.cgp.model.CGPFactory;
import eu.quanticol.cgp.model.SpatialModel;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Spatial Model</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class SpatialModelTest extends TestCase {

	/**
	 * The fixture for this Spatial Model test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpatialModel fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(SpatialModelTest.class);
	}

	/**
	 * Constructs a new Spatial Model test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpatialModelTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Spatial Model test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(SpatialModel fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Spatial Model test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpatialModel getFixture() {
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
		setFixture(CGPFactory.eINSTANCE.createSpatialModel());
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

} //SpatialModelTest
