package service;

import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class Phase5Service5 {
	
	private final ObjectMapper objectMapper;
	private final XmlMapper xmlMapper;
	
	public Phase5Service5(ObjectMapper objectMapper) {
		this.objectMapper=objectMapper;
		this.xmlMapper= new XmlMapper();
	}
	
	public String convertXml(String xml) throws Exception {
		
		JsonNode request = xmlMapper.readTree(xml);
		System.out.println(request.toPrettyString());
		if (request.has("Employee")) {
		    request = request.get("Employee");
		}
		
		JsonNode age = request.get("age");
		
		ObjectNode response = objectMapper.createObjectNode();
		response.put("status", "FAILED");
		ArrayNode errors = objectMapper.createArrayNode();
			
		if (age == null || age.isEmpty()) {

			ObjectNode ageExp = objectMapper.createObjectNode();
			ageExp.put("field", "age");
			ageExp.put("message", "Age is Missing in Request");
            errors.add(ageExp);
           
		} else {

		    String id = age.asText();

		    if (id.trim().isEmpty()) {

		        System.out.println("age is empty");

		    } else {

		        try {

		            int number = Integer.parseInt(id);

		            if (number < 18) {

		            	ObjectNode errorage = objectMapper.createObjectNode();
		            	errorage.put("field", "age");
		            	errorage.put("message", "Age must be Greater than 18");
                        errors.add(errorage);
                       
                        
		            } else {

		                System.out.println("age is valid");

		            }

		        } catch (NumberFormatException e) {

		        	ObjectNode ageExcep = objectMapper.createObjectNode();
		        	ageExcep.put("field", "age");
		        	ageExcep.put("message", "Age must be 18 or greater");
                    errors.add(ageExcep);
		        }
		    }
		}
		
		JsonNode employeeId = request.get("employeeId");

		if (employeeId == null) {

		    ObjectNode employeeobj = objectMapper.createObjectNode();
		    
		    employeeobj.put("failed", "employee id");
		    employeeobj.put("Message", "Employee id Must be Present in Request");
		    errors.add(employeeobj);
		} else {

		    String id = employeeId.asText();

		    if (id.trim().isEmpty()) {

		    	ObjectNode employeeobjempty = objectMapper.createObjectNode();
			    
		    	employeeobjempty.put("failed", "Employee id");
		    	employeeobjempty.put("Message", "Employee id cannot be Empty");
		    	errors.add(employeeobjempty);

		    } else {

		        try {

		            int number = Integer.parseInt(id);

		            if (number <= 0) {

		            	ObjectNode EmpIdInt = objectMapper.createObjectNode();
		            	EmpIdInt.put("field", "Employee Id");
		            	EmpIdInt.put("message", "Employee Id Must be Positive");
	                    errors.add(EmpIdInt);

		            } else {

		                System.out.println("employeeId is valid");

		            }

		        } catch (NumberFormatException e) {

		        	ObjectNode EmpIdIntExcep = objectMapper.createObjectNode();
		        	EmpIdIntExcep.put("field", "Employee Id");
		        	EmpIdIntExcep.put("message", "Employee Id Must be an Integer");
                    errors.add(EmpIdIntExcep);

		        }
		    }
		}
		
		JsonNode employee = request.get("employee");
		if (employee == null || employee.asText().trim().isEmpty() || employee.isEmpty()) {

		    ObjectNode employeenode = objectMapper.createObjectNode();
		    employeenode.put("Feild" , "error");
		    employeenode.put("Message ", "Employee cannot be Empty");
		    errors.add(employeenode);

		} else {
		JsonNode department = employee.get("department");
		if(department== null) {
			 ObjectNode departmentobj = objectMapper.createObjectNode();
			 departmentobj.put("Feild" , "error");
			 departmentobj.put("Message ", "Department cannot be Empty");
			    errors.add(departmentobj);
		}
		JsonNode address = employee.get("address");
		if ( address == null || address.asText().trim().isEmpty() || address.isEmpty()) {

			 ObjectNode addressobj = objectMapper.createObjectNode();
			 addressobj.put("Feild" , "error");
			 addressobj.put("Message ", "Address cannot be Empty");
			    errors.add(addressobj);

		}else {
		JsonNode city = address.get("city");
		if(city== null) {
			 ObjectNode cityobj = objectMapper.createObjectNode();
			 cityobj.put("Feild" , "error");
			 cityobj.put("Message ", "City cannot be Empty");
			    errors.add(cityobj);

		}
	
		
		}
		}
		if (errors.size() > 0) {

	        response.set("errors", errors);

	        return response.toPrettyString();
	    }
		
		JsonNode converted = convert(request);
		return converted.toPrettyString();
		
		
	}
	  public JsonNode convert(JsonNode node) {

          ObjectNode obj = objectMapper.createObjectNode();

          Iterator<Map.Entry<String, JsonNode>> fields = node.fields();

          while (fields.hasNext()) {

              Map.Entry<String, JsonNode> field = fields.next();

              String getKey = field.getKey();
              JsonNode value = field.getValue();

              if (value.isObject()) {

                  ObjectNode child = (ObjectNode) convert(value);
                  obj.set(getKey, child);

              } else {

                  if (value.isArray()) {

                      ArrayNode array = objectMapper.createArrayNode();

                      for (JsonNode item : value) {

                          if (item.isObject()) {
                              array.add(convert(item));
                          } else {
                              array.add(item.asText());
                          }
                      }

                      obj.set(getKey, array);

                  } else {

                      obj.put(getKey, value.asText());
                  }
              }
          }

          return obj;
      }

}
