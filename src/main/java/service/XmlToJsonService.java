package service;

import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class XmlToJsonService {
	
	private final ObjectMapper objectMapper;
	private final XmlMapper xmlMapper;
	
	public  XmlToJsonService(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
		this.xmlMapper = new XmlMapper();
	}
	
	public JsonNode convert(String xml ) throws Exception{
		
		JsonNode element = xmlMapper.readTree(xml);
		 System.out.println("XML as JSON tree:");
		    System.out.println(element.toPrettyString());
		
		    
		  JsonNode root = findElement(element,"Body");
		  
		  Iterator<JsonNode> body = root.elements();
		  
		  if(!body.hasNext()) {
	        throw new Exception("SOAP IS EMPTY");		  
		  }
		  
		  JsonNode go = body.next();
		  
		  ObjectNode response = objectMapper.createObjectNode();
		  
		  Iterator<Map.Entry<String, JsonNode>> fields =
	                response.fields();
		  
		  while(fields.hasNext()) {
			  Map.Entry<String, JsonNode> field =
	                    fields.next();
			  
			  response.set(field.getKey(), field.getValue());
		  }
		  
		  return response;
	
	}
	
	public JsonNode findElement(JsonNode node, String elementName) {
		if (node.isObject()) {
			
			Iterator<Map.Entry<String,JsonNode>> fields  = node.fields();
			
			while(fields.hasNext()) {
				
				Map.Entry<String, JsonNode> field = fields.next();
				if(field.getKey().equals(elementName)) {
					return field.getValue();
				}
				
				JsonNode result = findElement(field.getValue(),elementName);
				
				if(result != null) {
					return result;
				}
				
			}
		}
		if (node.isArray()) {

            for (JsonNode child : node) {

                JsonNode result =
                        findElement(child, elementName);

             if (result != null) {
                    return result;
                }
            }
        }
		return null;
	}
//	public JsonNode convert(String xml) throws Exception {
//
//        JsonNode root = xmlMapper.readTree(xml);
//
//        return root;

}

