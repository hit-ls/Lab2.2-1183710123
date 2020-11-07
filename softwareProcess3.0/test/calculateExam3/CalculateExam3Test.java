package calculateExam3;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import org.junit.Test;

import calculateExam3.CalculateExam3;

public class CalculateExam3Test {

	ActionEvent a = null;
	CalculateExam3 t = new CalculateExam3();
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
	@Test
	public void testJudge3() {
		t.b=2;
		t.Answer=1;
		t.Answer2=2;
		t.judge(1,2);
		assertTrue(t.file != null);
	}
}
