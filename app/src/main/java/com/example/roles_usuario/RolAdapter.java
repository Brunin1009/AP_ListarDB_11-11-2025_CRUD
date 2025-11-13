package com.example.roles_usuario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class RolAdapter extends RecyclerView.Adapter<RolAdapter.RolViewHolder> {

    private ArrayList<Rol> listaRoles;
    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    public RolAdapter(ArrayList<Rol> listaRoles) {
        this.listaRoles = listaRoles != null ? listaRoles : new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.longClickListener = listener;
    }

    @NonNull
    @Override
    public RolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rol, parent, false);
        return new RolViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RolViewHolder holder, int position) {
        Rol rol = listaRoles.get(position);
        holder.tvNombreRol.setText(rol.getNombre());
        holder.tvDescripcionRol.setText(rol.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return listaRoles.size();
    }

    public void actualizarLista(ArrayList<Rol> nuevaLista) {
        this.listaRoles = nuevaLista != null ? nuevaLista : new ArrayList<>();
        notifyDataSetChanged();
    }

    public Rol getRol(int position) {
        if (position >= 0 && position < listaRoles.size()) {
            return listaRoles.get(position);
        }
        return null;
    }

    class RolViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView tvNombreRol;
        MaterialTextView tvDescripcionRol;

        public RolViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreRol = itemView.findViewById(R.id.tvNombreRol);
            tvDescripcionRol = itemView.findViewById(R.id.tvDescripcionRol);

            itemView.setOnClickListener(v -> {
                if (clickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        clickListener.onItemClick(position);
                    }
                }
            });

            itemView.setOnLongClickListener(v -> {
                if (longClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        longClickListener.onItemLongClick(position);
                        return true;
                    }
                }
                return false;
            });
        }
    }
}

