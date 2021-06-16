package kk;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.awt.FlowLayout;

import javax.swing.table.DefaultTableModel;

//Status: -+

public class searchGroup extends JPanel{

	// Для записи и чтения из файла
	private static final long serialVersionUID = 1L;
	
	// Массив с людьми
	List <Student> stud = new ArrayList<Student>();	
	
	// Фукнция вывода инфы о человеке. Она принимает лишь нужную нам фамилию,
	// которую мы получили в функции searchWin
	//ПЕРЕПИСАТЬ НА ГРУППУ
	public searchGroup (String group) throws IOException
	{
		super();

		try {
			stud = getStudent(); // Получаем данные о людях
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		setLayout(new FlowLayout());
		
		// Создаём шапку для таблицы
		Object columnsHeader[] = new String[] {"Имя", "Фамилия", "Группа", "Предмет 1", "Предмет 2", "Предмет 3", "Предмет 4", "Предмет 5"};

		// Получаем инфу о человеке с выбранной в интерфейсе фамилией
		//Student student = Student.search(stud, group);

		DefaultTableModel tableModel = new DefaultTableModel(); // Объявление модели таблицы
		tableModel.setColumnIdentifiers(columnsHeader);

		// Создание строки с данными о нашем человеке типа Object[]
		Object[] ab = new String[] {group.name, group.surname, group.znak, fam.rojd.toString()};
		tableModel.addRow(ab); // Добавление этой строки на таблицу

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
			stud = (ArrayList <Student> ) ois.readObject();
			ois.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("no + " + ex.getMessage());
		}
		return stud;
	}
}
