package kk;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.FlowLayout;

import javax.swing.table.DefaultTableModel;

//Status: -+

public class searchBall extends JPanel{

	// Для записи и чтения из файла
	private static final long serialVersionUID = 1L;
	
	// Массив с людьми
	ArrayList <Student> stud = new ArrayList<Student>();	
	
	// Фукнция вывода инфы о человеке. Она принимает лишь нужную нам фамилию,
	// которую мы получили в функции searchWin
	//ПЕРЕПИСАТЬ НА ГРУППУ
	public searchBall (String ball) throws IOException
	{
		super();

		try {
			stud = getStudent(); // Получаем данные о людях
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		setLayout(new FlowLayout());
		
		// Создаём шапку для таблицы
		Object columnsHeader[] = new String[] {"Имя", "Фамилия", "Группа", "Средний балл"};

		// Получаем инфу о человеке с выбранной в интерфейсе фамилией
		//Student student = Student.search(stud, group);

		DefaultTableModel tableModel = new DefaultTableModel(); // Объявление модели таблицы
		tableModel.setColumnIdentifiers(columnsHeader);
		for (int i = 0; i < stud.size(); i++)
		{
			if (stud.get(i).getsrBall().equals(ball)) {
				Object[] ab = new String[]{stud.get(i).name, stud.get(i).surname, stud.get(i).group, stud.get(i).srBall};
				tableModel.addRow(ab);
			}
		} 
		/* int i = 1;

		// Создание строки с данными о нашем человеке типа Object[]
		Object[] ab = new String[]{stud.get(i).id, stud.get(i).name, stud.get(i).surname, stud.get(i).group};
		tableModel.addRow(ab); // Добавление этой строки на таблицу */

		JTable tab = new JTable(tableModel); // Объявление таблицы
		Box contents = new Box(BoxLayout.Y_AXIS);
		contents.add(new JScrollPane(tab)); // Добавление "ползунка" на таблицу
		this.add(contents);
	}

	// Функция получения "людей" из файла
	public ArrayList <Student> getStudent() throws IOException {
		ArrayList <Student> stud = new ArrayList<Student>();

		try (FileInputStream fis = new FileInputStream("student.txt");) {
			ObjectInputStream ois = new ObjectInputStream(fis);
			stud = (ArrayList <Student>) ois.readObject();
			ois.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("no + " + ex.getMessage());
		}
		return stud;
	}
}
