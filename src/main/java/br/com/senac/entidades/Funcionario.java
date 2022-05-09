package br.com.senac.entidades;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String nome;

   @Column(nullable = false, unique = true)
   private String cpf;

   @Column(nullable = false)
   private String telefone;

   @Column(nullable = false, unique = true)
   private String login;

   @Column(nullable = false)
   private String senha;

   @JoinColumn(name = "id_cargo")
   @ManyToOne
   private Cargo cargo;

   public Funcionario() {
   }

   public Funcionario(String nome, String cpf, String telefone, String login, String senha) {
      this.nome = nome;
      this.cpf = cpf;
      this.telefone = telefone;
      this.login = login;
      this.senha = senha;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return id;
   }

   public String getNome() {
      return nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getCpf() {
      return cpf;
   }

   public void setCpf(String cpf) {
      this.cpf = cpf;
   }

   public String getLogin() {
      return login;
   }

   public void setLogin(String login) {
      this.login = login;
   }

   public String getSenha() {
      return senha;
   }

   public void setSenha(String senha) {
      this.senha = senha;
   }

   public Cargo getCargo() {
      return cargo;
   }

   public void setCargo(Cargo cargo) {
      this.cargo = cargo;
   }

   @Override
   public int hashCode() {
       int hash = 0;
       hash += (id != null ? id.hashCode() : 0);
       return hash;
   }

   @Override
   public boolean equals(Object object) {
       if (!(object instanceof Funcionario)) {
           return false;
       }
       Funcionario other = (Funcionario) object;
       if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
           return false;
       }
       return true;
   }

   @Override
   public String toString() {
       return "com.senac.entidade.Funcionario[ id=" + id + " ]";
   }

}
