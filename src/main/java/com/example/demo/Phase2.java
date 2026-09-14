package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.databind.JsonNode;

import service.XmlToJsonService;


@RestController
@RequestMapping("/soap")

public class Phase2 {
	
private final XmlToJsonService xmlToJsonService;
	
	public Phase2(XmlToJsonService xmlToJsonService) {
        this.xmlToJsonService = xmlToJsonService;
    }
	
	@PostMapping(
	        value = "/receive",
	        consumes = MediaType.APPLICATION_XML_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE
	    )
	    public JsonNode receiveSoap(@RequestBody String xml) throws Exception {

		return xmlToJsonService.convert(xml);
	    }

}
