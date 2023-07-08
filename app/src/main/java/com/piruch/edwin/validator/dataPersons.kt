package com.piruch.edwin.validator

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class dataPersons(
    val nombre: String,
    val asunto: String,
    val cuerpo: String,
    val imagen: String
) : Parcelable

