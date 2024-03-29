package com.example.lasttry
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit API service interface for making network requests.
 */
interface ApiService {

    /**
     * GET request to /getResults to retrieve a list of Getdata objects.
     *
     * @return Call object for the asynchronous response containing a list of Getdata.
     */
    @GET("StudentInfo")
    // Retrieves student information based on registration number and date of birth.
    fun getStudentInfo(@Query("reg_no") regNo: Int,@Query("dateofbirth") dateofbirth:String): Call<List<StudentInfo>>
    @GET("getResults/{semester}")
    // Retrieves results for a specific semester based on registration number.
    fun getResults(
        @Path("semester") semester: String,
        @Query("reg_no") regNo: Int
    ): Call<List<Getdata>>


    @GET("StudentFullResults")
    // Retrieves full results for a student based on registration number.
    fun getStudentFullResults(@Query("reg_no") regNo: Int): Call<List<Getdata>>
    @GET("VDSCreatorInfo")
    // Retrieves student information based on registration number and date of birth.
    fun getVDSCreatorInfo(@Query("email") email: String,@Query("password") password:String): Call<List<VDSCreator>>

    @POST("VDSCreator")
    fun InputVDSCreatorInfo(
        @Body Info: VDSCreator // Request body containing updated RenterInfo
    ): Call<Void>


    @POST("VDSdata")
    fun InputVDSdataInfo(
        @Body Info: VDSdata // Request body containing updated RenterInfo
    ): Call<Void>
    @GET("PKIInfo")
    // Retrieves student information based on registration number and date of birth.
    fun getPKIInfo(@Query("name") name: String,@Query("email") email: String): Call<List<VDSdata>>

}


