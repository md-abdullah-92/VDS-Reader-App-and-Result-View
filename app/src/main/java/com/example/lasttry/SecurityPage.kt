package com.example.lasttry

import java.security.PrivateKey
import javax.crypto.Cipher
import android.util.Base64;
import java.security.PublicKey


class SecurityPage {
    companion object {
        fun differentiate(data: String): Map<String, Any> {
            fun parseInfo(data: String): Map<String, String> {
                val lines = data.trim().lines()
                val parsedData = mutableMapOf<String, String>()
                for (line in lines) {
                    val parts = line.split(":", limit = 2).map { it.trim() }
                    if (parts.size == 2) {
                        parsedData[parts[0]] = parts[1]
                    } else {
                        println("Invalid line format: $line")
                    }
                }
                return parsedData
            }

            val sections = data.trim().split("\n\n")
            val result = mutableMapOf<String, Any>()
            for (section in sections) {
                val lines = section.trim().lines()
                if (lines.isNotEmpty()) {
                    val sectionType = lines[0].trim()
                    when (sectionType) {
                        "PublicKey" -> result["PublicKey"] = lines.getOrNull(1)?.trim() ?: ""
                        "HashValue" -> result["HashValue"] = lines.getOrNull(1)?.trim() ?: ""
                        else -> {
                            if (sectionType.startsWith("Info")) {
                                val infoLines = lines.subList(1, lines.size)
                                val info = parseInfo(infoLines.joinToString("\n"))
                                result["Info"] = info
                            } else {
                                println("Unknown section type: $sectionType")
                            }
                        }
                    }
                }
            }
            return result
        }

    }
}



