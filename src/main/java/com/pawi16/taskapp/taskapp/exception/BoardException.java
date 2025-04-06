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
}
