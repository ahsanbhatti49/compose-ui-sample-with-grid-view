package compose.gridview.sample.repo

import androidx.lifecycle.LiveData
import compose.gridview.sample.api.ApiServices
import compose.gridview.sample.api.model.MockedModel
import compose.gridview.sample.api.network.NetworkResource
import compose.gridview.sample.api.network.Resource
import compose.gridview.sample.app.AppExecutors
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MockedDataRepo @Inject constructor(
    private val apiServices: ApiServices
) {
    fun getMockedResponse(): LiveData<Resource<MockedModel>> {
        return object : NetworkResource<MockedModel>() {
            override fun createCall(): LiveData<Resource<MockedModel>> {
                return apiServices.getMockedData()
            }
        }.asLiveData()
    }
}