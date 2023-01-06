
package image.gallery.organize.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdsInfo {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("AppOpen")
    @Expose
    private Integer appOpen;
    @SerializedName("LoaderNativeOnOff")
    @Expose
    private Boolean loaderNativeOnOff;
    @SerializedName("GoogleBannerAds")
    @Expose
    private String googleBannerAds;
    @SerializedName("GoogleInterAds")
    @Expose
    private String googleInterAds;
    @SerializedName("GoogleNativeAds")
    @Expose
    private String googleNativeAds;
    @SerializedName("GoogleAppOpenAds")
    @Expose
    private String googleAppOpenAds;
    @SerializedName("GoogleAppIdAds")
    @Expose
    private String googleAppIdAds;
    @SerializedName("AdsOnOff")
    @Expose
    private Boolean adsOnOff;
    @SerializedName("GoogleAdsOnOff")
    @Expose
    private Boolean googleAdsOnOff;
    @SerializedName("QurekaOnOff")
    @Expose
    private Boolean qurekaOnOff;
    @SerializedName("GoogleSplashOpenAdsOnOff")
    @Expose
    private Boolean googleSplashOpenAdsOnOff;
    @SerializedName("GoogleExitSplashInterOnOff")
    @Expose
    private Boolean googleExitSplashInterOnOff;
    @SerializedName("GoogleAppOpenAdsOnOff")
    @Expose
    private Boolean googleAppOpenAdsOnOff;
    @SerializedName("GoogleBannerOnOff")
    @Expose
    private Boolean googleBannerOnOff;
    @SerializedName("BannerAdWhichOne")
    @Expose
    private Integer bannerAdWhichOne;
    @SerializedName("IntervalCount")
    @Expose
    private Integer intervalCount;
    @SerializedName("BackIntervalCount")
    @Expose
    private Integer backIntervalCount;
    @SerializedName("GoogleInterOnOff")
    @Expose
    private Boolean googleInterOnOff;
    @SerializedName("GoogleBackInterOnOff")
    @Expose
    private Boolean googleBackInterOnOff;
    @SerializedName("GoogleMiniNativeOnOff")
    @Expose
    private Boolean googleMiniNativeOnOff;
    @SerializedName("GoogleLargeNativeOnOff")
    @Expose
    private Boolean googleLargeNativeOnOff;

    @SerializedName("GoogleListNativeOnOff")
    @Expose
    private Boolean googleListNativeOnOff;
    @SerializedName("ListNativeWhichOne")
    @Expose
    private Integer listNativeWhichOne;
    @SerializedName("ListNativeAfterCount")
    @Expose
    private Integer listNativeAfterCount;
    @SerializedName("QurekaIconOnOff")
    @Expose
    private Boolean qurekaIconOnOff;
    @SerializedName("QurekaBannerOnOff")
    @Expose
    private Boolean qurekaBannerOnOff;
    @SerializedName("QurekaInterOnOff")
    @Expose
    private Boolean qurekaInterOnOff;
    @SerializedName("QurekaBackInterOnOff")
    @Expose
    private Boolean qurekaBackInterOnOff;
    @SerializedName("QurekaMiniNativeOnOff")
    @Expose
    private Boolean qurekaMiniNativeOnOff;
    @SerializedName("QurekaLargeNativeOnOff")
    @Expose
    private Boolean qurekaLargeNativeOnOff;
    @SerializedName("QurekaListNativeOnOff")
    @Expose
    private Boolean qurekaListNativeOnOff;
    @SerializedName("QurekaAppOpenOnOff")
    @Expose
    private Boolean qurekaAppOpenOnOff;
    @SerializedName("ShowDialogBeforeAds")
    @Expose
    private Boolean showDialogBeforeAds;
    @SerializedName("DialogTimeInSec")
    @Expose
    private Integer dialogTimeInSec;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAppOpen() {
        return appOpen;
    }

    public void setAppOpen(Integer appOpen) {
        this.appOpen = appOpen;
    }

    public Boolean getLoaderNativeOnOff() {
        return loaderNativeOnOff;
    }

    public void setLoaderNativeOnOff(Boolean loaderNativeOnOff) {
        this.loaderNativeOnOff = loaderNativeOnOff;
    }

    public String getGoogleBannerAds() {
        return googleBannerAds;
    }

    public void setGoogleBannerAds(String googleBannerAds) {
        this.googleBannerAds = googleBannerAds;
    }

    public String getGoogleInterAds() {
        return googleInterAds;
    }

    public void setGoogleInterAds(String googleInterAds) {
        this.googleInterAds = googleInterAds;
    }

    public String getGoogleNativeAds() {
        return googleNativeAds;
    }

    public void setGoogleNativeAds(String googleNativeAds) {
        this.googleNativeAds = googleNativeAds;
    }

    public String getGoogleAppOpenAds() {
        return googleAppOpenAds;
    }

    public void setGoogleAppOpenAds(String googleAppOpenAds) {
        this.googleAppOpenAds = googleAppOpenAds;
    }

    public String getGoogleAppIdAds() {
        return googleAppIdAds;
    }

    public void setGoogleAppIdAds(String googleAppIdAds) {
        this.googleAppIdAds = googleAppIdAds;
    }

    public Boolean getAdsOnOff() {
        return adsOnOff;
    }

    public void setAdsOnOff(Boolean adsOnOff) {
        this.adsOnOff = adsOnOff;
    }

    public Boolean getGoogleAdsOnOff() {
        return googleAdsOnOff;
    }

    public void setGoogleAdsOnOff(Boolean googleAdsOnOff) {
        this.googleAdsOnOff = googleAdsOnOff;
    }

    public Boolean getQurekaOnOff() {
        return qurekaOnOff;
    }

    public void setQurekaOnOff(Boolean qurekaOnOff) {
        this.qurekaOnOff = qurekaOnOff;
    }

    public Boolean getGoogleSplashOpenAdsOnOff() {
        return googleSplashOpenAdsOnOff;
    }

    public void setGoogleSplashOpenAdsOnOff(Boolean googleSplashOpenAdsOnOff) {
        this.googleSplashOpenAdsOnOff = googleSplashOpenAdsOnOff;
    }

    public Boolean getGoogleExitSplashInterOnOff() {
        return googleExitSplashInterOnOff;
    }

    public void setGoogleExitSplashInterOnOff(Boolean googleExitSplashInterOnOff) {
        this.googleExitSplashInterOnOff = googleExitSplashInterOnOff;
    }

    public Boolean getGoogleAppOpenAdsOnOff() {
        return googleAppOpenAdsOnOff;
    }

    public void setGoogleAppOpenAdsOnOff(Boolean googleAppOpenAdsOnOff) {
        this.googleAppOpenAdsOnOff = googleAppOpenAdsOnOff;
    }

    public Boolean getGoogleBannerOnOff() {
        return googleBannerOnOff;
    }

    public void setGoogleBannerOnOff(Boolean googleBannerOnOff) {
        this.googleBannerOnOff = googleBannerOnOff;
    }

    public Integer getBannerAdWhichOne() {
        return bannerAdWhichOne;
    }

    public void setBannerAdWhichOne(Integer bannerAdWhichOne) {
        this.bannerAdWhichOne = bannerAdWhichOne;
    }

    public Integer getIntervalCount() {
        return intervalCount;
    }

    public void setIntervalCount(Integer intervalCount) {
        this.intervalCount = intervalCount;
    }

    public Integer getBackIntervalCount() {
        return backIntervalCount;
    }

    public void setBackIntervalCount(Integer backIntervalCount) {
        this.backIntervalCount = backIntervalCount;
    }

    public Boolean getGoogleInterOnOff() {
        return googleInterOnOff;
    }

    public void setGoogleInterOnOff(Boolean googleInterOnOff) {
        this.googleInterOnOff = googleInterOnOff;
    }

    public Boolean getGoogleBackInterOnOff() {
        return googleBackInterOnOff;
    }

    public void setGoogleBackInterOnOff(Boolean googleBackInterOnOff) {
        this.googleBackInterOnOff = googleBackInterOnOff;
    }

    public Boolean getGoogleMiniNativeOnOff() {
        return googleMiniNativeOnOff;
    }

    public void setGoogleMiniNativeOnOff(Boolean googleMiniNativeOnOff) {
        this.googleMiniNativeOnOff = googleMiniNativeOnOff;
    }

    public Boolean getGoogleLargeNativeOnOff() {
        return googleLargeNativeOnOff;
    }

    public void setGoogleLargeNativeOnOff(Boolean googleLargeNativeOnOff) {
        this.googleLargeNativeOnOff = googleLargeNativeOnOff;
    }


    public Boolean getGoogleListNativeOnOff() {
        return googleListNativeOnOff;
    }

    public void setGoogleListNativeOnOff(Boolean googleListNativeOnOff) {
        this.googleListNativeOnOff = googleListNativeOnOff;
    }

    public Integer getListNativeWhichOne() {
        return listNativeWhichOne;
    }

    public void setListNativeWhichOne(Integer listNativeWhichOne) {
        this.listNativeWhichOne = listNativeWhichOne;
    }

    public Integer getListNativeAfterCount() {
        return listNativeAfterCount;
    }

    public void setListNativeAfterCount(Integer listNativeAfterCount) {
        this.listNativeAfterCount = listNativeAfterCount;
    }

    public Boolean getQurekaIconOnOff() {
        return qurekaIconOnOff;
    }

    public void setQurekaIconOnOff(Boolean qurekaIconOnOff) {
        this.qurekaIconOnOff = qurekaIconOnOff;
    }

    public Boolean getQurekaBannerOnOff() {
        return qurekaBannerOnOff;
    }

    public void setQurekaBannerOnOff(Boolean qurekaBannerOnOff) {
        this.qurekaBannerOnOff = qurekaBannerOnOff;
    }

    public Boolean getQurekaInterOnOff() {
        return qurekaInterOnOff;
    }

    public void setQurekaInterOnOff(Boolean qurekaInterOnOff) {
        this.qurekaInterOnOff = qurekaInterOnOff;
    }

    public Boolean getQurekaBackInterOnOff() {
        return qurekaBackInterOnOff;
    }

    public void setQurekaBackInterOnOff(Boolean qurekaBackInterOnOff) {
        this.qurekaBackInterOnOff = qurekaBackInterOnOff;
    }

    public Boolean getQurekaMiniNativeOnOff() {
        return qurekaMiniNativeOnOff;
    }

    public void setQurekaMiniNativeOnOff(Boolean qurekaMiniNativeOnOff) {
        this.qurekaMiniNativeOnOff = qurekaMiniNativeOnOff;
    }

    public Boolean getQurekaLargeNativeOnOff() {
        return qurekaLargeNativeOnOff;
    }

    public void setQurekaLargeNativeOnOff(Boolean qurekaLargeNativeOnOff) {
        this.qurekaLargeNativeOnOff = qurekaLargeNativeOnOff;
    }

    public Boolean getQurekaListNativeOnOff() {
        return qurekaListNativeOnOff;
    }

    public void setQurekaListNativeOnOff(Boolean qurekaListNativeOnOff) {
        this.qurekaListNativeOnOff = qurekaListNativeOnOff;
    }

    public Boolean getQurekaAppOpenOnOff() {
        return qurekaAppOpenOnOff;
    }

    public void setQurekaAppOpenOnOff(Boolean qurekaAppOpenOnOff) {
        this.qurekaAppOpenOnOff = qurekaAppOpenOnOff;
    }

    public Boolean getShowDialogBeforeAds() {
        return showDialogBeforeAds;
    }

    public void setShowDialogBeforeAds(Boolean showDialogBeforeAds) {
        this.showDialogBeforeAds = showDialogBeforeAds;
    }

    public Integer getDialogTimeInSec() {
        return dialogTimeInSec;
    }

    public void setDialogTimeInSec(Integer dialogTimeInSec) {
        this.dialogTimeInSec = dialogTimeInSec;
    }



}
