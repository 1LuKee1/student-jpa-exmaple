package com.example.studentjpaexmaple.domain.character.exception;

public class CharacterSavingException extends RuntimeException {
    public CharacterSavingException(String message, Object cause) {
        super(message + cause);
    }
}
