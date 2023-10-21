package com.kkk.kopilot.core.extensions

val String.Companion.Empty: String
    get() = ""

fun String.isValidEmail(): Boolean {
    val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    return this.isNotEmpty() && this.matches(emailPattern)
}

fun String.isValidPhoneNumber(): Boolean {
    val frenchPhoneNumberPattern = Regex("^(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})\$\n")
    return this.isNotEmpty() && this.matches(frenchPhoneNumberPattern)
}