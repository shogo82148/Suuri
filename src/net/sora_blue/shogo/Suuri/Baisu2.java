package net.sora_blue.shogo.Suuri;

import android.os.Bundle;

public class Baisu2 extends Quiz {
	private int lists[][] = {
			{17, 18, 19, 21, 23, 24, 26, 27, 28, 29, 31, 32},
			{21, 28, 31, 19, 27, 32, 29, 23, 18, 26, 17, 24},
			{32, 31, 27, 29, 24, 23, 28, 21, 26, 19, 17, 18},
			{31, 23, 28, 29, 18, 26, 27, 32, 19, 21, 17, 27}
		};
	private int[] list = randomList(lists);
	private int base = 0, num = 2;
	
	//�𓚂��`�F�b�N����
	protected String checkAns0(String ans) {
		//�����𐮐��ɒ���
		int val = -1;
		try {
			val = Integer.valueOf(ans).intValue();
		} catch(Exception e) {
		}
		
		//�`�F�b�N
		if(val!=list[base]*num) return getString(R.string.result_ng);
		return null;
	}
	
	//�V����������������
	protected boolean nextQuiz0() {
		if(list[base]*num>100) {
			base++;
			num = 2;
			if(base>=list.length) {
				return false;
			}
		} else num++;
	
		return true;
	}
	
	protected String getQuiz() {
		/*�ǂ��炩��12�ȏ�ɂ���*/
		if(list[base]<12 && num<12) num = 12;
		
		return String.format(getString(R.string.quiz_baisu1),
				list[base], num);
	}
	
	//��ԕۑ�
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	  	outState.putInt("base", base);			//���[�U�[�̓���
		outState.putInt("num", num);
		outState.putIntArray("list", list);
	}
	
	//��Ԃ�߂�
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		base = savedInstanceState.getInt("base",0);
		num = savedInstanceState.getInt("num");
		list = savedInstanceState.getIntArray("list");
	}
}
