package com.stephenleedev.neighborandroid.domain.`interface`

/**
 * Written by StephenLeeDev on 2023/01/01.
 */

interface ClickWithPositionListener<T, INDEX> {
    fun onClick(t: T, position: INDEX)
}