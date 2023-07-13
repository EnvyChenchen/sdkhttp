/*
 * Copyright (C) 2020 Square, Inc.
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

import okio.internal.commonCompareTo
import okio.internal.commonEquals
import okio.internal.commonHashCode
import okio.internal.commonIsAbsolute
import okio.internal.commonIsRelative
import okio.internal.commonIsRoot
import okio.internal.commonName
import okio.internal.commonNameBytes
import okio.internal.commonNormalized
import okio.internal.commonParent
import okio.internal.commonRelativeTo
import okio.internal.commonResolve
import okio.internal.commonRoot
import okio.internal.commonSegments
import okio.internal.commonSegmentsBytes
import okio.internal.commonToPath
import okio.internal.commonToString
import okio.internal.commonVolumeLetter
import java.io.File
import java.nio.file.Paths
import java.nio.file.Path as NioPath

 class Path internal  constructor(
    internal  val bytes: ByteString
) : Comparable<Path> {
     val root: Path?
        get() = commonRoot()

     val segments: List<String>
        get() = commonSegments()

     val segmentsBytes: List<ByteString>
        get() = commonSegmentsBytes()

     val isAbsolute: Boolean
        get() = commonIsAbsolute()

     val isRelative: Boolean
        get() = commonIsRelative()

    @get:JvmName("volumeLetter")
     val volumeLetter: Char?
        get() = commonVolumeLetter()

    @get:JvmName("nameBytes")
     val nameBytes: ByteString
        get() = commonNameBytes()

    @get:JvmName("name")
     val name: String
        get() = commonName()

    @get:JvmName("parent")
     val parent: Path?
        get() = commonParent()

     val isRoot: Boolean
        get() = commonIsRoot()

    @JvmName("resolve")
     operator fun div(child: String): Path = commonResolve(child, normalize = false)

    @JvmName("resolve")
     operator fun div(child: ByteString): Path = commonResolve(child, normalize = false)

    @JvmName("resolve")
     operator fun div(child: Path): Path = commonResolve(child, normalize = false)

     fun resolve(child: String, normalize: Boolean): Path =
        commonResolve(child, normalize = normalize)

     fun resolve(child: ByteString, normalize: Boolean): Path =
        commonResolve(child, normalize = normalize)

     fun resolve(child: Path, normalize: Boolean): Path =
        commonResolve(child = child, normalize = normalize)

     fun relativeTo(other: Path): Path = commonRelativeTo(other)

     fun normalized(): Path = commonNormalized()

    fun toFile(): File = File(toString())

    fun toNioPath(): NioPath = Paths.get(toString())

     override fun compareTo(other: Path): Int = commonCompareTo(other)

     override fun equals(other: Any?): Boolean = commonEquals(other)

     override fun hashCode() = commonHashCode()

     override fun toString() = commonToString()

     companion object {
        @JvmField
         val DIRECTORY_SEPARATOR: String = File.separator

        @JvmName("get") @JvmStatic @JvmOverloads
         fun String.toPath(normalize: Boolean): Path = commonToPath(normalize)

        @JvmName("get") @JvmStatic @JvmOverloads
        fun File.toOkioPath(normalize: Boolean = false): Path = toString().toPath(normalize)

        @JvmName("get") @JvmStatic @JvmOverloads
        fun NioPath.toOkioPath(normalize: Boolean = false): Path = toString().toPath(normalize)
    }
}
