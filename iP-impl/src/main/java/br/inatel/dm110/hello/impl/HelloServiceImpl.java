package br.inatel.dm110.hello.impl;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import br.inatel.dm110.hello.api.HelloService;
import br.inatel.dm110.hello.api.Result;
import br.inatel.dm110.hello.interfaces.HelloRemote;

@RequestScoped
public class HelloServiceImpl implements HelloService {

	@EJB(lookup = "java:app/dm110-ejb-0.1-SNAPSHOT/HelloBean!br.inatel.dm110.hello.interfaces.HelloRemote")
	private HelloRemote hello;

	@Override
	public String sayHello(String name) {
		String message = hello.sayHello(name);
		return "<h1>" + message + "</h1>";
	}

	@Override
	public Result message(String first, String last) {
		Result result = new Result();
		result.setFirstName(first);
		result.setLastName(last);
		String message = String.format("Hello %s %s!!!", first, last);
		result.setMessage(message);
		return result;
	}

}
