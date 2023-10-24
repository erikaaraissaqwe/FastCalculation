package br.edu.ifsp.scl.fastcalculation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PointsResult(
    val points: Float
) : Parcelable
