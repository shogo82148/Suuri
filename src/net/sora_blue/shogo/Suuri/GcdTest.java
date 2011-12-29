package net.sora_blue.shogo.Suuri;

import android.os.Bundle;

public class GcdTest extends Quiz {
	/*掛けられる数のパターン*/
	int lists[][] = {
		{ 6,  7,  9, 11, 13,  6,  7, 12, 11, 13},
		{ 7, 13,  6, 12,  7, 11, 13, 11,  6,  9},
		{13,  6,  7,  9, 12, 13, 11,  9,  7, 11},
		{12, 13,  9, 11,  6, 13,  7,  6, 11,  7},
	};
	int[] list = randomList(lists);
	int pair1[] = getPairList(3);
	int pair2[] = getPairList(2);
	int num = (int)(myRand()*8L/Integer.MAX_VALUE) + 2;
	int a = list[0] * num;
	int b = list[0] * num;
	int num_question = 0;	//問題番号
	
	protected int[] getPairList(int offset) {
		int[] pair = new int[15];
		for(int i = 0; i<pair.length; i++) {
			pair[i] = ((myRand()%10)/2)*2+offset;
		}
		return pair;
	}
	
	//解答をチェックする
	protected String checkAns0(String ans) {
		//答えを整数に直す
		int val = -1;
		try {
			val = Integer.valueOf(ans).intValue();
		} catch(Exception e) {
		}
		
		//チェック
		if(val!=gcd(a, b)) return getString(R.string.result_ng);
		return null;
	}
	
	//新しい問題を準備する
	protected boolean nextQuiz0() {
		num_question++;
		if(num_question<15) {
			num = (int)(myRand()*8L/Integer.MAX_VALUE) + 2;
			a = pair1[num_question] * num;
			b = pair2[num_question] * num;
		} else if(num_question<25){
			num = list[num_question-15];
			a = pair1[num_question-15] * num;
			b = pair2[num_question-15] * num;
		} else return false;
		return true;
	}
	
	protected String getQuiz() {
		return String.format(getString(R.string.quiz_gcd),
				num_question+1, a, b);
	}
	
	//状態保存
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("num", num);
		outState.putInt("numquestion", num_question);
		outState.putIntArray("list", list);
		outState.putIntArray("pair1", pair1);
		outState.putIntArray("pair2", pair2);
	}
	
	//状態を戻す
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		num = savedInstanceState.getInt("num");
		num_question = savedInstanceState.getInt("numquestion");
		list = savedInstanceState.getIntArray("list");
		pair1 = savedInstanceState.getIntArray("pair1");
		pair2 = savedInstanceState.getIntArray("pair2");
	}
}
