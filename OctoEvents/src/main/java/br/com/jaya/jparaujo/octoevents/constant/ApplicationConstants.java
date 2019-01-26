package br.com.jaya.jparaujo.octoevents.constant;

public class ApplicationConstants {
    //EndPoints
    public static final String CONTENT_TYPE = "application/json";
    public static final String EVENT_PING_GITHUB = "X-GitHub-Event=ping";
    public static final String EVENT_ISSUES_GITHUB = "X-GitHub-Event=issues";
    public static final String EVENT_ISSUES_COMMENTS_GITHUB = "X-GitHub-Event=issue_comment";
    public static final String EVENT_ISSUES_BODY_ISSUE = "issue";

    //EndPoints>Messages
    public static final String EVENT_PING_SUCCESS = "Webhooks conectado com sucesso";
    public static final String EVENT_ENDPOINT_SEARCH_ISSUES = "Procurando publicações";
    public static final String EVENT_ENDPOINT_SAVE_SUCCESS = "Publicação salva com sucesso";

    //Issues DataBase
    public static final String TABLE_NAME_ISSUES = "ISSUES";
    public static final String FIELD_NAME_ISSUES_ID = "id";
    public static final String FIELD_NAME_ISSUES_NUMBER = "number";
    public static final String FIELD_NAME_ISSUES_ACTION = "action";
    public static final String FIELD_NAME_ISSUES_CREATED_AT = "created_at";



}
