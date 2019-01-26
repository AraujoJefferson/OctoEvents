package br.com.jaya.jparaujo.octoevents;

import br.com.jaya.jparaujo.octoevents.entity.Issues;
import br.com.jaya.jparaujo.octoevents.exception.IssueDoesNotExistException;
import br.com.jaya.jparaujo.octoevents.repository.IssuesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static br.com.jaya.jparaujo.octoevents.constant.ApplicationConstants.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IssuesRepository repository;

    @Before
    public void deleteAllBeforeTests() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void createIssue() throws Exception {
        repository.deleteAll();
        mockMvc.perform(post("/")
                .header(EVENT_ISSUES,"issues")
                .contentType(CONTENT_TYPE)
                .content(getIssue()))
                .andExpect(status().isOk());
    }

    @Test
    public void createIssueComment() throws Exception {
        repository.deleteAll();
        mockMvc.perform(post("/")
                .header(EVENT_ISSUES,"issue_comment")
                .contentType(CONTENT_TYPE)
                .content(getIssue()))
                .andExpect(status().isOk());
    }

    @Test
    public void ping() throws Exception {
        mockMvc.perform(post("/")
                .header(EVENT_ISSUES,"ping")
                .contentType(CONTENT_TYPE)
        ).andExpect(status().isOk());
    }

    @Test
    public void searchIssues() throws Exception {
        repository.deleteAll();

        Issues issues = new Issues();
        issues.setNumber(1L);
        issues.setAction("opened");
        issues.setCreated_at("2019-01-25T23:31:35Z");

        repository.save(issues);

        mockMvc.perform(get("/issues/" + issues.getNumber() + "/events")
                .contentType(CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(
                        content().json("[{\"id\":" + issues.getId() + ",\"number\":1,\"action\":\"opened\"," +
                                "\"created_at\":\"2019-01-25T23:31:35Z\"}]"))
                .andReturn();
    }

    @Test
    public void searchIssueNotExist() throws Exception {
        repository.deleteAll();

        mockMvc.perform(get("/issues/9999/events")
                .contentType(CONTENT_TYPE))
                .andExpect(status().isNotFound());
    }
    
    private String getIssue(){
        String retorno =  new String("{\n" +
                "  \"action\": \"edited\",\n" +
                "  \"issue\": {\n" +
                "    \"url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/issues/1\",\n" +
                "    \"repository_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents\",\n" +
                "    \"labels_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/issues/1/labels{/name}\",\n" +
                "    \"comments_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/issues/1/comments\",\n" +
                "    \"events_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/issues/1/events\",\n" +
                "    \"html_url\": \"https://github.com/AraujoJefferson/OctoEvents/issues/1\",\n" +
                "    \"id\": 403372570,\n" +
                "    \"node_id\": \"MDU6SXNzdWU0MDMzNzI1NzA=\",\n" +
                "    \"number\": 1,\n" +
                "    \"title\": \"teste\",\n" +
                "    \"user\": {\n" +
                "      \"login\": \"AraujoJefferson\",\n" +
                "      \"id\": 5533113,\n" +
                "      \"node_id\": \"MDQ6VXNlcjU1MzMxMTM=\",\n" +
                "      \"avatar_url\": \"https://avatars2.githubusercontent.com/u/5533113?v=4\",\n" +
                "      \"gravatar_id\": \"\",\n" +
                "      \"url\": \"https://api.github.com/users/AraujoJefferson\",\n" +
                "      \"html_url\": \"https://github.com/AraujoJefferson\",\n" +
                "      \"followers_url\": \"https://api.github.com/users/AraujoJefferson/followers\",\n" +
                "      \"following_url\": \"https://api.github.com/users/AraujoJefferson/following{/other_user}\",\n" +
                "      \"gists_url\": \"https://api.github.com/users/AraujoJefferson/gists{/gist_id}\",\n" +
                "      \"starred_url\": \"https://api.github.com/users/AraujoJefferson/starred{/owner}{/repo}\",\n" +
                "      \"subscriptions_url\": \"https://api.github.com/users/AraujoJefferson/subscriptions\",\n" +
                "      \"organizations_url\": \"https://api.github.com/users/AraujoJefferson/orgs\",\n" +
                "      \"repos_url\": \"https://api.github.com/users/AraujoJefferson/repos\",\n" +
                "      \"events_url\": \"https://api.github.com/users/AraujoJefferson/events{/privacy}\",\n" +
                "      \"received_events_url\": \"https://api.github.com/users/AraujoJefferson/received_events\",\n" +
                "      \"type\": \"User\",\n" +
                "      \"site_admin\": false\n" +
                "    },\n" +
                "    \"labels\": [\n" +
                "\n" +
                "    ],\n" +
                "    \"state\": \"open\",\n" +
                "    \"locked\": false,\n" +
                "    \"assignee\": null,\n" +
                "    \"assignees\": [\n" +
                "\n" +
                "    ],\n" +
                "    \"milestone\": null,\n" +
                "    \"comments\": 1,\n" +
                "    \"created_at\": \"2019-01-25T23:31:35Z\",\n" +
                "    \"updated_at\": \"2019-01-25T23:37:27Z\",\n" +
                "    \"closed_at\": null,\n" +
                "    \"author_association\": \"OWNER\",\n" +
                "    \"body\": \"about it 2\"\n" +
                "  },\n" +
                "  \"changes\": {\n" +
                "    \"body\": {\n" +
                "      \"from\": \"about it\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"repository\": {\n" +
                "    \"id\": 167554121,\n" +
                "    \"node_id\": \"MDEwOlJlcG9zaXRvcnkxNjc1NTQxMjE=\",\n" +
                "    \"name\": \"OctoEvents\",\n" +
                "    \"full_name\": \"AraujoJefferson/OctoEvents\",\n" +
                "    \"private\": false,\n" +
                "    \"owner\": {\n" +
                "      \"login\": \"AraujoJefferson\",\n" +
                "      \"id\": 5533113,\n" +
                "      \"node_id\": \"MDQ6VXNlcjU1MzMxMTM=\",\n" +
                "      \"avatar_url\": \"https://avatars2.githubusercontent.com/u/5533113?v=4\",\n" +
                "      \"gravatar_id\": \"\",\n" +
                "      \"url\": \"https://api.github.com/users/AraujoJefferson\",\n" +
                "      \"html_url\": \"https://github.com/AraujoJefferson\",\n" +
                "      \"followers_url\": \"https://api.github.com/users/AraujoJefferson/followers\",\n" +
                "      \"following_url\": \"https://api.github.com/users/AraujoJefferson/following{/other_user}\",\n" +
                "      \"gists_url\": \"https://api.github.com/users/AraujoJefferson/gists{/gist_id}\",\n" +
                "      \"starred_url\": \"https://api.github.com/users/AraujoJefferson/starred{/owner}{/repo}\",\n" +
                "      \"subscriptions_url\": \"https://api.github.com/users/AraujoJefferson/subscriptions\",\n" +
                "      \"organizations_url\": \"https://api.github.com/users/AraujoJefferson/orgs\",\n" +
                "      \"repos_url\": \"https://api.github.com/users/AraujoJefferson/repos\",\n" +
                "      \"events_url\": \"https://api.github.com/users/AraujoJefferson/events{/privacy}\",\n" +
                "      \"received_events_url\": \"https://api.github.com/users/AraujoJefferson/received_events\",\n" +
                "      \"type\": \"User\",\n" +
                "      \"site_admin\": false\n" +
                "    },\n" +
                "    \"html_url\": \"https://github.com/AraujoJefferson/OctoEvents\",\n" +
                "    \"description\": null,\n" +
                "    \"fork\": false,\n" +
                "    \"url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents\",\n" +
                "    \"forks_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/forks\",\n" +
                "    \"keys_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/keys{/key_id}\",\n" +
                "    \"collaborators_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/collaborators{/collaborator}\",\n" +
                "    \"teams_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/teams\",\n" +
                "    \"hooks_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/hooks\",\n" +
                "    \"issue_events_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/issues/events{/number}\",\n" +
                "    \"events_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/events\",\n" +
                "    \"assignees_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/assignees{/user}\",\n" +
                "    \"branches_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/branches{/branch}\",\n" +
                "    \"tags_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/tags\",\n" +
                "    \"blobs_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/git/blobs{/sha}\",\n" +
                "    \"git_tags_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/git/tags{/sha}\",\n" +
                "    \"git_refs_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/git/refs{/sha}\",\n" +
                "    \"trees_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/git/trees{/sha}\",\n" +
                "    \"statuses_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/statuses/{sha}\",\n" +
                "    \"languages_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/languages\",\n" +
                "    \"stargazers_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/stargazers\",\n" +
                "    \"contributors_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/contributors\",\n" +
                "    \"subscribers_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/subscribers\",\n" +
                "    \"subscription_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/subscription\",\n" +
                "    \"commits_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/commits{/sha}\",\n" +
                "    \"git_commits_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/git/commits{/sha}\",\n" +
                "    \"comments_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/comments{/number}\",\n" +
                "    \"issue_comment_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/issues/comments{/number}\",\n" +
                "    \"contents_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/contents/{+path}\",\n" +
                "    \"compare_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/compare/{base}...{head}\",\n" +
                "    \"merges_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/merges\",\n" +
                "    \"archive_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/{archive_format}{/ref}\",\n" +
                "    \"downloads_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/downloads\",\n" +
                "    \"issues_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/issues{/number}\",\n" +
                "    \"pulls_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/pulls{/number}\",\n" +
                "    \"milestones_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/milestones{/number}\",\n" +
                "    \"notifications_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/notifications{?since,all,participating}\",\n" +
                "    \"labels_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/labels{/name}\",\n" +
                "    \"releases_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/releases{/id}\",\n" +
                "    \"deployments_url\": \"https://api.github.com/repos/AraujoJefferson/OctoEvents/deployments\",\n" +
                "    \"created_at\": \"2019-01-25T13:44:58Z\",\n" +
                "    \"updated_at\": \"2019-01-25T18:19:22Z\",\n" +
                "    \"pushed_at\": \"2019-01-25T18:19:20Z\",\n" +
                "    \"git_url\": \"git://github.com/AraujoJefferson/OctoEvents.git\",\n" +
                "    \"ssh_url\": \"git@github.com:AraujoJefferson/OctoEvents.git\",\n" +
                "    \"clone_url\": \"https://github.com/AraujoJefferson/OctoEvents.git\",\n" +
                "    \"svn_url\": \"https://github.com/AraujoJefferson/OctoEvents\",\n" +
                "    \"homepage\": null,\n" +
                "    \"size\": 29,\n" +
                "    \"stargazers_count\": 0,\n" +
                "    \"watchers_count\": 0,\n" +
                "    \"language\": \"Java\",\n" +
                "    \"has_issues\": true,\n" +
                "    \"has_projects\": true,\n" +
                "    \"has_downloads\": true,\n" +
                "    \"has_wiki\": true,\n" +
                "    \"has_pages\": false,\n" +
                "    \"forks_count\": 0,\n" +
                "    \"mirror_url\": null,\n" +
                "    \"archived\": false,\n" +
                "    \"open_issues_count\": 1,\n" +
                "    \"license\": null,\n" +
                "    \"forks\": 0,\n" +
                "    \"open_issues\": 1,\n" +
                "    \"watchers\": 0,\n" +
                "    \"default_branch\": \"master\"\n" +
                "  },\n" +
                "  \"sender\": {\n" +
                "    \"login\": \"AraujoJefferson\",\n" +
                "    \"id\": 5533113,\n" +
                "    \"node_id\": \"MDQ6VXNlcjU1MzMxMTM=\",\n" +
                "    \"avatar_url\": \"https://avatars2.githubusercontent.com/u/5533113?v=4\",\n" +
                "    \"gravatar_id\": \"\",\n" +
                "    \"url\": \"https://api.github.com/users/AraujoJefferson\",\n" +
                "    \"html_url\": \"https://github.com/AraujoJefferson\",\n" +
                "    \"followers_url\": \"https://api.github.com/users/AraujoJefferson/followers\",\n" +
                "    \"following_url\": \"https://api.github.com/users/AraujoJefferson/following{/other_user}\",\n" +
                "    \"gists_url\": \"https://api.github.com/users/AraujoJefferson/gists{/gist_id}\",\n" +
                "    \"starred_url\": \"https://api.github.com/users/AraujoJefferson/starred{/owner}{/repo}\",\n" +
                "    \"subscriptions_url\": \"https://api.github.com/users/AraujoJefferson/subscriptions\",\n" +
                "    \"getIssueorganizations_url\": \"https://api.github.com/users/AraujoJefferson/orgs\",\n" +
                "    \"repos_url\": \"https://api.github.com/users/AraujoJefferson/repos\",\n" +
                "    \"events_url\": \"https://api.github.com/users/AraujoJefferson/events{/privacy}\",\n" +
                "    \"received_events_url\": \"https://api.github.com/users/AraujoJefferson/received_events\",\n" +
                "    \"type\": \"User\",\n" +
                "    \"site_admin\": false\n" +
                "  }\n" +
                "}");
        return retorno;
    }
}

