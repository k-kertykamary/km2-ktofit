package com.kkk.kopilot.datas.common.resourcereader

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import platform.Foundation.NSBundle
import platform.Foundation.NSError
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.stringWithContentsOfFile
import platform.darwin.NSObject
import platform.darwin.NSObjectMeta

actual class ResourceReader {
    @OptIn(BetaInteropApi::class)
    private val bundle: NSBundle = NSBundle.bundleForClass(BundleMarker)

    @OptIn(ExperimentalForeignApi::class)
    actual fun read(name: String): String {
        val (filename, type) = when (val lastPeriodIndex = name.lastIndexOf('.')) {
            0 -> {
                null to name.drop(1)
            }

            in 1..Int.MAX_VALUE -> {
                name.take(lastPeriodIndex) to name.drop(lastPeriodIndex + 1)
            }

            else -> {
                name to null
            }
        }
        val path = bundle.pathForResource(filename, type) ?: error(
            "Couldn't get path of $name (parse as : ${
                listOfNotNull(
                    filename,
                    type
                ).joinToString { "." }
            }"
        )
        return memScoped {
            val errorPtr = alloc<ObjCObjectVar<NSError?>>()
            NSString.stringWithContentsOfFile(
                path,
                encoding = NSUTF8StringEncoding,
                error = errorPtr.ptr
            ) ?: run {
                error("Couldn't load resource: $name. Error : ${errorPtr.value?.localizedDescription} - ${errorPtr.value}")
            }
        }
    }

    private class BundleMarker : NSObject() {
        companion object : NSObjectMeta()
    }
}