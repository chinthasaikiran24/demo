package com.example.demo;

import java.util.Iterator;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@RestController
@RequestMapping("/Phase4")
public class Phase4 {
	
	private final ObjectMapper objectMapper;
	private final XmlMapper xmlMapper;
	
	public Phase4(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
		this.xmlMapper = new XmlMapper();
	}
	
	@PostMapping("/obj")
	public String call(@RequestBody String xml) throws Exception{
		
		JsonNode root = xmlMapper.readTree(xml);
		System.out.println(root.toPrettyString());
		
		//JsonNode element = findElement(root,"employee");
		
		ObjectNode obj = objectMapper.createObjectNode();
		
		JsonNode body = root.get("Body");
		JsonNode CreateEmployeeRequest = body.get("CreateEmployeeRequest");
		
		
		JsonNode companyName = convert(CreateEmployeeRequest);
		
//		
//		 obj.put("Company name ",companyName.asText());
//		 obj.put("Employee id",element.get("id").asText());
//		 obj.put("Employee name",element.get("name").asText());
//		 obj.put("Employee department",element.get("department").asText());
//		 System.out.println(obj.toPrettyString());
//		
		return companyName.toPrettyString();
		
	}
	
//	public JsonNode findElement(JsonNode node, String element) {
//		if (node.isObject()) {
//		
//		Iterator<Map.Entry<String ,JsonNode>> fields = node.fields();
//		
//		while(fields.hasNext()) {
//			Map.Entry<String, JsonNode> field = fields.next();
//			
//			 if (field.getValue().isObject()) {
//
//	                System.out.println(field.getKey() + " is an object");
//	            }
//			 
//			 if(field.getKey().equals("address")) {
//					
//					field.getValue();
//					System.out.println("Address object " + field.getValue());
//				}
//			 
//			if(field.getKey().equals(element)) {
//				
//				return field.getValue();
//			}
//			
//			JsonNode result = findElement(field.getValue(),element);
//			if(result != null) {
//				return result;
//			}
//		}
//	}
//		return null;
//	}

	
	public JsonNode convert(JsonNode node) {
		
		ObjectNode obj = objectMapper.createObjectNode();
		
		
			
			Iterator<Map.Entry<String , JsonNode>> fields = node.fields();
			
			while(fields.hasNext()) {
				Map.Entry<String , JsonNode> field = fields.next();
				
				String getKey = field.getKey();
				JsonNode value = field.getValue();
				
				if(value.isObject()) {
					
					ObjectNode child = (ObjectNode) convert(value);
					obj.set(getKey, child);
					
				}
				else {
					if(value.isArray()) {
						ArrayNode array = objectMapper.createArrayNode();
						
						for(JsonNode item : value) {

			                if (item.isObject()) {
                                 
			                	array.add(convert(item));
						}else {
							array.add(item.asText());
						}
					}
						obj.set(getKey, array);
				}
					else {

			            obj.put(getKey, value.asText());
			        }
			}
			
				//return obj;
		
	}
			return obj;
	}
	
	
	
	/** only when XML is Object not Arrays 
	 * 
	 * public JsonNode convertXml(String xml) throws Exception {
    JsonNode root = xmlMapper.readTree(xml);

    JsonNode element = convertNode(root);

    return element;
}

public JsonNode convertNode(JsonNode node){

        ObjectNode obj = objectmapper.createObjectNode();

        Iterator<Map.Entry<String , JsonNode>> fields = node.fields();

        while(fields.hasNext()){
           Map.Entry<String , JsonNode> result = fields.next();

           String getKey = result.getKey();
           JsonNode getValue = result.getValue();

           if(getValue.isObject()){
              ObjectNode child = (ObjectNode) convertNode(getValue);
              obj.set(getKey,child);
            

           }
           
            if(getValue.isArray()){
            ArrayNode array = objectmapper.createArrayNode();
            for(JsonNode item : getValue){
              if(item.isObject){
                array.add(convertNode(item));
              }
              else{
                array.put(item.asText());
              }
            }
           obj.set(getKey,array);
        }
        else {

    obj.put(getKey, value.asText());
}

        }
        return obj;
    }
	 */
	
	
}


