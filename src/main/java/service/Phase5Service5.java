package service;

import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

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
		
		JsonNode employeeId = request.get("employeeId");

		if (employeeId == null) {

		    System.out.println("employeeId is missing");

		} else {

		    String id = employeeId.asText();

		    if (id.trim().isEmpty()) {

		        System.out.println("employeeId is empty");

		    } else {

		        try {

		            int number = Integer.parseInt(id);

		            if (number <= 0) {

		                System.out.println("employeeId must be positive");

		            } else {

		                System.out.println("employeeId is valid");

		            }

		        } catch (NumberFormatException e) {

		            System.out.println("employeeId must be an integer");

		        }
		    }
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
