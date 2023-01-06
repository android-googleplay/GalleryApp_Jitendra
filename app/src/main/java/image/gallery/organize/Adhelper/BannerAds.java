package image.gallery.organize.Adhelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import image.gallery.organize.Helper.Constant;
import image.gallery.organize.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.preference.PowerPreference;

public class BannerAds {

    @SuppressLint("StaticFieldLeak")
    public static Activity mActivity;

    public static AdView adView;
    public static boolean isLoaded = false;

    private AdSize getAdSize(Activity activity) {
        // Determine the screen width (less decorations) to use for the ad width.
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = outMetrics.density;

        float adWidthPixels = getFrameLayout(activity).getWidth();

        if (adWidthPixels == 0) {
            adWidthPixels = outMetrics.widthPixels;
        }

        int adWidth = (int) (adWidthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);
    }

    public void loadBannerAds(Activity activity) {
        if (PowerPreference.getDefaultFile().getBoolean(Constant.AdsOnOff, false) && PowerPreference.getDefaultFile().getBoolean(Constant.GoogleBannerOnOff, false)) {

            final String Ad = PowerPreference.getDefaultFile().getString(Constant.BANNERID, "123");
            adView = new AdView(activity);
            adView.setAdSize(getAdSize(activity));
            adView.setAdUnitId(Ad);
            isLoaded = false;

            adView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    isLoaded = true;
                }

                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    Constant.showLog(loadAdError.toString());
                    isLoaded = false;
                    adView = null;
                }
            });

            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }
    }


    public FrameLayout getFrameLayout(Activity activity) {
        return activity.findViewById(R.id.adFrameMini);
    }


    public TextView getTextLayout(Activity activity) {
        return activity.findViewById(R.id.adSpaceMini);
    }

    public void showBannerAds(Activity activity, FrameLayout adLayout, TextView adSpace) {

        if (adLayout == null || adSpace == null) {
            adLayout = getFrameLayout(activity);
            adSpace = getTextLayout(activity);
        }

        if (adLayout == null || adSpace == null)
            return;

        if (PowerPreference.getDefaultFile().getBoolean(Constant.AdsOnOff, true)) {

            if (PowerPreference.getDefaultFile().getBoolean(Constant.GoogleBannerOnOff, true) && adView != null && isLoaded) {

                adSpace.getLayoutParams().height = AdSize.SMART_BANNER.getHeightInPixels(activity);

                adLayout.removeAllViews();
                adLayout.addView(adView);

                adSpace.setVisibility(View.GONE);
                adLayout.setVisibility(View.VISIBLE);

                loadBannerAds(activity);

            } else {

                loadBannerAds(activity);

                if (PowerPreference.getDefaultFile().getBoolean(Constant.QurekaOnOff, true) && PowerPreference.getDefaultFile().getBoolean(Constant.QurekaBannerOnOff, true)) {
                    LinearLayout adView = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.qureka_banner, null);
                    ImageView imageViewMain = adView.findViewById(R.id.imageView);
                    setQureka(activity, imageViewMain, Constant.QBANNER_COUNT);

                    adView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Constant.gotoAds(activity);
                        }
                    });

                    adLayout.removeAllViews();
                    adLayout.addView(adView);

                    adSpace.setVisibility(View.GONE);
                    adLayout.setVisibility(View.VISIBLE);

                } else {
                    adLayout.setVisibility(View.GONE);
                    adSpace.setVisibility(View.GONE);
                }
            }
        } else {
            adLayout.setVisibility(View.GONE);
            adSpace.setVisibility(View.GONE);
        }
    }

    public void setQureka(Activity activity, ImageView imageViewMain, String isSmall) {

        if (PowerPreference.getDefaultFile().getInt(isSmall, 0) >= Constant.adsQurekaBanners.length) {
            PowerPreference.getDefaultFile().putInt(isSmall, 0);
            setQureka(activity, imageViewMain, isSmall);
        } else {

            if (imageViewMain != null && !activity.isFinishing())
                Glide.with(activity).load(Constant.adsQurekaBanners[PowerPreference.getDefaultFile().getInt(isSmall, 0)])
                        .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageViewMain);

            int top = PowerPreference.getDefaultFile().getInt(isSmall, 0) + 1;
            PowerPreference.getDefaultFile().putInt(isSmall, top);
        }
    }
}
