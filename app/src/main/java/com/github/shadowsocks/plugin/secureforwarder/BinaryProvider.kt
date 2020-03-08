package com.github.shadowsocks.plugin.secureforwarder

import android.net.Uri
import android.os.ParcelFileDescriptor
import android.util.Log
import com.github.shadowsocks.plugin.NativePluginProvider
import com.github.shadowsocks.plugin.PathProvider
import java.io.File
import java.io.FileNotFoundException

class BinaryProvider : NativePluginProvider() {
    override fun populateFiles(provider: PathProvider) {
        provider.addPath("sf", 755)
    }

    override fun getExecutable(): String {
        val exec = context!!.applicationInfo.nativeLibraryDir + "/libsf.so"
        Log.d("execPath", exec)
        Log.d("execExists", File(exec).exists().toString())
        return exec
    }

    override fun openFile(uri: Uri): ParcelFileDescriptor {
        when (uri.path) {
            "/sf" -> return ParcelFileDescriptor.open(File(getExecutable()), ParcelFileDescriptor.MODE_READ_ONLY)
            else -> throw FileNotFoundException()
        }

    }
}