package other;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class DepartmentClient {

    private final RestClient restClient;

    public DepartmentClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public String getDepartment(long id) {

        return restClient
                .get()
                .uri("http://department-service/departments/" + id)
                .retrieve()
                .body(String.class);
    }
}