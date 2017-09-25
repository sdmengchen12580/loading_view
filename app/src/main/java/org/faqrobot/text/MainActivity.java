package org.faqrobot.text;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BounceBallView bbv1;
    private EditText bounceCount;
    private EditText ballCount;
    private EditText ballDelay;
    private EditText duration;
    private EditText radius;
    private CheckBox physicMode;
    private CheckBox randomPath;
    private CheckBox randomColor;
    private CheckBox randomRadius;
    private Button b1;
    private Button b2;
    private BallDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**1.初始化控件*/
        initView();
        /**2.初始化点击事件*/
        clickView();
        /**3.初始化自定义的弹球控件*/
        bbv1.post(new Runnable() {
            @Override
            public void run() {
                initText();
            }
        });
        bbv1.start();


    }
    /**1.初始化控件*/
    private void initView() {
        dialog = new BallDialog();
        bbv1 = (BounceBallView) findViewById(R.id.bbv1);
        ballCount = (EditText) findViewById(R.id.ball_count);
        ballDelay = (EditText) findViewById(R.id.ball_delay);
        bounceCount = (EditText) findViewById(R.id.bounce_count);
        radius = (EditText) findViewById(R.id.radius);
        duration = (EditText) findViewById(R.id.duration);
        physicMode = (CheckBox) findViewById(R.id.physic_mode);
        randomColor = (CheckBox) findViewById(R.id.random_color);
        randomPath = (CheckBox) findViewById(R.id.random_path);
        randomRadius = (CheckBox) findViewById(R.id.random_radius);
        b2 = (Button) findViewById(R.id.b2);
        b1 = (Button) findViewById(R.id.b1);
    }

    /**2.初始化点击事件*/
    private void clickView() {
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apply(bbv1);
                initText();
                bbv1.start();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show(getFragmentManager(),"1");
            }
        });
    }

    /**初始化自定义控件属性*/
    private void initText(){
        ballCount.setText(bbv1.getBallCount()+"");
        ballDelay.setText(bbv1.getBallDelay()+"");
        bounceCount.setText(bbv1.getBounceCount()+"");
        duration.setText(bbv1.getDefaultDuration()+"");
        radius.setText(bbv1.getRadius()+"");
        physicMode.setChecked(bbv1.isPhysicsMode());
        randomRadius.setChecked(bbv1.isRandomRadius());
        randomPath.setChecked(bbv1.isRandomBallPath());
        randomColor.setChecked(bbv1.isRandomColor());
    }


    public void apply(BounceBallView bbv){
        if(bbv == null){
            Toast.makeText(this,"BounceBallView is null",Toast.LENGTH_LONG).show();
            return;
        }
        try{
            bbv.config()
                    .ballCount(Integer.parseInt(ballCount.getText().toString()))
                    .bounceCount(Integer.parseInt(bounceCount.getText().toString()))
                    .ballDelay(Integer.parseInt(ballDelay.getText().toString()))
                    .duration(Integer.parseInt(duration.getText().toString()))
                    .radius(Float.parseFloat(radius.getText().toString()))
                    .isPhysicMode(physicMode.isChecked())
                    .isRamdomPath(randomPath.isChecked())
                    .isRandomColor(randomColor.isChecked())
                    .isRandomRadius(randomRadius.isChecked())
                    .apply();
        }catch (Exception e){
            Toast.makeText(this,"错误",Toast.LENGTH_LONG).show();
        }
    }

}
