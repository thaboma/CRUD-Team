package za.co.bmw.crud.mapper;

import lombok.NonNull;
import za.co.bmw.crud.dto.EmployeeDto;
import za.co.bmw.crud.dto.TeamDto;
import za.co.bmw.crud.dto.VehicleDto;
import za.co.bmw.crud.entity.Employee;
import za.co.bmw.crud.entity.Team;
import za.co.bmw.crud.entity.Vehicle;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public class TeamMapper {
	public static Team mapTeamDto2Entity(@NonNull TeamDto teamDto) {
		return mapDto2Team(teamDto);
	}
	public static Team mapDto2Team(TeamDto teamDto) {
		Long id = System.currentTimeMillis();
		Team team= Team
				.builder()
				.id(id)
				.managerId(teamDto.getManagerId())
				.teamName(teamDto.getTeamName())
				.createdDate(teamDto.getCreatedDate())
				.build();


		Team outTeam=  Team
				.builder().vehicles(mapVehicleDtos2Entities(teamDto.getVehicles(),team))
				.employees(mapEmployeeDtos2Entities(teamDto.getEmployees(),team))
				.id(id)
				.managerId(teamDto.getManagerId())
				.teamName(teamDto.getTeamName())
				.createdDate(teamDto.getCreatedDate())
				.build();

		return outTeam;
	}

	public static Employee mapEmployeeDto2Entity(EmployeeDto employee,Team team) {
		Long id = System.currentTimeMillis();
		return Employee
				.builder()
				.id(id)
				.teamId(team.getId())
				.team(team)
				.employeeId(employee.getEmployeeId())
				.dateOfBirth(employee.getDateOfBirth())
				.name(employee.getName())
				.lastName(employee.getLastName())
				.build();
	}

	public static Vehicle mapVehicleDto2Entity(VehicleDto vehicleDto,Team team) {
		Long id = System.currentTimeMillis();
		return Vehicle
				.builder()
				.id(id)
				.team(team)
				.teamId(team.getId())
				.model(vehicleDto.getModel())
				.colour(vehicleDto.getColour())
				.vinNumber(vehicleDto.getVinNumber())
				.name(vehicleDto.getName())
				.acquiredDate(vehicleDto.getAcquiredDate())
				.build();

	}
	public static EmployeeDto mapEmployeeEntity2Dto(Employee employee) {
		return EmployeeDto
				.builder()
				.createdDate(new Date())
				.employeeId(employee.getEmployeeId())
				.dateOfBirth(employee.getDateOfBirth())
				.name(employee.getName())
				.lastName(employee.getLastName())
				.build();
	}

	public static VehicleDto mapVehicleEntity2Dto(Vehicle vehicle) {
		return VehicleDto
				.builder()
				.model(vehicle.getModel())
				.colour(vehicle.getColour())
				.vinNumber(vehicle.getVinNumber())
				.name(vehicle.getName())
				.acquiredDate(vehicle.getAcquiredDate())
				.build();
	}


	public static TeamDto mapDto2Team(Team team) {
		return TeamDto
				.builder()
				.teamId(team.getId())
				.managerId(team.getManagerId())
				.teamName(team.getTeamName())
				.vehicles(mapVehicleEntities2Dtos(team.getVehicles()))
				.employees(mapEmployeeEntities2Dtos(team.getEmployees()))
				.createdDate(team.getCreatedDate())
				.build();
	}

	public static Set<Vehicle> mapVehicleDtos2Entities(Set<VehicleDto> vehicleDtos,Team team) {
		return vehicleDtos.parallelStream().map(v->mapVehicleDto2Entity(v,team)).collect(Collectors.toSet());
	}

	public static Set<Employee> mapEmployeeDtos2Entities(Set<EmployeeDto> employeeDtos,Team team) {
		return employeeDtos.parallelStream().map(e->mapEmployeeDto2Entity(e,team)).collect(Collectors.toSet());
	}

	public static Set<VehicleDto> mapVehicleEntities2Dtos(Set<Vehicle> vehicles) {
		return vehicles.parallelStream().map(v->mapVehicleEntity2Dto(v)).collect(Collectors.toSet());
	}

	public static Set<EmployeeDto> mapEmployeeEntities2Dtos(Set<Employee> employees) {
		return employees.parallelStream().map(e->mapEmployeeEntity2Dto(e)).collect(Collectors.toSet());
	}

	public static Set<TeamDto> mapTeams2Dtos(Collection<Team> teams) {
		return teams.parallelStream().map(t-> mapDto2Team(t)).collect(Collectors.toSet());
	}

	public static <T> Collection<T> emptyIfNull(Collection<T> collection) {
		return collection == null ? Collections.emptyList() : collection;
	}
}
