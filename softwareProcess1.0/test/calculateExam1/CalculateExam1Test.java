package calculateExam1;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import org.junit.Test;

public class CalculateExam1Test {
	ActionEvent a = null;
	CalculateExam1 t = new CalculateExam1();
	@Test
	public void testGene() {
		t.b=1;
		t.generateQuestion();
		assertTrue(t.c != null);
	}
	@Test
	public void testJudge1() {
		t.Answer=1;
		t.judge(1);
		assertTrue(t.file != null);
	}
}
