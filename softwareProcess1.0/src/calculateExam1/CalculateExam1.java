package calculateExam1;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class CalculateExam1 extends JFrame implements ActionListener {
	
    private final String[] type = 
    	{ "-请选择类型-","加","减"}; 
    private final String[] controller = 
    	{ "开始","下一题","提交"}; 
    JTextField question = new JTextField("待出题");
    JTextField answer = new JTextField("答案:");
    JTextField answerText = new JTextField("");
    JButton update[] = new JButton[controller.length];
    JComboBox calculateType=new JComboBox();
    JTextArea file = new JTextArea("  提示栏");
    int Answer,b,m,n;
	String c,M,N;
    
	public CalculateExam1(){
		JFrame jf = new JFrame("小学生100以内加减法练习器");
		jf.setSize(680, 200);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        
        Font font = new Font("\5B8B\4F53",Font.BOLD,20);
        Font font1 = new Font("\5B8B\4F53",Font.BOLD,40);
        Font font2 = new Font("\5B8B\4F53",Font.PLAIN,30);
        
        file.setFont(font2);
		
		JPanel initBoard = new JPanel(new BorderLayout());
		JPanel moduleType = new JPanel(new GridLayout());
		JPanel questionArea = new JPanel(new GridLayout());
		JPanel submitArea = new JPanel(new GridLayout(1,4)) ;
		
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
		initBoard.add(file,BorderLayout.SOUTH);
		
		question.setFont(font1);
		question.setEnabled(false);
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

        if (source==update[0]) 
        	generateQuestion();      
        if (source==update[1])
        	generateQuestion();
        if (source==update[2]){
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
			file.setText("恭喜你，答对了！ 正确答案是：" + c + Answer);				
		}
		else
			file.setText("出错了哟。 正确答案是：" + c + Answer);
	}
	public void generateQuestion() {
		if(calculateType.getSelectedItem()=="加")
				b=1;
		if(calculateType.getSelectedItem()=="减")
				b=2;		
		n=(int)(Math.random()*(Math.pow(10, 2)-1))+1;
    	m=(int)(Math.random()*(Math.pow(10, 2)-1))+1;
    	N=String.valueOf(n);
    	M=String.valueOf(m);
		switch (b) {
			case 1:
				while((n+m)>100) {
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
		}		
			
		question.setText(c);
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new CalculateExam1();
	}
}
