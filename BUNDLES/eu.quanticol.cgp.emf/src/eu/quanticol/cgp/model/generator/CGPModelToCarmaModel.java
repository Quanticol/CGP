package eu.quanticol.cgp.model.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import eu.quanticol.carma.core.carma.*;
import eu.quanticol.cgp.model.CGPFactory;
import eu.quanticol.cgp.model.ComponentInstance;
import eu.quanticol.cgp.model.ComponentPrototype;
import eu.quanticol.cgp.model.ConnectionInstance;
import eu.quanticol.cgp.model.ConnectionPrototype;
import eu.quanticol.cgp.model.LocatedElement;
import eu.quanticol.cgp.model.NodeInstance;
import eu.quanticol.cgp.model.NodePrototype;
import eu.quanticol.cgp.model.SpatialModel;
import eu.quanticol.cgp.model.State;

public class CGPModelToCarmaModel implements CGPModelAdaptor {

	private final SpatialModel originalModel;

	private final Model model;

	private final SpaceDefinition spaceDefinition;

	private final SystemDefinition systemDefinition;

	private final List<FunctionDefinition> functionDefinitions;

	private final List<EnumDefinition> enumDefinitions;

	//private final List<ComponentDefinition> componentDefinitions;
	
	private final List<ComponentDefinition> mobileComponentDefinitions;
	private final List<ComponentDefinition> nodeComponentDefinitions;
	private final List<ComponentDefinition> generatorComponentDefinitions;
	
	private FunctionDefinition randomLocationFunction;

	public CGPModelToCarmaModel(SpatialModel originalModel) {
		super();
		this.model = CarmaFactory.eINSTANCE.createModel();
		this.originalModel = originalModel;



		spaceDefinition = CarmaFactory.eINSTANCE.createSpaceDefinition();
		systemDefinition = CarmaFactory.eINSTANCE.createSystemDefinition();
		functionDefinitions = new ArrayList<>();
		enumDefinitions = new ArrayList<>();
		nodeComponentDefinitions = new ArrayList<>();
		mobileComponentDefinitions = new ArrayList<>();
		generatorComponentDefinitions = new ArrayList<>();
	}

	private ComponentDefinition generateNodePrototype(NodePrototype np) {

		String nodeName = np.getName();

		ComponentDefinition cd = CarmaFactory.eINSTANCE.createComponentDefinition();
		cd.setName(nodeName);

		Variable myLocationVariable = CarmaFactory.eINSTANCE.createVariable();
		myLocationVariable.setName("myLoc");
		LocationType locationTypeParameter = CarmaFactory.eINSTANCE.createLocationType();
		myLocationVariable.setType(locationTypeParameter);

		Variable myCurrentOccupancyVariable = CarmaFactory.eINSTANCE.createVariable();
		IntegerType currentOccupancyParameterType = CarmaFactory.eINSTANCE.createIntegerType();
		myCurrentOccupancyVariable.setName("currentOccupancy");
		myCurrentOccupancyVariable.setType(currentOccupancyParameterType);

		cd.getParameters().add(myLocationVariable);
		cd.getParameters().add(myCurrentOccupancyVariable);

		StoreBlock sb = CarmaFactory.eINSTANCE.createStoreBlock();
		cd.setStore(sb);

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

		sb.getAttributes().add(nodeLocationAttribute);
		sb.getAttributes().add(registeredPedestrians);

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

		ProcessExpressionReference perAdvertisement = CarmaFactory.eINSTANCE.createProcessExpressionReference();
		perAdvertisement.setExpression(advertisementProcessState);
		peaAdvertisement.setNext(perAdvertisement);

		advertisementProcessState.setProcessExpression(peaAdvertisement);

		MyContext myContextAdvertisementNode = CarmaFactory.eINSTANCE.createMyContext();
		StoreAttribute saAdvertisementNode = CarmaFactory.eINSTANCE.createStoreAttribute();
		saAdvertisementNode.setReference(nodeLocationAttribute);
		myContextAdvertisementNode.setAttribute(saAdvertisementNode);

		oaAdvertisement.getOutputArguments().add(myContextAdvertisementNode);

		Update updateAdvertisement = CarmaFactory.eINSTANCE.createUpdate();
		oaAdvertisement.setUpdate(updateAdvertisement);

		pb.getProcesses().add(advertisementProcessState);

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

		unregistrationProcessState.setProcessExpression(peaUnregistration);

		MyContext myContextUnRegistrationNode = CarmaFactory.eINSTANCE.createMyContext();
		StoreAttribute saUnRegistrationNode = CarmaFactory.eINSTANCE.createStoreAttribute();
		saUnRegistrationNode.setReference(nodeLocationAttribute);
		myContextUnRegistrationNode.setAttribute(saUnRegistrationNode);

		oaUnregistration.getOutputArguments().add(myContextUnRegistrationNode);

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

		ProcessExpressionReference penUnRegistration = CarmaFactory.eINSTANCE.createProcessExpressionReference();
		penUnRegistration.setExpression(unregistrationProcessState);
		peaUnregistration.setNext(penUnRegistration);

		pb.getProcesses().add(unregistrationProcessState);

		// // registration process
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

		registrationProcessState.setProcessExpression(peaRegistration);

		MyContext myContextRegistrationNode = CarmaFactory.eINSTANCE.createMyContext();
		StoreAttribute saRegistrationNode = CarmaFactory.eINSTANCE.createStoreAttribute();
		saRegistrationNode.setReference(nodeLocationAttribute);
		myContextRegistrationNode.setAttribute(saRegistrationNode);

		oaRegistration.getOutputArguments().add(myContextRegistrationNode);

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
		ProcessExpressionReference penRegistration = CarmaFactory.eINSTANCE.createProcessExpressionReference();
		penRegistration.setExpression(registrationProcessState);
		peaRegistration.setNext(penRegistration);

		pb.getProcesses().add(registrationProcessState);

		// startAssignment

		ProcessState startAssignmentProcessState = CarmaFactory.eINSTANCE.createProcessState();
		startAssignmentProcessState.setName("StartAssignment");
		ProcessExpressionAction peaStartAssignment = CarmaFactory.eINSTANCE.createProcessExpressionAction();
		OutputAction oaStartAssignment = CarmaFactory.eINSTANCE.createOutputAction();
		oaStartAssignment.setWithData(true);
		Activity arStartAssignment = CarmaFactory.eINSTANCE.createActivity();
		arStartAssignment.setName("assignStart");
		arStartAssignment.setIsBroadacst(true);
		oaStartAssignment.setActivity(arStartAssignment);
		peaStartAssignment.setAction(oaStartAssignment);

		startAssignmentProcessState.setProcessExpression(peaStartAssignment);

		MyContext myContextStartAssignmentNode = CarmaFactory.eINSTANCE.createMyContext();
		StoreAttribute saStartAssignmentNode = CarmaFactory.eINSTANCE.createStoreAttribute();
		saStartAssignmentNode.setReference(nodeLocationAttribute);
		myContextStartAssignmentNode.setAttribute(saStartAssignmentNode);

		oaStartAssignment.getOutputArguments().add(myContextStartAssignmentNode);

		Update updateStartAssignment = CarmaFactory.eINSTANCE.createUpdate();
		oaStartAssignment.setUpdate(updateStartAssignment);

		ProcessExpressionReference penStartAssignment = CarmaFactory.eINSTANCE.createProcessExpressionReference();
		penStartAssignment.setExpression(startAssignmentProcessState);
		peaStartAssignment.setNext(penStartAssignment);

		pb.getProcesses().add(startAssignmentProcessState);

		// goalAssignment

		ProcessState goalAssignmentProcessState = CarmaFactory.eINSTANCE.createProcessState();
		goalAssignmentProcessState.setName("GoalAssignment");
		ProcessExpressionAction peaGoalAssignment = CarmaFactory.eINSTANCE.createProcessExpressionAction();
		OutputAction oaGoalAssignment = CarmaFactory.eINSTANCE.createOutputAction();
		oaGoalAssignment.setWithData(true);
		
		Activity arGoalAssignment = CarmaFactory.eINSTANCE.createActivity();
		arGoalAssignment.setName("assignGoal");
		arGoalAssignment.setIsBroadacst(true);
		oaGoalAssignment.setActivity(arGoalAssignment);
		peaGoalAssignment.setAction(oaGoalAssignment);

		goalAssignmentProcessState.setProcessExpression(peaGoalAssignment);

		MyContext myContextGoalAssignmentNode = CarmaFactory.eINSTANCE.createMyContext();
		StoreAttribute saGoalAssignmentNode = CarmaFactory.eINSTANCE.createStoreAttribute();
		saGoalAssignmentNode.setReference(nodeLocationAttribute);
		myContextGoalAssignmentNode.setAttribute(saGoalAssignmentNode);

		oaGoalAssignment.getOutputArguments().add(myContextGoalAssignmentNode);

		Update updateGoalAssignment = CarmaFactory.eINSTANCE.createUpdate();
		oaGoalAssignment.setUpdate(updateGoalAssignment);

		ProcessExpressionReference penGoalAssignment = CarmaFactory.eINSTANCE.createProcessExpressionReference();
		penGoalAssignment.setExpression(goalAssignmentProcessState);
		peaGoalAssignment.setNext(penGoalAssignment);

		pb.getProcesses().add(goalAssignmentProcessState);

		// Init block
		InitBlock ib = CarmaFactory.eINSTANCE.createInitBlock();
		ParallelComposition pc1 = CarmaFactory.eINSTANCE.createParallelComposition();
		ParallelComposition pc2 = CarmaFactory.eINSTANCE.createParallelComposition();

		ProcessReference prefAdvertisement = CarmaFactory.eINSTANCE.createProcessReference();
		prefAdvertisement.setExpression(advertisementProcessState);

		ProcessReference prefRegistration = CarmaFactory.eINSTANCE.createProcessReference();
		prefRegistration.setExpression(registrationProcessState);

		ProcessReference prefUnregistration = CarmaFactory.eINSTANCE.createProcessReference();
		prefUnregistration.setExpression(unregistrationProcessState);

		ProcessReference prefGoalAssignment = CarmaFactory.eINSTANCE.createProcessReference();
		prefGoalAssignment.setExpression(goalAssignmentProcessState);

		ProcessReference prefStartAssignment = CarmaFactory.eINSTANCE.createProcessReference();
		prefStartAssignment.setExpression(startAssignmentProcessState);

		pc2.setLeft(prefStartAssignment);
		pc2.setRight(prefGoalAssignment);

		ParallelComposition pc3 = CarmaFactory.eINSTANCE.createParallelComposition();

		pc1.setLeft(prefRegistration);
		pc1.setRight(prefUnregistration);

		pc3.setLeft(pc1);
		pc3.setRight(pc2);

		ParallelComposition pc4 = CarmaFactory.eINSTANCE.createParallelComposition();

		pc4.setLeft(pc3);
		pc4.setRight(prefAdvertisement);

		ib.setInit(pc4);

		cd.setInitBlock(ib);

		return cd;

	}

	@Override
	public Model getCarmaModel() {
		generateSpaceDefinition();
		generateEnumDefinitions();
		generateFunctionDefinitions();

		generateComponentDefinitions();

		generateSystemDefinition();

		model.getElements().add(this.spaceDefinition);

		for (EnumDefinition ed : this.enumDefinitions) {
			model.getElements().add(ed);
		}

		for (FunctionDefinition fd : this.functionDefinitions) {
			model.getElements().add(fd);
		}

		for (ComponentDefinition cd : this.nodeComponentDefinitions) {
			model.getElements().add(cd);
		}

		for (ComponentDefinition cd : this.mobileComponentDefinitions) {
			model.getElements().add(cd);
		}
		
		for (ComponentDefinition cd : this.generatorComponentDefinitions) {
			model.getElements().add(cd);
		}
		
		
		model.getElements().add(this.systemDefinition);

		return model;
	}

	private void generateSystemDefinition() {

		CollectiveBlock collectiveBlock = CarmaFactory.eINSTANCE.createCollectiveBlock();
		Environment environment = CarmaFactory.eINSTANCE.createEnvironment();
		systemDefinition.setCollective(collectiveBlock);
		systemDefinition.setEnvironment(environment);
		systemDefinition.setName("System");
		systemDefinition.setSpace(this.spaceDefinition);

		StoreBlock storeBlock = CarmaFactory.eINSTANCE.createStoreBlock();
		ProbabilityBlock probabilityBlock = CarmaFactory.eINSTANCE.createProbabilityBlock();
		RateBlock rateBlock = CarmaFactory.eINSTANCE.createRateBlock();
		UpdateBlock updateBlock = CarmaFactory.eINSTANCE.createUpdateBlock();

		for(ComponentDefinition cd : mobileComponentDefinitions){
			String componentName = cd.getName();
			EnvironmentUpdate spawnUpdate = CarmaFactory.eINSTANCE.createEnvironmentUpdate();
			ActionStub spawnStub = CarmaFactory.eINSTANCE.createActionStub();
			spawnStub.setIsBroadcast(true);
			Activity spawnActivity = CarmaFactory.eINSTANCE.createActivity();
			spawnUpdate.setStub(spawnStub);
			spawnActivity.setIsBroadacst(true);
			spawnActivity.setName("spawn" + componentName);
			spawnStub.setActivity(spawnActivity);
			
			ComponentPrototype cgpProtoype = null;
			for(ComponentPrototype cgpP : originalModel.getComponentPrototypes()){
				if(cgpP.getName().equals(cd.getName())){
					cgpProtoype = cgpP;
				}
			}
			if(cgpProtoype == null){
				throw new IllegalStateException("Component prototype " + cd.getName() + " not found in the original model");
			}
			
			
			String initStateName = stateName(cgpProtoype.getInitState(), "ReadyToArrive");
			
		//	initStateName = "S_ReadyToArrive";
			
			ProcessState referredPC = null;
			for(ProcessState pc :cd.getProcesses().getProcesses() ){
				if(pc.getName().equals(initStateName)){
					referredPC = pc;
				}
			}
			
			if(referredPC ==null){
				throw new IllegalStateException("Process " + initStateName + " not found in the component " + cd.getName());
			}
			
			Reference referenceToInitProcess = CarmaFactory.eINSTANCE.createReference();
						
			//attributes of init!!
			
			//startLoc
			SenderContext senderContextStartLoc= CarmaFactory.eINSTANCE.createSenderContext();
			StoreAttribute attrReferenceStartLoc = CarmaFactory.eINSTANCE.createStoreAttribute();
			
			AttibuteVarDeclaration attrVarDeclStartLoc = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
			attrVarDeclStartLoc.setName("startLoc");
			LocationType locTypeStartLoc = CarmaFactory.eINSTANCE.createLocationType();
			attrVarDeclStartLoc.setType(locTypeStartLoc);
			attrReferenceStartLoc.setReference(attrVarDeclStartLoc);
			senderContextStartLoc.setAttribute(attrReferenceStartLoc);
			
			//goalLoc
			SenderContext senderContextGoalLoc= CarmaFactory.eINSTANCE.createSenderContext();
			StoreAttribute attrReferenceGoalLoc = CarmaFactory.eINSTANCE.createStoreAttribute();
			
			AttibuteVarDeclaration attrVarDeclGoalLoc = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
			attrVarDeclGoalLoc.setName("goalLoc");
			LocationType locTypeGoalLoc = CarmaFactory.eINSTANCE.createLocationType();
			attrVarDeclGoalLoc.setType(locTypeGoalLoc);
			attrReferenceGoalLoc.setReference(attrVarDeclGoalLoc);
			senderContextGoalLoc.setAttribute(attrReferenceGoalLoc);
			
			//now
			AtomicNow atomicNowComponentInstantiation = CarmaFactory.eINSTANCE.createAtomicNow();
			
			//InitProcess
			referenceToInitProcess.setReference(referredPC);

			
			
			CollectiveBlock updateSpawnBlock = CarmaFactory.eINSTANCE.createCollectiveBlock();
			ComponentBlockInstantiation cbiMobileComponent = CarmaFactory.eINSTANCE.createComponentBlockInstantiation();
			cbiMobileComponent.setName(cd);
			cbiMobileComponent.getArguments().add(senderContextStartLoc);
			cbiMobileComponent.getArguments().add(senderContextGoalLoc);
			cbiMobileComponent.getArguments().add(atomicNowComponentInstantiation);
			cbiMobileComponent.getArguments().add(referenceToInitProcess);
			
			AtomicInteger population = CarmaFactory.eINSTANCE.createAtomicInteger();
			population.setValue(1);
			cbiMobileComponent.setPopulation(population);
			updateSpawnBlock.getCollective().add(cbiMobileComponent);
			spawnUpdate.setCommand(updateSpawnBlock);
			updateBlock.getUpdates().add(spawnUpdate);
		}
		
		//*****//
		//Collective
		
		for(ComponentDefinition generatorComponent : this.generatorComponentDefinitions){
			ComponentBlockInstantiation cbiGenerator = CarmaFactory.eINSTANCE.createComponentBlockInstantiation();
			cbiGenerator.setName(generatorComponent);
			AtomicInteger generationPopulation = CarmaFactory.eINSTANCE.createAtomicInteger();
			generationPopulation.setValue(1);
			cbiGenerator.setPopulation(generationPopulation);
			collectiveBlock.getCollective().add(cbiGenerator);
		}
		
		for(ComponentDefinition nodeDefinition : this.nodeComponentDefinitions){
			
			for(NodeBodyCommand nbc : this.spaceDefinition.getNodes()){
			    if(!( nbc  instanceof NamedNode)){
			    	throw new IllegalStateException("Unnamed node in collective definition.");
			    }
				NamedNode nn = (NamedNode)nbc;
				if(toNodeStyle(nodeDefinition.getName()).equals(nn.getName())){
					ComponentBlockInstantiation nodeInstantiation = CarmaFactory.eINSTANCE.createComponentBlockInstantiation();
					nodeInstantiation.setName(nodeDefinition);
					
					NodeExpressionOrArrayAccess currentNode = CarmaFactory.eINSTANCE.createNodeExpressionOrArrayAccess();
					

					Reference namedNodeRef = CarmaFactory.eINSTANCE.createReference();
					namedNodeRef.setReference(nn);
					namedNodeRef.setIsCall(false);

					currentNode.setSource(namedNodeRef);

					AtomicInteger valuexGoal = CarmaFactory.eINSTANCE.createAtomicInteger();
					valuexGoal.setValue(((AtomicInteger) nn.getValues().get(0)).getValue());
					AtomicInteger valueyGoal = CarmaFactory.eINSTANCE.createAtomicInteger();
					valueyGoal.setValue(((AtomicInteger) nn.getValues().get(1)).getValue());

					currentNode.getValues().add(valuexGoal);
					currentNode.getValues().add(valueyGoal);
					
					
					
					
					AtomicInteger initialOccupancy = CarmaFactory.eINSTANCE.createAtomicInteger();
					initialOccupancy.setValue(0);
					nodeInstantiation.getArguments().add(currentNode);
					nodeInstantiation.getArguments().add(initialOccupancy);
					
					collectiveBlock.getCollective().add(nodeInstantiation);
					
				}
			}
		}
		
		//component instances
		
		for(LocatedElement li : originalModel.getLocatedElements()){
			if(li instanceof ComponentInstance){
				ComponentInstance ci = (ComponentInstance)li;
				
				ComponentDefinition currentComponentPrototype = null;
				
				for(ComponentDefinition cdCurrent : this.mobileComponentDefinitions){
					if(cdCurrent.getName().equals(ci.getName())){
						currentComponentPrototype = cdCurrent;
					}
				}
				
				if(currentComponentPrototype == null){
					throw new IllegalStateException("Component " + ci.getName() + " not foudn");
				}
				
				int x = ci.getX();
				int y = ci.getY();
				NamedNode nearestNode =  findNearestNode(x,y, 10);
				if(nearestNode == null){
					nearestNode = createNodeOutsideOfGraph(x,y);
				
				}
				
				ComponentBlockInstantiation cbiComponent = CarmaFactory.eINSTANCE.createComponentBlockInstantiation();
				cbiComponent.setName(currentComponentPrototype);
				
				//startLocation
				NodeExpressionOrArrayAccess startLocationNeoaa = CarmaFactory.eINSTANCE.createNodeExpressionOrArrayAccess();
				Reference startLocRef = CarmaFactory.eINSTANCE.createReference();
				startLocRef.setIsCall(false);
				startLocRef.setReference(nearestNode);
				startLocationNeoaa.setSource(startLocRef);
				
				AtomicInteger valuexStart = CarmaFactory.eINSTANCE.createAtomicInteger();
				valuexStart.setValue(((AtomicInteger) nearestNode.getValues().get(0)).getValue());
				AtomicInteger valueyStart = CarmaFactory.eINSTANCE.createAtomicInteger();
				valueyStart.setValue(((AtomicInteger) nearestNode.getValues().get(1)).getValue());
				
				startLocationNeoaa.getValues().add(valuexStart);
				startLocationNeoaa.getValues().add(valueyStart);
				cbiComponent.getArguments().add(startLocationNeoaa);
				
				
				//random loc
				Reference randomLocRef = CarmaFactory.eINSTANCE.createReference();
				randomLocRef.setIsCall(true);
				randomLocRef.setReference(randomLocationFunction);
				cbiComponent.getArguments().add(randomLocRef);
				
				
				//time
				AtomicReal timeAtomicReal = CarmaFactory.eINSTANCE.createAtomicReal();
				timeAtomicReal.setValue(0.0);
				cbiComponent.getArguments().add(timeAtomicReal);
				
				
				//process
				String initStateName = stateName(ci.getPrototype().getInitState(), "ReadyToArrive");
				
				//	initStateName = "S_ReadyToArrive";
					
					ProcessState referredPC = null;
					for(ProcessState pc :currentComponentPrototype.getProcesses().getProcesses() ){
						if(pc.getName().equals(initStateName)){
							referredPC = pc;
						}
					}
					
					if(referredPC ==null){
						throw new IllegalStateException("Process " + initStateName + " not found in the component " + ci.getName());
					}
					
					Reference referenceToInitProcess = CarmaFactory.eINSTANCE.createReference();
				
					referenceToInitProcess.setIsCall(false);
					referenceToInitProcess.setReference(referredPC);
					cbiComponent.getArguments().add(referenceToInitProcess);
					
					//population
					AtomicInteger compPop = CarmaFactory.eINSTANCE.createAtomicInteger();
					compPop.setValue(1);
					cbiComponent.setPopulation(compPop);
					collectiveBlock.getCollective().add(cbiComponent);
			}
		}
		
		//end collective
		
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

		FunctionDefinition assignGoalFunction = null;
		FunctionDefinition assignStartFunction = null;
		FunctionDefinition assignNextFunction = null;
		
		for(FunctionDefinition fd : this.functionDefinitions){
			if(fd.getName().equals("prChooseNext")){
				assignNextFunction = fd;
			}
			else if(fd.getName().equals("prChooseStart")){
				assignStartFunction = fd;
				
			}
			else if(fd.getName().equals("prChooseGoal")){
				assignGoalFunction = fd;
			}
		}
		
		
		//assign Goal
		Probability assignGoalProbability = CarmaFactory.eINSTANCE.createProbability();
		Activity assignGoalActivity = CarmaFactory.eINSTANCE.createActivity();
		assignGoalActivity.setIsBroadacst(true);
		assignGoalActivity.setName("assignGoal");
		assignGoalProbability.setActivity(assignGoalActivity);
		ReturnCommand returnAssignGoalProbability = CarmaFactory.eINSTANCE.createReturnCommand();
		Reference functionAssignGoalProbabilityRef = CarmaFactory.eINSTANCE.createReference();
		functionAssignGoalProbabilityRef.setIsCall(true);
		
		functionAssignGoalProbabilityRef.setReference(assignGoalFunction);
		
		SenderContext senderContextLocAttrAssignGoalProbability = CarmaFactory.eINSTANCE.createSenderContext();
		StoreAttribute attrReferenceAssignGoalProbability = CarmaFactory.eINSTANCE.createStoreAttribute();
		
		AttibuteVarDeclaration attrVarDeclAssignGoalProbability = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
		attrVarDeclAssignGoalProbability.setName("nodeLocation");
		LocationType locTAssignGoalProbability = CarmaFactory.eINSTANCE.createLocationType();
		attrVarDeclAssignGoalProbability.setType(locTAssignGoalProbability);
		attrReferenceAssignGoalProbability.setReference(attrVarDeclAssignGoalProbability);
		
		
		senderContextLocAttrAssignGoalProbability.setAttribute(attrReferenceAssignGoalProbability);
		
		functionAssignGoalProbabilityRef.getArgs().add(senderContextLocAttrAssignGoalProbability);
		AtomicNow atomicNowAssignGoalProbability = CarmaFactory.eINSTANCE.createAtomicNow();
		functionAssignGoalProbabilityRef.getArgs().add(atomicNowAssignGoalProbability);
		
		returnAssignGoalProbability.setExpression(functionAssignGoalProbabilityRef);
		BlockCommand blockCommandGoalProb = CarmaFactory.eINSTANCE.createBlockCommand();
		blockCommandGoalProb.getCommands().add(returnAssignGoalProbability);
		assignGoalProbability.setExpression(blockCommandGoalProb);
		
		//assign Start
		Probability assignStartProbability = CarmaFactory.eINSTANCE.createProbability();
		Activity assignStartActivity = CarmaFactory.eINSTANCE.createActivity();
		assignStartActivity.setIsBroadacst(true);
		assignStartActivity.setName("assignStart");
		assignStartProbability.setActivity(assignStartActivity);
		ReturnCommand returnAssignStartProbability = CarmaFactory.eINSTANCE.createReturnCommand();
		Reference functionAssignStartProbabilityRef = CarmaFactory.eINSTANCE.createReference();
		functionAssignStartProbabilityRef.setIsCall(true);
		
		functionAssignStartProbabilityRef.setReference(assignStartFunction);
		
		SenderContext senderContextLocAttrAssignStartProbability = CarmaFactory.eINSTANCE.createSenderContext();
		StoreAttribute attrReferenceAssignStartProbability = CarmaFactory.eINSTANCE.createStoreAttribute();
		
		AttibuteVarDeclaration attrVarDeclAssignStartProbability = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
		attrVarDeclAssignStartProbability.setName("nodeLocation");
		LocationType locTAssignStartProbability = CarmaFactory.eINSTANCE.createLocationType();
		attrVarDeclAssignStartProbability.setType(locTAssignStartProbability);
		attrReferenceAssignStartProbability.setReference(attrVarDeclAssignStartProbability);
		
		
		senderContextLocAttrAssignStartProbability.setAttribute(attrReferenceAssignStartProbability);
		
		functionAssignStartProbabilityRef.getArgs().add(senderContextLocAttrAssignStartProbability);
		AtomicNow atomicNowAssignStartProbability = CarmaFactory.eINSTANCE.createAtomicNow();
		functionAssignStartProbabilityRef.getArgs().add(atomicNowAssignStartProbability);
		
		returnAssignStartProbability.setExpression(functionAssignStartProbabilityRef);
		BlockCommand blockCommandStartProb = CarmaFactory.eINSTANCE.createBlockCommand();
		blockCommandStartProb.getCommands().add(returnAssignStartProbability);
		assignStartProbability.setExpression(blockCommandStartProb);
		
		
		//choose
				Probability assignChooseProbability = CarmaFactory.eINSTANCE.createProbability();
				Activity assignChooseActivity = CarmaFactory.eINSTANCE.createActivity();
				assignChooseActivity.setIsBroadacst(true);
				assignChooseActivity.setName("choose");
				assignChooseProbability.setActivity(assignChooseActivity);
				ReturnCommand returnAssignChooseProbability = CarmaFactory.eINSTANCE.createReturnCommand();
				Reference functionAssignChooseProbabilityRef = CarmaFactory.eINSTANCE.createReference();
				functionAssignChooseProbabilityRef.setIsCall(true);
				
				functionAssignChooseProbabilityRef.setReference(assignNextFunction);
				
				ReceiverContext receiverContextChooseMyLoc = CarmaFactory.eINSTANCE.createReceiverContext();
				StoreAttribute storeAttributeReceiverChooseMyLoc = CarmaFactory.eINSTANCE.createStoreAttribute();
				AttibuteVarDeclaration attrVarDeclReceiverChooseMyLoc = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
				attrVarDeclReceiverChooseMyLoc.setName("currentLoc");
				LocationType locTypeReceiverChooseMyLoc = CarmaFactory.eINSTANCE.createLocationType();
				attrVarDeclReceiverChooseMyLoc.setType(locTypeReceiverChooseMyLoc);
				storeAttributeReceiverChooseMyLoc.setReference(attrVarDeclReceiverChooseMyLoc);
				receiverContextChooseMyLoc.setAttribute(storeAttributeReceiverChooseMyLoc);
				
				
				ReceiverContext receiverContextChooseGoalLoc = CarmaFactory.eINSTANCE.createReceiverContext();
				StoreAttribute storeAttributeReceiverChooseGoalLoc = CarmaFactory.eINSTANCE.createStoreAttribute();
				AttibuteVarDeclaration attrVarDeclReceiverChooseGoalLoc = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
				attrVarDeclReceiverChooseGoalLoc.setName("goalLoc");
				LocationType locTypeReceiverChooseGoalLoc = CarmaFactory.eINSTANCE.createLocationType();
				attrVarDeclReceiverChooseGoalLoc.setType(locTypeReceiverChooseGoalLoc);
				storeAttributeReceiverChooseGoalLoc.setReference(attrVarDeclReceiverChooseGoalLoc);
				receiverContextChooseGoalLoc.setAttribute(storeAttributeReceiverChooseGoalLoc);
				
				
				SenderContext senderContextLocAttrAssignChooseProbability = CarmaFactory.eINSTANCE.createSenderContext();
				StoreAttribute attrReferenceAssignChooseProbability = CarmaFactory.eINSTANCE.createStoreAttribute();
				
				AttibuteVarDeclaration attrVarDeclAssignChooseProbability = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
				attrVarDeclAssignChooseProbability.setName("nodeLocation");
				LocationType locTAssignChooseProbability = CarmaFactory.eINSTANCE.createLocationType();
				attrVarDeclAssignChooseProbability.setType(locTAssignChooseProbability);
				attrReferenceAssignChooseProbability.setReference(attrVarDeclAssignChooseProbability);
				senderContextLocAttrAssignChooseProbability.setAttribute(attrReferenceAssignChooseProbability);
				
				
				AtomicNow atomicNowAssignChooseProbability = CarmaFactory.eINSTANCE.createAtomicNow();
				
				functionAssignChooseProbabilityRef.getArgs().add(receiverContextChooseMyLoc);
				functionAssignChooseProbabilityRef.getArgs().add(senderContextLocAttrAssignChooseProbability);
				functionAssignChooseProbabilityRef.getArgs().add(receiverContextChooseGoalLoc);
				functionAssignChooseProbabilityRef.getArgs().add(atomicNowAssignChooseProbability);
				
				returnAssignChooseProbability.setExpression(functionAssignChooseProbabilityRef);
				BlockCommand blockCommandChooseProb = CarmaFactory.eINSTANCE.createBlockCommand();
				blockCommandChooseProb.getCommands().add(returnAssignChooseProbability);
				assignChooseProbability.setExpression(blockCommandChooseProb);
				
		//end - choose
		
		probabilityBlock.getProbabilities().add(assignChooseProbability);
		probabilityBlock.getProbabilities().add(assignGoalProbability);
		probabilityBlock.getProbabilities().add(assignStartProbability);
		
		BlockCommand rateBlockCommand = CarmaFactory.eINSTANCE.createBlockCommand();
		ReturnCommand rateBlockReturnCommand = CarmaFactory.eINSTANCE.createReturnCommand();
		AtomicReal atomicRealrateBlock = CarmaFactory.eINSTANCE.createAtomicReal();
		atomicRealrateBlock.setValue(1.0);
		rateBlockReturnCommand.setExpression(atomicRealrateBlock);
		rateBlockCommand.getCommands().add(rateBlockReturnCommand);
		rateBlock.setValue(rateBlockCommand);

		
		
	}

	private NamedNode createNodeOutsideOfGraph(int x, int y) {
		NamedNode newNode = CarmaFactory.eINSTANCE.createNamedNode();
		newNode.setName("OTHER");
		AtomicInteger atomicx = CarmaFactory.eINSTANCE.createAtomicInteger();
		atomicx.setValue(x);
		AtomicInteger atomicy = CarmaFactory.eINSTANCE.createAtomicInteger();
		atomicy.setValue(y);
		newNode.getValues().add(atomicx);
		newNode.getValues().add(atomicy);
		this.spaceDefinition.getNodes().add(newNode);
		return newNode;
	}

	private double distance(int x1, int x2, int y1, int y2){
	return Math.sqrt( (x1-x2)^2 + (y1-y2)^2 );
	}
	
	private NamedNode findNearestNode(int x, int y, int maxDistance) {
       double minDistance = Double.MAX_VALUE;
       NamedNode found = null;
		for(NodeBodyCommand nbc: this.spaceDefinition.getNodes()){
        	if(nbc instanceof NamedNode){
        		NamedNode namedNBC = (NamedNode)nbc;
        		int x2 = ((AtomicInteger)namedNBC.getValues().get(0)).getValue();
        		int y2 = ((AtomicInteger)namedNBC.getValues().get(1)).getValue();
        		double currentDistance = distance(x, x2, y, y2);
        		if(currentDistance < minDistance){
        			minDistance = currentDistance;
        			found = namedNBC;
        		}
        	}
        }
        
        if(minDistance <= maxDistance){
		return found;
        }
        else{
        	return null;
        }
	}

	private FunctionDefinition getNodeTypeFunction(EnumDefinition enumDefinition) {

		FunctionDefinition fd = CarmaFactory.eINSTANCE.createFunctionDefinition();
		fd.setName("getNodeType");
		CustomType ct = CarmaFactory.eINSTANCE.createCustomType();
		ct.setReference(enumDefinition);
		fd.setType(ct);

		LocationType lt = CarmaFactory.eINSTANCE.createLocationType();
		Variable inputVariable = CarmaFactory.eINSTANCE.createVariable();
		inputVariable.setName("nodeLoc");
		inputVariable.setType(lt);
		fd.getParameters().add(inputVariable);

		ArrayList<EnumCase> enumCasesInArray = new ArrayList<>();

		for (EnumCase enumCase : enumDefinition.getValues()) {
			if (enumCase.getName().equals("NULL_TYPE")) {
				enumCasesInArray.add(enumCase);
			}
		}

		for (EnumCase enumCase : enumDefinition.getValues()) {
			if (!enumCase.getName().equals("NULL_TYPE")) {
				enumCasesInArray.add(enumCase);
			}
		}

		FunctionCommand lastCommand = null;

		if (enumCasesInArray.size() == 1) {
			ReturnCommand currentLastCommand = CarmaFactory.eINSTANCE.createReturnCommand();
			lastCommand = currentLastCommand;
			Reference nullReturnReference = CarmaFactory.eINSTANCE.createReference();
			nullReturnReference.setIsCall(false);
			nullReturnReference.setReference(enumCasesInArray.get(0));
			currentLastCommand.setExpression(nullReturnReference);

		} else {

			// 0,1
			IfThenElseCommand currentLastCommand = CarmaFactory.eINSTANCE.createIfThenElseCommand();
			lastCommand = currentLastCommand;

			// 0 null
			BlockCommand nullBlockCommand = CarmaFactory.eINSTANCE.createBlockCommand();
			ReturnCommand nullReturnCommand = CarmaFactory.eINSTANCE.createReturnCommand();
			Reference nullReturnReference = CarmaFactory.eINSTANCE.createReference();
			nullReturnReference.setIsCall(false);
			nullReturnReference.setReference(enumCasesInArray.get(0));
			nullReturnCommand.setExpression(nullReturnReference);
			nullBlockCommand.getCommands().add(nullReturnCommand);
			currentLastCommand.setElseBlock(nullBlockCommand);

			// 1
			FieldAccess fieldAccess = CarmaFactory.eINSTANCE.createFieldAccess();
			LabelDefinition field = null;

			for (LabelDefinition ld : this.spaceDefinition.getLabels()) {

				if (toEnumCase(ld.getName()).equals(enumCasesInArray.get(1).getName())) {
					field = ld;
				}
			}

			fieldAccess.setField(field);

			Reference inputVariableRef = CarmaFactory.eINSTANCE.createReference();
			inputVariableRef.setIsCall(false);
			inputVariableRef.setReference(inputVariable);
			fieldAccess.setSource(inputVariableRef);
			currentLastCommand.setCondition(fieldAccess);

			BlockCommand firstBlockCommand = CarmaFactory.eINSTANCE.createBlockCommand();
			ReturnCommand firstReturnCommand = CarmaFactory.eINSTANCE.createReturnCommand();
			Reference firstReturnReference = CarmaFactory.eINSTANCE.createReference();
			firstReturnReference.setIsCall(false);

			firstReturnReference.setReference(enumCasesInArray.get(1));
			firstReturnCommand.setExpression(firstReturnReference);
			firstBlockCommand.getCommands().add(firstReturnCommand);
			currentLastCommand.setThenBlock(firstBlockCommand);

			for (int i = 2; i < enumCasesInArray.size(); i++) {
				IfThenElseCommand currentCommand = CarmaFactory.eINSTANCE.createIfThenElseCommand();
				//
				FieldAccess currentFieldAccess = CarmaFactory.eINSTANCE.createFieldAccess();
				LabelDefinition currentField = null;
				for (LabelDefinition ld : this.spaceDefinition.getLabels()) {
					if (toEnumCase(ld.getName()).equals(enumCasesInArray.get(i).getName())) {
						currentField = ld;
					}
				}

				if (currentField == null) {
					throw new IllegalStateException("null in currentFIels");
				}

				// System.out.println("111" + currentField);
				//
				currentFieldAccess.setField(currentField);
				// System.out.println("000" +inputVariable);
				Reference currentInputVariableRef = CarmaFactory.eINSTANCE.createReference();
				currentInputVariableRef.setIsCall(false);
				currentInputVariableRef.setReference(inputVariable);
				currentFieldAccess.setSource(currentInputVariableRef);
				//

				currentCommand.setCondition(currentFieldAccess);
				//
				//
				// System.out.println("222" +inputVariable);

				BlockCommand currentReturnBlock = CarmaFactory.eINSTANCE.createBlockCommand();
				ReturnCommand currentReturnCommand = CarmaFactory.eINSTANCE.createReturnCommand();
				Reference currentReturnReference = CarmaFactory.eINSTANCE.createReference();
				currentReturnReference.setIsCall(false);
				currentReturnReference.setReference(enumCasesInArray.get(i));
				currentReturnCommand.setExpression(currentReturnReference);
				currentReturnBlock.getCommands().add(currentReturnCommand);

				// System.out.println(currentReturnReference);

				//
				currentCommand.setThenBlock(currentReturnBlock);

				BlockCommand lastBlockCommand = CarmaFactory.eINSTANCE.createBlockCommand();
				lastBlockCommand.getCommands().add(lastCommand);
				currentCommand.setElseBlock(lastBlockCommand);
				//
				//
				lastCommand = currentCommand;
			}
		}
		BlockCommand bd = CarmaFactory.eINSTANCE.createBlockCommand();

		// System.out.println(lastCommand);

		fd.setBody(bd);
		bd.getCommands().add(lastCommand);

		return fd;
	}

	private void generateComponentDefinitions() {
		generateNodeComponents();
		generateMobileComponents();
		
		for(ComponentDefinition mobileCP : this.mobileComponentDefinitions){
		      generateGeneratorComponent(mobileCP);
		}
	}

	private void generateGeneratorComponent(ComponentDefinition mobileComponent) {
		if (spaceDefinition.getNodes().size() > 0) {

			ComponentDefinition generatorComponentDefinition = CarmaFactory.eINSTANCE.createComponentDefinition();
			generatorComponentDefinition.setName(mobileComponent.getName() + "_Generator");

			// store
			StoreBlock storeBlock = CarmaFactory.eINSTANCE.createStoreBlock();


			// attr goal
			AttibuteVarDeclaration storeGoalLocAttribute = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
			storeGoalLocAttribute.setName("goalLoc");
			LocationType storeGoalLocAttributeType = CarmaFactory.eINSTANCE.createLocationType();
			storeGoalLocAttribute.setType(storeGoalLocAttributeType);

			NodeExpressionOrArrayAccess storeGoalLocNodeExpressionOrArrayAccess = CarmaFactory.eINSTANCE
					.createNodeExpressionOrArrayAccess();
			storeGoalLocAttribute.setValue(storeGoalLocNodeExpressionOrArrayAccess);

			NodeBodyCommand someNodeGoal = spaceDefinition.getNodes().get(0);
			NamedNode someNamedNodeGoal = null;
			if (someNodeGoal instanceof NamedNode) {
				someNamedNodeGoal = (NamedNode) someNodeGoal;
			} else {
				throw new IllegalStateException("Unnamed node in space definition");
			}

			Reference someNamedNodeGoalRef = CarmaFactory.eINSTANCE.createReference();
			someNamedNodeGoalRef.setReference(someNamedNodeGoal);
			someNamedNodeGoalRef.setIsCall(false);

			storeGoalLocNodeExpressionOrArrayAccess.setSource(someNamedNodeGoalRef);

			AtomicInteger valuexGoal = CarmaFactory.eINSTANCE.createAtomicInteger();
			valuexGoal.setValue(((AtomicInteger) someNamedNodeGoal.getValues().get(0)).getValue());
			AtomicInteger valueyGoal = CarmaFactory.eINSTANCE.createAtomicInteger();
			valueyGoal.setValue(((AtomicInteger) someNamedNodeGoal.getValues().get(1)).getValue());

			storeGoalLocNodeExpressionOrArrayAccess.getValues().add(valuexGoal);
			storeGoalLocNodeExpressionOrArrayAccess.getValues().add(valueyGoal);

			// attr start
			AttibuteVarDeclaration storeStartLocAttribute = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
			storeStartLocAttribute.setName("startLoc");
			LocationType storeStartLocAttributeType = CarmaFactory.eINSTANCE.createLocationType();
			storeStartLocAttribute.setType(storeStartLocAttributeType);

			NodeExpressionOrArrayAccess storeStartLocNodeExpressionOrArrayAccess = CarmaFactory.eINSTANCE
					.createNodeExpressionOrArrayAccess();
			storeStartLocAttribute.setValue(storeStartLocNodeExpressionOrArrayAccess);

			NodeBodyCommand someNode = spaceDefinition.getNodes().get(0);
			NamedNode someNamedNode = null;
			if (someNode instanceof NamedNode) {
				someNamedNode = (NamedNode) someNode;
			} else {
				throw new IllegalStateException("Unnamed node in space definition");
			}

			Reference someNamedNodeRef = CarmaFactory.eINSTANCE.createReference();
			someNamedNodeRef.setReference(someNamedNode);
			someNamedNodeRef.setIsCall(false);

			storeStartLocNodeExpressionOrArrayAccess.setSource(someNamedNodeRef);

			AtomicInteger valuex = CarmaFactory.eINSTANCE.createAtomicInteger();
			valuex.setValue(((AtomicInteger) someNamedNode.getValues().get(0)).getValue());
			AtomicInteger valuey = CarmaFactory.eINSTANCE.createAtomicInteger();
			valuey.setValue(((AtomicInteger) someNamedNode.getValues().get(1)).getValue());

			storeStartLocNodeExpressionOrArrayAccess.getValues().add(valuex);
			storeStartLocNodeExpressionOrArrayAccess.getValues().add(valuey);

			storeBlock.getAttributes().add(storeStartLocAttribute);
			storeBlock.getAttributes().add(storeGoalLocAttribute);

			generatorComponentDefinition.setStore(storeBlock);

			// behaviour
			ProcessesBlock processesBlock = CarmaFactory.eINSTANCE.createProcessesBlock();
			generatorComponentDefinition.setProcesses(processesBlock);

			// assign start
			ProcessState assignStartProcessState = CarmaFactory.eINSTANCE.createProcessState();
			assignStartProcessState.setName("ReadyToAssignStart");
			ProcessExpressionAction peaAssignStart = CarmaFactory.eINSTANCE.createProcessExpressionAction();
			InputAction oaAssignStart = CarmaFactory.eINSTANCE.createInputAction();

			UntypedVariable untypedVariableNodeLocation = CarmaFactory.eINSTANCE.createUntypedVariable();
			untypedVariableNodeLocation.setName("nodeLoc");
			oaAssignStart.getParameters().add(untypedVariableNodeLocation);

			Activity activityAssignStart = CarmaFactory.eINSTANCE.createActivity();
			activityAssignStart.setName("assignStart");
			activityAssignStart.setIsBroadacst(true);
			oaAssignStart.setActivity(activityAssignStart);
			peaAssignStart.setAction(oaAssignStart);

			ProcessExpressionReference perAssignStart = CarmaFactory.eINSTANCE.createProcessExpressionReference();
			perAssignStart.setExpression(assignStartProcessState);

			Update updateAssignStart = CarmaFactory.eINSTANCE.createUpdate();
			UpdateAssignment updateAssignmentStartAssignment = CarmaFactory.eINSTANCE.createUpdateAssignment();
			updateAssignStart.getUpdateAssignment().add(updateAssignmentStartAssignment);
			StoreAttribute storeAttributeStartLoc = CarmaFactory.eINSTANCE.createStoreAttribute();
			storeAttributeStartLoc.setReference(storeStartLocAttribute);

			MyContext myContexStartLocation = CarmaFactory.eINSTANCE.createMyContext();
			myContexStartLocation.setAttribute(storeAttributeStartLoc);
			updateAssignmentStartAssignment.setTarget(myContexStartLocation);

			Reference incomingLocReferenceStart = CarmaFactory.eINSTANCE.createReference();
			incomingLocReferenceStart.setIsCall(false);
			incomingLocReferenceStart.setReference(untypedVariableNodeLocation);
			updateAssignmentStartAssignment.setExpression(incomingLocReferenceStart);

			oaAssignStart.setUpdate(updateAssignStart);

			// //assign goal

			ProcessState tessPS = CarmaFactory.eINSTANCE.createProcessState();
			tessPS.setName("ReadyToAssignGoal");

			ProcessExpressionAction peaTEST = CarmaFactory.eINSTANCE.createProcessExpressionAction();
			InputAction iaTest = CarmaFactory.eINSTANCE.createInputAction();

			UntypedVariable untypedVariableNodeLocation2 = CarmaFactory.eINSTANCE.createUntypedVariable();
			untypedVariableNodeLocation2.setName("nodeLoc");
			iaTest.getParameters().add(untypedVariableNodeLocation2);

			Activity activityTest = CarmaFactory.eINSTANCE.createActivity();
			activityTest.setName("assignGoal");
			activityTest.setIsBroadacst(true);
			iaTest.setActivity(activityTest);
			peaTEST.setAction(iaTest);

			tessPS.setProcessExpression(peaTEST);

			Update updateGoal = CarmaFactory.eINSTANCE.createUpdate();
			//UpdateAssignment au = CarmaFactory.eINSTANCE.createUpdateAssignment();

			//StoreAttribute storeAttributeTest = CarmaFactory.eINSTANCE.createStoreAttribute();
			//storeAttributeTest.setReference(testAttr);
			//;



			UpdateAssignment updateAssignmentGoalAssignment = CarmaFactory.eINSTANCE.createUpdateAssignment();

			StoreAttribute storeAttributeGoalLoc = CarmaFactory.eINSTANCE.createStoreAttribute();
			storeAttributeGoalLoc.setReference(storeGoalLocAttribute);

			MyContext myContexGoalLocation = CarmaFactory.eINSTANCE.createMyContext();
			myContexGoalLocation.setAttribute(storeAttributeGoalLoc);
			updateAssignmentGoalAssignment.setTarget(myContexGoalLocation);

			Reference incomingLocReferenceGoal = CarmaFactory.eINSTANCE.createReference();
			incomingLocReferenceGoal.setIsCall(false);
			incomingLocReferenceGoal.setReference(untypedVariableNodeLocation2);
			updateAssignmentGoalAssignment.setExpression(incomingLocReferenceGoal);

			//updateGoal.getUpdateAssignment().add(au);
			updateGoal.getUpdateAssignment().add(updateAssignmentGoalAssignment);
			iaTest.setUpdate(updateGoal);

			// spawn
			ProcessState spawnProcessState = CarmaFactory.eINSTANCE.createProcessState();
			spawnProcessState.setName("ReadyToSpawn");
			ProcessExpressionAction peaSpawn = CarmaFactory.eINSTANCE.createProcessExpressionAction();
			OutputAction oaSpawn = CarmaFactory.eINSTANCE.createOutputAction();
			oaSpawn.setWithData(false);
			Activity activitySpawn = CarmaFactory.eINSTANCE.createActivity();
			activitySpawn.setName("spawn" + mobileComponent.getName());
			activitySpawn.setIsBroadacst(true);
			oaSpawn.setActivity(activitySpawn);
			peaSpawn.setAction(oaSpawn);

			ProcessExpressionReference perSpawn = CarmaFactory.eINSTANCE.createProcessExpressionReference();
			perSpawn.setExpression(spawnProcessState);
            
			
			ActionGuard falseActionGuardSpawn = CarmaFactory.eINSTANCE.createActionGuard();
			AtomicFalse falseForActionGuard = CarmaFactory.eINSTANCE.createAtomicFalse();
			falseActionGuardSpawn.setGuard(falseForActionGuard);
			
			activitySpawn.setPredicate(falseActionGuardSpawn);
			
			ProcessExpressionReference perTest = CarmaFactory.eINSTANCE.createProcessExpressionReference();
			perTest.setExpression(tessPS);
			// peaAssignGoal.setNext(perSpawn);
			peaAssignStart.setNext(perTest);
			peaSpawn.setNext(perAssignStart);
			peaTEST.setNext(perSpawn);

			// assignGoalProcessState.setProcessExpression(peaAssignGoal);
			assignStartProcessState.setProcessExpression(peaAssignStart);
			spawnProcessState.setProcessExpression(peaSpawn);

			//Update updateAdvertisement = CarmaFactory.eINSTANCE.createUpdate();
			// oaAssignGoal.setUpdate(updateAdvertisement);

			// processesBlock.getProcesses().add(assignGoalProcessState);
			processesBlock.getProcesses().add(assignStartProcessState);
			processesBlock.getProcesses().add(tessPS);
			processesBlock.getProcesses().add(spawnProcessState);
			

			InitBlock initBlock = CarmaFactory.eINSTANCE.createInitBlock();
			ProcessReference pr = CarmaFactory.eINSTANCE.createProcessReference();
			pr.setExpression(assignStartProcessState);
			// pr.setExpression(assignGoalProcessState);
			initBlock.setInit(pr);

			generatorComponentDefinition.setInitBlock(initBlock);

			this.generatorComponentDefinitions.add(generatorComponentDefinition);
		}

	}

	private void generateMobileComponents() {
		for (ComponentPrototype cp : originalModel.getComponentPrototypes()) {
			this.mobileComponentDefinitions.add(generateMobileComponentPrototype(cp));
		}

	}

	private String stateName(State cgpState, String mobileProcessStateName) {
        if(cgpState == null){
        	return "_" + mobileProcessStateName;
        }
		String cgpStateName = cgpState.getName();
		return cgpStateName + "_" + mobileProcessStateName;
	}

	private ComponentDefinition generateMobileComponentPrototype(ComponentPrototype cp) {

		String name = cp.getName();
		ComponentDefinition cd = CarmaFactory.eINSTANCE.createComponentDefinition();
		cd.setName(name);

		/***********************************************************************************/
		/*****************
		 * Input parameters
		 ***************************************************/

		Variable startLocationParameter = CarmaFactory.eINSTANCE.createVariable();
		startLocationParameter.setName("start");
		LocationType locationTypeParameterStart = CarmaFactory.eINSTANCE.createLocationType();
		startLocationParameter.setType(locationTypeParameterStart);

		Variable goalLocationParameter = CarmaFactory.eINSTANCE.createVariable();
		goalLocationParameter.setName("goal");
		LocationType locationTypeParameterGoal = CarmaFactory.eINSTANCE.createLocationType();
		goalLocationParameter.setType(locationTypeParameterGoal);

		Variable stime_initTimeParameter = CarmaFactory.eINSTANCE.createVariable();
		stime_initTimeParameter.setName("stime");
		RealType timeType = CarmaFactory.eINSTANCE.createRealType();
		stime_initTimeParameter.setType(timeType);

		Variable processStartParameter = CarmaFactory.eINSTANCE.createVariable();
		processStartParameter.setName("Z");
		ProcessType processTypeParameter = CarmaFactory.eINSTANCE.createProcessType();
		processStartParameter.setType(processTypeParameter);

		cd.getParameters().add(startLocationParameter);
		cd.getParameters().add(goalLocationParameter);
		cd.getParameters().add(stime_initTimeParameter);
		cd.getParameters().add(processStartParameter);

		/***********************************************************************************/
		/***************** Store ***************************************************/

		StoreBlock sb = CarmaFactory.eINSTANCE.createStoreBlock();

		AttibuteVarDeclaration currentLocationAttributeVD = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
		currentLocationAttributeVD.setName("currentLoc");
		Reference valueReferenceCurrentLocation = CarmaFactory.eINSTANCE.createReference();
		valueReferenceCurrentLocation.setIsCall(false);
		valueReferenceCurrentLocation.setReference(startLocationParameter);
		currentLocationAttributeVD.setValue(valueReferenceCurrentLocation);
		LocationType locationTypeCurrentLocationStore = CarmaFactory.eINSTANCE.createLocationType();
		currentLocationAttributeVD.setType(locationTypeCurrentLocationStore);

		AttibuteVarDeclaration goalLocationAttributeVD = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
		goalLocationAttributeVD.setName("goalLoc");
		Reference valueReferenceGoalLocation = CarmaFactory.eINSTANCE.createReference();
		valueReferenceGoalLocation.setIsCall(false);
		valueReferenceGoalLocation.setReference(goalLocationParameter);
		goalLocationAttributeVD.setValue(valueReferenceGoalLocation);
		LocationType locationTypeGoalLocationStore = CarmaFactory.eINSTANCE.createLocationType();
		goalLocationAttributeVD.setType(locationTypeGoalLocationStore);

		AttibuteVarDeclaration nextLocationAttributeVD = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
		nextLocationAttributeVD.setName("nextLoc");
		Reference valueReferenceNextLocation = CarmaFactory.eINSTANCE.createReference();
		valueReferenceNextLocation.setIsCall(false);
		valueReferenceNextLocation.setReference(startLocationParameter);
		nextLocationAttributeVD.setValue(valueReferenceNextLocation);
		LocationType locationTypeNextLocationStore = CarmaFactory.eINSTANCE.createLocationType();
		nextLocationAttributeVD.setType(locationTypeNextLocationStore);

		AttibuteVarDeclaration previousLocationAttributeVD = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
		previousLocationAttributeVD.setName("previousLoc");
		Reference valueReferencePreviousLocation = CarmaFactory.eINSTANCE.createReference();
		valueReferencePreviousLocation.setIsCall(false);
		valueReferencePreviousLocation.setReference(startLocationParameter);
		previousLocationAttributeVD.setValue(valueReferencePreviousLocation);
		LocationType locationTypePreviousLocationStore = CarmaFactory.eINSTANCE.createLocationType();
		previousLocationAttributeVD.setType(locationTypePreviousLocationStore);

		AttibuteVarDeclaration stimeAttributeVD = CarmaFactory.eINSTANCE.createAttibuteVarDeclaration();
		stimeAttributeVD.setName("stime");
		Reference valueReferenceStime = CarmaFactory.eINSTANCE.createReference();
		valueReferenceStime.setIsCall(false);
		valueReferenceStime.setReference(stime_initTimeParameter);
		stimeAttributeVD.setValue(valueReferenceStime);
		RealType timeTypeAttributeVD = CarmaFactory.eINSTANCE.createRealType();
		stimeAttributeVD.setType(timeTypeAttributeVD);

		sb.getAttributes().add(currentLocationAttributeVD);
		sb.getAttributes().add(goalLocationAttributeVD);
		sb.getAttributes().add(nextLocationAttributeVD);
		sb.getAttributes().add(previousLocationAttributeVD);
		sb.getAttributes().add(stimeAttributeVD);
		cd.setStore(sb);

		/***********************************************************************************/
		/***************** Processes ***************************************************/

		ProcessesBlock processBlock = CarmaFactory.eINSTANCE.createProcessesBlock();
		cd.setProcesses(processBlock);

		// Setup: *************************************************************
		List<LabelDefinition> globalLabelDefinitios = this.spaceDefinition.getLabels();

		Set<EdgeProperty> availableEdgeProperties = new HashSet<EdgeProperty>();

		for (ConnectionBodyCommand cbd : this.spaceDefinition.getEdges()) {
			if (cbd instanceof ConnectionDeclaration) {
				ConnectionDeclaration connectionDeclaration = (ConnectionDeclaration) cbd;
				for (EdgeProperty ep : connectionDeclaration.getEdgeProperties()) {
					boolean alreadyContains = false;
					for (EdgeProperty edgeP : availableEdgeProperties) {
						if (edgeP.getName().equals(ep.getName())) {
							alreadyContains = true;
						}
					}

					if (!alreadyContains) {
						availableEdgeProperties.add(ep);
					}

				}
			}

		}
		// ********************************************************************

		List<State> states = cp.getStates();

		for (State currentState : states) {
			/***********************************************************************************/
			/*****************
			 * Processes: ReadyToChoose
			 ***************************************************/

			ProcessState processStateReadyToChoose = CarmaFactory.eINSTANCE.createProcessState();
			ProcessExpressionAction processExpressionActionReadyToChoose = CarmaFactory.eINSTANCE
					.createProcessExpressionAction();
			InputAction inputActionChoose = CarmaFactory.eINSTANCE.createInputAction();
			Activity activityChoose = CarmaFactory.eINSTANCE.createActivity();
			ProcessExpressionReference processExpressionReferenceReadyToChoose = CarmaFactory.eINSTANCE
					.createProcessExpressionReference();

			processStateReadyToChoose.setProcessExpression(processExpressionActionReadyToChoose);
			processStateReadyToChoose.setName(stateName(currentState, "ReadyToChoose"));
			activityChoose.setName("choose");
			activityChoose.setIsBroadacst(true);
			inputActionChoose.setActivity(activityChoose);
			processExpressionActionReadyToChoose.setAction(inputActionChoose);
			processExpressionReferenceReadyToChoose.setExpression(processStateReadyToChoose);
			processBlock.getProcesses().add(processStateReadyToChoose);

			// Input Variable:

			UntypedVariable untypedVariableNodeLocationChoose = CarmaFactory.eINSTANCE.createUntypedVariable();
			untypedVariableNodeLocationChoose.setName("nodeLoc");
			inputActionChoose.getParameters().add(untypedVariableNodeLocationChoose);

			Reference referenceToInputLocationChoose = CarmaFactory.eINSTANCE.createReference();
			referenceToInputLocationChoose.setIsCall(false);
			referenceToInputLocationChoose.setReference(untypedVariableNodeLocationChoose);

			// Action guard:

			List<LabelDefinition> allowedNodesChoose = new ArrayList<>();
			for (LabelDefinition labelDefinition : globalLabelDefinitios) {
				for (NodePrototype nodePrototype : currentState.getAllowedNodes()) {
					if (labelDefinition.getName().equals(nodePrototype.getName())) {
						allowedNodesChoose.add(labelDefinition);
					}
				}
			}

			List<EdgeProperty> allowedEdgePropertiesChoose = new ArrayList<>();
			for (EdgeProperty edgeProperty : availableEdgeProperties) {
				for (ConnectionPrototype connectionPrototype : currentState.getAllowedConnections()) {
					if (edgeProperty.getName().equals(connectionPrototype.getName())) {
						allowedEdgePropertiesChoose.add(edgeProperty);
					}
				}
			}

			ActionGuard actionGuardChoose = CarmaFactory.eINSTANCE.createActionGuard();
			AtomicFalse atomicFalseChoose = CarmaFactory.eINSTANCE.createAtomicFalse();
			Expression guardExpressionChoose = atomicFalseChoose;

			if (allowedNodesChoose.size() == 0 || allowedEdgePropertiesChoose.size() == 0) {
				AtomicFalse atomicFalse00Choose = CarmaFactory.eINSTANCE.createAtomicFalse();
				guardExpressionChoose = atomicFalse00Choose;
			} else {

				And outerAndChoose = CarmaFactory.eINSTANCE.createAnd();
				guardExpressionChoose = outerAndChoose;

				Expression nodeAllowanceGuardPartChoose = null;
				Expression connectionAllowanceGuardPartChoose = null;

				// NODES
				if (allowedNodesChoose.size() > 1) {

					Or nodeAllowedOrChoose = CarmaFactory.eINSTANCE.createOr();

					FieldAccess fieldAccess0Choose = CarmaFactory.eINSTANCE.createFieldAccess();
					fieldAccess0Choose.setField(allowedNodesChoose.get(0));

					Reference referenceToInputLocation0Choose = CarmaFactory.eINSTANCE.createReference();
					referenceToInputLocation0Choose.setIsCall(false);
					referenceToInputLocation0Choose.setReference(untypedVariableNodeLocationChoose);
					fieldAccess0Choose.setSource(referenceToInputLocation0Choose);

					FieldAccess fieldAccess1Choose = CarmaFactory.eINSTANCE.createFieldAccess();
					fieldAccess1Choose.setField(allowedNodesChoose.get(1));

					Reference referenceToInputLocation1Choose = CarmaFactory.eINSTANCE.createReference();
					referenceToInputLocation1Choose.setIsCall(false);
					referenceToInputLocation1Choose.setReference(untypedVariableNodeLocationChoose);
					fieldAccess1Choose.setSource(referenceToInputLocation1Choose);

					nodeAllowedOrChoose.setLeft(fieldAccess0Choose);
					nodeAllowedOrChoose.setRight(fieldAccess1Choose);

					for (int i = 2; i < allowedNodesChoose.size(); i++) {
						Or nodeAllowedOrInnerChoose = CarmaFactory.eINSTANCE.createOr();
						nodeAllowedOrInnerChoose.setRight(nodeAllowedOrChoose);
						FieldAccess fieldAccessiChoose = CarmaFactory.eINSTANCE.createFieldAccess();
						fieldAccessiChoose.setField(allowedNodesChoose.get(i));

						Reference referenceToInputLocationiChoose = CarmaFactory.eINSTANCE.createReference();
						referenceToInputLocationiChoose.setIsCall(false);
						referenceToInputLocationiChoose.setReference(untypedVariableNodeLocationChoose);

						fieldAccessiChoose.setSource(referenceToInputLocationiChoose);

						nodeAllowedOrInnerChoose.setLeft(fieldAccessiChoose);
						nodeAllowedOrChoose = nodeAllowedOrInnerChoose;
					}

					nodeAllowanceGuardPartChoose = nodeAllowedOrChoose;

				} else if (allowedNodesChoose.size() == 1) {

					FieldAccess fieldAccess0Choose = CarmaFactory.eINSTANCE.createFieldAccess();
					fieldAccess0Choose.setField(allowedNodesChoose.get(0));
					fieldAccess0Choose.setSource(referenceToInputLocationChoose);
					nodeAllowanceGuardPartChoose = fieldAccess0Choose;

				}

				// CONNECTIONS
				if (allowedEdgePropertiesChoose.size() > 1) {

					Or edgeAllowedOrChoose = CarmaFactory.eINSTANCE.createOr();

					/// 0
					IsIn isInEdgeProperty0Choose = CarmaFactory.eINSTANCE.createIsIn();
					AtomicTrue atomicTrueEdge0Choose = CarmaFactory.eINSTANCE.createAtomicTrue();
					isInEdgeProperty0Choose.setLeft(atomicTrueEdge0Choose);
					AccessToEdgeValue accessToEdgeValue0Choose = CarmaFactory.eINSTANCE.createAccessToEdgeValue();
					isInEdgeProperty0Choose.setRight(accessToEdgeValue0Choose);
					accessToEdgeValue0Choose.setLabel(allowedEdgePropertiesChoose.get(0));

					Reference currentLocationRefEdgeProp0Choose = CarmaFactory.eINSTANCE.createReference();
					currentLocationRefEdgeProp0Choose.setIsCall(false);
					currentLocationRefEdgeProp0Choose.setReference(currentLocationAttributeVD);

					Reference prospectiveNodeLocationEdge0RefChoose = CarmaFactory.eINSTANCE.createReference();
					prospectiveNodeLocationEdge0RefChoose.setIsCall(false);
					prospectiveNodeLocationEdge0RefChoose.setReference(untypedVariableNodeLocationChoose);

					accessToEdgeValue0Choose.setSrc(currentLocationRefEdgeProp0Choose);
					accessToEdgeValue0Choose.setTrg(prospectiveNodeLocationEdge0RefChoose);

					// 1
					IsIn isInEdgeProperty1Choose = CarmaFactory.eINSTANCE.createIsIn();
					AtomicTrue atomicTrueEdge1Choose = CarmaFactory.eINSTANCE.createAtomicTrue();
					isInEdgeProperty1Choose.setLeft(atomicTrueEdge1Choose);
					AccessToEdgeValue accessToEdgeValue1Choose = CarmaFactory.eINSTANCE.createAccessToEdgeValue();
					isInEdgeProperty1Choose.setRight(accessToEdgeValue1Choose);
					accessToEdgeValue1Choose.setLabel(allowedEdgePropertiesChoose.get(1));

					Reference currentLocationRefEdgeProp1Choose = CarmaFactory.eINSTANCE.createReference();
					currentLocationRefEdgeProp1Choose.setIsCall(false);
					currentLocationRefEdgeProp1Choose.setReference(currentLocationAttributeVD);

					Reference prospectiveNodeLocationEdge1RefChoose = CarmaFactory.eINSTANCE.createReference();
					prospectiveNodeLocationEdge1RefChoose.setIsCall(false);
					prospectiveNodeLocationEdge1RefChoose.setReference(untypedVariableNodeLocationChoose);

					accessToEdgeValue1Choose.setSrc(currentLocationRefEdgeProp1Choose);
					accessToEdgeValue1Choose.setTrg(prospectiveNodeLocationEdge1RefChoose);

					edgeAllowedOrChoose.setLeft(accessToEdgeValue0Choose);
					edgeAllowedOrChoose.setRight(accessToEdgeValue1Choose);

					for (int i = 2; i < allowedEdgePropertiesChoose.size(); i++) {
						Or edgeAllowedOrInnerChoose = CarmaFactory.eINSTANCE.createOr();
						edgeAllowedOrInnerChoose.setRight(edgeAllowedOrChoose);

						IsIn isInEdgePropertyiChoose = CarmaFactory.eINSTANCE.createIsIn();
						AtomicTrue atomicTrueEdgeiChoose = CarmaFactory.eINSTANCE.createAtomicTrue();
						isInEdgePropertyiChoose.setLeft(atomicTrueEdgeiChoose);
						AccessToEdgeValue accessToEdgeValueiChoose = CarmaFactory.eINSTANCE.createAccessToEdgeValue();
						isInEdgePropertyiChoose.setRight(accessToEdgeValueiChoose);
						accessToEdgeValueiChoose.setLabel(allowedEdgePropertiesChoose.get(i));

						Reference currentLocationRefEdgePropiChoose = CarmaFactory.eINSTANCE.createReference();
						currentLocationRefEdgePropiChoose.setIsCall(false);
						currentLocationRefEdgePropiChoose.setReference(currentLocationAttributeVD);

						Reference prospectiveNodeLocationEdgeiRefChoose = CarmaFactory.eINSTANCE.createReference();
						prospectiveNodeLocationEdgeiRefChoose.setIsCall(false);
						prospectiveNodeLocationEdgeiRefChoose.setReference(untypedVariableNodeLocationChoose);

						accessToEdgeValueiChoose.setSrc(currentLocationRefEdgePropiChoose);
						accessToEdgeValueiChoose.setTrg(prospectiveNodeLocationEdgeiRefChoose);

						edgeAllowedOrInnerChoose.setLeft(accessToEdgeValueiChoose);
						edgeAllowedOrChoose = edgeAllowedOrInnerChoose;
					}

					connectionAllowanceGuardPartChoose = edgeAllowedOrChoose;

				} else if (allowedEdgePropertiesChoose.size() == 1) {

					IsIn isInEdgeProperty0Choose = CarmaFactory.eINSTANCE.createIsIn();
					AtomicTrue atomicTrueEdgeChoose = CarmaFactory.eINSTANCE.createAtomicTrue();
					isInEdgeProperty0Choose.setLeft(atomicTrueEdgeChoose);
					AccessToEdgeValue accessToEdgeValue0Choose = CarmaFactory.eINSTANCE.createAccessToEdgeValue();
					isInEdgeProperty0Choose.setRight(accessToEdgeValue0Choose);
					accessToEdgeValue0Choose.setLabel(allowedEdgePropertiesChoose.get(0));

					Reference currentLocationRefEdgeProp0Choose = CarmaFactory.eINSTANCE.createReference();
					currentLocationRefEdgeProp0Choose.setIsCall(false);
					currentLocationRefEdgeProp0Choose.setReference(currentLocationAttributeVD);

					Reference prospectiveNodeLocationEdge0RefChoose = CarmaFactory.eINSTANCE.createReference();
					prospectiveNodeLocationEdge0RefChoose.setIsCall(false);
					prospectiveNodeLocationEdge0RefChoose.setReference(untypedVariableNodeLocationChoose);

					accessToEdgeValue0Choose.setSrc(currentLocationRefEdgeProp0Choose);
					accessToEdgeValue0Choose.setTrg(prospectiveNodeLocationEdge0RefChoose);

					connectionAllowanceGuardPartChoose = isInEdgeProperty0Choose;

				}
				outerAndChoose.setLeft(nodeAllowanceGuardPartChoose);
				outerAndChoose.setRight(connectionAllowanceGuardPartChoose);

			}

			actionGuardChoose.setGuard(guardExpressionChoose);

			activityChoose.setPredicate(actionGuardChoose);

			Update updateReadyToChoose = CarmaFactory.eINSTANCE.createUpdate();

			UpdateAssignment updateAssignmentChoose = CarmaFactory.eINSTANCE.createUpdateAssignment();
			MyContext myContextNextLocationChoose = CarmaFactory.eINSTANCE.createMyContext();

			StoreAttribute storeAttributeNextLocation = CarmaFactory.eINSTANCE.createStoreAttribute();
			storeAttributeNextLocation.setReference(nextLocationAttributeVD);
			myContextNextLocationChoose.setAttribute(storeAttributeNextLocation);
			updateAssignmentChoose.setTarget(myContextNextLocationChoose);
			Reference referenceToInputLocationUpdateChoose = CarmaFactory.eINSTANCE.createReference();
			referenceToInputLocationUpdateChoose.setIsCall(false);
			referenceToInputLocationUpdateChoose.setReference(untypedVariableNodeLocationChoose);
			updateAssignmentChoose.setExpression(referenceToInputLocationUpdateChoose);
			updateReadyToChoose.getUpdateAssignment().add(updateAssignmentChoose);

			inputActionChoose.setUpdate(updateReadyToChoose);

			/***********************************************************************************/
			/*****************
			 * Processes: ReadyToMove
			 ***************************************************/

			ProcessState processStateReadyToMove = CarmaFactory.eINSTANCE.createProcessState();
			ProcessExpressionAction processExpressionActionReadyToMove = CarmaFactory.eINSTANCE
					.createProcessExpressionAction();
			OutputAction outputActionMove = CarmaFactory.eINSTANCE.createOutputAction();
			Activity activityMove = CarmaFactory.eINSTANCE.createActivity();
			ProcessExpressionReference processExpressionReferenceReadyToMove = CarmaFactory.eINSTANCE
					.createProcessExpressionReference();

			processStateReadyToMove.setProcessExpression(processExpressionActionReadyToMove);
			processStateReadyToMove.setName(stateName(currentState, "ReadyToMove"));
			activityMove.setName("move");
			activityMove.setIsBroadacst(true);
			outputActionMove.setActivity(activityMove);
			outputActionMove.setWithData(true);
			processExpressionActionReadyToMove.setAction(outputActionMove);
			processExpressionReferenceReadyToMove.setExpression(processStateReadyToMove);

			ActionGuard actionGuardMove = CarmaFactory.eINSTANCE.createActionGuard();
			AtomicFalse actionGuardMoveAtomicFalse = CarmaFactory.eINSTANCE.createAtomicFalse();
			actionGuardMove.setGuard(actionGuardMoveAtomicFalse);
			activityMove.setPredicate(actionGuardMove);

			MyContext myContextCurrentLocationMove = CarmaFactory.eINSTANCE.createMyContext();
			StoreAttribute myContextStoreAttrubuteCurrentLocationMove = CarmaFactory.eINSTANCE.createStoreAttribute();
			myContextStoreAttrubuteCurrentLocationMove.setReference(currentLocationAttributeVD);
			myContextCurrentLocationMove.setAttribute(myContextStoreAttrubuteCurrentLocationMove);

			MyContext myContextNextLocationMove = CarmaFactory.eINSTANCE.createMyContext();
			StoreAttribute myContextNextLocationStoreAttributeMove = CarmaFactory.eINSTANCE.createStoreAttribute();
			myContextNextLocationStoreAttributeMove.setReference(nextLocationAttributeVD);
			myContextNextLocationMove.setAttribute(myContextNextLocationStoreAttributeMove);

			outputActionMove.getOutputArguments().add(myContextCurrentLocationMove);
			outputActionMove.getOutputArguments().add(myContextNextLocationMove);
			//
			Update updateMove = CarmaFactory.eINSTANCE.createUpdate();

			UpdateAssignment updateAsignmentMovePreviousLocation = CarmaFactory.eINSTANCE.createUpdateAssignment();

			MyContext myContextPreviousMoveUpdate = CarmaFactory.eINSTANCE.createMyContext();
			StoreAttribute myContextStoreAttrubutePreviousMoveUpdate = CarmaFactory.eINSTANCE.createStoreAttribute();
			myContextStoreAttrubutePreviousMoveUpdate.setReference(previousLocationAttributeVD);
			myContextPreviousMoveUpdate.setAttribute(myContextStoreAttrubutePreviousMoveUpdate);

			MyContext myContextCurrentLocationToPreviousMoveUpdate = CarmaFactory.eINSTANCE.createMyContext();
			StoreAttribute myContextStoreAttrubuteCurrentLocationToPreviousMoveUpdate = CarmaFactory.eINSTANCE
					.createStoreAttribute();
			myContextStoreAttrubuteCurrentLocationToPreviousMoveUpdate.setReference(currentLocationAttributeVD);
			myContextCurrentLocationToPreviousMoveUpdate
					.setAttribute(myContextStoreAttrubuteCurrentLocationToPreviousMoveUpdate);

			updateAsignmentMovePreviousLocation.setTarget(myContextPreviousMoveUpdate);
			updateAsignmentMovePreviousLocation.setExpression(myContextCurrentLocationToPreviousMoveUpdate);
			updateMove.getUpdateAssignment().add(updateAsignmentMovePreviousLocation);

			UpdateAssignment updateAsignmentMoveCurrentLocation = CarmaFactory.eINSTANCE.createUpdateAssignment();

			MyContext myContextCurrentLocationMoveUpdate = CarmaFactory.eINSTANCE.createMyContext();
			StoreAttribute myContextStoreAttrubuteCurrentLocationMoveUpdate = CarmaFactory.eINSTANCE
					.createStoreAttribute();
			myContextStoreAttrubuteCurrentLocationMoveUpdate.setReference(currentLocationAttributeVD);
			myContextCurrentLocationMoveUpdate.setAttribute(myContextStoreAttrubuteCurrentLocationMoveUpdate);

			MyContext myContextNextLocationMoveUpdate = CarmaFactory.eINSTANCE.createMyContext();
			StoreAttribute myContextStoreAttrubuteNextLocationMoveUpdate = CarmaFactory.eINSTANCE
					.createStoreAttribute();
			myContextStoreAttrubuteNextLocationMoveUpdate.setReference(nextLocationAttributeVD);
			myContextNextLocationMoveUpdate.setAttribute(myContextStoreAttrubuteNextLocationMoveUpdate);

			updateAsignmentMoveCurrentLocation.setTarget(myContextCurrentLocationMoveUpdate);
			updateAsignmentMoveCurrentLocation.setExpression(myContextNextLocationMoveUpdate);

			updateMove.getUpdateAssignment().add(updateAsignmentMoveCurrentLocation);

			outputActionMove.setUpdate(updateMove);

			processBlock.getProcesses().add(processStateReadyToMove);

			/***********************************************************************************/
			/*****************
			 * Processes: ReadyToUnregister
			 ***************************************************/

			ProcessState processStateReadyToUnregister = CarmaFactory.eINSTANCE.createProcessState();
			ProcessExpressionAction processExpressionActionReadyToUnregister = CarmaFactory.eINSTANCE
					.createProcessExpressionAction();
			InputAction inputActionUnregister = CarmaFactory.eINSTANCE.createInputAction();
			Activity activityUnregister = CarmaFactory.eINSTANCE.createActivity();
			ProcessExpressionReference processExpressionReferenceReadyToUnregister = CarmaFactory.eINSTANCE
					.createProcessExpressionReference();

			processStateReadyToUnregister.setProcessExpression(processExpressionActionReadyToUnregister);
			processStateReadyToUnregister.setName(stateName(currentState, "ReadyToUnregister"));
			activityUnregister.setName("unregister");
			activityUnregister.setIsBroadacst(false);
			inputActionUnregister.setActivity(activityUnregister);
			processExpressionActionReadyToUnregister.setAction(inputActionUnregister);
			processExpressionReferenceReadyToUnregister.setExpression(processStateReadyToUnregister);
			processBlock.getProcesses().add(processStateReadyToUnregister);

			UntypedVariable uvNodeLocationUnRegister = CarmaFactory.eINSTANCE.createUntypedVariable();
			uvNodeLocationUnRegister.setName("nodeLoc");

			inputActionUnregister.getParameters().add(uvNodeLocationUnRegister);

			ActionGuard unregisterActionGuard = CarmaFactory.eINSTANCE.createActionGuard();

			MyContext myContextNodeLocationUnRegister = CarmaFactory.eINSTANCE.createMyContext();
			StoreAttribute storeAttributeNodeLocationUnRegister = CarmaFactory.eINSTANCE.createStoreAttribute();
			myContextNodeLocationUnRegister.setAttribute(storeAttributeNodeLocationUnRegister);
			storeAttributeNodeLocationUnRegister.setReference(currentLocationAttributeVD);

			Equality nodeLocationUnRegisterEquality = CarmaFactory.eINSTANCE.createEquality();
			nodeLocationUnRegisterEquality.setLeft(myContextNodeLocationUnRegister);
			Reference uvReferenceNodeLocationUnRegister = CarmaFactory.eINSTANCE.createReference();
			uvReferenceNodeLocationUnRegister.setIsCall(false);
			uvReferenceNodeLocationUnRegister.setReference(uvNodeLocationUnRegister);
			nodeLocationUnRegisterEquality.setRight(uvReferenceNodeLocationUnRegister);

			unregisterActionGuard.setGuard(nodeLocationUnRegisterEquality);
			activityUnregister.setPredicate(unregisterActionGuard);

			/***********************************************************************************/
			/*****************
			 * Processes: ReadyToArrive
			 ***************************************************/

			ProcessState processStateReadyToArrive = CarmaFactory.eINSTANCE.createProcessState();
			ProcessExpressionAction processExpressionActionReadyToArrive = CarmaFactory.eINSTANCE
					.createProcessExpressionAction();
			OutputAction outputActionArrive = CarmaFactory.eINSTANCE.createOutputAction();
			Activity activityArrive = CarmaFactory.eINSTANCE.createActivity();
			ProcessExpressionReference processExpressionReferenceReadyToArrive = CarmaFactory.eINSTANCE
					.createProcessExpressionReference();

			// arrive

			ProcessExpressionGuard preGuardArrive = CarmaFactory.eINSTANCE.createProcessExpressionGuard();
			Guard preGuardArriveGuard = CarmaFactory.eINSTANCE.createGuard();
			preGuardArrive.setGuard(preGuardArriveGuard);
			preGuardArrive.setExpression(processExpressionActionReadyToArrive);

			Equality guardArriveEquality = CarmaFactory.eINSTANCE.createEquality();

			MyContext myContextCurrentLocationArrive = CarmaFactory.eINSTANCE.createMyContext();
			StoreAttribute myContextCurrentLocationStoreAttributeArrive = CarmaFactory.eINSTANCE.createStoreAttribute();
			myContextCurrentLocationStoreAttributeArrive.setReference(currentLocationAttributeVD);
			myContextCurrentLocationArrive.setAttribute(myContextCurrentLocationStoreAttributeArrive);

			MyContext myContextGoalLocationArrive = CarmaFactory.eINSTANCE.createMyContext();
			StoreAttribute myContextGoalLocationStoreAttributeArrive = CarmaFactory.eINSTANCE.createStoreAttribute();
			myContextGoalLocationStoreAttributeArrive.setReference(goalLocationAttributeVD);
			myContextGoalLocationArrive.setAttribute(myContextGoalLocationStoreAttributeArrive);

			guardArriveEquality.setLeft(myContextCurrentLocationArrive);
			guardArriveEquality.setRight(myContextGoalLocationArrive);

			preGuardArriveGuard.setBooleanExpression(guardArriveEquality);


			ActionGuard falseActionGuardArrive = CarmaFactory.eINSTANCE.createActionGuard();
			AtomicFalse falseForActionGuard = CarmaFactory.eINSTANCE.createAtomicFalse();
			falseActionGuardArrive.setGuard(falseForActionGuard);
			
			
			processStateReadyToArrive.setName(stateName(currentState, "ReadyToArrive"));
			activityArrive.setName("arrive");
			activityArrive.setPredicate(falseActionGuardArrive);
			activityArrive.setIsBroadacst(true);
			outputActionArrive.setActivity(activityArrive);
			processExpressionActionReadyToArrive.setAction(outputActionArrive);
			processExpressionReferenceReadyToArrive.setExpression(processStateReadyToArrive);

			ProcessExpressionKill killUponArrivalExpression = CarmaFactory.eINSTANCE.createProcessExpressionKill();

			processExpressionActionReadyToArrive.setNext(killUponArrivalExpression);
			processBlock.getProcesses().add(processStateReadyToArrive);

			ProcessExpressionChoice processExpressionChoiceReadyToArrive = CarmaFactory.eINSTANCE
					.createProcessExpressionChoice();
			processStateReadyToArrive.setProcessExpression(processExpressionChoiceReadyToArrive);

			processExpressionChoiceReadyToArrive.setLeft(preGuardArrive);

			// continue

			ProcessExpressionAction processExpressionActionReadyToArriveContinue = CarmaFactory.eINSTANCE
					.createProcessExpressionAction();

			ProcessExpressionGuard preGuardContinue = CarmaFactory.eINSTANCE.createProcessExpressionGuard();
			Guard preGuardArriveContinue = CarmaFactory.eINSTANCE.createGuard();
			preGuardContinue.setGuard(preGuardArriveContinue);
			preGuardContinue.setExpression(processExpressionActionReadyToArriveContinue);

			DisEquality guardContinueDisquality = CarmaFactory.eINSTANCE.createDisEquality();

			MyContext myContextCurrentLocationArriveContinue = CarmaFactory.eINSTANCE.createMyContext();
			StoreAttribute myContextCurrentLocationStoreAttributeArriveContinue = CarmaFactory.eINSTANCE
					.createStoreAttribute();
			myContextCurrentLocationStoreAttributeArriveContinue.setReference(currentLocationAttributeVD);
			myContextCurrentLocationArriveContinue.setAttribute(myContextCurrentLocationStoreAttributeArriveContinue);

			MyContext myContextGoalLocationArriveContinue = CarmaFactory.eINSTANCE.createMyContext();
			StoreAttribute myContextGoalLocationStoreAttributeArriveContunue = CarmaFactory.eINSTANCE
					.createStoreAttribute();
			myContextGoalLocationStoreAttributeArriveContunue.setReference(goalLocationAttributeVD);
			myContextGoalLocationArriveContinue.setAttribute(myContextGoalLocationStoreAttributeArriveContunue);

			guardContinueDisquality.setLeft(myContextCurrentLocationArriveContinue);
			guardContinueDisquality.setRight(myContextGoalLocationArriveContinue);

			preGuardArriveContinue.setBooleanExpression(guardContinueDisquality);

			processExpressionChoiceReadyToArrive.setRight(preGuardContinue);
			OutputAction outputActionArriveContinue = CarmaFactory.eINSTANCE.createOutputAction();
			Activity activityContinue = CarmaFactory.eINSTANCE.createActivity();
			ProcessExpressionReference processExpressionReferenceReadyToArriveContinue = CarmaFactory.eINSTANCE
					.createProcessExpressionReference();

			activityContinue.setName("continue");
			activityContinue.setIsBroadacst(true);
			outputActionArriveContinue.setActivity(activityContinue);
			processExpressionActionReadyToArriveContinue.setAction(activityContinue);
			processExpressionReferenceReadyToArriveContinue.setExpression(processStateReadyToArrive);

			/***********************************************************************************/
			/*****************
			 * Processes: ReadyToRegister
			 ***************************************************/

			ProcessState processStateReadyToRegister = CarmaFactory.eINSTANCE.createProcessState();
			ProcessExpressionAction processExpressionActionReadyToRegister = CarmaFactory.eINSTANCE
					.createProcessExpressionAction();
			InputAction inputActionRegister = CarmaFactory.eINSTANCE.createInputAction();
			Activity activityRegister = CarmaFactory.eINSTANCE.createActivity();
			ProcessExpressionReference processExpressionReferenceReadyToRegister = CarmaFactory.eINSTANCE
					.createProcessExpressionReference();

			processStateReadyToRegister.setProcessExpression(processExpressionActionReadyToRegister);
			processStateReadyToRegister.setName(stateName(currentState, "ReadyToRegister"));
			activityRegister.setName("register");
			activityRegister.setIsBroadacst(false);
			inputActionRegister.setActivity(activityRegister);
			processExpressionActionReadyToRegister.setAction(inputActionRegister);
			processExpressionReferenceReadyToRegister.setExpression(processStateReadyToRegister);

			processBlock.getProcesses().add(processStateReadyToRegister);

			UntypedVariable uvNodeLocationRegister = CarmaFactory.eINSTANCE.createUntypedVariable();
			uvNodeLocationRegister.setName("nodeLocation");

			inputActionRegister.getParameters().add(uvNodeLocationRegister);

			ActionGuard registerActionGuard = CarmaFactory.eINSTANCE.createActionGuard();

			MyContext myContextNodeLocationRegister = CarmaFactory.eINSTANCE.createMyContext();
			StoreAttribute storeAttributeNodeLocationRegister = CarmaFactory.eINSTANCE.createStoreAttribute();
			myContextNodeLocationRegister.setAttribute(storeAttributeNodeLocationRegister);
			storeAttributeNodeLocationRegister.setReference(currentLocationAttributeVD);

			Equality nodeLocationRegisterEquality = CarmaFactory.eINSTANCE.createEquality();
			nodeLocationRegisterEquality.setLeft(myContextNodeLocationRegister);
			Reference uvReferenceNodeLocationRegister = CarmaFactory.eINSTANCE.createReference();
			uvReferenceNodeLocationRegister.setIsCall(false);
			uvReferenceNodeLocationRegister.setReference(uvNodeLocationRegister);
			nodeLocationRegisterEquality.setRight(uvReferenceNodeLocationRegister);

			registerActionGuard.setGuard(nodeLocationRegisterEquality);
			activityRegister.setPredicate(registerActionGuard);

			/***********************************************************************************/
			/*****************
			 * Setup Next Of Processes
			 ***************************************************/
			// ReadyToRegister -> ReadyToChoose
			processExpressionActionReadyToRegister.setNext(processExpressionReferenceReadyToChoose);

			// ReadyToChoose ->ReadyToMove
			processExpressionActionReadyToChoose.setNext(processExpressionReferenceReadyToMove);

			// ReadyToMove -> ReadyToUnregister
			processExpressionActionReadyToMove.setNext(processExpressionReferenceReadyToUnregister);

			// RedyToUnregister -> ReadyToArrive
			processExpressionActionReadyToUnregister.setNext(processExpressionReferenceReadyToArrive);

			// ReadyToArrive ->ReadyToRegister
			processExpressionActionReadyToArriveContinue.setNext(processExpressionReferenceReadyToRegister);

		}

		/***********************************************************************************/
		/***************** Init ***************************************************/

		InitBlock initBlock = CarmaFactory.eINSTANCE.createInitBlock();

		ProcessReference processReferenceZ = CarmaFactory.eINSTANCE.createProcessReference();
		processReferenceZ.setExpression(processStartParameter);
		initBlock.setInit(processReferenceZ);

		cd.setInitBlock(initBlock);

		return cd;
	}

	private void generateNodeComponents() {
		for (NodePrototype np : originalModel.getNodePrototypes()) {
			this.nodeComponentDefinitions.add(generateNodePrototype(np));
		}

	}

	private String toNodeStyle(String input) {
		// String[] split = input.split("([A-Z])|_");
		// String result = "";
		// String finalResult = "";
		// if(split.length>0){
		// for(String word : split)
		// {
		// word.toUpperCase();
		// result += word + "_";
		// }
		// finalResult = (String)result.subSequence(0, result.length()-1);
		// }
		// else{
		// finalResult = input.toUpperCase();
		// }
		// if(finalResult.equals(input)){
		// finalResult+= "_NODE";
		// }

		return input + "_LOC";
	}

	private void generateSpaceDefinition() {

		Set<String> availableEdgeProtypes = new HashSet<>();

		for (ConnectionInstance cinstance : originalModel.getConnectionInstances()) {

			availableEdgeProtypes.add(cinstance.getPrototype().getName());

		}

		spaceDefinition.setName("AutogeneratedSpace");

		UniverseElement x = CarmaFactory.eINSTANCE.createUniverseElement();
		UniverseElement y = CarmaFactory.eINSTANCE.createUniverseElement();

		IntegerType xType = CarmaFactory.eINSTANCE.createIntegerType();
		IntegerType yType = CarmaFactory.eINSTANCE.createIntegerType();

		x.setType(xType);
		y.setType(yType);

		x.setName("x");
		y.setName("y");

		spaceDefinition.getUniverse().add(x);
		spaceDefinition.getUniverse().add(y);

		for (LocatedElement li : originalModel.getLocatedElements()) {
			if (li instanceof NodeInstance) {
				NodeInstance ni = (NodeInstance) li;
				String userNodeName = ni.getPrototype().getName();

				String nodeName = toNodeStyle(userNodeName);

				int nodeX = ni.getX();
				int nodeY = ni.getY();
				NamedNode nnode = CarmaFactory.eINSTANCE.createNamedNode();

				nnode.setName(nodeName);

				AtomicInteger xValue = CarmaFactory.eINSTANCE.createAtomicInteger();
				xValue.setValue(nodeX);
				AtomicInteger yValue = CarmaFactory.eINSTANCE.createAtomicInteger();
				yValue.setValue(nodeY);

				nnode.getValues().add(xValue);
				nnode.getValues().add(yValue);

				spaceDefinition.getNodes().add(nnode);

			}
		}

		// for (NodeBodyCommand nbc : spaceDefinition.getNodes()) {
		// if (nbc instanceof NamedNode) {
		// NamedNode namedNode = (NamedNode) nbc;
		// // System.out.println(namedNode.getName());
		// // System.out.println("values: " + namedNode.getValues());
		//
		// }
		// }

		for (ConnectionInstance ci : originalModel.getConnectionInstances()) {

			NodeInstance fromNode = ci.getFrom();

			String fromName = toNodeStyle(fromNode.getPrototype().getName());

			int fromX = fromNode.getX();
			int fromY = fromNode.getY();

			NodeInstance toNode = ci.getTo();
			String toName = toNodeStyle(toNode.getPrototype().getName());
			int toX = toNode.getX();
			int toY = toNode.getY();

			NamedNode from = null;
			NamedNode to = null;

			for (NodeBodyCommand nbc : spaceDefinition.getNodes()) {
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

			AtomicInteger nodeX = CarmaFactory.eINSTANCE.createAtomicInteger();
			nodeX.setValue(((AtomicInteger) (from.getValues().get(0))).getValue());

			AtomicInteger nodeY = CarmaFactory.eINSTANCE.createAtomicInteger();
			nodeY.setValue(((AtomicInteger) (from.getValues().get(1))).getValue());

			fromNLE.getValues().add(nodeX);
			fromNLE.getValues().add(nodeY);
			NamedLocationExpression toNLE = CarmaFactory.eINSTANCE.createNamedLocationExpression();
			toNLE.setRef(to);

			AtomicInteger tonodeX = CarmaFactory.eINSTANCE.createAtomicInteger();
			tonodeX.setValue(((AtomicInteger) (to.getValues().get(0))).getValue());

			AtomicInteger tonodeY = CarmaFactory.eINSTANCE.createAtomicInteger();
			tonodeY.setValue(((AtomicInteger) (to.getValues().get(1))).getValue());

			toNLE.getValues().add(tonodeX);
			toNLE.getValues().add(tonodeY);

			connectionDeclaration.setSource(fromNLE);
			connectionDeclaration.setTarget(toNLE);

			String name = ci.getPrototype().getName();

			for (String possibleConnectionName : availableEdgeProtypes) {
				if (name.equals(possibleConnectionName)) {
					EdgeProperty ep = CarmaFactory.eINSTANCE.createEdgeProperty();
					ep.setName(possibleConnectionName);
					AtomicTrue edgeValue = CarmaFactory.eINSTANCE.createAtomicTrue();

					ep.setValue(edgeValue);
					connectionDeclaration.getEdgeProperties().add(ep);

				} else {
					EdgeProperty ep = CarmaFactory.eINSTANCE.createEdgeProperty();
					ep.setName(possibleConnectionName);
					AtomicFalse edgeValue = CarmaFactory.eINSTANCE.createAtomicFalse();

					ep.setValue(edgeValue);
					connectionDeclaration.getEdgeProperties().add(ep);
				}

			}

			spaceDefinition.getEdges().add(connectionDeclaration);

		}

		// Areas:

		for (NodePrototype np : originalModel.getNodePrototypes()) {
			LabelDefinition labelDefinition = CarmaFactory.eINSTANCE.createLabelDefinition();
			labelDefinition.setName(np.getName());

			for (NodeBodyCommand nbc : spaceDefinition.getNodes()) {
				if (nbc instanceof NamedNode) {
					NamedNode namedNode = (NamedNode) nbc;
					if (namedNode.getName().equals(toNodeStyle(np.getName()))) {
						AreaElementDeclaration aed = CarmaFactory.eINSTANCE.createAreaElementDeclaration();
						NamedLocationExpression nle = CarmaFactory.eINSTANCE.createNamedLocationExpression();
						aed.setNode(nle);
						nle.setRef(namedNode);
						AtomicInteger nodeX = CarmaFactory.eINSTANCE.createAtomicInteger();
						nodeX.setValue(((AtomicInteger) (namedNode.getValues().get(0))).getValue());

						AtomicInteger nodeY = CarmaFactory.eINSTANCE.createAtomicInteger();
						nodeY.setValue(((AtomicInteger) (namedNode.getValues().get(1))).getValue());

						nle.getValues().add(nodeX);
						nle.getValues().add(nodeY);
						labelDefinition.getNodes().add(aed);
					}
				}
			}
			spaceDefinition.getLabels().add(labelDefinition);
		}

	}

	private void generateEnumDefinitions() {

		EnumDefinition nodeNamesEnum = CarmaFactory.eINSTANCE.createEnumDefinition();
		nodeNamesEnum.setName("NodeType");

		EnumCase nullCase = CarmaFactory.eINSTANCE.createEnumCase();
		nullCase.setName("NULL_TYPE");

		nodeNamesEnum.getValues().add(nullCase);

		for (NodePrototype np : originalModel.getNodePrototypes()) {
			String name = toEnumCase(np.getName());
			EnumCase enumCase = CarmaFactory.eINSTANCE.createEnumCase();
			enumCase.setName(name);
			nodeNamesEnum.getValues().add(enumCase);
		}

		enumDefinitions.add(nodeNamesEnum);

	}

	private String toEnumCase(String name) {

		return name + "_TYPE";
	}

	private FunctionDefinition createFunctionDefinitionWithLocationAndTime(String functionName) {
		FunctionDefinition functionDefinition = CarmaFactory.eINSTANCE.createFunctionDefinition();
		RealType realType = CarmaFactory.eINSTANCE.createRealType();
		functionDefinition.setType(realType);
		functionDefinition.setName(functionName);

		Variable nodeLocationVariable = CarmaFactory.eINSTANCE.createVariable();
		LocationType nodeLocationType = CarmaFactory.eINSTANCE.createLocationType();
		nodeLocationVariable.setType(nodeLocationType);
		nodeLocationVariable.setName("nodeLocation");

		Variable currentNowVariable = CarmaFactory.eINSTANCE.createVariable();
		RealType currentNowRealType = CarmaFactory.eINSTANCE.createRealType();
		currentNowVariable.setType(currentNowRealType);
		currentNowVariable.setName("currentTime");

		functionDefinition.getParameters().add(nodeLocationVariable);
		functionDefinition.getParameters().add(currentNowVariable);

		BlockCommand blockCommand = CarmaFactory.eINSTANCE.createBlockCommand();
		ReturnCommand returnCommand = CarmaFactory.eINSTANCE.createReturnCommand();
		AtomicReal returnAtomicReal = CarmaFactory.eINSTANCE.createAtomicReal();
		returnAtomicReal.setValue(1.0);
		returnCommand.setExpression(returnAtomicReal);
		blockCommand.getCommands().add(returnCommand);
		functionDefinition.setBody(blockCommand);

		return functionDefinition;
	}

	private FunctionDefinition createFunctionDefinitionWithThreeLocationsAndTime(String functionName) {
		FunctionDefinition functionDefinition = CarmaFactory.eINSTANCE.createFunctionDefinition();
		RealType realType = CarmaFactory.eINSTANCE.createRealType();
		functionDefinition.setType(realType);
		functionDefinition.setName(functionName);

		Variable nodeLocationVariable = CarmaFactory.eINSTANCE.createVariable();
		LocationType nodeLocationType = CarmaFactory.eINSTANCE.createLocationType();
		nodeLocationVariable.setType(nodeLocationType);
		nodeLocationVariable.setName("currentLocation");

		Variable nodeLocationVariable2 = CarmaFactory.eINSTANCE.createVariable();
		LocationType nodeLocationType2 = CarmaFactory.eINSTANCE.createLocationType();
		nodeLocationVariable2.setType(nodeLocationType2);
		nodeLocationVariable2.setName("nextLocation");

		Variable nodeLocationVariable3 = CarmaFactory.eINSTANCE.createVariable();
		LocationType nodeLocationType3 = CarmaFactory.eINSTANCE.createLocationType();
		nodeLocationVariable3.setType(nodeLocationType3);
		nodeLocationVariable3.setName("goalLocation");

		Variable currentNowVariable = CarmaFactory.eINSTANCE.createVariable();
		RealType currentNowRealType = CarmaFactory.eINSTANCE.createRealType();
		currentNowVariable.setType(currentNowRealType);
		currentNowVariable.setName("currentTime");

		functionDefinition.getParameters().add(nodeLocationVariable);
		functionDefinition.getParameters().add(nodeLocationVariable2);
		functionDefinition.getParameters().add(nodeLocationVariable3);
		functionDefinition.getParameters().add(currentNowVariable);

		BlockCommand blockCommand = CarmaFactory.eINSTANCE.createBlockCommand();
		ReturnCommand returnCommand = CarmaFactory.eINSTANCE.createReturnCommand();
		AtomicReal returnAtomicReal = CarmaFactory.eINSTANCE.createAtomicReal();
		returnAtomicReal.setValue(1.0);
		returnCommand.setExpression(returnAtomicReal);
		blockCommand.getCommands().add(returnCommand);
		functionDefinition.setBody(blockCommand);

		return functionDefinition;
	}

	private void generateFunctionDefinitions() {

		this.functionDefinitions.add(createFunctionDefinitionWithThreeLocationsAndTime("prChooseNext"));
		this.functionDefinitions.add(createFunctionDefinitionWithLocationAndTime("prChooseGoal"));
		this.functionDefinitions.add(createFunctionDefinitionWithLocationAndTime("prChooseStart"));
		this.functionDefinitions.add(this.getNodeTypeFunction(this.enumDefinitions.get(0)));
		
		this.randomLocationFunction = this.getRandomLocationFunction(this.spaceDefinition);
		this.functionDefinitions.add(randomLocationFunction);
		
	}

	private FunctionDefinition getRandomLocationFunction(SpaceDefinition spaceDef) {
		List<NamedNode> allNodes = new ArrayList<>();
		
		for(NodeBodyCommand nbc : spaceDef.getNodes()){
			allNodes.add((NamedNode)nbc);
		}
		
		FunctionDefinition randomLocationFunction = CarmaFactory.eINSTANCE.createFunctionDefinition();
		randomLocationFunction.setName("getRandomLocation");
		LocationType returnTypeLocation = CarmaFactory.eINSTANCE.createLocationType();
		randomLocationFunction.setType(returnTypeLocation);
		BlockCommand mainBlockCommand = CarmaFactory.eINSTANCE.createBlockCommand();
		randomLocationFunction.setBody(mainBlockCommand);
        ReturnCommand returnCommand =CarmaFactory.eINSTANCE.createReturnCommand();
        mainBlockCommand.getCommands().add(returnCommand);
        UniformFunction uniformSelection = CarmaFactory.eINSTANCE.createUniformFunction();
        returnCommand.setExpression(uniformSelection);
        
        
        for(NamedNode nn: allNodes){
        	NodeExpressionOrArrayAccess neoaa = CarmaFactory.eINSTANCE.createNodeExpressionOrArrayAccess();
        	
        	Reference nodeRef = CarmaFactory.eINSTANCE.createReference();
        	nodeRef.setIsCall(false);
        	nodeRef.setReference(nn);
        	neoaa.setSource(nodeRef);
        	
        	AtomicInteger atomicx = CarmaFactory.eINSTANCE.createAtomicInteger();
        	AtomicInteger atomicy = CarmaFactory.eINSTANCE.createAtomicInteger();
        	atomicx.setValue(((AtomicInteger)nn.getValues().get(0)).getValue());
        	atomicy.setValue(((AtomicInteger)nn.getValues().get(1)).getValue());
        	neoaa.getValues().add(atomicx);
        	neoaa.getValues().add(atomicy);
        	uniformSelection.getArgs().add(neoaa);
        }
		
		return randomLocationFunction;
	}

	
	
	
}
