package compose.gridview.sample.api.network


/**
 * Created by ahsan on 29,November,2019
 */

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<ResultType>(
    var status: ScreenState,
    var data: ResultType? = null,
    var retrofitAPICode: Int = 0,
    var errorMessage: String? = null
) {

    companion object {
        /**
         * Creates [Resource] object with `SUCCESS` status and [data].
         * Returning object of Resource(Status.SUCCESS, data, null)
         * last value is null so passing it optionally
         *
         */
        fun <ResultType> success(data: ResultType, retrofitAPICode: Int): Resource<ResultType> =
            Resource(ScreenState.Success, data, retrofitAPICode = retrofitAPICode)

        /**
         * Creates [Resource] object with `LOADING` status to notify
         * the UI to showing loading.
         * Returning object of Resource(Status.SUCCESS, null, null)
         * last two values are null so passing them optionally
         */
        fun <ResultType> loading(): Resource<ResultType> = Resource(ScreenState.Loading)

        /**
         * Creates [Resource] object with `ERROR` status and [message].
         * Returning object of Resource(Status.ERROR, errorMessage = message)
         */
        fun <ResultType> error(message: String?, retrofitAPICode: Int): Resource<ResultType> =
            Resource(ScreenState.Error, errorMessage = message, retrofitAPICode = retrofitAPICode)

    }
}