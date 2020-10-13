package sbr.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TeamController {

	@Autowired
	private TeamRepo repo;

	@GetMapping("/teams")
	public List<Team> getAllTeams() {
		return repo.findAll();
	}

	@GetMapping("/teams/{id}")
	public Team getTeam(@PathVariable long id) {
		return repo.findById(id).orElseThrow(() -> new TeamException(id));
	}

	@PostMapping("/teams")
	public Team createTeam(@RequestBody Team team) {
		return repo.save(team);
	}

	@PutMapping("/teams/{id}")
	public Team editTeam(@RequestBody Team newTeam, @PathVariable Long id) {
		return repo.findById(id).map(team -> {
			team.setTeamName(newTeam.getTeamName());
			return repo.save(team);
		}).orElseGet(() -> {
			newTeam.setId(id);
			return repo.save(newTeam);
		});
	}
	
	@PatchMapping("/teams/{id}")
	public Team partialEdit(@RequestBody Team newTeam, @PathVariable Long id) {
		return repo.findById(id).map(team -> {
			if (newTeam.getTeamName() != null) {
                team.setTeamName(newTeam.getTeamName());
            }
            return repo.save(team);
        }).orElseThrow(() -> new TeamException(id));
	}

	@DeleteMapping("/teams/{id}")
	public void deleteTeam(@PathVariable Long id) {
		repo.deleteById(id);
	}

}
