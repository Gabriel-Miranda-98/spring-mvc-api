package br.gabriel.api.spring_mvc_api.model;

import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "clientes")
public class Cliente {

  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
  private UUID id;
  private String nome;
  private String email;
  private String telefone;
  private String endereco;
  private String cpf;

  protected Cliente() {
  }

  public Cliente(String nome, String email, String telefone, String endereco, String cpf) {
    this.nome = nome;
    this.email = email;
    this.telefone = telefone;
    this.endereco = endereco;
    this.cpf = cpf;
  }
  public UUID getId() {
    return id;
  }
  public void setId(UUID id) {
    this.id = id;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getTelefone() {
    return telefone;
  }
  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }
  public String getEndereco() {
    return endereco;
  }
  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }
  public String getCpf() {
    return cpf;
  }
  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
  @Override
  public String toString() {
    return "Cliente{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", email='" + email + '\'' +
            ", telefone='" + telefone + '\'' +
            ", endereco='" + endereco + '\'' +
            ", cpf='" + cpf + '\'' +
            '}';
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Cliente)) return false;

    Cliente cliente = (Cliente) o;

    return id != null ? id.equals(cliente.id) : cliente.id == null;
  }
  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
  public static ClienteBuilder builder() {
    return new ClienteBuilder();
  }
  public static class ClienteBuilder {
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private String cpf;

    public ClienteBuilder nome(String nome) {
      this.nome = nome;
      return this;
    }

    public ClienteBuilder email(String email) {
      this.email = email;
      return this;
    }

    public ClienteBuilder telefone(String telefone) {
      this.telefone = telefone;
      return this;
    }

    public ClienteBuilder endereco(String endereco) {
      this.endereco = endereco;
      return this;
    }

    public ClienteBuilder cpf(String cpf) {
      this.cpf = cpf;
      return this;
    }

    public Cliente build() {
      return new Cliente(nome, email, telefone, endereco, cpf);
    }
  }

}
