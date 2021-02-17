package br.com.passoplantao.core.extensions

import android.os.Build
import java.util.*

fun ByteArray.encodeToBase64() : String=
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        Base64.getEncoder().encodeToString(this)
    else
        android.util.Base64.encodeToString(this, android.util.Base64.NO_WRAP)


fun String.decodeFromBase64() : ByteArray =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            Base64.getDecoder().decode(this)
        else
            android.util.Base64.decode(this, android.util.Base64.NO_WRAP)