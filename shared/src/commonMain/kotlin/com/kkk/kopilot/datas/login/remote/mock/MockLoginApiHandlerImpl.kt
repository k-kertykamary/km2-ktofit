package com.kkk.kopilot.datas.login.remote.mock

import com.kkk.kopilot.datas.common.remote.mock.MockApiHandler
import io.ktor.http.HttpStatusCode

class MockLoginApiHandlerImpl(
    override var resourceJsonFile: String = "mock/login/response.json",
    override var httpStatusCode: HttpStatusCode = HttpStatusCode.OK
) : MockApiHandler()