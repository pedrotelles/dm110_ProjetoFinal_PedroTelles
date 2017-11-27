package br.inatel.dm110.hello.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.inatel.dm110.hello.impl.HelloServiceImpl;
import br.inatel.dm110.hello.impl.InventoryServiceImpl;

@ApplicationPath("/api")
public class RestApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(HelloServiceImpl.class);
		classes.add(InventoryServiceImpl.class);
		return classes;
	}

}
