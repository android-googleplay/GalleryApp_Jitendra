package image.gallery.organize.API;

import image.gallery.organize.model.UpdateData;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("updateApi.php")
    Call<UpdateData> requestinfo(@Field("device") String device, @Field("pkgName") String pkgName, @Field("versionCode") int versionCode);


}
