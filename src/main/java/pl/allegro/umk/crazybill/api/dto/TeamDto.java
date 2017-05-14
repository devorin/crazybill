package pl.allegro.umk.crazybill.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

public class TeamDto {

    private final String id;
    private final String name;
    private final String group;

    @JsonCreator
    public TeamDto(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("group") String group
    ) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamDto teamDto = (TeamDto) o;
        return Objects.equal(id, teamDto.id) &&
                Objects.equal(name, teamDto.name) &&
                Objects.equal(group, teamDto.group);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, group);
    }
}
