package com.ilizma.player.framework

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream
import androidx.core.graphics.createBitmap

fun getArtworkData(context: Context, drawableResId: Int): ByteArray? {
    val drawable = ContextCompat.getDrawable(context, drawableResId) ?: return null
    val width = if (drawable.intrinsicWidth > 0) drawable.intrinsicWidth else 512
    val height = if (drawable.intrinsicHeight > 0) drawable.intrinsicHeight else 512
    val bitmap = createBitmap(width, height)
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return stream.toByteArray()
}
