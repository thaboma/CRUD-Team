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
public class VehicleDto {
	private String model;
	private String colour;
	private String name;
	@NotNull
	private String vinNumber;
	private Date acquiredDate;
}
