package LikeCount;

import javax.persistence.*;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer likes;

    public Team() {
    }

    public Team(String name, Integer likes) {
        this.name = name;
        this.likes = likes;
    }

    public Team(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    // I'm aware there exists packages that takes care of this problem but in this
    // case, it was faster for me to just write my own function.
    public String toJson() {
        return "{\"id\":" + id + ", \"name\":\"" + name + "\", \"likes\":" + likes + "}";
    }
}
