package com.elite.medical.utils



private fun String.Companion.validateEmail(email: String): String? {

    val emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"

    if (email.isEmpty())
        return "please enter your email."
    else if (!email.matches(emailRegex.toRegex()))
        return "Please enter valid email"

    return null
}



class InputValidation {


    companion object {


        fun emailValidation(email: String): String {

            val emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"

            if (email.isEmpty())
                return "please enter your email."
            else if (!email.matches(emailRegex.toRegex()))
                return "Please enter valid email"

            return ""
        }


        fun passwordValidation(password: String): String {
            if (password.isEmpty())
                return "password can't be empty."
            return ""
        }

        fun isFieldEmpty(field: String): String {

            return if (field.isEmpty())
                "This field can't be empty."
            else
                ""

        }
    }


}