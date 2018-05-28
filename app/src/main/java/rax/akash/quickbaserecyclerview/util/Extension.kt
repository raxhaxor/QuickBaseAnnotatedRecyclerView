package rax.akash.quickbaserecyclerview.util

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Akash Saggu(R4X) on 28-05-2018 at 19:00.
 * akashsaggu@protonmail.com
 */
infix fun ViewGroup.inflate(@LayoutRes view: Int): View {
    return LayoutInflater.from(context).inflate(view, this, false)
}