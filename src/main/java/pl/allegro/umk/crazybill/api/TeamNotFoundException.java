package pl.allegro.umk.crazybill.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TeamNotFoundException extends RuntimeException {
    TeamNotFoundException() {
        super("Team not found");
    }
}