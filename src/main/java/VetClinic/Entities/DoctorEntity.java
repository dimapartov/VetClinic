package VetClinic.Entities;


import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "doctor", schema = "public", catalog = "VetClinic")
public class DoctorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "doctor_id")
    private String doctorId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "surname")
    private String surname;
    @Basic
    @Column(name = "patronymic")
    private String patronymic;
    @Basic
    @Column(name = "year_of_employment")
    private String yearOfEmployment;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "specialization")
    private String specialization;
    @ManyToOne
    @JoinColumn(name = "office_number", referencedColumnName = "office_number", nullable = false)
    private OfficeEntity officeByOfficeNumber;
    @OneToMany(mappedBy = "doctorByDoctorId")
    private Collection<VisitEntity> visitsByDoctorId;
    @Override
    public String toString() {
        return "DoctorEntity{" +
                "doctorId='" + doctorId + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", yearOfEmployment='" + yearOfEmployment + '\'' +
                ", password='" + password + '\'' +
                ", specialization='" + specialization + '\'' +
                ", officeByOfficeNumber=" + officeByOfficeNumber +
                ", visitsByDoctorId=" + visitsByDoctorId +
                '}';
    }
}
