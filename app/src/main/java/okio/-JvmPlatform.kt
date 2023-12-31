/*
 * Copyright (C) 2018 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package okio

internal  fun ByteArray.toUtf8String(): String = String(this, Charsets.UTF_8)

internal  fun String.asUtf8ToByteArray(): ByteArray = toByteArray(Charsets.UTF_8)

// TODO remove if https://youtrack.jetbrains.com/issue/KT-20641 provides a better solution
 typealias ArrayIndexOutOfBoundsException = java.lang.ArrayIndexOutOfBoundsException

internal  inline fun <R> synchronized(lock: Any, block: () -> R): R {
    return kotlin.synchronized(lock, block)
}

 typealias IOException = java.io.IOException

 typealias ProtocolException = java.net.ProtocolException

 typealias EOFException = java.io.EOFException

 typealias FileNotFoundException = java.io.FileNotFoundException

 typealias Closeable = java.io.Closeable
