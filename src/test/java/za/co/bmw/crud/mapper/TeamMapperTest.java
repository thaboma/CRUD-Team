package za.co.bmw.crud.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.bmw.crud.dto.TeamDto;
import za.co.bmw.crud.entity.Employee;
import za.co.bmw.crud.entity.Team;
import za.co.bmw.crud.entity.Vehicle;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class TeamMapperTest {

	private Team teamEntity;
	private TeamDto teamDto;



	@BeforeEach
	void setUp() {
		teamEntity = getTeamEntity(getTeamRequest());
	}

	@Test
	void mapTeamDto2Entity() {
 	    Assertions.assertEquals(teamEntity.getManagerId(), "123456");
		Assertions.assertEquals(teamEntity.getTeamName(), "Test Team Name");
	}

	@Test
	void mapTeam2Dto() {
		teamDto =TeamMapper.mapDto2Team(teamEntity);
		Assertions.assertEquals(teamDto.getManagerId(), "123456");
		Assertions.assertEquals(teamDto.getTeamName(), "Test Team Name");
	}

	@Test
	void mapEmployeeDto2Entity() {
	    Employee employee =teamEntity.getEmployees().stream().findFirst().orElse(new Employee());
		Assertions.assertEquals(employee.getEmployeeId(), "3");
		Assertions.assertEquals(employee.getName(), "Test Employee");

	}


	@Test
	void mapVehicleDto2Entity () {
		Vehicle vehicle =teamEntity.getVehicles().stream().findFirst().orElse(new Vehicle());
		List<String> colours = Arrays.asList("Red","White");
		List<String> models = Arrays.asList("BMW","X5");
		Assertions.assertTrue(colours.contains(vehicle.getColour()));
		Assertions.assertTrue(models.contains(vehicle.getModel()));
	}


	@Test
	void mapEmployeesDtos2Entities() {

		List<Employee> employees =teamEntity.getEmployees().stream().collect(Collectors.toList());
		Assertions.assertEquals(employees.get(1).getEmployeeId(),  "4");
		Assertions.assertEquals(employees.get(1).getName(), "Test Employee2");
	}

	@Test
	void mapvehiclesDtos2Entities() {

		List<Vehicle> vehicles =teamEntity.getVehicles().stream().collect(Collectors.toList());

		List<String> colours = Arrays.asList("Red","White");
		List<String> models = Arrays.asList("BMW","X5");

		Assertions.assertTrue(models.contains(vehicles.get(1).getModel()));
		Assertions.assertTrue(colours.contains(vehicles.get(1).getColour()));
	}


	@Test
	void emptyIfNull() {
		Collection<Employee> employees = TeamMapper.emptyIfNull(null);
		Assertions.assertEquals(employees.size(),0);
	}

	private TeamDto getTeamRequest() {
		TeamDto teamDto = new TeamDto();
		teamDto.setTeamName("Test Team Name");
		teamDto.setManagerId("123456");
		teamDto.setCreatedDate(new Date());

		Set<Employee> employees = new HashSet<>();
		Employee employee = new Employee();
		employee.setEmployeeId("3");
		employee.setName("Test Employee");

		Employee employee2 = new Employee();
		employee2.setEmployeeId("4");
		employee2.setName("Test Employee2");

		employees.add(employee);
		employees.add(employee2);

		Set<Vehicle> vehicles = new HashSet<>();
		Vehicle vehicle= new Vehicle();
		vehicle.setName("BMW");
		vehicle.setColour("Red");
		vehicle.setModel("435 i");

		Vehicle vehicle2= new Vehicle();
		vehicle2.setName("BMW");
		vehicle2.setColour("White");
		vehicle2.setModel("X5");

		vehicles.add(vehicle);
		vehicles.add(vehicle2);


		teamDto.setEmployees(TeamMapper.mapEmployeeEntities2Dtos(employees));
		teamDto.setVehicles(TeamMapper.mapVehicleEntities2Dtos(vehicles));

		return teamDto;
	}

	private Team getTeamEntity(TeamDto teamDto) {
		return TeamMapper.mapDto2Team(teamDto);
	}
}