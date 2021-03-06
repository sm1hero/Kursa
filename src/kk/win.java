package kk;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;


public class win extends JFrame {
	    private static final long serialVersionUID = 1L; // Это для записи в файл
		
	    private JMenu createMenuItems() throws IOException
	    {
			// Объявление переменной-меню
	        JMenu menu = new JMenu("Главное меню");
			// Объявление переменных-пунктов меню
	        JMenuItem item1 = new JMenuItem("Добавить запись");
			JMenuItem item2 = new JMenuItem("Список студентов");
	        JMenuItem item3 = new JMenuItem("Вывод студентов с оценкой 2");
			JMenuItem item4 = new JMenuItem("Удаление");
	        
			

			// Добавление пунктов на меню
	        menu.add(item1); 
	        menu.add(item2);
	        menu.add(item3);
			menu.add(item4);


            // Первый пункт, который ссылается на функцию добавления нового человека
	        item1.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    try{
						// При нажатии на первый пункт вызывается "слой" с функцией
						// добавления человека, описание которой в файле newStudent
                    	setContentPane(new newStudent());
                    	validate();
                    }
                    catch (IOException e) {System.out.println(e.getMessage());}
                    }
                }
            );
			// Второй пункт - список студентов	
			item2.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent arg0)  {
                    System.out.println ("ActionListener.actionPerformed : open");
                    try{
                    	setContentPane(new ListStudent());
                    	validate();
                    }
                    catch (IOException e) {System.out.println(e.getMessage());}
                    }
            	});
			
			// Третий пункт - вывод студентов с оценкой 2
	        item3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {       
					try {
					setContentPane(new searchBall());
					validate();
					}
					catch (IOException e) {}
				}
			});

			// Четвёртый - Удаление
	        item4.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent arg0)  {
                    try{
                    	setContentPane(new delStudent());
                    	validate();
                    }
                    catch (IOException e) {System.out.println(e.getMessage());}
                    }
                });
	        
			return menu;
	    }
	    
	    public win() {
	        super("Главное меню");
			// Стиль расположения элементов на окне BoxLayout по оси X
	        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
			// При нажатии на крестик окно закрыватся, программа прекращает работу
	        setDefaultCloseOperation( EXIT_ON_CLOSE );
			// Для того, чтобы наши пункты отображались списком
	        JMenuBar menuBar = new JMenuBar();

	        try {
	        menuBar.add(createMenuItems()); 
	        } catch(IOException e){
	        	System.out.println("ok");
	        }

	        setJMenuBar(menuBar);
	        
			// Расположение окна от точки (200, 200) с левого верхнего угла
			// до точки (600, 500) 
	        setBounds(300, 300, 700, 600);
	        setVisible(true); // Включить показ окна
	    }

}
