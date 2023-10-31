package za.co.bmw.crud.dto;

import lombok.*;
import za.co.bmw.crud.entity.Employee;
import za.co.bmw.crud.entity.Vehicle;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamDto {
	@NotNull
	private String managerId;
	private long teamId;
	private String teamName;
	private Date createdDate;
	private Set<EmployeeDto> employees = new HashSet<>();
	private Set<VehicleDto> vehicles = new HashSet<>();
}

