package net.sora_blue.shogo.Suuri;

import android.os.Bundle;

public class Ratio extends Quiz {
	/*掛けられる数のパターン*/
	protected int lists[][] = {
			{ 6, 13,  9, 17, 11,  8, 14, 12,  7},
			{ 6,  9, 19,  8, 14,  7, 12, 13, 11},
			{ 7, 12, 14, 13,  6, 17,  8, 11,  9},
			{13,  8, 12, 11,  7,  6, 19,  9, 14}
		};
	int[] list = randomList(lists);
	int pair1[] = getPairList(3);
	int pair2[] = getPairList(2);
	int num = (int)(myRand()*8L/Integer.MAX_VALUE) + 2;
	int a = pair1[0] * num;
	int b = pair2[0] * num;
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
		int ans_a = -1, ans_b = -1;
		try {
			String[] l = ans.split(":");
			if(l.length!=2) return getString(R.string.result_ratio1);
			ans_a = Integer.valueOf(l[0]).intValue();
			ans_b = Integer.valueOf(l[1]).intValue();
		} catch(Exception e) {
		}
		
		//チェック
		int g = gcd(a, b);
		if(a%ans_a!=0 || b%ans_b!=0) return getString(R.string.result_ratio2);
		if(a/ans_a != b/ans_b) return getString(R.string.result_ratio3);
		if(ans_a!=a/g || ans_b!=b/g) return getString(R.string.result_ratio4);
		return null;
	}
	
	//新しい問題を準備する
	protected boolean nextQuiz0() {
		num_question++;
		if(num_question<11) {
			num = (int)(myRand()*8L/Integer.MAX_VALUE) + 2;
			a = pair1[num_question] * num;
			b = pair2[num_question] * num;
		} else if(num_question<20){
			num = list[num_question-11];
			a = pair1[num_question-11] * num;
			b = pair2[num_question-11] * num;
		} else return false;
		return true;
	}
	
	protected String getQuiz() {
		return String.format(getString(R.string.quiz_ratio),
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
