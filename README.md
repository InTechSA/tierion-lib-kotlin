# tierion-lib-kotlin

[Tierion](https://tierion.com) Hash API client library written in Kotlin

### Installation

```
$ git clone https://github.com/InTechSA/tierion-lib-kotlin.git
```

```
$ mvn install
```


### To use in your maven project

Add this into your pom.xml :

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

```xml
<dependency>
    <groupId>com.github.InTechSA</groupId>
    <artifactId>tierion-lib-kotlin</artifactId>
    <version>0.2.0</version>
</dependency>
```
### Usage

This library contains some methods for Tierion Hash API endpoints. More will come. Each function will return an object corresponding to result for each endpoint as defined in the Hash API documentation at https://tierion.com/docs/hashapi.

####HashItems

```kotlin
val data = "my awesome data"
try {
  val result = TierionLib.anchorData(data)
} catch (e:AnchorDataException){
  // handle the error
  
}

```

####BlockSubscriptions

```kotlin
val url = "http://mydomain.com/callback"
try {
  val result = TierionLib.subscribeToBlock(url)
} catch (e:SubscribeToBlockException){
  // handle the error
  
}
```

####Authenticate Tierion Request to your callback endpoint

```kotlin
// Get provided payload and header
try {
  val verified = TierionLib.verifyPayload(payload, header)
} catch (e:VerifyPayloadException){
  // handle the error
  
}
```


####Receipts

```kotlin
val id = "receipt id"
try {
  val result = TierionLib.getReceipt(id)
} catch (e:GetReceiptException){
  // handle the error
  
}
```