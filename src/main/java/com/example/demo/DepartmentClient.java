//
//package com.example.demo;
//
//import java.util.concurrent.CompletableFuture;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import io.github.resilience4j.retry.annotation.Retry;
//import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
//
//@FeignClient(name = "department-service")
//public interface DepartmentClient {
//
//    @Retry(
//            name = "departmentServiceRetry",
//            fallbackMethod = "departmentFallback"
//    )
//    @CircuitBreaker(
//            name = "departmentService",
//            fallbackMethod = "departmentFallback"
//    )
//    @TimeLimiter(
//            name = "departmentServiceTimeout",
//            fallbackMethod = "departmentFallback"
//    )
//    @GetMapping("/departments/{id}")
//    CompletableFuture<String> getDepartment(
//            @PathVariable("id") long id);
//
//    default CompletableFuture<String> departmentFallback(
//            long id,
//            Throwable ex) {
//
//        System.out.println(
//                "Fallback called because Department Service failed: "
//                + ex.getMessage()
//        );
//
//        return CompletableFuture.completedFuture(
//                "Department Service is currently unavailable"
//        );
//    }
//}
//

package com.example.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "department-service")
public interface DepartmentClient {

    @GetMapping("/departments/{id}")
    String getDepartment(@PathVariable("id") long id);
}
