package pl.allegro.umk.crazybill.api;


import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.allegro.umk.crazybill.api.dto.BillDto;
import pl.allegro.umk.crazybill.api.dto.TeamDto;
import pl.allegro.umk.crazybill.domain.Bill;
import pl.allegro.umk.crazybill.domain.Team;
import pl.allegro.umk.crazybill.repository.BillsRepository;
import pl.allegro.umk.crazybill.repository.TeamsRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
public class TeamsController {

    private final TeamsRepository teamsRepository;

    @Autowired
    public TeamsController(TeamsRepository teamsRepository) {
        this.teamsRepository = teamsRepository;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/teams",
            produces = "application/json"
    )
    public List<TeamDto> getTeams() {
        List<TeamDto> teamDtoList = Lists.newArrayList();
        teamsRepository.findAll().forEach(team -> {
            teamDtoList.add(team.toDto());
        });
        return teamDtoList;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/sorted-name-teams",
            produces = "application/json"
    )
    public List<TeamDto> getSortedByNameTeams() {
        List<TeamDto> teamDtoList = Lists.newArrayList();
        Sort sort = new Sort(Sort.Direction.ASC, "name");
        teamsRepository.findAll(sort).forEach(team -> {
            teamDtoList.add(team.toDto());
        });
        return teamDtoList;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/teams/{teamId}",
            produces = "application/json"
    )
    public TeamDto getTeam(@PathVariable String teamId) {
        Team team = teamsRepository.findOne(teamId);
        if (Objects.isNull(team)) {
            throw new TeamNotFoundException();
        }

        return team.toDto();
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/teams",
            consumes = "application/json"
    )
    public ResponseEntity createTeam(@RequestBody TeamDto teamDto) throws URISyntaxException {
        Team team = Team.fromDto(teamDto);
        teamsRepository.save(team);
        return ResponseEntity.created(new URI("/teams/" + team.getId())).build();
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/teams/{teamId}"
    )
    public void deleteTeam(@PathVariable String teamId) {
        teamsRepository.delete(teamId);
    }
}
