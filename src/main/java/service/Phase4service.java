package service;

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

public class Phase4service {
	
	private final ObjectMapper objectMapper;
    private final XmlMapper xmlMapper;

    public Phase4service(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.xmlMapper = new XmlMapper();
    }
    
    
        public JsonNode convertSoapXml(@RequestBody String xml) throws Exception {

            JsonNode root = xmlMapper.readTree(xml);
            System.out.println(root.toPrettyString());

            ObjectNode obj = objectMapper.createObjectNode();

            JsonNode body = root.get("Body");
            JsonNode CreateEmployeeRequest = body.get("CreateEmployeeRequest");

            JsonNode companyName = convert(CreateEmployeeRequest);

            return companyName;
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
