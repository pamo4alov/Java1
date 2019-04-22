import javax.swing.*;
import java.awt.event.*;

/*
1. Расширить задачу про котов и тарелки с едой.
2. Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды
(например, в миске 1 0 еды, а кот пытается покушать 1 5-20).
3. Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны). Если коту
удалось покушать (хватило еды), сытость = true.
4. Считаем, что если коту мало еды в тарелке, то он её просто не трогает, то есть не может быть
наполовину сыт (это сделано для упрощения логики программы).
5. Создать массив котов и тарелку с едой, попросить всех котов покушать из этой тарелки и
потом вывести информацию о сытости котов в консоль.
6. Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку.
*/

class Plate {
    private int food;
    public Plate(int food) { this.food = food; }
    public void decreaseFood(int requested) { food -= (requested <= food) ? requested: food; }
    public int getFood() { return food; }
    public void addFood(int volume) { food += volume; }
    public void info() { System.out.println("plate: " + food); }
}

class Cat {
    private String name;
    private int appetite;
    private boolean fed;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.fed = false;
    }

    public boolean eat(Plate p) {
        if (fed)
            return false;
        else if (this.appetite <= p.getFood()) {
            System.out.println("cat " + name + " has eaten " + appetite + " unit(s) of food");
            p.decreaseFood(appetite);
            fed = true;
            return fed;
        }
        else {
            System.out.println("cat " + name + " remained hungry");
            return false;
        }
    }

    public boolean isFed() { return this.fed; }
}

class Main {
    public static void main(String[] args) {
        Cat[] catalog = getCats();
        Plate plate = new Plate(30);
        for (Cat cat : catalog) {
            plate.info();
            cat.eat(plate);
        }
    }

    public static Cat[] getCats() {
        Cat mansik = new Cat("Mansik", 5);
        Cat musik = new Cat("Musik", 15);
        Cat borzik = new Cat("Borzik", 8);
        Cat lors = new Cat("Lors", 10);
        return new Cat[]{mansik, musik, borzik, lors};
    }
}

/*
    1. Необходимо создать окно с 1 полем (Фамилия, Имя, Отчество) и кнопку
    2. По нажатии кнопки открывается еще 1 окно с тремя полями и кнопкой, в поля вводишь ФИО и нажимаешь кнопку ОК.
    3. Дополнительное окно закрывается и в основном окне в поле оседает ФИО.
*/

class SimpleWindow extends JFrame {
    private JLabel lblSurname;
    private JLabel lblName;
    private JLabel lblMidname;
    private JLabel lblSurnameText;
    private JLabel lblNameText;
    private JLabel lblMidnameText;
    private JButton btnFillName;
    private JButton btnExit;

    public SimpleWindow(String title) {
        super(title);
        int width = 350, height = 250;
        setBounds(400, 300, width, height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        lblSurname = new JLabel();
        lblSurname.setText("Фамилия: ");
        lblSurname.setBounds(10, 10, width / 2 - 20, 50);
        add(lblSurname);

        lblName = new JLabel();
        lblName.setText("Имя: ");
        lblName.setBounds(10, 50, width / 2 - 20, 50);
        add(lblName);

        lblMidname = new JLabel();
        lblMidname.setText("Отчество: ");
        lblMidname.setBounds(10, 90, width / 2 - 20, 50);
        add(lblMidname);

        lblSurnameText = new JLabel();
        lblSurnameText.setText("... ");
        lblSurnameText.setBounds(lblSurname.getWidth() + 20, 10, width / 2 - 10, 50);
        add(lblSurnameText);

        lblNameText = new JLabel();
        lblNameText.setText("... ");
        lblNameText.setBounds(lblName.getWidth() + 20   , 50, width / 2 - 10, 50);
        add(lblNameText);

        lblMidnameText = new JLabel();
        lblMidnameText.setText("... ");
        lblMidnameText.setBounds(lblMidname.getWidth() + 20, 90, width / 2 - 10, 50);
        add(lblMidnameText);

        btnFillName = new JButton();
        btnFillName.setText("Заполнить Ф.И.О.");
        btnFillName.setBounds(10, height - 80, 150, 30);
        btnFillName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int width = 350, height = 250;
                JDialog innerDialog = new JDialog();
                innerDialog.setModal(true);
                innerDialog.setTitle("Модальное окно");
                innerDialog.setBounds(500, 400, width, height);
                innerDialog.setResizable(false);
                innerDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                innerDialog.setLayout(null);

                JTextField txtSurname = new JTextField();
                txtSurname.setBounds(10, 10, width - 35, 25);
                txtSurname.setText("");
                innerDialog.add(txtSurname);

                JTextField txtName = new JTextField();
                txtName.setBounds(10, txtSurname.getHeight() + 25, width - 35, 25);
                txtName.setText("");
                innerDialog.add(txtName);

                JTextField txtMidname = new JTextField();
                txtMidname.setBounds(10, txtSurname.getHeight() + txtName.getHeight() + 40, width - 35, 25);
                txtMidname.setText("");
                innerDialog.add(txtMidname);

                JButton btnOk = new JButton();
                btnOk.setText("Заполнить Ф.И.О.");
                btnOk.setBounds(width / 2 - 85, height - 80, 150, 30);
                btnOk.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (txtSurname.getText().equalsIgnoreCase("")
                                || txtName.getText().equalsIgnoreCase("")
                                || txtMidname.getText().equalsIgnoreCase("")) {
                            innerDialog.setTitle("Заполните все поля!");
                        } else {
                            lblSurnameText.setText(txtSurname.getText());
                            lblNameText.setText(txtName.getText());
                            lblMidnameText.setText(txtMidname.getText());
                            innerDialog.setVisible(false);
                        }

                    }
                });
                innerDialog.add(btnOk);

                innerDialog.setVisible(true);
            }
        });
        add(btnFillName);

        btnExit = new JButton("Выход");
        btnExit.setBounds(btnFillName.getWidth() + 25, height - 80, 150, 30);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(btnExit);

        setVisible(true);
    }
}

public class HW_7_1 {
    public static void main(String[] args) {
        new SimpleWindow("Главное окно");
    }
}

