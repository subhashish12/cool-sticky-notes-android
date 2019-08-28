package com.xyz.coolstickynotes.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xyz.coolstickynotes.Entity.Note;
import com.xyz.coolstickynotes.R;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {
    private OnItemClickListener listener;

    public static String DELETE="DELETE";
    public static String SHARE="SHARE";
    public static String EDIT="EDIT";

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(Note oldItem, Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Note oldItem, Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription());
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
        holder.textViewDescription.setBackgroundColor(currentNote.getColor());
        holder.date.setText(currentNote.getDate());
        holder.sticker.setBackgroundResource(currentNote.getSticker());
        holder.cardView.setCardBackgroundColor(currentNote.getColor());
    }

    public Note getNoteAt(int position) {
        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        private TextView textViewTitle;
        private TextView textViewDescription;
        TextView date;
        ImageView sticker;
        ImageButton edit,share,delete;

        public NoteHolder(final View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.roorCardView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            date=itemView.findViewById(R.id.text_view_date);
            sticker=itemView.findViewById(R.id.ivSticker);
            edit=itemView.findViewById(R.id.edit);
            share=itemView.findViewById(R.id.share);
            delete=itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    operation(getAdapterPosition(),EDIT);
                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    operation(getAdapterPosition(),SHARE);
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  operation(getAdapterPosition(),DELETE);
                }
            });


        }
    }

    private void operation(int position,String operation){
        if (listener != null && position != RecyclerView.NO_POSITION) {
            listener.onOptionClick(position,operation);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Note note);
        void onOptionClick(int position,String operation);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}