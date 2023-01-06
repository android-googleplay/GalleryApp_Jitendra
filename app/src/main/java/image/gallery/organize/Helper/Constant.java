package image.gallery.organize.Helper;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import image.gallery.organize.Activity.LeavingAppActivity;
import image.gallery.organize.Adhelper.InterAds;
import image.gallery.organize.MyApplication;
import image.gallery.organize.R;
import com.preference.PowerPreference;

import java.io.File;

public class Constant {

    public static String recycleList = "recycleList";
    public static String DEF_VALUE = "#1aff0006";

    public static String adsLog = "adsLog";
    public static String errorLog = "errorLog";

    public static Integer[] adsQurekaBanners = new Integer[]{R.drawable.qureka_banner1, R.drawable.qureka_banner2, R.drawable.qureka_banner3, R.drawable.qureka_banner4, R.drawable.qureka_banner5, R.drawable.qureka_banner6, R.drawable.qureka_banner7};
    public static Integer[] adsQurekaInters = new Integer[]{R.drawable.qureka_inter1, R.drawable.qureka_inter2, R.drawable.qureka_inter3, R.drawable.qureka_inter4, R.drawable.qureka_inter5};
    public static Integer[] adsQurekaGifInters = new Integer[]{R.drawable.qureka_round1, R.drawable.qureka_round2, R.drawable.qureka_round3, R.drawable.qureka_round4, R.drawable.qureka_round5};


    public static String QINTER_COUNT = "QINTER_COUNT";
    public static String QMINI_COUNT = "QMINI_COUNT";
    public static String QLARGE_COUNT = "QLARGE_COUNT";
    public static String QBANNER_COUNT = "QBANNER_COUNT";
    public static String QLIST_COUNT = "QLIST_COUNT";

    public static final String QUREKA_ADS = "QurekaLink";
    public static final String POLICY = "PolicyLink";

    public static final String AdsOnOff = "AdsOnOff";
    public static final String QurekaOnOff = "QurekaOnOff";

    public static final String AppOpen = "AppOpen";

    public static final String GoogleAppOpenOnOff = "GoogleAppOpenOnOff";
    public static final String GoogleBannerOnOff = "GoogleBannerOnOff";

    public static final String GoogleMiniNativeOnOff = "GoogleMiniNativeOnOff";
    public static final String GoogleLargeNativeOnOff = "GoogleLargeNativeOnOff";
    public static final String GoogleListNativeOnOff = "GoogleListNativeOnOff";

    public static final String SERVER_INTERVAL_COUNT = "GoogleIntervalCount";
    public static final String APP_INTERVAL_COUNT = "APP_INTERVAL_COUNT";

    public static final String BACK_ADS = "GoogleBackInterOnOff";
    public static final String SERVER_BACK_COUNT = "GoogleBackInterIntervalCount";
    public static final String APP_BACK_COUNT = "APP_BACK_COUNT";
    public static final String GoogleSplashOpenAdsOnOff = "GoogleSplashOpenAdsOnOff";
    public static final String GoogleExitSplashInterOnOff = "GoogleExitSplashInterOnOff";

    public static final String GoogleNativeTextOnOff = "GoogleNativeTextOnOff";
    public static final String GoogleNativeText = "GoogleNativeText";

    public static final String GoogleWhichOneNative = "GoogleWhichOneNative";
    public static final String ListNativeWhichOne = "ListNativeWhichOne";
    public static final String ListNativeAfterCount = "ListNativeAfterCount";

    public static final String QurekaBannerOnOff = "QurekaBannerOnOff";
    public static final String QurekaAppOpenOnOff = "QurekaAppOpenOnOff";
    public static final String QurekaInterOnOff = "QurekaInterOnOff";
    public static final String QurekaBackInterOnOff = "QurekaBackInterOnOff";
    public static final String QurekaMiniNativeOnOff = "QurekaMiniNativeOnOff";
    public static final String QurekaLargeNativeOnOff = "QurekaLargeNativeOnOff";
    public static final String QurekaListNativeOnOff = "QurekaListNativeOnOff";

    public static final String ShowDialogBeforeAds = "ShowDialogBeforeAds";
    public static final String DialogTimeInSec = "DialogTimeInSec";
    public static final String LoaderNativeOnOff = "LoaderNativeOnOff";

    public static final String OneSignalAppId = "OneSignalAppId";

    public static String BANNERID = "GoogleBannerAds";
    public static String OPENAD = "GoogleAppOpenAds";
    public static String INTERID = "GoogleInterAds";
    public static String NATIVEID = "GoogleNativeAds";

    public static String mAds = "mAds";


    public static void showLog(String message) {
        Log.e("errorLog", message);
    }

    public static void Log(String message) {
        Log.e("errorLog", message);
    }

    public static void showErrorLog(String message) {
        Log.e("errorLog", message);
    }


    public static void gotoAds(Context context) {
        try {
            String packageName = "com.android.chrome";
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorMain));
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.intent.setPackage(packageName);
            customTabsIntent.launchUrl(context, Uri.parse(PowerPreference.getDefaultFile().getString(Constant.QUREKA_ADS, "https://1064.win.qureka.com/")));
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }

    public static boolean checkInternet(Activity activity) {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public static void gotoTerms(Context context) {
        try {
            String packageName = "com.android.chrome";
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorMain));
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.intent.setPackage(packageName);
            customTabsIntent.launchUrl(context, Uri.parse(PowerPreference.getDefaultFile().getString(Constant.POLICY, "https://1064.win.qureka.com/")));
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }

    public static String getOutputFolder() {
        File dCimDirPath = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM).getAbsolutePath());

        if (!dCimDirPath.exists())
            dCimDirPath.mkdir();

        return dCimDirPath + File.separator + MyApplication.getContext().getString(R.string.folder_output);
    }

    public static native String getBase();

    public static native String getKey1();

    public static native String getKey2();


    public static void showRateDialog(Activity activity, boolean isStart, boolean isAds) {
        try {
            Dialog dialog = new Dialog(activity);
            dialog.setContentView(R.layout.dialog_exit);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            final AppCompatButton btnCancel = dialog.findViewById(R.id.btnCancel);
            final AppCompatButton btnRate = dialog.findViewById(R.id.btnRate);
            final AppCompatButton btnExit = dialog.findViewById(R.id.btnExit);

            if (!isStart)
                btnExit.setVisibility(View.GONE);

            btnRate.setOnClickListener(view -> {
                dialog.dismiss();
                Constant.rateUs(activity);
            });

            btnExit.setOnClickListener(view -> {
                dialog.dismiss();

                if (PowerPreference.getDefaultFile().getBoolean(Constant.GoogleExitSplashInterOnOff) && isAds) {
                    PowerPreference.getDefaultFile().putInt(Constant.APP_INTERVAL_COUNT, 0);
                    new InterAds().showSplashAds(activity, new InterAds.OnAdClosedListener() {
                        @Override
                        public void onAdClosed() {
                          activity.finishAffinity();
                        }
                    });
                } else {
                    activity.finishAffinity();
                }
            });

            btnCancel.setOnClickListener(view -> {
                dialog.dismiss();
            });

            dialog.show();


        } catch (Exception e) {
            Log.e("Catch", e.getMessage());
        }

    }

    public static void setQureka(Activity activity, String isSmall, ImageView imageViewMain, ImageView imageViewBG, ImageView imageViewGif) {
        if (PowerPreference.getDefaultFile().getInt(isSmall, 0) >= Constant.adsQurekaInters.length) {
            PowerPreference.getDefaultFile().putInt(isSmall, 0);
            setQureka(activity, isSmall, imageViewMain, imageViewBG, imageViewGif);
        } else {
            if (!activity.isFinishing()) {
                if (imageViewBG != null)
                    Glide.with(activity).load(Constant.adsQurekaInters[PowerPreference.getDefaultFile().getInt(isSmall, 0)])
                            .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageViewBG);

                if (imageViewMain != null)
                    Glide.with(activity).load(Constant.adsQurekaInters[PowerPreference.getDefaultFile().getInt(isSmall, 0)])
                            .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageViewMain);

                if (imageViewGif != null)
                    Glide.with(activity).asGif().load(Constant.adsQurekaGifInters[PowerPreference.getDefaultFile().getInt(isSmall, 0)])
                            .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageViewGif);
            }
            int top = PowerPreference.getDefaultFile().getInt(isSmall, 0) + 1;
            PowerPreference.getDefaultFile().putInt(isSmall, top);
        }
    }

    public static void rateUs(Activity activity) {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + activity.getPackageName())));
        } catch (ActivityNotFoundException e) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + activity.getPackageName())));
        }
    }


}
