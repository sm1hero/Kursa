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

	public inObj(ArrayList <Student> new_stud) throws IOException {
			ArrayList <Student> stud = new ArrayList<Student>(); // Массив для людей
			File Students = new File("students.txt"); // Объявление файла
		if (!Students.isFile()) { // Если fil это не файл
			Students.createNewFile();
		}

		// Чтение имеющихся данных из файла
		// Открывается поток для чтения и записи
		try (FileInputStream fis = new FileInputStream("students.txt");) {
			if (Students.length() > 0) { // Если в файле что-то есть 

				// Открывается поток для чтения объектов
				ObjectInputStream ois = new ObjectInputStream(fis);

				// В массив stud считываются объекты
				stud = (ArrayList <Student>) ois.readObject();

				// Закрывается поток чтения объектов
				ois.close();
			}
		} catch (ClassNotFoundException ex) {
			System.out.println("no + "+ ex.getMessage());
		}

		stud.addAll(new_stud); // Добавление всех новых в массив
		// Поток для записи
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.txt"))) {
			oos.writeObject(stud); // Запись объектов
			oos.flush();
			oos.close();
		}
		catch (IOException ex) {
			System.out.println("no "+ ex.getMessage());
		}
	} 
}

