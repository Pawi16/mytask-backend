package com.pawi16.taskapp.taskapp.exception;

public class BoardException extends BaseException{
    public BoardException(String code) {
        super("board." + code);
    }

    //create board
    public static BoardException createRequestNull(){
        return new BoardException("create.request.null");
    }

    public static BoardException createTitleNull(){
        return new BoardException("create.title.null");
    }

    public static BoardException createTitleEmpty(){
        return new BoardException("create.title.empty");
    }

    public static BoardException createUserNotFound(){ return new BoardException("create.user.not.found");}

    //edit board
    public static BoardException editRequestNull() { return new BoardException("edit.request.null");}

    public static BoardException editTitleNull() { return new BoardException("edit.title.null"); }

    public static BoardException editTitleEmpty() { return new BoardException("edit.title.empty");}

    public static BoardException editIdNull() { return new BoardException("edit.id.null");}

    public static BoardException editBoardNotFound() {
        return new BoardException("edit.board.not.found");
    }

    //getAllBoard

    public static BoardException getAllBoardsUserIdNull () {
        return new BoardException("get.all.boards.user.id.null");
    }

    public static BoardException getAllBoardsUserIdEmpty () {
        return new BoardException("get.all.boards.user.id.empty");
    }

    public static BoardException getAllBoardsUserNotFound () {
        return new BoardException("get.all.boards.user.not.found");
    }

    //get board
    public static BoardException getBoardNotFound () {
        return new BoardException("get.board.not.found");
    }


}
