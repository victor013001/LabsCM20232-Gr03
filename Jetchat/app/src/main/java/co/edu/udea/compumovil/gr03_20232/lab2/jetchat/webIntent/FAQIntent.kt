package co.edu.udea.compumovil.gr03_20232.lab2.jetchat.webIntent

import android.content.Intent
import android.net.Uri


val FAQIntent = "https://developer.android.com/jetpack/compose/faq"
val intent = Intent(Intent.ACTION_VIEW, Uri.parse(FAQIntent))

