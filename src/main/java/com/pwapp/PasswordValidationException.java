package com.pwapp;

public class PasswordValidationException extends Exception {
  public PasswordValidationException(String message) {
    super(message);
  }
}

class LengthException extends PasswordValidationException {
  public LengthException() {
    super("Must be at least 8 characters long");
  }
}

class LowercaseException extends PasswordValidationException {
  public LowercaseException() {
    super("Must contain at least one lowercase letter");
  }
}

class UppercaseException extends PasswordValidationException {
  public UppercaseException() {
    super("Must contain at least one uppercase letter");
  }
}

class NumberException extends PasswordValidationException {
  public NumberException() {
    super("Must contain at least one number");
  }
}

class SpecialCharacterException extends PasswordValidationException {
  public SpecialCharacterException() {
    super("Must contain at least one special character");
  }
} 
  
