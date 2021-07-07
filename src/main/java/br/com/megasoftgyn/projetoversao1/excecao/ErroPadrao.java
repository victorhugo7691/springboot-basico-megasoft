package br.com.megasoftgyn.projetoversao1.excecao;

import java.io.Serializable;

public class ErroPadrao implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long timestamp;
    
    private Integer status;
    
    private String error;
    
    private String message;
    
    private String path;
    
    public ErroPadrao() {}
    
    public ErroPadrao(final Long timestamp, final Integer status, final String error, final String message) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        // this.path = path;
    }
    
    public Long getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(final Long timestamp) {
        this.timestamp = timestamp;
    }
    
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(final Integer status) {
        this.status = status;
    }
    
    public String getError() {
        return this.error;
    }
    
    public void setError(final String error) {
        this.error = error;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public void setPath(final String path) {
        this.path = path;
    }
}
