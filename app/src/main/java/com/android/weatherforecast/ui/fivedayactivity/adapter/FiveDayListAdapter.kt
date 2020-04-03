/*
 * MIT License
 *
 * Copyright (c) 2020 Shreyas Patil
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.android.weatherforecast.ui.fivedayactivity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.weatherforecast.databinding.ItemFiveDayBinding
import com.android.weatherforecast.models.FiveDaysWeather
import com.android.weatherforecast.models.ItemHourly
import com.android.weatherforecast.ui.fivedayactivity.viewholder.FiveDayViewHolder


/**
 * Adapter class [RecyclerView.Adapter] for [RecyclerView] which binds [Post] along with [PostViewHolder]
 * @param onItemClickListener Listener which will receive callback when item is clicked.
 */
class FiveDayListAdapter(private val onItemClickListener: OnItemClickListener) :


    ListAdapter<FiveDaysWeather, FiveDayViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FiveDayViewHolder(
        ItemFiveDayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: FiveDayViewHolder, position: Int) =
        holder.bind(getItem(position),position, onItemClickListener)

    interface OnItemClickListener {
        fun onItemClicked(post: FiveDaysWeather, view: View)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FiveDaysWeather>() {
            override fun areItemsTheSame(oldItem: FiveDaysWeather, newItem: FiveDaysWeather): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: FiveDaysWeather, newItem: FiveDaysWeather): Boolean =
                oldItem == newItem

        }
    }
}
