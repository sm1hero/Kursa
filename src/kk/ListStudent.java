package kk;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.FlowLayout;
import javax.swing.table.DefaultTableModel;


//Status: +

public class ListStudent extends JPanel{

	// Для записи и чтения из файла
	private static final long serialVersionUID = 1L;

	// Объявление массива с людьми
	ArrayList <Student> stud = new ArrayList<Student>();

	public ListStudent() throws IOException
	{
		super();

		try {
			stud = getStudent(); // Получаем данные из базы
			//Student.groupDate(stud); // Группируем их по дате
		} catch (IOException e) {
			System.out.println("list "+ e.getMessage()); 
		}

		setLayout(new FlowLayout()); // Расположение FlowLayout

		// Создание "шапки" таблицы
		Object columnsHeader[] = new String[] {"Имя", "Фамилия", "Группа", "Предмет 1", "Предмет 2", "Предмет 3", "Предмет 4", "Предмет 5"};

		// Объявление модели таблицы
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnsHeader); // Добавление "шапки" на таблицу
		
		// Цикл, в котором добавляются строки с данными в таблицу
		for (int i = 0; i < stud.size(); i++)
		{
			// Создание строки типа Object[]
			Object[] ab = new String[]{stud.get(i).name, stud.get(i).surname, stud.get(i).group, stud.get(i).ball[1],
				stud.get(i).ball[2],stud.get(i).ball[3], stud.get(i).ball[4],
				stud.get(i).ball[5]};

			tableModel.addRow(ab); // Добавление строки
		}
		
		JTable tab = new JTable(tableModel); // Объявление таблицы
		Box contents = new Box(BoxLayout.Y_AXIS);

		// Добавление "ползунка" на таблицу (его не видно, если вся таблица убирается в окне)
		contents.add(new JScrollPane(tab));
		this.add(contents);
	} 

	// Функция получения данных из файла
	public ArrayList <Student> getStudent() throws IOException {
		ArrayList <Student> stud = new ArrayList<Student>(); // Сюда будем класть данные
		// Открываем поток записи в файл fil.txt
		try (FileInputStream fis = new FileInputStream("students.txt");) {

			// Открываем поток записи объектов
			ObjectInputStream ois = new ObjectInputStream(fis);

			// Считываем объекты из файла в уже созданный массив stud
			stud = (ArrayList <Student> ) ois.readObject();
		
			ois.close(); // Закрываем поток

		} catch (ClassNotFoundException ex) {
			System.out.println("no+ "+ ex.getMessage());
		}

		return stud; // Функция выдаст этот массив
	}
}
