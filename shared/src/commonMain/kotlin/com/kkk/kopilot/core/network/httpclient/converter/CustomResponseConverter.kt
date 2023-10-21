package com.kkk.kopilot.core.network.httpclient.converter

import com.kkk.kopilot.core.network.api.mappers.ApiResponseMappers
import com.kkk.kopilot.core.network.api.mappers.toCommonResponse
import com.kkk.kopilot.core.network.api.models.NetworkResponse
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.Converter
import de.jensklingenberg.ktorfit.internal.TypeData
import io.ktor.client.statement.HttpResponse

class CustomResponseConverter : Converter.Factory {
    override fun suspendResponseConverter(
        typeData: TypeData,
        ktorfit: Ktorfit
    ): Converter.SuspendResponseConverter<HttpResponse, *>? {
        if (typeData.typeInfo.type == NetworkResponse::class) {
            return object : Converter.SuspendResponseConverter<HttpResponse, Any> {
                override suspend fun convert(response: HttpResponse): Any {
                    return try {
                        ApiResponseMappers.create(response.toCommonResponse(typeData.typeArgs.first().typeInfo))
                    } catch (throwable: Throwable) {
                        ApiResponseMappers.create<Any>(throwable)
                    }
                }
            }
        }
        return null
    }

}