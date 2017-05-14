package pl.allegro.umk.crazybill.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.allegro.umk.crazybill.api.dto.TeamDto;

import java.util.UUID;

@Document(collection = "teams")
public class Team {
    @Id
    private String id;
    private String name;
    private String group;

    public Team(String id, String name, String group) {
        this.id = id;
        this.name = name;
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public static Team fromDto(TeamDto teamDto) {
        return new Team(UUID.randomUUID().toString(), teamDto.getName(), teamDto.getGroup());
    }

    public TeamDto toDto() {
        return new TeamDto(id, name, group);
    }

    public static TeamBuilder builder() {
        return new TeamBuilder();
    }

    public static class TeamBuilder {
        private String id;
        private String name;
        private String group;

        public Team build() {
            return new Team(id, name, group);
        }

        private TeamBuilder withId(String id) {
            this.id = id;
            return this;
        }

        private TeamBuilder withName(String name) {
            this.name = name;
            return this;
        }

        private TeamBuilder withGroup(String group) {
            this.group = group;
            return this;
        }
    }
}
