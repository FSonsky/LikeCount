# LikeCount

This app keeps a database of teams and the amount of likes each team has.

The project is built with the following:
* Java 11
* Maven
* h2 database
* JPA
* Spring Boot
* Thymeleaf

The functionality is both via a Controller and RestController so the user can access the database both visually and through URL commands.

### Controller (visual)
The app listens in on the root URL for the visual part (http://localhost:8080). Here you can see all teams, add teams and add likes.

### RestController
You can do the same as above by appending the following to the root URL:
* `/getTeams` - Returns a JSON-array of all the team objects in the database
* `/getLikesByTeamId/id` - Replace "id" with the team's id number. Returns the amount of likes the team has or -1 if no team with that id was found.
* `/getLikesByTeamName/name` - Replace "name" with the team's name (case-insensitive). Returns the amount of likes the team has or -1 if no team with that name was found.
* `/likeTeamById/id` - Replace "id" with the team's id number. This adds one like to an existing team. Returns the amount of likes the team has or -1 if no team with that id was found.
* `/likeTeamByName/name` - Replace "name" with the team's name (case-insensitive). This adds one like to an existing team. Returns the amount of likes the team has or -1 if no team with that name was found.
* `/createTeam/name` - Replace "name" with the new team's name. This creates a new team in the database. Returns the new team's id.

Created by Frederik Sonsky