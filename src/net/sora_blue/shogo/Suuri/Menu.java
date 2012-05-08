package net.sora_blue.shogo.Suuri;

import net.sora_blue.shogo.Suuri.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends Activity 
	implements View.OnClickListener{

	private Activater baisu1, baisu2, div1, gcdtest, ratio;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        for(int id:new int[]{
        		R.id.btnBaisu1,R.id.btnBaisu2,R.id.btnDiv,
        		R.id.btnGCD, R.id.btnRatio}) {
        	Button button = (Button)findViewById(id);
            button.setOnClickListener(this);
        }
        baisu1 = new Activater(this, Baisu1.class);
        baisu2 = new Activater(this, Baisu2.class);
        div1 = new Activater(this, Div1.class);
        gcdtest = new Activater(this, GcdTest.class);
        ratio = new Activater(this, Ratio.class);
    }

    private class Activater implements DialogInterface.OnClickListener {
    	Intent intent;
    	public Activater(Activity parent, Class<?> c) {
    		intent = new Intent(parent, c);
    	}
    	
    	public void onClick(DialogInterface dialog, int whichButton) {
			startActivity(intent);
		}
    }
    
	public void onClick(View v) {
		int title = -1;
		int msg = -1;
		Activater act = null;
		
		//押されたボタンによって起動する画面を変更
		switch(v.getId()) {
		case R.id.btnBaisu1:
			title = R.string.menu_baisu1;
			msg = R.string.starting_baisu1;
			act = baisu1;
			break;
		case R.id.btnBaisu2:
			title = R.string.menu_baisu2;
			msg = R.string.starting_baisu2;
			act = baisu2;
			break;
		case R.id.btnDiv:
			title = R.string.menu_div;
			msg = R.string.starting_div;
			act = div1;
			break;
		case R.id.btnGCD:
			title = R.string.menu_gcd;
			msg = R.string.starting_gcd;
			act = gcdtest;
			break;
		case R.id.btnRatio:
			title = R.string.menu_ratio;
			msg = R.string.starting_ratio;
			act = ratio;
			break;
		}
		if(act!=null) {
			try {
				//確認ダイアログを表示
				AlertDialog.Builder dlg = new AlertDialog.Builder(this);
				if(title>=0) dlg.setTitle(title);
				if(msg>=0) dlg.setMessage(msg);
				dlg.setPositiveButton("OK", act );
				dlg.setNegativeButton("Cancel", null);
				dlg.show();
			} catch (Exception e) {
				Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
			}
		}
	}
}