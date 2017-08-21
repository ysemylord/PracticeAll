package com.test.retrofitpractice.intercept;

import android.util.Log;

import java.io.EOFException;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class LoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request req = chain.request();
        Response response = chain.proceed(req);
        ResponseBody responseBody = response.body();

        String body_param = "";
        //Print Post Entity
        if (req.body() != null) {
            Buffer buffer = new Buffer();
            req.body().writeTo(buffer);

            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = req.body().contentType();
            if (contentType != null) {
                charset = contentType.charset(Charset.forName("UTF-8"));
            }

            if (isPlaintext(buffer)) {
                body_param = (URLDecoder.decode(buffer.readString(charset)));
            }
        }
        Log.i("req-interceptor", req.url() + "\n" + body_param);


        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            try {
                charset = contentType.charset(UTF8);
            } catch (UnsupportedCharsetException e) {
                return response;
            }
        }

        String responseStr = "";
        if (responseBody.contentLength() != 0) {
            responseStr = buffer.clone().readString(charset);
        }

        Log.i("response-interceptor", response.request().url() + "\n" + responseStr);


        return response;
    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     */
    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }
}