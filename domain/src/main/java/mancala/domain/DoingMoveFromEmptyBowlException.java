package mancala.domain;

public class DoingMoveFromEmptyBowlException extends RuntimeException {

        public DoingMoveFromEmptyBowlException() {
            super("You can not do a move from an empty bowl");
        }

    }

