package za.co.bmw.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
	@NotNull
	private String employeeId;
	private String name;
	private Date createdDate;
	private String lastName;
	private Date dateOfBirth;

}

