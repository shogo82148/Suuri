package net.sora_blue.shogo.Suuri;

import android.os.Bundle;

public class Baisu1 extends Quiz {
	private int lists[][] = {
			{ 6,  7, 12, 13, 14, 15, 16},
			{15, 12, 14,  6, 16, 13,  7},
			{ 7,  6, 13, 15, 14, 16, 12},
			{13,  7, 16,  6, 15, 12, 14}
		};
	private int[] list = randomList(lists);
	private int base = 0, num = 2;
	
	//解答をチェックする
	protected String checkAns0(String ans) {
		//答えを整数に直す
		int val = -1;
		try {
			val = Integer.valueOf(ans).intValue();
		} catch(Exception e) {
		}
		
		//チェック
		if(val!=list[base]*num) return getString(R.string.result_ng);
		return null;
	}
	
	//新しい問題を準備する
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
		/*どちらかを12以上にする*/
		if(list[base]<12 && num<12) num = 12;
		
		return String.format(getString(R.string.quiz_baisu1),
				list[base], num);
	}
	
	//状態保存
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	  	outState.putInt("base", base);			//ユーザーの答え
		outState.putInt("num", num);
		outState.putIntArray("list", list);
	}
	
	//状態を戻す
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		base = savedInstanceState.getInt("base",0);
		num = savedInstanceState.getInt("num");
		list = savedInstanceState.getIntArray("list");
	}
}
