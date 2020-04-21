package hitesh.asimplegame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.RequiresApi;

import java.util.HashMap;

/*처음에 볼륨키를 넣었는데 굳이 필요 없을 것 같아서 그냥 지우고 ON/OFF만 넣었습니다*/
/*MediaPlayer는 배경음악 처리 soundPool은 효과음 처리입니다*/
//일단 볼륨을 int로 보내고 2차때 객체를 넘기는 refactoring

public class SettingSound extends Activity {
    private static MediaPlayer mp;
    Switch sound;
    Switch effect;
    private SharedPreferences BackSF=null;
    SharedPreferences.Editor BackEditor=null;
    private SharedPreferences EffectSF=null;
    SharedPreferences.Editor EffectEditor=null;
    Intent intent;
    Bundle b;

    //=================배경음악==============//
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        intent = new Intent(getApplicationContext(),QuestionActivity.class);
        sound = findViewById(R.id.swt_sound);
        effect = findViewById(R.id.swt_effect);
        //===============상태저장=================//
        BackSF = getSharedPreferences("isMute",MODE_PRIVATE);
        EffectSF = getSharedPreferences("isMute",MODE_PRIVATE);
        BackEditor = BackSF.edit();
        EffectEditor = EffectSF.edit();
        //=====================넘길 상태 값===========================//
        intent = new Intent(SettingSound.this, VoiceQuestionActivity.class);
        sound.setChecked(BackSF.getBoolean("isMute",true));
        effect.setChecked(EffectSF.getBoolean("isMute",true));
        //==================배경음악==============//
        if(mp==null){
            mp = MediaPlayer.create(this,R.raw.background);
            mp.setLooping(true);
        }

        sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { //배경음악
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
               if(isChecked){
                    mp.pause();
                } else{
                    mp.start();
                }
                BackEditor.putBoolean("isBackMute",isChecked);
                BackEditor.commit();
            }
        });
        //=================================================//
        //====================효과음 버튼===================//
        effect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // 효과음 버튼 Listenr마다 넣어주기
            @Override //볼륨조절로 온오프하기
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    //soundPool.setVolume(soundPlay,0,0); //min
                   // b.putInt("volume", 0);
                } else{
                    //soundPool.setVolume(soundPlay,1,1);//max
                    //b.putInt("volume", 1); //volume 보내기
                }
                //intent.putExtras(b);
                EffectEditor.putBoolean("isEffectMute",isChecked);
                EffectEditor.commit(); //상태 유지 값 빼두 된다
            }
        });
    }

}