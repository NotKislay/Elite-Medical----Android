package com.elite.medical.admin.adapters.sidemenu.dashboard
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elite.medical.R
import com.elite.medical.retrofit.responsemodel.admin.sidemenu.dashboard.notifications.NotificationDetailsFromAdminNotificationsModel
import java.text.SimpleDateFormat
import java.util.Locale

class NotificationAdapter(private val cardItems: List<NotificationDetailsFromAdminNotificationsModel>, private val context: Context) :
    RecyclerView.Adapter<NotificationAdapter.ModelViewHolder>() {

    class ModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titlecontent: TextView = itemView.findViewById(R.id.title_content)
        val bodycontent: TextView = itemView.findViewById(R.id.body_content)
        val datecontent: TextView = itemView.findViewById(R.id.date_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_cards_notifn, parent, false)
        return ModelViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        val currentItem = cardItems[position]

        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault())
        val outputDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val outputTimeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        var datetimecontent=""
        try {
            val date= inputFormat.parse(currentItem.created_at)
            val formatteddate= outputDateFormat.format(date)
            val formattedtime= outputTimeFormat.format(date)
            datetimecontent = "$formatteddate $formattedtime"
        } catch (e : Exception){
            e.printStackTrace()
        }
        holder.titlecontent.text = currentItem.title
        holder.bodycontent.text= currentItem.body
        holder.datecontent.text= datetimecontent
    }

    override fun getItemCount() = cardItems.size
}

