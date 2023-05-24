package com.br.api.banco.jdbc;

public class Usuario {

    private Integer id;
    private String email;
    private String senha;
    private String tipoUsuario;
    private String funcionario;
    private Integer maquina;

    public Usuario(Integer id, String email, String senha, String tipoUsuario, String funcionario, Integer maquina) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.funcionario = funcionario;
        this.maquina = maquina;
    }

    public Usuario() {
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

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public Integer getMaquina() {
        return maquina;
    }

    public void setMaquina(Integer maquina) {
        this.maquina = maquina;
    }

    @Override
    public String toString() {
        return String.format("""
                             Login
                             Id: %d
                             E-mail: %s
                             Senha: %s
                             Tipo do Usuário: %s
                             Funcionário: %s
                             Maquina: %d
                             """, id, email, senha, tipoUsuario, funcionario, maquina);
    }
}
