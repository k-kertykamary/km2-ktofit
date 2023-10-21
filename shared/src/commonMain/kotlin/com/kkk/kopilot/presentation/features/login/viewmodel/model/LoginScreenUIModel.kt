package com.kkk.kopilot.presentation.features.login.viewmodel.model

import com.kkk.kopilot.core.extensions.Empty
import dev.icerock.moko.resources.StringResource

/**
 * Cette classe représente le modèle de l'interface utilisateur de l'écran de connexion.
 *
 * @param emailOrPhoneNumber L'adresse e-mail ou le numéro de téléphone saisis par l'utilisateur.
 * @param password Le mot de passe saisi par l'utilisateur.
 * @param isConnecting Indique si la connexion est en cours (par défaut, false).
 * @param error Une ressource de chaîne de caractères contenant un message d'erreur en cas de problème
 *              (par défaut, null).
 */
data class LoginScreenUIModel (
    val emailOrPhoneNumber: String = String.Empty,
    val password: String = String.Empty,
    val isConnecting: Boolean = false,
    val error: StringResource? = null
)