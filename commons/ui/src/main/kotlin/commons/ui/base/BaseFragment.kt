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

package commons.ui.base

import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Base fragment to standardize and simplify initialization for this component.
 *
 * @author Mohammad Esteki
 * @param layoutId Layout resource reference identifier.
 * @see Fragment
 */
abstract class BaseFragment<out VB, VE : Effect, VI : Intent, VS : State, VM : Model<VE, VI, VS>>(
    @LayoutRes private val layoutId: Int
) : Fragment(layoutId) {

    protected abstract val viewBinding: VB
    protected abstract val viewModel: VM

    private val jobList = ArrayList<Job>()

    /**
     * Called to Initialize view binding variables when fragment view is created.
     */
    protected abstract fun onInitViewBinding()

    /**
     * Called to process view states when fragment view is started.
     */
    protected abstract fun processViewState(viewState: VS)

    /**
     * Called to process view effects when fragment view is started.
     */
    protected abstract fun processViewEffect(viewEffect: VE)

    /**
     * Called when the Fragment is visible to the user.  This is generally
     * tied to {@link Activity#onStart() Activity.onStart} of the containing
     * Activity's lifecycle.
     */
    override fun onStart() {
        viewModel.viewState
            .onEach { processViewState(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
            .observeIn(this)
        viewModel.viewEffect
            .onEach { processViewEffect(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
            .observeIn(this)

        super.onStart()
    }

    /**
     * Called when the Fragment is no longer started.  This is generally
     * tied to {@link Activity#onStop() Activity.onStop} of the containing
     * Activity's lifecycle.
     */
    override fun onStop() {
        jobList.forEach { it.cancel() }

        super.onStop()
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param view The view returned by onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @see Fragment.onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitViewBinding()
    }

    /**
     * Add job to job list to clear all of them in onStop
     */
    protected fun Job.observeIn(fragment: BaseFragment<*, *, *, *, *>) {
        fragment.jobList.add(this)
    }

    /**
     * Return the [AppCompatActivity] this fragment is currently associated with.
     *
     * @throws IllegalStateException if not currently associated with an activity or if associated
     * only with a context.
     * @throws TypeCastException if the currently associated activity don't extend [AppCompatActivity].
     *
     * @see requireActivity
     */
    fun requireCompatActivity(): AppCompatActivity {
        requireActivity()
        val activity = requireActivity()
        if (activity is AppCompatActivity) {
            return activity
        } else {
            throw TypeCastException("Main activity should extend from 'AppCompatActivity'")
        }
    }

    /**
     * Hide navigation bar, status bar
     */
    @Suppress("DEPRECATION")
    protected fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            val controller = requireActivity().window.insetsController
            controller?.hide(WindowInsets.Type.statusBars())
        } else {
            requireActivity().window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                            // Set the content to appear under the system bars so that the
                            // content doesn't resize when the system bars hide and show.
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            // Hide the nav bar and status bar
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                    )
        }
    }

    /**
     * Shows the system bars by removing all the flags
     * except for the ones that make the content appear under the system bars.
     */
    @Suppress("DEPRECATION")
    protected fun showSystemUI() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            val controller = requireActivity().window.insetsController
            controller?.show(WindowInsets.Type.statusBars())
        } else {
            requireActivity().window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }
}
