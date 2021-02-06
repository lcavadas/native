package payroll

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "employees")
class Employee implements Persistable<UUID> {
  @Id
  UUID id
  String name
  String role

  @Transient
  @JsonIgnore
  @Override
  boolean isNew() {
    return (id == null)
  }
}
