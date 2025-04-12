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

    //validate parent and child board
    public static IssueException validateParentChildBoardDifferent() {
        return new IssueException("parent.child.issue.different.board");
    }

    // getIssueById
    public static IssueException getIssueIdNull() {
        return new IssueException("get.issue.id.null");
    }
    public static IssueException getIssueIdEmpty() {
        return new IssueException("get.issue.is.empty");
    }

    // editIssueById
    public static IssueException editIssueIdNull() {
        return new IssueException("edit.issue.id.null");
    }
    public static IssueException editIssueIdEmpty() {
        return new IssueException("edit.issue.is.empty");
    }
    public static IssueException editInvalidIssueType() {
        return new IssueException("edit.invalid.issue.type");
    }
    public static IssueException editDirectBoardChangeNotAllowed() {
        return new IssueException("edit.direct.board.change.not.allowed");
    }
    public static IssueException editEventCannotHaveParent() {
        return new IssueException("edit.event.cannot.have.parent");
    }

    //moveIssueToBoard
    public static IssueException moveRequestNull() {
        return new IssueException("move.request.null");
    }
    public static IssueException moveTargetBoardIdNull() {
        return new IssueException("move.target.board.id.null");
    }
    public static IssueException moveTargetBoardIdEmpty() {
        return new IssueException("move.target.board.id.empty");
    }
    public static IssueException moveIssueIdNull() {
        return new IssueException("move.issue.id.null");
    }
    public static IssueException moveIssueIdEmpty() {
        return new IssueException("move.issue.id.empty");
    }
    public static IssueException moveSameBoardNotAllowed() {
        return new IssueException("move.same.board.not.allowed");
    }

    //deleteIssue
    public static IssueException deleteIssueIdNull() {
        return new IssueException("delete.issue.id.null");
    }
    public static IssueException deleteIssueIdEmpty() {
        return new IssueException("delete.issue.id.empty");
    }
    public static IssueException deleteIssueNotFound() {
        return new IssueException("delete.issue.not.found");
    }

    //get issues
    public static IssueException getByBoardIdNull() {
        return new IssueException("get.by.board.id.null");
    }
    public static IssueException getByBoardIdEmpty() {
        return new IssueException("get.by.board.id.empty");
    }



}
