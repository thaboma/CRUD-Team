package za.co.bmw.crud.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.co.bmw.crud.dao.TeamRepository;
import za.co.bmw.crud.dto.EmployeeDto;
import za.co.bmw.crud.dto.TeamDto;
import za.co.bmw.crud.entity.Employee;
import za.co.bmw.crud.entity.Team;
import za.co.bmw.crud.entity.Vehicle;
import za.co.bmw.crud.mapper.TeamMapper;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamServiceImplTest {

	@InjectMocks
	private TeamServiceImpl teamService;

	@Mock
	private TeamRepository teamRepository;
	private Team teamEntity;
	private TeamDto teamDto;

	@BeforeEach
	void setUp() {
		 teamEntity = getTeamEntity(getTeamRequest());
		 teamDto =TeamMapper.mapDto2Team(teamEntity);
	}


	@Test
	void createTeam() {

		teamService.createTeam(teamDto);
		Set<TeamDto> teams =teamService.listTeams("123456");
		Assertions.assertNotNull(ObjectUtils.isNotEmpty(teams),"Teams should not be empty");

	}

	@Test
	void deleteTeam() {

		teamService.deleteTeam(123456L);
		Set<TeamDto> teams =teamService.listTeams("123456");
		Assertions.assertNotNull(ObjectUtils.isEmpty(teams),"Teams should be empty");

	}

	@Test
	void addEmployeesToTeam() {

		Set<EmployeeDto> employees = new HashSet<>();
		EmployeeDto employee = new EmployeeDto();
		employee.setEmployeeId("5");
		employee.setName("Test Employee5");

		EmployeeDto employee2 = new EmployeeDto();
		employee2.setEmployeeId("6");
		employee2.setName("Test Employee6");

		employees.add(employee);
		employees.add(employee2);
		teamEntity.setId(10L);
		teamEntity.getEmployees().addAll(TeamMapper.mapEmployeeDtos2Entities(employees,teamEntity));


		when(teamRepository.findById(10L)).thenReturn(java.util.Optional.ofNullable(teamEntity));
		when(teamRepository.findByTeamManagerId(any())).thenReturn(Arrays.asList(teamEntity));

		teamService.addEmployeesToTeam(10L,employees);
		Set<TeamDto> teams =teamService.listTeams("123456");
		Assertions.assertEquals(teams.stream().findFirst().get().getEmployees().size(), 4);
	}

	@Test
	void listTeams() {
		teamService.createTeam(teamDto);
		when(teamRepository.findByTeamManagerId(any())).thenReturn(Arrays.asList(teamEntity));
		Set<TeamDto> teams =teamService.listTeams("123456");
		Assertions.assertEquals(teams.size(),1);
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