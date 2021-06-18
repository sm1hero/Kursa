package kk;
import javax.swing.*;
import java.io.*;
import java.util.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;


// Это функции для вывода данных (3 пункт меню)

// Класс delStudent наследуется от класса JPanel
public class delStudent extends JPanel{

	// Объявление массива людей
	ArrayList <Student> stud = new ArrayList<Student>();	

	DefaultTableModel tableModel = new DefaultTableModel(); // Объявление модели таблицы
	JTable tab = new JTable(tableModel); 					// Объявление таблицы

	public delStudent() throws IOException
	{
		super(); // Конструктор delStudent передает данные в конструктор JPanel, так как первое унаследуется от второго. 
		
		
		JButton delButton = new JButton("Удалить"); // Объявление кнопки удаления строки
		this.add(delButton); 						// Добавление этой кнопки на форму
		showTable(); 								// её функция показа таблицы
		
			delButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
						// По кнопке Delete выбранная строка удаляется
						int[] selectedRows = tab.getSelectedRows();
						int k = 0;

						for (int i = 0; i < selectedRows.length; i++) {
							stud.remove(selectedRows[i] - k);
							k++;
						}

						// После удаления объект переписывается
						try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.txt"))) {
							oos.writeObject(stud); // Запись объекта
							oos.flush();
							oos.close();
						} catch (IOException  ex) {
							System.out.println("no "+ex.getMessage());	
						}

					showTable(); // Показ таблицы
					validate();	
				}
			}
		);
		
	}
	// Функция получения людей, как и в других файлах
	public ArrayList <Student> getStudent() throws IOException
	{
		ArrayList <Student> students = new ArrayList<Student>(); // Объявление массива для людей

		// Открывание потока чтения из файла
		try (FileInputStream fis=new FileInputStream("student.txt");) {
			ObjectInputStream ois = new ObjectInputStream(fis);  // Поток чтения объектов
			students=(ArrayList<Student>) ois.readObject(); 	 // Данные считываются в массив как объект
			ois.close(); 										 // Закрывание потока 
		} catch (ClassNotFoundException ex) {
			System.out.println("no+ "+ex.getMessage());
		}

		return students;
	}

	// Функция показа таблицы
	public void showTable() {
		// Очищение таблицы
		if (tableModel.getRowCount() > 0) {
			for (int i = tableModel.getRowCount() - 1; i > -1; i--) {
				tableModel.removeRow(i);
			}
		}
	

		try {
			stud = getStudent(); 	// Получение людей из фалйа	
			
			Collections.sort(stud, new Comparator<Student>() {
				public int compare(Student o1, Student o2) {
					return o1.group.compareTo(o2.group);
				}
			});
		} catch (IOException e) {
			System.out.println(e.getMessage()); 
		}

		// "Шапка" таблицы
		Object columnsHeader[] = new String[] {"Имя", "Фамилия", "Группа", "Оценка 1","Оценка 2", "Оценка 3", "Оценка 4", "Оценка 5", "Средний балл"};

		// Добавление "шапки"
		tableModel.setColumnIdentifiers(columnsHeader);

		for (int i = 0; i < stud.size(); i++) {
			// Строка с данными о человеке с индексом цикла i
			Object[] ab = new String[]{stud.get(i).name, stud.get(i).surname, stud.get(i).group, stud.get(i).ball[0],
				stud.get(i).ball[1], stud.get(i).ball[2], stud.get(i).ball[3],
				stud.get(i).ball[4], stud.get(i).srBall};

			tableModel.addRow(ab); // Добавление этой строки
		} 	

		Box contents = new Box(BoxLayout.Y_AXIS);
		contents.add(new JScrollPane(tab));    
		this.add(contents);
	}
}
