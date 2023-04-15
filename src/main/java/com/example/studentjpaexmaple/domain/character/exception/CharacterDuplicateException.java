package com.example.studentjpaexmaple.domain.character.exception;

public class CharacterDuplicateException extends RuntimeException {
    public CharacterDuplicateException(String message) {
        super(message);
    }
}
