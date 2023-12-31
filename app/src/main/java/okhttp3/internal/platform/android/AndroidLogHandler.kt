/*
 * Copyright (C) 2019 Square, Inc.
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
package okhttp3.internal.platform.android

import android.util.Log
import java.util.concurrent.CopyOnWriteArraySet
import java.util.logging.Handler
import java.util.logging.Level
import java.util.logging.LogRecord
import java.util.logging.Logger
import okhttp3.OkHttpClient
import okhttp3.internal.SuppressSignatureCheck
import okhttp3.internal.concurrent.TaskRunner
import okhttp3.internal.http2.Http2
import okhttp3.internal.platform.android.AndroidLog.androidLog

private val LogRecord.androidLevel: Int
    get() = when {
        level.intValue() > Level.INFO.intValue() -> Log.WARN
        level.intValue() == Level.INFO.intValue() -> Log.INFO
        else -> Log.DEBUG
    }

object AndroidLogHandler : Handler() {
    override fun publish(record: LogRecord) {
        androidLog(record.loggerName, record.androidLevel, record.message, record.thrown)
    }

    override fun flush() {
    }

    override fun close() {
    }
}
