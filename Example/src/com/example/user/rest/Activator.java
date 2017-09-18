package com.example.user.rest;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import com.example.user.api.UserService;

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
				.setInterface(Object.class.getName(), null)
				.setImplementation(UserResources.class)
				.add(createServiceDependency().setService(UserService.class).setRequired(true)));
	}

}
