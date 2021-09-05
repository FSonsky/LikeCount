package LikeCount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainRestController {

    @Autowired
    TeamRepository teamRepository;

    @GetMapping("/getTeams") // Returns a JSON array of Team objects or an empty array if no teams exist
    public String getTeams() {
        List<Team> teams = (List<Team>) teamRepository.findAll();

        return jsonArrayFromList(teams);
    }

    @GetMapping("/getLikesByTeamId/{id}") // Returns the amount of likes or -1 if team was not found
    public int getLikesById(@PathVariable Long id) {
        Team team = teamRepository.findById(id).orElse(null);

        if(team == null) {
            return -1;
        }

        return team.getLikes();
    }

    @GetMapping("/getLikesByTeamName/{name}") // Returns the amount of likes or -1 if team was not found
    public int getLikesByName(@PathVariable String name) {
        Team team = teamRepository.findByNameIgnoreCase(name);

        if(team == null) {
            return -1;
        }

        return team.getLikes();
    }

    @GetMapping("/likeTeamById/{id}") // Adds one like to team and returns new amount of likes or -1 if team was not found
    public int likeById(@PathVariable Long id) {
        Team team = teamRepository.findById(id).orElse(null);

        if(team == null) {
            return -1;
        }

        team.setLikes(team.getLikes() + 1);
        teamRepository.save(team);

        return team.getLikes();
    }

    @GetMapping("/likeTeamByName/{name}") // Adds one like to team and returns new amount of likes or -1 if team was not found
    public int likeTeamByName(@PathVariable String name) {
        Team team = teamRepository.findByNameIgnoreCase(name);

        if(team == null) {
            return -1;
        }

        team.setLikes(team.getLikes() + 1);
        teamRepository.save(team);

        return team.getLikes();
    }

    @GetMapping("/createTeam/{name}") // Creates a new team with given name
    public Long createTeam(@PathVariable String name) {
        Team newTeam = teamRepository.save(new Team(name, 0));

        return newTeam.getId();
    }

    // I'm aware there exists packages that takes care of this problem but in this
    // case, it was faster for me to just write my own function.
    private String jsonArrayFromList(List<Team> teams) {
        StringBuilder jsonString = new StringBuilder();
        jsonString.append("[");

        for (Team team : teams) {
            if (jsonString.length() > 1) { // If there is an object before we need to add ,
                jsonString.append(",");
            }
            jsonString.append(team.toJson());
        }

        jsonString.append("]");

        return jsonString.toString();
    }
}
