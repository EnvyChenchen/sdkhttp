/*
 * Copyright (C) 2014 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package okio

import java.io.IOException
import java.io.OutputStream
import java.nio.channels.WritableByteChannel
import java.nio.charset.Charset

 interface BufferedSink : Sink, WritableByteChannel {
    /** Returns this sink's internal buffer. */
    @Deprecated(
        message = "moved to val: use getBuffer() instead",
        replaceWith = ReplaceWith(expression = "buffer"),
        level = DeprecationLevel.WARNING
    )
    fun buffer(): Buffer

     val buffer: Buffer

    @Throws(IOException::class)
     fun write(byteString: ByteString): BufferedSink

    @Throws(IOException::class)
     fun write(byteString: ByteString, offset: Int, byteCount: Int): BufferedSink

    @Throws(IOException::class)
     fun write(source: ByteArray): BufferedSink

    @Throws(IOException::class)
     fun write(source: ByteArray, offset: Int, byteCount: Int): BufferedSink

    @Throws(IOException::class)
     fun writeAll(source: Source): Long

    @Throws(IOException::class)
     fun write(source: Source, byteCount: Long): BufferedSink

    @Throws(IOException::class)
     fun writeUtf8(string: String): BufferedSink

    @Throws(IOException::class)
     fun writeUtf8(string: String, beginIndex: Int, endIndex: Int): BufferedSink

    @Throws(IOException::class)
     fun writeUtf8CodePoint(codePoint: Int): BufferedSink

    @Throws(IOException::class)
    fun writeString(string: String, charset: Charset): BufferedSink

    @Throws(IOException::class)
    fun writeString(string: String, beginIndex: Int, endIndex: Int, charset: Charset): BufferedSink

    @Throws(IOException::class)
     fun writeByte(b: Int): BufferedSink

    @Throws(IOException::class)
     fun writeShort(s: Int): BufferedSink

    @Throws(IOException::class)
     fun writeShortLe(s: Int): BufferedSink

    @Throws(IOException::class)
     fun writeInt(i: Int): BufferedSink

    @Throws(IOException::class)
     fun writeIntLe(i: Int): BufferedSink

    @Throws(IOException::class)
     fun writeLong(v: Long): BufferedSink

    @Throws(IOException::class)
     fun writeLongLe(v: Long): BufferedSink

    @Throws(IOException::class)
     fun writeDecimalLong(v: Long): BufferedSink

    @Throws(IOException::class)
     fun writeHexadecimalUnsignedLong(v: Long): BufferedSink

    @Throws(IOException::class)
     override fun flush()

    @Throws(IOException::class)
     fun emit(): BufferedSink

    @Throws(IOException::class)
     fun emitCompleteSegments(): BufferedSink

    /** Returns an output stream that writes to this sink. */
    fun outputStream(): OutputStream
}
