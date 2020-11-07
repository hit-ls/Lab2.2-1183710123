package calculateExam3;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class CalculateExam3 extends JFrame implements ActionListener {
    private final String[] type = 
    	{ "-请选择类型-","加","减","乘","除","混合"}; 
    private final String[] controller = 
    	{ "开始","下一题","错题本"};                        
    JButton giveAnswer = new JButton("提交");         
    JButton update[] = new JButton[controller.length];  
    JTextField question = new JTextField("待出题");
    JTextField answer = new JTextField("答案");
    JTextField answerText = new JTextField("");
    JTextField question2 = new JTextField("除法余数，填此"); 
    JTextField answer2 = new JTextField("");
    JTextField user1 = new JTextField("用户名：");
    JTextField user2 = new JTextField("");
    JTextArea file = new JTextArea("");
    JScrollPane File=new JScrollPane(file);
    JTextField numberOfDigit1 = new JTextField("位数：");
    JTextField numberOfDigit2 = new JTextField("");
    JComboBox calculate=new JComboBox();
    int score=0;
	int Answer,Answer2,b,m,n;
	String c,N,M;

    public CalculateExam3() {
    	
        super("小学生四则运算练习器");
		Font font = new Font("\5B8B\4F53", Font.BOLD, 18);
		// add components
		JPanel pnlHead = new JPanel(new BorderLayout());
		JPanel pnlHeadupdate = new JPanel(new GridLayout(1,5));
		JPanel pnlBodyEast = new JPanel(new GridLayout(2,1));
		JPanel questionArea=new JPanel(new GridLayout(1,5));
		questionArea.add(question);
		questionArea.add(answer);
		questionArea.add(answerText);
		questionArea.add(question2);
		questionArea.add(answer2);
		pnlHeadupdate.add(user1);
		pnlHeadupdate.add(user2);
		pnlHeadupdate.add(numberOfDigit1);
		pnlHeadupdate.add(numberOfDigit2);
		pnlHeadupdate.add(calculate);
		for (int i = 0; i < type.length; i++) 
		{
            calculate.addItem(type[i]);
        }
		for (int i = 0; i < controller.length; i++) 
		{

			update[i] = new JButton(controller[i]);
			update[i].setFont(font);
			if(i==0)
				pnlHeadupdate.add( update[i]);
			else
				pnlBodyEast.add(update[i]);
		}

        pnlHead.add( questionArea, BorderLayout.CENTER);
        pnlHead.add( giveAnswer, BorderLayout.EAST);
        pnlHead.add( pnlHeadupdate, BorderLayout.NORTH);
		question.setFont(font);
		giveAnswer.setFont(font);
		user1.setEnabled(false);
		question.setEnabled(false);
		answer.setEnabled(false);
		question2.setEnabled(false);
		numberOfDigit1.setEnabled(false);
		
        JPanel pnlBody = new JPanel(new BorderLayout());
        pnlBody.add(File,BorderLayout.CENTER);
        pnlBody.add(pnlBodyEast,BorderLayout.EAST);

        setLayout(new BorderLayout());
        add(BorderLayout.NORTH, pnlHead);
        add(BorderLayout.CENTER, pnlBody);
        giveAnswer.addActionListener(this);
        question.addActionListener(this);
        calculate.addActionListener(this);

        for (int i = 0; i < controller.length; i++) 
		{
        	update[i].addActionListener(this);
		}
		// set frame properties
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(680, 400);
        Dimension questionSize = Toolkit.getDefaultToolkit().getScreenSize();   
        Dimension frameSize = getSize();   
        setLocation((questionSize.width - frameSize.width) / 2, (questionSize.height - frameSize.height) / 2);
        setVisible(true);
    }
 

	@Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

		if (source == giveAnswer)
	    {   
			
			String q=answerText.getText();
			int a=Integer.parseInt(q);        	
        	if(answer2.getText()!="") {
        		String p=answer2.getText();
    			int b=Integer.parseInt(p); 
    			judge(a,b);
        	}
        	else
        		judge(a);
	    }
        if (source==update[0]) {
        	generateQuestion();
            score=0;
        }
      
        if (source==update[1])
        	generateQuestion();
        if (source==update[2])
        {
        	showRecord();
        }
        
    }

	public void judge(int a ) {
		if(a==Answer)
			score=score+10; 	       	
    	String str = null;
    	switch (b) {
		case 1:
			str=String.format("%-18s%-18s%-18s%-18s\r\n",n+"+"+m+"=","你的答案:"+a,"正确答案:"+Answer,"目前得分:"+score);
			break;
		case 2:
			if(n<m)
				str=String.format("%-18s%-18s%-18s%-18s\r\n",m+"-"+n+"=","你的答案:"+a,"正确答案:"+Answer,"目前得分:"+score);
			if(n>=m)
				str=String.format("%-18s%-18s%-18s%-18s\r\n",n+"-"+m+"=","你的答案:"+a,"正确答案:"+Answer,"目前得分:"+score);	
			break;
		case 3:
			str=String.format("%-18s%-18s%-18s%-18s\r\n",n+"*"+m+"=","你的答案:"+a,"正确答案:"+Answer,"目前得分:"+score);
			break;
		case 4:
			if(n<m)
			{
				str=String.format("%-18s%-18s%-18s%-18s\r\n",m+"÷"+n+"=","你的答案:"+a,"正确答案:"+Answer,"目前得分:"+score);
								
			}	
			if(n>=m)
			{
				str=String.format("%-18s%-18s%-18s%-18s\r\n",n+"÷"+m+"=","你的答案:"+a,"正确答案:"+Answer,"目前得分:"+score);
			}
			break;
		}         		
    	record(str);
    	question.setText(c);
    	answerText.setText("");
    	answer2.setText("");
	    

	}
	public void judge(int a ,int b) {
		if(a==Answer && b==Answer2)
			score=score+10; 	       	
    	String str = null;
		if(n<m)
		{
			str=String.format("%-18s%-18s%-18s%-18s\r\n",m+"÷"+n+"=","你的答案:"+a+"余"+b,"正确答案:"+Answer + " 余数：" + Answer2,"目前得分:"+score);
							
		}	
		if(n>=m)
		{
			str=String.format("%-18s%-18s%-18s%-18s\r\n",n+"÷"+m+"=","你的答案:"+a+"余"+b,"正确答案:"+Answer + " 余数：" + Answer2,"目前得分:"+score);
		}
		record(str);
	}
	public void record(String str) {    	
   	    file.setText(file.getText()+str);
 		String o=file.getText();
			File file=new File("D:\\"+user2.getText()+".txt");
			FileWriter fw = null;
			try {
				fw = new FileWriter(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
 	    try {
				fw.write(o);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	}
	public void showRecord() {
    	File file1 = new File("D:\\"+user2.getText()+".txt");
        BufferedReader reader = null;
        try {
            
            reader = new BufferedReader(new FileReader(file1));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {                    
            	file.setText(file.getText()+tempString);
            }
            reader.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
	}
    public void generateQuestion()
    {
    	String s=numberOfDigit2.getText();
    	int Number=Integer.parseInt(s);		
		if(calculate.getSelectedItem()=="加")
				b=1;
		if(calculate.getSelectedItem()=="减")
				b=2;
		if(calculate.getSelectedItem()=="乘")
				b=3;
		if(calculate.getSelectedItem()=="除")
				b=4;
		if(calculate.getSelectedItem()=="混合")
				b=(int)(Math.random()*4)+1;
		for(int i=0;i<10;i++)
		{
			n=(int)(Math.random()*(Math.pow(10, Number)-1))+1;
    		m=(int)(Math.random()*(Math.pow(10, Number)-1))+1;
    		N=String.valueOf(n);
    		M=String.valueOf(m);
			switch (b) {
			case 1:
				while((n+m)>Math.random()*(Math.pow(10, Number))) {
					n=(int)(Math.random()*(Math.pow(10, 2)-1))+1;
		    		m=(int)(Math.random()*(Math.pow(10, 2)-1))+1;
		    		N=String.valueOf(n);
		    		M=String.valueOf(m);
				}
				c=N+"+"+M+"=";
				Answer=n+m;
				break;
			case 2:
				if(n<m)
					c=M+"-"+N+"=";
				if(n>=m)
					c=N+"-"+M+"=";
				Answer=Math.abs(m-n);	
				break;
			case 3:
				Answer=n*m;
				c=N+"*"+M+"=";
				break;
			case 4:
				if(n<m)
				{
					c=M+"÷"+N+"=";
					Answer=m/n;
					Answer2=m%n;				
				}	
				if(n>=m)
				{
					c=N+"÷"+M+"=";
					Answer=n/m;
					Answer2=n%m;
				}
				break;
			}
		}
		question.setText(c);
    }
    
    
   

	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->{
			new CalculateExam3();
		});
    }
}

