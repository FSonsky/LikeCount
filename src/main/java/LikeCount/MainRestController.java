package LikeCount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainRestController {

    @Autowired
    TeamRepository teamRepository;

    @GetMapping("/getTeams")
    public String getTeams() {
        List<Team> teams = (List<Team>) teamRepository.findAll();

        return jsonArrayFromList(teams);
    }

    @GetMapping("/getLikesByTeamId/{id}")
    public int getLikesById(@PathVariable Long id) {
        Team team = teamRepository.findById(id).orElse(null);

        if(team == null) {
            return -1;
        }

        return team.getLikes();
    }

    @GetMapping("/getLikesByTeamName/{name}")
    public int getLikesByName(@PathVariable String name) {
        Team team = teamRepository.findByNameIgnoreCase(name);

        if(team == null) {
            return -1;
        }

        return team.getLikes();
    }

    @GetMapping("/likeTeamById/{id}")
    public int likeById(@PathVariable Long id) {
        Team team = teamRepository.findById(id).orElse(null);

        if(team == null) {
            return -1;
        }

        team.setLikes(team.getLikes() + 1);
        teamRepository.save(team);

        return team.getLikes();
    }

    @GetMapping("/likeTeamByName/{name}")
    public int likeTeamByName(@PathVariable String name) {
        Team team = teamRepository.findByNameIgnoreCase(name);

        if(team == null) {
            return -1;
        }

        team.setLikes(team.getLikes() + 1);
        teamRepository.save(team);

        return team.getLikes();
    }

    @GetMapping("/createTeam/{name}")
    public Long createTeam(@PathVariable String name) {
        Team newTeam = teamRepository.save(new Team(name, 0));

        return newTeam.getId();
    }

    private String jsonArrayFromList(List<Team> teams) {
        StringBuilder jsonString = new StringBuilder();
        jsonString.append("[");

        for (Team team : teams) {
            if (jsonString.length() > 1) {
                jsonString.append(",");
            }
            jsonString.append(team.toJson());
        }

        jsonString.append("]");

        return jsonString.toString();
    }
}
