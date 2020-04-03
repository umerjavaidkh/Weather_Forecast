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

package com.android.weatherforecast.ui.selection_activity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.weatherforecast.databinding.ItemCityLocationBinding
import com.android.weatherforecast.databinding.ItemFiveDayBinding
import com.android.weatherforecast.models.CurrentWeather
import com.android.weatherforecast.models.ItemHourly
import com.android.weatherforecast.ui.fivedayactivity.viewholder.CurrentCityViewHolder
import com.android.weatherforecast.ui.fivedayactivity.viewholder.FiveDayViewHolder


/**
 * Adapter class [RecyclerView.Adapter] for [RecyclerView] which binds [Post] along with [PostViewHolder]
 * @param onItemClickListener Listener which will receive callback when item is clicked.
 */
class CityListAdapter(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<CurrentWeather, CurrentCityViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CurrentCityViewHolder(
        ItemCityLocationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CurrentCityViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClickListener)

    interface OnItemClickListener {
        fun onItemClicked(post: CurrentWeather, view: View)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CurrentWeather>() {
            override fun areItemsTheSame(oldItem: CurrentWeather, newItem: CurrentWeather): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CurrentWeather, newItem: CurrentWeather): Boolean =
                oldItem == newItem

        }
    }
}
