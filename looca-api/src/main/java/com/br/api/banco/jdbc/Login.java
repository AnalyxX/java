package com.br.api.banco.jdbc;

public class Login {

    private Integer id;
    private String email;
    private String senha;

    public Login(Integer id, String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
    }

    Login() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return String.format("Login\n"
                + "Id: %d\n"
                + "E-mail: %s\n"
                + "Senha: %s\n", id, email, senha);
    }
}
