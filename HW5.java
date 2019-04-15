public class HW5 {

    public static void main(String[] args) {
        Employee [] person = new Employee [5];
        person[0] = new Employee("Ivanov Ivan Ivanovich", "Accountant", "IvanovII@mail.ru", "89132376449", 35000, 29);
        person[1] = new Employee("Vasiliev Vasiliy Vasilievich", "Expert", "VasilievVV@mail.ru", "89132031039", 50000, 42);
        person[2] = new Employee("Petrov Petr Petrovich", "Specialist", "PetrovPP@mail.ru", "89132538945", 40000, 35);
        person[3] = new Employee("Andreev Andrey Andreevich", "Chief", "AndreevAA@mail.ru", "89132284567", 70000, 54);
        person[4] = new Employee("Fedorov Fedor Fedorovich", "Consultant", "FedorovFF@mail.ru", "89132699831", 35000, 38);

        for (Employee list : person) {
            if (list.getAge() > 40)
                System.out.println(list);
        }
    }
}


class Employee {
    private String fullName;
    private String position;
    private String email;
    private String telephone;
    private int pay;
    private int age;

    public Employee (String fullName, String position, String email, String telephone, int pay, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.telephone = telephone;
        this.pay = pay;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "Full name: " + fullName + "\n" +
                "Post: " + position + "\n" +
                "E-mail: " + email + "\n" +
                "Tel.: " + telephone + "\n" +
                "Salary: " + pay + "\n" +
                "Age: " + age + "\n";
    }
}
