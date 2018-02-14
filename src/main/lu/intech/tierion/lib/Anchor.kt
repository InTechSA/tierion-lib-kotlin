package lu.intech.tierion.lib

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.fasterxml.jackson.module.kotlin.*
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.result.Result
import org.slf4j.LoggerFactory

object Anchor {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    val mapper = jacksonObjectMapper()


    private var accessToken: String? = null
    private var refreshToken: String? = null

    private fun refreshTokenRequest(): Triple<Request, Response, Result<TokenResponse, FuelError>>{
        return Fuel
                .post(Constants.ANCHOR_BASE_URL + Constants.ANCHOR_REFRESH_URL, listOf("refreshToken" to refreshToken))
                .responseObject(TokenResponseDeserializer())
    }

    private fun login(): Triple<Request, Response, Result<TokenResponse, FuelError>>{
        logger.debug("login on tierion API. URL : ${Constants.ANCHOR_BASE_URL + Constants.ANCHOR_LOGIN_URL} - Body : ")
        return Fuel
                .post(Constants.ANCHOR_BASE_URL + Constants.ANCHOR_LOGIN_URL, listOf("username" to Constants.ANCHOR_LOGIN, "password" to Constants.ANCHOR_PASSWORD))
                .responseObject(TokenResponseDeserializer())
    }

    private fun anchorRequest(hash: String): Triple<Request, Response, Result<ReceiptResponse, FuelError>>{
        return Fuel
                .post(Constants.ANCHOR_BASE_URL + Constants.ANCHOR_HASH_URL, listOf("hash" to hash))
                .header(mapOf("Authorization" to "Bearer $accessToken"))
                .responseObject(ReceiptResponseDeserializer())
    }

    private fun receiptRequest(id: String): Triple<Request, Response, Result<ReceiptObjectResponse, FuelError>>{
        return Fuel
                .get("${Constants.ANCHOR_BASE_URL}${Constants.ANCHOR_RECEIPT_URL}/$id")
                .header(mapOf("Authorization" to "Bearer $accessToken"))
                .responseObject(ReceiptObjectResponseDeserializer())
    }

    private fun blockRequest(callbackUrl: String): Triple<Request, Response, Result<BlockResponse, FuelError>>{
        return Fuel
                .post(Constants.ANCHOR_BASE_URL + Constants.ANCHOR_BLOCK_SUB_URL, listOf("callbackUrl" to callbackUrl))
                .header(mapOf("Authorization" to "Bearer $accessToken"))
                .responseObject(BlockResponseDeserializer())
    }

    private fun checkToken(){
        val result = refreshToken?.let {
            // if refreshToken is not null : refresh
            logger.debug("refreshToken is not null, go refresh")
            refreshTokenRequest().third
        } ?: run {
            // if refreshToken is null : login
            logger.debug("refreshToken is null, go login")
            login().third
        }

        val (tokenResponse, err) = result

        err?.let { logger.warn("Error when trying to logging-in or refreshing : ${err.exception.message}") }
        logger.debug("token response : $tokenResponse after refresh or login")

        accessToken = tokenResponse?.access_token
        refreshToken = tokenResponse?.refresh_token
    }

    fun subscribeToBlock(callbackUrl: String): Result<BlockResponse, FuelError>{

        checkToken()
        return blockRequest(callbackUrl).third

    }

    fun anchor(hash: String): Result<ReceiptResponse, FuelError>{

        checkToken()
        return anchorRequest(hash).third

    }

    fun getReceipt(id: String): Result<ReceiptObjectResponse, FuelError> {

        checkToken()
        return receiptRequest(id).third

    }

}