package payroll;

import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
class EmployeeController {

  private final EmployeeRepository repository;
  private RabbitTemplate template;

  EmployeeController(EmployeeRepository repository, RabbitTemplate template) {
    this.repository = repository;
    this.template = template;
  }

  @GetMapping("/employees")
  List<Employee> all() {
    template.convertAndSend("native", "employee.list", "list!!!!");
    return repository.findAll();
  }

  @PostMapping("/employees")
  Employee newEmployee(@RequestBody Employee newEmployee) {
    return repository.save(newEmployee);
  }

  @GetMapping("/employees/{id}")
  Employee one(@PathVariable UUID id) {
    return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
  }

  @PutMapping("/employees/{id}")
  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable UUID id) {
    Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    employee.setName(newEmployee.getName());
    employee.setRole(newEmployee.getRole());
    return repository.save(employee);
  }

  @DeleteMapping("/employees/{id}")
  void deleteEmployee(@PathVariable UUID id) {
    repository.deleteById(id);
  }

  @RabbitListener(bindings = {@QueueBinding(value = @Queue("native-java"), exchange = @Exchange(value = "native", type = "topic"), key = "employee.list")})
  void listed(Message<String> message) {
    LoggerFactory.getLogger(this.getClass()).info(message.getPayload());
  }
}
