package br.com.jaya.jparaujo.octoevents.controller;

import br.com.jaya.jparaujo.octoevents.entity.Issue;
import br.com.jaya.jparaujo.octoevents.exception.EventDoesNotExistException;
import br.com.jaya.jparaujo.octoevents.business.EventRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

@RestController
@Validated
public class OctoEvents {

    private static final Logger LOGGER = LogManager.getLogger(OctoEvents.class);
    private final EventRepository repository;

    @Autowired
    public OctoEvents (EventRepository repository){
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET, path ="/issues/{issue}/events")
    public ResponseEntity getIssues(@PathVariable("issue") @NotNull Integer issue){
        Issue issueReturn = repository.findById(issue).orElseThrow(EventDoesNotExistException::new);
        LOGGER.info("Searched issue");
        return new ResponseEntity(issueReturn, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/",headers = { "X-GitHub-Issue=ping"} )
    public ResponseEntity eventPing(@RequestBody Map<String, Object> payload){
        LOGGER.info("Conectado com sucesso");
        return new ResponseEntity(payload, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/",headers = { "X-GitHub-Issue=issues"} )
    public ResponseEntity eventIssueSave(@RequestBody Map<String, Object> issues){
        LOGGER.info("Conectado com sucesso");
        return new ResponseEntity(issues, HttpStatus.OK);
    }
}
