package dev.matt2393.carreterasnex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class LauncherActivity extends Activity {

    private ImageView ivLogo;
    private TextView tvNex;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher);


        init();

        animacion();

        mHandler = new Handler();
    }

    private void init() {
        ivLogo = findViewById(R.id.iv_logo);
        tvNex = findViewById(R.id.tv_nex);
    }


    private void animacion() {

        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scalecabeza);
        ivLogo.startAnimation(animation2);

        Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scalecuerpo);
        tvNex.startAnimation(animation3);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

        /*Intent intent = new Intent(this, LauncherActivity.class);
        new Handler(Looper.getMainLooper()).postDelayed(() -> startActivity(intent), 3000);

         */
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Comprobar si el Servicio ya se está ejecutando
      /*  if (LinphoneService.isReady()) {
            onServiceReady();
        } else {
            //Si no es así, comencemos
            Intent i = new Intent(getBaseContext(), LinphoneService.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                startForegroundService(i);
            } else {
                startService(i);
            }
            startService(new Intent().setClass(this, LinphoneService.class));
            //Y esperar a que esté listo, para poder usarlo después con seguridad.
            new ServiceWaitThread().start();
        }

       */
    }

}
