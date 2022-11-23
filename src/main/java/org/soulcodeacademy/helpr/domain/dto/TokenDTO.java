package org.soulcodeacademy.helpr.domain.dto;

public class TokenDTO {
    private  String acessoToken;
    private  String tokenType = "Beare";

    public  TokenDTO(String acessoToken) {
        this.acessoToken = acessoToken;
    }

    public String getAcessoToken() {
        return acessoToken;
    }

    public void setAcessoToken(String acessoToken) {
        this.acessoToken = acessoToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
