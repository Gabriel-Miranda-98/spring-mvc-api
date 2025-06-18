package br.gabriel.api.spring_mvc_api.model;

import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "clientes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
  private UUID id;

  private String name;

  private String email;

  private String phone;
  private String address;



  @Override
  public String toString() {
    return "Cliente{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", phone='" + phone + '\'' +
        ", address='" + address + '\'' +
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


}
