package commons.ui.validation

import com.afollestad.vvalidator.assertion.Assertion
import com.afollestad.vvalidator.field.input.InputLayoutField
import com.google.android.material.textfield.TextInputLayout

class NotBlankAssertion internal constructor() : Assertion<TextInputLayout, NotBlankAssertion>() {

    override fun isValid(view: TextInputLayout) = view.editText?.text?.isNotBlank() ?: false

    override fun defaultDescription() = "cannot be empty"
}

fun InputLayoutField.isNotBlank() = assert(NotBlankAssertion())