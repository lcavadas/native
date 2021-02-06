package payroll

import java.util.*

internal class EmployeeNotFoundException(id: UUID) : RuntimeException("Could not find employee $id")
