package com.yyg.common.data.protocol

import android.os.Parcel
import android.os.Parcelable

/**
 * 上传图片返回的对象信息
 */
data class ImageItem(
    val originName: String? = null,
    val mediumUrl: String? = null,
    val smallUrl: String? = null,
    val url: String
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString() ?: ""
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(originName)
        writeString(mediumUrl)
        writeString(smallUrl)
        writeString(url)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ImageItem> = object : Parcelable.Creator<ImageItem> {
            override fun createFromParcel(source: Parcel): ImageItem = ImageItem(source)
            override fun newArray(size: Int): Array<ImageItem?> = arrayOfNulls(size)
        }
    }
}