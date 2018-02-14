package lu.intech.tierion.lib

object Constants {

    // Your login to connect to tierion API
    val ANCHOR_LOGIN = System.getenv("ANCHOR_LOGIN")?:"user@mail.com"

    // Your password to connect to tierion API
    val ANCHOR_PASSWORD = System.getenv("ANCHOR_PASSWORD")?:"xxxyyy123"

    // Tierion API base url
    val ANCHOR_BASE_URL = System.getenv("ANCHOR_BASE_URL")?:"https://hashapi.tierion.com"

    // Tierion API login endpoint
    val ANCHOR_LOGIN_URL = System.getenv("ANCHOR_LOGIN_URL")?:"/v1/auth/token"

    // Tierion API refresh token endpoint
    val ANCHOR_REFRESH_URL = System.getenv("ANCHOR_REFRESH_URL")?:"/v1/auth/refresh"

    // Tierion API hashitems endpoint
    val ANCHOR_HASH_URL = System.getenv("ANCHOR_HASH_URL")?:"/v1/hashitems"

    // Tierion API receipts endpoint
    val ANCHOR_RECEIPT_URL = System.getenv("ANCHOR_RECEIPT_URL")?:"/v1/receipts"

    // Tierion API block subscription endpoint
    val ANCHOR_BLOCK_SUB_URL = System.getenv("ANCHOR_BLOCK_SUB_URL")?:"/v1/blocksubscriptions"

    // Tierion Client Secret
    val ANCHOR_CLIENT_SECRET = System.getenv("ANCHOR_CLIENT_SECRET")?:"aaaaabbbbbbcccccddddeeeeffff112223344"

    // Hexadecimal characters
    val HEX_CHARS = "0123456789abcdef"
}