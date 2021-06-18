package kk;
import java.io.Serializable;


//Status: +

// Это объявление класса (можно назвать объектом).т
// implements Serializable нужно для записи данных в файл
// По заданию у твоего объекта есть 4 переменные, так называемых поля: фамилия, имя, группа и 4 предмета
public class Student implements Serializable{

	String id 	   = "";
	String surname = "";
	String name    = "";
	String group   = "";
	String[] ball  = new String[5];

	// Метод Student - конструктор
	// Они нужны, чтобы в программе ты смог задать переменную класса (можно назвать экземпляр)
	// Дело в том, что класс это почти тип данных, вроде строки или целого,
	// когда в программе ты увидишь что-то типа Student person = new Student(....)
	// это объявление переменной person типа знак, в таком случае говорят "объявлён экземпляр объекта"

	// ... = new Student(Ivan, Ivanov, Govno-12-23452PKS, 5, 5, 5, 5) - Пример создания экземпляра класса Student
	//
	public Student(String name, String surname, String group, String ball_1, String ball_2, String ball_3, String ball_4, String ball_5)
	{
		
		this.surname =  surname;
		this.name    =  name;
		this.group   =  group;

		this.ball[0] = ball_1;
		this.ball[1] = ball_2;
		this.ball[2] = ball_3;
		this.ball[3] = ball_4;
		this.ball[4] = ball_5;
	}

}