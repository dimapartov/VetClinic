package VetClinic.Helpers;

import VetClinic.Entities.CardEntity;
import VetClinic.Entities.ClientEntity;
import VetClinic.Entities.OfficeEntity;
import VetClinic.Entities.PetEntity;
import VetClinic.Util.HibernateUtil;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class QueryHandler {
    public static List<ClientEntity> getFirstQueryResult(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<ClientEntity> query = session
                    .createQuery("SELECT clientId FROM ClientEntity WHERE name = :name", ClientEntity.class);
            query.setParameter("name", name);
            return query.getResultList();
        }
    }
    public static List<ClientEntity> getSecondQueryResult(String surname, String breed) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<ClientEntity> query = session.createQuery("SELECT c FROM ClientEntity c " +
                    "JOIN PetEntity p ON p.clientByClientId=c.clientId " +
                    "WHERE c.surname=:surname AND p.breed=:breed", ClientEntity.class);
            query.setParameter("surname", surname);
            query.setParameter("breed", breed);
            return query.getResultList();
        }
    }
    public static List<OfficeEntity> getThirdQueryResult(String specialization) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<OfficeEntity> query = session.createQuery("SELECT o FROM OfficeEntity o " +
                    "JOIN DoctorEntity d ON d.officeByOfficeNumber.officeNumber=o.officeNumber " +
                    "WHERE d.specialization=:specialization", OfficeEntity.class);
            query.setParameter("specialization", specialization);
            return query.getResultList();
        }
    }
    public static List<CardEntity> getFourthQueryResult(String type) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<CardEntity> query = session.createQuery("SELECT c FROM CardEntity c JOIN PetEntity p " +
                    "ON c.petByPetId.petId=p.petId WHERE p.type=:type", CardEntity.class);
            query.setParameter("type", type);
            return query.getResultList();
        }
    }
    public static List<ClientEntity> getFifthQueryResult(String diagnosis) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<ClientEntity> query = session.createQuery("SELECT client FROM ClientEntity client " +
                    "JOIN PetEntity pet ON pet.clientByClientId = client.clientId JOIN CardEntity card " +
                    "ON card.petByPetId.petId = pet.petId WHERE card.diagnosis=:diagnosis", ClientEntity.class);
            query.setParameter("diagnosis", diagnosis);
            return query.getResultList();
        }
    }
    public static void addClient(ClientEntity client) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(client);
            transaction.commit();
        }

    }
    public static void deletePet(PetEntity pet) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(pet);
            transaction.commit();
        }
    }
    public static void changeGender(PetEntity pet) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(pet);
            transaction.commit();
        }
    }
    public static void getAllClients() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.createNamedQuery("findAllClients", ClientEntity.class).getResultList()
                    .forEach(System.out::println);
        }
    }
    public static List<PetEntity> getAllPetsAsList() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createNamedQuery("findAllPets", PetEntity.class).getResultList();
        }
    }
    public static void getAllPets() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.createNamedQuery("findAllPets", PetEntity.class).getResultList()
                    .forEach(System.out::println);
        }
    }
    public static List<PetEntity> getPetForUpdate(int petId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<PetEntity> query = session.createQuery("SELECT pet FROM PetEntity pet " +
                    "WHERE pet.petId=:petId", PetEntity.class);
            query.setParameter("petId", petId);
            return query.getResultList();
        }
    }
}
