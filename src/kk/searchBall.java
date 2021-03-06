package kk;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.FlowLayout;

import javax.swing.table.DefaultTableModel;

//Status: -+

public class searchBall extends JPanel {

	// Для записи и чтения из файла
	private static final long serialVersionUID = 1L;
	
	// Массив со студентами
	ArrayList <Student> stud = new ArrayList<Student>();	
	
	// Функция нахождения студента с оценкой 2.

	public searchBall () throws IOException {
		super();

		try {
			stud = getStudent(); // Получаем данные о студентах
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		setLayout(new FlowLayout());
		
		// Создаём шапку для таблицы
		Object columnsHeader[] = new String[] {"Имя", "Фамилия", "Группа", "Оценка 1", "Оценка 2", 
		"Оценка 3", "Оценка 4", "Оценка 5"};

		DefaultTableModel tableModel = new DefaultTableModel(); // Объявление модели таблицы
		tableModel.setColumnIdentifiers(columnsHeader);

		int alive = 0;

		//Цикл выводит всех i-тых студентов
		for (int i = 0; i < stud.size(); i++) {
			if (stud.get(i).ball[0].equals("2") || stud.get(i).ball[1].equals("2") || stud.get(i).ball[2].equals("2") || 
				stud.get(i).ball[3].equals("2") || stud.get(i).ball[4].equals("2"))
			{
				Object[] ab = new String[]{stud.get(i).name, stud.get(i).surname, stud.get(i).group, stud.get(i).ball[0],
					stud.get(i).ball[1],stud.get(i).ball[2], stud.get(i).ball[3],
					stud.get(i).ball[4]};
				tableModel.addRow(ab);
				alive++;
			}
		} 

		if (alive == 0){
			JOptionPane.showMessageDialog(new JPanel(),"Таких студентов не найдено");
		}

		JTable tab = new JTable(tableModel); 		// Объявление таблицы
		Box contents = new Box(BoxLayout.Y_AXIS);
		contents.add(new JScrollPane(tab)); 		// Добавление "ползунка" на таблицу
		this.add(contents);
	}

	// Функция получения списка студентов из файла
	public ArrayList <Student> getStudent() throws IOException {
		ArrayList <Student> student_list = new ArrayList<Student>();

		try (FileInputStream fis = new FileInputStream("student.txt");) {
			ObjectInputStream ois = new ObjectInputStream(fis);
			student_list = (ArrayList <Student>) ois.readObject();
			ois.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("no + " + ex.getMessage());
		}
		return student_list;
	}
}
