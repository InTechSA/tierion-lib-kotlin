package lu.intech.tierion.lib

import org.slf4j.LoggerFactory

object TierionLib {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * This method allows you to anchor your data into the blockchain
     * First, the method hashes your data
     * Then, it anchors this hash by calling Tierion Hash API
     * @param data your data you need to anchor into the blockchain (plain text)
     */
    fun anchorData(data: String): ReceiptResponse {
        logger.debug("Data to hash : $data")
        val hash = Hash.sha256(data)

        logger.debug("Data hash result : $hash")
        val result = Anchor.anchor(hash)
        if(result.component2() != null) {
            val error = result.component2()!!
            throw AnchorDataException(error.message, error.exception)
        }
        return result.component1()!!
    }

    /**
     * This method allows you to subscribe to block creation
     * by providing a callback URL to Tierion API
     * @param callbackUrl your callback url
     */
    fun subscribeToBlock(callbackUrl:String): BlockResponse {
        val result = Anchor.subscribeToBlock(callbackUrl)
        if(result.component2() != null) {
            val error = result.component2()!!
            throw SubscribeToBlockException(error.message, error.exception)
        }
        return result.component1()!!
    }

    /**
     * Get a Receipt by its id
     * This method needs to be called after the Tierion callback reques and
     * after the payload verification to get the receipt of the anchor you just made
     * @param id the id of the receipt
     */
    fun getReceipt(id: String): ReceiptObjectResponse {
        val result = Anchor.getReceipt(id)
        if(result.component2() != null) {
            val error = result.component2()!!
            throw GetReceiptException(error.message, error.exception)
        }
        return result.component1()!!
    }

    /**
     * Verify if the payload provided by Tierion API has same signature than header value of 'x-tierion-sig'
     * This method allows you to authenticate Tierion for your callback endpoint
     * @param payload the payload provided by Tierion Request you need to verify
     * @param headerValue the value of the 'x-tierion-sig' header containing the HMAC-SHA256 of the request
     */
    fun verifyPayload(payload: String, headerValue: String): Boolean {
        val hmac = Hash.hashMac(payload)
        return hmac?.equals(headerValue)
                ?: throw VerifyPayloadException("Error during hash mac from payload provided : $payload")
    }


}