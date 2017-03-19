package com.dimendiondata.server_management.model;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

public class XMLConverter {

	private Marshaller marshaller;
	private Unmarshaller unmarshaller;
	

	public Object convertFromXMLToObject(String path) throws IOException {

		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(path);
			return getUnmarshaller().unmarshal(new StreamSource(inputStream));
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}
	
	public Marshaller getMarshaller() {
		return marshaller;
	}

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}
	
}
