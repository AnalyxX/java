package com.br.api.banco.jdbc;

public class Login {

    private Integer id;
    private String email;
    private String senha;
    private String tipoUsuario;
    private String funcionario;

    public Login(Integer id, String email, String senha, String tipoUsuario, String funcionario) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.funcionario = funcionario;
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

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuaro(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }
    
    @Override
    public String toString() {
        return String.format("Login\n"
                + "Id: %d\n"
                + "E-mail: %s\n"
                + "Senha: %s\n"
                + "Tipo do Usuário: %s\n"
                + "Funcionário: %s\n"
                , id, email, senha,tipoUsuario,funcionario);
    }
}
