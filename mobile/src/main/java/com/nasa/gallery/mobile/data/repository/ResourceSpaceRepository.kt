package com.nasa.gallery.mobile.data.repository
import android.content.Context
import android.util.TypedValue
import com.google.gson.Gson
import com.nasa.gallery.mobile.data.model.SpaceImage
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

class ResourceSpaceRepository @Inject constructor(
    private val context : Context,
    private val gson: Gson
) : SpaceRepository{
    override suspend fun getImages(): List<SpaceImage> {
       val json = context.assets.open("data_source/gallery.json")
        return gson.fromJson(json, TypeToken<List<SpaceImage>>(){}.)
    }

    @Throws(IOException::class)
    private fun readTextFromInputStream(inputStream: InputStream?): String {
        val stringBuilder = StringBuilder()
        inputStream?.use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).use { reader ->
                var line: String? = reader.readLine()
                while (line != null) {
                    stringBuilder.append(line)
                    line = reader.readLine()
                }
            }
        }
        return stringBuilder.toString()
    }

}