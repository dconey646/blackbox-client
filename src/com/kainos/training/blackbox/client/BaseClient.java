package com.kainos.training.blackbox.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.kainos.training.blackbox_interface.model.Person;

public class BaseClient {
	
	private Client client;
	private WebTarget webTarget;
	private Invocation.Builder invocationBuilder;
	
	public List<Person> getPeople(){
		setupClient();
		webTarget = webTarget.path("/person");
		invocationBuilder = webTarget.request();
		Response response = invocationBuilder.get();
		return response.readEntity(new GenericType<List<Person>>() {});	
	}
	
	public List<Person> deletePerson(int index){
		setupClient();
		webTarget = webTarget.path("" + index);
		invocationBuilder = webTarget.request();
		Response response = invocationBuilder.delete();
		return response.readEntity(new GenericType<List<Person>>() {});	
	}
	
	public List<Person> addPerson(Person person){
		setupClient();
		webTarget = webTarget.path("/person");
		invocationBuilder = webTarget.request();
		Response response = invocationBuilder.post(Entity.entity(person, MediaType.APPLICATION_JSON));
		return response.readEntity(new GenericType<List<Person>>() {});		
	}
	
	private void setupClient(){
		client = ClientBuilder.newClient();		
		webTarget = client.target("http://localhost:8910");
	}

}
