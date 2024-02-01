package com.bitcodetech.machine_test_user.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.machine_test_user.R
import com.bitcodetech.machine_test_user.databinding.UserViewBinding
import com.bitcodetech.machine_test_user.models.User

class UserAdapter(
    private val users : ArrayList<User>
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    //private lateinit var binding : UserViewBinding

    interface OnUserClickListener {
        fun onUserClick(user: User, position: Int, userAdapter: UserAdapter)
    }

    var onUserClickListener : OnUserClickListener? = null

    inner class UserViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val binding : UserViewBinding

        init {
            binding = UserViewBinding.bind(view)
            binding.root.setOnClickListener {
                onUserClickListener?.onUserClick(
                    users[adapterPosition],
                    adapterPosition,
                    this@UserAdapter
                )
            }
        }
    }

    override fun getItemCount() = users.size

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_view, null)
            return UserViewHolder(view)
        /*val view = UserViewBinding.inflate(LayoutInflater.from(parent.context), parent, false).root
        return UserViewHolder(view)*/
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]

        holder.binding.txtFisrtName.text = user.name.firstname
        holder.binding.txtLastName.text = user.name.lastname
        holder.binding.txtUserName.text = user.username
    }
}