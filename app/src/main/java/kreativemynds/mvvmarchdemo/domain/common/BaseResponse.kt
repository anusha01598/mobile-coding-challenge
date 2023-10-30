package kreativemynds.mvvmarchdemo.domain.common

//network related operations
sealed class BaseResponse<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : BaseResponse<T>(data)
    class Error<T>(message: String, data: T? = null) : BaseResponse<T>(data, message)
    class Loading<T>(data: T? = null) : BaseResponse<T>(data)
}
