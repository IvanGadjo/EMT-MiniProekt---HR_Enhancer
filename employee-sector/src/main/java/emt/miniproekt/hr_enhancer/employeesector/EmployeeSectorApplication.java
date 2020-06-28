package emt.miniproekt.hr_enhancer.employeesector;

import emt.miniproekt.sharedkernel.SharedConfiguration;
import emt.miniproekt.sharedkernel.eventlog.consumer.RemoteEventLogService;
import emt.miniproekt.sharedkernel.port.client.RemoteEventLogServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@Import(SharedConfiguration.class)
public class EmployeeSectorApplication {

    @Autowired
    RemoteEventLogService remoteEventLogService;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeSectorApplication.class, args);
    }

}
