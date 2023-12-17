package VetClinic.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "card", schema = "public", catalog = "VetClinic")
public class CardEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "card_id")
    private int cardId;
    @Basic
    @Column(name = "diagnosis")
    private String diagnosis;
    @Basic
    @Column(name = "specifications")
    private String specifications;
    @ManyToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "pet_id", nullable = false)
    private PetEntity petByPetId;
    @ManyToOne
    @JoinColumn(name = "visit_id", referencedColumnName = "visit_id", nullable = false)
    private VisitEntity visitByVisitId;
    public String getDiagnosis() {
        return diagnosis;
    }
    @Override
    public String toString() {
        return "CardEntity{" +
                "cardId=" + cardId +
                ", diagnosis='" + diagnosis + '\'' +
                ", specifications='" + specifications + '\'' +
                ", petByPetId=" + petByPetId +
                ", visitByVisitId=" + visitByVisitId +
                '}';
    }
}
