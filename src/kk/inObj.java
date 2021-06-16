package kk;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

//Status: +

// Класс для записи объектов в файл
public class inObj  {

	public inObj (ArrayList <Student> new_student) throws IOException {

		ArrayList <Student> student_list = new ArrayList<Student>(); // Массив для людей

		File file = new File("student.txt"); // Объявление файла

		if (!file.isFile()) { // Если fil это не файл
			file.createNewFile();
		}

		// Чтение имеющихся данных из файла
		// Открывается поток для чтения и записи
		try (FileInputStream fis = new FileInputStream("student.txt");) {
			if (file.length() > 0) { // Если в файле что-то есть 

				// Открывается поток для чтения объектов
				ObjectInputStream ois = new ObjectInputStream(fis);

				// В массив stud считываются объекты
				student_list = (ArrayList <Student>) ois.readObject();

				// Закрывается поток чтения объектов
				ois.close();
			}
		} catch (ClassNotFoundException ex) {
			System.out.println("no + "+ ex.getMessage());
		}

		// Добавление всех новых студентов в массив
		if (student_list.addAll(new_student)) { 
			System.out.println("Добавление в массив - успешно");
		} else {
			System.out.println("Добавление в массив - ошибка");
		}

		// Поток для записи
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.txt"))) {
			oos.writeObject(student_list); // Запись объектов
			oos.flush();
			oos.close();
		}
		catch (IOException ex) {
			System.out.println("no "+ ex.getMessage());
		}
	} 
}

