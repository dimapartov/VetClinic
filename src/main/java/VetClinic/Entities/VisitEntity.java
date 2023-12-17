package VetClinic.Entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "visit", schema = "public", catalog = "VetClinic")
public class VisitEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "visit_id")
    private int visitId;
    @Basic
    @Column(name = "date")
    private String date;
    @Basic
    @Column(name = "reason")
    private String reason;
    @OneToMany(mappedBy = "visitByVisitId")
    private Collection<CardEntity> cardsByVisitId;
    @ManyToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "pet_id", nullable = false)
    private PetEntity petByPetId;
    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", nullable = false)
    private DoctorEntity doctorByDoctorId;
    @Override
    public String toString() {
        return "VisitEntity{" +
                "visitId=" + visitId +
                ", date='" + date + '\'' +
                ", reason='" + reason + '\'' +
                ", cardsByVisitId=" + cardsByVisitId +
                ", petByPetId=" + petByPetId +
                ", doctorByDoctorId=" + doctorByDoctorId +
                '}';
    }
}
