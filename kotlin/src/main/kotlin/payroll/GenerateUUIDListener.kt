package payroll

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent
import org.springframework.stereotype.Component
import java.util.*

@Component
class GenerateUUIDListener : AbstractMongoEventListener<Employee?>() {
  override fun onBeforeConvert(event: BeforeConvertEvent<Employee?>) {
    val employee = event.source
    if (employee.isNew) {
      employee.id = UUID.randomUUID()
    }
  }
}
