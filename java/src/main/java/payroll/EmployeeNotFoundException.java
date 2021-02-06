package payroll;

import java.util.UUID;

class EmployeeNotFoundException extends RuntimeException {

  EmployeeNotFoundException(UUID id) {
    super("Could not find employee " + id);
  }
}
