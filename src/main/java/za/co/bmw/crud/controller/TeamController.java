package za.co.bmw.crud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import za.co.bmw.crud.dto.EmployeeDto;
import za.co.bmw.crud.dto.TeamDto;
import za.co.bmw.crud.service.impl.TeamServiceImpl;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/team")
@CrossOrigin("*")
@Slf4j
public class TeamController {

	@Autowired
	private TeamServiceImpl teamService;

	@PostMapping("/create")
	public void createTeam(@Validated @RequestBody TeamDto teamDto) {
		teamService.createTeam(teamDto);
		log.info("Manager [{}] successfully created team {} ", teamDto.getManagerId(), teamDto.getTeamName());
	}

	@DeleteMapping("/delete")
	public void deleteTeam(@RequestParam Long teamId) {
		teamService.deleteTeam(teamId);
		log.info("Team with Id {} was successfully ", teamId);
	}

	@PutMapping("/add-employee")
	public void addEmployeesToTeam(Long teamId, @Validated @RequestBody Set<EmployeeDto> employeeDtos) {
		teamService.addEmployeesToTeam(teamId, employeeDtos);
		String employeeIds = employeeDtos.stream().map(e -> e.getEmployeeId()).collect(Collectors.joining(","));
		log.info("Employee [{}] successfully created team {} ", employeeIds);
	}

	@GetMapping("/list-by-managerId")
	public Set<TeamDto> listTeams(String managerId) {
		Set<TeamDto> managerTeams = teamService.listTeams(managerId);
		log.info("Teams for managerId {} were fetched successfully ", managerId);
		return managerTeams;
	}

}
