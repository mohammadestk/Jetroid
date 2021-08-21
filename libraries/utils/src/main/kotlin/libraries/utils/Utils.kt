/*
 * Copyright 2021 MohammadEsteki.ir
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libraries.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.io.FileDescriptor
import java.io.IOException
import kotlin.system.exitProcess

/**
 * Utility class for common functions
 *
 * @author Mohammad Esteki
 */
object Utils {

    /**
     * Create bitmap from uri address
     *
     * @param context App context
     * @param uri Source bitmap address
     *
     * @return Created bitmap
     */
    fun createBitmapFromUri(context: Context, uri: Uri): Bitmap? = try {
        val parcelFileDescriptor = context.contentResolver.openFileDescriptor(uri, "r")
        val fileDescriptor: FileDescriptor = parcelFileDescriptor!!.fileDescriptor
        val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor.close()

        image
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }

    /**
     * Hide keyboard
     *
     * @param context
     */
    fun hideKeyboard(context: Context, view: View) {
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        // Find the currently focused view, so we can grab the correct window token from it.
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * Restart application. this method kill app process and destroy all variables to prevent
     * memory leak
     *
     * @param activity
     */
    fun restartApplication(activity: Activity) {
        // Systems at 29/Q and later don't allow relaunch, but System.exit(0) on
        // all supported systems will relaunch ... but by killing the process, then
        // restarting the process with the back stack intact. We must make sure that
        // the launch activity is the only thing in the back stack before exiting.
        val pm = activity.packageManager
        val intent = pm.getLaunchIntentForPackage(activity.packageName)
        activity.finishAffinity() // Finishes all activities.
        activity.startActivity(intent) // Start the launch activity
        exitProcess(0) // System finishes and automatically relaunches us.
    }
}
