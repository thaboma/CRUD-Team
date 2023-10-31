package za.co.bmw.crud.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "team")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team implements Serializable {

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "manager_id", nullable = false)
	private String managerId;

	@Column(name = "team_name")
	private String teamName;

	@Column(name = "created_date")
	private Date createdDate;

	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	private Set<Employee> employees = new HashSet<>();

	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	private Set<Vehicle> vehicles = new HashSet<>();

}
