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

package commons.ui.extention

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pd.chocobar.ChocoBar

/**
 * Generic view model provider.
 *
 * @param key The key to use to identify the ViewModel.
 * @param factory Function creates a new instance of the ViewModel.
 * @return A ViewModel that is an instance of the given type [VM].
 * @see ViewModel
 */
@Suppress("UNCHECKED_CAST")
fun <VM : ViewModel> Fragment.viewModel(
    key: String? = null,
    factory: () -> VM
): VM {
    val factoryViewModel = factory()
    val viewModelProviderFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return factoryViewModel as T
        }
    }

    return if (key != null) {
        ViewModelProvider(this, viewModelProviderFactory).get(key, factoryViewModel::class.java)
    } else {
        ViewModelProvider(this, viewModelProviderFactory).get(factoryViewModel::class.java)
    }
}


/**
 * Show error snack with specific message.
 *
 * @param message Can be string or string resource
 */
fun Fragment.showErrorSnack(message: Any) {
    val builder = ChocoBar.builder()
        .setView(requireView())
        .orange()
    when (message) {
        is String -> builder.setText(message)
        is Int -> builder.setText(message)
    }
    builder.show()
}

/**
 * Show error toast with specific message.
 *
 * @param message Can be string or string resource
 */
fun Fragment.showToast(message: Any) {
    when (message) {
        is String -> Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        is Int -> Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}

/**
 * Show success snack with specific message.
 *
 * @param message Can be string or string resource
 */
fun Fragment.showSuccessSnack(message: Any) {
    val builder = ChocoBar.builder()
        .setView(requireView())
        .green()
    when (message) {
        is String -> builder.setText(message)
        is Int -> builder.setText(message)
    }
    builder.show()
}
