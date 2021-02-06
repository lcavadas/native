package payroll;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GenerateUUIDListener extends AbstractMongoEventListener<Employee> {
 
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Employee> event) {
        Employee employee = event.getSource();
        if (employee.isNew()) {
            employee.setId(UUID.randomUUID());
        }
    }
 
}
