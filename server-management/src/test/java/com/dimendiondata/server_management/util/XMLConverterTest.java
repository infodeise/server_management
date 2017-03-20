package com.dimendiondata.server_management.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.oxm.XmlMappingException;

import com.dimendiondata.server_management.model.Server;

public class XMLConverterTest {
	ClassPathXmlApplicationContext context ;
	XMLConverter converter;
	
	@Before
	public void setUp() {
		context = new ClassPathXmlApplicationContext("spring.xml");
		converter = (XMLConverter) context.getBean("XMLConverter");
	}
	
	@Test
	public void convertValidXMLServer() {
		Server server = new Server();
		String path = "src/test/resources/server.xml";
		
		try {
			server = (Server)converter.convertFromXMLToObject(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(server.getName(), "server test" );
		assertEquals(server.getDescription(), "server description");
	}
	
	@Test(expected = XmlMappingException.class)
	public void convertInvalidXMLServerMustReturnException() {
		String path = "src/test/resources/invalidServer.xml";
		try {
			Server server = (Server)converter.convertFromXMLToObject(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
