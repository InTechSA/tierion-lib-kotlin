package lu.intech.tierion.lib.test

import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import io.kotlintest.specs.StringSpec
import lu.intech.tierion.lib.Constants
import lu.intech.tierion.lib.Hash
import lu.intech.tierion.lib.TierionLib

class TierionLibTest : StringSpec() {
    init {
        "Hash.sha256 should return the good sha-256 digest of a string" {
            val data = "My Awesome Data"
            Hash.sha256(data) shouldBe "80ae0e172032f270c32c0bb193b3f26c3d73a47c14a34d17eb1573800c73dccf"
        }

        "Hash.hashMac should return the good HMAC" {
            val body = "My awesome body"
            Hash.hashMac(body) shouldBe "19a246ace9e98f8b238459e9f5cd46e4bfa87c0d1c4823aac0d8fd69663f3112"
        }

        "TierionLib.verifyPayload should return true if the payload has a good signature" {
            val payload = "My awesome body"
            TierionLib.verifyPayload(payload,"19a246ace9e98f8b238459e9f5cd46e4bfa87c0d1c4823aac0d8fd69663f3112") shouldBe true
        }
    }
}