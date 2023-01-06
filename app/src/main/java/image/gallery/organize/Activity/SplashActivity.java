package image.gallery.organize.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import image.gallery.organize.API.ApiClient;
import image.gallery.organize.Adhelper.BackInterAds;
import image.gallery.organize.Adhelper.BannerAds;
import image.gallery.organize.Adhelper.InterAds;
import image.gallery.organize.Adhelper.LargeNativeAds;
import image.gallery.organize.Adhelper.ListNative2Ads;
import image.gallery.organize.Adhelper.MiniNativeAds;
import image.gallery.organize.Adhelper.NewOpenAds;
import image.gallery.organize.Adhelper.OpenAds;
import image.gallery.organize.BuildConfig;
import image.gallery.organize.Fragment.FolderFragment;
import image.gallery.organize.Helper.MyCheckService;
import image.gallery.organize.MyApplication;
import image.gallery.organize.Encrypt.DecryptEncrypt;
import image.gallery.organize.Helper.Constant;
import image.gallery.organize.model.AdsInfo;
import image.gallery.organize.model.AppInfo;
import image.gallery.organize.model.UpdateData;

import com.google.android.gms.ads.MobileAds;
import com.google.gson.GsonBuilder;

import image.gallery.organize.Helper.Utils;
import image.gallery.organize.R;
import image.gallery.organize.library.ViewAnimator.AnimationListener;
import image.gallery.organize.library.ViewAnimator.ViewAnimator;

import com.google.gson.JsonObject;
import com.onesignal.OneSignal;
import com.preference.PowerPreference;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {

    public static DataLoad dataLoadListener;
    public static boolean isDataLoaded = false;
    public static boolean isFolderLoaded = false;

    public static int count = 0;

    private static final int REQUEST_PERMISSIONS = 200;
    boolean check = false;

    float oldY;

    ImageView animationView;

    private static final String CHANNEL_ID = "vpn";
    int VERSION = 0;
    Dialog dialog;

    ActivityResultLauncher<Intent> someActivityResultLauncher;

    @BindView(R.id.ivText)
    ImageView ivText;
    @BindView(R.id.ivIcon)
    ImageView ivIcon;

    @BindView(R.id.layoutChange)
    RelativeLayout layoutChange;
    @BindView(R.id.layoutRegular)
    RelativeLayout layoutRegular;

    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtDesc)
    TextView txtDesc;
    @BindView(R.id.txtUpdate)
    TextView txtUpdate;
    @BindView(R.id.txtSkip)
    TextView txtSkip;


    private boolean isMyServiceRunning(Class<?> cls) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE)).getRunningServices(Integer.MAX_VALUE)) {
            if (cls.getName().equals(runningServiceInfo.service.getClassName())) {
                Log.e("ServiceStatus", "Running");
                return true;
            }
        }
        Log.e("ServiceStatus", "Not running");
        return false;
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }, 2000);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_splash);
        ivText = findViewById(R.id.ivText);
        oldY = ivText.getY();



        MyApplication.isEnteredPwd = false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isMyServiceRunning(MyCheckService.class))
                    startService(new Intent(SplashActivity.this, MyCheckService.class));

                PowerPreference.getDefaultFile().putBoolean("running", true);
            }
        }, 2000);
        start();
    }

    public void start() {
        ButterKnife.bind(this);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float height = displaymetrics.heightPixels;

        animationView = findViewById(R.id.ivLoader);

        ivText.setY(height);
        ivText.setRotation(0f);

        setLauncher();
        setAnimation();
    }

    public void setLauncher() {
        if (someActivityResultLauncher == null) {
            someActivityResultLauncher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                check = false;
                                if (checkPermission()) {
                                    nextActivity();
                                } else {
                                    setPermission();
                                }
                            }
                        }
                    });
        }

    }

    private void setAnimation() {

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float height = displaymetrics.heightPixels;

        ViewAnimator.animate(ivIcon)
                .scale(0.f, 1.0f)
                .andAnimate(ivText)
                .translationY(height, oldY)
                .duration(1000)
                .startDelay(1000)
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        animationView.setVisibility(View.VISIBLE);
                        askForPermission();
                    }
                })
                .start();
    }

    private void getdata() {


        Utils.getInstance().scanMedia(this);

        PowerPreference.getDefaultFile().putBoolean("isDataLoaded", false);
        PowerPreference.getDefaultFile().putBoolean("isFolderLoaded", false);
        PowerPreference.getDefaultFile().putBoolean("isDFolderLoaded", false);

        Utils.getInstance().getBinImages(this);
        new movetoHomeAndLoadData().execute();
        new Thread(() -> loadFolders()).start();


    }

    private void loadFolders() {

        Cursor cursor = Utils.getInstance().getAllImagesVideoFromStorageForDate(SplashActivity.this);
        MyApplication.folderData.clear();

        if (cursor != null && cursor.moveToFirst()) {

            while (cursor.moveToNext()) {

                String path;
                int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

                while (cursor.moveToNext()) {

                    try {
                        path = cursor.getString(column_index_data);

                        if (!path.startsWith("/data") && new File(path).exists() && !path.endsWith("nomedia") && !path.endsWith(".gif")) {

                            ArrayList<String> datatemp = new ArrayList<>();
                            ArrayList<String> durtemp = new ArrayList<>();
                            String foldername = new File(path).getParentFile().getName();

                            if (!foldername.startsWith(".")) {
                                if (foldername.equalsIgnoreCase("Camera") || foldername.equalsIgnoreCase("Screenshots") ||
                                        foldername.equalsIgnoreCase("Videos") || foldername.equalsIgnoreCase("Downloads")) {
                                    if (MyApplication.DefaultFolderData.containsKey(foldername)) {
                                        datatemp.addAll(MyApplication.DefaultFolderData.get(foldername));
                                        datatemp.add(path);
                                    } else {
                                        datatemp.add(path);
                                    }

                                    MyApplication.DefaultFolderData.put(foldername, datatemp);
                                } else {
                                    if (MyApplication.folderData.containsKey(foldername)) {
                                        datatemp.addAll(MyApplication.folderData.get(foldername));
                                        datatemp.add(path);

                                    } else {
                                        datatemp.add(path);
                                    }
                                    MyApplication.folderData.put(foldername, datatemp);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        try {
            if (cursor != null)
                cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TreeMap<String, ArrayList<String>> sorted = new TreeMap<>();
        sorted.putAll(MyApplication.folderData);

        MyApplication.DefaultFolderData.put(MyApplication.DefaultFolderData.size(), "Favourite", new ArrayList<>());

        MyApplication.folderData.clear();
        MyApplication.folderData.putAll(sorted);

        isFolderLoaded = true;

        runOnUiThread(() -> {
            if (FolderFragment.adapter != null)
                FolderFragment.adapter.addData(MyApplication.folderData);

            if (FolderFragment.adapterDefault != null)
                FolderFragment.adapterDefault.addData(MyApplication.DefaultFolderData);
        });
    }

    private class movetoHomeAndLoadData extends AsyncTask<Void, Void, Void> {

        Cursor cursor = null;

        @Override
        protected Void doInBackground(Void... voids) {

            cursor = Utils.getInstance().getAllImagesVideoFromStorageForDate(SplashActivity.this);

            MyApplication.allimages.clear();
            SimpleDateFormat timeStampFormat = new SimpleDateFormat("MMM dd,yyyy");

            isDataLoaded = false;
            String Date = "";
            int millis = 1;

            if (cursor != null && cursor.moveToFirst()) {

                cursor.moveToPosition(0);

                long date = cursor.getLong(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATE_MODIFIED));
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(date);

                if (calendar.get(Calendar.YEAR) == 1970) {
                    millis = 1000;
                }

                if (cursor.moveToFirst()) {
                    do {

                        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA));

                        if (!path.startsWith("/data") && new File(path).exists() && !path.endsWith(".nomedia") && !path.endsWith(".gif")) {

                            calendar = Calendar.getInstance();
                            calendar.setTimeInMillis(cursor.getLong(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATE_MODIFIED)) * millis);

                            String datestr = timeStampFormat.format(calendar.getTime());
                            if (!datestr.equals(Date)) {
                                MyApplication.allimages.add(datestr);
                                Date = datestr;
                            }


                            MyApplication.allimages.add(path);
                            MyApplication.allimagesCopyWithoutDates.add(path);

                            count++;
                        }

                    } while (cursor.moveToNext());

                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            try {
                if (cursor != null)
                    cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            isDataLoaded = true;
            if (dataLoadListener != null)
                dataLoadListener.onDataLoaded();

        }
    }

    public interface DataLoad {
        void onDataLoaded();
    }

    @Override
    protected void onResume() {
        super.onResume();

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        if (check) {
            check = false;
            askForPermission();
        }
    }


    public void nextActivity() {
        getdata();
        PackageManager manager = getPackageManager();
        PackageInfo info = null;

        try {
            info = manager.getPackageInfo(getPackageName(), 0);
            VERSION = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Constant.Log(e.toString());
            VERSION = BuildConfig.VERSION_CODE;
        }

        if (Constant.checkInternet(this))
            checkUpdate();
        else {
            PowerPreference.getDefaultFile().putBoolean(Constant.QurekaOnOff, false);
            PowerPreference.getDefaultFile().putBoolean(Constant.AdsOnOff, false);

            gotoSkip();
        }
    }

    private void askForPermission() {
        check = false;
        if (checkPermission()) {
            nextActivity();
        } else {
            setPermission();
        }
    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(SplashActivity.this, READ_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(SplashActivity.this, WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void setPermission() {

        ActivityCompat.requestPermissions(SplashActivity.this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {

            if (grantResults.length > 0) {

                boolean write = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean writeD = shouldShowRequestPermissionRationale(permissions[0]);

                boolean read = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                boolean readD = shouldShowRequestPermissionRationale(permissions[1]);

                if (write && read) {
                    nextActivity();
                } else if (!writeD || !readD) {
                    forcePermissionDialog("You need to allow access to the permissions. Without this permission you can't access your storage. Are you sure deny this permission?",
                            (dialog, which) -> {
                                check = true;
                                Intent intent = new Intent();
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivity(intent);
                            });
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                        if (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) {
                            oKCancelDialog("You need to allow access to the permissions",
                                    (dialog, which) -> {
                                        requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE},
                                                REQUEST_PERMISSIONS);
                                    });
                        }
                    }
                }
            }
        }
    }


    private void oKCancelDialog(String s, DialogInterface.OnClickListener o) {
        new AlertDialog.Builder(SplashActivity.this)
                .setMessage(s)
                .setPositiveButton("OK", o)
                .create()
                .show();
    }

    private void forcePermissionDialog(String s, DialogInterface.OnClickListener aPackage) {
        new AlertDialog.Builder(SplashActivity.this)
                .setTitle("Permission Denied")
                .setMessage(s)
                .setPositiveButton("Give Permission", aPackage)
                .create()
                .show();
    }


    private void checkUpdate() {
        if (Constant.checkInternet(this)) {

            @SuppressLint("HardwareIds") String deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

            ApiClient.getInstance().getApi2().requestinfo(deviceId, getPackageName(), VERSION)
                    .enqueue(new Callback<UpdateData>() {
                        @Override
                        public void onResponse(Call<UpdateData> call, retrofit2.Response<UpdateData> response) {

                            try {
                                final UpdateData appData = response.body();

                                AdsInfo adsInfo = appData.getData().getAdsInfo().get(0);
                                AppInfo appInfo = appData.getData().getAppInfo().get(0);

                                PowerPreference.getDefaultFile().putBoolean(Constant.QurekaOnOff, adsInfo.getQurekaOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.AdsOnOff, adsInfo.getAdsOnOff());

                                PowerPreference.getDefaultFile().putBoolean(Constant.LoaderNativeOnOff, adsInfo.getLoaderNativeOnOff());

                                PowerPreference.getDefaultFile().putInt(Constant.AppOpen, adsInfo.getAppOpen());

                                PowerPreference.getDefaultFile().putInt(Constant.SERVER_INTERVAL_COUNT, adsInfo.getIntervalCount());
                                PowerPreference.getDefaultFile().putInt(Constant.APP_INTERVAL_COUNT, 0);

                                PowerPreference.getDefaultFile().putInt(Constant.SERVER_BACK_COUNT, adsInfo.getBackIntervalCount());
                                PowerPreference.getDefaultFile().putInt(Constant.APP_BACK_COUNT, 0);


                                PowerPreference.getDefaultFile().putBoolean(Constant.BACK_ADS, adsInfo.getGoogleBackInterOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.GoogleSplashOpenAdsOnOff, adsInfo.getGoogleSplashOpenAdsOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.GoogleExitSplashInterOnOff, adsInfo.getGoogleExitSplashInterOnOff());

                                PowerPreference.getDefaultFile().putString(Constant.QUREKA_ADS, appInfo.getQureka());

                                PowerPreference.getDefaultFile().putBoolean(Constant.GoogleBannerOnOff, adsInfo.getGoogleBannerOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.GoogleMiniNativeOnOff, adsInfo.getGoogleMiniNativeOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.GoogleLargeNativeOnOff, adsInfo.getGoogleLargeNativeOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.GoogleListNativeOnOff, adsInfo.getGoogleListNativeOnOff());

                                PowerPreference.getDefaultFile().putInt(Constant.GoogleWhichOneNative, adsInfo.getBannerAdWhichOne());
                                PowerPreference.getDefaultFile().putInt(Constant.ListNativeWhichOne, adsInfo.getListNativeWhichOne());
                                PowerPreference.getDefaultFile().putInt(Constant.ListNativeAfterCount, adsInfo.getListNativeAfterCount());

                                PowerPreference.getDefaultFile().putBoolean(Constant.QurekaBannerOnOff, adsInfo.getQurekaBannerOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.QurekaInterOnOff, adsInfo.getQurekaInterOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.QurekaBackInterOnOff, adsInfo.getQurekaBackInterOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.QurekaMiniNativeOnOff, adsInfo.getQurekaMiniNativeOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.QurekaLargeNativeOnOff, adsInfo.getQurekaLargeNativeOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.QurekaListNativeOnOff, adsInfo.getQurekaListNativeOnOff());
                                PowerPreference.getDefaultFile().putBoolean(Constant.QurekaAppOpenOnOff, adsInfo.getQurekaAppOpenOnOff());

                                PowerPreference.getDefaultFile().putBoolean(Constant.ShowDialogBeforeAds, adsInfo.getShowDialogBeforeAds());
                                PowerPreference.getDefaultFile().putDouble(Constant.DialogTimeInSec, adsInfo.getDialogTimeInSec());

                                PowerPreference.getDefaultFile().putString(Constant.INTERID, adsInfo.getGoogleInterAds());
                                PowerPreference.getDefaultFile().putString(Constant.NATIVEID, adsInfo.getGoogleNativeAds());
                                PowerPreference.getDefaultFile().putString(Constant.OPENAD, adsInfo.getGoogleAppOpenAds());
                                PowerPreference.getDefaultFile().putString(Constant.BANNERID, adsInfo.getGoogleBannerAds());

                                PowerPreference.getDefaultFile().putBoolean(Constant.GoogleAppOpenOnOff, adsInfo.getGoogleAppOpenAdsOnOff());

                                OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

                                PowerPreference.getDefaultFile().putString(Constant.OneSignalAppId, appInfo.getOneSignalAppId());
                                OneSignal.initWithContext(MyApplication.getInstance());
                                OneSignal.setAppId(PowerPreference.getDefaultFile().getString(Constant.OneSignalAppId, ""));


                                if (adsInfo.getAdsOnOff()) {
                                    try {
                                        ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
                                        ai.metaData.putString("com.google.android.gms.ads.APPLICATION_ID", adsInfo.getGoogleAppIdAds());
                                    } catch (PackageManager.NameNotFoundException e) {
                                        Constant.showLog("Failed to load meta-data, NameNotFound: " + e.getMessage());
                                    } catch (NullPointerException e) {
                                        Constant.showLog("Failed to load meta-data, NullPointer: " + e.getMessage());
                                    }

                                    MobileAds.initialize(SplashActivity.this, adsInfo.getGoogleAppIdAds());

                                    new InterAds().loadInterAds(SplashActivity.this);
                                    new BackInterAds().loadInterAds(SplashActivity.this);
                                    new MiniNativeAds().loadNativeAds(SplashActivity.this, null);
                                    new LargeNativeAds().loadNativeAds(SplashActivity.this, null);
                                    new NewOpenAds().loadOpenAd(SplashActivity.this);
                                    new OpenAds().loadOpenAd();
                                    new BannerAds().loadBannerAds(SplashActivity.this);
                                    new ListNative2Ads().loadNativeAds(SplashActivity.this, null);
                                }

                                if (!appInfo.getTitle().equals("")) {
                                    txtTitle.setText(appInfo.getTitle());
                                    txtTitle.setVisibility(View.VISIBLE);
                                }

                                if (!appInfo.getDescription().equals("")) {
                                    txtDesc.setText(appInfo.getDescription());
                                    txtDesc.setVisibility(View.VISIBLE);
                                }

                                if (!appInfo.getButtonName().equals("")) {
                                    txtUpdate.setText(appInfo.getButtonName());
                                }

                                if (!appInfo.getButtonSkip().equals("")) {
                                    txtSkip.setText(appInfo.getButtonSkip());
                                }

                                String flag = appInfo.getFlag();
                                boolean flagCheck = true;

                                if (flag.equals("NORMAL")) {
                                    layoutChange.setVisibility(View.GONE);
                                    flagCheck = true;
                                } else if (flag.equals("SKIP")) {

                                    if (VERSION < Integer.valueOf(appInfo.getVersion())) {
                                        txtUpdate.setVisibility(View.VISIBLE);
                                        layoutChange.setVisibility(View.VISIBLE);
                                        txtSkip.setVisibility(View.VISIBLE);
                                        flagCheck = false;
                                    } else {
                                        layoutChange.setVisibility(View.GONE);
                                        flagCheck = true;
                                    }
                                } else if (flag.equals("MOVE")) {
                                    txtUpdate.setVisibility(View.VISIBLE);
                                    txtSkip.setVisibility(View.GONE);
                                    layoutChange.setVisibility(View.VISIBLE);
                                    flagCheck = false;

                                } else if (flag.equals("FORCE")) {
                                    if (VERSION < Integer.valueOf(appInfo.getVersion())) {
                                        txtUpdate.setVisibility(View.VISIBLE);
                                        txtSkip.setVisibility(View.GONE);
                                        layoutChange.setVisibility(View.VISIBLE);
                                        flagCheck = false;
                                    } else {
                                        layoutChange.setVisibility(View.GONE);
                                    }
                                }

                                txtUpdate.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (flag.equals("MOVE")) {
                                            try {
                                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(appInfo.getLink())));
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            Constant.rateUs(SplashActivity.this);
                                        }
                                    }
                                });

                                txtSkip.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        layoutChange.setVisibility(View.GONE);
                                        gotoSkip();
                                    }
                                });

                                if (flagCheck) {
                                    gotoSkip();
                                }

                            } catch (Exception e) {
                                Constant.Log(e.toString());
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<UpdateData> call
                                , Throwable t) {
                            network_dialog(t.getMessage()).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    if (Constant.checkInternet(SplashActivity.this)) {
                                        checkUpdate();
                                    } else dialog.show();
                                }
                            });
                        }
                    });
        } else {
            network_dialog(getResources().getString(R.string.error_internet)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (Constant.checkInternet(SplashActivity.this)) {
                        checkUpdate();
                    } else dialog.show();
                }
            });
        }
    }


    public TextView network_dialog(String text) {
        dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialog_internet);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        ((TextView) dialog.findViewById(R.id.txtError)).setText(text);
        return dialog.findViewById(R.id.btnRetry);
    }


    public void gotoSkip() {
        if (PowerPreference.getDefaultFile().getBoolean(Constant.GoogleSplashOpenAdsOnOff,false)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    new NewOpenAds().showOpenAd(SplashActivity.this, new NewOpenAds.OnAdClosedListener() {
                        @Override
                        public void onAdClosed() {
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        }
                    });
                }
            },2000);
        } else {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }
    }
}
