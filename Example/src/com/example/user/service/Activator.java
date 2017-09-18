package com.example.user.service;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import com.example.user.api.UserService;
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
				.setInterface(UserService.class.getName(), null)
				.setImplementation(UserServiceImpl.class)
				.add(createServiceDependency().setService(UserPersistenceService.class).setRequired(true)));				
		
	}

}
