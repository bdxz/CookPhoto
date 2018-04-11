package com.example.morphtin.dishes.util;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;

/**
 * Created by Morphtin on 2018/4/3.
 */

public class RetrofitApi {
    private static RetrofitAPI retrofitAPI;

    public static RetrofitAPI Retrofit() {
        if (retrofitAPI == null) {

            retrofitAPI = new Retrofit.Builder()
                    .baseUrl("http://12.34.56.78:8080/cookphoto/android/")
                    //.addConverterFactory(ScalarsConverterFactory.create())
                    .build()
                    .create(RetrofitAPI.class);
        }
        return retrofitAPI;
    }

    private interface RetrofitAPI {

        //单张图片上传
        @Multipart
        @POST("addImage.html")
        Call<Object> updateImage(@Part MultipartBody.Part image);

        //多张图片上传
        @Multipart
        @POST("addImage.html")
        Call<Object> updateImage(@Part MultipartBody.Part[] images);

        /*
        上传图片方式

        File file1 = new File(path);
        File file2 = new File(path);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file1);其中`multipart/form-data`为编码类型

        MultipartBody.Part[]  images = new MultipartBody.Part[2];
        files[0] = MultipartBody.Part.createFormData("images", file1.getName(), requestFile);需要注意第一个参数`images'需要与服务器对应,也就是`键`，多个也一样
        requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file2);
        files[1] = MultipartBody.Part.createFormData("images", file2.getName(), requestFile);

       RetrofitApi.Retrofit().updateImage(images).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, response.code() + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        Server:
        @RequestMapping(value = "addImage", method = RequestMethod.POST)

        public void addImage(@RequestParam MultipartFile[] images,HttpServletResponse response, HttpServletRequest request) throws IllegalStateException, IOException,
		    ParseException {

         }
         public void addImage(@RequestParam MultipartFile image, HttpServletResponse response, HttpServletRequest request) throws IllegalStateException, IOException,
		    ParseException {

         }

         code 400 Bad Request 这种情况一般都是参数类型与服务器不匹配，一般需要检查参数和服务器参数是否对应的上
         code 500 Internal server error 在确认你的Url没有错误的话，一般都是服务器的原因
         code 404 Not found 请检查url是否拼接正确，一般来说都@POST("addPaster.html")的参数不宜过长
         */


        //图文上传
        @Multipart
        @POST("addImage.html")
        Call<Object> updateImageAndText(@Part MultipartBody.Part[] parts, @QueryMap Map<String, String> maps);

        //登陆
        @POST("Login/submit.html")
        @FormUrlEncoded
        Call<Object> Login(@Field("loginname") String loginname,
                           @Field("nloginpwd") String nloginpwd);
        /*
        An object can be specified for use as an HTTP request body with the @Body annotation.
            @POST("users/new")
            Call<User> createUser(@Body User user);



        RetrofitApi.Retrofit().Login("222", "333").enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        Log.e("成功",response.body().toString());  请求成功的值在response.body里
                    }
                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Log.e("失败","失败");
                    }
                });
         */

    }
}
