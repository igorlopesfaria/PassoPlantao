package br.com.passoplantao.core.extensions

import android.util.Patterns

fun String?.isValidEmail(): Boolean = (this != null && Patterns.EMAIL_ADDRESS.matcher(this).matches())

fun String?.isValidPhone(): Boolean = (this != null && Patterns.PHONE.matcher(this).matches())

fun String?.isValidPassword(): Boolean = (this != null && this.length > 5)
