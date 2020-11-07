package calculateExam2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class CalculateExam2 extends JFrame implements ActionListener {
	
    private final String[] type = 
    	{ "-请选择类型-","加","减","乘","除","混合"}; 
    private final String[] controller = 
    	{ "开始","下一题","提交"}; 
    JTextField question = new JTextField("待出题");
    JTextField answer = new JTextField("答案:");
    JTextField answerText = new JTextField("");
    JButton update[] = new JButton[controller.length];
    JComboBox calculateType=new JComboBox();
    JTextArea file = new JTextArea("");
    JTextField numberOfDigit1 = new JTextField("位数:");
    JTextField numberOfDigit2 = new JTextField("");
    int score=0;
    int Answer,Answer2,b,n,m;
	String c,N,M;
    
	public CalculateExam2(){
		JFrame jf = new JFrame("小学生四则运算练习器");
		jf.setSize(680, 300);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        
        Font font = new Font("\5B8B\4F53",Font.BOLD,20);
        Font font1 = new Font("\5B8B\4F53",Font.BOLD,40);
        Font font2 = new Font("\5B8B\4F53",Font.PLAIN,25);
        
        file.setFont(font2);
        file.setText("  提示栏");
		
		JPanel initBoard = new JPanel(new BorderLayout());
		JPanel moduleType = new JPanel(new GridLayout());
		JPanel questionArea = new JPanel(new GridLayout());
		JPanel submitArea = new JPanel(new GridLayout(1,4)) ;
		JPanel bitArea = new JPanel(new GridLayout(1,2));
		JPanel mixArea = new JPanel(new GridLayout(2,1));
		
		bitArea.add(numberOfDigit1);
		bitArea.add(numberOfDigit2);
		mixArea.add(bitArea);
		mixArea.add(file);
		questionArea.add(question);
		questionArea.add(answer);
		questionArea.add(answerText);
		moduleType.add(calculateType);
		for (int i = 0; i < type.length; i++) {
            calculateType.addItem(type[i]);
        }
		for (int i = 0; i < controller.length; i++) {

			update[i] = new JButton(controller[i]);
			update[i].setFont(font);
			submitArea.add(update[i]);
		}
		initBoard.add(moduleType,BorderLayout.EAST);
		initBoard.add(questionArea, BorderLayout.NORTH);
		initBoard.add(submitArea, BorderLayout.CENTER);
		initBoard.add(mixArea,BorderLayout.SOUTH);
		
		question.setFont(font1);
		question.setEnabled(false);
		numberOfDigit1.setFont(font);
		numberOfDigit1.setEnabled(false);
		answer.setFont(font2);
		answer.setEnabled(false);		
		answerText.setFont(font2);
		
		for (int i = 0; i < controller.length; i++) {
        	update[i].addActionListener(this);
		}
		jf.setContentPane(initBoard);
		jf.setVisible(true);
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		Object source = e.getSource();

        if (source==update[0]) {
        	generateQuestion();
            score=0;
        }
      
        if (source==update[1])
        	generateQuestion();
        if (source==update[2])
        {
			String q=answerText.getText();
			int a=Integer.parseInt(q);
			judge(a);
        }
    	generateQuestion();
    	question.setText(c);
	    answerText.setText("");
	}
	
	public void judge(int a) {
		if(a==Answer) {
			score=score+10;
			file.setText("恭喜你，答对了！ 正确答案是：" + c + Answer + "目前得分:"+score);				
		}
		else
			file.setText("出错了哟。 正确答案是：" + c + Answer + "目前得分:"+score);
	}
	public void generateQuestion() {
		String s=numberOfDigit2.getText();
    	int Number=Integer.parseInt(s);
		if(calculateType.getSelectedItem()=="加")
				b=1;
		if(calculateType.getSelectedItem()=="减")
				b=2;
		if(calculateType.getSelectedItem()=="乘")
				b=3;
		if(calculateType.getSelectedItem()=="除")
				b=4;		
		if(calculateType.getSelectedItem()=="混合")
				b=(int)(Math.random()*4)+1;
		
			n=(int)(Math.random()*(Math.pow(10, Number)-1))+1;
    		m=(int)(Math.random()*(Math.pow(10, Number)-1))+1;
    		N=String.valueOf(n);
    		M=String.valueOf(m);
			switch (b) {
			case 1:
				while((n+m)>Math.random()*(Math.pow(10, Number))) {
					n=(int)(Math.random()*(Math.pow(10, Number)-1))+1;
		    		m=(int)(Math.random()*(Math.pow(10, Number)-1))+1;
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
					do {
					c=M+"÷"+N+"=";
					Answer=m/n;
					Answer2=m%n;	
					}while(Answer2!=0);
				}	
				if(n>=m)
				{		
					do {
					c=N+"÷"+M+"=";
					Answer=n/m;
					Answer2=n%m;
					}while(Answer2!=0);
				}
				break;
			}		
			
		question.setText(c);
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new CalculateExam2();
	}
}
