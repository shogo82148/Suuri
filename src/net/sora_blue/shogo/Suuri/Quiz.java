package net.sora_blue.shogo.Suuri;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz extends Activity 
	implements View.OnClickListener {
    
	private StringBuffer	answer;			//���[�U�[�̓���
	private int passed_time;
    private Handler handler;
	private Timer timer;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
		//View��\��
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
        
        //���X�i�[�̐ݒ�
        for(int id:new int[]{
        		R.id.btn0,R.id.btn1,R.id.btn2,
        		R.id.btn3, R.id.btn4, R.id.btn5,
        		R.id.btn6, R.id.btn7, R.id.btn8,
        		R.id.btn9, R.id.btnColon, R.id.btnBack,
        		R.id.btnEnter}) {
        	Button button = (Button)findViewById(id);
            button.setOnClickListener(this);
        }
        
        //������
        if(answer==null) answer = new StringBuffer();
        //passed_time = 0;

		handler = new Handler();
    }
	
	protected void onStart() {
		super.onStart();
	}

	protected void onResume() {
		super.onResume();
        
        //��ʍX�V
		TextView txtQuiz = (TextView)findViewById(R.id.txtQuiz);
		txtQuiz.setText(getQuiz());
		
        refreshScoreMessage();
        refreshFigures();

        startTimer();
	}
	
	protected void onPause() {
		super.onPause();
		stopTimer();
	}

	private void startTimer() {
		timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                handler.post(
                		new Runnable() {
                			public void run() {
                				passed_time++;
                				refreshScoreMessage();
                			}
                		}
                );
            }
        }, 1000, 1000);
	}
	
	private void stopTimer() {
		if(timer!=null) timer.cancel();
		timer = null;
	}
	
	//�𓚂��`�F�b�N����
	protected String checkAns0(String ans) {
		return null;
	}
	
	private void checkAns() {
		if(answer.toString().equals("0")) {
			//�����A�r���ł�߂��ł����H
			try {
				//�m�F�_�C�A���O��\��
				AlertDialog.Builder dlg = new AlertDialog.Builder(this);
				dlg.setTitle(R.string.app_name);
				dlg.setMessage(R.string.quit_msg);
				dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						finish();
					}
				});
				dlg.setNegativeButton("Cancel", null);
				dlg.show();
			} catch (Exception e) {
				Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
			}
		} else {
			String result = checkAns0(answer.toString());
			if(result==null) {
				//����!
				nextQuiz();
			} else {
				//�O��
				TextView txtResult = (TextView)findViewById(R.id.txtResult);
				txtResult.setText(result);
			}
		}
		answer = new StringBuffer();
		refreshFigures();
	}
	
	//�V����������������
	protected boolean nextQuiz0() {
		return false;
	}
	
	//�N�C�Y�̕�������擾����
	protected String getQuiz() {
		return null;
	}
	
	private void nextQuiz() {
		if(!nextQuiz0()) {
			//�Q�[���I��
			showEvalutation();
		} else {
			//���̃Q�[���p��
			TextView txtQuiz = (TextView)findViewById(R.id.txtQuiz);
			txtQuiz.setText(getQuiz());
			TextView txtResult = (TextView)findViewById(R.id.txtResult);
			txtResult.setText(" ");
		}
	}
	
	//����ǉ�����
	private void addFigure(String fig) {
		if(answer.length()>15) return ;
		answer.append(fig);
		refreshFigures();
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch(keyCode) {
		case KeyEvent.KEYCODE_0:
			addFigure("0");
			break;
		case KeyEvent.KEYCODE_1:
			addFigure("1");
			break;
		case KeyEvent.KEYCODE_2:
			addFigure("2");
			break;
		case KeyEvent.KEYCODE_3:
			addFigure("3");
			break;
		case KeyEvent.KEYCODE_4:
			addFigure("4");
			break;
		case KeyEvent.KEYCODE_5:
			addFigure("5");
			break;
		case KeyEvent.KEYCODE_6:
			addFigure("6");
			break;
		case KeyEvent.KEYCODE_7:
			addFigure("7");
			break;
		case KeyEvent.KEYCODE_8:
			addFigure("8");
			break;
		case KeyEvent.KEYCODE_9:
			addFigure("9");
			break;
		case KeyEvent.KEYCODE_DEL:
		case KeyEvent.KEYCODE_CLEAR:
			if(answer.length()>0) answer.setLength(answer.length()-1);
			refreshFigures();
			break;
		case KeyEvent.KEYCODE_ENTER:
			checkAns();
			break;
		case KeyEvent.KEYCODE_A:
		case KeyEvent.KEYCODE_B:
		case KeyEvent.KEYCODE_C:
		case KeyEvent.KEYCODE_D:
		case KeyEvent.KEYCODE_E:
		case KeyEvent.KEYCODE_F:
		case KeyEvent.KEYCODE_G:
		case KeyEvent.KEYCODE_H:
		case KeyEvent.KEYCODE_I:
		case KeyEvent.KEYCODE_J:
		case KeyEvent.KEYCODE_K:
		case KeyEvent.KEYCODE_L:
		case KeyEvent.KEYCODE_M:
		case KeyEvent.KEYCODE_N:
		case KeyEvent.KEYCODE_O:
		case KeyEvent.KEYCODE_P:
		case KeyEvent.KEYCODE_Q:
		case KeyEvent.KEYCODE_R:
		case KeyEvent.KEYCODE_S:
		case KeyEvent.KEYCODE_T:
		case KeyEvent.KEYCODE_U:
		case KeyEvent.KEYCODE_V:
		case KeyEvent.KEYCODE_W:
		case KeyEvent.KEYCODE_X:
		case KeyEvent.KEYCODE_Y:
		case KeyEvent.KEYCODE_Z:
		case KeyEvent.KEYCODE_APOSTROPHE:
		case KeyEvent.KEYCODE_AT:
		case KeyEvent.KEYCODE_BACKSLASH:
		case KeyEvent.KEYCODE_COMMA:
		case KeyEvent.KEYCODE_EQUALS:
		case KeyEvent.KEYCODE_MINUS:
		case KeyEvent.KEYCODE_PERIOD:
		case KeyEvent.KEYCODE_PLUS:
		case KeyEvent.KEYCODE_POUND:
		case KeyEvent.KEYCODE_POWER:
		case KeyEvent.KEYCODE_RIGHT_BRACKET:
		case KeyEvent.KEYCODE_LEFT_BRACKET:
		case KeyEvent.KEYCODE_SEMICOLON:
		case KeyEvent.KEYCODE_SLASH:
		case KeyEvent.KEYCODE_SPACE:
		case KeyEvent.KEYCODE_STAR:
		case KeyEvent.KEYCODE_TAB:
			addFigure(":");
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btn0:
			addFigure("0");
			break;
		case R.id.btn1:
			addFigure("1");
			break;
		case R.id.btn2:
			addFigure("2");
			break;
		case R.id.btn3:
			addFigure("3");
			break;
		case R.id.btn4:
			addFigure("4");
			break;
		case R.id.btn5:
			addFigure("5");
			break;
		case R.id.btn6:
			addFigure("6");
			break;
		case R.id.btn7:
			addFigure("7");
			break;
		case R.id.btn8:
			addFigure("8");
			break;
		case R.id.btn9:
			addFigure("9");
			break;
		case R.id.btnColon:
			addFigure(":");
			break;
		case R.id.btnBack:
			if(answer.length()>0) answer.setLength(answer.length()-1);
			refreshFigures();
			break;
		case R.id.btnEnter:
			checkAns();
			break;
		}
	}
	
	//�^�C���̕]�����ʂ�\��
	private void showEvalutation() {
		stopTimer();
		
		int point = getScore(passed_time);
		String ngok;
		String eval_msg = "";
		
		//���i/�s���i
		if(point<60) {
			ngok = getString(R.string.eval_ng);
		} else {
			ngok = getString(R.string.eval_ok);
		}
		
		/*�R�����g��\��*/
		if(passed_time>=5*60) {
			eval_msg = getString(R.string.eval1);
		} else if(point<0) {
			eval_msg = getString(R.string.eval2);
		} else if(50<point && point<60) {
			eval_msg = getString(R.string.eval3);
		} else if(point>120) {
			eval_msg = getString(R.string.eval4);
		}
		
		String msg = String.format(
				getString(R.string.eval_message), 
				point, passed_time/60, passed_time%60,
				ngok, eval_msg);
		
		AlertDialog.Builder dlg = new AlertDialog.Builder(this);
		dlg.setMessage(msg);
		dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	    	public void onClick(DialogInterface dialog, int whichButton) {
	    		finish();
	    	}
		});
		dlg.show();
	}
	
	private void refreshScoreMessage() {
		//���݂̌o�ߎ��Ԃ̕\��
		String score_message = String.format(
				getString(R.string.score_message),
				passed_time/60, passed_time%60, getScore(passed_time)
				);
		TextView txtScoreMessage = (TextView)findViewById(R.id.txtScoreMessage);
		txtScoreMessage.setText(score_message);
	}
	
	private void refreshFigures() {
		//���͂���������̕\��
		TextView txtFigures = (TextView)findViewById(R.id.txtFigures);
		txtFigures.setText(answer.toString());
	}

	//�^�C������X�R�A�v�Z
	private static int getScore(int passed_time) {
		return 210 - passed_time;
	}
	
	//��ԕۑ�
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	  	outState.putString("answer", answer.toString());			//���[�U�[�̓���
		outState.putInt("passed_time", passed_time);
	}

	//��Ԃ�߂�
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		answer = new StringBuffer(savedInstanceState.getString("answer"));
		passed_time = savedInstanceState.getInt("passed_time");
		refreshFigures();
	}
	
	/////////////////// ���[�e�B���e�B�[�֐� ///////////////////////
	private static Random rand;
	
	//����������
	public static void mySrand() {
		rand = new Random();
	}
	
	//��������
	public static int myRand() {
		if(rand==null) return 0;
		int num = rand.nextInt();
		if(num<0) num *= -1;
		return num;
	}
	
	//�ő���񐔂̌v�Z
	public static int gcd(int a, int b) {
		int tmp;
		while(b!=0) {
			tmp = a % b;
			a = b;
			b = tmp;
		}
		return a;
	}
	
	//�^����ꂽ���X�g�Q�̒����烊�X�g��I��
	public static int[] randomList(int[][] lists) {
		//�����̏�����
		mySrand();
		return lists[myRand()%lists.length];
	}
}
