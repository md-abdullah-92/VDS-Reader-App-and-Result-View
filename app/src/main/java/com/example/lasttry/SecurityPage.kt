package com.example.lasttry

import android.content.ContentValues.TAG
import android.content.Context
import java.security.PrivateKey
import javax.crypto.Cipher
import android.util.Base64;
import android.util.Log
import android.widget.Toast
import java.security.PublicKey


class SecurityPage {
    companion object {
        fun decrypt(encryptedText: String, publicKey: PublicKey): String? {
            try {
                val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
                cipher.init(Cipher.DECRYPT_MODE, publicKey)
                val decryptedBytes = cipher.doFinal(Base64.decode(encryptedText, Base64.DEFAULT))
                return String(decryptedBytes)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
        fun differentiate(data: String,toastContext:Context): Map<String, Any> {
            fun parseInfo(data: String): Map<String, String> {
                val lines = data.trim().lines()
                val parsedData = mutableMapOf<String, String>()
                for (line in lines) {
                    val parts = line.split(":", limit = 2).map { it.trim() }
                    if (parts.size == 2) {
                        parsedData[parts[0]] = parts[1]
                    } else {
                        Toast.makeText(toastContext,"Invalid line format", Toast.LENGTH_LONG).show()
                        return parsedData
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
                                Toast.makeText(toastContext,"Unknown section type", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            }
            return result
        }

    }
}



