package com.example.presentation.model

import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import android.widget.TextView

data class TransitionDisplay(
    val image: ImageView,
    val card: View,
    val name: TextView,
    val display: CharacterDetailDisplay
)

class CharacterDetailDisplay(
    val id: Int,
    val name: String,
    val image: String,
    val type: String?,
    val planet: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeString(type)
        parcel.writeString(planet)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CharacterDetailDisplay> {
        override fun createFromParcel(parcel: Parcel): CharacterDetailDisplay {
            return CharacterDetailDisplay(parcel)
        }

        override fun newArray(size: Int): Array<CharacterDetailDisplay?> {
            return arrayOfNulls(size)
        }
    }


}