package br.com.senac.entidades;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "cargo")
public class Cargo implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false, length = 50)
   private String descricao;

   public Cargo(){}

   public Cargo(String descricao){
      this.descricao = descricao;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   public Long getId() {
      return id;
   }

   public String getDescricao() {
      return descricao;
   }

   
   @Override
   public int hashCode() {
      int hash = 0;
      hash += (id != null ? id.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof Cargo)) {
         return false;
     }
     Cargo other = (Cargo) obj;
     return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
   }

   @Override
   public String toString() {
      return "Cargo [ id=" + id + " ]";
   }
   

}
