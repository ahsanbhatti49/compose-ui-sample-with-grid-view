package compose.gridview.sample.api

import androidx.lifecycle.LiveData
import compose.gridview.sample.api.model.MockedModel
import compose.gridview.sample.api.network.Resource
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by ahsan on 07,July 2022
 * Api services to communicate with server
 */

interface ApiServices {
    @GET("v2/5df79a3a320000f0612e0115")
    fun getMockedData(): LiveData<Resource<MockedModel>>
}
