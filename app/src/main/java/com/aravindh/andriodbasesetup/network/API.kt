package com.aravindh.andriodbasesetup.network

import android.content.Context
import com.aravindh.andriodbasesetup.R
import com.aravindh.andriodbasesetup.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.io.InputStream
import java.security.*
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager


//OKHTTPCLIENT CONFIGURATION FOR READ/CONNECTION TIMEOUT
private val client: OkHttpClient = OkHttpClient.Builder()
    .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
    .connectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
    .build()

//MOSHI LIBRARY FOR CONVERT JOSN OBJECT INTO MODEL CLASS
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//RETROFIT CONFIGURATION
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

//API SERVICE
object API {
    val retrofitApiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }


    fun trustCert(context: Context): OkHttpClient.Builder {
        val okHttpClient = OkHttpClient.Builder()
        try {
            val ksTrust = KeyStore.getInstance("BKS")
            val instream =
                context.resources.openRawResource(R.raw.my_ca)
            ksTrust.load(instream, "secret".toCharArray())
            // TrustManager decides which certificate authorities to use.
            val tmf = TrustManagerFactory
                .getInstance(TrustManagerFactory.getDefaultAlgorithm())
            tmf.init(ksTrust)
            val sslContext =
                SSLContext.getInstance("TLS")
            sslContext.init(null, tmf.trustManagers, null)
            okHttpClient.sslSocketFactory(sslContext.socketFactory, systemDefaultTrustManager())
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: CertificateException) {
            e.printStackTrace()
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        }
        return okHttpClient
    }
}

fun initSSL(context: Context) {
    var sslContext: SSLContext? = null
    try {
        sslContext = createCertificate(
            context.resources.openRawResource(R.raw.my_ca)
        )
    } catch (e: CertificateException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: KeyStoreException) {
        e.printStackTrace()
    } catch (e: KeyManagementException) {
        e.printStackTrace()
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }

}

@Throws(
    CertificateException::class,
    IOException::class,
    KeyStoreException::class,
    KeyManagementException::class,
    NoSuchAlgorithmException::class
)
private fun createCertificate(trustedCertificateIS: InputStream): SSLContext {
    val cf =
        CertificateFactory.getInstance("X.509")
    val ca: Certificate
    ca = try {
        cf.generateCertificate(trustedCertificateIS)
    } finally {
        trustedCertificateIS.close()
    }
    // creating a KeyStore containing our trusted CAs
    val keyStoreType = KeyStore.getDefaultType()
    val keyStore = KeyStore.getInstance(keyStoreType)
    keyStore.load(null, null)
    keyStore.setCertificateEntry("ca", ca)
    // creating a TrustManager that trusts the CAs in our KeyStore
    val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
    val tmf =
        TrustManagerFactory.getInstance(tmfAlgorithm)
    tmf.init(keyStore)
    // creating an SSLSocketFactory that uses our TrustManager
    val sslContext = SSLContext.getInstance("TLS")
    sslContext.init(null, tmf.trustManagers, null)
    return sslContext
}

private fun systemDefaultTrustManager(): X509TrustManager {
    return try {
        val trustManagerFactory =
            TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(null as KeyStore?)
        val trustManagers =
            trustManagerFactory.trustManagers
        check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
            "Unexpected default trust managers:" + Arrays.toString(
                trustManagers
            )
        }
        trustManagers[0] as X509TrustManager
    } catch (e: GeneralSecurityException) {
        throw AssertionError() // The system has no TLS. Just give up.
    }
}



