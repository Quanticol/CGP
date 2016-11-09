/**
 */
package eu.quanticol.cgp.model.provider;

import eu.quanticol.cgp.model.util.CGPAdapterFactory;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CGPItemProviderAdapterFactory extends CGPAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CGPItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.quanticol.cgp.model.SpatialModel} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpatialModelItemProvider spatialModelItemProvider;

	/**
	 * This creates an adapter for a {@link eu.quanticol.cgp.model.SpatialModel}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSpatialModelAdapter() {
		if (spatialModelItemProvider == null) {
			spatialModelItemProvider = new SpatialModelItemProvider(this);
		}

		return spatialModelItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.quanticol.cgp.model.NodePrototype} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodePrototypeItemProvider nodePrototypeItemProvider;

	/**
	 * This creates an adapter for a {@link eu.quanticol.cgp.model.NodePrototype}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createNodePrototypeAdapter() {
		if (nodePrototypeItemProvider == null) {
			nodePrototypeItemProvider = new NodePrototypeItemProvider(this);
		}

		return nodePrototypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.quanticol.cgp.model.ComponentPrototype} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentPrototypeItemProvider componentPrototypeItemProvider;

	/**
	 * This creates an adapter for a {@link eu.quanticol.cgp.model.ComponentPrototype}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createComponentPrototypeAdapter() {
		if (componentPrototypeItemProvider == null) {
			componentPrototypeItemProvider = new ComponentPrototypeItemProvider(this);
		}

		return componentPrototypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.quanticol.cgp.model.ConnectionPrototype} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectionPrototypeItemProvider connectionPrototypeItemProvider;

	/**
	 * This creates an adapter for a {@link eu.quanticol.cgp.model.ConnectionPrototype}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createConnectionPrototypeAdapter() {
		if (connectionPrototypeItemProvider == null) {
			connectionPrototypeItemProvider = new ConnectionPrototypeItemProvider(this);
		}

		return connectionPrototypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.quanticol.cgp.model.NodeInstance} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeInstanceItemProvider nodeInstanceItemProvider;

	/**
	 * This creates an adapter for a {@link eu.quanticol.cgp.model.NodeInstance}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createNodeInstanceAdapter() {
		if (nodeInstanceItemProvider == null) {
			nodeInstanceItemProvider = new NodeInstanceItemProvider(this);
		}

		return nodeInstanceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.quanticol.cgp.model.ComponentInstance} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentInstanceItemProvider componentInstanceItemProvider;

	/**
	 * This creates an adapter for a {@link eu.quanticol.cgp.model.ComponentInstance}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createComponentInstanceAdapter() {
		if (componentInstanceItemProvider == null) {
			componentInstanceItemProvider = new ComponentInstanceItemProvider(this);
		}

		return componentInstanceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.quanticol.cgp.model.ConnectionInstance} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectionInstanceItemProvider connectionInstanceItemProvider;

	/**
	 * This creates an adapter for a {@link eu.quanticol.cgp.model.ConnectionInstance}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createConnectionInstanceAdapter() {
		if (connectionInstanceItemProvider == null) {
			connectionInstanceItemProvider = new ConnectionInstanceItemProvider(this);
		}

		return connectionInstanceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link eu.quanticol.cgp.model.State} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StateItemProvider stateItemProvider;

	/**
	 * This creates an adapter for a {@link eu.quanticol.cgp.model.State}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createStateAdapter() {
		if (stateItemProvider == null) {
			stateItemProvider = new StateItemProvider(this);
		}

		return stateItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (spatialModelItemProvider != null) spatialModelItemProvider.dispose();
		if (nodePrototypeItemProvider != null) nodePrototypeItemProvider.dispose();
		if (componentPrototypeItemProvider != null) componentPrototypeItemProvider.dispose();
		if (connectionPrototypeItemProvider != null) connectionPrototypeItemProvider.dispose();
		if (nodeInstanceItemProvider != null) nodeInstanceItemProvider.dispose();
		if (componentInstanceItemProvider != null) componentInstanceItemProvider.dispose();
		if (connectionInstanceItemProvider != null) connectionInstanceItemProvider.dispose();
		if (stateItemProvider != null) stateItemProvider.dispose();
	}

}
