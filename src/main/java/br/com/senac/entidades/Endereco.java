package br.com.senac.entidades;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(length = 9, nullable = false)
   private String cep;

   @Column(length = 100, nullable = false)
   private String logradouro;

   @Column
   private String complemento;

   @Column(length = 50)
   private String cidade;

   @Column(length = 2)
   private String uf;

   @Column
   private String pontoReferencia;

   @JoinColumn(name = "id_cliente")
   @ManyToOne
   private Cliente cliente;

   public Endereco() {
   }

   public Endereco(String cep, String logradouro, String complemento, String cidade, String uf,
         String pontoReferencia) {
      this.cep = cep;
      this.logradouro = logradouro;
      this.complemento = complemento;
      this.cidade = cidade;
      this.uf = uf;
      this.pontoReferencia = pontoReferencia;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return id;
   }

   public void setCep(String cep) {
      this.cep = cep;
   }

   public String getCep() {
      return cep;
   }

   public void setLogradouro(String logradouro) {
      this.logradouro = logradouro;
   }

   public String getLogradouro() {
      return logradouro;
   }

   public void setComplemento(String complemento) {
      this.complemento = complemento;
   }

   public String getComplemento() {
      return complemento;
   }

   public void setCidade(String cidade) {
      this.cidade = cidade;
   }

   public String getCidade() {
      return cidade;
   }

   public void setUf(String uf) {
      this.uf = uf;
   }

   public String getUf() {
      return uf;
   }

   public void setCliente(Cliente cliente) {
      this.cliente = cliente;
   }

   public Cliente getCliente() {
      return cliente;
   }

   public void setPontoReferencia(String pontoReferencia) {
      this.pontoReferencia = pontoReferencia;
   }

   public String getPontoReferencia() {
      return pontoReferencia;
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (id != null ? id.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof Endereco)) {
         return false;
      }
      Endereco other = (Endereco) obj;
      return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
   }

   @Override
   public String toString() {
      return "Endereco [ id=" + id + " ]";
   }
}