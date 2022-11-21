package io.github.lexadiky.pdx.ui.uikit.image.transformation

import android.graphics.Bitmap
import coil.size.Size
import coil.transform.Transformation
import java.util.Arrays

object CropTransparentTransformation : Transformation {

    override val cacheKey: String = "CropTransparentTransformation"

    override suspend fun transform(input: Bitmap, size: Size): Bitmap {
        return crop(input)
    }

    fun crop(bitmap: Bitmap): Bitmap {
        val height = bitmap.height
        val width = bitmap.width
        var empty = IntArray(width)
        var buffer = IntArray(width)
        Arrays.fill(empty, 0)
        var top = 0
        var left = 0
        var botton = height
        var right = width
        for (y in 0 until height) {
            bitmap.getPixels(buffer, 0, width, 0, y, width, 1)
            if (!empty.contentEquals(buffer)) {
                top = y
                break
            }
        }
        for (y in height - 1 downTo top + 1) {
            bitmap.getPixels(buffer, 0, width, 0, y, width, 1)
            if (!empty.contentEquals(buffer)) {
                botton = y
                break
            }
        }
        val bufferSize = botton - top + 1
        empty = IntArray(bufferSize)
        buffer = IntArray(bufferSize)
        Arrays.fill(empty, 0)
        for (x in 0 until width) {
            bitmap.getPixels(buffer, 0, 1, x, top + 1, 1, bufferSize)
            if (!empty.contentEquals(buffer)) {
                left = x
                break
            }
        }
        Arrays.fill(empty, 0)
        for (x in width - 1 downTo left + 1) {
            bitmap.getPixels(buffer, 0, 1, x, top + 1, 1, bufferSize)
            if (!empty.contentEquals(buffer)) {
                right = x
                break
            }
        }
        return Bitmap.createBitmap(bitmap, left, top, right - left, botton - top)
    }
}