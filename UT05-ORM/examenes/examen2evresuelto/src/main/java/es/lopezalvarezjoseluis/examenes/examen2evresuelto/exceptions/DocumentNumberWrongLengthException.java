package es.lopezalvarezjoseluis.examenes.examen2evresuelto.exceptions;

public class DocumentNumberWrongLengthException extends RuntimeException {
  private static final String MESSAGE_FORMAT = "El número de documento %s tiene una longitud incorrecta";

  public DocumentNumberWrongLengthException(String documentNumber) {
    super(String.format(MESSAGE_FORMAT, documentNumber));
  }

}
