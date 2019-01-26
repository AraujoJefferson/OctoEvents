package br.com.jaya.jparaujo.octoevents.endpoints;

import br.com.jaya.jparaujo.octoevents.business.IssuesBusiness;
import br.com.jaya.jparaujo.octoevents.entity.Issues;
import br.com.jaya.jparaujo.octoevents.exception.IssueDoesNotExistException;
import br.com.jaya.jparaujo.octoevents.repository.IssuesRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import static br.com.jaya.jparaujo.octoevents.constant.ApplicationConstants.*;
import javax.validation.constraints.NotNull;
import java.util.Map;

@RestController
@Validated
public class IssueEndpoint {

    private static final Logger LOGGER = LogManager.getLogger(IssueEndpoint.class);
    private final IssuesBusiness controller;

    @Autowired
    public IssueEndpoint(IssuesRepository repository){
        this.controller = new IssuesBusiness(repository);
    }

    @RequestMapping(method = RequestMethod.GET, path ="/issues/{"+FIELD_NAME_ISSUES_NUMBER+"}/events", consumes = CONTENT_TYPE)
    public ResponseEntity getIssues(@PathVariable(FIELD_NAME_ISSUES_NUMBER) @NotNull Long number){
        LOGGER.info(EVENT_ENDPOINT_SEARCH_ISSUES);
        return new ResponseEntity(controller.findIssuesByNumber(number), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/",headers = {EVENT_PING_GITHUB}, consumes = CONTENT_TYPE)
    public ResponseEntity eventPing(@RequestBody Map<String, Object> requestBody){
        LOGGER.info(EVENT_PING_SUCCESS);
        return new ResponseEntity(new String(EVENT_PING_SUCCESS),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/",headers = {EVENT_ISSUES_GITHUB}, consumes = CONTENT_TYPE)
    public ResponseEntity eventIssueSave(@RequestBody Map<String, Object> requestBody){
        LOGGER.info(EVENT_ENDPOINT_SAVE_SUCCESS);
        return new ResponseEntity(controller.saveIssues(requestBody), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/",headers = {EVENT_ISSUES_COMMENTS_GITHUB}, consumes = CONTENT_TYPE)
    public ResponseEntity eventIssueCommentsSave(@RequestBody Map<String, Object> requestBody){
        LOGGER.info(EVENT_ENDPOINT_SAVE_SUCCESS);
        return new ResponseEntity(controller.saveIssues(requestBody), HttpStatus.OK);
    }
}
