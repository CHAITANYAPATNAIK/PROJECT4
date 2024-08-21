import java.util.*;

class VaccinationManagementSystem {
    private Map<String, Child> records = new HashMap<>();
    private List<Appointment> appointments = new ArrayList<>();

    public void addChild(String name, int age) {
        if (records.putIfAbsent(name, new Child(name, age)) == null) {
            System.out.println("Record for " + name + " added.");
        } else {
            System.out.println("Record for " + name + " already exists.");
        }
    }

    public void scheduleVaccination(String name, String date, String vaccine) {
        if (records.containsKey(name)) {
            appointments.add(new Appointment(name, date, vaccine));
            System.out.println("Appointment for " + name + " scheduled on " + date + " for " + vaccine + ".");
        } else {
            System.out.println("No record for " + name + ". Add the child first.");
        }
    }

    public void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No upcoming appointments.");
        } else {
            appointments.forEach(app -> System.out.println(app.getName() + " - " + app.getVaccine() + " on " + app.getDate()));
        }
    }

    public void updateVaccinationRecord(String name, String vaccine, String date) {
        Child child = records.get(name);
        if (child != null) {
            child.addVaccination(new Vaccination(vaccine, date));
            System.out.println("Record updated for " + name + " with " + vaccine + " on " + date + ".");
        } else {
            System.out.println("No record found for " + name + ".");
        }
    }

    public void viewVaccinationRecord(String name) {
        Child child = records.get(name);
        if (child != null) {
            List<Vaccination> vaccinations = child.getVaccinations();
            if (vaccinations.isEmpty()) {
                System.out.println("No vaccinations recorded.");
            } else {
                vaccinations.forEach(v -> System.out.println(v.getVaccine() + " on " + v.getDate()));
            }
        } else {
            System.out.println("No record found for " + name + ".");
        }
    }
}

class Child {
    private String name;
    private List<Vaccination> vaccinations = new ArrayList<>();

    public Child(String name, int age) {
        this.name = name;
    }

    public void addVaccination(Vaccination vaccination) {
        vaccinations.add(vaccination);
    }

    public List<Vaccination> getVaccinations() {
        return vaccinations;
    }
}

class Vaccination {
    private String vaccine;
    private String date;

    public Vaccination(String vaccine, String date) {
        this.vaccine = vaccine;
        this.date = date;
    }

    public String getVaccine() { return vaccine; }
    public String getDate() { return date; }
}

class Appointment {
    private String name;
    private String date;
    private String vaccine;

    public Appointment(String name, String date, String vaccine) {
        this.name = name;
        this.date = date;
        this.vaccine = vaccine;
    }

    public String getName() { return name; }
    public String getDate() { return date; }
    public String getVaccine() { return vaccine; }
}

public class Main {
    public static void main(String[] args) {
        VaccinationManagementSystem system = new VaccinationManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Child\n2. Schedule Vaccination\n3. View Appointments\n4. Update Record\n5. View Record\n6. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter child's name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter child's age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline
                    system.addChild(name, age);
                    break;
                case 2:
                    System.out.print("Enter child's name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter vaccination date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter vaccine name: ");
                    String vaccine = scanner.nextLine();
                    system.scheduleVaccination(name, date, vaccine);
                    break;
                case 3:
                    system.viewAppointments();
                    break;
                case 4:
                    System.out.print("Enter child's name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter vaccine name: ");
                    vaccine = scanner.nextLine();
                    System.out.print("Enter vaccination date (YYYY-MM-DD): ");
                    date = scanner.nextLine();
                    system.updateVaccinationRecord(name, vaccine, date);
                    break;
                case 5:
                    System.out.print("Enter child's name: ");
                    name = scanner.nextLine();
                    system.viewVaccinationRecord(name);
                    break;
                case 6:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
