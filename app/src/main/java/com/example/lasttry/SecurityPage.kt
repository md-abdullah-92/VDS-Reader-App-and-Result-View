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
       // var publicKey="MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAsSm7tserI8awN0nItgpHsRcnZ9JXxfcmfJzumsEKxDwlqqiirK475c52I1St8uVfX3Vo8Z276Q/4qH+bGQkVyMrvxoCzGVFq3aOq42HPntLmg3IGFGZMaK4CEsiuty07MUUwMVtyKQSTGm0wqyFfz/FEn7S1eXYJ1EnF7J+QYKEPCqaet9PVW8wLqKSJCdynR+0Pd58FIED1/K0DwG3sjLWZ0U78LBXR1iz2rBhD4jlMb7Exrgvw3eGXnqIJWDunConsMD160YqU6ZzsIrDDmAoWj5dgiHd85ArDJtbtk540Q5aOI/zyUPNB9nnwllgcVGx3A6MRmKZUWLAFiuy3HzzOMjKmc21u9O5o+XpWyBVauGtJPk6haFzDSBHHJT2VSqN2jtq4wvAv/TGTo5Cj5aG1eHRghZySxRUYzSgIQSiJ2DrHwE6GNidjGfoXHnlX3UInLRA+8G/4hr4wRO8uw46CC1u1vc1TwzkOl6T47ocYADwZY8BBdvoRFMoH+NjAQxjwYVzh6nYUJE4r261OfY0gvmXbPINswBFIfLGDqcqXNYOMXA8Haqjwum6vujAeAoxDvOG3ohqaur5jyFZOT6rn9B636Kuz6v0gFnHaUYNTvyYJELhTl36VmbHmjtMCs+eXiVurCfggIW5PLP4S8kmNNMQnRPtjg1Gy06JdRtsCAwEAAQ=="
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
                        //"PublicKey" -> result["PublicKey"] = lines.getOrNull(1)?.trim() ?: ""
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



