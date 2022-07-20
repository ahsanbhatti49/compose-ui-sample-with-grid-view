package compose.gridview.sample.api.network


import androidx.lifecycle.LiveData
import compose.gridview.sample.utils.ApiErrorHandling
import retrofit2.*
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean


/**
 * Created by ahsan on 29,November,2019
 */

/**
 * A Retrofit adapter that converts the Call into a LiveData of ApiResponse.
 * @param <R>
 */
class LiveDataCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, LiveData<Resource<R>>> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): LiveData<Resource<R>> {
        return object : LiveData<Resource<R>>() {
            var started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(response.toResource())
                        }

                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            postValue(
                                Resource.error(//this will be passed to ui in later commits
                                    ApiErrorHandling.error(
                                        throwable,
                                        call.isCanceled
                                    ), if (throwable is HttpException) throwable.code() else 0
                                )
                            )
                        }
                    })
                }
            }
        }
    }
}
