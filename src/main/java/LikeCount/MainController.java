package LikeCount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    TeamRepository teamRepository;

    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false) Long likeTeam) {
        model.addAttribute("teams", teamRepository.findAll());

        if (likeTeam != null) {
            Team team = teamRepository.findById(likeTeam).orElse(null);
            if (team != null) {
                team.setLikes(team.getLikes() + 1);
                teamRepository.save(team);
            }
        }

        return "index";
    }

    @PostMapping("/")
    public String postIndex(@RequestParam String teamName) {
        teamRepository.save(new Team(teamName, 0));

        return "redirect:/";
    }
}
