package za.co.bmw.crud.service;

import za.co.bmw.crud.dto.EmployeeDto;
import za.co.bmw.crud.dto.TeamDto;

import java.util.Set;

public interface TeamService {

 void createTeam(TeamDto teamDto);

 void deleteTeam(Long teamId);

 void addEmployeesToTeam(Long teamId , Set<EmployeeDto> employeeDtos);

 Set<TeamDto> listTeams(String managerId);

}
