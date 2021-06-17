package kk;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class searchWin extends JDialog{
	private static final long serialVersionUID = 1L;
	JTextField srBallTxt=new JTextField(20); 
	JButton searchButton = new JButton("Поиск");

	searchWin(JFrame Parent){
	super(Parent,"Введите средний балл для поиска",true); 
	 this.setLayout(new FlowLayout());
	   
	   this.add(srBallTxt);
	   this.add(searchButton);
	   this.setSize(400, 200);
	   setLocationRelativeTo(Parent);

	   searchButton.addActionListener(new ActionListener()
	     {
		   @Override
	         public void actionPerformed(ActionEvent arg0) {
	        	
	        		  searchWin.this.dispose();//ссылается на его экземпляр внешнего класса
	         }});
}
	public String getSearch()
	{
		return srBallTxt.getText();
	}
}
