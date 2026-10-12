package pe.edu.upc.talenttrack.core.util

import java.util.Locale

fun Double.toCurrency(): String = String.format(Locale.US, "$%.2f", this)