package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@RestController
@RequestMapping("/Prase")
public class Phase3 {
	
	@PostMapping("/call")
	public List<String> call(@RequestBody String xml) throws Exception {
		
		DocumentBuilderFactory doc = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder builder = doc.newDocumentBuilder();
		
		Document d = builder.parse(new java.io.ByteArrayInputStream(xml.getBytes()));
		System.out.println("value of d "+d);
		Element root = d.getDocumentElement();
		System.out.println("Attribute name "+root.getAttribute("id"));
		System.out.println(root.getTagName());
		
		
		NodeList nodes =
		        root.getElementsByTagName("name");
		
		NodeList employees =
		        root.getElementsByTagName("employee");
		
		System.out.println(employees.getLength());
		String id = root.getAttribute("id");
		System.out.print(id);
		
		if(id != null) {
			System.out.print("true");
			
		}else {
			System.out.print("false");
		}
		
		Node n = employees.item(0);
		System.out.println("First employee details " +n.getTextContent());
		Node n1 = employees.item(1);
		System.out.println("second employee details " +n1.getTextContent());
		System.out.println(nodes.getLength());
		ArrayList<String> list = new ArrayList<>();
		
		ArrayList<String> ids = new ArrayList<>();
		ArrayList<String> names = new ArrayList<>();
		ArrayList<String> namesdept = new ArrayList<>();
		
		
		
		for (int i=0;i < employees.getLength();i++) {
			
			 Element employee  = (Element) employees.item(i);
			 

			    String ido = employee.getElementsByTagName("id").item(0).getTextContent().trim();
			    String name = employee.getElementsByTagName("name").item(0).getTextContent().trim();
			    String department = employee.getElementsByTagName("department").item(0).getTextContent().trim();
			    names.add(name);
			    namesdept.add(name+" - " +department);
			    ids.add(ido);
			    list.add(ido + " " + name + " " + department);
		}
		
//		for (int i = 0; i < employees.getLength(); i++) {
//
//		    Element employee = (Element) employees.item(i);
//
//		    NodeList children = employee.getChildNodes();
//
//		    for (int j = 0; j < children.getLength(); j++) {
//
//		        Node child = children.item(j);
//
//		        if (child.getNodeType() == Node.ELEMENT_NODE) {
//
//		            System.out.println(
//		                child.getNodeName() + " = " +
//		                child.getTextContent().trim()
//		            );
//		        }
//		    }
//		}
		
		
		 return list;
		 
//		 
//		 <Company id="C100">
//
//		    <name>ABC Technologies</name>
//
//		    <employees>
//
//		        <employee>
//		            <id>101</id>
//		            <name>John</name>
//		            <department>IT</department>
//		        </employee>
//
//		        <employee>
//		            <id>102</id>
//		            <name>Raj</name>
//		            <department>HR</department>
//		        </employee>
//
//		    </employees>
//
//		</Company>
//		
//		Document
//		  │
//		  │  ← Entire XML document
//		  │
//		  └── Company                         ← Element + ROOT Element
//		       │
//		       ├── id="C100"                  ← Attribute
//		       │
//		       ├── name                       ← Element / Node
//		       │    │
//		       │    └── "ABC Technologies"    ← Text Node
//		       │
//		       └── employees                  ← Element / Node
//		            │
//		            ├── employee              ← Element / Node
//		            │    │
//		            │    ├── id               ← Element / Node
//		            │    │    └── "101"       ← Text Node
//		            │    │
//		            │    ├── name             ← Element / Node
//		            │    │    └── "John"      ← Text Node
//		            │    │
//		            │    └── department       ← Element / Node
//		            │         └── "IT"        ← Text Node
//		            │
//		            └── employee              ← Element / Node
//		                 │
//		                 ├── id               ← Element / Node
//		                 │    └── "102"       ← Text Node
//		                 │
//		                 ├── name             ← Element / Node
//		                 │    └── "Raj"       ← Text Node
//		                 │
//		                 └── department       ← Element / Node
//		                      └── "HR"        ← Text Node
//		
		
		
	}
	
	

}
