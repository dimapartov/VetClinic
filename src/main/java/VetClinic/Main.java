package VetClinic;

import VetClinic.Entities.CardEntity;
import VetClinic.Entities.ClientEntity;
import VetClinic.Entities.OfficeEntity;
import VetClinic.Entities.PetEntity;
import VetClinic.Helpers.QueryHandler;
import VetClinic.Util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Список запросов:");
            List<String> queryList = new ArrayList<>();
            queryList.add("1. Запрос, выводящий id пользователей c определенным именем");
            queryList.add("2. Запрос, выводящий имена клиентов с определенной фамилией и" + " " +
                    "имеющих питомцев определенной породы");
            queryList.add("3. Запрос, выводящий этаж, на котором расположен кабинет доктора определенной специальности");
            queryList.add("4. Запрос, выводящий все диагнозы животных определенного типа");
            queryList.add("5. Запрос, выводящий имена клиентов, у которых собаки получали определенный диагноз");
            queryList.add("6. Запрос, позволяющий добавить клиента");
            queryList.add("7. Запрос, позволяющий удалить животное с определенным id");
            queryList.add("8. Запрос, позволяющий изменить пол животного у животного с определенным id");
            queryList.add("9. Выход из программы");
            queryList.forEach(System.out::println);
            System.out.print("Введите номер запроса: ");
            Scanner scanner = new Scanner(System.in);
            int queryNumber = scanner.nextInt();
            switch (queryNumber) {
                case (1):
                    System.out.println("Выбран первый запрос.");
                    System.out.print("Введите имя: ");
                    Scanner  scanner1 = new Scanner(System.in);
                    String name = scanner1.nextLine();
                    System.out.println(QueryHandler.getFirstQueryResult(name));
                    break;
                case (2):
                    System.out.println("Выбран второй запрос.");
                    System.out.print("Введите фамилию: ");
                    Scanner scanner2 = new Scanner(System.in);
                    String inputSurname = scanner2.nextLine();
                    System.out.print("Введите породу: ");
                    String inputBreed = scanner2.nextLine();
                    List<ClientEntity> list = QueryHandler.getSecondQueryResult(inputSurname, inputBreed);
                    for (ClientEntity client : list) {
                        System.out.println(client.getName());
                    }
                    break;
                case (3):
                    System.out.println("Выбран третий запрос.");
                    Scanner scanner3 = new Scanner(System.in);
                    System.out.print("Введите специализацию: ");
                    String specialization = scanner3.nextLine();
                    List<OfficeEntity> list1 = QueryHandler.getThirdQueryResult(specialization);
                    for (OfficeEntity office : list1) {
                        System.out.println(office.getFloor());
                    }
                    break;
                case (4):
                    System.out.println("Выбран четвертый запрос.");
                    Scanner scanner4 = new Scanner(System.in);
                    System.out.print("Введите тип животного: ");
                    String type = scanner4.nextLine();
                    List<CardEntity> list2 = QueryHandler.getFourthQueryResult(type);
                    for (CardEntity card : list2) {
                        System.out.println(card.getDiagnosis());
                    }
                    break;
                case (5):
                    System.out.println("Выбран пятый запрос.");
                    System.out.print("Введите диагноз: ");
                    Scanner scanner5 = new Scanner(System.in);
                    String diagnosis = scanner5.nextLine();
                    List<ClientEntity> list3 = QueryHandler.getFifthQueryResult(diagnosis);
                    for (ClientEntity client : list3) {
                        System.out.println(client.getName());
                    }
                    break;
                case (6):
                    System.out.println("Выбран шестой запрос.");
                    Scanner scanner6 = new Scanner(System.in);
                    System.out.print("Введите логин: \n");
                    String login = scanner6.nextLine();
                    System.out.print("Введите имя: \n");
                    String userName = scanner6.nextLine();
                    System.out.print("Введите фамилию: \n");
                    String surname = scanner6.nextLine();
                    System.out.print("Введите отчество: \n");
                    String patronymic = scanner6.nextLine();
                    System.out.print("Введите номер телефона: \n");
                    String phoneNumber = scanner6.nextLine();
                    System.out.print("Введите пароль: \n");
                    String password = scanner6.nextLine();

                    ClientEntity client = new ClientEntity();

                    client.setClientId(login);
                    client.setName(userName);
                    client.setSurname(surname);
                    client.setPatronymic(patronymic);
                    client.setPhoneNumber(phoneNumber);
                    client.setPassword(password);

                    QueryHandler.getAllClients();
                    QueryHandler.addClient(client);
                    QueryHandler.getAllClients();
                    break;
                case (7):
                    System.out.println("Выбран седьмой запрос.");
                    System.out.print("Введите id животного: ");
                    Scanner scanner7 = new Scanner(System.in);
                    int id = scanner7.nextInt();
                    QueryHandler.getAllPets();
                    List<PetEntity> list4 = QueryHandler.getAllPetsAsList();
                    for (PetEntity pet : list4) {
                        int petId = pet.getPetId();
                        if (petId == id) {
                            QueryHandler.deletePet(pet);
                        }
                    }
                    QueryHandler.getAllPets();
                    break;
                case (8):
                    System.out.println("Выбран восьмой запрос.");
                    System.out.print("Введите id животного: ");
                    Scanner scanner8 = new Scanner(System.in);
                    int petId = Integer.parseInt(scanner8.nextLine());
                    System.out.print("Введите пол животного: ");
                    String gender = scanner8.nextLine();
                    List<PetEntity> list5 = QueryHandler.getPetForUpdate(petId);
                    for (PetEntity pet : list5) {
                        int petIdFromList = pet.getPetId();
                        if (petIdFromList == petId) {
                            System.out.println(pet);
                            pet.setGender(gender);
                            QueryHandler.changeGender(pet);
                            System.out.println(pet);
                        }
                    }
                    break;
                case (9):
                    HibernateUtil.shutDown();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Выберите корректный номер запроса из списка");
            }
        }
    }
}
