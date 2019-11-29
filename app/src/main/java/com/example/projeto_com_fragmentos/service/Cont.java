package com.example.projeto_com_fragmentos.service;

class Cont {



    private String nome;
    private String ntelemovel;
    private String ntelefone;
    private String email;
    private String idade;
    private String altura;
    private String genero;


    public Cont(String nome, String ntelemovel, String ntelefone, String email, String idade, String altura, String genero) {
        this.nome = nome;
        this.ntelemovel = ntelemovel;
        this.ntelefone = ntelefone;
        this.email = email;
        this.idade = idade;
        this.altura = altura;
        this.genero = genero;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNtelemovel() {
        return ntelemovel;
    }

    public void setNtelemovel(String ntelemovel) {
        this.ntelemovel = ntelemovel;
    }

    public String getNtelefone() {
        return ntelefone;
    }

    public void setNtelefone(String ntelefone) {
        this.ntelefone = ntelefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Cont{" +
                "nome='" + nome + '\'' +
                ", ntelemovel='" + ntelemovel + '\'' +
                ", ntelefone='" + ntelefone + '\'' +
                ", email='" + email + '\'' +
                ", idade='" + idade + '\'' +
                ", altura='" + altura + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }
}
