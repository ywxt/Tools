package ywxt.tools.home.models

import android.graphics.drawable.Drawable
import android.os.Parcelable

data class ToolModel(
    var text: String, 
    var image: Drawable, 
    val action: String,
    val data: Parcelable?
)