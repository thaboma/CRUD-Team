package za.co.bmw.crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.bmw.crud.entity.Team;

import java.util.Collection;

@Repository("teamRepository")
public interface TeamRepository extends JpaRepository<Team, Long> {

	@Query("FROM Team AS t WHERE t.managerId = ?1 and t.teamName =?2 ")
	Collection<Team> findByTeamManagerIdAndTeamName(String employeeId, String teamName);

	@Query("FROM Team AS t WHERE t.managerId = ?1 ")
	Collection<Team> findByTeamManagerId(String employee_id);

}
