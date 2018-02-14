package lu.intech.tierion.lib

class AnchorDataException(
        override val message: String?,
        override val cause: Throwable?
):Throwable(message, cause)

class SubscribeToBlockException(
        override val message: String?,
        override val cause: Throwable?
):Throwable(message, cause)

class GetReceiptException(
      override val message: String?,
      override val cause: Throwable?
):Throwable(message, cause)

class VerifyPayloadException(
        override val message: String?,
        override val cause: Throwable? = null
):Throwable(message, cause)