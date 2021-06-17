package kk;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

//Status:  +

public class searchWin extends JDialog{

    // Для записи и чтения из файла
	private static final long serialVersionUID = 1L;

    // Объявление массива с людьми
	public List <Student> stud = new ArrayList <Student> ();

    // Объявления массива для фамилий
	List <String> ball_list = new ArrayList <String> ();

	JComboBox select_ball; // Список для фамилий
	public String srBall;

	searchWin(JFrame Parent){
		super(Parent,"Введите средний балл для поиска", true); 
		try {
            // Поток для чтения файла
            FileInputStream fis = new FileInputStream("student.txt");

            if (fis.available() > 0) {
                // Поток для чтения объектов
                ObjectInputStream ois = new ObjectInputStream(fis);

                // Считывание данных из файла
            	stud = (List <Student> ) ois.readObject();

                // Цикл для добавления фамилий в массив
                // Он "проходит" каждого человека и берёт только фамилию
                for (Student i: stud) {
                    ball_list.add(i.group); // Добавление фамилии в массив
                }

                // Добавление фамилий в список
                select_ball = new JComboBox(ball_list.toArray());

                // Добавление списка на форму
                this.add(select_ball);

                // Кнопка для поиска
                JButton find = new JButton("Найти");
               
                // Добавление кнопки для поиска
                this.add(find);

                find.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // По кнопке закрывает окно
						searchWin.this.dispose();//ссылается на его экземпляр внешнего класса
                    }
                });
                ois.close(); // Закрытие потока чтения объектов
            } else {
                System.out.println("Невозможно прочитать из файла!");
            }
       }
        catch (FileNotFoundException ex1) {
            System.out.print(ex1.getMessage());
        }
        catch (IOException ex2) {
            System.out.print(ex2.getMessage());
        }
		catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	
	 	this.setLayout(new FlowLayout()); // Расположение FlowLayout
	// Размер окошка с поиском, хоть и не будет заметно, так как оно на форме, 400 на 200
	   this.setSize(400, 200);
	   setLocationRelativeTo(Parent); // Ссылается на окно, в котором вызвано это
}
    // Функция получения фамилии из списка JComboBox
	public String getSearch()
	{
		return select_ball.getSelectedItem().toString();
	}
}
