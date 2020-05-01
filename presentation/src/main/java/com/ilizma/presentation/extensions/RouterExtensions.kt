package com.ilizma.presentation.extensions

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.ilizma.presentation.ui.base.BaseActivity
import java.io.IOException
import java.io.Serializable

//region BaseActivity extensions

inline fun <reified T : Parcelable> Intent.setParcelableParam(obj: T, tag: String = "") {
    putExtra(T::class.java.name + tag, obj)
}

inline fun <reified T : Parcelable> BaseActivity.getParcelableParam(
    errorMessage: String = "${this::class.java.simpleName} needs a ${T::class.java.simpleName} to populate",
    data: Intent = intent,
    tag: String = ""
): T {
    if (data.extras == null) {
        throw IOException(errorMessage)
    } else {
        return data.extras?.getParcelable<Parcelable>(T::class.java.name + tag) as T
    }
}

inline fun <reified T : Parcelable> Intent.getParcelableParam(tag: String = ""): T =
    getParcelableExtra(T::class.java.name + tag) as T

inline fun <reified T : Serializable> Intent.setSerializableParam(obj: T, tag: String = "") {
    putExtra(T::class.java.name + tag, obj)
}

inline fun <reified T : Serializable> BaseActivity.getSerializableParam(
    errorMessage: String = "${this::class.java.simpleName} needs a ${T::class.java.simpleName} to populate",
    data: Intent = intent,
    tag: String = ""
): T =
    (data.extras?.getSerializable(T::class.java.name + tag) ?: throw IOException(errorMessage)) as T

inline fun <reified T : Serializable> Intent.getSerializableParam(
    errorMessage: String = "${this::class.java.simpleName} needs a ${T::class.java.simpleName} to populate",
    tag: String = ""
): T =
    (getSerializableExtra(T::class.java.name + tag) ?: throw IOException(errorMessage)) as T

//endregion

//region BaseFragment extensions

inline fun <reified T : Parcelable> Fragment.setParcelableParam(obj: T, tag: String = "") {
    if (arguments == null) {
        arguments = Bundle()
    }
    arguments?.putParcelable(T::class.java.name + tag, obj)
}

inline fun <reified T : Parcelable> Fragment.getParcelableParam(
    errorMessage: String = "${this::class.java.simpleName} needs a ${T::class.java.simpleName} to populate",
    tag: String = ""
): T {
    if (arguments == null) {
        throw IOException(errorMessage)
    } else {
        return arguments?.getParcelable<Parcelable>(T::class.java.name + tag) as T
    }
}

inline fun <reified T : Serializable> Fragment.setSerializableParam(obj: T, tag: String = "") {
    if (arguments == null) {
        arguments = Bundle()
    }
    arguments?.putSerializable(T::class.java.name + tag, obj)
}

inline fun <reified T : Serializable> Fragment.getSerializableParam(
    errorMessage: String = "${this::class.java.simpleName} needs a ${T::class.java.simpleName} to populate",
    tag: String = ""
): T =
    (arguments?.getSerializable(T::class.java.name + tag) ?: throw IOException(errorMessage)) as T

inline fun <reified T : Serializable> Fragment.getNullableSerializableParam(tag: String = ""): T? =
    arguments?.getSerializable(T::class.java.name + tag) as T?

inline fun <reified T : Parcelable> Fragment.getNullableParcelableParam(tag: String = ""): T? =
    arguments?.getParcelable(T::class.java.name + tag) as T?

//endregion