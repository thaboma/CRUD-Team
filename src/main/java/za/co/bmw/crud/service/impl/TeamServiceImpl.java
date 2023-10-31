package za.co.bmw.crud.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import za.co.bmw.crud.dao.TeamRepository;
import za.co.bmw.crud.dto.EmployeeDto;
import za.co.bmw.crud.dto.TeamDto;
import za.co.bmw.crud.entity.Employee;
import za.co.bmw.crud.entity.Team;
import za.co.bmw.crud.mapper.TeamMapper;
import za.co.bmw.crud.service.TeamService;

import java.util.Collection;
import java.util.Set;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired TeamRepository teamRepository;

	@Override
	public void createTeam(TeamDto teamDto) {
		Team team = TeamMapper.mapTeamDto2Entity(teamDto);
		teamRepository.save(team);
	}

	@Override
	public void deleteTeam(Long teamId) {
		teamRepository.deleteById(teamId);
	}

	@Override
	public void addEmployeesToTeam(Long teamId, Set<EmployeeDto> employeeDtos) {
		Team selectedTeam = teamRepository.findById(teamId).orElse(null);

		if (ObjectUtils.isEmpty(selectedTeam)){
			throw new RuntimeException("No team found for the given teamId ="+teamId);
		}

		Set<Employee> employees=TeamMapper.mapEmployeeDtos2Entities(employeeDtos,selectedTeam);
		selectedTeam.getEmployees().addAll(employees);
		teamRepository.save(selectedTeam);
	}

	@Override
	public Set<TeamDto> listTeams(String managerId) {
		Collection<Team> teams= teamRepository.findByTeamManagerId(managerId);
		Set<TeamDto> teamDtos =TeamMapper.mapTeams2Dtos(teams);
		// log
		return teamDtos;
	}
}
