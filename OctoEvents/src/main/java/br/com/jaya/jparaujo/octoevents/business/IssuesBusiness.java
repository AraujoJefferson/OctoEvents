package br.com.jaya.jparaujo.octoevents.business;

import br.com.jaya.jparaujo.octoevents.entity.Issues;
import br.com.jaya.jparaujo.octoevents.exception.IssueDoesNotExistException;
import br.com.jaya.jparaujo.octoevents.repository.IssuesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static br.com.jaya.jparaujo.octoevents.constant.ApplicationConstants.*;

public class IssuesBusiness {
    private final IssuesRepository repository;

    public IssuesBusiness(IssuesRepository repository) {
        this.repository = repository;
    }

    public List<Issues> findIssuesByNumber(Long number) {
        List<Issues> issuesReturn = repository.findByNumber(number)
                .orElseThrow(IssueDoesNotExistException::new);
        return issuesReturn;
    }

    public Issues saveIssues(Map<String, Object> requestBody) {
        Map<String, Object> requestIssue = (Map<String, Object>) requestBody.get(EVENT_ISSUES_BODY_ISSUE);

        Issues issues = new Issues();
        issues.setAction(requestBody.getOrDefault(FIELD_NAME_ISSUES_ACTION, "").toString());
        issues.setCreated_at(requestIssue.getOrDefault(FIELD_NAME_ISSUES_CREATED_AT, "").toString());
        issues.setNumber(Long.parseLong(requestIssue.getOrDefault(FIELD_NAME_ISSUES_NUMBER, "0").toString()));
        return repository.save(issues);
    }
}
