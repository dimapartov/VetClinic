package VetClinic.Entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "office", schema = "public", catalog = "VetClinic")
public class OfficeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "office_number")
    private int officeNumber;
    @Basic
    @Column(name = "floor")
    private int floor;
    @Basic
    @Column(name = "specialization")
    private String specialization;
    @OneToMany(mappedBy = "officeByOfficeNumber")
    private Collection<DoctorEntity> doctorsByOfficeNumber;
    public int getFloor() {
        return floor;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfficeEntity that = (OfficeEntity) o;

        if (officeNumber != that.officeNumber) return false;
        if (floor != that.floor) return false;
        if (specialization != null ? !specialization.equals(that.specialization) : that.specialization != null)
            return false;

        return true;
    }
    @Override
    public int hashCode() {
        int result = officeNumber;
        result = 31 * result + floor;
        result = 31 * result + (specialization != null ? specialization.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "OfficeEntity{" +
                "officeNumber=" + officeNumber +
                ", floor=" + floor +
                ", specialization='" + specialization + '\'' +
                ", doctorsByOfficeNumber=" + doctorsByOfficeNumber +
                '}';
    }
}
