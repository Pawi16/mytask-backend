package com.pawi16.taskapp.taskapp.exception;

public class IssueException extends BaseException{
    public IssueException(String code) {
        super("issue." + code);
    }

    //create issue
    public static IssueException createRequestNull () {
        return new IssueException("create.request.null");
    }
    public static IssueException createNameNull () {
        return new IssueException("create.name.null");
    }
    public static IssueException createIssueTypeNull () {
        return new IssueException("create.issue.type.null");
    }
    public static IssueException createBoardIdNull () {
        return new IssueException("create.board.id.null");
    }
    public static IssueException createInvalidIssueType() {
        return new IssueException("create.invalid.issue.type");
    }

    //get issue
    public static IssueException getIssueNotFound () {
        return new IssueException("get.issue.not.found");
    }

    //validate issue type
    public static IssueException validateTypeParentRequired () {
        return new IssueException("type.parent.required");
    }
    public static IssueException validateTypeEventCannotHaveEventChild () {
        return new IssueException("type.event.cannot.have.event.child");
    }
    public static IssueException validateTypeSubtaskMustBeUnderTask() {
        return new IssueException("type.subtask.must.be.under.task");
    }
    public static IssueException validateTypeEventCannotBeChildOfTask() {
        return new IssueException("type.event.cannot.be.child.of.task");
    }
    public static IssueException validateTypeTaskMustBeUnderEvent() {
        return new IssueException("type.task.must.be.under.event");
    }
    public static IssueException validateTypeSubtaskCannotHaveChild() {
        return new IssueException("type.subtask.cannot.have.child");
    }

    // getIssueById
    public static IssueException getIssueIdNull() {
        return new IssueException("get.issue.id.null");
    }
    public static IssueException getIssueIdEmpty() {
        return new IssueException("get.issue.is.empty");
    }




}
