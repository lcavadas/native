package payroll

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "employees")
class Employee : Persistable<UUID?> {
  @Id
  private var id: UUID? = null
  var name: String? = null
  var role: String? = null

  override fun getId(): UUID? {
    return id
  }

  @Transient
  @JsonIgnore
  override fun isNew(): Boolean {
    return getId() == null
  }

  fun setId(id: UUID?) {
    this.id = id
  }

  override fun toString(): String {
    return "Employee{id=$id, name='$name', role='$role'}"
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Employee

    if (id != other.id) return false
    if (name != other.name) return false
    if (role != other.role) return false

    return true
  }

  override fun hashCode(): Int {
    var result = id?.hashCode() ?: 0
    result = 31 * result + (name?.hashCode() ?: 0)
    result = 31 * result + (role?.hashCode() ?: 0)
    return result
  }
}
