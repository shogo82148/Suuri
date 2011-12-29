package net.sora_blue.shogo.Suuri;

import android.os.Bundle;

public class Div1 extends Quiz {
	private int lists[][] = {
			{3, 4, 5, 6, 7, 8, 9, 4, 9, 7},
			{7, 8, 9, 7, 8, 4, 6, 3, 5, 4},
			{8, 5, 6, 4, 4, 3, 7, 9, 7, 9},
			{5, 9, 3, 7, 7, 8, 4, 4, 9, 6}
		};
	private int[] list = randomList(lists);
	protected int[] div = getDivList();
	protected int nquiz = 0;
	
	protected int[] getDivList() {
		int[] div = new int[15];
		int i;
		for(i=0;i<15;i++) {
			int num;
			do {
				num = (int)(myRand()*800L/Integer.MAX_VALUE)+200;
			} while((num%list[i%10])!=0);
			div[i] = num;
		}
		return div;
	}
	
	//�𓚂��`�F�b�N����
	protected String checkAns0(String ans) {
		//�����𐮐��ɒ���
		int val = -1;
		try {
			val = Integer.valueOf(ans).intValue();
		} catch(Exception e) {
		}
		
		//�`�F�b�N
		if(val!=div[nquiz]/list[nquiz%10]) return getString(R.string.result_ng);
		return null;
	}
	
	//�V����������������
	protected boolean nextQuiz0() {
		nquiz++;
		if(nquiz>=15) return false;
		return true;
	}
	
	protected String getQuiz() {
		return String.format(getString(R.string.quiz_div),
				nquiz+1, div[nquiz], list[nquiz%10]);
	}
	
	//��ԕۑ�
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putIntArray("list", list);
		outState.putIntArray("div", div);
	}
	
	//��Ԃ�߂�
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		list = savedInstanceState.getIntArray("list");
		div = savedInstanceState.getIntArray("div");
	}
}
