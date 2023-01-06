
package image.gallery.organize.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("app_info")
    @Expose
    private List<AppInfo> appInfo = null;
    @SerializedName("ads_info")
    @Expose
    private List<AdsInfo> adsInfo = null;

    public List<AppInfo> getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(List<AppInfo> appInfo) {
        this.appInfo = appInfo;
    }

    public List<AdsInfo> getAdsInfo() {
        return adsInfo;
    }

    public void setAdsInfo(List<AdsInfo> adsInfo) {
        this.adsInfo = adsInfo;
    }

}
