package lu.intech.tierion.lib

import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.core.ResponseDeserializable

data class TokenResponse(val access_token: String = "", val expires_in: Int = 0, val refresh_token: String = "")

class TokenResponseDeserializer : ResponseDeserializable<TokenResponse> {
    override fun deserialize(content: String) = Anchor.mapper.readValue<TokenResponse>(content)
}

data class ReceiptResponse(val receiptId: String = "", val timestamp: Long = 0)

class ReceiptResponseDeserializer : ResponseDeserializable<ReceiptResponse> {
    override fun deserialize(content: String) = Anchor.mapper.readValue<ReceiptResponse>(content)
}

data class ReceiptObjectResponse(val receipt: String = "")

class ReceiptObjectResponseDeserializer : ResponseDeserializable<ReceiptObjectResponse> {
    override fun deserialize(content: String) = Anchor.mapper.readValue<ReceiptObjectResponse>(content)
}

data class BlockResponse(val id: String = "")

class BlockResponseDeserializer : ResponseDeserializable<BlockResponse> {
    override fun deserialize(content: String) = Anchor.mapper.readValue<BlockResponse>(content)
}