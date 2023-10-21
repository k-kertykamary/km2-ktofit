package com.kkk.kopilot.core.logger

import io.github.aakira.napier.Napier
import io.ktor.client.plugins.logging.Logger

object KermitKtorLogger: Logger {
    override fun log(message: String) {
        Napier.d("Ktor - $message")
    }
}