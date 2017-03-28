package eu.quanticol.cgp.model.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;

import eu.quanticol.carma.core.carma.AccessToEdgeValue;
import eu.quanticol.carma.core.carma.ActionGuard;
import eu.quanticol.carma.core.carma.Activity;
import eu.quanticol.carma.core.carma.Addition;
import eu.quanticol.carma.core.carma.And;
import eu.quanticol.carma.core.carma.AtomicFalse;
import eu.quanticol.carma.core.carma.AtomicInteger;
import eu.quanticol.carma.core.carma.AtomicReal;
import eu.quanticol.carma.core.carma.AtomicTrue;
import eu.quanticol.carma.core.carma.AttibuteVarDeclaration;
import eu.quanticol.carma.core.carma.BlockCommand;
import eu.quanticol.carma.core.carma.BooleanType;
import eu.quanticol.carma.core.carma.CarmaFactory;
import eu.quanticol.carma.core.carma.CollectiveBlock;
import eu.quanticol.carma.core.carma.ComponentDefinition;
import eu.quanticol.carma.core.carma.ConnectionBodyCommand;
import eu.quanticol.carma.core.carma.ConnectionDeclaration;
import eu.quanticol.carma.core.carma.CustomType;
import eu.quanticol.carma.core.carma.DirectedEdge;
import eu.quanticol.carma.core.carma.EdgeProperty;
import eu.quanticol.carma.core.carma.EnumCase;
import eu.quanticol.carma.core.carma.EnumDefinition;
import eu.quanticol.carma.core.carma.Environment;
import eu.quanticol.carma.core.carma.Equality;
import eu.quanticol.carma.core.carma.Guard;
import eu.quanticol.carma.core.carma.InitBlock;
import eu.quanticol.carma.core.carma.InputAction;
import eu.quanticol.carma.core.carma.IntegerType;
import eu.quanticol.carma.core.carma.IsIn;
import eu.quanticol.carma.core.carma.LocationType;
import eu.quanticol.carma.core.carma.Model;
import eu.quanticol.carma.core.carma.MyContext;
import eu.quanticol.carma.core.carma.NamedLocationExpression;
import eu.quanticol.carma.core.carma.NamedNode;
import eu.quanticol.carma.core.carma.NodeBodyCommand;
import eu.quanticol.carma.core.carma.Or;
import eu.quanticol.carma.core.carma.OutputAction;
import eu.quanticol.carma.core.carma.ParallelComposition;
import eu.quanticol.carma.core.carma.PoSetExpression;
import eu.quanticol.carma.core.carma.ProbabilityBlock;
import eu.quanticol.carma.core.carma.ProcessExpressionAction;
import eu.quanticol.carma.core.carma.ProcessExpressionChoice;
import eu.quanticol.carma.core.carma.ProcessExpressionGuard;
import eu.quanticol.carma.core.carma.ProcessExpressionKill;
import eu.quanticol.carma.core.carma.ProcessExpressionReference;
import eu.quanticol.carma.core.carma.ProcessReference;
import eu.quanticol.carma.core.carma.ProcessState;
import eu.quanticol.carma.core.carma.ProcessType;
import eu.quanticol.carma.core.carma.ProcessesBlock;
import eu.quanticol.carma.core.carma.RateBlock;
import eu.quanticol.carma.core.carma.Reference;
import eu.quanticol.carma.core.carma.ReturnCommand;
import eu.quanticol.carma.core.carma.SpaceDefinition;
import eu.quanticol.carma.core.carma.StoreAttribute;
import eu.quanticol.carma.core.carma.StoreBlock;
import eu.quanticol.carma.core.carma.Subtraction;
import eu.quanticol.carma.core.carma.SystemDefinition;
import eu.quanticol.carma.core.carma.UniverseElement;
import eu.quanticol.carma.core.carma.UntypedVariable;
import eu.quanticol.carma.core.carma.Update;
import eu.quanticol.carma.core.carma.UpdateAssignment;
import eu.quanticol.carma.core.carma.UpdateBlock;
import eu.quanticol.carma.core.carma.Variable;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.ConnectionInstance;
import eu.quanticol.cgp.model.ConnectionPrototype;
import eu.quanticol.cgp.model.LocatedElement;
import eu.quanticol.cgp.model.NodeInstance;
import eu.quanticol.cgp.model.NodePrototype;
import eu.quanticol.cgp.model.SpatialModel;
import eu.quanticol.cgp.model.State;

public class CarmaCoreToCGPRevised implements CGPModelAdaptor {


	SpatialModel sm;
	
	
	public CarmaCoreToCGPRevised(SpatialModel sm) {
		super();
		this.sm = sm;
	}

	@Override
	public Model getCarmaModel() {
		
		return spatialModelToModel(sm);
	}
	
	private Model spatialModelToModel(SpatialModel sm) {
		Model m = CarmaFactory.eINSTANCE.createModel();

		EnumDefinition nodeEnum = createNodeEnumDefinition();
		m.getElements().add(nodeEnum);
		EnumCase noneType = CarmaFactory.eINSTANCE.createEnumCase();
		noneType.setName("NONE");
		nodeEnum.getValues().add(noneType);
       
		for (NodePrototype np : sm.getNodePrototypes()) {
			ComponentDefinition cd = nodePrototypetoCarma(np, nodeEnum);
			m.getElements().add(cd);
		}

		EList<EnumCase> nodeEnumCases = nodeEnum.getValues();

		List<ConnectionInstance> connectionInstances = new ArrayList<>();
		List<NodeInstance> nodeInstances = new ArrayList<>();

		for (LocatedElement le : sm.getLocatedElements()) {
			if (le instanceof NodeInstance) {
				NodeInstance ni = (NodeInstance) le;
				nodeInstances.add(ni);
			}
		}

		for (ConnectionInstance ci : sm.getConnectionInstances()) {
			connectionInstances.add(ci);
		}

		SpaceDefinition space = createSpaceDefinition(nodeInstances, connectionInstances);
		m.getElements().add(space);

		Set<EdgeProperty> edgeProperties = new HashSet<EdgeProperty>();

		for (ConnectionBodyCommand cd : space.getEdges()) {
			if (cd instanceof ConnectionDeclaration) {
				ConnectionDeclaration connectionDeclaration = (ConnectionDeclaration) cd;
				for (EdgeProperty ep : connectionDeclaration.getEdgeProperties()) {
					edgeProperties.add(ep);

				}
			}

		}


		for (ComponentPrototype cp : sm.getComponentPrototypes()) {
			ComponentDefinition cd = mobileComponenttoCarma(cp, edgeProperties, nodeEnumCases, nodeEnum);
			m.getElements().add(cd);
		}

		SystemDefinition systemDefinition = CarmaFactory.eINSTANCE.createSystemDefinition();
		
		CollectiveBlock collectiveBlock = CarmaFactory.eINSTANCE.createCollectiveBlock();
		Environment environment = CarmaFactory.eINSTANCE.createEnvironment();
		systemDefinition.setCollective(collectiveBlock);
		systemDefinition.setEnvironment(environment);
		systemDefinition.setName("System");
		systemDefinition.setSpace(space);
		
		StoreBlock storeBlock = CarmaFactory.eINSTANCE.createStoreBlock();
		ProbabilityBlock probabilityBlock = CarmaFactory.eINSTANCE.createProbabilityBlock();
		RateBlock rateBlock = CarmaFactory.eINSTANCE.createRateBlock();
		UpdateBlock updateBlock = CarmaFactory.eINSTANCE.createUpdateBlock();
		
		environment.setProbabilityBlock(probabilityBlock);
		environment.setRateBlock(rateBlock);
		environment.setStore(storeBlock);
		environment.setUpdateBlock(updateBlock);
		
		BlockCommand probBlockCommand = CarmaFactory.eINSTANCE.createBlockCommand();
		ReturnCommand probBlockReturnCommand = CarmaFactory.eINSTANCE.createReturnCommand();
		AtomicReal atomicRealProbBlock = CarmaFactory.eINSTANCE.createAtomicReal();
		atomicRealProbBlock.setValue(1.0);
		probBlockReturnCommand.setExpression(atomicRealProbBlock);
		probBlockCommand.getCommands().add(probBlockReturnCommand);
		probabilityBlock.setValue(probBlockCommand);
		
		BlockCommand rateBlockCommand = CarmaFactory.eINSTANCE.createBlockCommand();
		ReturnCommand rateBlockReturnCommand = CarmaFactory.eINSTANCE.createReturnCommand();
		AtomicReal atomicRealrateBlock = CarmaFactory.eINSTANCE.createAtomicReal();
		atomicRealrateBlock.setValue(1.0);
		rateBlockReturnCommand.setExpression(atomicRealrateBlock);
		rateBlockCommand.getCommands().add(rateBlockReturnCommand);
		rateBlock.setValue(rateBlockCommand);
		
		m.getElements().add(systemDefinition);

		return m;
	}

	private static SpaceDefinition createSpaceDefinition(List<NodeInstance> nodeInstances,
			List<ConnectionInstance> connections) {

		SpaceDefinition sd = CarmaFactory.eINSTANCE.createSpaceDefinition();

		Set<String> availableEdgeProtypes = new HashSet<>();

		for (ConnectionInstance cinstance : connections) {
			availableEdgeProtypes.add(cinstance.getPrototype().getName());
		}

		sd.setName("Autogenerated");

		// UniverseElement id = CarmaFactory.eINSTANCE.createUniverseElement();
		UniverseElement x = CarmaFactory.eINSTANCE.createUniverseElement();
		UniverseElement y = CarmaFactory.eINSTANCE.createUniverseElement();

		// IntegerType idType = CarmaFactory.eINSTANCE.createIntegerType();
		IntegerType xType = CarmaFactory.eINSTANCE.createIntegerType();
		IntegerType yType = CarmaFactory.eINSTANCE.createIntegerType();

		// id.setType(idType);
		x.setType(xType);
		y.setType(yType);

		// sd.getUniverse().add(id);
		sd.getUniverse().add(x);
		sd.getUniverse().add(y);

		// int nodeCounter = 0;
		for (NodeInstance ni : nodeInstances) {
			String nodeName = ni.getPrototype().getName();
			int nodeX = ni.getX();
			int nodeY = ni.getY();
			NamedNode nnode = CarmaFactory.eINSTANCE.createNamedNode();
			nnode.setName(nodeName);

			// AtomicInteger idValue =
			// CarmaFactory.eINSTANCE.createAtomicInteger();
			// idValue.setValue(nodeCounter);
			AtomicInteger xValue = CarmaFactory.eINSTANCE.createAtomicInteger();
			xValue.setValue(nodeX);
			AtomicInteger yValue = CarmaFactory.eINSTANCE.createAtomicInteger();
			yValue.setValue(nodeY);

			// nnode.getValues().add(idValue);
			nnode.getValues().add(xValue);
			nnode.getValues().add(yValue);
			sd.getNodes().add(nnode);

			// nodeCounter ++;
		}

		for (ConnectionInstance ci : connections) {

			NodeInstance fromNode = ci.getFrom();

			String fromName = fromNode.getPrototype().getName();
			int fromX = fromNode.getX();
			int fromY = fromNode.getY();

			NodeInstance toNode = ci.getTo();
			String toName = toNode.getPrototype().getName();
			int toX = toNode.getX();
			int toY = toNode.getY();

			NamedNode from = null;
			NamedNode to = null;

			for (NodeBodyCommand nbc : sd.getNodes()) {
				if (nbc instanceof NamedNode) {
					NamedNode namedNode = (NamedNode) nbc;
					if (namedNode.getName().equals(fromName)) {
						if (namedNode.getValues().get(0) instanceof AtomicInteger
								&& namedNode.getValues().get(1) instanceof AtomicInteger) {
							AtomicInteger nodeX = (AtomicInteger) (namedNode.getValues().get(0));
							AtomicInteger nodeY = (AtomicInteger) (namedNode.getValues().get(1));
							if (nodeX.getValue() == fromX && nodeY.getValue() == fromY) {
								from = namedNode;
							}

						}
					}

					if (namedNode.getName().equals(toName)) {
						if (namedNode.getValues().get(0) instanceof AtomicInteger
								&& namedNode.getValues().get(1) instanceof AtomicInteger) {
							AtomicInteger nodeX = (AtomicInteger) (namedNode.getValues().get(0));
							AtomicInteger nodeY = (AtomicInteger) (namedNode.getValues().get(1));
							if (nodeX.getValue() == toX && nodeY.getValue() == toY) {
								to = namedNode;
							}

						}
					}

				}
			}

			ConnectionDeclaration connectionDeclaration = CarmaFactory.eINSTANCE.createConnectionDeclaration();
			DirectedEdge de = CarmaFactory.eINSTANCE.createDirectedEdge();
			connectionDeclaration.setDirection(de);
			NamedLocationExpression fromNLE = CarmaFactory.eINSTANCE.createNamedLocationExpression();
			fromNLE.setRef(from);
			NamedLocationExpression toNLE = CarmaFactory.eINSTANCE.createNamedLocationExpression();
			toNLE.setRef(to);
			connectionDeclaration.setSource(fromNLE);
			connectionDeclaration.setTarget(toNLE);

			String name = ci.getPrototype().getName();

			for (String possibleConnectionName : availableEdgeProtypes) {
				if (name.equals(possibleConnectionName)) {
					EdgeProperty ep = CarmaFactory.eINSTANCE.createEdgeProperty();
					ep.setName(possibleConnectionName);
					AtomicInteger edgeValue = CarmaFactory.eINSTANCE.createAtomicInteger();
					edgeValue.setValue(1);
					ep.setValue(edgeValue);
					connectionDeclaration.getEdgeProperties().add(ep);

				} else {
					EdgeProperty ep = CarmaFactory.eINSTANCE.createEdgeProperty();
					ep.setName(possibleConnectionName);
					AtomicInteger edgeValue = CarmaFactory.eINSTANCE.createAtomicInteger();
					edgeValue.setValue(0);
					ep.setValue(edgeValue);
					connectionDeclaration.getEdgeProperties().add(ep);
				}

			}
			sd.getEdges().add(connectionDeclaration);

		}

		return sd;
	}

	private static EnumDefinition createNodeEnumDefinition() {
		EnumDefinition ed = CarmaFactory.eINSTANCE.createEnumDefinition();
		ed.setName("Node");
		return ed;
	};

	private static ComponentDefinition mobileComponenttoCarma(ComponentPrototype cp, Set<EdgeProperty> edgeProperties,
			EList<EnumCase> enumCases, EnumDefinition enumDefinition) {

		String name = cp.getName();
        System.out.println("OOOOOOOOOOOOO 1");
		ComponentDefinition cd = CarmaFactory.eINSTANCE.createComponentDefinition();
		cd.setName(name);

		Variable startLocationParameter = CarmaFactory.eINSTANCE.createVariable();
		startLocationParameter.setName("start");
		LocationType locationTypeParameterStart = CarmaFactory.eINSTANCE.createLocationType();
		startLocationParameter.setType(locationTypeParameterStart);
		System.out.println("OOOOOOOOOOOOO 2");
		Variable goalLocationParameter = CarmaFactory.eINSTANCE.createVariable();
		goalLocationParameter.setName("goal");
		LocationType locationTypeParameterGoal = CarmaFactory.eINSTANCE.createLocationType();
		goalLocationParameter.setType(locationTypeParameterGoal);

		Variable stime_initTimeParameter = CarmaFactory.eINSTANCE.createVariable();
		stime_initTimeParameter.setName("stime");
		IntegerType timeType = CarmaFactory.eINSTANCE.createIntegerType();
		stime_initTimeParameter.setType(timeType);
		System.out.println("OOOOOOOOOOOOO 3");
		Variable processStartParameter = CarmaFactory.eINSTANCE.createVariable();
		processStartParameter.setName("Z");
		ProcessType processTypeParameter = CarmaFactory.eINSTANCE.createProcessType();
		processStartParameter.setType(processTypeParameter);

		cd.getParameters().add(startLocationParameter);
		cd.getParameters().add(goalLocationParameter);
		cd.getParameters().add(stime_initTimeParameter);
		cd.getParameters().add(processStartParameter);
		System.out.println("OOOOOOOOOOOOO 4");
		StoreBlock sb = CarmaFactory.eINSTANCE.createStoreBlock();
        cd.setStore(sb);
		AttibuteVarDeclaration currentLocationAttributeVD = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
		currentLocationAttributeVD.setName("currentLocation");
		Reference valueReferenceCurrentLocation = CarmaFactory.eINSTANCE.createReference();
		valueReferenceCurrentLocation.setIsCall(false);
		valueReferenceCurrentLocation.setReference(startLocationParameter);
		System.out.println("OOOOOOOOOOOOO 5");
		currentLocationAttributeVD.setValue(valueReferenceCurrentLocation);
		LocationType locationTypeCurrentLocationStore = CarmaFactory.eINSTANCE.createLocationType();
		currentLocationAttributeVD.setType(locationTypeCurrentLocationStore);

		AttibuteVarDeclaration currentNodeTypeVD = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
		currentNodeTypeVD.setName("currentLocationType");
		System.out.println("OOOOOOOOOOOOO 6");
		EnumCase noneEnumCase = null;
		for (EnumCase enumCase : enumCases) {
			if (enumCase.getName().equals("NONE")) {
				noneEnumCase = enumCase;
			}
		}
		if (noneEnumCase == null) {
			throw new IllegalStateException("\"NONE\" EnumCase not found.");
		}
		System.out.println("OOOOOOOOOOOOO 7");
		Reference valueReferenceCurrentNodeType = CarmaFactory.eINSTANCE.createReference();
		valueReferenceCurrentNodeType.setIsCall(false);
		valueReferenceCurrentNodeType.setReference(noneEnumCase);
		currentNodeTypeVD.setValue(valueReferenceCurrentNodeType);
		CustomType typeCustomType = CarmaFactory.eINSTANCE.createCustomType();
		typeCustomType.setReference(enumDefinition);
		currentNodeTypeVD.setType(typeCustomType);
		System.out.println("OOOOOOOOOOOOO 8");
		
		AttibuteVarDeclaration goalLocationAttributeVD = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
		goalLocationAttributeVD.setName("goalLocation");
		Reference valueReferenceGoalLocation = CarmaFactory.eINSTANCE.createReference();
		valueReferenceGoalLocation.setIsCall(false);
		valueReferenceGoalLocation.setReference(goalLocationParameter);
		goalLocationAttributeVD.setValue(valueReferenceGoalLocation);
		LocationType locationTypeGoalLocationStore = CarmaFactory.eINSTANCE.createLocationType();
		goalLocationAttributeVD.setType(locationTypeGoalLocationStore);

		System.out.println("OOOOOOOOOOOOO 9");
		AttibuteVarDeclaration nextLocationAttributeVD = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
		nextLocationAttributeVD.setName("nextLocation");
		Reference valueReferenceNextLocation = CarmaFactory.eINSTANCE.createReference();
		valueReferenceNextLocation.setIsCall(false);
		valueReferenceNextLocation.setReference(startLocationParameter);
		nextLocationAttributeVD.setValue(valueReferenceNextLocation);
		LocationType locationTypeNextLocationStore = CarmaFactory.eINSTANCE.createLocationType();
		nextLocationAttributeVD.setType(locationTypeNextLocationStore);

		System.out.println("OOOOOOOOOOOOO 10");
		
		AttibuteVarDeclaration previousLocationAttributeVD = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
		previousLocationAttributeVD.setName("previousLocation");
		Reference valueReferencePreviousLocation = CarmaFactory.eINSTANCE.createReference();
		valueReferencePreviousLocation.setIsCall(false);
		valueReferencePreviousLocation.setReference(startLocationParameter);
		previousLocationAttributeVD.setValue(valueReferencePreviousLocation);
		LocationType locationTypePreviousLocationStore = CarmaFactory.eINSTANCE.createLocationType();
		previousLocationAttributeVD.setType(locationTypePreviousLocationStore);

		System.out.println("OOOOOOOOOOOOO 11");
		
		AttibuteVarDeclaration stimeAttributeVD = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
		stimeAttributeVD.setName("stime");
		Reference valueReferenceStime = CarmaFactory.eINSTANCE.createReference();
		valueReferenceStime.setIsCall(false);
		valueReferenceStime.setReference(stime_initTimeParameter);
		stimeAttributeVD.setValue(valueReferenceStime);
		IntegerType timeTypeAttributeVD = CarmaFactory.eINSTANCE.createIntegerType();
		stimeAttributeVD.setType(timeTypeAttributeVD);

		System.out.println("OOOOOOOOOOOOO 12");
		
		AttibuteVarDeclaration isCompAttributeVD = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
		isCompAttributeVD.setName("is" + name);
		AtomicTrue atrue = CarmaFactory.eINSTANCE.createAtomicTrue();
		isCompAttributeVD.setValue(atrue);
		BooleanType booleanTypeAttributeVD = CarmaFactory.eINSTANCE.createBooleanType();
		isCompAttributeVD.setType(booleanTypeAttributeVD);

		System.out.println("OOOOOOOOOOOOO 13");
		
		sb.getAttributes().add(isCompAttributeVD);
		sb.getAttributes().add(currentLocationAttributeVD);
		sb.getAttributes().add(goalLocationAttributeVD);
		sb.getAttributes().add(nextLocationAttributeVD);
		sb.getAttributes().add(previousLocationAttributeVD);
		sb.getAttributes().add(stimeAttributeVD);
		sb.getAttributes().add(currentNodeTypeVD);
		System.out.println("OOOOOOOOOOOOO 14");
		
		cd.setStore(sb);

		Map<String, EnumCase> nodeLabels = new HashMap<>();
		for (EnumCase ec : enumCases) {
			nodeLabels.put(ec.getName(), ec);
		}

		System.out.println("OOOOOOOOOOOOO 15");
		
		List<ProcessState> allProcessStates = new ArrayList<>();

		for (State s : cp.getStates()) {
//
//			String stateName = s.getName();
//
//			EList<ConnectionPrototype> connections = s.getAllowedConnections();
//			List<String> connectionNames = new ArrayList<>();
//			for (ConnectionPrototype connectionPrototype : connections) {
//				connectionNames.add(connectionPrototype.getName());
//			}
//
//			EList<NodePrototype> nodes = s.getAllowedNodes();
//			List<String> nodeNames = new ArrayList<>();
//			for (NodePrototype nodePrototype : nodes) {
//				nodeNames.add(nodePrototype.getName());
//			}
//
//			// READY TO CHOOSE
//			// --------------------------------------------------
//
//			ProcessState psReadyToChoose = CarmaFactory.eINSTANCE.createProcessState();
//			psReadyToChoose.setName(stateName + "_ReadyToChoose");
//			ProcessExpressionAction processExpressionActionChoose = CarmaFactory.eINSTANCE
//					.createProcessExpressionAction();
//			InputAction inputActionChoose = CarmaFactory.eINSTANCE.createInputAction();
//			Activity activityChoose = CarmaFactory.eINSTANCE.createActivity();
//			activityChoose.setName("choose");
//			activityChoose.setIsBroadacst(true);
//			ActionGuard guardChoose = CarmaFactory.eINSTANCE.createActionGuard();
//
//			// Isin expression for postset
//			IsIn isinPostset = CarmaFactory.eINSTANCE.createIsIn();
//			Reference referenceToInputLocation = CarmaFactory.eINSTANCE.createReference();
//			referenceToInputLocation.setIsCall(false);
//			UntypedVariable uvNodeLocation = CarmaFactory.eINSTANCE.createUntypedVariable();
//			uvNodeLocation.setName("nodeLocation");
//			referenceToInputLocation.setReference(uvNodeLocation);
//			isinPostset.setLeft(referenceToInputLocation);
//			PoSetExpression poSetExpression = CarmaFactory.eINSTANCE.createPoSetExpression();
//			MyContext myContextCurrentLocation = CarmaFactory.eINSTANCE.createMyContext();
//			StoreAttribute saCurrentLocation = CarmaFactory.eINSTANCE.createStoreAttribute();
//			saCurrentLocation.setReference(currentLocationAttributeVD);
//			myContextCurrentLocation.setAttribute(saCurrentLocation);
//			poSetExpression.setSource(myContextCurrentLocation);
//			isinPostset.setRight(poSetExpression);
//
//			Set<String> currentStateAllowedEdges = new HashSet<>();
//			for (ConnectionPrototype connP : s.getAllowedConnections()) {
//				currentStateAllowedEdges.add(connP.getName());
//			}
//
//			// Isin expressions for edge property values
//			List<IsIn> edgePropertyChecks = new ArrayList<IsIn>();
//			for (EdgeProperty ep : edgeProperties) {
//
//				if (currentStateAllowedEdges.contains(ep.getName())) {
//					IsIn isinEdgePropertyValues = CarmaFactory.eINSTANCE.createIsIn();
//
//					AccessToEdgeValue accessToEdgeValue = CarmaFactory.eINSTANCE.createAccessToEdgeValue();
//					accessToEdgeValue.setLabel(ep);
//					Reference currentLocationRef = CarmaFactory.eINSTANCE.createReference();
//					currentLocationRef.setIsCall(false);
//					currentLocationRef.setReference(currentLocationAttributeVD);
//					accessToEdgeValue.setSrc(currentLocationRef);
//
//					Reference prospectiveNodeLocationRef = CarmaFactory.eINSTANCE.createReference();
//					prospectiveNodeLocationRef.setIsCall(false);
//					prospectiveNodeLocationRef.setReference(uvNodeLocation);
//					accessToEdgeValue.setTrg(prospectiveNodeLocationRef);
//
//					AtomicInteger aiEdgeValue = CarmaFactory.eINSTANCE.createAtomicInteger();
//					aiEdgeValue.setValue(1);
//
//					isinEdgePropertyValues.setLeft(aiEdgeValue);
//					isinEdgePropertyValues.setRight(accessToEdgeValue);
//
//					edgePropertyChecks.add(isinEdgePropertyValues);
//
//				}
//			}
//
//			Or outermostOr = CarmaFactory.eINSTANCE.createOr();
//
//			for (IsIn isinToAddToOr : edgePropertyChecks) {
//				Or newOr = CarmaFactory.eINSTANCE.createOr();
//				newOr.setRight(outermostOr);
//				newOr.setLeft(isinToAddToOr);
//				outermostOr = newOr;
//			}
//
//			And nodeAndEdgeChecks = CarmaFactory.eINSTANCE.createAnd();
//			nodeAndEdgeChecks.setLeft(isinPostset);
//			nodeAndEdgeChecks.setRight(outermostOr);
//
//			// Update
//
//			Update updateReadyToChoose = CarmaFactory.eINSTANCE.createUpdate();
//
//			UpdateAssignment updateAssignmentChoose = CarmaFactory.eINSTANCE.createUpdateAssignment();
//			MyContext myContextNextLocationChoose = CarmaFactory.eINSTANCE.createMyContext();
//
//			StoreAttribute storeAttributeNextLocation = CarmaFactory.eINSTANCE.createStoreAttribute();
//			storeAttributeNextLocation.setReference(nextLocationAttributeVD);
//			myContextNextLocationChoose.setAttribute(storeAttributeNextLocation);
//			updateAssignmentChoose.setTarget(myContextNextLocationChoose);
//			Reference referenceToInputLocationUpdateChoose = CarmaFactory.eINSTANCE.createReference();
//			referenceToInputLocationUpdateChoose.setIsCall(false);
//			referenceToInputLocationUpdateChoose.setReference(uvNodeLocation);
//			updateAssignmentChoose.setExpression(referenceToInputLocationUpdateChoose);
//			updateReadyToChoose.getUpdateAssignment().add(updateAssignmentChoose);
//
//			inputActionChoose.setUpdate(updateReadyToChoose);
//			// End: Update
//
//			guardChoose.setGuard(nodeAndEdgeChecks);
//			activityChoose.setPredicate(guardChoose);
//			inputActionChoose.setActivity(activityChoose);
//
//			processExpressionActionChoose.setAction(inputActionChoose);
//
//			psReadyToChoose.setProcessExpression(processExpressionActionChoose);
//
//			// END : READY TO CHOOSE
//			// --------------------------------------------------
//
//			// READYTOMOVE
//
//			ProcessState psReadyToMove = CarmaFactory.eINSTANCE.createProcessState();
//			ProcessExpressionReference processReferenceMove = CarmaFactory.eINSTANCE.createProcessExpressionReference();
//			processReferenceMove.setExpression(psReadyToMove);
//			processExpressionActionChoose.setNext(processReferenceMove);
//
//			psReadyToMove.setName(stateName + "_ReadyToMove");
//			ProcessExpressionAction processExpressionActionMove = CarmaFactory.eINSTANCE
//					.createProcessExpressionAction();
//
//			OutputAction outputActionMove = CarmaFactory.eINSTANCE.createOutputAction();
//			Activity activityMove = CarmaFactory.eINSTANCE.createActivity();
//			activityMove.setName("move");
//			activityMove.setIsBroadacst(true);
//			ActionGuard actionGuardMove = CarmaFactory.eINSTANCE.createActionGuard();
//			AtomicFalse atomicFalseActionGuardMove = CarmaFactory.eINSTANCE.createAtomicFalse();
//			actionGuardMove.setGuard(atomicFalseActionGuardMove);
//			activityMove.setPredicate(actionGuardMove);
//
//			outputActionMove.setWithData(true);
//
//			MyContext myContextCurrentLocationMove = CarmaFactory.eINSTANCE.createMyContext();
//			StoreAttribute myContextStoreAttrubuteCurrentLocationMove = CarmaFactory.eINSTANCE.createStoreAttribute();
//			myContextStoreAttrubuteCurrentLocationMove.setReference(currentLocationAttributeVD);
//
//			MyContext myContextNextLocationMove = CarmaFactory.eINSTANCE.createMyContext();
//			StoreAttribute myContextNextLocationStoreAttributeMove = CarmaFactory.eINSTANCE.createStoreAttribute();
//			myContextNextLocationStoreAttributeMove.setReference(nextLocationAttributeVD);
//
//			outputActionMove.getOutputArguments().add(myContextCurrentLocationMove);
//			outputActionMove.getOutputArguments().add(myContextNextLocationMove);
//
//			Update updateMove = CarmaFactory.eINSTANCE.createUpdate();
//
//			UpdateAssignment updateAsignmentMovePreviousLocation = CarmaFactory.eINSTANCE.createUpdateAssignment();
//
//			MyContext myContextPreviousMoveUpdate = CarmaFactory.eINSTANCE.createMyContext();
//			StoreAttribute myContextStoreAttrubutePreviousMoveUpdate = CarmaFactory.eINSTANCE.createStoreAttribute();
//			myContextStoreAttrubutePreviousMoveUpdate.setReference(previousLocationAttributeVD);
//			myContextPreviousMoveUpdate.setAttribute(myContextStoreAttrubutePreviousMoveUpdate);
//
//			MyContext myContextCurrentLocationToPreviousMoveUpdate = CarmaFactory.eINSTANCE.createMyContext();
//			StoreAttribute myContextStoreAttrubuteCurrentLocationToPreviousMoveUpdate = CarmaFactory.eINSTANCE
//					.createStoreAttribute();
//			myContextStoreAttrubuteCurrentLocationToPreviousMoveUpdate.setReference(currentLocationAttributeVD);
//			myContextCurrentLocationToPreviousMoveUpdate
//					.setAttribute(myContextStoreAttrubuteCurrentLocationToPreviousMoveUpdate);
//
//			updateAsignmentMovePreviousLocation.setTarget(myContextPreviousMoveUpdate);
//			updateAsignmentMovePreviousLocation.setExpression(myContextCurrentLocationToPreviousMoveUpdate);
//			updateMove.getUpdateAssignment().add(updateAsignmentMovePreviousLocation);
//
//			UpdateAssignment updateAsignmentMoveCurrentLocation = CarmaFactory.eINSTANCE.createUpdateAssignment();
//
//			MyContext myContextCurrentLocationMoveUpdate = CarmaFactory.eINSTANCE.createMyContext();
//			StoreAttribute myContextStoreAttrubuteCurrentLocationMoveUpdate = CarmaFactory.eINSTANCE
//					.createStoreAttribute();
//			myContextStoreAttrubuteCurrentLocationMoveUpdate.setReference(currentLocationAttributeVD);
//			myContextCurrentLocationMoveUpdate.setAttribute(myContextStoreAttrubuteCurrentLocationMoveUpdate);
//
//			MyContext myContextNextLocationMoveUpdate = CarmaFactory.eINSTANCE.createMyContext();
//			StoreAttribute myContextStoreAttrubuteNextLocationMoveUpdate = CarmaFactory.eINSTANCE
//					.createStoreAttribute();
//			myContextStoreAttrubuteNextLocationMoveUpdate.setReference(nextLocationAttributeVD);
//			myContextNextLocationMoveUpdate.setAttribute(myContextStoreAttrubuteNextLocationMoveUpdate);
//
//			updateAsignmentMoveCurrentLocation.setTarget(myContextCurrentLocationMoveUpdate);
//			updateAsignmentMoveCurrentLocation.setExpression(myContextNextLocationMoveUpdate);
//
//			updateMove.getUpdateAssignment().add(updateAsignmentMoveCurrentLocation);
//
//			outputActionMove.setUpdate(updateMove);
//
//			processExpressionActionMove.setAction(outputActionMove);
//
//			psReadyToMove.setProcessExpression(processExpressionActionMove);
//
//			// END: READY TO MOVE
//
//			// READy TO UNREGISTER
//			ProcessState psReadyToUnregister = CarmaFactory.eINSTANCE.createProcessState();
//			psReadyToUnregister.setName(stateName + "_ReadyToUnregister");
//
//			ProcessExpressionReference processReferenceUnregister = CarmaFactory.eINSTANCE
//					.createProcessExpressionReference();
//			processReferenceUnregister.setExpression(psReadyToUnregister);
//			processExpressionActionMove.setNext(processReferenceUnregister);
//
//			ProcessExpressionAction processExpressionActionReadyToUnregister = CarmaFactory.eINSTANCE
//					.createProcessExpressionAction();
//			psReadyToUnregister.setProcessExpression(processExpressionActionReadyToUnregister);
//			InputAction inputActionUnregister = CarmaFactory.eINSTANCE.createInputAction();
//
//			processExpressionActionReadyToUnregister.setAction(inputActionUnregister);
//			Activity unregisterActivity = CarmaFactory.eINSTANCE.createActivity();
//			inputActionUnregister.setActivity(unregisterActivity);
//
//			UntypedVariable uvNodeLocationUnregister = CarmaFactory.eINSTANCE.createUntypedVariable();
//			uvNodeLocationUnregister.setName("nodeLocation");
//
//			UntypedVariable uvNodeTypeUnregister = CarmaFactory.eINSTANCE.createUntypedVariable();
//			uvNodeTypeUnregister.setName("nodeType");
//
//			unregisterActivity.setName("unregister");
//			unregisterActivity.setIsBroadacst(false);
//			inputActionUnregister.getParameters().add(uvNodeLocationUnregister);
//			inputActionUnregister.getParameters().add(uvNodeTypeUnregister);
//
//			ActionGuard unregisterActionGuard = CarmaFactory.eINSTANCE.createActionGuard();
//
//			Equality unregisterActionGuardEquality = CarmaFactory.eINSTANCE.createEquality();
//			MyContext unregisterMyContextPreviousLocation = CarmaFactory.eINSTANCE.createMyContext();
//			StoreAttribute unregisterMyContextStoreAttributePreviousLocation = CarmaFactory.eINSTANCE
//					.createStoreAttribute();
//			unregisterMyContextStoreAttributePreviousLocation.setReference(previousLocationAttributeVD);
//			unregisterMyContextPreviousLocation.setAttribute(unregisterMyContextStoreAttributePreviousLocation);
//			unregisterActionGuardEquality.setLeft(unregisterMyContextPreviousLocation);
//			Reference nodeLocationReferenceUnregister = CarmaFactory.eINSTANCE.createReference();
//			nodeLocationReferenceUnregister.setIsCall(false);
//			nodeLocationReferenceUnregister.setReference(uvNodeLocationUnregister);
//			unregisterActionGuardEquality.setRight(nodeLocationReferenceUnregister);
//
//			Equality unregisterGuardTypeEquality = CarmaFactory.eINSTANCE.createEquality();
//			MyContext unregisterMyContextType = CarmaFactory.eINSTANCE.createMyContext();
//			StoreAttribute unregisterMyContextStoreAttributeType = CarmaFactory.eINSTANCE.createStoreAttribute();
//			unregisterMyContextStoreAttributeType.setReference(currentNodeTypeVD);
//			unregisterMyContextType.setAttribute(unregisterMyContextStoreAttributeType);
//			unregisterActionGuardEquality.setLeft(unregisterMyContextType);
//			Reference typeReferenceUnregister = CarmaFactory.eINSTANCE.createReference();
//			typeReferenceUnregister.setIsCall(false);
//			typeReferenceUnregister.setReference(uvNodeTypeUnregister);
//			unregisterGuardTypeEquality.setRight(typeReferenceUnregister);
//
//			And andUnregisterGuard = CarmaFactory.eINSTANCE.createAnd();
//			andUnregisterGuard.setLeft(unregisterActionGuardEquality);
//			andUnregisterGuard.setRight(unregisterGuardTypeEquality);
//			unregisterActionGuard.setGuard(andUnregisterGuard);
//
//			unregisterActivity.setPredicate(unregisterActionGuard);
//
//			Update unregisterUpdate = CarmaFactory.eINSTANCE.createUpdate();
//			UpdateAssignment updateAssignmentUnregister = CarmaFactory.eINSTANCE.createUpdateAssignment();
//			MyContext updateUnregisterMyContextCurrentLocationType = CarmaFactory.eINSTANCE.createMyContext();
//			StoreAttribute unregisterMyContextStoreAttributeTypeUpdate = CarmaFactory.eINSTANCE.createStoreAttribute();
//			unregisterMyContextStoreAttributeTypeUpdate.setReference(currentNodeTypeVD);
//			updateUnregisterMyContextCurrentLocationType.setAttribute(unregisterMyContextStoreAttributeTypeUpdate);
//			updateAssignmentUnregister.setTarget(updateUnregisterMyContextCurrentLocationType);
//			Reference referenceToNoneNodeType = CarmaFactory.eINSTANCE.createReference();
//			referenceToNoneNodeType.setIsCall(false);
//			referenceToNoneNodeType.setReference(noneEnumCase);
//			updateAssignmentUnregister.setExpression(referenceToNoneNodeType);
//
//			unregisterUpdate.getUpdateAssignment().add(updateAssignmentUnregister);
//
//			inputActionUnregister.setUpdate(unregisterUpdate);
//
//			// END READY TO UNREGISTER
//
//			// READY TO ARRIVE
//			ProcessState psReadyToArrive = CarmaFactory.eINSTANCE.createProcessState();
//			psReadyToArrive.setName(stateName + "_ReadyToArrive");
//
//			ProcessExpressionReference processReferenceArrive = CarmaFactory.eINSTANCE
//					.createProcessExpressionReference();
//			processReferenceUnregister.setExpression(psReadyToArrive);
//			processExpressionActionReadyToUnregister.setNext(processReferenceArrive);
//
//			ProcessExpressionChoice processExpressionActionReadyToArriveChoice = CarmaFactory.eINSTANCE
//					.createProcessExpressionChoice();
//			psReadyToArrive.setProcessExpression(processExpressionActionReadyToArriveChoice);
//			ProcessExpressionAction processExpressionActionReadyToArriveContinue = CarmaFactory.eINSTANCE
//					.createProcessExpressionAction();
//			ProcessExpressionAction processExpressionActionReadyToArriveArrive = CarmaFactory.eINSTANCE
//					.createProcessExpressionAction();
//
//			processExpressionActionReadyToArriveChoice.setRight(processExpressionActionReadyToArriveContinue);
//
//			ProcessExpressionGuard processExpressionGuardArrive = CarmaFactory.eINSTANCE.createProcessExpressionGuard();
//			Guard guardArrive = CarmaFactory.eINSTANCE.createGuard();
//			Equality guardArriveEquality = CarmaFactory.eINSTANCE.createEquality();
//
//			MyContext myContextCurrentLocationArrive = CarmaFactory.eINSTANCE.createMyContext();
//			StoreAttribute myContextCurrentLocationStoreAttributeArrive = CarmaFactory.eINSTANCE.createStoreAttribute();
//			myContextCurrentLocationStoreAttributeArrive.setReference(currentLocationAttributeVD);
//			myContextCurrentLocationArrive.setAttribute(myContextCurrentLocationStoreAttributeArrive);
//
//			MyContext myContextGoalLocationArrive = CarmaFactory.eINSTANCE.createMyContext();
//			StoreAttribute myContextGoalLocationStoreAttributeArrive = CarmaFactory.eINSTANCE.createStoreAttribute();
//			myContextGoalLocationStoreAttributeArrive.setReference(goalLocationAttributeVD);
//			myContextGoalLocationArrive.setAttribute(myContextGoalLocationStoreAttributeArrive);
//
//			guardArriveEquality.setLeft(myContextCurrentLocationArrive);
//			guardArriveEquality.setRight(myContextGoalLocationArrive);
//
//			guardArrive.setBooleanExpression(guardArriveEquality);
//			processExpressionGuardArrive.setGuard(guardArrive);
//
//			processExpressionActionReadyToArriveChoice.setLeft(processExpressionGuardArrive);
//
//			processExpressionGuardArrive.setExpression(processExpressionActionReadyToArriveArrive);
//
//			OutputAction outputActionArriveArrive = CarmaFactory.eINSTANCE.createOutputAction();
//			outputActionArriveArrive.setWithData(false);
//			ProcessExpressionKill killUponArrivalExpression = CarmaFactory.eINSTANCE.createProcessExpressionKill();
//			processExpressionActionReadyToArriveArrive.setNext(killUponArrivalExpression);
//
//			processExpressionActionReadyToArriveArrive.setAction(outputActionArriveArrive);
//			Activity arriveAndKillActivity = CarmaFactory.eINSTANCE.createActivity();
//			ActionGuard actionGuardKill = CarmaFactory.eINSTANCE.createActionGuard();
//			AtomicFalse atomicFalseagKill = CarmaFactory.eINSTANCE.createAtomicFalse();
//			actionGuardKill.setGuard(atomicFalseagKill);
//			outputActionArriveArrive.setActivity(arriveAndKillActivity);
//			outputActionArriveArrive.setWithData(false);
//			arriveAndKillActivity.setName("arrive");
//			arriveAndKillActivity.setIsBroadacst(true);
//			arriveAndKillActivity.setPredicate(actionGuardKill);
//
//			OutputAction continueNotArriveAction = CarmaFactory.eINSTANCE.createOutputAction();
//			processExpressionActionReadyToArriveContinue.setAction(continueNotArriveAction);
//			continueNotArriveAction.setWithData(false);
//			Activity continueActivity = CarmaFactory.eINSTANCE.createActivity();
//			continueActivity.setIsBroadacst(true);
//			continueActivity.setName("continue");
//			ActionGuard actionGuardContinue = CarmaFactory.eINSTANCE.createActionGuard();
//			AtomicFalse atomicFalseContunue = CarmaFactory.eINSTANCE.createAtomicFalse();
//			actionGuardContinue.setGuard(atomicFalseContunue);
//			continueActivity.setPredicate(actionGuardContinue);
//
//			// END READY TO ARRIVE
//
//			// READY TO REGISTER
//			ProcessState psReadyToRegister = CarmaFactory.eINSTANCE.createProcessState();
//			psReadyToRegister.setName(stateName + "_ReadyToRegister");
//
//			ProcessExpressionReference processReferenceRegister = CarmaFactory.eINSTANCE
//					.createProcessExpressionReference();
//			processReferenceRegister.setExpression(psReadyToRegister);
//			processExpressionActionReadyToArriveContinue.setNext(processReferenceRegister);
//
//			ProcessExpressionAction processExpressionActionReadyToRegister = CarmaFactory.eINSTANCE
//					.createProcessExpressionAction();
//			psReadyToRegister.setProcessExpression(processExpressionActionReadyToRegister);
//
//			InputAction registerAction = CarmaFactory.eINSTANCE.createInputAction();
//			Activity registerActivity = CarmaFactory.eINSTANCE.createActivity();
//			registerActivity.setName("register");
//			registerActivity.setIsBroadacst(false);
//			registerAction.setActivity(registerActivity);
//			UntypedVariable uvNodeLocationRegister = CarmaFactory.eINSTANCE.createUntypedVariable();
//			uvNodeLocationRegister.setName("nodeLocation");
//			UntypedVariable uvNodeTypeRegister = CarmaFactory.eINSTANCE.createUntypedVariable();
//			uvNodeTypeRegister.setName("type");
//			registerAction.getParameters().add(uvNodeLocationRegister);
//			registerAction.getParameters().add(uvNodeTypeRegister);
//
//			ActionGuard registerActionGuard = CarmaFactory.eINSTANCE.createActionGuard();
//
//			MyContext myContextNodeLocationRegister = CarmaFactory.eINSTANCE.createMyContext();
//			StoreAttribute storeAttributeNodeLocationRegister = CarmaFactory.eINSTANCE.createStoreAttribute();
//			myContextNodeLocationRegister.setAttribute(storeAttributeNodeLocationRegister);
//			storeAttributeNodeLocationRegister.setReference(currentLocationAttributeVD);
//
//			MyContext myContextNodeTypeRegister = CarmaFactory.eINSTANCE.createMyContext();
//			StoreAttribute storeAttributeNodeTypeRegister = CarmaFactory.eINSTANCE.createStoreAttribute();
//			myContextNodeTypeRegister.setAttribute(storeAttributeNodeTypeRegister);
//			storeAttributeNodeTypeRegister.setReference(currentNodeTypeVD);
//
//			Equality nodeLocationRegisterEquality = CarmaFactory.eINSTANCE.createEquality();
//			nodeLocationRegisterEquality.setLeft(myContextNodeLocationRegister);
//			Reference uvReferenceNodeLocationRegister = CarmaFactory.eINSTANCE.createReference();
//			uvReferenceNodeLocationRegister.setIsCall(false);
//			uvReferenceNodeLocationRegister.setReference(uvNodeLocationRegister);
//			nodeLocationRegisterEquality.setRight(uvReferenceNodeLocationRegister);
//
//			Equality nodeTypeRegisterEquality = CarmaFactory.eINSTANCE.createEquality();
//			nodeTypeRegisterEquality.setLeft(myContextNodeTypeRegister);
//			Reference uvReferenceTypeRegister = CarmaFactory.eINSTANCE.createReference();
//			uvReferenceTypeRegister.setIsCall(false);
//			uvReferenceTypeRegister.setReference(uvNodeTypeRegister);
//			nodeTypeRegisterEquality.setRight(uvReferenceTypeRegister);
//
//			And registerGuardAnd = CarmaFactory.eINSTANCE.createAnd();
//			registerGuardAnd.setLeft(nodeLocationRegisterEquality);
//			registerGuardAnd.setRight(nodeTypeRegisterEquality);
//
//			registerActionGuard.setGuard(registerGuardAnd);
//			registerActivity.setPredicate(registerActionGuard);
//
//			// END READY TO REGISTER
//
//			// Setup next for ready to choose
//
//			ProcessExpressionReference processReferenceChoose = CarmaFactory.eINSTANCE
//					.createProcessExpressionReference();
//			processReferenceChoose.setExpression(psReadyToChoose);
//			processExpressionActionReadyToRegister.setNext(processReferenceChoose);
//			//
//
//			allProcessStates.add(psReadyToChoose);
//			allProcessStates.add(psReadyToMove);
//			allProcessStates.add(psReadyToUnregister);
//			allProcessStates.add(psReadyToArrive);
//			allProcessStates.add(psReadyToRegister);
//
		}

		System.out.println("OOOOOOOOOOOOO 16");
		
		ProcessesBlock componentProcessesBlock = CarmaFactory.eINSTANCE.createProcessesBlock();

		for (ProcessState processState : allProcessStates) {
			componentProcessesBlock.getProcesses().add(processState);
		}
		
		System.out.println("OOOOOOOOOOOOO 17");
		cd.setProcesses(componentProcessesBlock);

		InitBlock compInitBlock = CarmaFactory.eINSTANCE.createInitBlock();
		ProcessReference processZReference = CarmaFactory.eINSTANCE.createProcessReference();
		processZReference.setExpression(processStartParameter);
		compInitBlock.setInit(processZReference);
		cd.setInitBlock(compInitBlock);
		System.out.println("OOOOOOOOOOOOO 18");
		return cd;
	}

	private static ComponentDefinition nodePrototypetoCarma(NodePrototype np, EnumDefinition ed) {

		String nodeName = np.getName();

		EnumCase ec = CarmaFactory.eINSTANCE.createEnumCase();
		ec.setName(nodeName);

		ComponentDefinition cd = CarmaFactory.eINSTANCE.createComponentDefinition();
		cd.setName(nodeName);

		Variable myLocationVariable = CarmaFactory.eINSTANCE.createVariable();
		myLocationVariable.setName("myLocation");
		LocationType locationTypeParameter = CarmaFactory.eINSTANCE.createLocationType();
		myLocationVariable.setType(locationTypeParameter);

		Variable myCurrentOccupancyVariable = CarmaFactory.eINSTANCE.createVariable();
		IntegerType currentOccupancyParameterType = CarmaFactory.eINSTANCE.createIntegerType();
		myCurrentOccupancyVariable.setName("myCurrentOccupancy");
		myCurrentOccupancyVariable.setType(currentOccupancyParameterType);

		cd.getParameters().add(myLocationVariable);
		cd.getParameters().add(myCurrentOccupancyVariable);

		StoreBlock sb = CarmaFactory.eINSTANCE.createStoreBlock();
        cd.setStore(sb);
		AttibuteVarDeclaration typeAttribute = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
		typeAttribute.setName("type");
		Reference typeReference = CarmaFactory.eINSTANCE.createReference();
		typeReference.setIsCall(false);
		typeReference.setReference(ec);
		typeAttribute.setValue(typeReference);
		CustomType typeCustomType = CarmaFactory.eINSTANCE.createCustomType();
		typeCustomType.setReference(ed);
		typeAttribute.setType(typeCustomType);

		AttibuteVarDeclaration nodeLocationAttribute = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
		nodeLocationAttribute.setName("nodeLocation");
		Reference nodeLocationReference = CarmaFactory.eINSTANCE.createReference();
		nodeLocationReference.setIsCall(false);
		nodeLocationReference.setReference(myLocationVariable);
		nodeLocationAttribute.setValue(nodeLocationReference);
		LocationType locationType = CarmaFactory.eINSTANCE.createLocationType();
		nodeLocationAttribute.setType(locationType);

		AttibuteVarDeclaration registeredPedestrians = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
		registeredPedestrians.setName("registeredPedestrians");
		Reference registeredPedestrianReference = CarmaFactory.eINSTANCE.createReference();
		registeredPedestrianReference.setIsCall(false);
		registeredPedestrianReference.setReference(myCurrentOccupancyVariable);
		registeredPedestrians.setValue(registeredPedestrianReference);
		IntegerType integerType = CarmaFactory.eINSTANCE.createIntegerType();
		registeredPedestrians.setType(integerType);

		sb.getAttributes().add(typeAttribute);
		sb.getAttributes().add(nodeLocationAttribute);

		ProcessesBlock pb = CarmaFactory.eINSTANCE.createProcessesBlock();
        cd.setProcesses(pb);
		// advertisement process

		ProcessState advertisementProcessState = CarmaFactory.eINSTANCE.createProcessState();
		advertisementProcessState.setName("Advertisement");
		ProcessExpressionAction peaAdvertisement = CarmaFactory.eINSTANCE.createProcessExpressionAction();
		OutputAction oaAdvertisement = CarmaFactory.eINSTANCE.createOutputAction();
		oaAdvertisement.setWithData(true);
		Activity activityAdvertisement = CarmaFactory.eINSTANCE.createActivity();
		activityAdvertisement.setName("choose");
		activityAdvertisement.setIsBroadacst(true);
		oaAdvertisement.setActivity(activityAdvertisement);
		peaAdvertisement.setAction(oaAdvertisement);

		MyContext myContextAdvertisementNode = CarmaFactory.eINSTANCE.createMyContext();
		StoreAttribute saAdvertisementNode = CarmaFactory.eINSTANCE.createStoreAttribute();
		saAdvertisementNode.setReference(nodeLocationAttribute);
		myContextAdvertisementNode.setAttribute(saAdvertisementNode);

		MyContext myContextAdvertisementType = CarmaFactory.eINSTANCE.createMyContext();
		StoreAttribute saAdvertisementType = CarmaFactory.eINSTANCE.createStoreAttribute();
		saAdvertisementType.setReference(typeAttribute);
		myContextAdvertisementType.setAttribute(saAdvertisementType);

		oaAdvertisement.getOutputArguments().add(myContextAdvertisementNode);
		oaAdvertisement.getOutputArguments().add(myContextAdvertisementType);

		Update updateAdvertisement = CarmaFactory.eINSTANCE.createUpdate();
		oaAdvertisement.setUpdate(updateAdvertisement);

		// unregistration process
		ProcessState unregistrationProcessState = CarmaFactory.eINSTANCE.createProcessState();
		unregistrationProcessState.setName("Unregistration");
		ProcessExpressionAction peaUnregistration = CarmaFactory.eINSTANCE.createProcessExpressionAction();
		OutputAction oaUnregistration = CarmaFactory.eINSTANCE.createOutputAction();
		oaUnregistration.setWithData(true);
		Activity activityUnregister = CarmaFactory.eINSTANCE.createActivity();
		activityUnregister.setName("unregister");
		activityUnregister.setIsBroadacst(false);
		oaUnregistration.setActivity(activityUnregister);
		peaUnregistration.setAction(oaUnregistration);

		MyContext myContextUnRegistrationNode = CarmaFactory.eINSTANCE.createMyContext();
		StoreAttribute saUnRegistrationNode = CarmaFactory.eINSTANCE.createStoreAttribute();
		saUnRegistrationNode.setReference(nodeLocationAttribute);
		myContextUnRegistrationNode.setAttribute(saUnRegistrationNode);

		MyContext myContextUnRegistrationType = CarmaFactory.eINSTANCE.createMyContext();
		StoreAttribute saUnRegistraoinType = CarmaFactory.eINSTANCE.createStoreAttribute();
		saUnRegistraoinType.setReference(typeAttribute);
		myContextUnRegistrationType.setAttribute(saUnRegistraoinType);

		oaUnregistration.getOutputArguments().add(myContextUnRegistrationNode);
		oaUnregistration.getOutputArguments().add(myContextUnRegistrationType);

		Update updateUnRegistration = CarmaFactory.eINSTANCE.createUpdate();
		UpdateAssignment updateAssignmentUnRegistration = CarmaFactory.eINSTANCE.createUpdateAssignment();
		updateUnRegistration.getUpdateAssignment().add(updateAssignmentUnRegistration);
		StoreAttribute saRegisteredPedestriansUnregistration = CarmaFactory.eINSTANCE.createStoreAttribute();
		saRegisteredPedestriansUnregistration.setReference(registeredPedestrians);
		updateAssignmentUnRegistration.setTarget(saRegisteredPedestriansUnregistration);
		Subtraction updateAdditionUnRegistration = CarmaFactory.eINSTANCE.createSubtraction();
		updateAssignmentUnRegistration.setExpression(updateAdditionUnRegistration);
		Reference updateRegisteredPedestriansReferenceUnregistration = CarmaFactory.eINSTANCE.createReference();
		updateRegisteredPedestriansReferenceUnregistration.setIsCall(false);
		updateRegisteredPedestriansReferenceUnregistration.setReference(registeredPedestrians);
		updateAdditionUnRegistration.setLeft(updateRegisteredPedestriansReferenceUnregistration);
		AtomicInteger aione = CarmaFactory.eINSTANCE.createAtomicInteger();
		aione.setValue(1);
		updateAdditionUnRegistration.setRight(aione);

		oaUnregistration.setUpdate(updateUnRegistration);

		// registration process
		ProcessState registrationProcessState = CarmaFactory.eINSTANCE.createProcessState();
		registrationProcessState.setName("Registration");
		ProcessExpressionAction peaRegistration = CarmaFactory.eINSTANCE.createProcessExpressionAction();
		OutputAction oaRegistration = CarmaFactory.eINSTANCE.createOutputAction();
		oaRegistration.setWithData(true);
		Activity arRegister = CarmaFactory.eINSTANCE.createActivity();
		arRegister.setName("register");
		arRegister.setIsBroadacst(false);
		oaRegistration.setActivity(arRegister);
		peaRegistration.setAction(oaRegistration);

		MyContext myContextRegistrationNode = CarmaFactory.eINSTANCE.createMyContext();
		StoreAttribute saRegistrationNode = CarmaFactory.eINSTANCE.createStoreAttribute();
		saRegistrationNode.setReference(nodeLocationAttribute);
		myContextRegistrationNode.setAttribute(saRegistrationNode);

		MyContext myContextRegistrationType = CarmaFactory.eINSTANCE.createMyContext();
		StoreAttribute saRegistraoinType = CarmaFactory.eINSTANCE.createStoreAttribute();
		saRegistraoinType.setReference(typeAttribute);
		myContextRegistrationType.setAttribute(saRegistraoinType);

		oaRegistration.getOutputArguments().add(myContextRegistrationNode);
		oaRegistration.getOutputArguments().add(myContextRegistrationType);

		Update updateRegistration = CarmaFactory.eINSTANCE.createUpdate();
		UpdateAssignment updateAssignmentRegistration = CarmaFactory.eINSTANCE.createUpdateAssignment();
		updateRegistration.getUpdateAssignment().add(updateAssignmentRegistration);
		StoreAttribute saRegisteredPedestrians = CarmaFactory.eINSTANCE.createStoreAttribute();
		saRegisteredPedestrians.setReference(registeredPedestrians);
		updateAssignmentRegistration.setTarget(saRegisteredPedestrians);
		Addition updateAdditionRegistration = CarmaFactory.eINSTANCE.createAddition();
		updateAssignmentRegistration.setExpression(updateAdditionRegistration);
		Reference updateRegisteredPedestriansReference = CarmaFactory.eINSTANCE.createReference();
		updateRegisteredPedestriansReference.setIsCall(false);
		updateRegisteredPedestriansReference.setReference(registeredPedestrians);
		updateAdditionRegistration.setLeft(updateRegisteredPedestriansReference);
		AtomicInteger aioneR = CarmaFactory.eINSTANCE.createAtomicInteger();
		aioneR.setValue(1);
		updateAdditionRegistration.setRight(aioneR);

		oaRegistration.setUpdate(updateRegistration);

		// Combining all processes in process block
		pb.getProcesses().add(registrationProcessState);
		pb.getProcesses().add(unregistrationProcessState);
		pb.getProcesses().add(advertisementProcessState);

		ProcessExpressionReference penRegistration = CarmaFactory.eINSTANCE.createProcessExpressionReference();
		penRegistration.setExpression(registrationProcessState);
		peaRegistration.setNext(penRegistration);

		ProcessExpressionReference penUnRegistration = CarmaFactory.eINSTANCE.createProcessExpressionReference();
		penUnRegistration.setExpression(unregistrationProcessState);
		peaUnregistration.setNext(penUnRegistration);

		ProcessExpressionReference perAdvertisement = CarmaFactory.eINSTANCE.createProcessExpressionReference();
		perAdvertisement.setExpression(advertisementProcessState);
		peaAdvertisement.setNext(perAdvertisement);

		// Init block
		InitBlock ib = CarmaFactory.eINSTANCE.createInitBlock();
		ParallelComposition pc1 = CarmaFactory.eINSTANCE.createParallelComposition();
		ProcessReference prefAdvertisement = CarmaFactory.eINSTANCE.createProcessReference();
		prefAdvertisement.setExpression(advertisementProcessState);
		ProcessReference prefRegistration = CarmaFactory.eINSTANCE.createProcessReference();
		prefRegistration.setExpression(registrationProcessState);
		ProcessReference prefUnregistration = CarmaFactory.eINSTANCE.createProcessReference();
		prefUnregistration.setExpression(unregistrationProcessState);

		pc1.setLeft(prefRegistration);
		pc1.setRight(prefUnregistration);
		ParallelComposition pc2 = CarmaFactory.eINSTANCE.createParallelComposition();
		pc2.setLeft(pc1);
		pc2.setRight(prefAdvertisement);

		ib.setInit(pc2);

		cd.setInitBlock(ib);

		ed.getValues().add(ec);

		return cd;

	}



}

