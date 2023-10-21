package com.kkk.kopilot.core.network.api.models

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            error.parseToDefault().asCustomResponseFailure()
            return ApiErrorResponse(
                errorMessage = error.message ?: "Unknown error",
                httpStatusCode = 0
            )
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                val headers = response.headers()
                if (body == null || response.code == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body, headers)
                }
            } else {
                ApiErrorResponse(
                    errorMessage = response.description,
                    httpStatusCode = response.code
                )
            }
        }
    }
}

data class ApiSuccessResponse<T>(
    val body: T,
    val headers: Set<Map.Entry<String, List<String>>>
) : ApiResponse<T>()

/**
 * Separate class for HTTP 204 responses so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiErrorResponse<T>(
    val errorMessage: String,
    val httpStatusCode: Int
) : ApiResponse<T>()