/*
 * Copyright 2021 MohammadEsteki.ir
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libraries.utils.crypto

import android.util.Base64
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * Helper class for cryptography
 *
 * @author Mohammad Esteki
 */
class CryptoHelper {
    private val keySize = 128

    companion object {
        private var ivspec: IvParameterSpec? = null
        private var keyspec: SecretKeySpec? = null
        private var cipher: Cipher? = null
    }

    init {
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            val iv = ByteArray(cipher!!.blockSize)
            val sr = SecureRandom.getInstance("SHA1PRNG")
            sr.nextBytes(iv)
            ivspec = IvParameterSpec(iv)
            keyspec = SecretKeySpec(rawKey, "AES")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // 192 and 256 bits may not be available
    @get:Throws(Exception::class)
    private val rawKey: ByteArray
        get() {
            val kgen = KeyGenerator.getInstance("AES")
            val sr = SecureRandom.getInstance("SHA1PRNG")
            sr.setSeed(Base64.decode("Q0BuVUJyZUBrMHVyQzBkZSE/", Base64.DEFAULT))
            kgen.init(keySize, sr) // 192 and 256 bits may not be available
            val skey = kgen.generateKey()
            return skey.encoded
        }

    /**
     * Encrypt plain text and convert it to byte
     *
     * @param plain input text
     */
    fun encrypt(plain: String): ByteArray? = try {
        cipher!!.init(Cipher.ENCRYPT_MODE, keyspec, ivspec)
        cipher!!.doFinal(plain.toByteArray())
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    /**
     * Decrypt byte array and convert it to string
     *
     * @param encoded encrypted byte array
     */
    fun decrypt(encoded: ByteArray?): String? = try {
        cipher!!.init(Cipher.DECRYPT_MODE, keyspec, ivspec)
        String(cipher!!.doFinal(encoded))
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
