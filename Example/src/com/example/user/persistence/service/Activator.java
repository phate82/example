package com.example.user.persistence.service;

import org.amdatu.mongo.MongoDBService;
import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import com.example.user.persistence.api.UserPersistenceService;

public class Activator extends DependencyActivatorBase {

	@Override
	public void destroy(BundleContext context, DependencyManager dm)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(BundleContext context, DependencyManager dm)
			throws Exception {
		
		dm.add(createComponent()
	        	.setInterface(UserPersistenceService.class.getName(), null)
	            .setImplementation(UserPersistenceServiceImpl.class)
	            .add(createServiceDependency().setService(MongoDBService.class).setRequired(true)));
	}

}
