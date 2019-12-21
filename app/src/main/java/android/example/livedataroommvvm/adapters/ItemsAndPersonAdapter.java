package android.example.livedataroommvvm.adapters;

import android.example.livedataroommvvm.R;
import android.example.livedataroommvvm.model.PersonModel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemsAndPersonAdapter extends RecyclerView.Adapter<ItemsAndPersonAdapter.ViewHolder> {

    private List<PersonModel> personModelList;
    private View.OnLongClickListener onLongClickListener;

    public ItemsAndPersonAdapter(List<PersonModel> personModelList, View.OnLongClickListener onLongClickListener)
    {
        this.personModelList = personModelList;
        this.onLongClickListener = onLongClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_n_person_listitem_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PersonModel personModel = personModelList.get(position);
        holder.snoTextView.setText(personModel.id+" ");
        holder.itemTextView.setText(personModel.getItemName());
        holder.nameTextView.setText(personModel.getPersonName());
        holder.itemView.setOnLongClickListener(onLongClickListener);
        holder.itemView.setTag(personModel);
    }

    @Override
    public int getItemCount() {
        return personModelList.size();
    }

    public void addItems(List<PersonModel> personModelList) {
        this.personModelList = personModelList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView snoTextView;
        private TextView itemTextView;
        private TextView nameTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTextView =  itemView.findViewById(R.id.itemTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            snoTextView = itemView.findViewById(R.id.snoTextView);
        }
    }
}
