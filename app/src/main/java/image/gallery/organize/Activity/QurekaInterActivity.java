package image.gallery.organize.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import image.gallery.organize.Adhelper.BackInterAds;
import image.gallery.organize.Adhelper.InterAds;
import image.gallery.organize.Helper.Constant;
import image.gallery.organize.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

public class QurekaInterActivity extends AppCompatActivity {
    public boolean isBackAds = false;
    boolean isClicked = false;

    @BindView(R.id.rlMain)
    RelativeLayout rlMain;

    @BindView(R.id.gif_inter_round)
    GifImageView gifInterRound;

    @BindView(R.id.qurekaAds)
    ImageView qurekaAds;

    @BindView(R.id.qurekaAds1)
    ImageView qurekaAds1;

    @BindView(R.id.qurekaAdsClose)
    ImageView qurekaAdsClose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qureka_inter);

        ButterKnife.bind(this);

        if (getIntent() != null && getIntent().hasExtra(Constant.BACK_ADS)) {
            isBackAds = getIntent().getBooleanExtra(Constant.BACK_ADS, true);
        }

        Constant.setQureka(this, Constant.QINTER_COUNT, qurekaAds, qurekaAds1, gifInterRound);
        rlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBackAds) {
                    if (BackInterAds.mOnAdClosedListener != null)
                        BackInterAds.mOnAdClosedListener.onAdClosed();
                } else {
                    if (InterAds.mOnAdClosedListener != null)
                        InterAds.mOnAdClosedListener.onAdClosed();
                }
                Constant.gotoAds(QurekaInterActivity.this);
                finish();
            }
        });

        qurekaAdsClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (isBackAds) {
            if (BackInterAds.mOnAdClosedListener != null)
                BackInterAds.mOnAdClosedListener.onAdClosed();
        } else {
            if (InterAds.mOnAdClosedListener != null)
                InterAds.mOnAdClosedListener.onAdClosed();
        }
        finish();
    }
}