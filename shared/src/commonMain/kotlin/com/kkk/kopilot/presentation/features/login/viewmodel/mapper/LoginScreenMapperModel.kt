package com.kkk.kopilot.presentation.features.login.viewmodel.mapper

import com.kkk.kopilot.MR
import com.kkk.kopilot.core.extensions.isValidEmail
import com.kkk.kopilot.core.extensions.isValidPhoneNumber
import com.kkk.kopilot.datas.common.remote.api.model.DefaultFailureCauseModel
import com.kkk.kopilot.presentation.features.login.viewmodel.model.LoginScreenUIModel
import com.kkk.kopilot.presentation.features.login.viewmodel.model.LoginScreenViewModel

class LoginScreenMapperModel {

    /**
     * Cette fonction prend en entrée une adresse e-mail ou un numéro de téléphone et un mot de passe.
     * Elle évalue ces entrées et renvoie l'état de la vue du modèle [LoginScreenViewModel] en conséquence.
     * Si l'adresse est une adresse e-mail valide ou un numéro de téléphone valide, l'état sera "Connecting"
     * indiquant que la connexion est en cours. Sinon, en cas d'erreur, elle renvoie un état "Error" avec
     * un message d'erreur approprié en fonction de la validation des entrées.
     *
     * @param emailOrPhoneNumber L'adresse e-mail ou le numéro de téléphone saisis par l'utilisateur.
     * @param password Le mot de passe saisi par l'utilisateur.
     * @return L'état de la vue du modèle [LoginScreenViewModel] en fonction des entrées.
     */
    fun map(emailOrPhoneNumber: String, password: String): LoginScreenViewModel.State {
        when {
            emailOrPhoneNumber.isValidEmail() || emailOrPhoneNumber.isValidPhoneNumber() -> {
                return LoginScreenViewModel.State.Connecting(
                    LoginScreenUIModel(
                        emailOrPhoneNumber = emailOrPhoneNumber,
                        password = password,
                        isConnecting = true
                    )
                )
            }

            else -> {
                val error = when {
                    emailOrPhoneNumber.isBlank() -> {
                        MR.strings.error_login_email_or_phone_number_blank
                    }

                    password.isBlank() -> {
                        MR.strings.error_login_password_blank
                    }

                    !emailOrPhoneNumber.isValidEmail() -> {
                        MR.strings.error_login_email_not_valid
                    }

                    !emailOrPhoneNumber.isValidPhoneNumber() -> {
                        MR.strings.error_login_phone_number_not_valid
                    }

                    else -> {
                        MR.strings.error_default
                    }
                }
                return LoginScreenViewModel.State.Error(
                    LoginScreenUIModel(
                        emailOrPhoneNumber = emailOrPhoneNumber,
                        password = password,
                        isConnecting = false,
                        error = error
                    )
                )
            }
        }
    }


    /**
     * Cette fonction prend en entrée une adresse e-mail ou un numéro de téléphone, un mot de passe,
     * et une cause d'erreur [DefaultFailureCauseModel]. Elle génère un état d'erreur pour la vue du modèle
     * [LoginScreenViewModel] en fonction de la cause d'erreur fournie. Elle associe la cause à un message
     * d'erreur lisible et renvoie un état d'erreur avec ce message.
     *
     * @param emailOrPhoneNumber L'adresse e-mail ou le numéro de téléphone saisis par l'utilisateur.
     * @param password Le mot de passe saisi par l'utilisateur.
     * @param cause La cause d'erreur, qui peut être de type "Unknown," "InternetError," ou "NoInternet."
     * @return L'état d'erreur de la vue du modèle [LoginScreenViewModel] avec un message associé à la cause.
     */
    fun mapError(
        emailOrPhoneNumber: String,
        password: String,
        cause: DefaultFailureCauseModel
    ): LoginScreenViewModel.State {

        val readableErrorString = when(cause) {
            is DefaultFailureCauseModel.Unknown -> MR.strings.error_default
            is DefaultFailureCauseModel.InternetError -> MR.strings.error_internet
            is DefaultFailureCauseModel.NoInternet -> MR.strings.error_no_internet
        }

        return LoginScreenViewModel.State.Error(
            LoginScreenUIModel(
                emailOrPhoneNumber = emailOrPhoneNumber,
                password = password,
                isConnecting = false,
                error = readableErrorString
            )
        )
    }
}