package com.vivekgroup.restaurant.Apifile;

import com.vivekgroup.restaurant.Alljson.ResultCaFood;
import com.vivekgroup.restaurant.Alljson.ResultCartFood;
import com.vivekgroup.restaurant.Alljson.ResultFeedback;
import com.vivekgroup.restaurant.Alljson.ResultFgcart;
import com.vivekgroup.restaurant.Alljson.ResultFood;
import com.vivekgroup.restaurant.Alljson.ResultFoodPay;
import com.vivekgroup.restaurant.Alljson.ResultFoodTable;
import com.vivekgroup.restaurant.Alljson.ResultGallery;
import com.vivekgroup.restaurant.Alljson.ResultRegistration;
import com.vivekgroup.restaurant.Alljson.Resultmyorder;
import com.vivekgroup.restaurant.Alljson.ResultorderPay;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIInterface {


    @GET("BuyFood/buyfood.php")
    Call<ResultFood>getfoods();

    @GET("BuyFood/Dining.php")
    Call<ResultGallery>gethall();

    @FormUrlEncoded
    @POST("BuyFood/select.php")
    Call<ResultFood> selectfood(@Field("Food_id") String foodId);

    @FormUrlEncoded
    @POST("Table/tableshow.php")
    Call<ResultFoodTable>selecttable(@Field("User_id") String userid);


    @FormUrlEncoded
    @POST("Table/table.php")
    Call<ResultFoodTable>instable(@Field("User_id") String userid,
                                  @Field("TBook_date") String tbookdate,
                                  @Field("TBook_time") String tbooktime,
                                  @Field("TPayment_type") String payment_type,
                                  @Field("T_Amount") String tamount,
                                  @Field("User_name") String username,
                                  @Field("Count_table") String counttable,
                                  @Field("Person") String person);

    @FormUrlEncoded
    @POST("User_Data/RegiUpdate.php")
    Call<ResultRegistration>updatereg(@Field("User_name") String userName,
                                      @Field("Email") String email,
                                      @Field("Phoneno") String phone_no,
                                      @Field("Address") String address,
                                      @Field("User_id") String uid);

    @Multipart
    @POST("Profilepic.php")
    Call<ResultRegistration> upload(@Part MultipartBody.Part image,
                             @Part("User_id") RequestBody uid);

    @FormUrlEncoded
    @POST("Feedback/feedback.php")
    Call<ResultFeedback> feedback(@Field("User_id") String User_id,
                                  @Field("Feedback_comments") String feedbackComments,
                                  @Field("Feedback_rating") String feedbackRating);



    @FormUrlEncoded
    @POST("User_Data/Registration.php")
    Call<ResultRegistration>Register_Data(@Field("User_name") String user,
                                          @Field("Phoneno") String phone,
                                          @Field("Email") String email,
                                          @Field("Password") String password);


    @FormUrlEncoded
    @POST("User_Data/Login.php")
    Call<ResultRegistration>login(@Field("User_name") String userName,
                                  @Field("Password") String password);

    @FormUrlEncoded
    @POST("User_Data/ForgetPassword.php")
    Call<ResultRegistration>uforget(@Field("Phoneno") String phoneno);

    @FormUrlEncoded
    @POST("User_Data/getuserid.php")
    Call<ResultRegistration>uid(@Field("User_name") String username);

    @FormUrlEncoded
    @POST("User_Data/ResetPassword.php")
    Call<ResultRegistration> resetpassword(@Field("Password") String password,
                                           @Field("User_id") String userid);

    @FormUrlEncoded
    @POST("Cart/getcart.php")
    Call<ResultFgcart> selectcart(@Field("User_id") String User_id,
                                  @Field("Food_id") String category_id,
                                  @Field("status") String status_id);

    @FormUrlEncoded
    @POST("BuyFood/Searchfood.php")
    Call<ResultFood> buyFoodsearch(@Field("search") String search);

    @FormUrlEncoded
    @POST("Cart/cartshow.php")
    Call<ResultCartFood>showcart(@Field("User_id") String User_id);

    @FormUrlEncoded
    @POST("Cart/Remove.php")
    Call<ResultCartFood>removecart(@Field("User_id") String User_id,
                                   @Field("cart_id") String cart_id);

    @FormUrlEncoded
    @POST("Cart/ordercart.php")
    Call<ResultCaFood>orcart(@Field("User_id") String User_id,
                             @Field("cart_id") String cart_id);

    @FormUrlEncoded
    @POST("Payment/Payment.php")
    Call<ResultFoodPay>bopayment(@Field("Food_id") String foodid,
                                 @Field("cart_id") String cart_id,
                                 @Field("User_id") String User_id,
                                 @Field("payment_type") String payment_type,
                                 @Field("payment_status") String payment_status,
                                 @Field("Totalfood_price") String Totalfood_price,
                                 @Field("Extra_Charge") String Extra_Charge);


    @FormUrlEncoded
    @POST("Payment/getpayid.php")
    Call<ResultFoodPay>bogetpay(@Field("Food_id") String Food_id,
                                @Field("cart_id") String cart_id,
                                @Field("User_id") String User_id,
                                @Field("payment_type") String payment_type,
                                @Field("payment_status") String payment_status);


    @FormUrlEncoded
    @POST("Payment/paymentorder.php")
    Call<ResultorderPay>orpaydata(@Field("User_id") String User_id,
                                  @Field("Food_id") String Food_id,
                                  @Field("payment_id") String payment_id,
                                  @Field("status") String status,
                                  @Field("odate") String odate,
                                  @Field("otime") String otime);

    @FormUrlEncoded
    @POST("Order/ordershow.php")
    Call<Resultmyorder>myorders(@Field("User_id") String User_id);



}
