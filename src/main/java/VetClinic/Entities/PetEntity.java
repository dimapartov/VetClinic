package VetClinic.Entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "pet", schema = "public", catalog = "VetClinic")
@NamedQuery(name = "findAllPets", query = "SELECT p FROM PetEntity p")
public class PetEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pet_id")
    private int petId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "breed")
    private String breed;
    @Basic
    @Column(name = "gender")
    private String gender;
    @OneToMany(mappedBy = "petByPetId")
    private Collection<CardEntity> cardsByPetId;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id", nullable = false)
    private ClientEntity clientByClientId;
    @OneToMany(mappedBy = "petByPetId")
    private Collection<VisitEntity> visitsByPetId;
    public int getPetId() {
        return petId;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    @Override
    public String toString() {
        return "PetEntity{" +
                "petId=" + petId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", breed='" + breed + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
