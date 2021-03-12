package nl.hu.cisq1.lingo.trainer.presentation.dto;

import org.springframework.http.HttpStatus;

public class TrainerResponseDTO {
    public Long gameID = -1L;
    public HttpStatus httpStatus = HttpStatus.I_AM_A_TEAPOT;
    public String message = "";
    public ProgressDTO progress = null;
}