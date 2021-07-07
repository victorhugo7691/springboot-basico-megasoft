package br.com.megasoftgyn.projetoversao1.excecao;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExcecaoController {
    
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErroPadrao> objectNotFound(final ObjectNotFoundException e) {
        // final ErroPadrao err = new ErroPadrao(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Não encontramos esses dados!", e.getMessage(), request.getRequestURI());
        final ErroPadrao erro = new ErroPadrao(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Não encontramos esses dados!", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroPadrao> dataIntegrity(final DataIntegrityViolationException e) {
        // final ErroPadrao err = new ErroPadrao(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Os dados estão incorretos!", e.getMessage(), request.getRequestURI());
        final ErroPadrao erro = new ErroPadrao(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Problema de integridade, você não pode remover objetos que estão em uso!", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
