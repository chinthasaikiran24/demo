package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Phase3Service {

    public static void main(String[] args) throws Exception {

        String xml = """
                <Company id="C100">

                    <name>ABC Technologies</name>

                    <employee>

                        <id>101</id>

                        <name>John</name>

                        <department>IT</department>

                    </employee>

                </Company>
                """;

        // XML -> JsonNode
        XmlMapper xmlMapper = new XmlMapper();

        JsonNode root = xmlMapper.readTree(xml);

        // Company ID
        JsonNode companyId = root.get("@id");
        System.out.println("Company ID = " + companyId.asText());

        // Company Name
        JsonNode companyName = root.get("name");
        System.out.println("Company Name = " + companyName.asText());

        // Employee
        JsonNode employee = root.get("employee");

        // Employee ID
        String employeeId = employee.get("id").asText();

        // Employee Name
        String employeeName = employee.get("name").asText();

        // Department
        String department = employee.get("department").asText();

        System.out.println("Employee ID = " + employeeId);
        System.out.println("Employee Name = " + employeeName);
        System.out.println("Department = " + department);
    }
}