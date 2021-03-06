package cl.web.zoo.entity;
// Generated 27-10-2018 3:13:47 by Hibernate Tools 4.3.5.Final


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Sector generated by hbm2java
 */
@Entity
@Table(name = "sector")
public class Sector implements java.io.Serializable {


  private Integer id;
  private String nombre;
  private String desc;
  private Set<Animal> animals = new HashSet<Animal>(0);

  public Sector() {}


  public Sector(String nombre, String desc) {
    this.nombre = nombre;
    this.desc = desc;
  }

  public Sector(String nombre, String desc, Set<Animal> animals) {
    this.nombre = nombre;
    this.desc = desc;
    this.animals = animals;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)


  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  @Column(name = "nombre", nullable = false, length = 50)
  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }


  @Column(name = "desc", nullable = false, length = 50)
  public String getDesc() {
    return this.desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "sector")
  public Set<Animal> getAnimals() {
    return this.animals;
  }

  public void setAnimals(Set<Animal> animals) {
    this.animals = animals;
  }



}


