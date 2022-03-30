package com.mkvats.same_string_same_color

import android.graphics.Color
import java.util.Random


fun colorFromStringSafe(inputString: String): Int {
    //String to Hex
    var hexColor = 0
    inputString.forEach {
        hexColor = it.code + ((hexColor shl 5) - hexColor)
    }
    //Hex to color
    val result = (hexColor shr 16 and 0xFF).toString(16) +
            (hexColor shr 8 and 0xFF).toString(16) +
            (hexColor and 0xFF).toString(16)

    //Returning the color if got it
    return Color.parseColor("#$result")
}

fun colorFromString(inputString: String): Int {
    var hexColor = 0
    return try {
        //String to Hex
        inputString.forEach {
            hexColor = it.code + ((hexColor shl 5) - hexColor)
        }
        //Hex to color
        var result = (hexColor shr 16 and 0xFF).toString(16) +
                (hexColor shr 8 and 0xFF).toString(16) +
                (hexColor and 0xFF).toString(16)


        //Fix the below the we can return better pretty color
        //Currently sometimes the code gets below exception
        //java.lang.IllegalArgumentException: Unexpected hex string: "some_random_color_string"
        //Color to specific color
        val num = result.toInt(16)
        val valueR = num shr 16 - 30
        val valueG = (num shr 8 and 0x00FF) - 30
        val valueB = (num and 0x0000FF) - 30
        //RGB Values to pretty color
        try {
            val finalPrettyColor = (0x1000000 +
                    (if (valueR < 255) if (valueR < 1) 0 else valueR else 255) * 0x10000 +
                    (if (valueG < 255) if (valueG < 1) 0 else valueG else 255) * 0x100 +
                    if (valueB < 255) if (valueB < 1) 0 else valueB else 255
                    ).toString(16).slice(IntRange(1, 1))

            result = finalPrettyColor
        }catch (_:Exception){}

        //Returning the color if got it
        Color.parseColor("#$result")

    } catch (exc: Exception) {
        Color.argb(235, Random().nextInt(256), Random().nextInt(256), Random().nextInt(256))
    }
}