package br.com.senac.entidades;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "status")
public class Status implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false, unique = true)
   private String status;

   public Status(){}

   public Status(String status){
      this.status = status;
   }

   public Long getId() {
      return id;
   }
   public void setId(Long id) {
      this.id = id;
   }
   public String getStatus() {
      return status;
   }
   public void setStatus(String status) {
      this.status = status;
   }

   @Override
   public int hashCode() {
       int hash = 0;
       hash += (id != null ? id.hashCode() : 0);
       return hash;
   }

   @Override
   public boolean equals(Object object) {
       if (!(object instanceof Status)) {
           return false;
       }
       Status other = (Status) object;
       if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
           return false;
       }
       return true;
   }

   @Override
   public String toString() {
       return "com.senac.entidade.Status[ id=" + id + " ]";
   }
   
}
