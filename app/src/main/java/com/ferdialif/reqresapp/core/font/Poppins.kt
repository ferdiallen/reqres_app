package com.ferdialif.reqresapp.core.font

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.ferdialif.reqresapp.R

val poppins = FontFamily(
    listOf(
        Font(R.font.regular, weight = FontWeight.Normal),
        Font(R.font.semibold, weight = FontWeight.SemiBold),
        Font(R.font.bold, weight = FontWeight.Bold),
        Font(R.font.light, weight = FontWeight.Light),
    )
)