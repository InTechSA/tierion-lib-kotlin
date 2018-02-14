package lu.intech.tierion.lib

import org.slf4j.LoggerFactory
import java.security.InvalidKeyException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.crypto.spec.SecretKeySpec
import javax.crypto.Mac


object Hash {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun sha512(input: String) = hashString("SHA-512", input)

    fun sha256(input: String) = hashString("SHA-256", input)

    fun sha1(input: String) = hashString("SHA-1", input)

    /**
     * Supported algorithms on Android:
     *
     * Algorithm	Supported API Levels
     * MD5          1+
     * SHA-1	    1+
     * SHA-224	    1-8,22+
     * SHA-256	    1+
     * SHA-384	    1+
     * SHA-512	    1+
     */
    private fun hashString(type: String, input: String): String {

        val bytes = MessageDigest
                .getInstance(type)
                .digest(input.toByteArray())
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            result.append(Constants.HEX_CHARS[i shr 4 and 0x0f])
            result.append(Constants.HEX_CHARS[i and 0x0f])
        }

        return result.toString()
    }



    fun hashMac(data:String): String?{
        try {
            val algorithm = "HmacSHA256"
            val key = Constants.ANCHOR_CLIENT_SECRET

            val hasher = Mac.getInstance(algorithm)
            hasher.init(SecretKeySpec(key.toByteArray(), algorithm))

            val hash = hasher.doFinal(data.toByteArray())

            val result = StringBuilder(hash.size * 2)

            hash.forEach {
                val i = it.toInt()
                result.append(Constants.HEX_CHARS[i shr 4 and 0x0f])
                result.append(Constants.HEX_CHARS[i and 0x0f])
            }

            return result.toString()

        } catch (e: NoSuchAlgorithmException) {

            logger.warn("NoSuchAlgorithmException Exception during hashMac with data : $data")
            logger.warn(e.message)
            return null

        } catch (e: InvalidKeyException) {

            logger.warn("InvalidKeyException Exception during hashMac with data : $data")
            logger.warn(e.message)
            return null

        }

    }
}

