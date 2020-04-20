package hitesh.asimplegame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.RequiresApi;

/*처음에 볼륨키를 넣었는데 굳이 필요 없을 것 같아서 그냥 지우고 ON/OFF만 넣었습니다*/
/*MediaPlayer는 배경음악 처리 soundPool은 효과음 처리입니다*/

public class SettingSound extends Activity implements View.OnClickListener{
    private static MediaPlayer mp;
    Switch sound;
    Switch effect;
    int soundPlay;
    private SoundPool soundPool;
    private SharedPreferences BackSF=null;
    SharedPreferences.Editor BackEditor=null;
    private SharedPreferences EffectSF=null;
    SharedPreferences.Editor EffectEditor=null;
    private Intent intent =null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        intent = new Intent(getApplicationContext(),QuestionActivity.class);
        sound = findViewById(R.id.swt_sound);
        effect = findViewById(R.id.swt_effect);

        BackSF = getSharedPreferences("isMute",MODE_PRIVATE);
        EffectSF = getSharedPreferences("isMute",MODE_PRIVATE);
        BackEditor = BackSF.edit();
        EffectEditor = EffectSF.edit();

        sound.setChecked(BackSF.getBoolean("isMute",true));
        effect.setChecked(EffectSF.getBoolean("isMute",true));

        if(mp==null){
            mp = MediaPlayer.create(this,R.raw.background);
            mp.setLooping(true);
        }
        AudioAttributes audioAttributes = new AudioAttributes.Builder()        //deprecated 생성자 바꿈
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        soundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(1).build(); //버전에 따라 다르게 빌드 해준다.
        soundPlay = soundPool.load(this,R.raw.button,1); //음원 바꿀 것
        soundPool.play(soundPlay,1f,1f,0,0,1f);

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

        effect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // 효과음 버튼 Listenr마다 넣어주기
            @Override //볼륨조절로 온오프하기
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    soundPool.setVolume(soundPlay,0,0); //min
                } else{
                    soundPool.setVolume(soundPlay,1,1);//max
                }
                EffectEditor.putBoolean("isEffectMute",isChecked);
                EffectEditor.commit();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}