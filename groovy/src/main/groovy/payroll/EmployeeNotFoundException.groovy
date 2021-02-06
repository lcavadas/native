package payroll

class EmployeeNotFoundException extends RuntimeException {
  EmployeeNotFoundException(UUID id) {
    super("Could not find employee " + id)
  }
}
