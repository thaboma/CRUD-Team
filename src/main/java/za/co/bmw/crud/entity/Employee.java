package za.co.bmw.crud.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "employee")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee implements Serializable {

		@Id
		@Column(name = "id", nullable = false)
		private Long id;

		@Column(name = "team_id", nullable = false)
		private Long teamId;

		@Column(name = "employee_id", nullable = false)
		private String employeeId;

		@Column(name = "name")
		private String name;

		@Column(name = "Last_name")
		private String lastName;

		@Column(name = "date_of_birth")
		private Date dateOfBirth;

		@Column(name = "created_date")
		private Date createdDate;

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(
				name = "team_id", referencedColumnName = "id", updatable = false, insertable = false

		)
		Team team;

		@Override public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Employee employee = (Employee) o;
			return Objects.equals(employeeId, employee.employeeId);
		}

		@Override public int hashCode() {
			return Objects.hash(employeeId);
		}
}
