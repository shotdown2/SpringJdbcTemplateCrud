package com.jdbc.ws;

import javax.xml.ws.Endpoint;

import com.jdbc.services.UserServiceImpl;

public class PublicaWebService {

	public static void main(String[] args) {

		UserServiceImpl service = new UserServiceImpl();

		String url = "http://localhost:8080/usersws";

		System.out.println("Service rodando " + url + "?wsdl");

		Endpoint.publish(url, service);

	}

}
