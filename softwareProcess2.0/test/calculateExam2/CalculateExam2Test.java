package calculateExam2;

import static org.junit.Assert.assertTrue;

import java.awt.event.ActionEvent;

import org.junit.Test;

import calculateExam2.CalculateExam2;

public class CalculateExam2Test {
	ActionEvent a = null;
	CalculateExam2 t = new CalculateExam2();
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
	@Test
	public void testJudge2() {
		t.b=2;
		t.Answer=1;
		t.judge(1);
		assertTrue(t.file != null);
	}
}
