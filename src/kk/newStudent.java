package kk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

//Status: 

public class newStudent extends JPanel{

	String count = "1";
	int buffer = Integer.parseInt(count);

	// Для записи и чтения из файла
	private static final long serialVersionUID = 1L;
	// Массив для данных
	public ArrayList <Student> stud = new ArrayList<Student>();

	// Ниже так называемые "мекти", просто текст и поле ввода
	JLabel namLab = new JLabel("Имя");
	JTextField namTxt = new JTextField(20); 

	JLabel famLab = new JLabel("Фамилия");
	JTextField famTxt = new JTextField(20); 

	JLabel groupLab = new JLabel("Группа");
	JTextField groupTxt = new JTextField(20); 

	JLabel ball_1Lab = new JLabel("Предмет 1");
	JTextField ball_1Txt = new JTextField(1); 

	JLabel ball_2Lab = new JLabel("Предмет 2");
	JTextField ball_2Txt = new JTextField(1); 

	JLabel ball_3Lab = new JLabel("Предмет 3");
	JTextField ball_3Txt = new JTextField(1); 

	JLabel ball_4Lab = new JLabel("Предмет 4");
	JTextField ball_4Txt = new JTextField(1); 

	JLabel ball_5Lab = new JLabel("Предмет 5");
	JTextField ball_5Txt = new JTextField(1); 

	// Две кнопки, Add, чтобы добавить введённого человека в оперативную память,
	// Save - чтобы сохранить данные из оперативной памяти в файл
    JButton addButton  = new JButton("Добавить");
    JButton saveButton = new JButton("Сохранить");


	public newStudent() throws IOException
	{
		// Она говорит надо писать это первой строкой, если наш класс 
		// наследуется от друого
		super(); 
		// Расположение BoxLayout по оси Y
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// Объявление панелей
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		// Расположение элементов на этих панелях
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
		// На первую панель добавляются все метки и поля вводы
		p1.add(namLab);
		p1.add(namTxt);
		p1.add(famLab);
		p1.add(famTxt);
		p1.add(groupLab);
		p1.add(groupTxt);
		p1.add(ball_1Lab);
		p1.add(ball_1Txt);
		p1.add(ball_2Lab);
		p1.add(ball_2Txt);
		p1.add(ball_3Lab);
		p1.add(ball_3Txt);
		p1.add(ball_4Lab);
		p1.add(ball_4Txt);
		p1.add(ball_5Lab);
		p1.add(ball_5Txt);
		// На вторую панель - кнопки
		p2.add(addButton);
		p2.add(saveButton);
		// Панели добавляются на форму (на окно)
		this.add(p1);
		this.add(p2);

		// Задание границ от левого верхнего угла точки (200, 200) до точки (500, 500)
		setBounds(400, 400, 1000, 1000);
		addButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				// По кнопке
				if (check_abitur()) { //Проверка
					// Если эти данные соответствуют шаблонам в функции check_abitur(),
					// то добавляются в оперативку.
					// Сначала "создается" человек ab с данными	
					
					
					count = Integer.toString(buffer);

					Student ab = new Student(count, namTxt.getText(), famTxt.getText(), groupTxt.getText(),ball_1Txt.getText(), 
					ball_2Txt.getText(),ball_3Txt.getText(), ball_4Txt.getText(),
					ball_5Txt.getText());

					buffer++; //Обновляем номер человека на следующего

					
					stud.add(ab); // Затем его добавляют в массив
					System.out.println(ab); // Проверяем массив после записи
					clearForm(); // После добавления этого человека форма очищает,то что мы ввели
				} else {
					JOptionPane.showMessageDialog(p1, "Данные введены неверно");		 
				}
			}});

		// Запись в файл
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				try {
				// Вызывается функция класса inObj для записи и она записывает
				inObj obj = new inObj(stud);
				}
				catch(IOException e)
				{
					System.out.println("no men" + e.getMessage());
				}
			}});
	}

	// Функция очищения полей ввода
	public void clearForm() {
		famTxt.setText("");
		namTxt.setText("");
		groupTxt.setText("");
		ball_1Txt.setText("");
		ball_2Txt.setText("");
		ball_3Txt.setText("");
		ball_4Txt.setText("");
		ball_5Txt.setText("");
	}

	// Функция проверки полей ввода
	public boolean check_abitur() {
		Pattern pattern  = Pattern.compile("^[A-ZА-Я][a-zа-я]+$");

		Matcher matcher1 = pattern.matcher(famTxt.getText());
		Matcher matcher2 = pattern.matcher(namTxt.getText());

		if (!matcher1.find() || !matcher2.find()) {
			return false;
		}

		pattern = Pattern.compile("^.+$");

		Matcher matcher3 = pattern.matcher(groupTxt.getText());

		if(!matcher3.find()){
			return false;
		}

		pattern = Pattern.compile("^\\d+$");

		Matcher matcher4 = pattern.matcher(ball_1Txt.getText());
		Matcher matcher5 = pattern.matcher(ball_2Txt.getText());
		Matcher matcher6 = pattern.matcher(ball_3Txt.getText());
		Matcher matcher7 = pattern.matcher(ball_4Txt.getText());
		Matcher matcher8 = pattern.matcher(ball_5Txt.getText());

		if (!matcher4.find() || !matcher5.find() || !matcher6.find() || !matcher7.find() || !matcher8.find()){ 
			return false;
		}
		
	return true;
	}

}
