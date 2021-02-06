package payroll

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent
import org.springframework.stereotype.Component

@Component
class GenerateUUIDListener extends AbstractMongoEventListener<Employee> {

  @Override
  void onBeforeConvert(BeforeConvertEvent<Employee> event) {
    Employee employee = event.getSource()
    if (employee.isNew()) {
      employee.setId(UUID.randomUUID())
    }
  }

}
