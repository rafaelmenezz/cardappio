package br.com.senac.entidades;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable{
   
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String nome;

   @Column(nullable = false)
   private String telefone;


   public Cliente(){}

   public Cliente(String nome, String telefone){
      this.nome = nome;
      this.telefone = telefone;
   }

   public Long getId() {
      return id;
   }
   
   public String getNome() {
      return nome;
   }
   
   public String getTelefone() {
      return telefone;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public void setTelefone(String telefone) {
      this.telefone = telefone;
   }


   @Override
   public int hashCode() {
      int hash = 0;
      hash += (id != null ? id.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof Cliente)) {
         return false;
     }
     Cliente other = (Cliente) obj;
     return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
   }

   @Override
   public String toString() {
      return "Cliente [ id=" + id + " ]";
   }

}
